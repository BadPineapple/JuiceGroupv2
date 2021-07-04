package ilion.gc.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import ilion.admin.negocio.Usuario;
import ilion.arquivo.negocio.ArquivoNegocio;
import ilion.gc.categoria.negocio.CategoriaArtigo;
import ilion.gc.categoria.negocio.CategoriaArtigoNegocio;
import ilion.gc.categoria.negocio.SubCategoriaArtigoNegocio;
import ilion.gc.negocio.Artigo;
import ilion.gc.negocio.ArtigoConteudo;
import ilion.gc.negocio.ArtigoNegocio;
import ilion.gc.negocio.SubCategoriaArtigo;
import ilion.gc.topico.negocio.Topico;
import ilion.gc.util.UteisGC;
import ilion.util.StringUtil;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@RequestMapping("/ilionnet/artigo-form")
@SessionAttributes("artigo")
@UsuarioLogado("artigo-form.sp")
public class ArtigoFormController {
	
	static Logger logger = Logger.getLogger(ArtigoFormController.class);
	
	@Autowired
	private ArtigoNegocio artigoNegocio;
	
	@Autowired
	private CategoriaArtigoNegocio categoriaArtigoNegocio;

	@Autowired
	private SubCategoriaArtigoNegocio subCategoriaArtigoNegocio;
	
	@Autowired
	private ArquivoNegocio arquivoNegocio;

	@RequestMapping(method = RequestMethod.GET)
	public String carregar(String id, Long idCategoria, String idSubCategoria, ModelMap modelMap, HttpServletRequest request) throws Exception {
		if((id == null || 
				id.length() == 0) &&
				idCategoria == null) {
			return "redirect:gc.sp";
		}
		
		Artigo artigo = null; 
		
		Long idLong = Uteis.converterLong(id);
		
		if(idLong != null) {
			artigo = (Artigo) artigoNegocio.consultarPorIdParaEditar(idLong);
			
			Calendar dataPublicacaoCalendar = Calendar.getInstance();
			dataPublicacaoCalendar.setTime(artigo.getDataPublicacao());
			//artigo.setHoraPublicacao(artigo.getDataPublicacao());
			artigo.setHoraPublicacaoInt(dataPublicacaoCalendar.get(Calendar.HOUR_OF_DAY));

			List<ArtigoConteudo> artigoConteudos = artigoNegocio.listarArtigoConteudo(artigo, null);
			for(ArtigoConteudo artigoConteudo : artigoConteudos) {
				artigoConteudo.setCodControle(artigoConteudo.getId()+""+System.currentTimeMillis());
				
				String alinhamentoArquivoLateral = 
					(String) StringUtil.buscarValor(artigoConteudo.getConteudoInfo(), UteisGC.CONTEUDOINFO_NOS.ARQ_LATERAL_ALINHAMENTO, String.class);
				artigoConteudo.setAlinhamentoArquivoLateral(alinhamentoArquivoLateral);
			}
			artigo.setArtigoConteudos(artigoConteudos);
			
			artigo.setSecoesList(Uteis.formarListaComXml(artigo.getSecoes(), "s"));
		}

		if(artigo == null) {
			artigo = new Artigo();
			
			CategoriaArtigo categoriaArtigo = categoriaArtigoNegocio.consultarPorId(idCategoria);
			
			if( categoriaArtigo == null ) {
				return "redirect:gc-busca.sp?m=categoria-nao-encontrada";
			}
			
			artigo.setCategoriaArtigo(categoriaArtigo);
			
			Boolean possuiSubcategorias = categoriaArtigo.getSubCategoriaConfig().getPossuiSubCategorias() != null && 
					categoriaArtigo.getSubCategoriaConfig().getPossuiSubCategorias();
			
			if( possuiSubcategorias ) {
				
				Long idSubCategoriaLong = Uteis.converterLong(idSubCategoria);
				
				if(idSubCategoriaLong != null) {
					SubCategoriaArtigo subCategoriaArtigo = subCategoriaArtigoNegocio.consultarPorId(idSubCategoriaLong);
					artigo.setSubCategoria(subCategoriaArtigo);
				}
				
			}

			Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
			Boolean possuiPermissaoPublicar = usuarioSessao.getPermissoes().contains("artigo-publicar.sp");
			if(possuiPermissaoPublicar == null || !possuiPermissaoPublicar) {
				artigo.setStatus("Em Aprovação");
			} else {
				artigo.setStatus("Publicado");
			}
			
			Date dataPublicacao = new Date();
			Date dataExpiracao = Uteis.acrescentar(dataPublicacao, Calendar.YEAR, 5);
			
			artigo.setDataPublicacao(dataPublicacao);
			
			Calendar dataPublicacaoCalendar = Calendar.getInstance();
			dataPublicacaoCalendar.setTime(artigo.getDataPublicacao());
			//artigo.setHoraPublicacao(artigo.getDataPublicacao());
			artigo.setHoraPublicacaoInt(dataPublicacaoCalendar.get(Calendar.HOUR_OF_DAY));
			
			artigo.setDataExpiracao(dataExpiracao);
			
			artigo.setCodControle("ART:"+System.currentTimeMillis()+"");
			
			artigo.setArtigoConteudos(new ArrayList<ArtigoConteudo>());
		}
		
		if(artigo.getId() == null) {
			Long idSubCategoriaLong = null;
			if(artigo.getSubCategoria() != null) {
				idSubCategoriaLong = artigo.getSubCategoria().getId();
			}

			Integer posicao = artigoNegocio.consultarUltimaPosicaoArtigo(artigo.getCategoriaArtigo().getId(), idSubCategoriaLong);
			if(posicao == null)
				posicao = 0;
			posicao++;
			artigo.setPosicao(posicao);
		}
		
		modelMap.addAttribute("artigo", artigo);
		
		return "/ilionnet/modulos/gc/artigo-form";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Boolean.class, "destaque", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "destaque2", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "subDestaque", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "destaqueEsa", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "destaqueCel", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "destaqueEleicoesLimpas", new CustomBooleanEditor(true));
		binder.registerCustomEditor(Boolean.class, "destaqueAdvogadoDigital", new CustomBooleanEditor(true));
		
