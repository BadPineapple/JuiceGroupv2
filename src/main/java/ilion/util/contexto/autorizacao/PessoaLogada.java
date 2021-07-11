package ilion.util.contexto.autorizacao;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PessoaLogada {
	PermissaoNegadaAcaoEnum acao() default PermissaoNegadaAcaoEnum.REDIRECT;
	String redirectTo() default "/entrar";
	String[] value() default {};
}
