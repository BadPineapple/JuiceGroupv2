package ilion.vitazure.negocio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.validation.ValidationException;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.Usuario;
import ilion.contato.negocio.ContatoImportacao;
import ilion.util.Uteis;
import ilion.vitazure.model.Pessoa;



public class ArquivoTextoImportarFuncionario implements ContatoImportacao{

	static Logger logger = Logger.getLogger(ArquivoTextoImportarFuncionario.class);
	
	private InputStream inputStream;
	private Usuario usuario;
	private Integer numeroLinha;
	private Integer qtdSucesso;
	private Integer qtdErro;
	private List<Integer> linhasErro;
	private String erro;
	private PessoaNegocio pessoaNegocio;
	private List<Pessoa> listPessoaImportada;
	private List<String> listPessoaErro;
	private static final String SLASH_WINDOWS = "\\";
	private static final String SLASH_LINUX = "/";
	
	
	public ArquivoTextoImportarFuncionario() {
		super();
	}
	
	public ArquivoTextoImportarFuncionario(InputStream inputStream, Usuario usuario) {
		super();
		
		this.pessoaNegocio = SpringApplicationContext.getBean(PessoaNegocio.class);
		
		this.inputStream = inputStream;
		this.usuario = usuario;
		
		this.numeroLinha = 0;
		
		this.qtdSucesso = 0;
		this.qtdErro = 0;
		this.linhasErro = new ArrayList<Integer>();
		this.listPessoaImportada = new ArrayList<Pessoa>();
		this.listPessoaErro = new ArrayList<String>();
	}
	
	public void importar() throws Exception {
		
		try {
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
			String linha;
			while ((linha = bufferedReader.readLine()) != null) {
				numeroLinha++;
				if( ! Uteis.ehNuloOuVazio(linha) ) {
					if( ehLinhaValida(linha) ) {
						importarLinha(linha);
					} else {
						linhasErro.add(numeroLinha);
					}
				}
			}
			bufferedReader.close();
		} catch (Exception e) {
			logger.error("Erro ao importar funcionario", e);
			this.erro = "ERRO: "+e.getMessage();
		}
	}
	
	
	public void importarExcelFuncionario(MultipartFile arquivo, String caminho, String pacote) throws Exception {

	      String extension = FilenameUtils.getExtension(arquivo.getOriginalFilename());
	      String fileName = FilenameUtils.removeExtension(arquivo.getOriginalFilename()).concat("_").concat(String.valueOf(new Date().getTime())).concat(".").concat(extension);
	      java.io.File basePath = new java.io.File(caminho.concat(SLASH_LINUX).concat(pacote).concat(SLASH_LINUX));
	      if (!basePath.exists()) {
	        basePath.mkdirs();
	      }
	      Path newPath = Paths.get(caminho + SLASH_LINUX + pacote + SLASH_LINUX + fileName);
	      Workbook workbook = null;
	      try {
	        if (!arquivo.getOriginalFilename().isEmpty()) {
	          Files.copy(arquivo.getInputStream(), newPath);
	        } else {
	          throw new ValidationException("Arquivo inválido.");
	        }
	        FileInputStream fileInputStream = new FileInputStream(newPath.toString());
	        if(extension.equals("xls")){
	        	workbook  = new HSSFWorkbook(new POIFSFileSystem(fileInputStream));
	        } else if(extension.equals("xlsx")){
	          workbook  = new XSSFWorkbook(fileInputStream);
	        }else {
		      throw new ValidationException("Formato "+extension+" invalido.");	
		    }
	        Sheet sheet = workbook.getSheetAt(0);
	        Iterator linhas = sheet.rowIterator();
	        while (linhas.hasNext()) {
                XSSFRow linha = (XSSFRow) linhas.next();
                Iterator celulas = linha.cellIterator();
                String nome = null;
        		String email = null;
        		String telefone = null;
        		
                while (celulas.hasNext()) {
                    XSSFCell celula = (XSSFCell) celulas.next();
                    int z = celula.getColumnIndex();

                    switch (z) {
                        case 0:
                        	nome = celula.toString();
                        	break;
                        case 1:
                        	email = celula.toString();
                        	break;
                        case 2:
                        	telefone = celula.toString();
                        	break;
                    }

                }
                if( ! Uteis.ehNuloOuVazio(nome) && ! Uteis.ehNuloOuVazio(email) && ! Uteis.ehNuloOuVazio(telefone)) {
        			importarFuncionario(nome, email,telefone, usuario);
        		} else {
        			linhasErro.add(numeroLinha);
        			qtdErro++;
        			this.listPessoaErro.add(!Uteis.ehNuloOuVazio(nome) ? nome : ! Uteis.ehNuloOuVazio(email) ? email : telefone);
        		}
            }
	      } catch (IOException e) {
	        throw new ValidationException("Houve um erro: " + e.getMessage());
	      }
	      
	      
	      
	    }
	
	
	/**
	 * @param linha
	 * @return true se houver dois itens (nome e email) ou
	 * se houver quatro itens (nome, email, empresa e telefone)
	 */
	private Boolean ehLinhaValida(String linha) {
		StringTokenizer st = new StringTokenizer(linha, ";");
		
		if(st.countTokens() != 3) {
			return false;
		}
		
		return true;
	}
	private Boolean ehLinhaValidaExcel(String linha) {
		StringTokenizer st = new StringTokenizer(linha, ",");
		
		if(st.countTokens() != 3) {
			return false;
		}
		
		return true;
	}
	
