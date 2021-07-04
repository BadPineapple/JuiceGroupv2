package ilion.terrafos.monitoramento.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ilion.CustomErrorController;
import ilion.terrafos.cadastros.negocio.Fazenda;
import ilion.terrafos.cadastros.negocio.FazendaPastosNoMapaVH;
import ilion.terrafos.entrevero.negocio.EntreveroNegocio;
import ilion.terrafos.forragens.negocio.MedicaoForragemNegocio;
import ilion.terrafos.suplementacao.negocio.SuplementacaoNegocio;
import ilion.util.contexto.autorizacao.UsuarioLogado;
import ilion.util.json.APIResponse;

@Controller
@UsuarioLogado
public class MonitoramentoController extends CustomErrorController {

	static Logger logger = Logger.getLogger(MonitoramentoController.class);
	
	@Autowired
	private MedicaoForragemNegocio medicaoForragemNegocio;
	
	@Autowired
	private EntreveroNegocio entreveroNegocio;

	@Autowired
	private SuplementacaoNegocio suplementacaoNegocio;
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/altura-das-forragens")
    public String alturaDasForragensView(HttpServletRequest request) {
		
        return "/ilionnet2/terrafos/monitoramento/altura-das-forragens";
    }
	
	@GetMapping("/ilionnet/terrafos/monitoramento/altura-das-forragens")
	@ResponseBody
	public ResponseEntity<APIResponse> alturaDasForragens(HttpServletRequest request) {
		
		Fazenda fazenda = (Fazenda) request.getSession().getAttribute("fazendaSessao");
		
		FazendaPastosNoMapaVH fazendaPastosNoMapaVH = medicaoForragemNegocio.monitoramento(fazenda);
		
		return new ResponseEntity<APIResponse>(APIResponse.success(fazendaPastosNoMapaVH), HttpStatus.OK);
	}
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/entreveros")
	public String entreverosView(HttpServletRequest request) {
		
		return "/ilionnet2/terrafos/monitoramento/entreveros";
	}
	
	@GetMapping("/ilionnet/terrafos/monitoramento/entreveros")
	@ResponseBody
	public ResponseEntity<APIResponse> entreveros(HttpServletRequest request) {
		
		Fazenda fazenda = (Fazenda) request.getSession().getAttribute("fazendaSessao");
		
		FazendaPastosNoMapaVH fazendaPastosNoMapaVH = entreveroNegocio.monitoramento(fazenda);
		
		return new ResponseEntity<APIResponse>(APIResponse.success(fazendaPastosNoMapaVH), HttpStatus.OK);
	}
	
	@GetMapping("/ilionnet/terrafos/fazendas/{idFazenda}/abastecimento-dos-cochos")
	public String suplementacaoView(HttpServletRequest request) {
		
		return "/ilionnet2/terrafos/monitoramento/abastecimento-dos-cochos";
	}
    
	@GetMapping("/ilionnet/terrafos/monitoramento/abastecimento-dos-cochos")
	@ResponseBody
	public ResponseEntity<APIResponse> suplementacao(HttpServletRequest request) {
		
		Fazenda fazenda = (Fazenda) request.getSession().getAttribute("fazendaSessao");
		
		FazendaPastosNoMapaVH fazendaPastosNoMapaVH = suplementacaoNegocio.monitoramento(fazenda);
		
		return new ResponseEntity<APIResponse>(APIResponse.success(fazendaPastosNoMapaVH), HttpStatus.OK);
	}
}