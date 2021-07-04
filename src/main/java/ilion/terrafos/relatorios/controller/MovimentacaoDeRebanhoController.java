package ilion.terrafos.relatorios.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ilion.CustomErrorController;
import ilion.terrafos.animais.negocio.LoteNegocio;
import ilion.terrafos.animais.negocio.RelatorioMovimentacaoDeRebanhoVH;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.util.Uteis;
import ilion.util.contexto.autorizacao.UsuarioLogado;

@Controller
@UsuarioLogado
public class MovimentacaoDeRebanhoController extends CustomErrorController {

	static Logger logger = Logger.getLogger(MovimentacaoDeRebanhoController.class);
	
	@Autowired
	private LoteNegocio loteNegocio;
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/relatorios/movimentacao-de-rebanho")
    public String view(
    		@PathVariable Long idFazenda, 
    		@RequestParam(required = false) String dataInicio, 
    		@RequestParam(required = false) String dataFim,  
    		HttpServletRequest request) {
		
		Fazenda fazenda = (Fazenda) request.getSession().getAttribute("fazendaSessao");
		
		if( Uteis.ehNuloOuVazio( dataInicio ) && Uteis.ehNuloOuVazio(dataFim) ) {
			Date dtInicio = Uteis.inicioMes(new Date());
			String dtInicioParam = Uteis.formatarDataHora(dtInicio, "dd/MM/yyyy");
			
			Date dtFim = Uteis.fimMes(new Date());
			String dtFimParam = Uteis.formatarDataHora(dtFim, "dd/MM/yyyy");
			
			return "redirect:/ilionnet/terrafos/fazendas/"+idFazenda+"/relatorios/movimentacao-de-rebanho?dataInicio="+dtInicioParam+"&dataFim="+dtFimParam;
		}
		
		Date dtInicio = Uteis.converterDataHora(dataInicio, "dd/MM/yyyy");
		Date dtFim = Uteis.converterDataHora(dataFim, "dd/MM/yyyy");
		
		try {
			
			RelatorioMovimentacaoDeRebanhoVH relatorio = loteNegocio.relatorioMovimentacaoDeRebanho(fazenda, dtInicio, dtFim);
			request.setAttribute("relatorio", relatorio);
			
			return "/ilionnet2/terrafos/relatorios/movimentacao-de-rebanho";
		} catch (Exception e) {
			logger.error("", e);
			return "redirect:/ilionnet/terrafos/fazendas/"+idFazenda+"/relatorios/movimentacao-de-rebanho?m=erro&e="+e.getMessage();
		}
		
    }
	
}