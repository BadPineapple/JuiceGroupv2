/*
 * Created on 15/10/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ilion.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ilion.util.busca.BuscaUtil;
import ilion.vitazure.enumeradores.StatusEnum;

public class VLHForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer pagingPage = new Integer(1);
	private Integer pagingNumberPer = new Integer(20);
	private String sortColumn = "";
	private Integer sortDirection = ValueListInfo.ASC;
	private String palavraChave;
	private String palavraChaveBusca;
	private Long id;
	private Long idCategoria;
	private Long idArtigo;
	private String letraInicial;
	private String tipo;
	private String idioma;
	private String status;

	private Long idGrupoContato;

	private String categoria;
	private Long idSubCategoria;
	private Boolean permissaoEmail;
	private String nomeCategoria;
	
	private String grupo;
	
	private String marcaSlug;
	
	private Map<String, Object> params = new HashMap<>();
	
	private String dataInicio;

	private String dataFim;
	
	private StatusEnum statusAgenda;
	
	public VLHForm() {
		super();
	}

	//    public VLHForm(HttpServletRequest request) {
	//		super();
	//		UteisSpring.setarParametros(this, request);
	//	}

	public Integer getPagingPage() {
		return pagingPage;
	}

	public void setPagingPage(Integer pagingPage) {
		this.pagingPage = pagingPage;
	}

	public Integer getPagingNumberPer() {
		return pagingNumberPer;
	}

	public void setPagingNumberPer(Integer pagingNumberPer) {
		this.pagingNumberPer = pagingNumberPer;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public Integer getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(Integer sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getLetraInicial() {
		return letraInicial;
	}

	public void setLetraInicial(String letraInicial) {
		this.letraInicial = letraInicial;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPalavraChaveBusca() {
		return palavraChaveBusca;
	}

	public void setPalavraChaveBusca(String palavraChaveBusca) {
		this.palavraChaveBusca = palavraChaveBusca;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Long getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(Long idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public Boolean getPermissaoEmail() {
		return permissaoEmail;
	}

	public void setPermissaoEmail(Boolean permissaoEmail) {
		this.permissaoEmail = permissaoEmail;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public Long getIdGrupoContato() {
		return idGrupoContato;
	}

	public void setIdGrupoContato(Long idGrupoContato) {
		this.idGrupoContato = idGrupoContato;
	}

	public Long getIdArtigo() {
		return idArtigo;
	}

	public void setIdArtigo(Long idArtigo) {
		this.idArtigo = idArtigo;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getMarcaSlug() {
		return marcaSlug;
	}

	public void setMarcaSlug(String marcaSlug) {
		this.marcaSlug = marcaSlug;
	}
	
	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public StatusEnum getStatusAgenda() {
		return statusAgenda;
	}

	public void setStatusAgenda(StatusEnum statusAgenda) {
		this.statusAgenda = statusAgenda;
	}

	public static VLHForm getVLHSessionSomente(String id, HttpServletRequest request) {
		HttpSession s = request.getSession();

		String atributo = id + "#VLHForm#" + s.hashCode();

		VLHForm vlhForm = (VLHForm) s.getAttribute(atributo);
		if (vlhForm == null) {
			vlhForm = new VLHForm();
			s.setAttribute(atributo, vlhForm);
		}

		return vlhForm;
	}

	public static VLHForm getVLHSession(String id, HttpServletRequest request) {
		VLHForm vlhForm = getVLHSessionSomente(id, request);

		vlhForm.setarParametros(request);

		return vlhForm;
	}
	public static VLHForm getVLHSessionMudarPagina(String id, HttpServletRequest request, int i) {
		VLHForm vlhForm = getVLHSessionSomente(id, request);
		
		vlhForm.setarParametrosPagina(i);
		
		return vlhForm;
	}
	
	private void setarParametros(HttpServletRequest request) {
		String setarParametros = (String) request.getParameter("setarParametros");
		String sp = (String) request.getParameter("sp");
		String paginar = (String) request.getParameter("paginar");
		
		if( "true".equals(setarParametros) || "true".equals(sp) || "true".equals(paginar) ) {
			
			setarParametrosPaginacao(request);
			
		}
		
		if( "true".equals(setarParametros) || "true".equals(sp) ) {
			
			setarParametrosFiltros(request);
			
		}
	}
	
	public void setarParametrosFiltros(HttpServletRequest request) {
		//			String palavraChaveAnterior = this.getPalavraChave();

		String palavraChave = request.getParameter("palavraChave");
		this.palavraChave = palavraChave;
		this.palavraChaveBusca = BuscaUtil.ajustar(palavraChave);

		//			if("GET".equals(request.getMethod())) {
		//				this.setPalavraChave(palavraChaveAnterior);
		//			}

		Object status = request.getParameter("status");
		if (status != null)
			this.setStatus((String) status);

		Object idioma = request.getParameter("idioma");
		this.setIdioma((idioma != null) ? (String) idioma : "br");

		this.setIdCategoria(Uteis.converterLong(request.getParameter("idCategoria")));
		//this.setIdC(Uteis.converterLong(request.getParameter("idC")));

		Object id = request.getParameter("id");
		if (id != null)
			this.setId(Uteis.converterLong((String) id));
		this.setLetraInicial(request.getParameter("letraInicial"));

		this.setTipo(request.getParameter("tipo"));

		this.setCategoria(request.getParameter("categoria"));

		this.setIdCategoria(Uteis.converterLong(request.getParameter("idCategoria")));
		
		Long idSubCategoria = Uteis.converterLong(request.getParameter("idSubCategoria"));
		this.setIdSubCategoria(idSubCategoria);

		this.setNomeCategoria(request.getParameter("nomeCategoria"));

		this.setIdGrupoContato(Uteis.converterLong(request.getParameter("idGrupoContato")));

		String permissaoEmail = request.getParameter("permissaoEmail");
		if ("false".equals(permissaoEmail)) {
			this.setPermissaoEmail(false);
		} else if ("true".equals(permissaoEmail)) {
			this.setPermissaoEmail(true);
		}

		this.setIdArtigo(Uteis.converterLong(request.getParameter("idArtigo")));
			
		this.marcaSlug = request.getParameter("marca");
		
		this.setDataInicio(request.getParameter("dataInicio"));

		this.setDataFim(request.getParameter("dataFim"));
		
		this.setStatusAgenda(StatusEnum.fromString(request.getParameter("statusAgenda")));
		
		this.params.clear();
		
		for (Iterator iterator = request.getParameterMap().keySet().iterator(); iterator.hasNext();) {
			String param = (String) iterator.next();
			String valor = request.getParameter(param);
			
			this.params.put(param, Uteis.decode(valor));
		}
	}

	public void setarParametrosPaginacao(HttpServletRequest request) {
		Object pagingPage = request.getParameter("pagingPage");

		if (pagingPage != null) {
			this.setPagingPage(Uteis.converterInteger((String) pagingPage));
			this.setPagingPage((this.getPagingPage().intValue() == 0) ? new Integer(1) : this.getPagingPage());
		}
		
		Integer pagingNumberPer = Uteis.converterInteger(request.getParameter("pagingNumberPer"));
		
		if( pagingNumberPer == null ) {
			pagingNumberPer = 20;
		}
		
		this.pagingNumberPer = pagingNumberPer;
		
		Object sortColumn = request.getParameter("sortColumn");
		if (sortColumn != null)
			this.setSortColumn((String) sortColumn);
		
		Object sortDirection = request.getParameter("sortDirection");
		if (sortDirection != null) {
			this.setSortDirection(Uteis.converterInteger((String) sortDirection));
			this
			.setSortDirection((this.getSortDirection().intValue() == 0) ? ValueListInfo.ASC
					: this.getSortDirection());
		}
	}

	private void setarParametrosPagina(int pagina) {
		Object pagingPage = pagina;

		if (pagingPage != null) {
			this.setPagingPage((Integer) pagingPage);
			this.setPagingPage((this.getPagingPage().intValue() == 0) ? new Integer(1) : this.getPagingPage());
		}
	}


	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}


}