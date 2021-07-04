package ilion.terrafos.animais.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RelatorioMovimentacaoDeRebanhoVH {

	private List<MovimentacaoDeRebanhoVH> linhas = new ArrayList<>();
	
	private MovimentacaoDeRebanhoVH total = new MovimentacaoDeRebanhoVH();

	public List<MovimentacaoDeRebanhoVH> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<MovimentacaoDeRebanhoVH> linhas) {
		this.linhas = linhas;
	}

	public MovimentacaoDeRebanhoVH getTotal() {
		return total;
	}

	public void setTotal(MovimentacaoDeRebanhoVH total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "RelatorioMovimentacaoDeRebanhoVH [linhas=" + linhas + ", total=" + total + "]";
	}

	public static RelatorioMovimentacaoDeRebanhoVH from(List<Map<String, Object>> registros) {
		
		RelatorioMovimentacaoDeRebanhoVH r = new RelatorioMovimentacaoDeRebanhoVH();
		
		for (Map<String, Object> map : registros) {
			String categoria_animal = (String) map.get("categoria_animal");
			String tipo = (String) map.get("tipo");
			Integer saldo = ((Number) map.get("saldo")).intValue();
			
			MovimentacaoDeRebanhoVH movimento = r.obterMovimentacaoDeRebanhoVH(categoria_animal);
			movimento.adicionarSaldo(tipo, saldo);
			
		}
		
		r.calcularTotais();
		
		return r;
	}

	private void calcularTotais() {
		
		for (MovimentacaoDeRebanhoVH m : linhas) {
			
			this.total.adicionarSaldo(LoteTipo.ESTOQUE_INICIAL.name(), m.getEstoqueInicial());
			this.total.adicionarSaldo(LoteTipo.NASCIMENTO.name(), m.getNascimento());
			this.total.adicionarSaldo(LoteTipo.COMPRA.name(), m.getCompra());
			this.total.adicionarSaldo(LoteTipo.TRANSFERENCIA_ENTRADA.name(), m.getTransferenciaEntrada());
			this.total.adicionarSaldo(LoteTipo.VENDA.name(), m.getVenda());
			this.total.adicionarSaldo(LoteTipo.MORTE.name(), m.getMorte());
			this.total.adicionarSaldo(LoteTipo.TRANSFERENCIA_SAIDA.name(), m.getTransferenciaSaida());
			this.total.adicionarSaldo(LoteTipo.AJUSTES.name(), m.getAjustes());
			
		}
		
	}

	private MovimentacaoDeRebanhoVH obterMovimentacaoDeRebanhoVH(String categoria_animal) {
		
		MovimentacaoDeRebanhoVH movimentacao = new MovimentacaoDeRebanhoVH(categoria_animal);
		
		if( this.linhas.contains(movimentacao) ) {
			Integer index = this.linhas.indexOf(movimentacao);
			return this.linhas.get(index);
		}
		
		this.linhas.add(movimentacao);
		
		return movimentacao;
	}
	
}