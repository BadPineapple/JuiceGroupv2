package ilion.arquivo.taglibs.html;

import ilion.arquivo.negocio.Arquivo;

public abstract class AbstractArquivoHtml {
	
	
	protected AbstractArquivoHtml proximo;
	
	public void executar(Arquivo arquivo, Appendable out) {
		if( ehValido(arquivo) ) {
			escrever(arquivo, out);
		} else {
			if( proximo != null ) {
				proximo.executar(arquivo, out);
			}
		}
	}
	
	protected abstract Boolean ehValido(Arquivo arquivo);
	protected abstract void escrever(Arquivo arquivo, Appendable out);
}