package ilion.arquivo.negocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ilion.admin.negocio.PropNegocio;
import ilion.arquivo.negocio.upload.ArquivoTipoEnum;
import ilion.util.CacheUtil;
import ilion.util.Uteis;
import ilion.util.contexto.CacheFilter;
import ilion.util.exceptions.ValidacaoException;
import ilion.util.persistencia.HibernateUtil;

@Service
public class ArquivoNegocio {

	private static Logger logger = Logger.getLogger(ArquivoNegocio.class);

	public enum Layout {
		TOPO, // deprecated
		CENTRO, LATERAL, LATERAL_ESQUERDA, INFERIOR, DESTAQUE, GALERIA, PREVIEW;

		@Override
		public String toString() {
			return name().toLowerCase();
		}

		public static Layout fromString(String layout) {
			for (Layout l : values()) {
				if (l.toString().equalsIgnoreCase(layout)) {
					return l;
				}
			}

			return null;
		}

	}

	@Autowired
	private HibernateUtil hibernateUtil;

	@Autowired
	private ArquivoUteis arquivoUteis;

	@Autowired
	private Redimencionamento redimencionamento;

	@Autowired
	private ClienteImagemService clienteImagemService;

	@Autowired
	private PropNegocio propNegocio;

	public Object consultar(Class clazz, Serializable id) {
		return hibernateUtil.findById(clazz, id);
	}

	@Transactional
	public Arquivo inserir(Arquivo arquivo) {
		return (Arquivo) hibernateUtil.save(arquivo);
	}

	@Transactional
	public void atualizar(Arquivo arquivo) {
		hibernateUtil.update(arquivo);
	}

	@Transactional
	@CacheEvict(value = { "imagens.site", "arquivos.publicados", "arquivos.first.result.url",
			"arquivos.first.result.url.lista", "arquivos.image.urls" }, allEntries = true)
	public void atualizarDetalhes(Arquivo arquivo) throws Exception {

		Arquivo a = (Arquivo) consultar(Arquivo.class, arquivo.getId());

		a.setTexto(arquivo.getTexto());
		a.setTitle(arquivo.getTitle());
		a.setCreditos(arquivo.getCreditos());
		a.setData(arquivo.getData());
		a.setLink(arquivo.getLink());
		a.setTipoLink(arquivo.getTipoLink());
		a.setNaoPublicado(arquivo.getNaoPublicado());
		a.setLayout(arquivo.getLayout());

		hibernateUtil.update(a);
		
		CacheUtil.getInstance().resetAllCaches();
		
	}

	@Transactional
	@CacheEvict(value = { "imagens.site", "arquivos.publicados", "arquivos.first.result.url",
			"arquivos.first.result.url.lista", "arquivos.image.urls" }, allEntries = true)
	public void excluir(Arquivo arquivo) throws Exception {
		excluir(arquivo, true);
	}

	@Transactional
	@CacheEvict(value = { "imagens.site", "arquivos.publicados", "arquivos.first.result.url",
			"arquivos.first.result.url.lista", "arquivos.image.urls" }, allEntries = true)
	public void excluir(Arquivo arquivo, Boolean necessitaConsultarArquivo) {
		if (necessitaConsultarArquivo)
			arquivo = (Arquivo) hibernateUtil.findById(Arquivo.class, arquivo.getId());

		String path = arquivoUteis.getPathArquivos();

		try {
			arquivoUteis.apagarArquivo(arquivo.getArquivo1(), path);

			if (Arquivo.IMAGEM.equals(arquivo.getTipo())
					&& (arquivo.getArquivo2() != null && !arquivo.getArquivo2().equals("icon_img_download.gif"))) {
				arquivoUteis.apagarArquivo(arquivo.getArquivo2(), path);
			}
		} catch (Exception e) {
			String m = "Erro ao excluir arquivo: " + arquivo.getArquivo1() + "/" + arquivo.getArquivo2();
			logger.error(m, e);
		}

		hibernateUtil.delete(arquivo);

		CacheUtil.getInstance().resetAllCaches();
	}

	public Integer maxPosicao(String nomeClasse, String idObjeto, String codigo) {
		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		if (!Uteis.ehNuloOuVazio(nomeClasse) && idObjeto != null) {
			dc.add(Restrictions.eq("nomeClasse", nomeClasse));
			dc.add(Restrictions.eq("idObjeto", idObjeto));
		} else {
			dc.add(Restrictions.eq("codigo", idObjeto));
		}

		dc.setProjection(Projections.max("posicao"));

		return (Integer) hibernateUtil.uniqueResult(dc);
	}

