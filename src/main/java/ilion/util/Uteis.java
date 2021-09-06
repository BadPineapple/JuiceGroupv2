package ilion.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.brazilutils.br.cpfcnpj.Cnpj;
import org.brazilutils.br.cpfcnpj.Cpf;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Service;

@Service
public class Uteis {

	static Logger logger = Logger.getLogger(Uteis.class);

	public static String caminhoRealPath;

	public static String caminhoFisico;

	private static SimpleDateFormat simpleDateFormat;
	
	private static DecimalFormat df2MaximumFractionDigits;

	static {
		simpleDateFormat = new SimpleDateFormat("", new Locale("pt", "br"));
		
		df2MaximumFractionDigits = new DecimalFormat();
		df2MaximumFractionDigits.setMaximumFractionDigits(2);
	}

	public static Long converterLong(String s) {
		Long l = null;
		try {
			l = new Long(s);
		} catch (Exception e) {
			l = null;
		}
		return l;
	}

	public static Integer converterInteger(String s) {
		Integer l = null;
		try {
			l = new Integer(s);
		} catch (Exception e) {
			l = null;
		}
		return l;
	}

	public static Byte converterByte(String s) {
		Byte l = null;
		try {
			l = new Byte(s);
		} catch (Exception e) {
			l = null;
		}
		return l;
	}

	public static Boolean converterBoolean(String s) {
		Boolean l = null;
		try {
			l = new Boolean(s);
		} catch (Exception e) {
			l = null;
		}
		return l;
	}

	public static BigDecimal converterBigDecimal(String valor) {
		if(valor == null)
			return null;

		if(nfPTBR2CasasDecimais == null) {
			nfPTBR2CasasDecimais = NumberFormat.getInstance(new Locale("pt", "BR"));

			nfPTBR2CasasDecimais.setCurrency(Currency.getInstance(new Locale("pt", "BR")));
			nfPTBR2CasasDecimais.setMinimumIntegerDigits(1);
			nfPTBR2CasasDecimais.setMinimumFractionDigits(2);
			nfPTBR2CasasDecimais.setMaximumFractionDigits(2);
		}

		try {
			Object number = nfPTBR2CasasDecimais.parseObject(valor);

			BigDecimal retorno = null;

			if(number instanceof Long) {
				Long l = (Long) number;
				retorno = new BigDecimal(l);
			} else if(number instanceof Double) {
				Double d = (Double) number;
				retorno = new BigDecimal(d);
			}

			return retorno;
		} catch (Throwable e) {
			return null;
		}
	}

	private static NumberFormat nfEN2CasasDecimais;

	public static String formatarValorMoedaEN2CasasDecimais(Object valor) {
		if (valor == null)
			return "";

		if (nfEN2CasasDecimais == null) {
			nfEN2CasasDecimais = NumberFormat.getInstance();

			nfEN2CasasDecimais.setMinimumIntegerDigits(1);
			nfEN2CasasDecimais.setMinimumFractionDigits(2);
			nfEN2CasasDecimais.setMaximumFractionDigits(2);
			nfEN2CasasDecimais.setRoundingMode(RoundingMode.DOWN);
			nfEN2CasasDecimais.setGroupingUsed(false);
		}

		return nfEN2CasasDecimais.format(valor);
	}

	public static String formatarDataHora(Date data, String pattern) {
		if (data == null)
			return "";

		simpleDateFormat.applyPattern(pattern);
		return simpleDateFormat.format(data);
	}
	public static String formatarDateTime(DateTime data, String pattern) {
		if (data == null)
			return "";
		
		String teste = data.toString();
		Date opa =  converterDataHora(teste, "dd/MM/YYYY");
		DateTimeFormatter formato = DateTimeFormatter.ofPattern(pattern); 
		return simpleDateFormat.format(opa);
	}

	public static Date converterDataHora(String dataString, String pattern) {
		if (dataString == null || dataString.length() == 0)
			return null;

		simpleDateFormat.applyPattern(pattern);
		try {
			return simpleDateFormat.parse(dataString);
		} catch (Exception e) {
			return null;
		}
	}

	public static String formatarValorMoedaPTBR(Object valor) {
		if (valor == null)
			return "";

		if (nfPTBR2CasasDecimais == null) {
			nfPTBR2CasasDecimais = NumberFormat.getInstance(new Locale("pt",
					"BR"));

			nfPTBR2CasasDecimais.setCurrency(Currency.getInstance(new Locale(
					"pt", "BR")));
			nfPTBR2CasasDecimais.setMinimumIntegerDigits(1);
			nfPTBR2CasasDecimais.setMinimumFractionDigits(2);
			nfPTBR2CasasDecimais.setMaximumFractionDigits(2);
		}

		return nfPTBR2CasasDecimais.format(valor);
	}

	private static NumberFormat nfPTBRSemCasasDecimais;
	private static NumberFormat nfPTBR2CasasDecimais;
	private static NumberFormat nfPTBR4CasasDecimais;

