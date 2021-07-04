package ilion.terrafos.cadastros.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ilion.terrafos.animais.negocio.LoteNegocio;
import ilion.terrafos.cadastrospadroes.negocio.CategoriaAnimalPadraoNegocio;
import ilion.terrafos.cadastrospadroes.negocio.ForrageiraPadraoNegocio;
import ilion.terrafos.cadastrospadroes.negocio.ProdutoPadraoNegocio;
import ilion.util.CacheUtil;
import ilion.util.Uteis;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;

@Service
public class FazendaNegocio {
	
	@Autowired
	private HibernateUtil hibernateUtil;
	
	@Autowired
	private ForrageiraPadraoNegocio forrageiraPadraoNegocio;
	
	@Autowired
	private CategoriaAnimalPadraoNegocio categoriaAnimalPadraoNegocio;
	
	@Autowired
	private ProdutoPadraoNegocio produtoPadraoNegocio;
	
	@Autowired
	private RetiroNegocio retiroNegocio;
	
	@Autowired
	private PastoNegocio pastoNegocio;
	
	@Autowired
	private LoteNegocio loteNegocio;

	@Autowired
	private CategoriaAnimalNegocio categoriaAnimalNegocio;
	
	@Autowired
	private RacaNegocio racaNegocio;
	
	@Autowired
	private ProdutoNegocio produtoNegocio;
	
	public List<Fazenda> listar() {
		
		DetachedCriteria dc = DetachedCriteria.forClass(Fazenda.class);
		
		dc.addOrder(Order.asc("nome"));
		
		List<Fazenda> categorias = (List<Fazenda>) hibernateUtil.list(dc);
		
		return categorias;
	}
	
	public Fazenda findById(Long id) {
		return (Fazenda) hibernateUtil.findById(Fazenda.class, id);
	}
	
	@Transactional
	public Fazenda incluirAtualizar(Fazenda fazenda) throws Exception {
		
		if( Uteis.ehNuloOuVazio(fazenda.getNome()) ) {
			throw new ValidacaoException("Nome deve ser definido.");
		}
		
		if( Uteis.ehNuloOuVazio(fazenda.getProprietario()) ) {
			throw new ValidacaoException("Proprietário deve ser definido.");
		}

		if( Uteis.ehNuloOuVazio(fazenda.getMunicipio()) ) {
			throw new ValidacaoException("Município deve ser definido.");
		}
		
		if( Uteis.ehNuloOuVazio(fazenda.getUf()) ) {
			throw new ValidacaoException("UF deve ser definido.");
		}
		
		if( fazenda.getId() == null ) {

			fazenda = (Fazenda) hibernateUtil.save(fazenda);
			incluirCadastrosPadroes(fazenda);
			
		} else {
			
			hibernateUtil.update(fazenda);
			
		}
		
		
		CacheUtil.getInstance().resetAllCaches();
		
		return fazenda;
	}
	
	@Transactional
	public void incluirCadastrosPadroes(Fazenda fazenda) throws Exception {
		
		forrageiraPadraoNegocio.cadastroInicial(fazenda);
		
		categoriaAnimalPadraoNegocio.cadastroInicial(fazenda);
		
		produtoPadraoNegocio.cadastroInicial(fazenda);
		
	}

//	@Transactional
//	public void excluir(Fazenda categoria) {
//		
//		if( haProdutosVinculados(categoria) ) {
//			throw new ValidacaoException("Há produtos vinculados.");
//		}
//		
//		categoria = (Fazenda) hibernateUtil.findById(Fazenda.class, categoria.getId());
//		
//		hibernateUtil.delete(categoria);
//		
//	}
//
//	private Boolean haProdutosVinculados(Fazenda categoria) {
//		
//		DetachedCriteria dc = DetachedCriteria.forClass(Servico.class);
//		
//		dc.add(Restrictions.eq("categoria", categoria));
//		
//		return hibernateUtil.possuiRegistros(dc);
//	}

	public List<FazendaVH> carregarVinculos(List<Fazenda> fazendas) {
		
		List<FazendaVH> fazendasVH = new ArrayList<>();
		
		for (Fazenda fazenda : fazendas) {
			FazendaVH fazendaVH = carregarVinculos(fazenda);
			fazendasVH.add(fazendaVH);
		}
		
		return fazendasVH;
	}
	
	public FazendaVH carregarVinculos(Fazenda fazenda) {
		
		FazendaVH fazendaVH = FazendaVH.from(fazenda);
		
		List<Retiro> retiros = retiroNegocio.listar(fazenda);
		fazendaVH.setRetiros(retiros);
		
		for (Retiro retiro : retiros) {
			
			List<Pasto> pastos = pastoNegocio.listar(retiro);
			retiro.setPastos(pastos);
			
			for (Pasto pasto : pastos) {
				
				Map<String, Object> totalDeAnimaisPM = pastoNegocio.consultarTotalAnimaisPesoMedio(pasto);
				
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
				
				List<Map<String, Object>> totaisDeAnimais = loteNegocio.listarSaldoDeAnimaisPorPasto(pasto);
				pasto.setTotaisDeAnimais(totaisDeAnimais);
				
			}
		}
		
		List<CategoriaAnimal> categorias = categoriaAnimalNegocio.listar(fazenda);
		fazendaVH.setCategoriasDeAnimais(categorias);
		
		List<Raca> racas = racaNegocio.listar(fazenda);
		fazendaVH.setRacas(racas);
		
		List<Produto> produtos = produtoNegocio.listar(fazenda);
		fazendaVH.setProdutos(produtos);
		
		return fazendaVH;
	}
}