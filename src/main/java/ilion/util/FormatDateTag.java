package ilion.util;

import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.joda.time.LocalDateTime;

public class FormatDateTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	
	private Object value;
	private String pattern;

	public void setValue(Object value) {
		this.value = value;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}


	@Override
	public int doStartTag() throws JspException {
		
		try {
			
			JspWriter out = pageContext.getOut();
			
			if( value == null ) {
				out.print( "---" );
			} else if( value instanceof Date ) {
				Date d = (Date) value;
				out.print( Uteis.formatarDataHora(d, pattern) );
			} else if( value instanceof LocalDateTime ) {
				LocalDateTime ldt = (LocalDateTime) value;
				String dataFormat = Uteis.formatarUTCToLocal(ldt, pattern);
				out.print( dataFormat );
			} else if( value instanceof java.time.LocalDate ) {
				
				java.time.LocalDate ldt = (java.time.LocalDate) value;
				out.print( ldt.format(DateTimeFormatter.ofPattern(pattern)) );
				
			} else if( value instanceof java.time.LocalDateTime ) {
				java.time.LocalDateTime ldt = (java.time.LocalDateTime) value;
				out.print( ldt.format(DateTimeFormatter.ofPattern(pattern)) );
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return SKIP_BODY;
	}
}