	public static String formatarValorMoedaPTBRSemCasasDecimais(Object valor) {
		if(valor == null)
			return "";

		if(nfPTBRSemCasasDecimais == null) {
			nfPTBRSemCasasDecimais = NumberFormat.getInstance(new Locale("pt", "BR"));

			nfPTBRSemCasasDecimais.setCurrency(Currency.getInstance(new Locale("pt", "BR")));
			nfPTBRSemCasasDecimais.setMinimumIntegerDigits(1);
			nfPTBRSemCasasDecimais.setMinimumFractionDigits(0);
			nfPTBRSemCasasDecimais.setMaximumFractionDigits(0);
		}

		return nfPTBRSemCasasDecimais.format(valor);
	}

	public static String formatarValorMoedaPTBR2CasasDecimais(Object valor) {
		if(valor == null)
			return "";

		if(nfPTBR2CasasDecimais == null) {
			nfPTBR2CasasDecimais = NumberFormat.getInstance(new Locale("pt", "BR"));

			nfPTBR2CasasDecimais.setCurrency(Currency.getInstance(new Locale("pt", "BR")));
			nfPTBR2CasasDecimais.setMinimumIntegerDigits(1);
			nfPTBR2CasasDecimais.setMinimumFractionDigits(2);
			nfPTBR2CasasDecimais.setMaximumFractionDigits(2);
		}

		return nfPTBR2CasasDecimais.format(valor);
	}

	public static String formatarValorMoedaPTBR4CasasDecimais(Object valor) {
		if(valor == null)
			return "";

		if(nfPTBR4CasasDecimais == null) {
			nfPTBR4CasasDecimais = NumberFormat.getInstance(new Locale("pt", "BR"));

			nfPTBR4CasasDecimais.setCurrency(Currency.getInstance(new Locale("pt", "BR")));
			nfPTBR4CasasDecimais.setMinimumIntegerDigits(1);
			nfPTBR4CasasDecimais.setMinimumFractionDigits(2);
			nfPTBR4CasasDecimais.setMaximumFractionDigits(4);
		}

		return nfPTBR4CasasDecimais.format(valor);
	}

	public static CustomNumberEditor novoCustomPrecoPTBREditor(Class clazz, boolean permitirVazio) {

		if (nfPTBR2CasasDecimais == null) {
			nfPTBR2CasasDecimais = NumberFormat.getInstance(new Locale("pt",
					"BR"));

			nfPTBR2CasasDecimais.setCurrency(Currency.getInstance(new Locale(
					"pt", "BR")));
			nfPTBR2CasasDecimais.setMinimumIntegerDigits(1);
			nfPTBR2CasasDecimais.setMinimumFractionDigits(2);
			nfPTBR2CasasDecimais.setMaximumFractionDigits(2);
		}

		return new CustomNumberEditor(clazz, nfPTBR2CasasDecimais, permitirVazio);
	}

	public static String throwableInformation(Throwable t) {
		
		StringWriter sw = new StringWriter();
		
		t.printStackTrace(new PrintWriter(sw));

		return sw.toString();
	}

	public static Boolean dataApos1900(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);

		int ano = c.get(Calendar.YEAR);

