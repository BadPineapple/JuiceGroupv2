package ilion.contato.negocio;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.util.Uteis;

@Service
public class ArquivoExcelContatoExportacao implements ContatoExportacao {
	
	static Logger logger = Logger.getLogger(ArquivoExcelContatoExportacao.class);
	
	@Autowired
	private ContatoNegocio contatoNegocio;
	
	@Autowired
	private PropNegocio propNegocio;
	
	private ContatoGrupo contatoGrupo;
	private Boolean permissaoEmail;
	private HttpServletResponse response;
	
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private Integer linha;
	
	public ArquivoExcelContatoExportacao() {
		super();
	}
	
	@PostConstruct
	public void inicializaAtributos() {
		this.wb = new HSSFWorkbook();
		this.sheet = wb.createSheet("Contatos "+propNegocio.findValueById(PropEnum.NOME_EMPRESA));
		this.linha = 0;
	}
	
	public ArquivoExcelContatoExportacao(ContatoGrupo contatoGrupo, Boolean permissaoEmail, HttpServletResponse response) {
		super();
		
		this.contatoGrupo = contatoGrupo;
		this.permissaoEmail = permissaoEmail;
		this.response = response;
	}

	public void setContatoGrupo(ContatoGrupo contatoGrupo) {
		this.contatoGrupo = contatoGrupo;
	}


	public void setPermissaoEmail(Boolean permissaoEmail) {
		this.permissaoEmail = permissaoEmail;
	}


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void exportar() throws Exception {
		
		Boolean haContatosParaConsultar = true;
		Integer maxResult = 100;
		Integer firstResult = 0;
		while(haContatosParaConsultar) {
			List<Map<String, Object>> contatosMap = null;
			
			if(contatoGrupo != null && contatoGrupo.getId() != null) {
				contatosMap = contatoNegocio.listarContatosGrupo(maxResult, firstResult, contatoGrupo, permissaoEmail);
			} else {
				contatosMap = contatoNegocio.listarTodosContatos(maxResult, firstResult, permissaoEmail, "nome;email;telefone;");
			}
			
			adicionarContatos(contatosMap);
			
			if(contatosMap != null) {
				if(contatosMap.size() < maxResult) {
					haContatosParaConsultar = false;
				} else {
					firstResult += maxResult;
				}
			}
		}
		
		enviarParaResponse();
	}
	
	private void adicionarColuna(HSSFRow row, int index, Object valor) {
		if(valor == null) {
			return;
		}
		
		HSSFCell cell = row.createCell(index);

		if(valor instanceof String) {
			String v = (String) valor;
			
			cell.setCellValue(v);
		} else if(valor instanceof Boolean) {
			Boolean b = (Boolean) valor;

			cell.setCellValue(b);
		} else if(valor instanceof Integer) {
			Integer i = (Integer) valor;

			cell.setCellValue(i);
		} else if(valor instanceof Double) {
			Double d = (Double) valor;

			cell.setCellValue(d);
		} else if(valor instanceof Float) {
			Float f = (Float) valor;

			cell.setCellValue(f);
		} else if(valor instanceof BigDecimal) {
			BigDecimal v = (BigDecimal) valor;
			Double d = v.doubleValue();

			cell.setCellValue(d);
		} else {
			logger.error("tipo de dado não previsto: "+valor);
		}
	}
	
	private void adicionarContatos(List<Map<String, Object>> contatosMap) {
		if(contatosMap != null) {
			
			for (Map<String, Object> cMap : contatosMap) {
				
				String nome = (String) cMap.get("nome");
				String email = (String) cMap.get("email");
				String telefone = (String) cMap.get("telefone");
				
				HSSFRow row = sheet.createRow(linha);
				
				adicionarColuna(row, 0, nome);
				adicionarColuna(row, 1, email);
				adicionarColuna(row, 2, telefone);
				
				linha++;
			}
			
		}
	}
	
	private void enviarParaResponse() throws Exception {
		String nome = "contatos-"+Uteis.formatarDataHora(new Date(), "yyyy-MM-dd")+".xls";
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition","inline; filename="+nome);
		
		OutputStream os = response.getOutputStream();
		
		wb.write(os);
		os.close();
	}
}
