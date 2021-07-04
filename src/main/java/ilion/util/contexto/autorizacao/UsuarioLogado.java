package ilion.util.contexto.autorizacao;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface UsuarioLogado {
	
	PermissaoNegadaAcaoEnum acao() default PermissaoNegadaAcaoEnum.REDIRECT;
	String[] value() default {};
	
}