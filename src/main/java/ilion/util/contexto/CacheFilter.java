package ilion.util.contexto;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import ilion.util.Uteis;

public class CacheFilter implements Filter {
	
	static Logger logger = Logger.getLogger(CacheFilter.class);
	
	ServletContext sc;
	FilterConfig fc;
	long cacheTimeout = Long.MAX_VALUE;
	static String pathCache;
	
	public CacheFilter() {
		super();
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain)
	throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

//		if(FilterUteis.requisicaoEncaminhada(request, response, chain)) {
//			return;
//		}

		if("POST".equalsIgnoreCase(request.getMethod())) {
			chain.doFilter(request, response);
			return;
		}

		String uri = request.getRequestURI();

		if(uri.endsWith(".do") ||
				uri.endsWith(".sp") ||
				uri.contains("area-restrita") ||
				//uri.contains("rodadas-preteritas") ||
				//uri.contains("videoaulas") ||
				uri.contains("identificacao") ||
				uri.contains("pagamento") ||
				uri.contains("finalizacao") ||
				uri.contains("ilionnet") ||
				uri.contains("/api/")) {
			chain.doFilter(request, response);
			return;
		}
		
		if("true".equalsIgnoreCase(request.getParameter("nc"))) {
			chain.doFilter(request, response);
			return;
		}
		
		File tempDir = new File(pathCache);
		if(!tempDir.exists()) {
			chain.doFilter(request, response);
			return;
		}
		
		// check if was a resource that shouldn't be cached.
		//String path = sc.getRealPath("");
		
		String queryString = request.getQueryString();
		if(queryString == null || "null".equals(queryString)) {
			queryString = "";
		}
		
		// customize to match parameters
		String id = request.getRequestURI()+queryString;
		while(id.endsWith("/")) {
			id = id.substring(0, id.length() - 1);
		}
		if(id.indexOf("/") != -1) {
			//id = id.substring(id.lastIndexOf("/"));
			id = id.replaceAll("/", "__");
		}

		// optionally append i18n sensitivity
		String localeSensitive = fc.getInitParameter("locale-sensitive");
		if (localeSensitive != null) {
			StringWriter ldata = new StringWriter();
			Enumeration locales = request.getLocales();
			while (locales.hasMoreElements()) {
				Locale locale = (Locale)locales.nextElement();
				ldata.write(locale.getISO3Language());
			}
			id = id + ldata.toString();
		}

		// get possible cache
		String temp = tempDir.getAbsolutePath();
		temp += "/";
		File file = new File(temp+id);
		
		if(file.exists() && file.isDirectory()) {
			chain.doFilter(request, response);
			return;
		}

		//set timestamp check
		if(!file.exists()) {
			String name = file.getAbsolutePath();
			name = name.substring(0,name.lastIndexOf("/")==-1?0:name.lastIndexOf("/"));
			new File(name).mkdirs();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			CacheResponseWrapper wrappedResponse =
				new CacheResponseWrapper(response, baos);

			chain.doFilter(req, wrappedResponse);

			if(baos != null && baos.size() != 0) {
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(baos.toByteArray());
				fos.flush();
				fos.close();
			}
		}

		if(file.exists() && file.length() != 0) {
			//String mt = sc.getMimeType(request.getRequestURI());
			response.setContentType("text/html;charset=iso-8859-1");

			escreverArquivo(file, response);
		}
	}

	//static byte[] lineSeparator = System.getProperty("line.separator").getBytes();
	static String lineSeparator = System.getProperty("line.separator");

	private void escreverArquivo(File arquivoEmCache, HttpServletResponse response) {
		BufferedReader input = null;
		try {
			Reader reader = new InputStreamReader(new FileInputStream(arquivoEmCache), "ISO-8859-1");
			input = new BufferedReader( reader );
			String line = null;

			while (( line = input.readLine()) != null){
				response.getWriter().print(line);
				response.getWriter().print(lineSeparator);
			}
		} catch (IOException ex){
			logger.error("escrevendo arquivo em cache no response", ex);
		} finally {
			try {
				if (input!= null) {
					input.close();
				}
			} catch (IOException ex) {
				logger.error("fechando o buffer do arquivo em cache", ex);
			}
		}
	}

	public void init(FilterConfig filterConfig) {
		this.fc = filterConfig;
		this.sc = filterConfig.getServletContext();
	}

	public void destroy() {
		this.sc = null;
		this.fc = null;
	}

	protected static void verificarPastaCache(ServletContext servletContext) {
		pathCache = servletContext.getRealPath("/") + "arquivos/filtercache/";
		
		File f = new File(pathCache);
		if(!f.exists()) {
			try {
				f.mkdirs();
			} catch (Exception e) {
				logger.error("nao foi possivel criar a pasta filtercache para o cache", e);
			}
		}
		limparCache();
	}

	public static void limparCache() {
//		ArtigoSiteNegocio artigoSiteNegocio = SpringApplicationContext.getBean(ArtigoSiteNegocio.class);
//		artigoSiteNegocio.limparCache();
		
//		if(pathCache != null && pathCache.length() != 0) {
//			File f = new File(pathCache);
//			if(f.exists())
//				excluirArquivosRecursivamente(f);
//		}
	}

	private synchronized static void excluirArquivosRecursivamente(File f) {
		File[] arquivos = f.listFiles();
		if(arquivos != null) {
			for (int i = 0; i < arquivos.length; i++) {
				File file = arquivos[i];
				if(file.isDirectory())
					excluirArquivosRecursivamente(file);

				file.delete();
			}
		}
	}
	
	public static void limparCache(String pagina) {
		
		Assert.hasText(pagina, "pagina deve ser preenchida.");
		
		if( ! Uteis.ehNuloOuVazio(pathCache) ) {
			
			File f = new File(pathCache);
			if( ! f.exists() ) {
				return;
			}
			
			File[] arquivos = f.listFiles();
			if( arquivos == null ) {
				return;
			}
			
			for (File arquivo : arquivos) {
				
				if( arquivo.getPath().contains(pagina) ) {
					arquivo.delete();
				}
				
			}
			
		}
		
	}
}