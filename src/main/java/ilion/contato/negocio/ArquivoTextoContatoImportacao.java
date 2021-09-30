package ilion.contato.negocio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.Usuario;
import ilion.util.Uteis;

public class ArquivoTextoContatoImportacao implements ContatoImportacao {
	
	static Logger logger = Logger.getLogger(ArquivoTextoContatoImportacao.class);
	
	private ContatoNegocio contatoNegocio;
	
	private InputStream inputStream;
	private String charset;
	private ContatoGrupo contatoGrupo;
	private Usuario usuario;
	
	private Integer numeroLinha;
	
	private Integer qtdSucesso;
	private List<Integer> linhasErro;
	private String erro;
	
	public ArquivoTextoContatoImportacao() {
		super();
	}

	public ArquivoTextoContatoImportacao(InputStream inputStream, String charset, ContatoGrupo contatoGrupo, Usuario usuario) {
		super();
		
		this.contatoNegocio = SpringApplicationContext.getBean(ContatoNegocio.class);
		
		this.inputStream = inputStream;
		this.charset = charset;
		this.contatoGrupo = contatoGrupo;
		this.usuario = usuario;
		
		this.numeroLinha = 0;
		
		this.qtdSucesso = 0;
		this.linhasErro = new ArrayList<Integer>();
	}
	
	public void importar() throws Exception {
		
		try {
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
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
			logger.error("Erro ao importar contatos", e);
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
		
		if(st.countTokens() != 2 && st.countTokens() != 4) {
			return false;
		}
		
		return true;
	}
	
	private void importarLinha(String linha) {
		String nome = null;
		String email = null;
		String empresa = null;
		String telefone = null;
		
		StringTokenizer st = new StringTokenizer(linha, ";");
		
		int i = 0;
		while(st.hasMoreElements()) {
			String element = (String) st.nextElement();
			
			if(i == 0){
				
				nome = element;
				
			} else if(i == 1){
				
				email = element;
				
			} else if(i == 2){
				
				empresa = element;
				
			} else if(i == 3){
				
				telefone = element;
				
			}
			i++;
		}
		
		if( ! Uteis.ehNuloOuVazio(email) ) {
			
			if(email.contains("/")) {
				StringTokenizer st2 = new StringTokenizer(email, "/");
				
				while(st2.hasMoreElements()) {
					String element = (String) st2.nextElement();
					
					importarContato(nome, element, empresa, telefone, contatoGrupo, usuario);
				}
			} else {
				importarContato(nome, email, empresa, telefone, contatoGrupo, usuario);
			}
			
			qtdSucesso++;
		} else {
			linhasErro.add(numeroLinha);
		}
	}

	private void importarContato(String nome, String email, String empresa, String telefone, ContatoGrupo contatoGrupo, Usuario usuario) {
		email = email.trim();
		
		Contato contato = contatoNegocio.consultarContatoEmail(email);
		
		if(contato == null) {
			contato = new Contato();
			contato.setCadastradoPor(usuario.getNome());
			contato.setDataCriacao(new Date());
		}
		
		contato.setNome(nome);
		contato.setEmail(email);
		
		if(empresa != null && empresa.length() != 0) {
			contato.setEmpresa(empresa);
		}
		
		if(telefone != null && telefone.length() != 0) {
			contato.setTelefone(telefone);
		}
		
		contatoNegocio.incluirAtualizarImportacao(contato, contatoGrupo);
	}
	
	public String getLog() {
		String retorno = "";
		
		if(qtdSucesso > 0) {
			retorno += "<strong>Qtd. de linhas importadas:</strong> "+qtdSucesso+"<br/>";
		}
		
		if( ! linhasErro.isEmpty() ) {
			retorno += "<strong>Linhas ignoradas:</strong> "+linhasErro+"<br/>";
		}
		
		if( ! Uteis.ehNuloOuVazio(erro) ) {
			retorno += "<strong>ERRO:</strong> "+erro;
		}
		
		return retorno;
	}

	@Override
	public void importarExcelFuncionario(MultipartFile arquivo, String caminho, String pacote) throws Exception {
		// TODO Auto-generated method stub
		
	}
}