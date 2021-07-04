package ilion.terrafos.animais.negocio;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.Pasto;
import ilion.terrafos.forragens.negocio.MedicaoDeForragemDTO;
import ilion.terrafos.forragens.negocio.MedicaoForragemNegocio;
import ilion.terrafos.forragens.negocio.MedicaoForragemTipo;
import ilion.terrafos.relatorios.negocio.LogDeAcao;
import ilion.terrafos.relatorios.negocio.LogDeAcaoBuilder;
import ilion.terrafos.relatorios.negocio.LogDeAcaoThread;
import ilion.terrafos.relatorios.negocio.LogDeAcaoTipo;
import ilion.util.Uteis;
import ilion.util.persistencia.HibernateUtil;

@Service
public class LoteNegocio {
	
	static Logger logger = Logger.getLogger(LoteNegocio.class);
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private PesoMedioNegocio pesoMedioNegocio;
	
	@Autowired
	private MedicaoForragemNegocio medicaoForragemNegocio;
	
	@Transactional
	public void salvar(FormacaoDeLoteDTO formacaoDeLote, Usuario usuario) throws Exception {
		
		Lote lote = formacaoDeLote.toLote(usuario);
		
		lote.ehValido();
		
		PesoMedio pesoMedio = formacaoDeLote.toPesoMedio(usuario);
		
		pesoMedio.ehValido();
		
		salvar(lote, usuario);
		
		pesoMedioNegocio.salvar(pesoMedio, usuario);
		
		LogDeAcao logDeAcao = new LogDeAcaoBuilder()
				.doTipo(LogDeAcaoTipo.FORMACAO_DE_LOTES)
				.doPasto(lote.getPasto())
				.doUsuario(usuario)
				.build();
		
		LogDeAcaoThread.incluiLog(logDeAcao);
		
	}
	
	@Transactional
	public void salvar(Lote lote, Usuario usuario) throws Exception {
		
		lote.ehValido();
		
		if( lote.getId() == null ) {
			lote = (Lote) hibernateUtil.save(lote);
		} else {
			hibernateUtil.update(lote);
		}
		
	}
	
	@Transactional
	public void salvar(MovimentoDeLoteDTO movimentoDeLote, Usuario usuario) throws Exception {
		
		salvarMovimentoDeLote(movimentoDeLote, usuario);
		
		salvarPesoMedio(movimentoDeLote, usuario);
		
	}
	
	@Transactional
	private void salvarMovimentoDeLote(MovimentoDeLoteDTO movimentoDeLote, Usuario usuario) throws Exception {
		
		List<Lote> lotes = movimentoDeLote.toLotes(usuario);
		
		for (Lote lote : lotes) {
			
			lote.ehValido();
			
		}
		
		for (Lote lote : lotes) {
			
			salvar(lote, usuario);
			
		}
		
		if( ! lotes.isEmpty() ) {
			Lote lote = lotes.get(0);

			LogDeAcao logDeAcao = new LogDeAcaoBuilder()
					.doTipo(LogDeAcaoTipo.MOVIMENTACAO_DE_LOTES)
					.doPasto(lote.getPasto())
					.doUsuario(usuario)
					//.comInfo("status", entreveroDTO.getStatus())
					.build();
			
			LogDeAcaoThread.incluiLog(logDeAcao);
		}
		
		MedicaoDeForragemDTO medicaoDeForragemDTO = new MedicaoDeForragemDTO();
		medicaoDeForragemDTO.setAltura(movimentoDeLote.getAlturaEntradaForrageira());
		medicaoDeForragemDTO.setIdPasto(movimentoDeLote.getIdPastoDestino());
		medicaoDeForragemDTO.setTipo(MedicaoForragemTipo.ENTRADA);
		
		medicaoForragemNegocio.incluir(medicaoDeForragemDTO, usuario);
	}
	
	@Transactional
	private void salvarPesoMedio(MovimentoDeLoteDTO movimentoDeLote, Usuario usuario) throws Exception {
		
		List<PesoMedio> pms = movimentoDeLote.toPesoMedios(usuario);
		
		for (PesoMedio pm : pms) {
			
			pesoMedioNegocio.salvar(pm, usuario);
			
		}
		
	}
	
	public List<Map<String, Object>> listarSaldoDeAnimaisPorPasto(Pasto pasto) {
		
		String sql = "";
		sql += " select sum(lote.valor) total, lote.categoriaanimal_id \"idCategoriaAnimal\", c.nome ";
		sql += " from terrafos_lote lote ";
		sql += " join terrafos_categoria_animal c on c.id=lote.categoriaanimal_id ";
		sql += " where lote.pasto_id="+pasto.getId();
		sql += " group by lote.categoriaanimal_id, lote.pasto_id, c.nome ";
		sql += " order by total desc ";
		
		return hibernateUtil.listarSQLMap(sql);
	}
	
	public List<Map<String, Object>> listarSaldoDeAnimaisComPesoMedio(Pasto pasto) {
		
		String sql = "";
		sql += " select ca.nome categoria_animal, sum(lote.valor) saldo, pm.valor as peso_medio ";
		sql += " from terrafos_lote lote  ";
		sql += " join terrafos_pasto p on p.id=lote.pasto_id "; 
		sql += " join terrafos_categoria_animal ca on ca.id=lote.categoriaanimal_id "; 
		sql += " join ( select pm.*  ";
		sql += " 	from terrafos_peso_medio pm "; 
		sql += " 	join (select categoriaanimal_id, max(datacriacao) as datacriacao from terrafos_peso_medio group by categoriaanimal_id) ultima on ultima.categoriaanimal_id=pm.categoriaanimal_id and ultima.datacriacao=pm.datacriacao) pm on pm.categoriaanimal_id=lote.categoriaanimal_id "; 
		sql += "  where lote.pasto_id="+pasto.getId();
		sql += "  group by lote.categoriaanimal_id, ca.nome, pm.valor ";
		
		return hibernateUtil.listarSQLMap(sql);
	}
	
