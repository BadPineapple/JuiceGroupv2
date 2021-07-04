package ilion.util;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class RedirectAttributesUtil {

	public static void addFlashAttributeSuccess(String msg, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("msgSuccess", msg);
		
	}
	
	public static void addFlashAttributeError(String msg, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addFlashAttribute("msgError", msg);
		
	}
	
}