package ilion.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ilion.CustomRestErrorController;
import ilion.admin.negocio.Cidade;
import ilion.admin.negocio.CidadeNegocio;
import ilion.admin.negocio.UF;
import ilion.util.contexto.autorizacao.AcessoLivre;

@Controller
@RequestMapping(value="/api/cidades")
@AcessoLivre
public class CidadeSiteController extends CustomRestErrorController {

	static Logger logger = Logger.getLogger(CidadeSiteController.class);

	@Autowired
	private CidadeNegocio cidadeNegocio;

	@RequestMapping(value="/{uf}")
	public @ResponseBody Object cidades(@PathVariable UF uf, HttpServletRequest request, HttpServletResponse response) {

		List<Cidade> cidades = cidadeNegocio.listarCidades(uf);

		return cidades;
	}

	@RequestMapping(value="/consultar-por-id/{id}")
	public @ResponseBody Object cidade(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {

		Cidade cidade = cidadeNegocio.consultar(id);

		return cidade;
	}
}
