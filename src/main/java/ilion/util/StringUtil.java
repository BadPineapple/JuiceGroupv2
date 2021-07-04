package ilion.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import ilion.gc.util.UteisGC;



/**
 * String Utility Class This is used to encode passwords programmatically
 *
 * <p>
 * <a h
 * ref="StringUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @version $Revision: 1.1 $ $Date: 2004/02/03 04:56:21 $
 */
public class StringUtil {

	public static String encode(String str) {
		byte[] bytesEncoded = Base64.encodeBase64(str.getBytes());
		
		return new String(bytesEncoded);
	}
	
	public static String decode(String str) {
		byte[] bytesDecoded = Base64.decodeBase64(str.getBytes());
		
		return new String(bytesDecoded);
	}
	
	public static String encodePassword(String s) {
		return encode(s);
	}
	
	public static String decodePassword(String s) {
		return decode(s);
	}
	
    public static Object buscarValor(String xml, UteisGC.CONTEUDOINFO_NOS no, Class clazz) {
    	return buscarValor(xml, no.toString(), clazz);
    }
    
	public static Object buscarValor(String xml, String no, Class clazz) {
		if(xml != null && no != null && xml.contains("<"+no+">")) {
			String s = xml.substring(xml.indexOf("<"+no+">")+no.toString().length()+2, xml.indexOf("</"+no+">"));
			if(Boolean.class == clazz) {
				return Boolean.valueOf(s);
			} else if(Integer.class == clazz) {
				return new Integer(s);
			} else if(Long.class == clazz) {
				return new Long(s);
			} else if(String.class == clazz) {
				return s;
			}
		}
		return null;
	}
	
	private static Map<String, String> caracteresEspeciaisMap;
	static {
		caracteresEspeciaisMap = new HashMap<String, String>();
		caracteresEspeciaisMap.put("â", "a");
		caracteresEspeciaisMap.put("Â", "A");
		caracteresEspeciaisMap.put("á", "a");
		caracteresEspeciaisMap.put("Á", "A");
		caracteresEspeciaisMap.put("ã", "a");
		caracteresEspeciaisMap.put("Ã", "A");
		caracteresEspeciaisMap.put("à", "a");
		caracteresEspeciaisMap.put("À", "A");
		caracteresEspeciaisMap.put("ê", "e");
		caracteresEspeciaisMap.put("Ê", "E");
		caracteresEspeciaisMap.put("é", "e");
		caracteresEspeciaisMap.put("É", "E");
		caracteresEspeciaisMap.put("í", "i");
		caracteresEspeciaisMap.put("Í", "I");
		caracteresEspeciaisMap.put("î", "i");
		caracteresEspeciaisMap.put("Î", "I");
		caracteresEspeciaisMap.put("ì", "i");
		caracteresEspeciaisMap.put("Ì", "I");
		caracteresEspeciaisMap.put("ó", "o");
		caracteresEspeciaisMap.put("Ó", "O");
		caracteresEspeciaisMap.put("ô", "o");
		caracteresEspeciaisMap.put("Ô", "O");
		caracteresEspeciaisMap.put("õ", "o");
		caracteresEspeciaisMap.put("Õ", "O");
		caracteresEspeciaisMap.put("ò", "o");
		caracteresEspeciaisMap.put("Ò", "O");
		caracteresEspeciaisMap.put("ú", "u");
		caracteresEspeciaisMap.put("Ú", "U");
		caracteresEspeciaisMap.put("û", "u");
		caracteresEspeciaisMap.put("Û", "U");
		caracteresEspeciaisMap.put("ç", "c");
		caracteresEspeciaisMap.put("Ç", "C");
		caracteresEspeciaisMap.put("ñ", "n");
		caracteresEspeciaisMap.put("Ñ", "N");
		caracteresEspeciaisMap.put("ª", "");
		caracteresEspeciaisMap.put("º", "");
		caracteresEspeciaisMap.put("°", "");
		caracteresEspeciaisMap.put("¹", "");
		caracteresEspeciaisMap.put("²", "");
		caracteresEspeciaisMap.put("³", "");
		caracteresEspeciaisMap.put("£", "");
		caracteresEspeciaisMap.put("¢", "");
		caracteresEspeciaisMap.put("¬", "");
		
	}
	
