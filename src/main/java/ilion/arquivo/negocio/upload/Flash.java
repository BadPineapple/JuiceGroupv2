package ilion.arquivo.negocio.upload;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ilion.arquivo.negocio.Arquivo;
import ilion.arquivo.negocio.ArquivoNegocio;

@Service
public class Flash implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nomeClasse;
	private String idObjeto;
	private String codigo;
	
	private String arquivo1;
	private String arquivo2;
	
	private Integer largura;
	private Integer altura;
	
	private Integer posicao;
	
	private List<MultipartFile> arquivos;
	
	public String getArquivo1() {
		return arquivo1;
	}

	public void setArquivo1(String arquivo1) {
		this.arquivo1 = arquivo1;
	}

	public String getArquivo2() {
		return arquivo2;
	}

	public void setArquivo2(String arquivo2) {
		this.arquivo2 = arquivo2;
	}

	public Integer getLargura() {
		return largura;
	}

	public void setLargura(Integer largura) {
		this.largura = largura;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}


	public String getNomeClasse() {
		return nomeClasse;
	}

	public void setNomeClasse(String nomeClasse) {
		this.nomeClasse = nomeClasse;
	}

	public String getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(String idObjeto) {
		this.idObjeto = idObjeto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public Flash clone() {
		Flash i = new Flash();
		
		i.setAltura(this.altura);
		i.setLargura(this.largura);
		
		i.setNomeClasse(this.nomeClasse);
		i.setIdObjeto(this.idObjeto);
		i.setCodigo(this.codigo);
		
		i.setPosicao(this.posicao);
		
		return i;
	}

	public Arquivo toArquivo() {
		Arquivo a = new Arquivo();

		a.setNomeClasse(this.nomeClasse);
		a.setIdObjeto(this.idObjeto);
		a.setCodigo(this.codigo);

		a.setLargura(this.largura);
		a.setAltura(this.altura);

		a.setArquivo1(this.arquivo1);
		a.setArquivo2(this.arquivo2);
		
		a.setPosicao(this.posicao);
		
		a.setLayout("lateral");
		
		a.setNaoPublicado(false);
		
		return a;
	}
	
	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
	
	@Autowired
	ArquivoNegocio arquivoNegocio;
	
	public void proximaPosicao() {
		if(this.getPosicao() == null) {
			Integer ultimaPosicao = arquivoNegocio.maxPosicao(this.getNomeClasse(), this.getIdObjeto().toString(), this.getCodigo());

			if(ultimaPosicao == null) {
				ultimaPosicao = 0;
			}
			
			this.setPosicao( ultimaPosicao );
		}
		
		this.setPosicao(this.getPosicao()+1);
	}

	public List<MultipartFile> getArquivos() {
		return arquivos;
	}

	public void setArquivos(List<MultipartFile> arquivos) {
		this.arquivos = arquivos;
	}
	
}