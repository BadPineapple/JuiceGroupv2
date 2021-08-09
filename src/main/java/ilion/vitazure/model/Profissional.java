package ilion.vitazure.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import ilion.vitazure.enumeradores.BancoEnum;
import ilion.vitazure.enumeradores.DuracaoAtendimentoEnum;
import ilion.vitazure.enumeradores.EspecialidadesEnum;
import ilion.vitazure.enumeradores.TemasTrabalhoEnum;
import ilion.vitazure.enumeradores.TempoAntecendenciaEnum;
import ilion.vitazure.enumeradores.TipoContaEnum;
import ilion.vitazure.enumeradores.TipoProfissionalEnum;

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

	  private String dataInicioPlano;
	  
	  private String dataFimPlano;
	  
	  private String documentoCrpCrm;
	  
	  private String cadastroEpsi;

	  private String tituloProfissional;
	  
	  @Enumerated(EnumType.STRING)
	  private TipoProfissionalEnum tipoProfissional;
	  
	  @Enumerated(EnumType.STRING)
	  private EspecialidadesEnum especialidade;
	  
	  @Enumerated(EnumType.STRING)
	  private TemasTrabalhoEnum temasTrabalho;
	  
	  @Enumerated(EnumType.STRING)
	  private DuracaoAtendimentoEnum duracaoAtendimento;
	  
	  private String biografia;
	  
	  @Enumerated(EnumType.STRING)
	  private DuracaoAtendimentoEnum duracaoAtendimentoValor;
	  
	  @Enumerated(EnumType.STRING)
	  private TempoAntecendenciaEnum tempoAntecendencia;
	  
	  private BigDecimal valorConsultaOnline;

	  private BigDecimal valorConsultaPresencial;
	  
	  @Enumerated(EnumType.STRING)
	  private TipoContaEnum tipoConta;
	  
	  @Enumerated(EnumType.STRING)
	  private BancoEnum banco;
	  
	  private String agencia;

	  private String conta;
	  
	  private String digitoVerificador;

	  private String nomeFavorecido;

	  private Integer idConta;

	  private String idRecebedor;
	  
	  private Boolean convenio20;
	  
	  private Boolean convenio30;
	  
	  private Boolean convenio40;
	  
	  private Boolean convenio50;
	  
	  private Boolean convenio60;
	  
	  private Boolean pacote2com5Desconto;
	  
	  private Boolean pacote3com10Desconto;
	  
	  private Boolean pacote4com15Desconto;

	  private Boolean primeiraConsultaCortesia;
	  
	  private Integer quantidadeConsultaCortesiaMes;
	  
	  private Boolean atendimentoPorLibras;
	  
	  private Boolean habilitarDesconto40;
	  
	  private Integer quantidadeConsultaDesconto40Mes;
	  
	  private Boolean avisoFerias;

	  private Boolean adolescentes;
	  
	  private Boolean adultos;
	  
	  private Boolean casais;
	  
	  private Boolean idosos;
	  
	  private String tokenTransacaoPlano;
	  
	  private Integer idTransacao;
	  
	  @Transient
	  List<Date> datasPossivelAgendamento;
	  
	  @Transient
	  List<HorarioPossivelAtendimento> horarioPossivelAtendimento;
	  
	  private String dataInicioAvisoFerias;

	  private String dataFimAvisoFerias;
	  

	public Long getId() {
		if (id == null) {
			id = 0l;
		}
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		if (pessoa == null) {
			pessoa = new Pessoa();
		}
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getPlano() {
		return plano;
	}

	public void setPlano(String plano) {
		this.plano = plano;
	}

	public String getDataInicioPlano() {
		return dataInicioPlano;
	}

	public void setDataInicioPlano(String dataInicioPlano) {
		this.dataInicioPlano = dataInicioPlano;
	}

	public String getDataFimPlano() {
		return dataFimPlano;
	}

	public void setDataFimPlano(String dataFimPlano) {
		this.dataFimPlano = dataFimPlano;
	}

	public String getDocumentoCrpCrm() {
		return documentoCrpCrm;
	}

	public void setDocumentoCrpCrm(String documentoCrpCrm) {
		this.documentoCrpCrm = documentoCrpCrm;
	}

	public String getCadastroEpsi() {
		return cadastroEpsi;
	}

	public void setCadastroEpsi(String cadastroEpsi) {
		this.cadastroEpsi = cadastroEpsi;
	}

	public String getTituloProfissional() {
		return tituloProfissional;
	}

	public void setTituloProfissional(String tituloProfissional) {
		this.tituloProfissional = tituloProfissional;
	}

	public TipoProfissionalEnum getTipoProfissional() {
		if (tipoProfissional == null) {
			tipoProfissional = TipoProfissionalEnum.NAO_INFORMADO;
		}
		return tipoProfissional;
	}

	public void setTipoProfissional(TipoProfissionalEnum tipoProfissional) {
		this.tipoProfissional = tipoProfissional;
	}

	public EspecialidadesEnum getEspecialidade() {
		if (especialidade == null) {
			especialidade = EspecialidadesEnum.NAO_INFORMADO;
		}
		return especialidade;
	}

	public void setEspecialidade(EspecialidadesEnum especialidade) {
		this.especialidade = especialidade;
	}

	public TemasTrabalhoEnum getTemasTrabalho() {
		if (temasTrabalho == null) {
			temasTrabalho = TemasTrabalhoEnum.NAO_INFORMADO;
		}
		return temasTrabalho;
	}

	public void setTemasTrabalho(TemasTrabalhoEnum temasTrabalho) {
		this.temasTrabalho = temasTrabalho;
	}

	public DuracaoAtendimentoEnum getDuracaoAtendimento() {
		if (duracaoAtendimento == null) {
			duracaoAtendimento = DuracaoAtendimentoEnum.NAO_INFORMADO;
		}
		return duracaoAtendimento;
	}

	public void setDuracaoAtendimento(DuracaoAtendimentoEnum duracaoAtendimento) {
		this.duracaoAtendimento = duracaoAtendimento;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}


	public TipoContaEnum getTipoConta() {
		if (tipoConta == null) {
			tipoConta = TipoContaEnum.NAO_INFORMADO;
		}
		return tipoConta;
	}

	public void setTipoConta(TipoContaEnum tipoConta) {
		this.tipoConta = tipoConta;
	}

	public BancoEnum getBanco() {
		if (banco == null) {
			banco = BancoEnum.NAO_INFORMADO;
		}
		return banco;
	}

	public void setBanco(BancoEnum banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getDigitoVerificador() {
		return digitoVerificador;
	}

	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}

	public String getNomeFavorecido() {
		return nomeFavorecido;
	}

	public void setNomeFavorecido(String nomeFavorecido) {
		this.nomeFavorecido = nomeFavorecido;
	}

	public Integer getIdConta() {return idConta;}

	public void setIdConta(Integer idConta) {this.idConta = idConta;}

	public String getIdRecebedor() {return idRecebedor;}

	public void setIdRecebedor(String idRecebedor) {this.idRecebedor = idRecebedor;}

	public Boolean getConvenio20() {
		return convenio20;
	}

	public void setConvenio20(Boolean convenio20) {
		this.convenio20 = convenio20;
	}

	public Boolean getConvenio30() {
		return convenio30;
	}

	public void setConvenio30(Boolean convenio30) {
		this.convenio30 = convenio30;
	}

	public Boolean getConvenio40() {
		return convenio40;
	}

	public void setConvenio40(Boolean convenio40) {
		this.convenio40 = convenio40;
	}

	public Boolean getConvenio50() {
		return convenio50;
	}

	public void setConvenio50(Boolean convenio50) {
		this.convenio50 = convenio50;
	}

	public Boolean getConvenio60() {
		return convenio60;
	}

	public void setConvenio60(Boolean convenio60) {
		this.convenio60 = convenio60;
	}

	public Boolean getPacote2com5Desconto() {
		return pacote2com5Desconto;
	}

	public void setPacote2com5Desconto(Boolean pacote2com5Desconto) {
		this.pacote2com5Desconto = pacote2com5Desconto;
	}

	public Boolean getPacote3com10Desconto() {
		return pacote3com10Desconto;
	}

	public void setPacote3com10Desconto(Boolean pacote3com10Desconto) {
		this.pacote3com10Desconto = pacote3com10Desconto;
	}

	public Boolean getPacote4com15Desconto() {
		return pacote4com15Desconto;
	}

	public void setPacote4com15Desconto(Boolean pacote4com15Desconto) {
		this.pacote4com15Desconto = pacote4com15Desconto;
	}

	public Boolean getPrimeiraConsultaCortesia() {
		return primeiraConsultaCortesia;
	}

	public void setPrimeiraConsultaCortesia(Boolean primeiraConsultaCortesia) {
		this.primeiraConsultaCortesia = primeiraConsultaCortesia;
	}

	public Integer getQuantidadeConsultaCortesiaMes() {
		return quantidadeConsultaCortesiaMes;
	}

	public void setQuantidadeConsultaCortesiaMes(Integer quantidadeConsultaCortesiaMes) {
		this.quantidadeConsultaCortesiaMes = quantidadeConsultaCortesiaMes;
	}

	public Boolean getAtendimentoPorLibras() {
		return atendimentoPorLibras;
	}

	public void setAtendimentoPorLibras(Boolean atendimentoPorLibras) {
		this.atendimentoPorLibras = atendimentoPorLibras;
	}

	public Boolean getHabilitarDesconto40() {
		return habilitarDesconto40;
	}

	public void setHabilitarDesconto40(Boolean habilitarDesconto40) {
		this.habilitarDesconto40 = habilitarDesconto40;
	}

	public Integer getQuantidadeConsultaDesconto40Mes() {
		return quantidadeConsultaDesconto40Mes;
	}

	public void setQuantidadeConsultaDesconto40Mes(Integer quantidadeConsultaDesconto40Mes) {
		this.quantidadeConsultaDesconto40Mes = quantidadeConsultaDesconto40Mes;
	}

	public Boolean getAvisoFerias() {
		return avisoFerias;
	}

	public void setAvisoFerias(Boolean avisoFerias) {
		this.avisoFerias = avisoFerias;
	}

	public DuracaoAtendimentoEnum getDuracaoAtendimentoValor() {
		if (duracaoAtendimentoValor == null) {
			duracaoAtendimentoValor = DuracaoAtendimentoEnum.NAO_INFORMADO;
		}
		return duracaoAtendimentoValor;
	}

	public void setDuracaoAtendimentoValor(DuracaoAtendimentoEnum duracaoAtendimentoValor) {
		this.duracaoAtendimentoValor = duracaoAtendimentoValor;
	}

	public TempoAntecendenciaEnum getTempoAntecendencia() {
		if (tempoAntecendencia == null) {
			tempoAntecendencia = TempoAntecendenciaEnum.NAO_INFORMADO;
		}
		return tempoAntecendencia;
	}

	public void setTempoAntecendencia(TempoAntecendenciaEnum tempoAntecendencia) {
		this.tempoAntecendencia = tempoAntecendencia;
	}

	public BigDecimal getValorConsultaOnline() {
		if (valorConsultaOnline == null) {
			valorConsultaOnline = BigDecimal.ZERO;
		}
		return valorConsultaOnline;
	}

	public void setValorConsultaOnline(BigDecimal valorConsultaOnline) {
		this.valorConsultaOnline = valorConsultaOnline;
	}

	public BigDecimal getValorConsultaPresencial() {
		if (valorConsultaPresencial == null) {
			valorConsultaPresencial = BigDecimal.ZERO;
		}
		return valorConsultaPresencial;
	}

	public void setValorConsultaPresencial(BigDecimal valorConsultaPresencial) {
		this.valorConsultaPresencial = valorConsultaPresencial;
	}

	public List<Date> getDatasPossivelAgendamento() {
		if (datasPossivelAgendamento == null) {
			datasPossivelAgendamento = new ArrayList<Date>();
		}
		return datasPossivelAgendamento;
	}

	public void setDatasPossivelAgendamento(List<Date> datasPossivelAgendamento) {
		this.datasPossivelAgendamento = datasPossivelAgendamento;
	}

	public List<HorarioPossivelAtendimento> getHorarioPossivelAtendimento() {
		if (horarioPossivelAtendimento == null) {
			horarioPossivelAtendimento = new ArrayList<HorarioPossivelAtendimento>();
		}
		return horarioPossivelAtendimento;
	}

	public void setHorarioPossivelAtendimento(List<HorarioPossivelAtendimento> horarioPossivelAtendimento) {
		this.horarioPossivelAtendimento = horarioPossivelAtendimento;
	}

	public Boolean getAdolescentes() {
		return adolescentes;
	}

	public void setAdolescentes(Boolean adolescentes) {
		this.adolescentes = adolescentes;
	}

	public Boolean getAdultos() {
		return adultos;
	}

	public void setAdultos(Boolean adultos) {
		this.adultos = adultos;
	}

	public Boolean getCasais() {
		return casais;
	}

	public void setCasais(Boolean casais) {
		this.casais = casais;
	}

	public Boolean getIdosos() {
		return idosos;
	}

	public void setIdosos(Boolean idosos) {
		this.idosos = idosos;
	}
	  
	public int getQuantidadesDiasVencimentoPlano() {
		return getPlano().equals("plano_mensal") ? 30 : getPlano().equals("plano_semestral") ? 180 :  365 ;
	}

	public String getTokenTransacaoPlano() {
		return tokenTransacaoPlano;
	}

	public void setTokenTransacaoPlano(String tokenTransacaoPlano) {
		this.tokenTransacaoPlano = tokenTransacaoPlano;
	}

	public Integer getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(Integer idTransacao) {
		this.idTransacao = idTransacao;
	}
	 
	public String getBiografiaApresentar() {
		if (getBiografia().length() >= 200) {
			String retorno = getBiografia().substring(0, 200);
			return retorno.concat(" ...");
		}
		return getBiografia();
	}

	public String getDataInicioAvisoFerias() {
		return dataInicioAvisoFerias;
	}

	public void setDataInicioAvisoFerias(String dataInicioAvisoFerias) {
		this.dataInicioAvisoFerias = dataInicioAvisoFerias;
	}

	public String getDataFimAvisoFerias() {
		return dataFimAvisoFerias;
	}

	public void setDataFimAvisoFerias(String dataFimAvisoFerias) {
		this.dataFimAvisoFerias = dataFimAvisoFerias;
	}
	
	public String getPlanoApresentar() {
	  return getPlano().equals("plano_mensal") ? "Mensal" : getPlano().equals("plano_semestral") ? "Semestral" :  "Anual";
	}
}
