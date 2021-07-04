package ilion.util;

import java.util.Base64;

public class PasswordBase64{  

	public static String encode(final String text) {
		return Base64.getEncoder().encodeToString(text.getBytes());  
	}  

	public static String decode(final String text) { 
		return new String(Base64.getDecoder().decode(text.getBytes()));  
	}  

}  
