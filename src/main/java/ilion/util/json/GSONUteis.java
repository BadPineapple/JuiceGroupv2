package ilion.util.json;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ilion.util.exceptions.ValidacaoException;

public class GSONUteis {

	static Logger logger = Logger.getLogger(GSONUteis.class);
	
	private static Gson gson;
	
	static {
		gson = getInstance();
	}
	
	public static Gson getInstance() {
		
		if( gson == null ) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Date.class, new DateAdapter());
			gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gsonBuilder.setPrettyPrinting();
			
			gson = gsonBuilder.create();
		}
		
		return gson;
	}
	
	public static void escreverJSONSucesso(String mensagem, HttpServletResponse response) throws Exception {
		
		String resJson = GSONUteis.objectToJson(new SucessoJSON(mensagem));
		
		escreverJSONString(resJson, response);
	}
	
	public static void escreverJSONErro(String mensagem, HttpServletResponse response) throws Exception {
		
		String resJson = GSONUteis.objectToJson(new ErroJSON(mensagem));
		
		escreverJSONString(resJson, response);
	}
	
	public static void escreverJSONErro(Throwable e, HttpServletResponse response) throws IOException {
		
		if( e instanceof ValidacaoException ) {
			ValidacaoException v = (ValidacaoException) e;
			logger.error("Validacao: "+v.getMessage());
		} else {
			logger.error("", e);
		}
		
		String resJson = GSONUteis.objectToJson(new ErroJSON(e.getMessage()));
		
		escreverJSONString(resJson, response);
	}
	
	public static void escreverJSON(Object obj, HttpServletResponse response) throws Exception {
		
		String resJson = GSONUteis.objectToJson(obj);
		
		escreverJSONString(resJson, response);
	}
	
	public static void escreverJSONString(String res, HttpServletResponse response) throws IOException {
		
		response.setContentType("application/json");
		//response.setCharacterEncoding("UTF-8");
		response.setHeader("pragma", "no-cache");
		response.getWriter().write(res);
		response.getWriter().close();
		
	}
	
	public static String objectToJson(Object obj) {
		
		return gson.toJson(obj);
	}
}