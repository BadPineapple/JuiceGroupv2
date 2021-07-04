package ilion.terrafos.relatorios.controller;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ilion.CustomErrorController;
import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.terrafos.cadastros.negocio.AvaliacaoDoPasto;
import ilion.terrafos.cadastros.negocio.AvaliacaoDosPastos;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.Pasto;
import ilion.terrafos.cadastros.negocio.PastoNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@UsuarioLogado
public class ResumoEmGraficosController extends CustomErrorController {

	static Logger logger = Logger.getLogger(ResumoEmGraficosController.class);

	@Autowired
	private PropNegocio propNegocio;
	
	@Autowired
	private PastoNegocio pastoNegocio;

	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/relatorios/graficos")
	public String view(@PathVariable Long idFazenda, HttpServletRequest request) {

		// Fazenda fazenda = (Fazenda)
		Fazenda fazenda = (Fazenda) request.getSession().getAttribute("fazendaSessao");

		try {
			
			gerarDistribuicaoDasForragensPieChart(fazenda);
			
			gerarEscoreDasInvasorasHAPieChart(fazenda);
			
			gerarAguadasPorTipoBarChart(fazenda);
			
			gerarAreaBarChart(fazenda);
			
			gerarQualidadeDasAguadasBarChart(fazenda);
			
			carregarDadosLotacaoMediaEmUA(fazenda, request);
			
			return "/ilionnet2/terrafos/relatorios/graficos";
		} catch (Exception e) {
			logger.error("", e);
			return "redirect:/ilionnet/terrafos/fazendas/" + idFazenda + "/relatorios/graficos?m=erro&e="
					+ e.getMessage();
		}

	}
	
	private void carregarDadosLotacaoMediaEmUA(Fazenda fazenda, HttpServletRequest request) {
		
		List<Pasto> pastos = pastoNegocio.listar(fazenda);
		
		for (Pasto pasto : pastos) {
			if( pasto.getAvaliacaoDoPasto() == null ) {
				pasto.setAvaliacaoDoPasto(new AvaliacaoDoPasto());
			}
		}
		
		AvaliacaoDosPastos avaliacaoDosPastos = new AvaliacaoDosPastos();
		avaliacaoDosPastos.setPastos(pastos);
		
		avaliacaoDosPastos.somarLotacaoMediaTotais();
		
		request.setAttribute("avaliacaoDosPastos", avaliacaoDosPastos);
		
	}

	Color azulClaro = new Color(89,186,209,255);
	Color azulEscuro = new Color(96,149,201,255);
	Color vermelho = new Color(205,102,95,255);
	Color verdeMusgo = new Color(170,197,108,255);
	Color roxo = new Color(147,122,178,255);

	public void gerarDistribuicaoDasForragensPieChart(Fazenda fazenda) throws Exception {

		try {

			// Create Chart
			PieChart chart = new PieChartBuilder().width(500).height(350).title("Distribuição das Forragens").build();

			// Customize Chart
			Color[] sliceColors = new Color[] { 
					azulClaro, 
					azulEscuro,
					vermelho,
					verdeMusgo,
					roxo
			};
			chart.getStyler().setSeriesColors(sliceColors);
			chart.getStyler().setChartBackgroundColor(new Color(255, 255, 255));
			
			List<Map<String, Object>> distribuicao = pastoNegocio.consultarDistribuicaoDasForragens(fazenda);
			
			for (Map<String, Object> map : distribuicao) {
				String forrageira = (String) map.get("especie");
				Number total = (Number) map.get("total");
				
				chart.addSeries(forrageira, total);
			}
			
			String pathStatic = propNegocio.findValueById(PropEnum.STATIC_PATH_ABSOLUTO);
			pathStatic += "/graficos/";

			BitmapEncoder.saveJPGWithQuality(chart, pathStatic + "distribuicao-das-forragens.jpg", 0.95f);

		} catch (Exception e) {
			logger.error("erro ao gerarDistribuicaoDasForragensPieChart", e);
		}

	}

	public void gerarEscoreDasInvasorasHAPieChart(Fazenda fazenda) {

		try {
			
			
//			// Create Chart
			PieChart chart = new PieChartBuilder().width(500).height(350).title("Escore de Invasoras (ha)").build();

			chart.getStyler().setChartBackgroundColor(new Color(255, 255, 255));
			
			List<Map<String, Object>> rows = pastoNegocio.consultarEscoreDasInvasoras(fazenda);
			
			for (Map<String, Object> map : rows) {
				String label = (String) map.get("label");
				Number total = (Number) map.get("total");
				
				chart.addSeries(label, total);
			}
			
			String pathStatic = propNegocio.findValueById(PropEnum.STATIC_PATH_ABSOLUTO);
			pathStatic += "/graficos/";

			BitmapEncoder.saveJPGWithQuality(chart, pathStatic + "escore-das-invasoras.jpg", 0.95f);

		} catch (Exception e) {
			logger.error("erro ao gerarEscoreDasInvasorasHABarChart", e);
		}

	}
	