		binder.registerCustomEditor(Integer.class, "posicao", new CustomNumberEditor(Integer.class, true));
		//binder.registerCustomEditor(Byte.class, "artigoConteudo.posicao", new CustomNumberEditor(Byte.class, true));
		binder.registerCustomEditor(Date.class, "dataEvento", Uteis.novoCustomDateEditor("dd/MM/yyyy", true));
		binder.registerCustomEditor(Date.class, "dataPublicacao", Uteis.novoCustomDateEditor("dd/MM/yyyy", true));
		binder.registerCustomEditor(Date.class, "dataExpiracao", Uteis.novoCustomDateEditor("dd/MM/yyyy", true));
		//binder.registerCustomEditor(Date.class, "horaPublicacao", Uteis.novoCustomDateEditor("HH:mm", true));
		binder.registerCustomEditor(Integer.class, "horaPublicacaoInt", new CustomNumberEditor(Integer.class, true));
		binder.registerCustomEditor(List.class, "topicos", new CustomCollectionEditor(List.class) {
			
			@Override
			protected Object convertElement(Object element) {
				Long id = null;
				//System.out.println("element: "+element);
				if(element instanceof String && !((String)element).equals("")){
					//From the JSP 'element' will be a String
					try {
						id = Long.parseLong((String) element);
					} catch (NumberFormatException e) {
						//                      System.out.println("Element was " + ((String) element));
						//                      e.printStackTrace();
					}
				} else if(element instanceof Long) {
					//From the database 'element' will be a Long
					id = (Long) element;
				}
				
				return id != null ? new Topico(id) : null;
			}
		});
		
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(String acao, @ModelAttribute("artigo") Artigo artigo, BindingResult bindingResult, HttpServletRequest request) {
		
		artigoNegocio.validarArtigo(acao, artigo, bindingResult);
		
		if (bindingResult.hasErrors()) {
			
			executarAcoes(acao, artigo);
			
			return "/ilionnet/modulos/gc/artigo-form";
		} else {
			
			Usuario usuarioSessao = (Usuario) request.getSession().getAttribute("usuarioSessao");
			
			artigo = artigoNegocio.incluirArtigo(artigo, usuarioSessao);
			
			String nomeCategoria = artigo.getCategoriaArtigo().getNome();
			String idSubCategoria = artigo.getSubCategoria() != null ? artigo.getSubCategoria().getId().toString() : "";

			return "redirect:artigo-form.sp?nomeCategoria="+nomeCategoria+"&idSubCategoria="+idSubCategoria+"&id="+artigo.getId()+"&m=ok";
		}
	}

