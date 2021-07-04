package ilion.terrafos.cadastros.negocio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.terrafos.animais.negocio.LoteNegocio;
import ilion.util.Uteis;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.json.GSONUteis;
import ilion.util.persistencia.HibernateUtil;

@Service
public class PastoNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private LoteNegocio loteNegocio;
	
//	@Autowired
//	private IEntidadeImagemNegocio entidadeImagemNegocio;
	
	public List<Pasto> listar(Fazenda fazenda) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Pasto.class);
		
		dc.createAlias("retiro", "r");
		dc.add(Restrictions.eq("r.fazenda", fazenda));
		
		dc.addOrder(Order.asc("nome"));
		
		List<Pasto> retiros = (List<Pasto>) hibernateUtil.list(dc);
		
		return retiros;
	}
	
	public Pasto findById(Long id) {
		return (Pasto) hibernateUtil.findById(Pasto.class, id);
	}
	
	@Transactional
	public Pasto incluirAtualizar(Pasto pasto) throws Exception {
		
		if( pasto.getRetiro() == null || pasto.getRetiro().getId() == null ) {
			throw new ValidacaoException("Retiro deve ser selecionado.");
		}
		
		if( Uteis.ehNuloOuVazio(pasto.getNome()) ) {
			throw new ValidacaoException("Nome deve ser definido.");
		}
		
		if( pasto.getForrageira() == null || pasto.getForrageira().getId() == null ) {
			throw new ValidacaoException("Forrageira deve ser selecionada.");
		}
		
		if( pasto.getId() == null ) {
			pasto = (Pasto) hibernateUtil.save(pasto);
		} else {
			hibernateUtil.update(pasto);
		}
		
		return pasto;
	}
	
	@Transactional
	public void incluirAtualizar(AvaliacaoDosPastos avaliacaoDosPastos) throws Exception {
		
		for (Pasto pasto : avaliacaoDosPastos.getPastos()) {
			
			incluirAtualizar(pasto);
			
		}
		
	}
	
	public FazendaPastosNoMapaVH listarRegioesDosPastos(Fazenda fazenda) {
		
		fazenda = (Fazenda) hibernateUtil.findById(Fazenda.class, fazenda.getId());
		
		FazendaPastosNoMapaVH fazendaPastosNoMapaVH = new FazendaPastosNoMapaVH();
		
		if( ! Uteis.ehNuloOuVazio(fazenda.getGoogleMapsJson()) ) {
			fazendaPastosNoMapaVH = GSONUteis.getInstance().fromJson(fazenda.getGoogleMapsJson(), FazendaPastosNoMapaVH.class);
		}
		
		DetachedCriteria dc = DetachedCriteria.forClass(Pasto.class);
		
		dc.createAlias("retiro", "r");
		dc.add(Restrictions.eq("r.fazenda", fazenda));
		
		dc.add(Restrictions.isNotNull("googleMapsJson"));
		
		dc.addOrder(Order.asc("nome"));
		
		List<Pasto> pastos = (List<Pasto>) hibernateUtil.list(dc);
		
		List<PastoMapaVH> pastosMapa = new ArrayList<>();
		
		for (Pasto pasto : pastos) {
			
			if( Uteis.ehNuloOuVazio(pasto.getGoogleMapsJson()) ) {
				continue;
			}
			
			Map<String, Object> totalDeAnimaisPM = consultarTotalAnimaisPesoMedio(pasto);
			
			PastoMapaVH pastoMapaVH = GSONUteis.getInstance().fromJson(pasto.getGoogleMapsJson(), PastoMapaVH.class);
			pastoMapaVH.setIdPasto(pasto.getId());
			pastoMapaVH.setNome(pasto.getNome());
			pastoMapaVH.setAreaTotal( Uteis.formatarValorMoedaPTBR(pasto.getAreaTotal()) );
			pastoMapaVH.setIdForrageira(pasto.getForrageira().getId());
			
			Number totalDeAnimais = (Number) totalDeAnimaisPM.get("total_de_animais");
			if( totalDeAnimais != null ) {
				pastoMapaVH.setTotalDeAnimais(totalDeAnimais.intValue());
			}
			
			Double pesoMedio = loteNegocio.calcularPesoMedio(pasto);
			if( pesoMedio != null ) {
				pastoMapaVH.setPesoMedio(pesoMedio.intValue());
			}
			
			List<Map<String, Object>> categoriaAnimaisQtd = loteNegocio.listarSaldoDeAnimaisPorPasto(pasto);
			pastoMapaVH.setCategoriaAnimaisQtd(categoriaAnimaisQtd);
			
			pastosMapa.add(pastoMapaVH);
			
		}
		
		fazendaPastosNoMapaVH.setPastos(pastosMapa);
		
		return fazendaPastosNoMapaVH;
	}
	
	public List<Pasto> listar(Retiro retiro) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Pasto.class);
		
		dc.add(Restrictions.eq("retiro", retiro));
		
		dc.addOrder(Order.asc("nome"));
		
		List<Pasto> pastos = (List<Pasto>) hibernateUtil.list(dc);

		return pastos;
	}
	
	public Map<String, Object> consultarTotalAnimaisPesoMedio(Pasto pasto) {
		
		String sql = "";
		sql += " select (select sum(lote.valor) total from terrafos_lote lote where lote.pasto_id="+pasto.getId()+") total_de_animais, "; 
		sql += " (select max(pm.valor) pesomedio from terrafos_peso_medio pm where pm.pasto_id="+pasto.getId()+") peso_medio ";
		
		return hibernateUtil.uniqueResultMapSQL(sql);
	}
	
	public Pasto consultarPorIdComSaldos(Pasto pasto) {
		
		pasto = findById(pasto.getId());
		
		Map<String, Object> totalDeAnimaisPM = consultarTotalAnimaisPesoMedio(pasto);
		
		Number totalDeAnimais = (Number) totalDeAnimaisPM.get("total_de_animais");
		if( totalDeAnimais == null ) {
			totalDeAnimais = 0;
		}
		pasto.setTotalDeAnimais(totalDeAnimais.intValue());
		
		Double pesoMedio = loteNegocio.calcularPesoMedio(pasto);
		if( pesoMedio == null ) {
			pesoMedio = 0d;
		}
		pasto.setPesoMedio(pesoMedio.intValue());
		
		Double lotacaoMedia = loteNegocio.calcularLotacaoMedia(totalDeAnimais.doubleValue(), pesoMedio, pasto.getAreaTotal());
		
		pasto.setLotacaoMedia(lotacaoMedia);
		
		return pasto;
	}
	
	public List<Map<String, Object>> consultarDistribuicaoDasForragens(Fazenda fazenda) {
		
		String sql = "  ";
		sql += " select f.especie, sum(p.areaTotal) as total ";
		sql += " from terrafos_pasto p ";
		sql += " join terrafos_forrageira f on f.id=p.forrageira_id ";
		sql += " where p.id in (select id from terrafos_pasto where retiro_id in (select id from terrafos_retiro where fazenda_id="+fazenda.getId()+")) ";
		sql += " group by f.especie ";
		
		List<Map<String, Object>> rows = hibernateUtil.listarSQLMap(sql);
		
//		List<String> xData = new ArrayList<String>();
//		List<Double> yData = new ArrayList<Double>();
//		
//		for (Map<String, Object> m : rows) {
//			
//			String nome = (String) m.get("especie");
//			Double escore = (Double) m.get("total");
//			
//			xData.add(nome);
//			yData.add(escore);
//			
//		}
//		
//		return new Object[] { xData, yData };
		return rows;
	}
	
	public List<Map<String, Object>> consultarEscoreDasInvasoras(Fazenda fazenda) {
		
		String sql = "";
		sql += " select p.avaliacao_escore_invasoras, count(p.avaliacao_escore_invasoras) as total "; 
		sql += " from terrafos_pasto p  ";
		sql += " where p.id in (select id from terrafos_pasto where retiro_id in (select id from terrafos_retiro where fazenda_id="+fazenda.getId()+")) "; 
		sql += " group by p.avaliacao_escore_invasoras  ";
		
		List<Map<String, Object>> rows = hibernateUtil.listarSQLMap(sql);
		
		for (Map<String, Object> m : rows) {
			
			Number avaliacao_escore_invasoras = (Number) m.get("avaliacao_escore_invasoras");
			//Number total = (Number) m.get("total");
			
			String label = "";
			
			switch (avaliacao_escore_invasoras.intValue()) {
			case 0:
				label = "Limpo";
				break;
			case 1:
				label = "Até 10%";
				break;
			case 2:
				label = "Entre 10 a 30%";
				break;
			case 3:
				label = "Entre 30 a 60%";
				break;
			case 4:
				label = "Entre 60 a 80%";
				break;
			case 5:
				label = "Acima de 80%";
				break;
			default:
				label = "?";
				break;
			}
			
			m.put("label", label);
		}
		
		return rows;
	}
	
	public Object[] consultarAguadasPorTipo(Fazenda fazenda) {
		
		String sql = " select avaliacao_aguadas, count(avaliacao_aguadas) qtd ";
		sql += " from terrafos_pasto ";
		sql += " where id in (select id from terrafos_pasto where retiro_id in (select id from terrafos_retiro where fazenda_id="+fazenda.getId()+")) ";
		sql += " and avaliacao_aguadas in ('Artificial', 'Natural') ";
		sql += " group by avaliacao_aguadas ";
		
		List<Map<String, Object>> rows = hibernateUtil.listarSQLMap(sql);
		
		List<String> xData = new ArrayList<String>();
		List<Integer> yData = new ArrayList<Integer>();
		
		for (Map<String, Object> m : rows) {
			
			String aguada = (String) m.get("avaliacao_aguadas");
			Integer qtd = ((Number) m.get("qtd")).intValue();
			
			xData.add(aguada);
			yData.add(qtd);
			
		}
		
		return new Object[] { xData, yData };
	}
	
	public Object[] consultarAreas(Fazenda fazenda) {
		
		String sql = "  ";
		sql += " select sum(p.areatotal) areatotal, sum(p.aee_ha) aee_ha ";
		sql += " from (select p.areatotal, p.avaliacao_aee, (p.areatotal*p.avaliacao_aee/100) as aee_ha ";
		sql += " from terrafos_pasto p  ";
		sql += " where id in (select id from terrafos_pasto where retiro_id in (select id from terrafos_retiro where fazenda_id="+fazenda.getId()+"))) p "; 
		
		List<Map<String, Object>> rows = hibernateUtil.listarSQLMap(sql);
		
		List<Double> valores = new ArrayList<Double>();
		
		//DecimalFormat df2 = new DecimalFormat("#.##");
		
		for (Map<String, Object> m : rows) {
			
			Double area = ((Number) m.get("areatotal")).doubleValue();
			Double aee_ha = ((Number) m.get("aee_ha")).doubleValue();
			
			BigDecimal areaTotal = new BigDecimal(area).setScale(2, RoundingMode.HALF_DOWN);
			BigDecimal aeeHa = new BigDecimal(aee_ha).setScale(2, RoundingMode.HALF_DOWN);
			
			valores.add(areaTotal.doubleValue());
			valores.add(aeeHa.doubleValue());
			
		}
		
		List<String> labels = Arrays.asList( "Área", "AEE (ha)" );
		
		return new Object[] { labels, valores };
	}
	
	public Object[] consultarQualidadeDasAguadas(Fazenda fazenda) {
		
		String sql = " select avaliacao_qualidade_aguadas, count(avaliacao_qualidade_aguadas) qtd ";
		sql += " from terrafos_pasto ";
		sql += " where id in (select id from terrafos_pasto where retiro_id in (select id from terrafos_retiro where fazenda_id="+fazenda.getId()+")) ";
		sql += " and avaliacao_qualidade_aguadas in ('Boa', 'Ruim') ";
		sql += " group by avaliacao_qualidade_aguadas ";
		
		List<Map<String, Object>> rows = hibernateUtil.listarSQLMap(sql);
		
		List<String> xData = new ArrayList<String>();
		List<Integer> yData = new ArrayList<Integer>();
		
		for (Map<String, Object> m : rows) {
			
			String aguada = (String) m.get("avaliacao_qualidade_aguadas");
			Integer qtd = ((Number) m.get("qtd")).intValue();
			
			xData.add(aguada);
			yData.add(qtd);
			
		}
		
		return new Object[] { xData, yData };
	}
	
	public Boolean haPastoVinculado(Forrageira forrageira) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Pasto.class);
		
		dc.add(Restrictions.eq("forrageira", forrageira));
		
		return hibernateUtil.possuiRegistros(dc);
	}
}