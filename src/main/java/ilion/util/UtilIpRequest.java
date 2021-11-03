package ilion.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public final class UtilIpRequest {
	
	static Logger logger = Logger.getLogger(UtilIpRequest.class);
	
	private static final String[] IP_HEADER_CANDIDATES = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };
	
	private static final String[] HOST_NAME_CANDIDATES = { "referer", "origin", "host" };

	// Utility class - private constructor
	private UtilIpRequest() {
	}

	public static String getClientIpAddress(HttpServletRequest request) {
		for (String header : IP_HEADER_CANDIDATES) {
			String ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}
	
	public static Map<String, String> getMapRequisicao(HttpServletRequest request) {
		Map<String, String> requisicao = new HashMap<String, String>();
		Enumeration headersName = request.getHeaderNames();
		//logger.info("---------------------Map Headers - inicio");
		while (headersName.hasMoreElements()) {
			String key = (String) headersName.nextElement();
			String value = request.getHeader(key);
			logger.info(String.format("%s = %s", key, value));
			requisicao.put(key, value);
		}
		//logger.info("---------------------Map Headers - fim");
		return requisicao;
	}
	
	public static String getHostName(HttpServletRequest request) {
		//logger.info("---------------------Map Hostname - inicio");
		for (String header : HOST_NAME_CANDIDATES) {
			String ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				logger.info(String.format("%s = %s", header, ip));
			}
		}
		//logger.info("---------------------Map Hostname - fim");
		for (String header : HOST_NAME_CANDIDATES) {
			String ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		return request.getRemoteHost();
	}
	
	public static String getNomeHost() throws UnknownHostException {
		InetAddress  ip = InetAddress.getLocalHost();
		String  hostname = ip.getHostName();
	    return hostname;
	}
	
/*	public static void logar(HttpServletRequest request) {
		logger.info("---------------------Headers - inicio");
		Enumeration headersName = request.getHeaderNames();
		while (headersName.hasMoreElements()) {
			String key = (String) headersName.nextElement();
			String value = request.getHeader(key);
			logger.info(String.format("%s = %s", key, value));
		}
		logger.info("---------------------Headers - fim");
	}*/
	
	public static String IPExterno() {
        URL ipAdress;
        try {
            ipAdress = new URL("http://myexternalip.com/raw");
            BufferedReader in = new BufferedReader(new InputStreamReader(ipAdress.openStream()));
            String ip = in.readLine();
            return ip;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return "";
    }
	
	
}

