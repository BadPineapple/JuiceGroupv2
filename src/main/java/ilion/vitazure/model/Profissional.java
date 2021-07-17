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

import ilion.vitazure.enumeradores.BancoEnum;
import ilion.vitazure.enumeradores.DuracaoAtendimentoEnum;
import ilion.vitazure.enumeradores.EspecialidadesEnum;
import ilion.vitazure.enumeradores.EstadoEnum;
import ilion.vitazure.enumeradores.TemasTrabalhoEnum;
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
	  
	  private Date dataInicioPlano;
	  
	  private Date dataFimPlano;
	  
	  private String documentoCrpCrm;
	  
	  private String cadastroEpsi;

	  private String tituloProfissional;
	  
	  private TipoProfissionalEnum tipoProfissional;
	  
	  private EspecialidadesEnum especialidade;
	  
	  private TemasTrabalhoEnum temasTrabalho;
	  
	  private DuracaoAtendimentoEnum duracaoAtendimento;
	  
	  private String biografia;
	  
	  private String cep;

	  private String logradouro;
	  
	  private String numero;
	  
	  private String bairro;
	  
	  private String cidade;
	  
	  private EstadoEnum estado;
	  
	  private String complemento;
	  
	  private String linkGoogleMaps;
	  
	  private TipoContaEnum tipoConta;
	  
	  private BancoEnum banco;
	  
	  private String agencia;

	  private String conta;
	  
	  private String digitoVerificador;

	  private String nomeFavorecido;
	  
	  
	  private Boolean convenio20;
	  
	  private Boolean convenio30;
	  
	  private Boolean convenio40;
	  
	  private Boolean convenio50;
	  
	  private Boolean convenio60;
	  
	  private Boolean pacote2com30Desconto;
	  
	  private Boolean pacote3com40Desconto;
	  
	  private Boolean pacote4com50Desconto;

	  private Boolean primeiraConsultaCortesia;
	  
	  private Integer quantidadeConsultaCortesiaMes;
	  
	  private Boolean atendimentoPorLibras;
	  
	  private Boolean habilitarDesconto40;
	  
	  private Integer quantidadeConsultaDesconto40Mes;
	  
	  private Boolean avisoFerias;
}