	private void importarLinha(String linha) throws Exception {
		String nome = null;
		String email = null;
		String telefone = null;
		
		StringTokenizer st = new StringTokenizer(linha, ";");
		
		int i = 0;
		while(st.hasMoreElements()) {
			String element = (String) st.nextElement();
			if(i == 0){
				nome = element;
			} else if(i == 1){
				email = element;
			}  else if(i == 3){
				telefone = element;
			}
			i++;
		}
		if( ! Uteis.ehNuloOuVazio(email) ) {
			importarFuncionario(nome, email,telefone, usuario);
			qtdSucesso++;
		} else {
			linhasErro.add(numeroLinha);
		}
	}

	private void importarFuncionario(String nome, String email, String telefone, Usuario usuario) throws Exception {
		email = email.trim();
		
		Pessoa pessoa = pessoaNegocio.consultarPorEmail(email);
		
		if(pessoa == null) {
			pessoa = new Pessoa();
			pessoa.setNomeResponsavelImportacao(usuario.getNome());
			pessoa.setDataCadastro(new Date());
			pessoa.setNome(nome);
			pessoa.setEmail(email);
			pessoa.setCelular(telefone);
			pessoa.setCliente(Boolean.TRUE);
			pessoa.setPessoaImportada(Boolean.TRUE);
			pessoa.setSenha("Vitazure1");
			pessoa.setEmpresaImportacao(usuario.getEmpresa());
			pessoa.setConfirmado(Boolean.TRUE);
			pessoa = pessoaNegocio.incluirAtualizar(pessoa);
			this.listPessoaImportada.add(pessoa);
			qtdSucesso++;
		}
		
	}
	
	public String getLog() {
		StringBuilder retorno = new StringBuilder();
		if(qtdSucesso > 0) {
			retorno.append("<strong>Qtd. de linhas importadas:</strong> "+qtdSucesso+"<br/>");
			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
			this.listPessoaImportada.forEach(pessoa -> {
				retorno.append(" <div class=\"col-md-4 col-lg-4 col-sm-12\">");
				retorno.append("<strong>Nome: </strong>").append(pessoa.getNome());
				retorno.append("</div>");
			});
			retorno.append("</div>");
		}
		if( ! linhasErro.isEmpty() ) {
			retorno.append("<strong>Linhas Erro:</strong>"+qtdErro+"<br/>");
			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
			this.listPessoaErro.forEach(string -> {
				retorno.append(" <div class=\"col-md-4 col-lg-4 col-sm-12\">");
				retorno.append("<strong>Nome: </strong>").append(string);
				retorno.append("</div>");
			});
			retorno.append("</div>");
		}
		if( ! Uteis.ehNuloOuVazio(erro) ) {
			retorno.append("<strong>ERRO:</strong> "+erro);
		}
		return retorno.toString();
	}
	
	
	
}
