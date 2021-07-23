package ilion.vitazure.negocio;

import java.util.ArrayList;
import java.util.List;

import ilion.vitazure.model.EnderecoAtendimento;
import ilion.vitazure.model.FormacaoAcademica;
import ilion.vitazure.model.Profissional;

public class ProfissionalVH {

	private Profissional profissional;
	
	private List<FormacaoAcademica> formacaoAcademica;
	
	private List<EnderecoAtendimento> enderecoAtendimento;

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

	
	
	
	
	
}
