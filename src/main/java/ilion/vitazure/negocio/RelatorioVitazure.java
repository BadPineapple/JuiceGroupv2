package ilion.vitazure.negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.ArquivoUteis;
import ilion.util.Uteis;
import ilion.util.VLHForm;
import ilion.vitazure.model.Agenda;
import net.mlw.vlh.ValueList;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service
public class RelatorioVitazure {

	private String path;
	private String url;
	private String logo;
	private String retorno;
	private HSSFWorkbook hSSFWorkbook;
	private HSSFSheet hSSFSheet;
	
	
	@Autowired
	private PropNegocio propNegocio;
	
	public String getPath() {
		if(path == null) {
			path = "";
		}
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		if(path == null) {
			path = "";
		}
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogo() {
		if(logo == null) {
			logo = "";
		}
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getRetorno() {
		if(path == null) {
			path = "";
		}
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}
	
	public void gerarIreport(ValueList valueList , VLHForm vlhForm , String relatorioJasper , String titulo) throws JRException {
		JasperDesign jasper = JRXmlLoader.load(Uteis.caminhoFisico.concat("Relatorio/").concat(relatorioJasper).concat(".jrxml"));
		JasperReport relatorio = JasperCompileManager.compileReport( jasper );
		//executa o relatório
		Map parametros = new HashMap();
		parametros.put("tituloRelatorio", titulo);
		parametros.put("logo", getLogo());
		parametros.put("empresa", vlhForm.getPalavraChave());
		parametros.put("dataInicio", Uteis.formatarDataHora(Uteis.converterHoraEmDate(vlhForm.getDataInicio(), "yyyy-MM-dd"), "dd/MM/yyyy"));
		parametros.put("dataFim", Uteis.formatarDataHora(Uteis.converterHoraEmDate(vlhForm.getDataFim(), "yyyy-MM-dd"), "dd/MM/yyyy"));
		parametros.put("situacao", vlhForm.getStatusAgenda() == null ? "Todas" : vlhForm.getStatusAgenda().getNome());
		JasperPrint impressao = JasperFillManager.fillReport( relatorio , parametros, new JRBeanCollectionDataSource(valueList.getList()));
		JasperExportManager.exportReportToPdfFile(impressao, getPath().concat("/").concat(relatorioJasper).concat(".pdf"));
	}
	
	public HSSFWorkbook gerarExcel(HttpServletResponse response, List<Agenda> listAgenda , String relatorio) throws IOException {
		hSSFWorkbook = new HSSFWorkbook();
        hSSFSheet = hSSFWorkbook.createSheet(relatorio);
        headerResumoAtendimento();
        IntStream.range(0, listAgenda.size()).forEach(index -> dadosResumoAtendimento(listAgenda.get(index), index + 1));
        return hSSFWorkbook;
        
	}
	
	private void headerResumoAtendimento() {
		Row row = hSSFSheet.createRow(0);
		CellStyle style = hSSFWorkbook.createCellStyle();
		HSSFFont fonts = hSSFWorkbook.createFont();
		fonts.setBold(Boolean.TRUE);
		fonts.setFontHeight((short) 200);
		style.setFont(fonts);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("ID");
		cell.setCellStyle(style);
		hSSFSheet.autoSizeColumn(0);
		
		cell = row.createCell(1);
		cell.setCellValue("Profissional");
		cell.setCellStyle(style);
		hSSFSheet.autoSizeColumn(1);
		
		cell = row.createCell(2);
		cell.setCellValue("Paciente");
		cell.setCellStyle(style);
		hSSFSheet.autoSizeColumn(2);
		
		cell = row.createCell(3);
		cell.setCellValue("Data consulta");
		cell.setCellStyle(style);
		hSSFSheet.autoSizeColumn(3);
		
		cell = row.createCell(4);
		cell.setCellValue("Tempo Consulta");
		cell.setCellStyle(style);
		hSSFSheet.autoSizeColumn(4);
		
		cell = row.createCell(5);
		cell.setCellValue("Situação");
		cell.setCellStyle(style);
		hSSFSheet.autoSizeColumn(5);
	}
	
	private void dadosResumoAtendimento(Agenda agenda , int linha) {
		
		Row row = hSSFSheet.createRow(linha);
		CellStyle style = hSSFWorkbook.createCellStyle();
		HSSFFont fonts = hSSFWorkbook.createFont();
		style.setFont(fonts);
		
		Cell cell = row.createCell(0);
		cell.setCellValue(agenda.getId());
		cell.setCellStyle(style);
		hSSFSheet.autoSizeColumn(0);
		
		cell = row.createCell(1);
		cell.setCellValue(agenda.getProfissional().getPessoa().getNome());
		cell.setCellStyle(style);
		hSSFSheet.autoSizeColumn(1);
		
		cell = row.createCell(2);
		cell.setCellValue(agenda.getPaciente().getNome());
		cell.setCellStyle(style);
		hSSFSheet.autoSizeColumn(2);
		
		cell = row.createCell(3);
		cell.setCellValue(agenda.getDataHoraApresentar());
		cell.setCellStyle(style);
		hSSFSheet.autoSizeColumn(3);
		
		cell = row.createCell(4);
		cell.setCellValue(agenda.getProfissional().getDuracaoAtendimento().getNomeApresentar());
		cell.setCellStyle(style);
		hSSFSheet.autoSizeColumn(4);
		
		cell = row.createCell(5);
		cell.setCellValue(agenda.getStatus().getNome());
		cell.setCellStyle(style);
		hSSFSheet.autoSizeColumn(5);
		
		
		
		
	}
	
	
	public String UrlDownloadArquivo(ValueList valueList , VLHForm vlhForm , String relatorioJasper , String titulo) throws Exception {
		gerarPastaDownload(vlhForm.getPalavraChave());
		gerarIreport(valueList , vlhForm , relatorioJasper , titulo);
		return  getPath().concat("/").concat(relatorioJasper).concat(".pdf");
	}
	

	public void gerarPastaDownload(String nomeArquivo) {
		
		StringBuilder pathTemp = new StringBuilder();
		StringBuilder urlTemp = new StringBuilder();
		
		String site = propNegocio.findValueById(PropEnum.URL);
		
		pathTemp.append(Uteis.caminhoFisico).append("arquivos/temp");
		urlTemp.append(propNegocio.findValueById(PropEnum.STATIC_URL)).append("/arquivos/temp");
		
		ArquivoUteis.verificarPastaExistente(pathTemp.toString());
		
		ArquivoUteis.verificarPastaExistente(pathTemp.toString());
		
		setLogo(site+"/assets/images/logo.png");
		
		urlTemp.append(nomeArquivo).append(".pdf");
		
		setPath(pathTemp.toString());
		
	}
	
}
