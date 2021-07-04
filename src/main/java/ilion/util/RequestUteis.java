package ilion.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;

public class RequestUteis {

	static Logger logger = Logger.getLogger(RequestUteis.class);
	
	public static String sendPost(
			String endpoint, 
			Map<String, String> headers, 
			String data) throws Exception {
		// URL of target page script.
		URL url = new URL(endpoint);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

		urlConn.setDoInput(true);
		urlConn.setDoOutput(true);
		urlConn.setUseCaches(false);
		
		for (String key : headers.keySet()) {
			urlConn.setRequestProperty(key, headers.get(key));
		}
		//urlConn.setRequestMethod("POST");

		// Send POST output.
		DataOutputStream cgiInput = new DataOutputStream(urlConn.getOutputStream());
		
		cgiInput.write(data.getBytes("UTF-8"));
		cgiInput.flush();
		cgiInput.close();
		
		int responseCode = urlConn.getResponseCode();
		
		if( responseCode != 200 ) {
			String respostaError = lerInputStream(urlConn.getErrorStream());
			throw new RuntimeException("responseCode: "+responseCode+", msg: "+respostaError);
		}
		
		String resposta = lerInputStream(urlConn.getInputStream());
		
		if( logger.isDebugEnabled() ) {
			logger.debug("sendPost: "+endpoint+", data: "+data+", responseCode: "+responseCode);
		}
		
		return resposta;
	}
	
	public static String sendPost(
			String endpoint, 
			Map<String, String> headers, 
			Map<String, String> params) throws Exception {
		// URL of target page script.
		URL url = new URL(endpoint);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

		urlConn.setDoInput(true);
		urlConn.setDoOutput(true);
		urlConn.setUseCaches(false);
		
		for (String key : headers.keySet()) {
			urlConn.setRequestProperty(key, headers.get(key));
		}
		//urlConn.setRequestMethod("POST");

		// Send POST output.
		DataOutputStream cgiInput = new DataOutputStream(urlConn.getOutputStream());
		
		String content = converterParametros(params);
		
		cgiInput.writeBytes(content);
		cgiInput.flush();
		cgiInput.close();
		
		int responseCode = urlConn.getResponseCode();
		
		if( responseCode != 200 ) {
			String respostaError = lerInputStream(urlConn.getErrorStream());
			throw new RuntimeException("responseCode: "+responseCode+", msg: "+respostaError);
		}
		
		String resposta = lerInputStream(urlConn.getInputStream());
		
		return resposta;
	}
	
	private static String lerInputStream(InputStream is) {
		StringBuilder resposta = new StringBuilder();
		try {
			BufferedReader cgiOutput = 
					new BufferedReader(new InputStreamReader(is, "UTF-8"));


			String line = null;
			while (null != (line = cgiOutput.readLine())){
				resposta.append(line);
			}
			cgiOutput.close();

			return resposta.toString();
		} catch (Exception e) {
		}
		
		return resposta.toString();
	}

	private static String converterParametros(Map<String, String> parametros) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		
		for (String k : parametros.keySet()) {
			String valor = parametros.get(k);
			
			sb.append(k).append("=").append(valor).append("&");
		}
		
		return sb.toString();
	}
	
//	public static void main(String[] args) throws Exception {
//		
//		String endpoint = "https://ws.pagseguro.uol.com.br/v2/checkout/";
//		
//		Map<String, String> headers = new HashMap<String, String>();
//		//headers.put("Content-Type", "application/html");
//		
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("email", "allmax.documentos@gmail.com");
//		params.put("token", "D2670A04684C4C9EA1EC3A3A09028B88");
//		params.put("currency", "BRL");
//		params.put("reference", "REF1111");
//		params.put("itemId1", "0001");
//		params.put("itemDescription1", "H801/CE311/CF351A - CARTUCHO DE TONER COMPATIVEL HP YELLOW (H801/CE311/CF351A - CARTUCHO DE TONER MAIS QUE 100 CARACTERES");
//		params.put("itemAmount1", "30.00");
//		params.put("itemQuantity1", "1");
//		
////		params.put("paymentMethodGroup1", "CREDIT_CARD");
////		params.put("paymentMethodConfigKey1_1", "MAX_INSTALLMENTS_LIMIT");
////		params.put("paymentMethodConfigValue1_1", "3");
//		
//		String response = sendPost(endpoint, headers, params);
//		System.out.println(response);
//		
////		String code = getValorFromXmlJDOM(response, "code");
////		System.out.println(code);
//	}
}