	private void executarAcoes(String acao, Artigo artigo) {
		if(acao == null) {
			return;
		}

		if(acao.startsWith("Clique aqui")) {
			adicionarConteudo(acao, artigo);
		} else if("Editar".equals(acao)) {
			for(ArtigoConteudo artigoConteudo : artigo.getArtigoConteudos()) {
				if(artigoConteudo.getCodControle().equals(artigo.getCodControleConteudo())) {
					artigo.setArtigoConteudo(artigoConteudo);
					break;
				}
			}
		} else if("&#8593;".equals(acao)) {
			for (ArtigoConteudo artigoConteudo : artigo.getArtigoConteudos()) {
				if(artigoConteudo.getCodControle().equals(artigo.getCodControleConteudo())) {
					Byte posicao = artigoConteudo.getPosicao();
					if(posicao > 1) {
						posicao--;
						artigoConteudo.setPosicao(posicao);
					}
					break;
				}
			}
		} else if("&#8595;".equals(acao)) {
			for (ArtigoConteudo artigoConteudo : artigo.getArtigoConteudos()) {
				if(artigoConteudo.getCodControle().equals(artigo.getCodControleConteudo())) {
					Byte posicao = artigoConteudo.getPosicao();
					posicao++;
					artigoConteudo.setPosicao(posicao);
					break;
				}
			}
		} else if("X".equals(acao)) {
			for (Iterator<ArtigoConteudo> iterator = artigo.getArtigoConteudos().iterator(); iterator.hasNext();) {
				ArtigoConteudo artigoConteudo = (ArtigoConteudo) iterator.next();

				if(artigoConteudo.getCodControle().equals(artigo.getCodControleConteudo())) {
					iterator.remove();

					if(artigoConteudo.getId() != null) {
						artigoNegocio.excluir(artigoConteudo);
						arquivoNegocio.excluirArquivos(ArtigoConteudo.class.getSimpleName(), artigoConteudo.getId());
					}
					
					break;
				}
			}
		}

		Collections.sort(artigo.getArtigoConteudos());
	}

	private Byte qtdLayout(List<ArtigoConteudo> conteudos, String layout) {
		Byte qtd = 0;

		for (ArtigoConteudo artigoConteudo : conteudos) {
			if(layout.equals(artigoConteudo.getLayout())) {
				qtd++;
			}
		}

		return qtd;
	}

	private void adicionarConteudo(String acao, Artigo artigo) {
		artigoNegocio.verificarAdicionarArtigoConteudo(artigo);
		
		String layout = null;

		if(acao.contains("'")) {
			layout = acao.substring(acao.indexOf("'")+1, acao.lastIndexOf("'"));
		}

		if(layout != null) {
			ArtigoConteudo artigoConteudo = new ArtigoConteudo();
			artigoConteudo.setCodControle(System.currentTimeMillis()+"");
			artigoConteudo.setLayout(layout);

			Byte posicao = qtdLayout(artigo.getArtigoConteudos(), layout);
			posicao++;
			artigoConteudo.setPosicao(posicao);
			artigoConteudo.setAlinhamentoArquivoLateral("left");

			artigo.setArtigoConteudo(artigoConteudo);
		}
	}
}