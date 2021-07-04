package ilion.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

import org.apache.log4j.Logger;

public class Html2Text extends HTMLEditorKit.ParserCallback {
	StringBuffer s;

	public Html2Text() {
	}

	public void parse(String texto) throws IOException {
		StringReader stringReader = new StringReader(texto);
		
		parse(stringReader);
	}
	
	public void parse(Reader in) throws IOException {
		s = new StringBuffer();
		ParserDelegator delegator = new ParserDelegator();
		// the third parameter is TRUE to ignore charset directive
		delegator.parse(in, this, Boolean.TRUE);
	}

	public void handleText(char[] text, int pos) {
		s.append(text);
	}

	public String getText() {
		return s.toString();
	}

	public static String parseReturn(String texto) {
		try {
			Html2Text html2Text = new Html2Text();
			
			html2Text.parse(new StringReader(texto));
			return html2Text.getText();
		} catch (Exception e) {
			Logger.getLogger(Html2Text.class.getName()).error("", e);
			return texto;
		}
	}
	
//	public static void main(String[] args) {
//		try {
//			// the HTML to convert
//			FileReader in = new FileReader("java-new.html");
//			Html2Text parser = new Html2Text();
//			parser.parse(in);
//			in.close();
//			System.out.println(parser.getText());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}