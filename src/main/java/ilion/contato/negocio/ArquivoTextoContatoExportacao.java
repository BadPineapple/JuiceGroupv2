package ilion.contato.negocio;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.util.Uteis;

@Service
public class ArquivoTextoContatoExportacao implements ContatoExportacao {
	
	@Autowired
	private ContatoNegocio contatoNegocio;
	
	private ContatoGrupo contatoGrupo;
	private Boolean permissaoEmail;
	private HttpServletResponse response;
	
	private StringBuilder exportados;
	
	
	public ArquivoTextoContatoExportacao() {
		super();
	}

	public ArquivoTextoContatoExportacao(ContatoGrupo contatoGrupo, Boolean permissaoEmail, HttpServletResponse response) {
		super();
		this.contatoGrupo = contatoGrupo;
		this.permissaoEmail = permissaoEmail;
		this.response = response;
		
		exportados = new StringBuilder();
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
	
	public void setExportados(StringBuilder exportados) {
		this.exportados = exportados;
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
	
	private void adicionarContatos(List<Map<String, Object>> contatosMap) {
		if(contatosMap != null) {
			
			for (Map<String, Object> cMap : contatosMap) {
				
				String nome = (String) cMap.get("nome");
				String email = (String) cMap.get("email");
				String telefone = (String) cMap.get("telefone");
				
				exportados.append(nome).append(";").append(email).append(";").append(telefone).append(";").append("\r\n");
			}
			
		}
	}
	
	@Autowired
	ArquivoNegocio arquivoNegocio;
	
	private void enviarParaResponse() throws Exception {
		String contatosExportados = exportados.toString();
		
		if(contatosExportados.length() != 0) {
			String nome = "contatos-"+Uteis.formatarDataHora(new Date(), "yyyy-MM-dd-HH-mm")+".txt";
			
			ByteArrayInputStream inputStream = new ByteArrayInputStream(contatosExportados.getBytes());
			
			arquivoNegocio.enviarInputStreamResponse(inputStream, nome, contatosExportados.length(), response);
		}
	}
}