	public void gerarAguadasPorTipoBarChart(Fazenda fazenda) {

		try {
			
			Object[] result = pastoNegocio.consultarAguadasPorTipo(fazenda);
			
			List<Integer> xData = (List<Integer>) result[0];
			List<Integer> yData = (List<Integer>) result[1];
			
			// Create Chart
			CategoryChart chart = new CategoryChartBuilder().width(400).height(300).title("Aguadas Por Tipo")
					.xAxisTitle("Aguada").yAxisTitle("Qtd.").build();

			Color[] sliceColors = new Color[] { 
					azulEscuro,
			};
			chart.getStyler().setSeriesColors(sliceColors);
			chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
			chart.getStyler().setHasAnnotations(true);
			chart.getStyler().setChartBackgroundColor(new Color(255, 255, 255));

			// Series
			//chart.addSeries("test 1", Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }), Arrays.asList(new Integer[] { 4, 5, 9, 6, 5 }));
			chart.addSeries("Qtd.", xData, yData);
			
			String pathStatic = propNegocio.findValueById(PropEnum.STATIC_PATH_ABSOLUTO);
			pathStatic += "/graficos/";

			BitmapEncoder.saveJPGWithQuality(chart, pathStatic + "aguadas-por-tipo.jpg", 0.95f);

		} catch (Exception e) {
			logger.error("erro ao gerarEscoreDasInvasorasHABarChart", e);
		}

	}
	
	public void gerarAreaBarChart(Fazenda fazenda) {

		try {
			
			Object[] result = pastoNegocio.consultarAreas(fazenda);
			
			List<String> xData = (List<String>) result[0];
			List<Double> yData = (List<Double>) result[1];
			
			// Create Chart
			CategoryChart chart = new CategoryChartBuilder().width(400).height(300).title("Áreas")
					.xAxisTitle("Total").yAxisTitle("Qtd.").build();

			Color[] sliceColors = new Color[] { 
					azulEscuro,
			};
			chart.getStyler().setSeriesColors(sliceColors);
			chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
			chart.getStyler().setHasAnnotations(true);
			chart.getStyler().setChartBackgroundColor(new Color(255, 255, 255));

			// Series
			//chart.addSeries("test 1", Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }), Arrays.asList(new Integer[] { 4, 5, 9, 6, 5 }));
			chart.addSeries("Qtd.", xData, yData);
			
			String pathStatic = propNegocio.findValueById(PropEnum.STATIC_PATH_ABSOLUTO);
			pathStatic += "/graficos/";

			BitmapEncoder.saveJPGWithQuality(chart, pathStatic + "area.jpg", 0.95f);

		} catch (Exception e) {
			logger.error("erro ao gerarAreaBarChart", e);
		}

	}
	
	public void gerarQualidadeDasAguadasBarChart(Fazenda fazenda) {

		try {
			
			Object[] result = pastoNegocio.consultarQualidadeDasAguadas(fazenda);
			
			List<Integer> xData = (List<Integer>) result[0];
			List<Integer> yData = (List<Integer>) result[1];
			
			// Create Chart
			CategoryChart chart = new CategoryChartBuilder().width(350).height(250).title("Qualidade das Aguadas")
					.xAxisTitle("Qualidade").yAxisTitle("Qtd.").build();

			Color[] sliceColors = new Color[] { 
					azulEscuro,
			};
			chart.getStyler().setSeriesColors(sliceColors);
			chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
			chart.getStyler().setHasAnnotations(true);
			chart.getStyler().setChartBackgroundColor(new Color(255, 255, 255));

			// Series
			//chart.addSeries("test 1", Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }), Arrays.asList(new Integer[] { 4, 5, 9, 6, 5 }));
			chart.addSeries("Qtd.", xData, yData);
			
			String pathStatic = propNegocio.findValueById(PropEnum.STATIC_PATH_ABSOLUTO);
			pathStatic += "/graficos/";

			BitmapEncoder.saveJPGWithQuality(chart, pathStatic + "qualidade-das-aguadas.jpg", 0.95f);

		} catch (Exception e) {
			logger.error("erro ao gerarQualidadeDasAguadasBarChart", e);
		}

	}
}