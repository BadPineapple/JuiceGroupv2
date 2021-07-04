package ilion.terrafos.animais.negocio;

public class MovimentacaoDeRebanhoVH {

	private String nome;
	
	private Integer estoqueInicial = 0;
	
	private Integer nascimento = 0;
	
	private Integer compra = 0;
	
	private Integer transferenciaEntrada = 0;
	
	private Integer morte = 0;
	
	private Integer venda = 0;
	
	private Integer transferenciaSaida = 0;
	
	private Integer ajustes = 0;
	
	private Integer estoqueFinal = 0;

	
	
	public MovimentacaoDeRebanhoVH() {
		super();
	}
	
	public MovimentacaoDeRebanhoVH(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getEstoqueInicial() {
		return estoqueInicial;
	}

	public void setEstoqueInicial(Integer estoqueInicial) {
		this.estoqueInicial = estoqueInicial;
	}

	public Integer getNascimento() {
		return nascimento;
	}

	public void setNascimento(Integer nascimento) {
		this.nascimento = nascimento;
	}

	public Integer getCompra() {
		return compra;
	}

	public void setCompra(Integer compra) {
		this.compra = compra;
	}

	public Integer getTransferenciaEntrada() {
		return transferenciaEntrada;
	}

	public void setTransferenciaEntrada(Integer transferenciaEntrada) {
		this.transferenciaEntrada = transferenciaEntrada;
	}

	public Integer getMorte() {
		return morte;
	}

	public void setMorte(Integer morte) {
		this.morte = morte;
	}

	public Integer getVenda() {
		return venda;
	}

	public void setVenda(Integer venda) {
		this.venda = venda;
	}

	public Integer getTransferenciaSaida() {
		return transferenciaSaida;
	}

	public void setTransferenciaSaida(Integer transferenciaSaida) {
		this.transferenciaSaida = transferenciaSaida;
	}

	public Integer getEstoqueFinal() {
		return estoqueFinal;
	}

	public void setEstoqueFinal(Integer estoqueFinal) {
		this.estoqueFinal = estoqueFinal;
	}

	public Integer getAjustes() {
		return ajustes;
	}

	public void setAjustes(Integer ajustes) {
		this.ajustes = ajustes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		MovimentacaoDeRebanhoVH other = (MovimentacaoDeRebanhoVH) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	
	
	@Override
	public String toString() {
		return "MovimentacaoDeRebanhoVH [nome=" + nome + ", estoqueInicial=" + estoqueInicial + ", nascimento="
				+ nascimento + ", compra=" + compra + ", transferenciaEntrada=" + transferenciaEntrada + ", morte="
				+ morte + ", venda=" + venda + ", transferenciaSaida=" + transferenciaSaida + ", ajustes=" + ajustes
				+ ", estoqueFinal=" + estoqueFinal + "]";
	}

	public void adicionarSaldo(String tipo, Integer saldo) {
		
		LoteTipo loteTipo = LoteTipo.fromString(tipo);
		
		switch (loteTipo) {
		case ESTOQUE_INICIAL:
			
			this.estoqueInicial += saldo;
			this.estoqueFinal += saldo;
			
			break;
		case NASCIMENTO:
			
			this.nascimento += saldo;
			this.estoqueFinal += saldo;
			
			break;
		case COMPRA:
			
			this.compra += saldo;
			this.estoqueFinal += saldo;
			
			break;
		case TRANSFERENCIA_ENTRADA:
			
			this.transferenciaEntrada += saldo;
			this.estoqueFinal += saldo;
			
			break;
		case MORTE:
			
			this.morte += saldo;
			this.estoqueFinal += saldo;
			
			break;
		case VENDA:
			
			this.venda += saldo;
			this.estoqueFinal += saldo;
			
			break;
		case TRANSFERENCIA_SAIDA:
			
			this.transferenciaSaida += saldo;
			this.estoqueFinal += saldo;
			
			break;
		case AJUSTES:
			
			this.ajustes += saldo;
			this.estoqueFinal += saldo;
			
			break;
		default:
			break;
		}
		
	}
	
}