	public Double calcularLotacaoMedia(Double saldoDeAnimais, Double pesoMedio, Float areaTotal) {
		
		Double lotacaoMedia = 0d;

		try {
			
			
			if( areaTotal > 0 ) {
				lotacaoMedia = ((pesoMedio*saldoDeAnimais)/450)/areaTotal;
				
				lotacaoMedia = Uteis.converterDoubleDoisDecimais(lotacaoMedia);
			}
			
		} catch (Exception e) {
			logger.error("Erro ao calcularLotacaoMedia saldoDeAnimais: "+saldoDeAnimais+", pesoMedio: "+pesoMedio+", areaTotal: "+areaTotal, e);
		}
		
		return lotacaoMedia;
	}
	
	public Double calcularPesoMedio(Pasto pasto) {
		
		Double pesoMedio = 0d;

		try {
			
			Double totalPesoMedioXSaldo = 0d;
			Double totalDeAnimais = 0d;
			
			List<Map<String, Object>> saldoDeAnimaisComPM = listarSaldoDeAnimaisComPesoMedio(pasto);
			
			for (Map<String, Object> m : saldoDeAnimaisComPM) {
				
				Number saldo = (Number) m.get("saldo");
				Number peso_medio = (Number) m.get("peso_medio");
				
				totalDeAnimais += saldo.doubleValue();
				totalPesoMedioXSaldo += saldo.doubleValue()*peso_medio.doubleValue();
				
			}
			
			if( totalDeAnimais > 0 ) {
				pesoMedio = totalPesoMedioXSaldo/totalDeAnimais;
				
				pesoMedio = Uteis.converterDoubleDoisDecimais(pesoMedio);				
			}
			
		} catch (Exception e) {
			logger.error("Erro ao calcularPesoMedio do pasto.id: "+pasto.getId(), e);
		}
		
		return pesoMedio;
	}
	
	@Transactional
	public void baixas(BaixasDTO baixas, Usuario usuario) throws Exception {
		
		List<Lote> lotes = baixas.toLotes(usuario);
		
		for (Lote lote : lotes) {
			
			lote.ehValido();
			
		}
		
		for (Lote lote : lotes) {
			
			salvar(lote, usuario);
			
		}
		
		if( ! lotes.isEmpty() ) {
			Lote lote = lotes.get(0);

			LogDeAcao logDeAcao = new LogDeAcaoBuilder()
					.doTipo(LogDeAcaoTipo.BAIXAS)
					.doPasto(lote.getPasto())
					.doUsuario(usuario)
					//.comInfo("status", entreveroDTO.getStatus())
					.build();
			
			LogDeAcaoThread.incluiLog(logDeAcao);
			
		}
		
	}
	
	@Transactional
	public void nascimentos(NascimentosDTO nascimentos, Usuario usuario) throws Exception {
		
		nascimentos.ehValido();
		
		Lote lote = nascimentos.toLote(usuario);
		
		salvar(lote, usuario);
		
		LogDeAcao logDeAcao = new LogDeAcaoBuilder()
				.doTipo(LogDeAcaoTipo.NASCIMENTOS)
				.doPasto(lote.getPasto())
				.doUsuario(usuario)
				//.comInfo("status", entreveroDTO.getStatus())
				.build();
		
		LogDeAcaoThread.incluiLog(logDeAcao);
		
	}
	
	public RelatorioMovimentacaoDeRebanhoVH relatorioMovimentacaoDeRebanho(Fazenda fazenda, Date dtInicio, Date dtFim) {
		
		String sql = "";
		sql += " select ca.nome categoria_animal, lote.tipo, sum(lote.valor) saldo "; 
		sql += " from terrafos_lote lote   ";
		sql += " join terrafos_pasto p on p.id=lote.pasto_id "; 
		sql += " join terrafos_categoria_animal ca on ca.id=lote.categoriaanimal_id "; 
		sql += " where lote.pasto_id in (select id from terrafos_pasto where retiro_id in (select id from terrafos_retiro where fazenda_id="+fazenda.getId()+")) ";
		
		if( dtInicio != null && dtFim != null ) {
			
			String dtInicioParam = Uteis.formatarDataHora(dtInicio, "yyyy-MM-dd HH:mm");
			dtFim = Uteis.fimDia(dtFim);
			String dtFimParam = Uteis.formatarDataHora(dtFim, "yyyy-MM-dd HH:mm");
			
			sql += " and lote.datacriacao between '"+dtInicioParam+"' and '"+dtFimParam+"'  ";
			
		}
		
		sql += " group by lote.categoriaanimal_id, lote.tipo, ca.nome  ";
		
		List<Map<String, Object>> registros = hibernateUtil.listarSQLMap(sql);
		
		RelatorioMovimentacaoDeRebanhoVH relatorio = RelatorioMovimentacaoDeRebanhoVH.from(registros);
		
		return relatorio;
	}
}