package ilion.terrafos.animais.negocio;

import ilion.SpringApplicationContext;
import ilion.admin.negocio.Usuario;
import ilion.terrafos.cadastros.negocio.CategoriaAnimal;
import ilion.terrafos.cadastros.negocio.CategoriaAnimalNegocio;
import ilion.terrafos.cadastros.negocio.Pasto;
import ilion.terrafos.cadastros.negocio.Raca;
import ilion.terrafos.cadastros.negocio.Sexo;
import ilion.util.exceptions.ValidacaoException;

public class NascimentosDTO {
	
	private Long idPasto;
	
	private Long idRaca;
	
	private Integer qtd;
	
	private Sexo sexo;
	
	public Long getIdPasto() {
		return idPasto;
	}

	public void setIdPasto(Long idPasto) {
		this.idPasto = idPasto;
	}

	public Long getIdRaca() {
		return idRaca;
	}

	public void setIdRaca(Long idRaca) {
		this.idRaca = idRaca;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Lote toLote(Usuario usuario) {
		
		CategoriaAnimalNegocio categoriaAnimalNegocio = SpringApplicationContext.getBean(CategoriaAnimalNegocio.class);
		CategoriaAnimal categoriaAnimal = categoriaAnimalNegocio.consultarCategoriaParaNascimento(this.sexo);
		
		Lote lote = new Lote();
		
		lote.setCategoriaAnimal(categoriaAnimal);
		lote.setPasto(new Pasto(idPasto));
		lote.setValor(qtd);
		lote.setTipo(LoteTipo.NASCIMENTO);
		lote.setUsuario(usuario);
		lote.setRaca(new Raca(idRaca));
		
		return lote;
	}
	
	public void ehValido() {
		
		if( this.idPasto == null ) {
			throw new ValidacaoException("Pasto deve ser definido.");
		}
		
		if( this.idRaca == null ) {
			throw new ValidacaoException("Raça deve ser definida.");
		}
		
		if( this.qtd == null ) {
			throw new ValidacaoException("Qtd. deve ser definida.");
		}
		
		if( this.qtd <= 0 ) {
			throw new ValidacaoException("Qtd. deve ser maior que zero.");
		}
		
		if( this.sexo == null ) {
			throw new ValidacaoException("Sexo deve ser definido.");
		}
		
	}
	
}