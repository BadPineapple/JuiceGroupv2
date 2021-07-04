package ilion.util.busca;

import java.util.HashMap;
import java.util.Map;

public class BuscaUtil {

	private static final String caracteresDesnecessariosRegex = "[\\|\\/\\\\\\s?!,.:;=_<>'\"%$()\\[\\]{}-]+";
	
	private static Map<String, String> stringToReplaceMap;
	static {
		stringToReplaceMap = new HashMap<String, String>();
		stringToReplaceMap.put("â", "a");
		stringToReplaceMap.put("Â", "A");
		stringToReplaceMap.put("á", "a");
		stringToReplaceMap.put("Á", "A");
		stringToReplaceMap.put("ã", "a");
		stringToReplaceMap.put("Ã", "A");
		stringToReplaceMap.put("à", "a");
		stringToReplaceMap.put("À", "A");
		stringToReplaceMap.put("ê", "e");
		stringToReplaceMap.put("Ê", "E");
		stringToReplaceMap.put("é", "e");
		stringToReplaceMap.put("É", "E");
		stringToReplaceMap.put("í", "i");
		stringToReplaceMap.put("Í", "I");
		stringToReplaceMap.put("î", "i");
		stringToReplaceMap.put("Î", "I");
		stringToReplaceMap.put("ì", "i");
		stringToReplaceMap.put("Ì", "I");
		stringToReplaceMap.put("ó", "o");
		stringToReplaceMap.put("Ó", "O");
		stringToReplaceMap.put("ô", "o");
		stringToReplaceMap.put("Ô", "O");
		stringToReplaceMap.put("õ", "o");
		stringToReplaceMap.put("Õ", "O");
		stringToReplaceMap.put("ò", "o");
		stringToReplaceMap.put("Ò", "O");
		stringToReplaceMap.put("ú", "u");
		stringToReplaceMap.put("Ú", "U");
		stringToReplaceMap.put("û", "u");
		stringToReplaceMap.put("Û", "U");
		stringToReplaceMap.put("ç", "c");
		stringToReplaceMap.put("Ç", "C");
		stringToReplaceMap.put("ñ", "n");
		stringToReplaceMap.put("Ñ", "N");
		stringToReplaceMap.put("ª", "");
		stringToReplaceMap.put("º", "");
		stringToReplaceMap.put("°", "");
		stringToReplaceMap.put("¹", "");
		stringToReplaceMap.put("²", "");
		stringToReplaceMap.put("³", "");
		stringToReplaceMap.put("£", "");
		stringToReplaceMap.put("¢", "");
		stringToReplaceMap.put("¬", "");
		stringToReplaceMap.put(" de ", " ");
		stringToReplaceMap.put(" e ", " ");
		
	}
	
	public static String ajustar(String texto) {
		if(texto == null) {
			return null;
		}
		
		if(texto.contains("+")) {
			texto = texto.replace("+", "");
		}
		
		texto = texto.toLowerCase();
		
		texto = replaceStrings(texto);
		
		texto = texto.replaceAll(caracteresDesnecessariosRegex, " ");
		
		return texto;
	}
	
	public static String replaceStrings(String texto) {
		for (String c : stringToReplaceMap.keySet()) {
			if(texto.contains(c)) {
				texto = texto.replaceAll("\\"+c, stringToReplaceMap.get(c));
			}
		}
		return texto;
	}
}
