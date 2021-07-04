package ilion.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
@Service
public class HtmlLinkExtrator {

	static Logger logger = Logger.getLogger(HtmlLinkExtrator.class);

	private Pattern patternTag, patternLink;
	private Matcher matcherTag, matcherLink;

	private static final String HTML_A_TAG_PATTERN = 
			"(?i)<a([^>]+)>(.+?)</a>";

	private static final String HTML_A_HREF_TAG_PATTERN = 
			"\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

	public HtmlLinkExtrator(){
		patternTag = Pattern.compile(HTML_A_TAG_PATTERN);
		patternLink = Pattern.compile(HTML_A_HREF_TAG_PATTERN);
	}

	/**
	 * Validate html with regular expression
	 * @param html html content for validation
	 * @return List<String> links and link text
	 */
	public List<String> grabHTMLLinks(final String html){

		List<String> result = new ArrayList<String>();

		matcherTag = patternTag.matcher(html);

		while(matcherTag.find()){
			
			String href = null;
			
			try {
				href = matcherTag.group(1); //href
				//String linkText = matcherTag.group(2); //link text
				
				if( ! Uteis.ehNuloOuVazio(href) ) {
					matcherLink = patternLink.matcher(href);
					
					while(matcherLink.find()){
						
						String link = matcherLink.group(1); //link
						
						result.add(link);
						
					}
				}
			} catch (Exception e) {
				logger.error("href: "+href, e);
			}
			
		}
		
		return result;
	}

}