		return ano > 1900;
	}

	public static String formatarRequestInfo(HttpServletRequest request) {
		return formatarRequestInfo(request, true);
	}

	public static String formatarRequestInfo(HttpServletRequest request,
			Boolean userAgent) {
		StringBuilder sb = new StringBuilder();

		if (userAgent) {
			sb.append("User-Agent: ");
			sb.append(request.getHeader("User-Agent"));
			sb.append("<br/>");
		}

		sb.append(request.getMethod());
		sb.append("|");
		sb.append(request.getRequestURL()).append("?")
		.append(request.getQueryString());
		sb.append("<br/>");

		for (Iterator iter = request.getParameterMap().keySet().iterator(); iter
				.hasNext();) {
			String k = (String) iter.next();
			if (!"senha".equals(k) && !"confirmar".equals(k)
					&& !"password".equals(k)) {
				sb.append("<b>").append(k).append("</b>=")
				.append(request.getParameter(k)).append("& ");
			}
		}

		return sb.toString();
	}
	
	public static Map<String, String> paramsToMap(HttpServletRequest request) {
		
		Map<String, String> params = new HashMap<>();

		for (Iterator iter = request.getParameterMap().keySet().iterator(); iter
				.hasNext();) {
			String param = (String) iter.next();
			String value = request.getParameter(param);
			
			params.put(param, value);
		}

		return params;
	}

	//	public static boolean checarValidade(HttpServletRequest request, String nomeParametro) throws CaptchaServiceException {
	//		Boolean isResponseCorrect = Boolean.FALSE;
	//
	//		//retrieve the response
	//		String codigoValidador = request.getParameter(nomeParametro);
	//		if(codigoValidador == null || codigoValidador.length() == 0)
	//			return isResponseCorrect;
	//
	//		codigoValidador = codigoValidador.toUpperCase();
	//
	//		//remenber that we need an id to validate!
	//		String captchaId = request.getSession().getId();
	//		// Call the Service method
	//
	//		isResponseCorrect = imageCaptchaService.validateResponseForID(captchaId,
	//				codigoValidador);
	//		return isResponseCorrect;
	//	}
	//
	//	public static ImageCaptchaService getImageCaptchaService() {
	//		return imageCaptchaService;
	//	}

	public static Object getRetornoMetodoReflection(Object objeto, String metodo) {
		Object valor = null;

		try {
			Method method = objeto.getClass().getMethod(metodo);
			valor = method.invoke(objeto);
		} catch (Exception e) {
			// logger.error(e.getMessage());
		}

		return valor;
	}

	private static final String emailRegex = "([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})";

	public static Boolean ehEmailValido(String email) {
		if (email == null) {
			return false;
		}

		email = email.trim();

		if (email.trim().length() == 0) {
			return false;
		}

		return Pattern.matches(emailRegex, email);
	}

	public static Date acrescentar(Date data, int tipoCalendar, int qtd) {
		
		if( qtd == 0 ) {
			return data;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(tipoCalendar, calendar.get(tipoCalendar) + qtd);
		return calendar.getTime();
	}

	public static Date subtrair(Date data, int tipoCalendar, int qtd) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(tipoCalendar, calendar.get(tipoCalendar) - qtd);
		return calendar.getTime();
	}

	public static String getHtml(String urls, String charset) {
		StringBuffer html = new StringBuffer();

		try {
			URL url = new URL(urls);
			BufferedReader bin = new BufferedReader(new InputStreamReader(url.openStream(), charset));
			String line;
			while ((line = bin.readLine()) != null) {
				html.append(line);
			}
			bin.close();
		} catch (Exception e) {
			String m = "erro em getHtml(), URL: "+urls+", charset: "+charset;
			logger.error(m, e);
			//new EmailErroThread(e, m);
			html = new StringBuffer();
		}

		return html.toString();
	}

	public static String getHtml(String urls) {
		return getHtml(urls, "ISO-8859-1");
	}

	public static String formatarStringQueryInObjects(List lista) {
		StringBuilder s = new StringBuilder();
		for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
			Object o = iterator.next();
			s.append("'").append(o).append("',");
		}

		String in = s.toString();
		in = in.substring(0, in.lastIndexOf(","));

		return in;
	}

	public static String formatarStringQueryInComStrings(String ... sVetor) {

		if( sVetor == null || sVetor.length == 0 ) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sVetor.length; i++) {
			Object s = sVetor[i];

			sb.append("'").append(s).append("',");
		}

		String idsIn = sb.toString();
		idsIn = idsIn.substring(0, idsIn.lastIndexOf(","));
		return idsIn;
	}

	@SuppressWarnings("unchecked")
	public static String formatarStringQueryIn(List ids) {

		if( ids == null || ids.isEmpty() ) {
			return null;
		}

		StringBuilder s = new StringBuilder();
		for (Iterator iterator = ids.iterator(); iterator.hasNext();) {
			Long id = (Long) iterator.next();
			s.append(id).append(",");
		}
		String idsIn = s.toString();
		idsIn = idsIn.substring(0, idsIn.lastIndexOf(","));
		return idsIn;
	}

	public static String formatarTempoMilisegundos(Long diferenca) {
		int horas = (int) (diferenca / (1000 * 60 * 60));
		long resto = (int) (diferenca % (1000 * 60 * 60));

		int minutos = (int) resto / (1000 * 60);
		resto = (int) (diferenca % (1000 * 60));

		int segundos = (int) resto / (1000);

		StringBuilder sb = new StringBuilder();
		if (horas != 0) {
			sb.append(horas).append(" hora(s) ");
		}

		if (minutos != 0) {
			sb.append(minutos).append(" minuto(s) ");
		}

		if (segundos != 0) {
			sb.append(segundos).append(" segundo(s) ");
		}

		return sb.toString();
	}

	public static CustomDateEditor novoCustomDateEditor(String pattern,
			boolean permitirVazio) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		simpleDateFormat.setLenient(false);
		return new CustomDateEditor(simpleDateFormat, permitirVazio);
	}

	public static CustomNumberEditor novoCustomNumberEditor(Class clazz,
			boolean permitirVazio) {
		NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt",
				"BR"));
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setMinimumFractionDigits(2);
		numberFormat.setMinimumIntegerDigits(1);

		return new CustomNumberEditor(clazz, numberFormat, permitirVazio);
	}

	public static String encode(String string) {
		if (string == null || string.length() == 0)
			return "";

		String res = string;
		try {
			res = URLEncoder.encode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("encodeURI string: " + string, e);
			res = string;
		}

		res = res.replace("+", "%20");
		return res;
	}

	public static String decode(String string) {
		if (string == null || string.length() == 0)
			return "";

		String res = string;
		try {
			res = URLDecoder.decode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("decodeURI string: " + string, e);
			res = string;
		}

		return res;
	}

	public static void escreverResposta(String retorno, boolean encode,
			HttpServletResponse response) throws Exception {
		if (retorno == null)
			retorno = "";

		if (encode) {
			retorno = encode(retorno);
		}

		response.setHeader("Cache-Control", "no-cache");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().write(retorno);
		response.getWriter().close();
	}

	public static Collection<String> converterStringParaCollection(String s,
			String delim) {
		Collection<String> colecao = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(s, delim);
		while (st.hasMoreElements()) {
			String el = (String) st.nextElement();

			colecao.add(el);
		}

		return colecao;
	}

	public static List<String> formarListaComString(String string, String delim) {
		List<String> lista = null;

		if (string == null || string.length() == 0) {
			return lista;
		}

		lista = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(string, delim);
		while (st.hasMoreElements()) {
			String e = (String) st.nextElement();

			lista.add(e);
		}

		return lista;
	}

	@SuppressWarnings("unchecked")
	public static List<String> formarListaComXml(String xml, String node)
			throws Exception {
		xml = "<root>" + xml + "</root>";

		Document document = DocumentHelper.parseText(xml);

		Element root = document.getRootElement();

		List<String> lista = new ArrayList<String>();

		for (Iterator i = root.elementIterator(node); i.hasNext();) {
			Element el = (Element) i.next();

			String valor = el.getText();

			lista.add(valor);
		}

		return lista;
	}

	public static String formarXmlComLista(List<String> lista, String node) {
		StringBuilder sb = new StringBuilder();

		if (lista != null && !lista.isEmpty()) {
			for (String valor : lista) {
				sb.append("<").append(node).append(">");
				sb.append(valor);
				sb.append("</").append(node).append(">");
			}
		}

		return sb.toString();
	}

	private static Map<Integer, String> diasSemana;
	private static Map<Integer, String> meses;

	static {
		diasSemana = new HashMap<Integer, String>();

		diasSemana.put(Calendar.SUNDAY, "domingo");
		diasSemana.put(Calendar.MONDAY, "segunda-feira");
		diasSemana.put(Calendar.TUESDAY, "terça-feira");
		diasSemana.put(Calendar.WEDNESDAY, "quarta-feira");
		diasSemana.put(Calendar.THURSDAY, "quinta-feira");
		diasSemana.put(Calendar.FRIDAY, "sexta-feira");
		diasSemana.put(Calendar.SATURDAY, "sábado");

		meses = new HashMap<Integer, String>();

		meses.put(Calendar.JANUARY, "janeiro");
		meses.put(Calendar.FEBRUARY, "fevereiro");
		meses.put(Calendar.MARCH, "março");
		meses.put(Calendar.APRIL, "abril");
		meses.put(Calendar.MAY, "maio");
		meses.put(Calendar.JUNE, "junho");
		meses.put(Calendar.JULY, "julho");
		meses.put(Calendar.AUGUST, "agosto");
		meses.put(Calendar.SEPTEMBER, "setembro");
		meses.put(Calendar.OCTOBER, "outubro");
		meses.put(Calendar.NOVEMBER, "novembro");
		meses.put(Calendar.DECEMBER, "dezembro");

	}

	public static String formatarDataPorExtenso(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		Integer diaSemana = c.get(Calendar.DAY_OF_WEEK);
		String diaMes = "" + c.get(Calendar.DAY_OF_MONTH);

		if (diaMes.length() == 1) {
			diaMes = "0" + diaMes;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(diasSemana.get(diaSemana));
		sb.append(", ");
		sb.append(diaMes);
		sb.append(" de ");
		sb.append(meses.get(c.get(Calendar.MONTH)));
		sb.append(" de ");
		sb.append(c.get(Calendar.YEAR));

		return sb.toString();
	}
	
	public static String formatarDataHoraPorExtenso(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		Integer diaSemana = c.get(Calendar.DAY_OF_WEEK);
		String diaMes = "" + c.get(Calendar.DAY_OF_MONTH);
		String horas = "" + c.get(Calendar.HOUR_OF_DAY);
		String minutos = "" + c.get(Calendar.MINUTE);
		
		if (diaMes.length() == 1) {
			diaMes = "0" + diaMes;
		}
		
		if (horas.length() == 1) {
			horas = "0" + horas;
		}
		
		if (minutos.length() == 1) {
			minutos = "0" + minutos;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(diasSemana.get(diaSemana));
		sb.append(", ");
		sb.append(diaMes);
		sb.append(" de ");
		sb.append(meses.get(c.get(Calendar.MONTH)));
		sb.append(" de ");
		sb.append(c.get(Calendar.YEAR));
		sb.append(", ");
		sb.append(horas);
		sb.append(":");
		sb.append(minutos);
		
		return sb.toString();
	}
	
	public static String nomeDoMes(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		
		return meses.get(c.get(Calendar.MONTH));
	}
	
	public static String nomeDoDiaDaSemana(Date data) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		
		Integer diaSemana = c.get(Calendar.DAY_OF_WEEK);
		
		return diasSemana.get(diaSemana);
	}

	public static Date inicioDia(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	public static Date fimDia(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		return c.getTime();
	}
	
	public static Date inicioMes(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);

		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	public static Date fimMes(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);

		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		c.add(Calendar.MONTH, 1);

		c.add(Calendar.DAY_OF_MONTH, -1);

		return c.getTime();
	}

	public static Date inicioAno(int ano) {
		Calendar c = Calendar.getInstance();

		c.set(Calendar.YEAR, ano);

		c.set(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	public static Date fimAno(int ano) {
		Calendar c = Calendar.getInstance();

		c.set(Calendar.YEAR, ano+1);

		c.set(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		c.add(Calendar.SECOND, -1);

		return c.getTime();
	}

	public static Integer diferencaEmMinutos(Date d1, Date d2) {

		if(d1 == null || d2 == null) {
			return 0;
		}

		return diferencaEmMinutos(d1.getTime(), d2.getTime());
	}

	public static Integer diferencaEmMinutos(long l1, long l2) {

		long diferenca = l1 - l2;

		return (int) (diferenca/(1000*60));
	}
	
	public static Integer diferencaEmSegundos(Date d1, Date d2) {

		if(d1 == null || d2 == null) {
			return 0;
		}

		return diferencaEmSegundos(d1.getTime(), d2.getTime());
	}
	
	public static Integer diferencaEmSegundos(long l1, long l2) {
		
		long diferenca = l1 - l2;
		
		return (int) (diferenca/(1000));
	}

	public static Date inicioAno(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	public static Date fimAno(Date data) {
		data = inicioAno(data);

		Calendar c = Calendar.getInstance();
		c.setTime(data);

		c.add(Calendar.YEAR, 1);
		c.add(Calendar.MINUTE, -1);

		return c.getTime();
	}

	public static String escreverNomeCategoria(String grupo, String categoria) {
		if (categoria != null) {
			if (categoria.equals(grupo) || !categoria.startsWith(grupo)) {
				return categoria;
			} else {
				return categoria.substring(grupo.length() + 1,
						categoria.length());
			}
		} else {
			return "null";
		}
	}

	public static Boolean enqueteJaVotada(Long idEnquete, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();

		Boolean jaVotada = false;

		if(cookies != null) {
			for(int i=0;i<cookies.length;i++) {
				if(cookies[i].getName().equals(idEnquete.toString())) {
					jaVotada = true;
				}
			}
		}

		return jaVotada;
	}

	public static Boolean ehNuloOuVazio(String ... s) {

		for (String string : s) {
			if( string == null || string.trim().length() == 0 ) {
				return true;
			}
		}

		return false;
	}

	public static Boolean ehNuloOuZero(Number ... n) {

		for (Number num : n) {
			if( num == null || "0".equals(num.toString()) ) {
				return true;
			}
		}

		return false;
	}

	public static Boolean ehIdValido(Number ... n) {

		for (Number num : n) {
			if( num != null && num.longValue() >0 ) {
				return true;
			}
		}

		return false;
	}

	public static Boolean ehTrue(Boolean b) {
		if( b == null ) {
			return false;
		}

		return b == true;
	}

	public static String bodyToString(HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader bufferedReader = request.getReader();
		char[] charBuffer = new char[128];
		int bytesRead = -1;
		while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
			sb.append(charBuffer, 0, bytesRead);
		}

		return sb.toString();
	}

	private static List<String> cpfsInvalidos;

	static {
		cpfsInvalidos = new ArrayList<String>();
		cpfsInvalidos.add("00000000000");
		cpfsInvalidos.add("11111111111");
		cpfsInvalidos.add("22222222222");
		cpfsInvalidos.add("33333333333");
		cpfsInvalidos.add("44444444444");
		cpfsInvalidos.add("55555555555");
		cpfsInvalidos.add("66666666666");
		cpfsInvalidos.add("77777777777");
		cpfsInvalidos.add("88888888888");
		cpfsInvalidos.add("99999999999");
		cpfsInvalidos.add("00011122233");
		cpfsInvalidos.add("11122233344");
	}

	public static Boolean ehCpfValido(String cpf) {
		if(cpf == null || cpf.trim().length() == 0) {
			return false;
		} else if(cpfsInvalidos.contains(cpf)) {
			return false;
		} else if( ! Cpf.isValid(cpf)) {
			return false;
		}

		return true;
	}

	private static List<String> cnpjsInvalidos;

	static {
		cnpjsInvalidos = new ArrayList<String>();
		cnpjsInvalidos.add("00000000000000");
		cnpjsInvalidos.add("11111111111111");
		cnpjsInvalidos.add("22222222222222");
		cnpjsInvalidos.add("33333333333333");
		cnpjsInvalidos.add("44444444444444");
		cnpjsInvalidos.add("55555555555555");
		cnpjsInvalidos.add("66666666666666");
		cnpjsInvalidos.add("77777777777777");
		cnpjsInvalidos.add("88888888888888");
		cnpjsInvalidos.add("99999999999999");
	}

	public static Boolean ehCnpjValido(String cnpj) {
		if(cnpj == null || cnpj.trim().length() == 0) {
			return false;
		} else if(cnpjsInvalidos.contains(cnpj)) {
			return false;
		} else if( ! Cnpj.isValid(cnpj)) {
			return false;
		}

		return true;
	}

	private static DateTimeZone DATE_TIME_ZONE_USERS;

	public static DateTimeZone getDateTimeZoneUsers() {
		if( DATE_TIME_ZONE_USERS == null ) {
			//String timezone = ConstantesProp.TIMEZONE_USERS;
			String timezone = "America/Sao_Paulo";
			DATE_TIME_ZONE_USERS = DateTimeZone.forID(timezone);
		}
		return DATE_TIME_ZONE_USERS;
	}

	public static DateTime converterUTCToLocal(LocalDateTime localDateTimeEmUTC) {

		DateTime auxUTC = localDateTimeEmUTC.toDateTime(DateTimeZone.UTC);

		DateTime auxLocal = auxUTC.withZone(getDateTimeZoneUsers());

		return auxLocal;
	}

	public static String formatarUTCToLocal(LocalDateTime localDateTimeEmUTC, String pattern) {

		DateTime auxUTC = localDateTimeEmUTC.toDateTime(DateTimeZone.UTC);

		DateTime auxLocal = auxUTC.withZone(getDateTimeZoneUsers());

		return auxLocal.toString(pattern);
	}

	public static int diferencaEmDias(Date dataInicial, Date dataFinal) {
		if(dataInicial.after(dataFinal)) {
			return -1;
		}

		Calendar c1 = Calendar.getInstance();
		c1.setTime(dataInicial);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(dataFinal);

		int totalDeDias = 0;

		while(c1.before(c2)) {
			c1.add(Calendar.DAY_OF_MONTH, 1);
			totalDeDias++;
		}

		return totalDeDias;
	}

	public static String diferencaEntreDatasPorExtenso(Date dataInicial, Date dataFinal) {

		if(dataInicial.after(dataFinal)) {
			return "dataFinal menor que dataInicial";
		}

		int totalDeDias = diferencaEmDias(dataInicial, dataFinal);

		int anos = totalDeDias/365;
		int restoAnos = totalDeDias%365;

		int meses = restoAnos/30;
		int dias = restoAnos%30;

		String r = "";

		if(anos != 0) {
			r += anos+" ano(s) ";
		}

		if(meses != 0) {
			r += meses+" mes(es) ";
		}

		if(dias != 0) {
			r += dias+" dia(s) ";
		}

		return r;
	}

	public static Object valorComAspasOuNulo(Object obj) {
		return valorComAspasOuNulo(obj, true);
	}

	public static Object valorComAspasOuNulo(Object obj, Boolean stringUpperCase) {
		if(obj == null) {
			return null;
		}

		if(obj instanceof String) {
			String string = (String) obj;
			return "'" + (stringUpperCase ? string.toUpperCase() : string) + "'";
		} else if(obj instanceof Number) {
			return obj;
		} else if(obj instanceof Date) {
			String data = formatarDataHora((Date)obj, "yyyy-MM-dd HH:mm:ss");
			return "'"+data+"'";
		} else if(obj instanceof Boolean) {
			Boolean bool = (Boolean) obj;
			return (bool == null || ! bool) ? "0" : "1";
		} else {
			logger.info(obj.getClass()+" tipo nao tratado. ");
			return "'"+obj+"'";
		}
	}

	public static Boolean valorAlterado(Object valor, Object valorAntes) {
		Boolean atualizado = false;

		if(valorAntes == null && valor != null) {
			atualizado = true;
		} else if(valorAntes != null && valor == null) {
			atualizado = true;
		} else if(valorAntes != null && valor != null) {
			atualizado = ! valorAntes.equals(valor);
		}

		return atualizado;
	}

	public static Boolean dataVencimentoMenorOuIgual(Date dataVencimento) {
		Date hoje = new Date();
		if(hoje.before(dataVencimento)) {
			return true;
		} else {
			String hojeS = formatarDataHora(hoje, "yyyyMMdd");
			String dataVenc = formatarDataHora(dataVencimento, "yyyyMMdd");

			if(hojeS.equals(dataVenc)) {
				return true;
			}
		}

		return false;
	}

	public static long daysBetween(Calendar startDate, Calendar endDate) {
		Calendar date = (Calendar) startDate.clone();

		long daysBetween = 0;

		String d1 = formatarDataHora(date.getTime(), "dd/MM/yyyy");
		String d2 = formatarDataHora(endDate.getTime(), "dd/MM/yyyy");

		if( ! d1.equals(d2)) {
			while (date.before(endDate)) {
				date.add(Calendar.DAY_OF_MONTH, 1);
				daysBetween++;
			}
		}

		return daysBetween;
	}

	public static long monthsBetween(Date startDate, Date endDate) {
		Calendar d1 = Calendar.getInstance();
		d1.setTime(startDate);

		Calendar d2 = Calendar.getInstance();
		d2.setTime(endDate);

		return monthsBetween(d1, d2);
	}

	public static long monthsBetween(Calendar startDate, Calendar endDate) {
		Calendar date = (Calendar) startDate.clone();
		long monthsBetween = 0;
		while (date.before(endDate)) {
			date.add(Calendar.MONTH, 1);
			monthsBetween++;
		}
		return monthsBetween;
	}

	public static String htmlAlteracaoDadosNseq124() {
		StringBuilder sb = new StringBuilder();

		try {
			FileInputStream fis = new FileInputStream(new File(caminhoFisico+"/oab/alteracao-dados-nseq-124.html"));
			InputStreamReader inReader=  new InputStreamReader(fis);

			BufferedReader read = new BufferedReader(inReader);
			String linha = null;

			while ((linha = read.readLine()) != null) {
				sb.append(linha + "\n");
			}
		} catch (Exception e) {
			logger.error("", e);
		}

		return sb.toString();
	}

	public static Boolean ehTamanhoStringExcedida(String s, int tamanho) {
		return s != null && s.length() > tamanho;
	}

	public static String zerosEsquerda(String s, int i) {
		if(s.length() >= i) {
			return s;
		} else {
			while(s.length() < i) {
				s = "0"+s;
			}
			return s;
		}
	}

	public static String retirarAspas(String s) {
		if(s == null || s.length() == 0) {
			return s;
		}

		if(s.indexOf("'") != -1) {
			s = s.replaceAll("'", "");
		}

		if(s.indexOf("\'") != -1) {
			s = s.replaceAll("\'", "");
		}

		if(s.indexOf("\"") != -1) {
			s = s.replaceAll("\"", "");
		}

		return s;
	}

	//	public static void addRow(HSSFSheet sheet, List<String> colunas, int rownumber) {
	//
	//		HSSFRow row = sheet.createRow(rownumber);
	//
	//		for (int i = 0; i < colunas.size(); i++) {
	//			HSSFCell cell = row.createCell(i);
	//
	//			cell.setCellValue(colunas.get(i));
	//		}
	//	}

	public static void excluirElementosNuloEVazios(List<String> localidades) {
		for (Iterator iterator = localidades.iterator(); iterator.hasNext();) {
			String e = (String) iterator.next();

			if( ehNuloOuVazio(e) ) {
				iterator.remove();
			}
		}
	}

	public static String getIP(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");

		if( ehNuloOuVazio(ip) ) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	public static String getUserAgent(HttpServletRequest request) {

		return request.getHeader("User-Agent");
	}

	public static Boolean dataDentroDoIntervalo(Date data, Date dataInicio, Date dataFim) {

		dataInicio = Uteis.inicioDia(dataInicio);

		if( data.before(dataInicio) ) {
			return false;
		}

		dataFim = Uteis.fimDia(dataFim);

		if( data.after(dataFim) ) {
			return false;
		}

		return true;
	}

	public static List<Long> converterVetorStringParaListaLong(String[] vetor) {
		List<Long> ids = new ArrayList<Long>();

		if( vetor == null ) {
			return ids;
		}

		for (String s : vetor) {
			Long id = Uteis.converterLong(s);

			if( id == null ) {
				continue;
			}

			ids.add( id );
		}

		return ids;
	}

	public static String extractStackTrace(Throwable throwable) {
		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter( writer );
		throwable.printStackTrace( printWriter );
		printWriter.flush();

		return writer.toString();
	}

	public static boolean set(Object object, String fieldName, Object fieldValue) {
		Class<?> clazz = object.getClass();
		while (clazz != null) {
			try {
				Field field = clazz.getDeclaredField(fieldName);
				field.setAccessible(true);
				field.set(object, fieldValue);
				return true;
			} catch (NoSuchFieldException e) {
				clazz = clazz.getSuperclass();
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		return false;
	}
	
	public static String join(List<String> facilidades, String separator) {
		
		if( facilidades == null || facilidades.isEmpty() ) {
			return "";
		}
		
		String r = "";
		
		for (String s : facilidades) {
			r += s+separator;
		}
		
		return r;
	}
	
	public static XMLGregorianCalendar getXMLGregorianCalendar(Date data) {
		XMLGregorianCalendar xmlDate = null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);

		try {
			xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlDate;
	}
	
	public static String calcularDuracao(Date dataSaida, Date dataChegada) {
		
		Instant start = dataSaida.toInstant();
		Instant end = dataChegada.toInstant();
		
		Duration duration = Duration.between(start, end);
		
		String retorno = "";
		
		long days = duration.toDays();
		duration = duration.minusDays(days);
		long hours = duration.toHours();
		duration = duration.minusHours(hours);
		long minutes = duration.toMinutes();
				
		if( days > 0 ) {
			retorno += days+"d ";
		}
		
		if( hours == 0 && minutes == 0 ) {
			return retorno.trim();
		}
		
		if( hours >= 0 ) {
			retorno += hours+"h ";
		}
		
		if( minutes > 0 ) {
			retorno += minutes+"m ";
		}
		
		return retorno.trim();
	}
	
	public static Float convert2MaximumFractionDigits(Float n) {
		if( n == null ) {
			return null;
		}
		
		try {
			Float __n = new Float(df2MaximumFractionDigits.format(n));
			return __n;			
		} catch (Exception e) {
			return n;
		}
		
	}
	
	public static Double convert2MaximumFractionDigits(Double n) {
		if( n == null ) {
			return null;
		}
		
		try {
			Double __n = new Double(df2MaximumFractionDigits.format(n));			
			return __n;
		} catch (Exception e) {
			return n;
		}
		
	}
	
	public static Double converterDoubleDoisDecimais(Double valor) {
		
		if( valor == null ) {
			return null;
		}
		
	    DecimalFormat fmt = new DecimalFormat("0.00");      
	    String string = fmt.format(valor);
	    return Double.parseDouble(string);
	}
	
	public static String formatarDataSemana(Date data, String pattern) {
		if (data == null) {
			return "";
		}
		String etaLele = "";
		switch (data.getDay()) {
        case 0:
        	etaLele = "DOM ";
            break;
        case 1:
        	etaLele = "SEG ";
            break;
        case 2:
        	etaLele = "TER ";
        break;
        case 3:
        	etaLele = "QUA ";
            break;
        case 4:
        	etaLele = "QUI ";
            break;
        case 5:
        	etaLele = "SEX ";
            break;
        case 6:
        	etaLele = "SAB ";

    }
		simpleDateFormat.applyPattern(pattern);
		String convert = simpleDateFormat.format(data);
		return etaLele.concat(convert);
	}
	
	public static Date converterHoraEmDate(String Hora , String pattern) {
		try {
		    DateFormat sdf = new SimpleDateFormat(pattern);
			Date date = sdf.parse(Hora);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String calculodeHoraSemIntervalo(String tempo, Integer nrAulas, Integer duracao) {
		Double resultado;
		resultado = (double) duracao * nrAulas;
		int res1 = (int) (resultado / 60);
		double res2 = ((resultado % 60));

		String hora = tempo.substring(0, 2);
		String minuto = tempo.substring(3, 5);
		Integer hora1 = Integer.parseInt(hora);
		Integer minuto1 = Integer.parseInt(minuto);

		hora1 = hora1 + res1;
		minuto1 = (int) (minuto1 + res2);

		if (hora1 > 23) {
			hora1 = hora1 - 23;
			hora1 = hora1 - 1;
		}
		if (minuto1 > 59) {
			minuto1 = minuto1 - 60;
			hora1 = hora1 + 1;
			minuto1 = minuto1 + 0;
		}

		if (minuto1 >= 0 && minuto1 <= 9) {
			minuto = "0" + String.valueOf(minuto1);
		} else {
			minuto = String.valueOf(minuto1);
		}

		if (hora1 >= 0 && hora1 <= 9) {
			hora = "0" + String.valueOf(hora1);
		} else {
			hora = String.valueOf(hora1);
		}

		tempo = hora + ":" + minuto;
		return tempo;

	}
	
	public static Date converterDataHoraString(String data, String horaData) throws ParseException {
	
		SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

		Date date = sdf.parse(data);
	    
		String hora = horaData.substring(0, 2);
		String minuto = horaData.substring(3, 5);
		Integer hora1 = Integer.parseInt(hora);
		Integer minuto1 = Integer.parseInt(minuto);
		
		date.setHours(hora1);
		date.setMinutes(minuto1);
	  
	  return date;
	
	}
	
	public static Date highDateTime(Date date) {
		Calendar aux = Calendar.getInstance();
		aux.setTime(date);
		toOnlyDate(aux); //zera os parametros de hour,min,sec,milisec
		aux.roll(Calendar.DATE, true); //vai para o dia seguinte
		aux.roll(Calendar.MILLISECOND, false); //reduz 1 milisegundo
		return aux.getTime();
	}
	
	public static void toOnlyDate(Calendar date) {
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		}
	
	public static String tratamentoMensagemErro(Exception exeption) {
			
			if (exeption.getMessage() == null) {
				return "Aconteceu um problema inesperado, informe ao administrador e o mesmo será solucionado.";
			}
			
	        if (exeption.getCause() != null && exeption.getCause().getCause().toString().contains("table")) {
	           String table = "";
	           table = exeption.getCause().getCause().toString().substring(exeption.getCause().getCause().toString().lastIndexOf("table")+6, exeption.getCause().getCause().toString().length());
			   table = table.substring(table.indexOf("\"")+1, table.length());
			   table = table.substring(0, table.indexOf("\""));
			   return "Valor Referenciado com a Tabela "+  table;
			}
		
		return exeption.getMessage();
	}
	
	public static String formatDataLocateBrasil(Date data, String pattern) {
		if (data != null) {
			SimpleDateFormat formatador = new SimpleDateFormat(pattern, new Locale("pt", "BR"));
			String teste = formatador.format(data);
			return teste;
		}
		return "";
	}
	
	public static Date getData(String data, String pattern) throws ParseException {
		try {
			if (data != null) {
				SimpleDateFormat formatador = new SimpleDateFormat(pattern);
				return formatador.parse(data);
			}
		} catch (Exception e) {
		}
		return null;
	}
	
	public static String getData(Date data, String pattern) {
		if (data != null) {
			SimpleDateFormat formatador = new SimpleDateFormat(pattern, new Locale("pt", "BR"));
			return formatador.format(data);
		}
		return "";
	}
	
	public static String getHoraAtual() {
		return getData(new Date(), "HH:mm");
	}
	
	public static Boolean realizarValidacaoHora1MaiorHora2(String hora1, String hora2) {
		Integer h1 = Integer.valueOf(hora1.substring(0, 2));
		Integer h2 = Integer.valueOf(hora2.substring(0, 2));
		Integer m1 = Integer.valueOf(hora1.substring(3, 5));
		Integer m2 = Integer.valueOf(hora2.substring(3, 5));
		if (h1 >= h2 && m1 > m2) {
			return true;
		} else if (h1.equals(h2)) {
			h1 = Integer.valueOf(hora1.substring(3, 5));
			h2 = Integer.valueOf(hora2.substring(3, 5));
			if (h1 > h2) {
				return true;
			}
		}
		return false;
	}
	
	public static Boolean isHojeIndependenteDaHora(Date data) {
        Date hoje = new Date();
        Boolean dataAtual = ((inicioDia(hoje).before(data) || inicioDia(hoje).equals(data)) && (fimDia(hoje).after(data) || fimDia(hoje).equals(data)));
        return dataAtual;
    }
	
	public static Boolean validarDataInicialMaiorFinalComHora(String horaValidar , Date data1, Date data2){
		try {
	    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    	String dataAtual = getData(data1,"dd/MM/yyyy ").concat(horaValidar);
	    	String b = format.format(data2);
			Date data1Formt = format.parse(dataAtual);
			Date data2Formt = format.parse(b);
    	if (data1Formt.compareTo(data2Formt) >= 0) {
    		return Boolean.TRUE;
    	}
    	return Boolean.FALSE;
		} catch (ParseException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
    	
    }
	
}