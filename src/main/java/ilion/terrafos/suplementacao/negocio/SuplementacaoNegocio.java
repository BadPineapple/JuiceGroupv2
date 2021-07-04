package ilion.terrafos.suplementacao.negocio;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.FazendaPastosNoMapaVH;
import ilion.terrafos.cadastros.negocio.Pasto;
import ilion.terrafos.cadastros.negocio.PastoMapaVH;
import ilion.terrafos.cadastros.negocio.PastoNegocio;
import ilion.terrafos.cadastros.negocio.Produto;
import ilion.terrafos.cadastros.negocio.ProdutoNegocio;
import ilion.terrafos.relatorios.negocio.LogDeAcao;
import ilion.terrafos.relatorios.negocio.LogDeAcaoBuilder;
import ilion.terrafos.relatorios.negocio.LogDeAcaoThread;
import ilion.terrafos.relatorios.negocio.LogDeAcaoTipo;
import ilion.util.Uteis;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;

@Service
public class SuplementacaoNegocio {
	
	static Logger logger = Logger.getLogger(SuplementacaoNegocio.class);
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private PastoNegocio pastoNegocio;
	
	@Autowired
	private ProdutoNegocio produtoNegocio;
	
	@Transactional
	public void incluir(SuplementacaoDTO suplementacaoDTO, Usuario usuario) {
		
		if( suplementacaoDTO.getIdPasto() == null ) {
			throw new ValidacaoException("Id do pasto deve ser preenchido.");
		}
		
		if( suplementacaoDTO.getStatus() == null ) {
			throw new ValidacaoException("Status deve ser preenchido.");
		}
		
		if( SuplementacaoStatus.COM_PRODUTO.equals(suplementacaoDTO.getStatus()) ) {
			if( suplementacaoDTO.getIdProduto() == null ) {
				throw new ValidacaoException("Produto deve ser selecionado.");
			}
			
			if( suplementacaoDTO.getQtd() == null ) {
				throw new ValidacaoException("Qtd. de produto deve ser preenchido.");
			}
		}
		
		logger.info("novo cadastro: "+suplementacaoDTO+", "+usuario);
		
		Suplementacao e = new Suplementacao();
		e.setPasto(new Pasto(suplementacaoDTO.getIdPasto()));
		e.setUsuario(usuario);
		e.setStatus(suplementacaoDTO.getStatus());
		
		if( SuplementacaoStatus.COM_PRODUTO.equals(suplementacaoDTO.getStatus()) ) {
			Produto produto = produtoNegocio.findById(suplementacaoDTO.getIdProduto());
			
			e.setProduto(produto);
			e.setQtd(suplementacaoDTO.getQtd().floatValue()*produto.getApresentacao().floatValue());
		}
		
		hibernateUtil.save(e);
		
		LogDeAcao logDeAcao = new LogDeAcaoBuilder()
				.doTipo(LogDeAcaoTipo.SUPLEMENTACAO)
				.doPasto(e.getPasto())
				.doUsuario(usuario)
				.build();
		
		LogDeAcaoThread.incluiLog(logDeAcao);
	}
	
	public FazendaPastosNoMapaVH monitoramento(Fazenda fazenda) {
		
		FazendaPastosNoMapaVH fazendaPastosNoMapaVH = pastoNegocio.listarRegioesDosPastos(fazenda);
		
		List<PastoMapaVH> pastos = fazendaPastosNoMapaVH.getPastos();
		
		List<Map<String, Object>> suplementacaos = listarUltimosRegistrosDeSuplementacaoDosPastos(fazenda);
		
		for (int i = 0; i < pastos.size(); i++) {
			PastoMapaVH p = pastos.get(i);
			
			p.setCor(PastoMapaVH.COR_ALERTA);
			
			for (Map<String, Object> m : suplementacaos) {
				Number idPasto = (Number) m.get("pasto_id");
				
				if( idPasto.longValue() == p.getIdPasto().longValue() ) {
					
					String status = (String) m.get("status");
					
					p.setCochoVazio( SuplementacaoStatus.VAZIO.name().equals(status) );
					
					if( ! p.getCochoVazio() ) {
						p.setCor(PastoMapaVH.COR_PADRAO);
					}
					
				}
				
			}
			
		}
		
		return fazendaPastosNoMapaVH;
	}
	
	public List<Map<String, Object>> listarUltimosRegistrosDeSuplementacaoDosPastos(Fazenda fazenda) {
		
		String sql = "";
		sql += " select e.status, e.pasto_id, p.nome pasto_nome "; 
		sql += " from terrafos_suplementacao e ";
		sql += " join (select max(datacriacao) as datacriacao, pasto_id from terrafos_suplementacao group by pasto_id) as maximos "; 
		sql += " on maximos.datacriacao=e.datacriacao and maximos.pasto_id=e.pasto_id ";
		sql += " join terrafos_pasto p on p.id=e.pasto_id ";
		sql += " where e.pasto_id in (select id from terrafos_pasto where retiro_id in (select id from terrafos_retiro where fazenda_id="+fazenda.getId()+")) ";
		
		List<Map<String, Object>> suplementacaos = hibernateUtil.listarSQLMap(sql);
		
		return suplementacaos;
	}
	
	public List<Map<String, Object>> relatorioConsumoSuplementacao(Fazenda fazenda, Date dtInicio, Date dtFim) {
		
		if( dtInicio == null || dtFim == null ) {
			return Collections.emptyList();
		}
		
		String dtInicioParam = Uteis.formatarDataHora(dtInicio, "yyyy-MM-dd HH:mm");
		dtFim = Uteis.fimDia(dtFim);
		String dtFimParam = Uteis.formatarDataHora(dtFim, "yyyy-MM-dd HH:mm");
		
		String sql = "";
		sql += " select pr.nome produto_nome, sum(e.qtd) consumo ";
		sql += " from terrafos_suplementacao e  ";
		sql += " join terrafos_pasto p on p.id=e.pasto_id "; 
		sql += " join terrafos_produto pr on pr.id=e.produto_id ";
		sql += " where e.pasto_id in (select id from terrafos_pasto where retiro_id in (select id from terrafos_retiro where fazenda_id="+fazenda.getId()+")) and ";
		sql += " e.status='COM_PRODUTO' and ";
		sql += " e.datacriacao between '"+dtInicioParam+"' and '"+dtFimParam+"' ";
		sql += " group by e.produto_id, pr.nome ";
		
		List<Map<String, Object>> suplementacaos = hibernateUtil.listarSQLMap(sql);
		
		return suplementacaos;
	}
}
