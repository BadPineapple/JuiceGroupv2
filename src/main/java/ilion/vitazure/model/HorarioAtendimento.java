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

import ilion.vitazure.enumeradores.DiasSemanaEnum;

@Entity
@Table(name = "horarioAtendimento")
public class HorarioAtendimento {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO, generator = "enderecoAtendimento_id_seq")
	  @SequenceGenerator(name = "enderecoAtendimento_id_seq", sequenceName = "enderecoAtendimento_id_seq", allocationSize = 1)
	  private Long id;
	 
	  @Enumerated(EnumType.STRING)
	  private DiasSemanaEnum diaSemana;
	
	  @ManyToOne
	  private Profissional profissional;
	  
	  @ManyToOne
	  private EnderecoAtendimento enderecoAtendimento;
	  
	  private Boolean atendimentoOnline;

	  private Boolean atendimentoPresencial;
	  
	  private String horaInicio;
	  
	  private String horaFim;
	 
	  private String horaAlmocoInicio;
	  
	  private String horaAlmocoFim;
	  
	  

	public Long getId() {
		if (id == null) {
			id = 0l;
		}
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DiasSemanaEnum getDiaSemana() {
		if (diaSemana == null) {
			diaSemana = DiasSemanaEnum.SEGUNDA;
		}
		return diaSemana;
	}

	public void setDiaSemana(DiasSemanaEnum diaSemana) {
		this.diaSemana = diaSemana;
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

	public EnderecoAtendimento getEnderecoAtendimento() {
		if (enderecoAtendimento == null) {
			enderecoAtendimento = new EnderecoAtendimento();
		}
		return enderecoAtendimento;
	}

	public void setEnderecoAtendimento(EnderecoAtendimento enderecoAtendimento) {
		this.enderecoAtendimento = enderecoAtendimento;
	}

	public Boolean getAtendimentoOnline() {
		if (atendimentoOnline == null) {
			atendimentoOnline = Boolean.FALSE;
		}
		return atendimentoOnline;
	}

	public void setAtendimentoOnline(Boolean atendimentoOnline) {
		this.atendimentoOnline = atendimentoOnline;
	}
	
	public Boolean getAtendimentoPresencial() {
		if (atendimentoPresencial == null) {
			atendimentoPresencial = Boolean.FALSE;
		}
		return atendimentoPresencial;
	}

	public void setAtendimentoPresencial(Boolean atendimentoPresencial) {
		this.atendimentoPresencial = atendimentoPresencial;
	}

	public String getHoraInicio() {
		if (horaInicio == null) {
			horaInicio = "";
		}
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		if (horaFim == null) {
			horaFim = "";
		}
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public String getHoraAlmocoInicio() {
		if (horaAlmocoInicio == null) {
			horaAlmocoInicio = "";
		}
		return horaAlmocoInicio;
	}

	public void setHoraAlmocoInicio(String horaAlmocoInicio) {
		this.horaAlmocoInicio = horaAlmocoInicio;
	}

	public String getHoraAlmocoFim() {
		if (horaAlmocoFim == null) {
			horaAlmocoFim = "";
		}
		return horaAlmocoFim;
	}

	public void setHoraAlmocoFim(String horaAlmocoFim) {
		this.horaAlmocoFim = horaAlmocoFim;
	}

	
	  
	  
	  
}