	public List listarArquivos(String nomeClasse, String idObjeto, String codigo) {
		if (Uteis.ehNuloOuVazio(nomeClasse) && Uteis.ehNuloOuVazio(idObjeto) && Uteis.ehNuloOuVazio(codigo)) {
			return null;
		}

		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		if (!Uteis.ehNuloOuVazio(nomeClasse) && !Uteis.ehNuloOuVazio(idObjeto)) {
			dc.add(Restrictions.eq("nomeClasse", nomeClasse));
			dc.add(Restrictions.eq("idObjeto", idObjeto));
		} else {
			dc.add(Restrictions.eq("codigo", codigo));
		}

		dc.addOrder(Order.asc("posicao"));

		return hibernateUtil.list(dc);
	}

	public void vincularArquivos(Class clazz, Serializable idObjeto, String codigo) {
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE ").append(Arquivo.class.getName());
		sb.append(" SET ");
		sb.append(" nomeClasse = '").append(clazz.getSimpleName()).append("', ");
		sb.append(" idObjeto = '").append(idObjeto).append("' ");
		sb.append(" WHERE codigo = '").append(codigo).append("' ");

		hibernateUtil.updateHQL(sb.toString());
	}

	@Cacheable("arquivos.publicados")
	public List listarArquivosPublicados(String nomeClasse, String idObjeto, String codigo) {
		if (Uteis.ehNuloOuVazio(nomeClasse) && Uteis.ehNuloOuVazio(idObjeto) && Uteis.ehNuloOuVazio(codigo)) {
			return null;
		}

		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		if (!Uteis.ehNuloOuVazio(nomeClasse) && !Uteis.ehNuloOuVazio(idObjeto)) {
			dc.add(Restrictions.eq("nomeClasse", nomeClasse));
			dc.add(Restrictions.eq("idObjeto", idObjeto));
		} else {
			dc.add(Restrictions.eq("codigo", codigo));
		}

		dc.add(Restrictions.eq("naoPublicado", Boolean.FALSE));

		dc.addOrder(Order.asc("posicao"));

		return hibernateUtil.list(dc);
	}

	public List<Arquivo> listarArquivosPublicados(String nomeClasse, List<String> idsObjetos) {

		if (idsObjetos == null || idsObjetos.isEmpty()) {
			return Collections.emptyList();
		}

		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		Conjunction conjunction = Restrictions.conjunction();

		conjunction.add(Restrictions.eq("nomeClasse", nomeClasse));
		conjunction.add(Restrictions.in("idObjeto", idsObjetos));

		dc.add(conjunction);

		dc.add(Restrictions.eq("naoPublicado", Boolean.FALSE));

		dc.addOrder(Order.asc("posicao"));

		return (List<Arquivo>) hibernateUtil.list(dc);
	}

	/*
	 * retirada dos arquivos em cache se dar� ao salvar o artigo
	 */
	public void excluirArquivos(String nomeClasse, Serializable idObjeto) {
		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		Criterion criterion2 = Restrictions.and(Restrictions.eq("nomeClasse", nomeClasse),
				Restrictions.eq("idObjeto", idObjeto.toString()));

		Criterion criterion3 = Restrictions.and(Restrictions.isNull("nomeClasse"), Restrictions.isNull("idObjeto"));
		criterion3 = Restrictions.and(criterion3, Restrictions.le("codigo", timeInMillisOntem()));

		dc.add(Restrictions.or(criterion2, criterion3));

		Collection arquivos = hibernateUtil.list(dc);

		for (Iterator iter = arquivos.iterator(); iter.hasNext();) {
			Arquivo arquivo = (Arquivo) iter.next();
			excluir(arquivo, false);
		}
	}

