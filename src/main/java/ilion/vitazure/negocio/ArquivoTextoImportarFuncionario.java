package ilion.vitazure.negocio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
	
	
	private PessoaNegocio pessoaNegocio;
	private InputStream inputStream;
	private Usuario usuario;
	private Integer numeroLinha;
	private Integer qtdSucesso;
	private Integer qtdErro;
	private List<Integer> linhasErro;
	private String erro;
	private List<Pessoa> listPessoaImportada;
	private List<String> listPessoaErro;
	private List<Pessoa> erroPessoasDuplicadas;
	private List<Pessoa> pessoasjaCadastradaBanco;
	private List<Pessoa> pessoasjaCadastradaProfissionalBanco;
	private List<Pessoa> pessoasOperacao1;
	private List<Pessoa> pessoasOperacao1VinculadoOutraEmpresa;
	private List<Pessoa> pessoasOperacao2;
	private List<Pessoa> pessoasOperacao3;
	private List<Pessoa> pessoasOperacao3ProfissionalNaoCadastrado;
	private List<Pessoa> pessoasOperacao2ProfissionalNaoCadastrado;
	private List<Pessoa> pessoasOperacao2VinculadoOutraEmpresa;
	private static final String SLASH_WINDOWS = "\\";
	private static final String SLASH_LINUX = "/";
	private Boolean operacoesValidadas;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Usuario getUsuario() {
		if(usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getNumeroLinha() {
		if(numeroLinha == null) {
			numeroLinha = 0;
		}
		return numeroLinha;
	}

	public void setNumeroLinha(Integer numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	public Integer getQtdSucesso() {
		if(qtdSucesso == null) {
			qtdSucesso = 0;
		}
		return qtdSucesso;
	}

	public void setQtdSucesso(Integer qtdSucesso) {
		this.qtdSucesso = qtdSucesso;
	}

	public Integer getQtdErro() {
		if(qtdErro == null) {
			qtdErro = 0;
		}
		return qtdErro;
	}

	public void setQtdErro(Integer qtdErro) {
		this.qtdErro = qtdErro;
	}

	public List<Integer> getLinhasErro() {
		if(linhasErro == null) {
			linhasErro = new ArrayList<Integer>();
		}
		return linhasErro;
	}

	public void setLinhasErro(List<Integer> linhasErro) {
		this.linhasErro = linhasErro;
	}

	public String getErro() {
		if(erro == null) {
		   erro = "";
		}
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public List<Pessoa> getListPessoaImportada() {
		if(listPessoaImportada == null) {
			listPessoaImportada = new ArrayList<Pessoa>();
		}
		return listPessoaImportada;
	}

	public void setListPessoaImportada(List<Pessoa> listPessoaImportada) {
		this.listPessoaImportada = listPessoaImportada;
	}

	public List<String> getListPessoaErro() {
		if(listPessoaErro == null) {
			listPessoaErro = new ArrayList<String>();
		}
		return listPessoaErro;
	}

	public void setListPessoaErro(List<String> listPessoaErro) {
		this.listPessoaErro = listPessoaErro;
	}

	public List<Pessoa> getErroPessoasDuplicadas() {
		if(erroPessoasDuplicadas == null) {
		    erroPessoasDuplicadas = new ArrayList<Pessoa>();
		}
		return erroPessoasDuplicadas;
	}

	public void setErroPessoasDuplicadas(List<Pessoa> erroPessoasDuplicadas) {
		this.erroPessoasDuplicadas = erroPessoasDuplicadas;
	}

	public List<Pessoa> getPessoasjaCadastradaBanco() {
		if(pessoasjaCadastradaBanco == null) {
			pessoasjaCadastradaBanco = new ArrayList<Pessoa>();
		}
		return pessoasjaCadastradaBanco;
	}

	public void setPessoasjaCadastradaBanco(List<Pessoa> pessoasjaCadastradaBanco) {
		this.pessoasjaCadastradaBanco = pessoasjaCadastradaBanco;
	}

	public List<Pessoa> getPessoasjaCadastradaProfissionalBanco() {
		if(pessoasjaCadastradaProfissionalBanco == null) {
			pessoasjaCadastradaProfissionalBanco = new ArrayList<Pessoa>();
		}
		return pessoasjaCadastradaProfissionalBanco;
	}

	public void setPessoasjaCadastradaProfissionalBanco(List<Pessoa> pessoasjaCadastradaProfissionalBanco) {
		this.pessoasjaCadastradaProfissionalBanco = pessoasjaCadastradaProfissionalBanco;
	}

	public Boolean getOperacoesValidadas() {
		if(operacoesValidadas == null) {
			operacoesValidadas = Boolean.TRUE;
		}
		return operacoesValidadas;
	}

	public void setOperacoesValidadas(Boolean operacoesValidadas) {
		this.operacoesValidadas = operacoesValidadas;
	}
	
	public List<Pessoa> getPessoasOperacao1() {
		if(pessoasOperacao1 == null) {
			pessoasOperacao1 = new ArrayList<Pessoa>();
		}
		return pessoasOperacao1;
	}

	public void setPessoasOperacao1(List<Pessoa> pessoasOperacao1) {
		this.pessoasOperacao1 = pessoasOperacao1;
	}

	public List<Pessoa> getPessoasOperacao2() {
		if(pessoasOperacao2 == null) {
			pessoasOperacao2 = new ArrayList<Pessoa>();
		}
		return pessoasOperacao2;
	}

	public void setPessoasOperacao2(List<Pessoa> pessoasOperacao2) {
		this.pessoasOperacao2 = pessoasOperacao2;
	}

	public List<Pessoa> getPessoasOperacao3() {
		if(pessoasOperacao3 == null) {
			pessoasOperacao3 = new ArrayList<Pessoa>();
		}
		return pessoasOperacao3;
	}

	public void setPessoasOperacao3(List<Pessoa> pessoasOperacao3) {
		this.pessoasOperacao3 = pessoasOperacao3;
	}
	
	public List<Pessoa> getPessoasOperacao1VinculadoOutraEmpresa() {
		if(pessoasOperacao1VinculadoOutraEmpresa == null) {
			pessoasOperacao1VinculadoOutraEmpresa = new ArrayList<Pessoa>();
		}
		return pessoasOperacao1VinculadoOutraEmpresa;
	}

	public void setPessoasOperacao1VinculadoOutraEmpresa(List<Pessoa> pessoasOperacao1VinculadoOutraEmpresa) {
		this.pessoasOperacao1VinculadoOutraEmpresa = pessoasOperacao1VinculadoOutraEmpresa;
	}
	
	public List<Pessoa> getPessoasOperacao3ProfissionalNaoCadastrado() {
		if(pessoasOperacao3ProfissionalNaoCadastrado == null) {
			pessoasOperacao3ProfissionalNaoCadastrado = new ArrayList<Pessoa>();
		}
		return pessoasOperacao3ProfissionalNaoCadastrado;
	}

	public void setPessoasOperacao3ProfissionalNaoCadastrado(List<Pessoa> pessoasOperacao3ProfissionalNaoCadastrado) {
		this.pessoasOperacao3ProfissionalNaoCadastrado = pessoasOperacao3ProfissionalNaoCadastrado;
	}
	
	public List<Pessoa> getPessoasOperacao2ProfissionalNaoCadastrado() {
		if(pessoasOperacao2ProfissionalNaoCadastrado == null) {
			pessoasOperacao2ProfissionalNaoCadastrado = new ArrayList<Pessoa>();
		}
		return pessoasOperacao2ProfissionalNaoCadastrado;
	}

	public void setPessoasOperacao2ProfissionalNaoCadastrado(List<Pessoa> pessoasOperacao2ProfissionalNaoCadastrado) {
		this.pessoasOperacao2ProfissionalNaoCadastrado = pessoasOperacao2ProfissionalNaoCadastrado;
	}
	
	public List<Pessoa> getPessoasOperacao2VinculadoOutraEmpresa() {
		if(pessoasOperacao2VinculadoOutraEmpresa == null) {
			pessoasOperacao2VinculadoOutraEmpresa = new ArrayList<Pessoa>();
		}
		return pessoasOperacao2VinculadoOutraEmpresa;
	}

	public void setPessoasOperacao2VinculadoOutraEmpresa(List<Pessoa> pessoasOperacao2VinculadoOutraEmpresa) {
		this.pessoasOperacao2VinculadoOutraEmpresa = pessoasOperacao2VinculadoOutraEmpresa;
	}

	public ArquivoTextoImportarFuncionario() {
		super();
	}
	
	public ArquivoTextoImportarFuncionario(InputStream inputStream, Usuario usuario) {
		super();
		setInputStream(inputStream);
		setUsuario(usuario);
		this.pessoaNegocio = SpringApplicationContext.getBean(PessoaNegocio.class);
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
      		  String cpf = null;
      		  String operacao = null;
      		
              while (celulas.hasNext()) {
                  XSSFCell celula = (XSSFCell) celulas.next();
                  int z = celula.getColumnIndex();

                  switch (z) {
                      case 0:
                      	nome = celula.toString();
                      	break;
                      case 1:
                      	cpf = celula.toString();
                      	break;
                      case 2:
                      	operacao = celula.toString();
                      	break;
                  }

              }
      			importarFuncionario(nome, cpf,operacao);
          }
	        validarCpfDuplicadoLista();
	      } catch (IOException e) {
	        throw new ValidationException("Houve um erro: " + e.getMessage());
	      }
	    }
	
	private void importarFuncionario(String nome, String cpf ,String operacao) throws Exception {
		
		Pessoa pessoa = new Pessoa();
		pessoa = pessoaNegocio.consultarPorCpf(cpf);
		
		if(pessoa == null) {
			pessoa = new Pessoa();
			pessoa.setNomeResponsavelImportacao(usuario.getNome());
			pessoa.setDataCadastro(new Date());
			pessoa.setNome(nome);
			pessoa.setCpf(cpf);
			pessoa.setCliente(Boolean.TRUE);
			pessoa.setPessoaImportada(Boolean.TRUE);
			pessoa.setSenha("Vitazure1");
			pessoa.setConfirmado(Boolean.TRUE);
			pessoa.setClienteAtivo(Boolean.TRUE);
			pessoa.setOperacaoImportacao(operacao);
		}else{
			pessoa.setConfirmado(Boolean.TRUE);
			pessoa.setClienteAtivo(Boolean.TRUE);
			pessoa.setOperacaoImportacao(operacao);
		}
		getListPessoaImportada().add(pessoa);
		
	}
	
	
	public void validarCpfDuplicadoLista() {
		
		getListPessoaImportada().removeAll(getListPessoaImportada().stream().filter(pessoaNulas -> Uteis.ehNuloOuVazio(pessoaNulas.getNome()) && Uteis.ehNuloOuVazio(pessoaNulas.getCpf()) && Uteis.ehNuloOuVazio(pessoaNulas.getOperacaoImportacao())).collect(Collectors.toList()));
		
		setOperacoesValidadas(getListPessoaImportada().stream().allMatch(pessoa -> (pessoa.getOperacaoImportacao() != null && pessoa.getOperacaoImportacao().equals("1.0")) || (pessoa.getOperacaoImportacao() != null && pessoa.getOperacaoImportacao().equals("2.0")) || (pessoa.getOperacaoImportacao() != null && pessoa.getOperacaoImportacao().equals("3.0"))));
		getListPessoaImportada().stream().forEach(pessoaImportada -> validar(pessoaImportada));
		if(getErroPessoasDuplicadas().isEmpty() && getOperacoesValidadas()) {
			getListPessoaImportada().stream().forEach(pessoa -> incluirAtualizarFuncionarioArquivo(pessoa));
		}
	}
	
	private void incluirAtualizarFuncionarioArquivo(Pessoa pessoa) {
		try {
			if(pessoa.getOperacaoImportacao().equals("1.0") && Uteis.ehNuloOuVazio(pessoa.getEmpresaImportacao())) {
				pessoa.setEmpresaImportacao(getUsuario().getEmpresa());
				getPessoasOperacao1().add(pessoa);
				pessoaNegocio.incluirAtualizar(pessoa);
			}else if(pessoa.getOperacaoImportacao().equals("1.0") && !Uteis.ehNuloOuVazio(pessoa.getEmpresaImportacao())) {
				getPessoasOperacao1VinculadoOutraEmpresa().add(pessoa);
			}else if(pessoa.getId() != null && pessoa.getId() != 0L && pessoa.getOperacaoImportacao().equals("2.0") && (!Uteis.ehNuloOuVazio(pessoa.getEmpresaImportacao()) && usuario.getEmpresa().equals(pessoa.getEmpresaImportacao()))) {
				pessoa.setEmpresaImportacao(null);
				pessoa.setPessoaImportada(Boolean.FALSE);
				pessoa.setNomeResponsavelImportacao(null);
				getPessoasOperacao2().add(pessoa);
				pessoaNegocio.incluirAtualizar(pessoa);
			}else if(pessoa.getId() != null && pessoa.getId() != 0L && pessoa.getOperacaoImportacao().equals("2.0") && (!Uteis.ehNuloOuVazio(pessoa.getEmpresaImportacao()) && !usuario.getEmpresa().equals(pessoa.getEmpresaImportacao()))) {
                 getPessoasOperacao2VinculadoOutraEmpresa().add(pessoa);
			}else if((pessoa.getId() == null || pessoa.getId() == 0L) && pessoa.getOperacaoImportacao().equals("2.0")) {
		    	getPessoasOperacao2ProfissionalNaoCadastrado().add(pessoa);
		    }else if(pessoa.getId() != null && pessoa.getId() != 0L && pessoa.getOperacaoImportacao().equals("3.0")) {
				pessoa.setEmpresaImportacao(getUsuario().getEmpresa());
				getPessoasOperacao3().add(pessoa);
				pessoaNegocio.incluirAtualizar(pessoa);
		    }else if((pessoa.getId() == null || pessoa.getId() == 0L) && pessoa.getOperacaoImportacao().equals("3.0")) {
		    	getPessoasOperacao3ProfissionalNaoCadastrado().add(pessoa);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void validar(Pessoa pessoa) {
		Boolean pessoaDuplicada = getListPessoaImportada().stream()
				.anyMatch(pessoaImportada -> pessoaImportada.getCpf().equals(pessoa.getCpf()) && !pessoaImportada.getNome().equals(pessoa.getNome()));
		if(pessoaDuplicada) {
			getErroPessoasDuplicadas().add(pessoa);
		}
	}
	
	
	public String getLog() {
		StringBuilder retorno = new StringBuilder();
		if(!getOperacoesValidadas()) {
			retorno.append("<strong>Operação invalida, verifique as instruções para importação de funcionários.</strong><br/>");
		}
		if(!getErroPessoasDuplicadas().isEmpty()) {
			retorno.append("<strong>Problemas ao importar arquivos, existe cpf duplicados:</strong><br/>");
			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
			getErroPessoasDuplicadas().forEach(pessoa -> {
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>Nome: </strong>").append(pessoa.getNome());
				retorno.append("</div>");
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>CPf: </strong>").append(pessoa.getCpf());
				retorno.append("</div>");
			});
			retorno.append("</div>");
		}
		if(!getPessoasOperacao1().isEmpty()) {
			retorno.append("<strong>Qtd. de linhas importadas: ").append(getPessoasOperacao1().size()).append("</strong><br/>");
			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
			getPessoasOperacao1().forEach(pessoa -> {
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>Nome: </strong>").append(pessoa.getNome());
				retorno.append("</div>");
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>CPf: </strong>").append(pessoa.getCpf());
				retorno.append("</div>");
			});
			retorno.append("</div>");
		}
		if(!getPessoasOperacao2().isEmpty()) {
			retorno.append("<strong>Qtd. de linhas importadas, com desligamento do CPF da base: ").append(getPessoasOperacao2().size()).append("</strong><br/>");
			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
			getPessoasOperacao2().forEach(pessoa -> {
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>Nome: </strong>").append(pessoa.getNome());
				retorno.append("</div>");
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>CPf: </strong>").append(pessoa.getCpf());
				retorno.append("</div>");
			});
			retorno.append("</div>");
		}if(!getPessoasOperacao2VinculadoOutraEmpresa().isEmpty()) {
			retorno.append("<strong>Qtd. de linhas não importadas, com CPF não vinculado a essa empresa: ").append(getPessoasOperacao2().size()).append("</strong><br/>");
			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
			getPessoasOperacao2VinculadoOutraEmpresa().forEach(pessoa -> {
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>Nome: </strong>").append(pessoa.getNome());
				retorno.append("</div>");
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>CPf: </strong>").append(pessoa.getCpf());
				retorno.append("</div>");
			});
			retorno.append("</div>");
		}
		if(!getPessoasOperacao2ProfissionalNaoCadastrado().isEmpty()) {
			retorno.append("<strong>Qtd. de linhas não importadas com desligamento, CPF não existente na base: ").append(getPessoasOperacao2ProfissionalNaoCadastrado().size()).append("</strong><br/>");
			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
			getPessoasOperacao2ProfissionalNaoCadastrado().forEach(pessoa -> {
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>Nome: </strong>").append(pessoa.getNome());
				retorno.append("</div>");
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>CPf: </strong>").append(pessoa.getCpf());
				retorno.append("</div>");
			});
			retorno.append("</div>");
		}
		if(!getPessoasOperacao3().isEmpty()) {
			retorno.append("<strong>Qtd. de linhas importadas, com CPF já existente na base: ").append(getPessoasOperacao3().size()).append("</strong><br/>");
			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
			getPessoasOperacao3().forEach(pessoa -> {
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>Nome: </strong>").append(pessoa.getNome());
				retorno.append("</div>");
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>CPf: </strong>").append(pessoa.getCpf());
				retorno.append("</div>");
			});
			retorno.append("</div>");
		}
		if(!getPessoasOperacao1VinculadoOutraEmpresa().isEmpty()) {
			retorno.append("<strong>Qtd. de linhas não importadas, com CPF vinculado a uma empresa: ").append(getPessoasOperacao1VinculadoOutraEmpresa().size()).append("</strong><br/>");
			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
			getPessoasOperacao1VinculadoOutraEmpresa().forEach(pessoa -> {
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>Nome: </strong>").append(pessoa.getNome());
				retorno.append("</div>");
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>CPf: </strong>").append(pessoa.getCpf());
				retorno.append("</div>");
			});
			retorno.append("</div>");
		}
		if(!getPessoasOperacao3ProfissionalNaoCadastrado().isEmpty()) {
			retorno.append("<strong>Qtd. de linhas não importadas, CPF não existente na base: ").append(getPessoasOperacao3ProfissionalNaoCadastrado().size()).append("</strong><br/>");
			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
			getPessoasOperacao3ProfissionalNaoCadastrado().forEach(pessoa -> {
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>Nome: </strong>").append(pessoa.getNome());
				retorno.append("</div>");
				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
				retorno.append("<strong>CPf: </strong>").append(pessoa.getCpf());
				retorno.append("</div>");
			});
			retorno.append("</div>");
		}
//		if(getQtdSucesso() > 0 && getErroPessoasDuplicadas().isEmpty() && !getErroOperacao()) {
//			retorno.append("<strong>Qtd. de linhas importadas:</strong> "+qtdSucesso+"<br/>");
//			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
//			getListPessoaImportada().forEach(pessoa -> {
//				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
//				retorno.append("<strong>Nome: </strong>").append(pessoa.getNome());
//				retorno.append("</div>");
//				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
//				retorno.append("<strong>CPf: </strong>").append(pessoa.getCpf());
//				retorno.append("</div>");
//			});
//			retorno.append("</div>");
//		}
//		if( ! getLinhasErro().isEmpty() && getErroPessoasDuplicadas().isEmpty() && !getErroOperacao()) {
//			retorno.append("<strong>Linhas Erro:</strong>"+qtdErro+"<br/>");
//			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
//			getLinhasErro().forEach(string -> {
//				retorno.append(" <div class=\"col-md-4 col-lg-4 col-sm-12\">");
//				retorno.append("<strong>Nome: </strong>").append(string);
//				retorno.append("</div>");
//			});
//			retorno.append("</div>");
//		}
//		if( ! Uteis.ehNuloOuVazio(erro) ) {
//			retorno.append("<strong>ERRO:</strong> "+erro);
//		}
//		if(!getPessoasjaCadastradaBanco().isEmpty() && !getErroOperacao()) {
//			retorno.append("<strong>CPF já existente na base de dados:</strong><br/>");
//			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
//			getPessoasjaCadastradaBanco().forEach(pessoa -> {
//				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
//				retorno.append("<strong>Nome: </strong>").append(pessoa.getNome());
//				retorno.append("</div>");
//				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
//				retorno.append("<strong>CPf: </strong>").append(pessoa.getCpf());
//				retorno.append("</div>");
//			});
//			retorno.append("</div>");
//		}
//		if(!getPessoasjaCadastradaProfissionalBanco().isEmpty() && !getErroOperacao()) {
//			retorno.append("<strong>CPF já existente na base de dados com o tipo Profissional:</strong><br/>");
//			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
//			getPessoasjaCadastradaProfissionalBanco().forEach(pessoa -> {
//				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
//				retorno.append("<strong>Nome: </strong>").append(pessoa.getNome());
//				retorno.append("</div>");
//				retorno.append(" <div class=\"col-md-6 col-lg-6 col-sm-12\">");
//				retorno.append("<strong>CPf: </strong>").append(pessoa.getCpf());
//				retorno.append("</div>");
//			});
//			retorno.append("</div>");
//		}	
		return retorno.toString();
	}
	
	
	
}
