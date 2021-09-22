package ilion.vitazure.negocio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

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
	private List<Integer> linhasErro;
	private String erro;
	private PessoaNegocio pessoaNegocio;
	private List<Pessoa> listPessoaImportada;
	
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
		this.linhasErro = new ArrayList<Integer>();
		this.listPessoaImportada = new ArrayList<Pessoa>();
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
			pessoa = pessoaNegocio.incluirAtualizar(pessoa);
			pessoaNegocio.emailAtivacao(pessoa);
	    	this.listPessoaImportada.add(pessoa);
		}
		
	}
	
	public String getLog() {
		StringBuilder retorno = new StringBuilder();
		if(qtdSucesso > 0) {
			retorno.append("<strong>Qtd. de linhas importadas:</strong> "+qtdSucesso+"<br/>");
			retorno.append(" <div class=\"col-md-12 col-lg-12 col-sm-12\">");
			this.listPessoaImportada.forEach(pessoa -> {
				retorno.append(" <div class=\"col-md-4 col-lg-4 col-sm-12\">");
				retorno.append("<strong>Nome:</strong>").append(pessoa.getNome());
				retorno.append("</div>");
			});
			retorno.append("</div>");
		}
		if( ! linhasErro.isEmpty() ) {
			retorno.append("<strong>Linhas ignoradas:</strong> "+linhasErro+"<br/>");
		}
		if( ! Uteis.ehNuloOuVazio(erro) ) {
			retorno.append("<strong>ERRO:</strong> "+erro);
		}
		return retorno.toString();
	}
	
	
	
}