	private String timeInMillisOntem() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY, 10);

		return c.getTimeInMillis() + "";
	}

	public List listarArquivos(String nomeClasse, String idObjeto, Layout layout, Boolean todos) {
		return listarArquivos(nomeClasse, idObjeto, null, layout, todos);
	}

	public List listarArquivos(String nomeClasse, String idObjeto, String codArquivo, Layout layout, Boolean todos) {
		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		if (!Uteis.ehNuloOuVazio(nomeClasse) && idObjeto != null) {
			dc.add(Restrictions.eq("nomeClasse", nomeClasse));
			dc.add(Restrictions.eq("idObjeto", idObjeto));
		} else {
			dc.add(Restrictions.eq("codigo", codArquivo));
		}

		if (layout != null) {
			Criterion c = Restrictions.or(Restrictions.eq("layout", layout.toString()),
					Restrictions.eq("layout", Layout.PREVIEW.toString()));
			dc.add(c);
		}

		if (!todos) {
			dc.add(Restrictions.eq("naoPublicado", Boolean.FALSE));
		}

		dc.addOrder(Order.asc("posicao"));

		return hibernateUtil.list(dc);
	}

	@Cacheable("arquivos.first.result.url")
	public Arquivo findFirstResultImageUrl(String nomeClasse, String idObjeto, String layout) {

		if (Uteis.ehNuloOuVazio(nomeClasse, idObjeto)) {
			throw new ValidacaoException("NomeClasse/idObjeto deve ser preenchidos.");
		}

		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		dc.add(Restrictions.eq("nomeClasse", nomeClasse));
		dc.add(Restrictions.eq("idObjeto", idObjeto));

		List<Byte> tipos = Arrays.asList((byte) 1, (byte) 2);
		dc.add(Restrictions.in("tipo", tipos));

		if (layout != null) {
			dc.add(Restrictions.eq("layout", layout));
		}

		dc.addOrder(Order.asc("posicao"));

		Arquivo arquivo = (Arquivo) hibernateUtil.findFirstResult(dc);

		if (arquivo == null) {
			arquivo = new Arquivo();
			arquivo.setUrl(propNegocio.getUrlSemImagem());
			arquivo.setTitle("");
		}

		return arquivo;
	}
	
	@Cacheable("download.first.result.url")
	public Arquivo findFirstResultDownloadUrl(String nomeClasse, String idObjeto, String layout) {

		if (Uteis.ehNuloOuVazio(nomeClasse, idObjeto)) {
			throw new ValidacaoException("NomeClasse/idObjeto deve ser preenchidos.");
		}

		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		dc.add(Restrictions.eq("nomeClasse", nomeClasse));
		dc.add(Restrictions.eq("idObjeto", idObjeto));

		List<Byte> tipos = Arrays.asList((byte) 3);
		dc.add(Restrictions.in("tipo", tipos));

		if (layout != null) {
			dc.add(Restrictions.eq("layout", layout));
		}

		dc.addOrder(Order.asc("posicao"));

		Arquivo arquivo = (Arquivo) hibernateUtil.findFirstResult(dc);
		
		return arquivo;
	}

	@Cacheable("arquivos.first.result.url.lista")
	public List<Arquivo> findFirstResultImageUrlLista(String nomeClasse, List<String> idsObjetos, String layout) {

		if (idsObjetos.isEmpty()) {
			return Collections.emptyList();
		}

		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		dc.add(Restrictions.eq("nomeClasse", nomeClasse));
		dc.add(Restrictions.in("idObjeto", idsObjetos));

		List<Byte> tipos = Arrays.asList((byte) 1, (byte) 2);
		dc.add(Restrictions.in("tipo", tipos));

		if (layout != null) {
			dc.add(Restrictions.eq("layout", layout));
		}

		dc.addOrder(Order.asc("posicao"));

		List<Arquivo> arquivos = (List<Arquivo>) hibernateUtil.list(dc, idsObjetos.size());

		return arquivos;
	}

	@Cacheable("arquivos.image.urls")
	public List<Arquivo> findImageUrls(String nomeClasse, String idObjeto, String... layouts) {

		if (Uteis.ehNuloOuVazio(nomeClasse, idObjeto)) {
			throw new ValidacaoException("NomeClasse/idObjeto deve ser preenchidos.");
		}

		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		dc.add(Restrictions.eq("nomeClasse", nomeClasse));
		dc.add(Restrictions.eq("idObjeto", idObjeto));

		List<Byte> tipos = Arrays.asList((byte) 1, (byte) 2);
		dc.add(Restrictions.in("tipo", tipos));

		dc.add(Restrictions.eq("naoPublicado", Boolean.FALSE));

		if (layouts != null && layouts.length != 0) {
			dc.add(Restrictions.in("layout", layouts));
		}

		dc.addOrder(Order.asc("posicao"));

		List<Arquivo> arquivos = (List<Arquivo>) hibernateUtil.list(dc);
		return arquivos;
	}

	public Boolean possuiArquivos(String nomeClasse, String idObjeto, Layout layout, Boolean todos) {
		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		dc.add(Restrictions.eq("nomeClasse", nomeClasse));
		dc.add(Restrictions.eq("idObjeto", idObjeto));

		if (layout != null) {
			Criterion c = Restrictions.or(Restrictions.eq("layout", layout.toString()),
					Restrictions.eq("layout", Layout.PREVIEW.toString()));
			dc.add(c);
		}

		if (!todos) {
			dc.add(Restrictions.eq("naoPublicado", Boolean.FALSE));
		}

		return hibernateUtil.possuiRegistros(dc);
	}

	public Integer qtdArquivos(String nomeClasse, String idObjeto, String codigo) {
		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		if (!Uteis.ehNuloOuVazio(nomeClasse, idObjeto)) {
			dc.add(Restrictions.eq("nomeClasse", nomeClasse));
			dc.add(Restrictions.eq("idObjeto", idObjeto));
		} else {
			dc.add(Restrictions.eq("codigo", codigo));
		}

		dc.setProjection(Projections.rowCount());

		return (Integer) hibernateUtil.uniqueResult(dc);
	}

	public Boolean possuiArquivos(String nomeClasse, String idObjeto, String codigo) {
		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		if (!Uteis.ehNuloOuVazio(nomeClasse, idObjeto)) {
			dc.add(Restrictions.eq("nomeClasse", nomeClasse));
			dc.add(Restrictions.eq("idObjeto", idObjeto));
		} else {
			dc.add(Restrictions.eq("codigo", codigo));
		}

		dc.setProjection(Projections.rowCount());

		return hibernateUtil.possuiRegistros(dc);
	}

	public Arquivo consultarArquivoNewsletterTopo(String tipo, String nomeArquivo) {
		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		if ("topo".equals(tipo)) {
			dc.add(Restrictions.eq("nomeClasse", "toponewsletter"));
		} else if ("rodape".equals(tipo)) {
			dc.add(Restrictions.eq("nomeClasse", "rodapenewsletter"));
		}
		dc.add(Restrictions.eq("idObjeto", "1"));

		dc.add(Restrictions.eq("texto", nomeArquivo));
		dc.addOrder(Order.asc("texto"));

		List arquivos = hibernateUtil.list(dc);
		if (arquivos != null && !arquivos.isEmpty()) {
			return (Arquivo) arquivos.get(0);
		} else {
			return null;
		}
	}

	public void duplicarArquivos(String nomeClasseOrigem, String idObjetoOrigem, String nomeClasseDestino,
			String idObjetoDestino) {
		List arquivosOrigem = listarArquivos(nomeClasseOrigem, idObjetoOrigem, null, true);

		for (Iterator iterator = arquivosOrigem.iterator(); iterator.hasNext();) {
			Arquivo aOrigem = (Arquivo) iterator.next();

			Arquivo arquivo = new Arquivo();
			arquivo.setAltura(aOrigem.getAltura());
			arquivo.setCreditos(aOrigem.getCreditos());
			arquivo.setData(aOrigem.getData());
			arquivo.setIdObjeto(idObjetoDestino);
			arquivo.setLargura(aOrigem.getLargura());
			arquivo.setLayout(aOrigem.getLayout());
			arquivo.setLink(aOrigem.getLink());
			arquivo.setNaoPublicado(aOrigem.getNaoPublicado());
			arquivo.setNomeClasse(nomeClasseDestino);
			arquivo.setPosicao(aOrigem.getPosicao());
			arquivo.setTexto(aOrigem.getTexto());
			arquivo.setTipo(aOrigem.getTipo());
			arquivo.setTipoLink(aOrigem.getTipoLink());
			arquivo.setTitle(aOrigem.getTitle());

			String arquivo1Origem = aOrigem.getArquivo1();
			if (arquivo1Origem != null) {
				String novoNomeArquivo1 = arquivoUteis.renomear(arquivo1Origem);

				arquivo.setArquivo1(novoNomeArquivo1);

				File arquivoOrigem = new File(Uteis.caminhoFisico + "/arquivos/" + arquivo1Origem);
				File arquivoDestino = null;
				if (arquivoOrigem.exists()) {
					arquivoDestino = new File(Uteis.caminhoFisico + "/arquivos/" + novoNomeArquivo1);
				} else {
					arquivoOrigem = new File(Uteis.caminhoFisico + "/arquivos/downloads/" + arquivo1Origem);
					arquivoDestino = new File(Uteis.caminhoFisico + "/arquivos/downloads/" + novoNomeArquivo1);
				}

				arquivoUteis.new CopiarArquivosThread(arquivoOrigem, arquivoDestino);
			}

			String arquivo2Origem = aOrigem.getArquivo2();
			if (arquivo2Origem != null) {
				String novoNomeArquivo2 = arquivoUteis.renomearImagem_g(arquivo2Origem);

				arquivo.setArquivo2(novoNomeArquivo2);

				File arquivoOrigem = new File(Uteis.caminhoFisico + "/arquivos/" + arquivo2Origem);
				File arquivoDestino = null;
				if (arquivoOrigem.exists()) {
					arquivoDestino = new File(Uteis.caminhoFisico + "/arquivos/" + novoNomeArquivo2);
				} else {
					arquivoOrigem = new File(Uteis.caminhoFisico + "/arquivos/downloads/" + arquivo2Origem);
					arquivoDestino = new File(Uteis.caminhoFisico + "/arquivos/downloads/" + novoNomeArquivo2);
				}

				arquivoUteis.new CopiarArquivosThread(arquivoOrigem, arquivoDestino);
			}

			hibernateUtil.save(arquivo);
		}
	}

	public void enviarInputStreamResponse(InputStream inputStream, String nome, Integer length,
			HttpServletResponse response) throws Exception {

		response.setHeader("Pragma", "public, must-revalidate");
		response.setHeader("Cache-Control", "public, must-revalidate");
		response.setContentLength(length);
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nome + "");

		PrintWriter out = response.getWriter();
		try {
			// output the file
			int count = 0;
			int i;
			while ((i = inputStream.read()) != -1) {
				count++;
				out.write(i);
			}
			inputStream.close();
			out.close();
		} catch (IOException e) {
			logger.error("Problem in Download.  Could not output requested file ", e);
		}
	}

	public static String lerConteudoArquivo(String path) {
		StringBuilder sb = new StringBuilder();

		try {
			FileInputStream fis = new FileInputStream(new File(path));
			InputStreamReader inReader = new InputStreamReader(fis);

			BufferedReader read = new BufferedReader(inReader);
			String linha = null;

			while ((linha = read.readLine()) != null) {
				sb.append(linha + "\n");
			}
			read.close();
		} catch (Exception e) {
			logger.error("", e);
		}

		return sb.toString();
	}

	@Transactional
	@CacheEvict(value = { "imagens.site", "arquivos.publicados", "arquivos.first.result.url",
			"arquivos.first.result.url.lista", }, allEntries = true)
	public void alterarParaNaoPublicado(Arquivo arquivo) {
		arquivo = (Arquivo) consultar(Arquivo.class, arquivo.getId());

		arquivo.setNaoPublicado(true);

		atualizar(arquivo);
	}

	@Transactional
	@CacheEvict(value = { "imagens.site", "arquivos.publicados" }, allEntries = true)
	public void alterarLayout(Arquivo arquivo, String layout) {
		arquivo = (Arquivo) consultar(Arquivo.class, arquivo.getId());

		arquivo.setLayout(layout);

		atualizar(arquivo);
	}

	@Transactional
	@CacheEvict(value = { "imagens.site", "arquivos.publicados", "arquivos.first.result.url",
			"arquivos.first.result.url.lista", }, allEntries = true)
	public void alterarPosicao(Arquivo arquivo, Integer posicao) {
		arquivo = (Arquivo) consultar(Arquivo.class, arquivo.getId());

		arquivo.setPosicao(posicao);

		atualizar(arquivo);
	}

	@Transactional
	@CacheEvict(value = { "imagens.site", "arquivos.publicados", "arquivos.first.result.url",
			"arquivos.first.result.url.lista", }, allEntries = true)
	public void alterarPosicoes(String[] ids, String[] posicoesAnteriores, String[] posicoesNovas) {
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				String id = ids[i];
				String posicaoAnterior = posicoesAnteriores[i];
				String posicaoNova = posicoesNovas[i];

				Integer posicaoAnteriorInteger = Uteis.converterInteger(posicaoAnterior);
				Integer posicaoNovaInteger = Uteis.converterInteger(posicaoNova);

				if (posicaoAnteriorInteger != null && !posicaoAnteriorInteger.equals(posicaoNovaInteger)) {
					alterarPosicao(new Arquivo(id), posicaoNovaInteger);
				}
			}
		}
	}

	@Transactional
	public Arquivo gravarImagemIlionnetCliente(Arquivo arquivo) throws Exception {
		MultipartFile multipartFile = arquivo.getArquivo();

		String nomeArquivo1 = arquivoUteis.renomear(multipartFile.getOriginalFilename());
		arquivo.setArquivo1(nomeArquivo1);

		InputStream inputStream = multipartFile.getInputStream();

		if (ArquivoTipoEnum.IMAGEM.ehGif(nomeArquivo1)) {
			arquivoUteis.gravarArquivo(inputStream, nomeArquivo1, arquivoUteis.getPathArquivos());

			Map<String, Integer> dimen = redimencionamento
					.obterDimensoes(new File(arquivoUteis.getPathArquivos() + nomeArquivo1));

			arquivo.setLargura(dimen.get("largura"));
			arquivo.setAltura(dimen.get("altura"));
		} else {

			if (arquivo.getLarguraPequena() == null) {
				arquivoUteis.gravarArquivo(inputStream, nomeArquivo1, arquivoUteis.getPathArquivos());

				Map<String, Integer> dimen = redimencionamento.obterDimensoes(inputStream);

				arquivo.setLargura(dimen.get("largura"));
				arquivo.setAltura(dimen.get("altura"));
			} else {
				Map<String, Integer> dimen = redimencionamento.reduzirGravar(inputStream,
						arquivoUteis.getPathArquivos(), nomeArquivo1, arquivo.getLarguraPequena());

				arquivo.setLargura(dimen.get("largura"));
				arquivo.setAltura(dimen.get("altura"));
			}
		}

		if (arquivo.getId() == null) {
			arquivo = (Arquivo) hibernateUtil.save(arquivo);
		} else {
			hibernateUtil.update(arquivo);
		}

		clienteImagemService.limparCache();

		return arquivo;
	}

	public Integer proximaPosicao(String nomeClasse, String idObjeto, String codigo) {
		Integer ultimaPosicao = maxPosicao(nomeClasse, idObjeto, codigo);

		if (ultimaPosicao == null) {
			ultimaPosicao = 0;
		}

		ultimaPosicao++;

		return ultimaPosicao;
	}

	public static void escreverConteudoArquivo(String path, String conteudo) {
		try {
			File f = new File(path);

			if (!f.exists()) {
				f.createNewFile();
			}

			// Create file
			FileWriter fstream = new FileWriter(path);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(conteudo);
			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any
			String m = "erro ao escrever arquivo: path: " + path;
			logger.error(m, e);
		}
	}

	public void enviarArquivoResponse(String path, HttpServletResponse response) throws Exception {
		File file = new File(path);

		response.setHeader("Pragma", "public, must-revalidate");
		response.setHeader("Cache-Control", "public, must-revalidate");
		response.setContentLength((int) file.length());
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "");

		FileInputStream fileInputStream = new FileInputStream(file);

		PrintWriter out = response.getWriter();
		try {
			// output the file
			int count = 0;
			int i;
			while ((i = fileInputStream.read()) != -1) {
				count++;
				out.write(i);
			}
			fileInputStream.close();
			out.close();
		} catch (IOException e) {
			logger.error("Problem in Download.  Could not output requested file ", e);
		}
	}

	public String consultarTextoPorArquivo1(String arquivo1) {

		if (Uteis.ehNuloOuVazio(arquivo1)) {
			return null;
		}

		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);
		dc.add(Restrictions.eq("arquivo1", arquivo1));

		List<Arquivo> arquivos = hibernateUtil.listar(dc, 1, 0, null);

		if (arquivos == null || arquivos.isEmpty()) {
			return null;
		}

		return arquivos.get(0).getTexto();
	}

	public Arquivo getArquivo(String nomeClasse, String idObjeto, String layout) {
		if (Uteis.ehNuloOuVazio(nomeClasse) && Uteis.ehNuloOuVazio(idObjeto) && Uteis.ehNuloOuVazio(layout)) {
			return null;
		}

		DetachedCriteria dc = DetachedCriteria.forClass(Arquivo.class);

		if (!Uteis.ehNuloOuVazio(nomeClasse) && !Uteis.ehNuloOuVazio(idObjeto)) {
			dc.add(Restrictions.eq("nomeClasse", nomeClasse));
			dc.add(Restrictions.eq("idObjeto", idObjeto));
			dc.add(Restrictions.eq("layout", layout));
		}

		dc.addOrder(Order.asc("posicao"));

		// return hibernateUtil.list(dc);
		return (Arquivo) hibernateUtil.uniqueResult(dc);
	}

}