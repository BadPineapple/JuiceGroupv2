package ilion.vitazure.enumeradores;

public enum TemasTrabalhoEnum {
	
	NAO_INFORMADO(""),
	ACOMPANHAMENTO_PSICOLOGICO_GRAVIDEZ("Acompanhamento psicológico de gravidez"),
	ACOMPANHAMENTO_PSICOLOGICO_IDOSO("Acompanhamento psicológico de idosos"),
	ADAPTACAO_HOME_OFFICE("Adaptação ao home office"),
	ADOCAO_FILHOS("Adoção de filhos"),
	ADOLESCENCIA("Adolescência"),
	ANSIEDADE("Ansiedade"),
	APRENDER("Aprendizagem"),
	AVALIACAO_PSICOLOGICA("Avaliação psicológica"),
	CASAL("Casal"),
	CASAMENTO("Casamento"),
	COMPULSOES("Compulsões"),
	CONFLITO_AMOROSO("Conflitos amorosos"),
	CONFLITO_FAMILIAR("Conflitos familiares"),
	DEPENDENCIA_JOGOS("Dependência em jogos"),
	DEPENDENCIA_QUIMICA("Dependência química"),
	DEPRESSAO("Depressão"),
	DESENVOLVIMENTO_COPETENCIA_PROFISSIONAIS("Desenvolvimento de competências profissionais"),
	DESENVOLVIMENTO_PESSOAL("Desenvolvimento pessoal"),
	EMAGRECIMENTO("Emagrecimento"),
	ENTREVISTA_PSICOLOGICAS("Entrevistas psicológicas"),
	ESTRESSE("Estresse"),
	ESTRESSE_TRAUMATICO("Estresse Pós Traumático"),
	FOBIA_SOCIAL("Fobia Social"),
	IDOSO("Idoso / Terceira Idade"),
	INCERTEZAS_MOMENTO_FUTURO("Incertezas quanto ao momento e futuro"),
	INFANCIA("Infância"),
	LUDOTERAPIA("Ludoterapia"),
	MEDOS_FOBIAS("Medos e Fobias"),
	MORTE_LUTO("Morte e Luto"),
	NEUROPSICOLOGIA("Neuropsicologia"),
	OBESIDADE("Obesidade"),
	ORIENTACOES_PAIS("Orientação de pais"),
	ORIENTACOES_PROFISSIONAIS("Orientação profissional"),
	ORIENTACOES_PSICOLOGICA("Orientação psicológica"),
	PACIENTES_ENFERMOS("Pacientes enfermos"),
	PESSOAS_DEFICIENCIA("Pessoas com deficiência"),
	PLANEJAMENTO_PSICOPEDAGOGICO("Planejamento psicopedagógico"),
	PREPARACAO_APOSENTADORIA("Preparação para aposentadoria"),
	PSICOMOTRICIDADE("Psicomotricidade"),
	PSICOPEDAGOGIA("Psicopedagogia"),
	QUARENTENA("Quarentena e isolamento social"),
	REABILITACAO_COGNITIVA("Reabilitação cognitiva"),
	REABILITACAO_NEUROPSICOLOGIA("Reabilitação neuropsicológica"),
	RECRUTAMENTO_SELECAO_PESSOA("Recrutamento e seleção de pessoal"),
	RELACIONAMENTO("Relacionamento"),
	RELACIONAMENTO_AFETIVO("Relacionamentos afetivos"),
	SAUDE_MENTAL("Saúde mental"),
	SEXUALIDADE_IDENTIDADE_GENERO("Sexualidade e identidade de gênero"),
	SINDROME_PANICO("Síndrome do pânico"),
	SUICIDIO("Suicídio"),
	TDAH("TDAH (Transtorno de Déficit de Atenção e Hiperatividade)"),
	TESTE_PSICOLOGICO("Testes psicológicos"),
	TOC("TOC (Transtorno Obsessivo Compulsivo)"),
	TRANSTORNO_BIPOLAR("Transtorno bipolar"),
	TRANSTORNO_HUMOR("Transtorno de humor"),
	TRANSTORNO_ALIMENTAR("Transtornos alimentares"),
	TREINAMENTO_EMPRESARIAL("Treinamento empresarial"),
	VIOLENCIA_DOMESTICA("Violência doméstica"),
	VIOLENCIA_SEXUAL("Violência sexual");

	
	
	
	
	private String valor;
	
	
	public String getValor() {
		return valor;
	}
	

	private static final TemasTrabalhoEnum VALUES[] = TemasTrabalhoEnum.values();
	
	
	private TemasTrabalhoEnum(String valor) {
		this.valor = valor;
	}
	
	public static TemasTrabalhoEnum fromString(String valor) {
		for (TemasTrabalhoEnum item : VALUES) {
			if( item.valor.equals(valor) || item.toString().equals(valor)){
				return item;
			}
		}
		return null;
	}
	
}
