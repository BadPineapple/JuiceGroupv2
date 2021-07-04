package ilion.terrafos.cadastros.negocio;

import java.util.List;

public class AvaliacaoDosPastos {

	private List<Pasto> pastos;
	
	private Float lotacaoMediaTotalJan = 0f;
	
	private Float lotacaoMediaTotalFev = 0f;
	
	private Float lotacaoMediaTotalMar = 0f;
	
	private Float lotacaoMediaTotalAbr = 0f;
	
	private Float lotacaoMediaTotalMai = 0f;
	
	private Float lotacaoMediaTotalJun = 0f;
	
	private Float lotacaoMediaTotalJul = 0f;
	
	private Float lotacaoMediaTotalAgo = 0f;
	
	private Float lotacaoMediaTotalSet = 0f;
	
	private Float lotacaoMediaTotalOut = 0f;
	
	private Float lotacaoMediaTotalNov = 0f;
	
	private Float lotacaoMediaTotalDez = 0f;
	
	public List<Pasto> getPastos() {
		return pastos;
	}

	public void setPastos(List<Pasto> pastos) {
		this.pastos = pastos;
	}
	
	

	public Float getLotacaoMediaTotalJan() {
		return lotacaoMediaTotalJan;
	}

	public void setLotacaoMediaTotalJan(Float lotacaoMediaTotalJan) {
		this.lotacaoMediaTotalJan = lotacaoMediaTotalJan;
	}

	public Float getLotacaoMediaTotalFev() {
		return lotacaoMediaTotalFev;
	}

	public void setLotacaoMediaTotalFev(Float lotacaoMediaTotalFev) {
		this.lotacaoMediaTotalFev = lotacaoMediaTotalFev;
	}

	public Float getLotacaoMediaTotalMar() {
		return lotacaoMediaTotalMar;
	}

	public void setLotacaoMediaTotalMar(Float lotacaoMediaTotalMar) {
		this.lotacaoMediaTotalMar = lotacaoMediaTotalMar;
	}

	public Float getLotacaoMediaTotalAbr() {
		return lotacaoMediaTotalAbr;
	}

	public void setLotacaoMediaTotalAbr(Float lotacaoMediaTotalAbr) {
		this.lotacaoMediaTotalAbr = lotacaoMediaTotalAbr;
	}

	public Float getLotacaoMediaTotalMai() {
		return lotacaoMediaTotalMai;
	}

	public void setLotacaoMediaTotalMai(Float lotacaoMediaTotalMai) {
		this.lotacaoMediaTotalMai = lotacaoMediaTotalMai;
	}

	public Float getLotacaoMediaTotalJun() {
		return lotacaoMediaTotalJun;
	}

	public void setLotacaoMediaTotalJun(Float lotacaoMediaTotalJun) {
		this.lotacaoMediaTotalJun = lotacaoMediaTotalJun;
	}

	public Float getLotacaoMediaTotalJul() {
		return lotacaoMediaTotalJul;
	}

	public void setLotacaoMediaTotalJul(Float lotacaoMediaTotalJul) {
		this.lotacaoMediaTotalJul = lotacaoMediaTotalJul;
	}

	public Float getLotacaoMediaTotalAgo() {
		return lotacaoMediaTotalAgo;
	}

	public void setLotacaoMediaTotalAgo(Float lotacaoMediaTotalAgo) {
		this.lotacaoMediaTotalAgo = lotacaoMediaTotalAgo;
	}

	public Float getLotacaoMediaTotalSet() {
		return lotacaoMediaTotalSet;
	}

	public void setLotacaoMediaTotalSet(Float lotacaoMediaTotalSet) {
		this.lotacaoMediaTotalSet = lotacaoMediaTotalSet;
	}

	public Float getLotacaoMediaTotalOut() {
		return lotacaoMediaTotalOut;
	}

	public void setLotacaoMediaTotalOut(Float lotacaoMediaTotalOut) {
		this.lotacaoMediaTotalOut = lotacaoMediaTotalOut;
	}

	public Float getLotacaoMediaTotalNov() {
		return lotacaoMediaTotalNov;
	}

	public void setLotacaoMediaTotalNov(Float lotacaoMediaTotalNov) {
		this.lotacaoMediaTotalNov = lotacaoMediaTotalNov;
	}

	public Float getLotacaoMediaTotalDez() {
		return lotacaoMediaTotalDez;
	}

	public void setLotacaoMediaTotalDez(Float lotacaoMediaTotalDez) {
		this.lotacaoMediaTotalDez = lotacaoMediaTotalDez;
	}

	@Override
	public String toString() {
		return "AvaliacaoDosPastos [pastos=" + pastos + "]";
	}

	public void somarLotacaoMediaTotais() {
		
		for (Pasto pasto : pastos) {
			
			this.lotacaoMediaTotalJan += pasto.getLotacaoMediaJan();
			this.lotacaoMediaTotalFev += pasto.getLotacaoMediaFev();
			this.lotacaoMediaTotalMar += pasto.getLotacaoMediaMar();
			this.lotacaoMediaTotalAbr += pasto.getLotacaoMediaAbr();
			this.lotacaoMediaTotalMai += pasto.getLotacaoMediaMai();
			this.lotacaoMediaTotalJun += pasto.getLotacaoMediaJun();
			this.lotacaoMediaTotalJul += pasto.getLotacaoMediaJul();
			this.lotacaoMediaTotalAgo += pasto.getLotacaoMediaAgo();
			this.lotacaoMediaTotalSet += pasto.getLotacaoMediaSet();
			this.lotacaoMediaTotalOut += pasto.getLotacaoMediaOut();
			this.lotacaoMediaTotalNov += pasto.getLotacaoMediaNov();
			this.lotacaoMediaTotalDez += pasto.getLotacaoMediaDez();
			
		}
		
	}
	
}