	public static String substituirAcentos(String texto) {
		for (String acento : caracteresEspeciaisMap.keySet()) {
			if(texto.contains(acento)) {
				texto = texto.replaceAll(acento, caracteresEspeciaisMap.get(acento));
			}
		}
		return texto;
	}
	
	private static List<Character> caracteresPermitidos;
	static {
		caracteresPermitidos = new ArrayList<Character>();
		
		caracteresPermitidos.add('-');
		caracteresPermitidos.add('a');
		caracteresPermitidos.add('b');
		caracteresPermitidos.add('c');
		caracteresPermitidos.add('d');
		caracteresPermitidos.add('e');
		caracteresPermitidos.add('f');
		caracteresPermitidos.add('g');
		caracteresPermitidos.add('h');
		caracteresPermitidos.add('i');
		caracteresPermitidos.add('j');
		caracteresPermitidos.add('k');
		caracteresPermitidos.add('l');
		caracteresPermitidos.add('m');
		caracteresPermitidos.add('n');
		caracteresPermitidos.add('o');
		caracteresPermitidos.add('p');
		caracteresPermitidos.add('q');
		caracteresPermitidos.add('r');
		caracteresPermitidos.add('s');
		caracteresPermitidos.add('t');
		caracteresPermitidos.add('u');
		caracteresPermitidos.add('v');
		caracteresPermitidos.add('x');
		caracteresPermitidos.add('y');
		caracteresPermitidos.add('w');
		caracteresPermitidos.add('z');
		caracteresPermitidos.add('0');
		caracteresPermitidos.add('1');
		caracteresPermitidos.add('2');
		caracteresPermitidos.add('3');
		caracteresPermitidos.add('4');
		caracteresPermitidos.add('5');
		caracteresPermitidos.add('6');
		caracteresPermitidos.add('7');
		caracteresPermitidos.add('8');
		caracteresPermitidos.add('9');
	}
	
	public static String excluirCaracteresNaoPermitidos(String texto) {
		if(texto != null) {
			
			List<String> paraRetirar = new ArrayList<String>();
			for (int i = 0; i < texto.length(); i++) {
				Character c = texto.charAt(i);
				
				if( ! caracteresPermitidos.contains(c)) {
					paraRetirar.add(c.toString());
				}
			}
			
			for (String s : paraRetirar) {
				texto = texto.replaceAll("\\"+s, "");
			}
		}
		return texto;
	}
	
	public static String substituirCaracteresEspeciais(String texto) {
		for (String c : caracteresEspeciaisMap.keySet()) {
			if(texto.contains(c)) {
				texto = texto.replaceAll("\\"+c, caracteresEspeciaisMap.get(c));
			}
		}
		return texto;
	}

	private static final String enderecoUrlRegex = "[\\|\\/\\\\\\s?!,.:;=_<>'\"%$()\\[\\]{}-]+";
	
	public static String ajustarTextoParaUrl(String texto) {
		if(texto == null) {
			return null;
		}
		
		if(texto.contains("*")) {
			texto = texto.replace("*", "");
		}
		
		if(texto.contains("+")) {
			texto = texto.replace("+", "");
		}
		
		texto = texto.toLowerCase();
		
		texto = substituirCaracteresEspeciais(texto);
		
		texto = texto.replaceAll(enderecoUrlRegex, "-");
		
		if(texto != null) {
			if(texto.startsWith("-")) {
				texto = texto.substring(1);
			}
			if(texto.endsWith("-")) {
				texto = texto.substring(0, texto.length()-1);
			}
		}
		
		texto = excluirCaracteresNaoPermitidos(texto);
		
		return texto;
	}
	
	public static String sanitize(String string) {
		
		if( Uteis.ehNuloOuVazio(string) ) {
			return "";
		}
		
		return string.replaceAll("[^a-zA-Z0-9]+", "");
	}
	
	public static String ajustarTextoParaBusca(String texto) {
		if(texto == null) {
			return null;
		}
		
		if(texto.contains("+")) {
			texto = texto.replace("+", "");
		}
		
		texto = texto.toLowerCase();
		
		texto = substituirCaracteresEspeciais(texto);
		
		texto = texto.replaceAll(enderecoUrlRegex, " ");
		
		return texto;
	}
	
}