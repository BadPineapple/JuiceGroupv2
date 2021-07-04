package ilion.terrafos.cadastrospadroes.negocio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.Forrageira;

@Entity
@Table(name="terrafos_forrageira_padrao")
public class ForrageiraPadrao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="terrafos_forrageira_padrao_id_seq")
	@SequenceGenerator(name="terrafos_forrageira_padrao_id_seq", sequenceName="terrafos_forrageira_padrao_id_seq", allocationSize=1)
	private Long id;
	
	@Column(nullable = false)
	private String especie;
	
	@Column
	private Integer producao;

	@Column
	private Integer jan;
	
	@Column
	private Integer fev;
	
	@Column
	private Integer mar;
	
	@Column
	private Integer abr;
	
	@Column
	private Integer mai;
	
	@Column
	private Integer jun;
	
	@Column
	private Integer jul;
	
	@Column
	private Integer ago;
	
	@Column
	private Integer set;
	
	@Column
	private Integer out;
	
	@Column
	private Integer nov;
	
	@Column
	private Integer dez;
	
	@Column
	private Integer entrada;
	
	@Column
	private Integer saida;
	
	
	

	public ForrageiraPadrao() {
		super();
	}
	
	public ForrageiraPadrao(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public Integer getProducao() {
		return producao;
	}

	public void setProducao(Integer producao) {
		this.producao = producao;
	}

	public Integer getJan() {
		return jan;
	}

	public void setJan(Integer jan) {
		this.jan = jan;
	}

	public Integer getFev() {
		return fev;
	}

	public void setFev(Integer fev) {
		this.fev = fev;
	}

	public Integer getMar() {
		return mar;
	}

	public void setMar(Integer mar) {
		this.mar = mar;
	}

	public Integer getAbr() {
		return abr;
	}

	public void setAbr(Integer abr) {
		this.abr = abr;
	}

	public Integer getMai() {
		return mai;
	}

	public void setMai(Integer mai) {
		this.mai = mai;
	}

	public Integer getJun() {
		return jun;
	}

	public void setJun(Integer jun) {
		this.jun = jun;
	}

	public Integer getJul() {
		return jul;
	}

	public void setJul(Integer jul) {
		this.jul = jul;
	}

	public Integer getAgo() {
		return ago;
	}

	public void setAgo(Integer ago) {
		this.ago = ago;
	}

	public Integer getSet() {
		return set;
	}

	public void setSet(Integer set) {
		this.set = set;
	}

	public Integer getOut() {
		return out;
	}

	public void setOut(Integer out) {
		this.out = out;
	}

	public Integer getNov() {
		return nov;
	}

	public void setNov(Integer nov) {
		this.nov = nov;
	}

	public Integer getDez() {
		return dez;
	}

	public void setDez(Integer dez) {
		this.dez = dez;
	}

	public Integer getEntrada() {
		return entrada;
	}

	public void setEntrada(Integer entrada) {
		this.entrada = entrada;
	}

	public Integer getSaida() {
		return saida;
	}

	public void setSaida(Integer saida) {
		this.saida = saida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ForrageiraPadrao other = (ForrageiraPadrao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ForrageiraPadrao [id=" + id + ", especie=" + especie + ", producao=" + producao
				+ ", entrada=" + entrada + ", saida=" + saida + "]";
	}
	
	public Forrageira toForrageira(Fazenda fazenda) {
		
		Forrageira f = new Forrageira();
		f.setFazenda(fazenda);
		f.setAbr(abr);
		f.setAgo(ago);
		f.setDez(dez);
		f.setEntrada(entrada);
		f.setEspecie(especie);
		f.setFev(fev);
		f.setJan(jan);
		f.setJul(jul);
		f.setJun(jun);
		f.setMai(mai);
		f.setMar(mar);
		f.setNov(nov);
		f.setOut(out);
		f.setProducao(producao);
		f.setSaida(saida);
		f.setSet(set);
		
		return f;
	}
	
	

	
	
}
