package ilion.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

public class UteisWeb {

	private static final int _1_SEGUNDO = 1000;

	private static final int _CONNECTION_TIMEOUT = 5 * _1_SEGUNDO;

	private static final int _READ_TIMEOUT = 30 * _1_SEGUNDO;

	private static final Logger logger = Logger.getLogger(UteisWeb.class);

	private static HttpClient httpClient; 

	static {
		httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(_CONNECTION_TIMEOUT);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(_READ_TIMEOUT);		
	}

//	public static void acceptSSL() {
//		TrustManager[] trustAllCerts = new TrustManager[] { 
//				new X509TrustManager() {
//					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//						return null;
//					}
//					public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
//					}
//					public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
//					}
//				}
//		};
//
//		try {
//			SSLContext ctx = SSLContext.getInstance("TLS");
//			ctx.init(new KeyManager[0], trustAllCerts, new SecureRandom());
//			SSLContext.setDefault(ctx);
//		} catch (Exception e) {
//			String m = "Erro ao chamar metodo acceptSSL()";
//			logger.error(m, e);
//		}
//	}

	public static String makeGet(String url, Map<String, String> headersMap) throws Exception {

		//acceptSSL();

		GetMethod httpMethod = new GetMethod(url);
		
		if( ! headersMap.isEmpty() ) {
			for (String headerName : headersMap.keySet()) {
				String headerValue = headersMap.get(headerName);
				
				httpMethod.addRequestHeader(headerName, headerValue);			
			}
		}

		if (logger.isDebugEnabled()) {
			logger.info("GET: '" + url + "'");
		}

		try {

//			StopWatch stopWatch = new StopWatch();
//			stopWatch.start();

			httpClient.executeMethod(httpMethod);
			
			
//			stopWatch.stop();
			String resposta = httpMethod.getResponseBodyAsString();
			
			if( httpMethod.getStatusCode() >= 400 ) {
				throw new RuntimeException("erro ao GET: "+url+", statusCode: "+httpMethod.getStatusCode()+", response: "+resposta);
			}
			
//			if (logger.isDebugEnabled()) {
//				logger.info("Retorno [em " + stopWatch + "]: \n" + resposta);
//			}
			
			return resposta;
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		} finally {
			httpMethod.releaseConnection();
		}
	}
	
	public static String makePostJson2(String endpoint, String content, Map<String, String> paramsMap, Map<String, String> headersMap) throws Exception {
		
		URL url = new URL(endpoint);
		URLConnection con = url.openConnection();
		HttpURLConnection http = (HttpURLConnection)con;
		http.setRequestMethod("POST"); // PUT is another valid option
		http.setDoOutput(true);
		
		byte[] out = content.getBytes(StandardCharsets.ISO_8859_1);
		int length = out.length;

		http.setFixedLengthStreamingMode(length);
		
		for (String key : headersMap.keySet()) {
			http.setRequestProperty(key, headersMap.get(key));
		}
		
		try(OutputStream os = http.getOutputStream()) {
			
			os.write(out);
			
//			for (String key : paramsMap.keySet()) {
//				sendField(os, key, (String) paramsMap.get(key));
//			}
			
		}
		http.connect();
		
		
		String jsonError = readIS(http.getErrorStream(), "ISO-8859-1");
		String jsonSuccess = readIS(http.getInputStream(), "ISO-8859-1");
		
		if (logger.isDebugEnabled()) {
			logger.info(endpoint+": success: "+jsonSuccess+", error: "+jsonError);
		}
		
		return jsonSuccess;
	}
	
//	private static void sendField(OutputStream out, String name, String field) throws Exception {
//	    String o = "Content-Disposition: form-data; name=\"" 
//	             + URLEncoder.encode(name,"UTF-8") + "\"\r\n\r\n";
//	    out.write(o.getBytes(StandardCharsets.ISO_8859_1));
//	    out.write(URLEncoder.encode(field,"UTF-8").getBytes(StandardCharsets.ISO_8859_1));
//	    out.write("\r\n".getBytes(StandardCharsets.ISO_8859_1));
//	}
	
	private static String readIS(InputStream is, String charset) {
		StringBuffer res = new StringBuffer();
		
		try {
			BufferedReader bin = new BufferedReader(new InputStreamReader(is, charset));
			String line;
			while ((line = bin.readLine()) != null) {
				res.append(line);
			}
			bin.close();			
		} catch (Exception e) {
			logger.error("erro ao ler input stream", e);
		}
		
		return res.toString();
	}
	
	
	public static String makePostJson(String url, String content, Map<String, String> paramsMap, Map<String, String> headersMap) throws Exception {
		
		PostMethod post = new PostMethod(url);
		
//		if (logger.isDebugEnabled()) {
//			logger.info("Destino: '" + url + "'");
//		}
		
		try {
			
			for (String key : headersMap.keySet()) {
				
				post.setRequestHeader(key, headersMap.get(key));
				
			}
			
			org.apache.commons.httpclient.params.HttpClientParams params = new org.apache.commons.httpclient.params.HttpClientParams();
			
			for (String key : paramsMap.keySet()) {
				
				params.setParameter(key, paramsMap.get(key));
				
			}
			
			httpClient.setParams(params);
			
			StringRequestEntity sre = new StringRequestEntity(content, "application/json", "ISO-8859-1");
			post.setRequestEntity(sre);
			
			httpClient.executeMethod(post);
			
			Integer statusCode = post.getStatusCode();
			String resposta = post.getResponseBodyAsString();
			
			if (logger.isDebugEnabled()) {
				logger.info("StatusCode: "+statusCode+", Resposta: '" + resposta + "'");
			}
			
			if( statusCode >= 400 ) {
				throw new RuntimeException("Erro ao efetuar post url: "+url+". StatusCode: "+statusCode+", Resposta: "+resposta);
			}
			
			return resposta;
			
		} finally {
			post.releaseConnection();
		}
	}

//	public static void main(String[] args) {
//		
//		String autentication = Base64.getEncoder().encodeToString("api:rjapirj".getBytes());
//		
//		Map<String, String> headers = new HashMap<>();
//		
//		headers.put("Authorization", "Basic "+autentication);
//		
//		String json = makeGet("http://18.235.188.113:8090/api-gateway/localidade/buscaOrigem", headers);
//		System.out.println(json);
//		
//	}
}
