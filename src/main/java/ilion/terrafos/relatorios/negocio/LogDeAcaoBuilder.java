package ilion.terrafos.relatorios.negocio;

import java.util.HashMap;
import java.util.Map;

import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.Pasto;
import ilion.util.json.GSONUteis;

public class LogDeAcaoBuilder {

	private LogDeAcao logDeAcao = new LogDeAcao();
	
	private Map<String, Object> info = new HashMap<>();
	
	public LogDeAcaoBuilder doPasto(Pasto pasto) {
		
		logDeAcao.setPasto(pasto);
		
		return this;
	}
	
	public LogDeAcaoBuilder doUsuario(Usuario usuario) {
		
		logDeAcao.setUsuario(usuario);
		
		return this;
	}
	
	public LogDeAcaoBuilder doTipo(LogDeAcaoTipo tipo) {
		
		logDeAcao.setTipo(tipo);
		
		return this;
	}
	
	public LogDeAcaoBuilder comInfo(String key, Object value) {
		
		info.put(key, value);
		
		return this;
	}
	
	public LogDeAcao build() {
		
		String infoJson = GSONUteis.getInstance().toJson(info);
		logDeAcao.setInfoJson(infoJson);
		
		return logDeAcao;
	}
}