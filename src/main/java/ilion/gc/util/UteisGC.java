package ilion.gc.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import ilion.SpringApplicationContext;
import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.ArtigoConteudo;
import ilion.gc.negocio.Enquete;
import ilion.gc.negocio.SubCategoriaArtigo;
import ilion.util.Uteis;

@Service
public class UteisGC {

	static Logger logger = Logger.getLogger(UteisGC.class);

	public static final String GRUPOS_ATRIBUTO_SESSAO = "gruposSessao";

	public enum CONTEUDOINFO_NOS {
		NOME_CLASS,
		ID_OBJETO,
		RESUMO,
		TEXTO,
		ARQ_CODARQUIVO,
		ARQ_QTD,
		ARQ_TOPO,
		ARQ_LATERAL,
		ARQ_INFERIOR,
		ARQ_LATERAL_ALINHAMENTO;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	public synchronized static String conteudoInfo(Object objeto, Class clazz) {
		String nomeClasse = clazz.getSimpleName();
		Serializable idObjeto = (Serializable) Uteis.getRetornoMetodoReflection(objeto, "getId");
		String codControle = (String) Uteis.getRetornoMetodoReflection(objeto, "getCodControle");

		StringBuilder sb = new StringBuilder();
		sb.append("<").append(CONTEUDOINFO_NOS.NOME_CLASS).append(">").append(nomeClasse).append("</").append(CONTEUDOINFO_NOS.NOME_CLASS).append("> ");
		sb.append("<").append(CONTEUDOINFO_NOS.ID_OBJETO).append(">").append(idObjeto).append("</").append(CONTEUDOINFO_NOS.ID_OBJETO).append("> ");
		sb.append("<").append(CONTEUDOINFO_NOS.ARQ_CODARQUIVO).append(">").append(codControle).append("</").append(CONTEUDOINFO_NOS.ARQ_CODARQUIVO).append("> ");

		//		if(objeto instanceof Artigo) {
		//			Artigo artigo = (Artigo) objeto;
		//			String codControle = (String) Uteis.getRetornoMetodoReflection(objeto, "getCodControle");
		//			sb.append(artigoConteudoInfo(artigo, codControle));
		//		}

		if(objeto instanceof Artigo ||
				objeto instanceof ArtigoConteudo ||
				objeto instanceof SubCategoriaArtigo ||
				objeto instanceof Enquete) {
			String alinhamentoArquivoLateral = (String) Uteis.getRetornoMetodoReflection(objeto, "getAlinhamentoArquivoLateral");
			sb.append(arquivoInfo(nomeClasse, idObjeto, codControle, alinhamentoArquivoLateral));
		}

		return sb.toString();
	}

	//	public static String conteudoInfoVazio(Object object) {
	//		StringBuilder sb = new StringBuilder();
	//		sb.append("<").append(CONTEUDOINFO_NOS.NOME_CLASS).append(">").append(nomeClasse).append("</").append(CONTEUDOINFO_NOS.NOME_CLASS).append("> ");
	//		sb.append("<").append(CONTEUDOINFO_NOS.ID_OBJETO).append(">").append(idObjeto).append("</").append(CONTEUDOINFO_NOS.ID_OBJETO).append("> ");
	//		sb.append("<").append(CONTEUDOINFO_NOS.ARQ_CODARQUIVO).append(">").append(codArquivo).append("</").append(CONTEUDOINFO_NOS.ARQ_CODARQUIVO).append("> ");
	//
	//		return sb.toString();
	//	}

	public static String artigoConteudoInfo(List<ArtigoConteudo> artigoConteudos) {
		Boolean possuiResumo = false;
		Boolean possuiTexto = false;

		for (ArtigoConteudo artigoConteudo : artigoConteudos) {
			if(CONTEUDOINFO_NOS.RESUMO.toString().toLowerCase().equals(artigoConteudo.getLayout())) {
				possuiResumo = true;
			} else if(CONTEUDOINFO_NOS.TEXTO.toString().toLowerCase().equals(artigoConteudo.getLayout())) {
				possuiTexto = true;
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<").append(CONTEUDOINFO_NOS.RESUMO).append(">").append(possuiResumo).append("</").append(CONTEUDOINFO_NOS.RESUMO).append("> ");
		sb.append("<").append(CONTEUDOINFO_NOS.TEXTO).append(">").append(possuiTexto).append("</").append(CONTEUDOINFO_NOS.TEXTO).append("> ");

		return sb.toString();
	}


	@SuppressWarnings("unchecked")
	private static String arquivoInfo(String nomeClasse, Serializable idObjeto, String codArquivo, String alinhamentoArquivoLateral) {

		ArquivoNegocio arquivoFacade = SpringApplicationContext.getBean(ArquivoNegocio.class);

		Collection<Arquivo> arquivos = arquivoFacade.listarArquivos(nomeClasse, idObjeto.toString(), codArquivo);

		Integer qtd = arquivos.size();
		Boolean possuiTopo = false;
		Boolean possuiLaterais = false;
		Boolean possuiInferiores = false;

		for (Iterator iter = arquivos.iterator(); iter.hasNext();) {
			Arquivo arquivo = (Arquivo) iter.next();

			if("topo".equals(arquivo.getLayout()))
				possuiTopo = true;

			if("lateral".equals(arquivo.getLayout()))
				possuiLaterais = true;

			if("inferior".equals(arquivo.getLayout()))
				possuiInferiores = true;
		}

		return formarArquivoInfo(qtd,
				possuiTopo,
				possuiLaterais,
				possuiInferiores,
				alinhamentoArquivoLateral);
	}

	public static String formarArquivoInfo(Integer qtd,
			Boolean possuiTopo,
			Boolean possuiLaterais,
			Boolean possuiInferiores,
			String alinhamentoArquivoLateral) {
		StringBuilder sb = new StringBuilder();

		sb.append("<").append(CONTEUDOINFO_NOS.ARQ_QTD).append(">").append(qtd.intValue()).append("</").append(CONTEUDOINFO_NOS.ARQ_QTD).append("> ");
		sb.append("<").append(CONTEUDOINFO_NOS.ARQ_TOPO).append(">").append(possuiTopo.toString()).append("</").append(CONTEUDOINFO_NOS.ARQ_TOPO).append("> ");
		sb.append("<").append(CONTEUDOINFO_NOS.ARQ_LATERAL).append(">").append(possuiLaterais.toString()).append("</").append(CONTEUDOINFO_NOS.ARQ_LATERAL).append("> ");
		sb.append("<").append(CONTEUDOINFO_NOS.ARQ_INFERIOR).append(">").append(possuiInferiores.toString()).append("</").append(CONTEUDOINFO_NOS.ARQ_INFERIOR).append("> ");
		sb.append("<").append(CONTEUDOINFO_NOS.ARQ_LATERAL_ALINHAMENTO).append(">").append(alinhamentoArquivoLateral).append("</").append(CONTEUDOINFO_NOS.ARQ_LATERAL_ALINHAMENTO).append("> ");
		//sb.append("<").append(NO_ALINHAMENTO_DESTAQUE).append(">").append(alinhamentoArquivoDestaque).append("</").append(NO_ALINHAMENTO_DESTAQUE).append("> ");

		return sb.toString();
	}


}