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

import ilion.vitazure.enumeradores.TemasTrabalhoEnum;

@Entity
@Table(name = "temaTrabalho")
public class TemaTrabalho {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO, generator = "temaTrabalho_id_seq")
	  @SequenceGenerator(name = "temaTrabalho_id_seq", sequenceName = "temaTrabalho_id_seq", allocationSize = 1)
	  private Long id;
	  
	  @Enumerated(EnumType.STRING)
	  private TemasTrabalhoEnum tema;
	  
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
	
		public TemasTrabalhoEnum getTema() {
			return tema;
		}
	
		public void setTema(TemasTrabalhoEnum tema) {
			this.tema = tema;
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
