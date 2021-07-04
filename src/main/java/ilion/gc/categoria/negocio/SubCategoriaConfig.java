package ilion.gc.categoria.negocio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubCategoriaConfig implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="sub_possui_sub_categorias")
	private Boolean possuiSubCategorias;
	
	@Column(name="sub_ordem",length=30)
	private String ordem;
	
	@Column(name="sub_possui_rss")
	private String possuiRssSubCategoria;
	
	@Column(name="sub_possui_descricao")
	private Boolean possuiDescricao;
	
	@Column(name="sub_possui_telefone")
	private Boolean possuiTelefone;

	@Column(name="sub_possui_email")
	private Boolean possuiEmail;



	public Boolean getPossuiSubCategorias() {
		return possuiSubCategorias;
	}

	public void setPossuiSubCategorias(Boolean possuiSubCategorias) {
		this.possuiSubCategorias = possuiSubCategorias;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getPossuiRssSubCategoria() {
		return possuiRssSubCategoria;
	}

	public void setPossuiRssSubCategoria(String possuiRssSubCategoria) {
		this.possuiRssSubCategoria = possuiRssSubCategoria;
	}

	public Boolean getPossuiDescricao() {
		return possuiDescricao;
	}

	public void setPossuiDescricao(Boolean possuiDescricao) {
		this.possuiDescricao = possuiDescricao;
	}

	public Boolean getPossuiTelefone() {
		return possuiTelefone;
	}

	public void setPossuiTelefone(Boolean possuiTelefone) {
		this.possuiTelefone = possuiTelefone;
	}

	public Boolean getPossuiEmail() {
		return possuiEmail;
	}

	public void setPossuiEmail(Boolean possuiEmail) {
		this.possuiEmail = possuiEmail;
	}
	
}