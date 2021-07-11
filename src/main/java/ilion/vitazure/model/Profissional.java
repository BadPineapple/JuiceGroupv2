package ilion.vitazure.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "profissional")
public class Profissional implements Serializable{

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO, generator = "profissional_id_seq")
	  @SequenceGenerator(name = "profissional_id_seq", sequenceName = "profissional_id_seq", allocationSize = 1)
	  private Long id;
	  
	  @ManyToOne
	  private Pessoa pessoa;
	  
	  private Boolean ativo;
	  
	  private String plano;
	  
	  private Date dataInicioPlano;
	  
	  private Date dataFimPlano;
	  
	  
	  
}
