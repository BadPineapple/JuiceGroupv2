package ilion.vitazure.model;

import ilion.vitazure.enumeradores.DiasSemanaEnum;

public class HorarioPossivelAtendimento {

	private String horaPossivelAtendiemnto;
	private DiasSemanaEnum diaSemanaEnum;
	private Long codigoProfissional;
	
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
	
	
	
	
}
