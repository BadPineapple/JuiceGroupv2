package ilion.vitazure.negocio;

import java.util.ArrayList;
import java.util.List;

import ilion.vitazure.model.EnderecoAtendimento;
import ilion.vitazure.model.Especialidade;
import ilion.vitazure.model.FormacaoAcademica;
import ilion.vitazure.model.HorarioAtendimento;
import ilion.vitazure.model.Profissional;
import ilion.vitazure.model.TemaTrabalho;

public class ProfissionalVH {

	private Profissional profissional;
	
	private List<FormacaoAcademica> formacaoAcademica;
	
	private List<EnderecoAtendimento> enderecoAtendimento;
	
	private List<HorarioAtendimento> horarioAtendimento;
	
	private List<Especialidade> especialidade;

	private List<TemaTrabalho> temasTrabalho;

	private List<String> temas;
	
	private Especialidade especialidadeAdicionar;
	
	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public List<FormacaoAcademica> getFormacaoAcademica() {
		if (formacaoAcademica == null) {
			formacaoAcademica = new ArrayList<FormacaoAcademica>();
		}
		return formacaoAcademica;
	}

	public void setFormacaoAcademica(List<FormacaoAcademica> formacaoAcademica) {
		this.formacaoAcademica = formacaoAcademica;
	}

	public List<EnderecoAtendimento> getEnderecoAtendimento() {
		if (enderecoAtendimento == null) {
			enderecoAtendimento = new ArrayList<EnderecoAtendimento>();
		}
		return enderecoAtendimento;
	}

	public void setEnderecoAtendimento(List<EnderecoAtendimento> enderecoAtendimento) {
		this.enderecoAtendimento = enderecoAtendimento;
	}

	public List<HorarioAtendimento> getHorarioAtendimento() {
		if (horarioAtendimento == null) {
			horarioAtendimento = new ArrayList<HorarioAtendimento>();
		}
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(List<HorarioAtendimento> horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}

	public List<Especialidade> getEspecialidade() {
		if (especialidade == null) {
			especialidade = new ArrayList<Especialidade>();
		}
		return especialidade;
	}

	public void setEspecialidade(List<Especialidade> especialidade) {
		this.especialidade = especialidade;
	}

	public List<TemaTrabalho> getTemasTrabalho() {
		if (temasTrabalho == null) {
			temasTrabalho = new ArrayList<TemaTrabalho>();
		}
		return temasTrabalho;
	}

	public void setTemasTrabalho(List<TemaTrabalho> temasTrabalho) {
		this.temasTrabalho = temasTrabalho;
	}

	public Especialidade getEspecialidadeAdicionar() {
		return especialidadeAdicionar;
	}

	public void setEspecialidadeAdicionar(Especialidade especialidadeAdicionar) {
		this.especialidadeAdicionar = especialidadeAdicionar;
	}

	public List<String> getTemas() {
		return temas;
	}

	public void setTemas(List<String> temas) {
		this.temas = temas;
	}

	
	
	
	
	
}
