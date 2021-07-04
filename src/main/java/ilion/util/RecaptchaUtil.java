package ilion.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;

@Service
public class RecaptchaUtil {
	
	private String SECRET_KEY = "favor-alterar"; 
	
	private Gson gson = new Gson();
	
	@Autowired
	private PropNegocio propNegocio;
	
	public boolean isValid(String response) {
		
		String url = propNegocio.findValueById(PropEnum.URL);
		
		if( url.startsWith("http://localhost:8080") ) {
			return true;
		}
		
		try {
			
			String endpoint = "https://www.google.com/recaptcha/api/siteverify?" + "secret=" + SECRET_KEY + "&response="
					+ response;
			
			InputStream res = new URL(endpoint).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			
			int cp;
			
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
			String jsonText = sb.toString();
			res.close();
			
			Recaptcha rec = gson.fromJson(jsonText, Recaptcha.class);
			return rec.getSuccess();
			
		} catch (Exception e) {

			return false;
		}
	}

}