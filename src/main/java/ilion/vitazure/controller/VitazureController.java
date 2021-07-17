package ilion.vitazure.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ilion.util.contexto.autorizacao.PessoaLogada;
import ilion.vitazure.model.Pessoa;

@Controller
@SessionAttributes("usuario")
@PessoaLogada
public class VitazureController {

	@GetMapping("/vitazure/informacoes-perfil")
	public String carregar(ModelMap modelMap, HttpServletRequest request) {
		Pessoa pessoa = (Pessoa) request.getSession().getAttribute("pessoaSessao");
		modelMap.addAttribute("pessoa", pessoa);
		if (pessoa.getCliente() && pessoa.getCpf().equals("")) {
			return "/ilionnet2/vitazure/completar-cadastro";
		}else if(pessoa.getCliente() && !pessoa.getCpf().equals("")) {
			return "/ilionnet2/vitazure/painel-do-cliente";
		}else if (pessoa.getPsicologo() && pessoa.getCpf().equals("")) {
			return "/ilionnet2/vitazure/completar-cadastro";
		}else {
			return "/ilionnet2/vitazure/informacoes-perfil";
		}
	}
	@RequestMapping("/deslogar")
	public String deslogar(HttpServletRequest request) {
		request.getSession().removeAttribute("pessoaSessao");
		return "redirect:/home";
	}
	
}
