package ilion.terrafos.forragens.negocio;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.FazendaPastosNoMapaVH;
import ilion.terrafos.cadastros.negocio.Forrageira;
import ilion.terrafos.cadastros.negocio.ForrageiraNegocio;
import ilion.terrafos.cadastros.negocio.Pasto;
import ilion.terrafos.cadastros.negocio.PastoMapaVH;
import ilion.terrafos.cadastros.negocio.PastoNegocio;
import ilion.terrafos.relatorios.negocio.LogDeAcao;
import ilion.terrafos.relatorios.negocio.LogDeAcaoBuilder;
import ilion.terrafos.relatorios.negocio.LogDeAcaoThread;
import ilion.terrafos.relatorios.negocio.LogDeAcaoTipo;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;

@Service
public class MedicaoForragemNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private PastoNegocio pastoNegocio;
	
	@Autowired
	private ForrageiraNegocio forrageiraNegocio;
	
	@Transactional
	public void incluir(MedicaoDeForragemDTO medicaoDeForragemDTO, Usuario usuario) {
		
		if( medicaoDeForragemDTO.getIdPasto() == null ) {
			throw new ValidacaoException("Id do pasto deve ser preenchido.");
		}
		
		if( medicaoDeForragemDTO.getTipo() == null ) {
			throw new ValidacaoException("Tipo da forragem deve ser preenchido.");
		}
		
		if( medicaoDeForragemDTO.getAltura() == null ) {
			throw new ValidacaoException("Altura da forragem deve ser preenchido.");
		}
		
		Pasto pasto = (Pasto) hibernateUtil.findById(Pasto.class, medicaoDeForragemDTO.getIdPasto());
		
		MedicaoForragem mf = new MedicaoForragem();
		mf.setAltura(medicaoDeForragemDTO.getAltura());
		mf.setForrageira(pasto.getForrageira());
		mf.setPasto(pasto);
		mf.setTipo(medicaoDeForragemDTO.getTipo());
		mf.setUsuario(usuario);
		
		hibernateUtil.save(mf);
		
		LogDeAcao logDeAcao = new LogDeAcaoBuilder()
				.doTipo(LogDeAcaoTipo.MEDICAO_DE_FORRAGEM)
				.doPasto(mf.getPasto())
				.doUsuario(usuario)
				.build();
		
		LogDeAcaoThread.incluiLog(logDeAcao);
	}

	public FazendaPastosNoMapaVH monitoramento(Fazenda fazenda) {
		
		FazendaPastosNoMapaVH fazendaPastosNoMapaVH = pastoNegocio.listarRegioesDosPastos(fazenda);
		
		List<PastoMapaVH> pastos = fazendaPastosNoMapaVH.getPastos();
		
		List<Map<String, Object>> medicoes = listarUltimasMedicoesDosPastos(fazenda);
		
		for (int i = 0; i < pastos.size(); i++) {
			PastoMapaVH p = pastos.get(i);
			
			Forrageira forrageira = forrageiraNegocio.findById(p.getIdForrageira());
			
			p.getAlturaDasForragens().put("ENTRADA_PADRAO", forrageira.getEntrada());
			p.getAlturaDasForragens().put("SAIDA_PADRAO", forrageira.getSaida());
			
			for (Map<String, Object> m : medicoes) {
				Number idPasto = (Number) m.get("pasto_id");
				
				if( idPasto.longValue() == p.getIdPasto().longValue() ) {
					
					String tipo = (String) m.get("tipo");
					Number altura = (Number) m.get("altura");
					
					p.getAlturaDasForragens().put(tipo, altura.intValue());
					
				}
				
			}
			
			p.calcularAlturaDasForragens();
			
		}
		
		return fazendaPastosNoMapaVH;
	}
	
	public List<Map<String, Object>> listarUltimasMedicoesDosPastos(Fazenda fazenda) {
		
		String sql = "";
		sql += " select m.tipo, altura, m.pasto_id, p.nome pasto_nome ";
		sql += " from terrafos_medicao_forragem m ";
		sql += " join (select max(datacriacao) as datacriacao, pasto_id, tipo from terrafos_medicao_forragem group by pasto_id, tipo) as maximos "; 
		sql += " on maximos.datacriacao=m.datacriacao and maximos.pasto_id=m.pasto_id ";
		sql += " join terrafos_pasto p on p.id=m.pasto_id ";
		sql += " where m.pasto_id in (select id from terrafos_pasto where retiro_id in (select id from terrafos_retiro where fazenda_id="+fazenda.getId()+")) ";
		
		List<Map<String, Object>> medicoes = hibernateUtil.listarSQLMap(sql);
		
		return medicoes;
	}
}
