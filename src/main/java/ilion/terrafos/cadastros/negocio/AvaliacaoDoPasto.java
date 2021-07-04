package ilion.terrafos.cadastros.negocio;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AvaliacaoDoPasto {
	
	@Column(name="avaliacao_aee")
	private Float aee;
	
	@Column(name="avaliacao_of")
	private Float of;
	
	@Column(name="avaliacao_escore_invasoras")
	private Integer escoreInvasoras;
	
	@Column(name="avaliacao_area_cocho")
	private Integer areaCocho;
	
	@Column(name="avaliacao_cobertura_de_cocho")
	private String coberturaDeCocho;
	
	@Column(name="avaliacao_acesso_cocho")
	private String acessoCocho;
	
	@Column(name="avaliacao_sub_estoque")
	private String subEstoque;
	
	@Column(name="avaliacao_aguadas")
	private String aguadas;
	
	@Column(name="avaliacao_qualidade_aguadas")
	private String qualidadeAguadas;
	
	@Column(name="avaliacao_cercas")
	private String cercas;

	public Float getAee() {
		return aee;
	}

	public void setAee(Float aee) {
		this.aee = aee;
	}

	public Float getOf() {
		return of;
	}

	public void setOf(Float of) {
		this.of = of;
	}

	public Integer getEscoreInvasoras() {
		return escoreInvasoras;
	}

	public void setEscoreInvasoras(Integer escoreInvasoras) {
		this.escoreInvasoras = escoreInvasoras;
	}

	public Integer getAreaCocho() {
		return areaCocho;
	}

	public void setAreaCocho(Integer areaCocho) {
		this.areaCocho = areaCocho;
	}

	public String getCoberturaDeCocho() {
		return coberturaDeCocho;
	}

	public void setCoberturaDeCocho(String coberturaDeCocho) {
		this.coberturaDeCocho = coberturaDeCocho;
	}

	public String getAcessoCocho() {
		return acessoCocho;
	}

	public void setAcessoCocho(String acessoCocho) {
		this.acessoCocho = acessoCocho;
	}

	public String getSubEstoque() {
		return subEstoque;
	}

	public void setSubEstoque(String subEstoque) {
		this.subEstoque = subEstoque;
	}

	public String getAguadas() {
		return aguadas;
	}

	public void setAguadas(String aguadas) {
		this.aguadas = aguadas;
	}

	public String getQualidadeAguadas() {
		return qualidadeAguadas;
	}

	public void setQualidadeAguadas(String qualidadeAguadas) {
		this.qualidadeAguadas = qualidadeAguadas;
	}

	public String getCercas() {
		return cercas;
	}

	public void setCercas(String cercas) {
		this.cercas = cercas;
	}
	
	
}
