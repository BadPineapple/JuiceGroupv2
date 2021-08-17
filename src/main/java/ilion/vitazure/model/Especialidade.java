package ilion.vitazure.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ilion.vitazure.enumeradores.EspecialidadesEnum;

@Entity
@Table(name = "especialidade")
public class Especialidade {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO, generator = "especialidade_id_seq")
	  @SequenceGenerator(name = "especialidade_id_seq", sequenceName = "especialidade_id_seq", allocationSize = 1)
	  private Long id;
	 
	  private String especialidade;
	  
	  @ManyToOne
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

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	 
	public Profissional getProfissional() {
		if (profissional == null) {
			profissional = new Profissional();
		}
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

}
