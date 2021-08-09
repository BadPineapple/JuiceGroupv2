package ilion.vitazure.model;

import ilion.vitazure.enumeradores.DiasSemanaEnum;

public class HorarioPossivelAtendimento {

	private String horaPossivelAtendiemnto;
	private DiasSemanaEnum diaSemanaEnum;
	private Long codigoProfissional;
	private String enderecoatendimento;
	private String linkGoogleMaps;
	
	public String getHoraPossivelAtendiemnto() {
		return horaPossivelAtendiemnto;
	}
	public void setHoraPossivelAtendiemnto(String horaPossivelAtendiemnto) {
		this.horaPossivelAtendiemnto = horaPossivelAtendiemnto;
	}
	public DiasSemanaEnum getDiaSemanaEnum() {
		return diaSemanaEnum;
	}
	public void setDiaSemanaEnum(DiasSemanaEnum diaSemanaEnum) {
		this.diaSemanaEnum = diaSemanaEnum;
	}
	public Long getCodigoProfissional() {
		return codigoProfissional;
	}
	public void setCodigoProfissional(Long codigoProfissional) {
		this.codigoProfissional = codigoProfissional;
	}
	public String getEnderecoatendimento() {
		return enderecoatendimento;
	}
	public void setEnderecoatendimento(String enderecoatendimento) {
		this.enderecoatendimento = enderecoatendimento;
	}
	public String getLinkGoogleMaps() {
		return linkGoogleMaps;
	}
	public void setLinkGoogleMaps(String linkGoogleMaps) {
		this.linkGoogleMaps = linkGoogleMaps;
	}
	
	
	
	
}
