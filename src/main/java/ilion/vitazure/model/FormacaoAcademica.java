package ilion.vitazure.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "formacaoAcademica")
public class FormacaoAcademica {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO, generator = "formacaoAcademica_id_seq")
	  @SequenceGenerator(name = "formacaoAcademica_id_seq", sequenceName = "formacaoAcademica_id_seq", allocationSize = 1)
	  private Long id;
	  
	  private String tipoFormacao;

	  private String descricaoFormacao;
	  
	  
	  @ManyToOne
	  @JoinColumn(nullable = true)
	  private Profissional profissional;

		public Long getId() {
			if (id == null) {
				id = 0l;
			}
			return id;
		}
	
		public void setId(Long id) {
			this.id = id;
		}
	
		public String getDescricaoFormacao() {
			if (descricaoFormacao == null) {
				descricaoFormacao ="";
			}
			return descricaoFormacao;
		}
	
		public void setDescricaoFormacao(String descricaoFormacao) {
			this.descricaoFormacao = descricaoFormacao;
		}
	
		public String getTipoFormacao() {
			if (tipoFormacao == null) {
				tipoFormacao = "";
			}
			return tipoFormacao;
		}
	
		public void setTipoFormacao(String tipoFormacao) {
			this.tipoFormacao = tipoFormacao;
		}
	
		public Profissional getProfissional() {
			return profissional;
		}
	
		public void setProfissional(Profissional profissional) {
			this.profissional = profissional;
		}
	  
	  
	  
	
}
