package ilion.util.json;

import java.util.Map;

public class RespostaStatus {
	
	public static RespostaStatus sucesso;
	
	public static RespostaStatus clienteNaoLogado;
	
	static {
		sucesso = new RespostaStatus();
		sucesso.status = RespostaStatusEnum.SUCESSO;
		sucesso.mensagem = "";
		
		clienteNaoLogado = new RespostaStatus();
		clienteNaoLogado.status = RespostaStatusEnum.CLIENTE_NAO_LOGADO;
		clienteNaoLogado.mensagem = "";
	}
	
	private RespostaStatusEnum status;
	private String mensagem;
	private Map<String, Object> dados;
	
	public RespostaStatusEnum getStatus() {
		return status;
	}
	
	public void setStatus(RespostaStatusEnum status) {
		this.status = status;
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Map<String, Object> getDados() {
		return dados;
	}

	public void setDados(Map<String, Object> dados) {
		this.dados = dados;
	}

	public static RespostaStatus erroComMensagem(String msg) {
		RespostaStatus rs = new RespostaStatus();
		
		rs.status = RespostaStatusEnum.ERRO;
		rs.mensagem = msg;
		
		return rs;
	}
	
	public static RespostaStatus erroComException(Exception e) {
		RespostaStatus rs = new RespostaStatus();
		
		rs.status = RespostaStatusEnum.ERRO;
		rs.mensagem = e.getMessage();
		
		return rs;
	}
	
//	public static RespostaStatus fromClienteLogado(ClienteLogadoVH clienteLogadoVH) {
//		RespostaStatus rs = new RespostaStatus();
//		
//		rs.status = RespostaStatusEnum.SUCESSO;
//		rs.mensagem = "";
//		rs.dados = new HashMap<String, Object>();
//		rs.dados.put("cliente", clienteLogadoVH);
//		
//		return rs;
//	}
//	
//	public static RespostaStatus fromCarrinhoVH(CarrinhoVH carrinhoVH) {
//		RespostaStatus rs = new RespostaStatus();
//		
//		rs.status = RespostaStatusEnum.SUCESSO;
//		rs.mensagem = "";
//		rs.dados = new HashMap<String, Object>();
//		rs.dados.put("carrinho", carrinhoVH);
//		
//		return rs;
//	}
}