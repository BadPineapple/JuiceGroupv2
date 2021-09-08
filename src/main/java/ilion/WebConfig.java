package ilion;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ilion.admin.negocio.PropEnum;
import ilion.admin.negocio.PropNegocio;
import ilion.util.contexto.autorizacao.AuthInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private PropNegocio propNegocio;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry r) {
		String arquivosAbsoluto = propNegocio.findValueById(PropEnum.STATIC_PATH_ABSOLUTO);
		
		String myExternalFilePath = "file:///"+arquivosAbsoluto;
		
		r.
		addResourceHandler("/static/**")
		.addResourceLocations(myExternalFilePath)
		.setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS).noTransform().cachePublic())
		.setCachePeriod(60*60*24*7);//em segundos
		
		super.addResourceHandlers(r);
	}

	@Bean
	public AuthInterceptor getAuthInterceptor(){
		return new AuthInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getAuthInterceptor()).addPathPatterns("/**");
	}
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	 
	    return new EmbeddedServletContainerCustomizer() {
	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
	            container.addErrorPages(error404Page);
	        }
	    };
	}
}
