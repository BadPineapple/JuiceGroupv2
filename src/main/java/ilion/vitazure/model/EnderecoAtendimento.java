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

import ilion.vitazure.enumeradores.EstadoEnum;

@Entity
@Table(name = "enderecoAtendimento")
public class EnderecoAtendimento {
	
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO, generator = "enderecoAtendimento_id_seq")
	  @SequenceGenerator(name = "enderecoAtendimento_id_seq", sequenceName = "enderecoAtendimento_id_seq", allocationSize = 1)
	  private Long id;
	
	  private String cep;

	  private String logradouro;
	  
	  private String numero;
	  
	  private String bairro;
	  
	  private String cidade;
	  
	  @Enumerated(EnumType.STRING)
	  private EstadoEnum estado;
	  
	  private String complemento;
	  
	  private String linkGoogleMaps;
	
	  @ManyToOne
	  private Profissional profissional;
	  
	    public String getCep() {
			return cep;
		}

		public void setCep(String cep) {
			this.cep = cep;
		}

		public String getLogradouro() {
			return logradouro;
		}

		public void setLogradouro(String logradouro) {
			this.logradouro = logradouro;
		}

		public String getNumero() {
			return numero;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}

		public String getBairro() {
			return bairro;
		}

		public void setBairro(String bairro) {
			this.bairro = bairro;
		}

		public String getCidade() {
			return cidade;
		}

		public void setCidade(String cidade) {
			this.cidade = cidade;
		}

		public EstadoEnum getEstado() {
			if (estado == null) {
				estado = EstadoEnum.NAO_INFORMADO;
			}
			return estado;
		}

		public void setEstado(EstadoEnum estado) {
			this.estado = estado;
		}

		public String getComplemento() {
			return complemento;
		}

		public void setComplemento(String complemento) {
			this.complemento = complemento;
		}

		public String getLinkGoogleMaps() {
			return linkGoogleMaps;
		}

		public void setLinkGoogleMaps(String linkGoogleMaps) {
			this.linkGoogleMaps = linkGoogleMaps;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Profissional getProfissional() {
			return profissional;
		}

		public void setProfissional(Profissional profissional) {
			this.profissional = profissional;
		}
		
		@Override
		public String toString() {
			return linkGoogleMaps +".,"+ estado +".,"+logradouro +".,"+complemento +".,"+cep+".,"+cidade+".,"+bairro +".,"+ numero +".,"+ id;
		}
		
		
}
