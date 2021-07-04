<%@ include file="/ilionnet/taglibs.jsp" %>

<!-- Sidebar Starts -->

<div class="pmd-sidebar-overlay"></div>


<!-- Left sidebar -->

<aside class="pmd-sidebar sidebar-default pmd-sidebar-slide-push pmd-sidebar-left pmd-sidebar-open bg-fill-darkblue sidebar-with-icons"
       role="navigation">

 <ul class="nav pmd-sidebar-nav">


  <!-- User info -->

  <li class="dropdown pmd-dropdown pmd-user-info visible-xs visible-md visible-sm visible-lg">

   <a aria-expanded="false"
      data-toggle="dropdown"
      class="btn-user dropdown-toggle media"
      data-sidebar="true"
      aria-expandedhref="javascript:void(0);">
	<c:choose>
		<c:when test="${not empty fazendaSessao}">
		
		    <div class="media-body media-middle">Fazenda: ${fazendaSessao.nome}</div>
		
		    <div class="media-right media-middle"><i class="dic-more-vert dic"></i></div>
		
		</c:when>
		<c:otherwise>
					
			<div class="alert alert-danger" role="alert">
				<em>Nenhuma fazenda selecionada</em>					
			</div>
		
		</c:otherwise>
	</c:choose>
   </a>

   <ul class="dropdown-menu">

    <li><a href="<ilion:url/>ilionnet/terrafos/home">In&iacute;cio</a></li>
	
	<li><a href="<ilion:url/>ilionnet/terrafos/fazendas">Fazendas</a></li>
	
   </ul>

  </li><!-- End user info -->

  <c:if test="${not empty fazendaSessao}">
  <ilion:permissaoExiste permissoes="terrafos_cadastros">
  <li class="dropdown pmd-dropdown">

   <a id="cadastros"
      aria-expanded="false"
      data-toggle="dropdown"
      class="btn-user dropdown-toggle media"
      data-sidebar="true"
      href="javascript:void(0);">

    <i class="media-left media-middle material-icons">
     library_add
    </i>

    <span class="media-body">Cadastros</span>

    <div class="media-right media-bottom"><i class="dic-more-vert dic"></i></div>

   </a>

   <ul class="dropdown-menu">

    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/retiros">Retiros</a></li>
    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/pastos">Pastos</a></li>
    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/forrageiras">Forrageiras</a></li>
    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/categorias-animal">Categoria Animal</a></li>
    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/racas">Raças</a></li>
    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/produtos">Produtos</a></li>
    <li> &nbsp; </li>
    
   </ul>

  </li>
  </ilion:permissaoExiste>
  </c:if>
  
  <ilion:permissaoExiste permissoes="terrafos_cadastros_padroes">
  <li class="dropdown pmd-dropdown">

   <a id="cadastrosPadroes"
      aria-expanded="false"
      data-toggle="dropdown"
      class="btn-user dropdown-toggle media"
      data-sidebar="true"
      href="javascript:void(0);">

    <i class="media-left media-middle material-icons">
     library_add
    </i>

    <span class="media-body">Cadastros Padr&otilde;es</span>

    <div class="media-right media-bottom"><i class="dic-more-vert dic"></i></div>

   </a>

   <ul class="dropdown-menu">

    <li><a href="<ilion:url/>ilionnet/terrafos/cadastros-padroes/forrageiras">Forrageiras</a></li>
    <li><a href="<ilion:url/>ilionnet/terrafos/cadastros-padroes/categorias-animal">Categoria Animal</a></li>
    <li><a href="<ilion:url/>ilionnet/terrafos/cadastros-padroes/produtos">Produtos</a></li>
    <li> &nbsp; </li>
    
   </ul>

  </li>
  </ilion:permissaoExiste>

  <c:if test="${not empty fazendaSessao}">
  
  <ilion:permissaoExiste permissoes="terrafos_avaliacao_tecnica">
  <li class="dropdown pmd-dropdown">

   <a id="avaliacaoTecnica"
      aria-expanded="false"
      data-toggle="dropdown"
      class="btn-user dropdown-toggle media"
      data-sidebar="true"
      href="javascript:void(0);">

    <i class="media-left media-middle material-icons">
     edit
    </i>

    <span class="media-body">Avalia&ccedil;&atilde;o T&eacute;cnica</span>

    <div class="media-right media-bottom"><i class="dic-more-vert dic"></i></div>

   </a>

   <ul class="dropdown-menu">
    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/avaliacao-dos-pastos">Avalia&ccedil;&atilde;o dos Pastos</a></li>
    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/planos-de-acao">Planos de A&ccedil;&atilde;o</a></li>
    <li> &nbsp; </li>
    
   </ul>

  </li>
  </ilion:permissaoExiste>

  <ilion:permissaoExiste permissoes="terrafos_relatorios">
  <li class="dropdown pmd-dropdown">

   <a id="relatorios"
      aria-expanded="false"
      data-toggle="dropdown"
      class="btn-user dropdown-toggle media"
      data-sidebar="true"
      href="javascript:void(0);">

    <i class="media-left media-middle material-icons">
     stacked_bar_chart
    </i>

    <span class="media-body">Relatórios</span>

    <div class="media-right media-bottom"><i class="dic-more-vert dic"></i></div>

   </a>

   <ul class="dropdown-menu">
	<li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/relatorios/graficos">Resumo em Gráficos</a></li>
    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/relatorios/movimentacao-de-rebanho">Movimentação de Rebanho</a></li>
    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/relatorios/consumo-suplementacao">Consumo Suplementação</a></li>
    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/relatorios/log-de-acoes">Log de Ações</a></li>
    <li> &nbsp; </li>
    
   </ul>

  </li>
  </ilion:permissaoExiste>
  
  <ilion:permissaoExiste permissoes="terrafos_monitoramento">
  <li class="dropdown pmd-dropdown">

   <a id="monitoramento"
      aria-expanded="false"
      data-toggle="dropdown"
      class="btn-user dropdown-toggle media"
      data-sidebar="true"
      href="javascript:void(0);">

    <i class="media-left media-middle material-icons">
     map
    </i>

    <span class="media-body">Monitoramento</span>

    <div class="media-right media-bottom"><i class="dic-more-vert dic"></i></div>

   </a>

   <ul class="dropdown-menu">

    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/abastecimento-dos-cochos">Abastecimento dos Cochos</a></li>
    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/altura-das-forragens">Altura das Forragens</a></li>
    <li><a href="<ilion:url/>ilionnet/terrafos/fazendas/${fazendaSessao.id}/entreveros">Entreveros</a></li>
    <li> &nbsp; </li>
    
   </ul>

  </li>
  </ilion:permissaoExiste>
  
  </c:if>


<ilion:permissao tipo="usuario.sp">
  <li>

   <a class="pmd-ripple-effect" href="<ilion:url/>ilionnet/usuario">

    <i class="media-left media-middle">
     <svg version="1.1"
          x="0px"
          y="0px"
          width="19.83px"
          height="18px"
          viewBox="287.725 407.535 19.83 18"
          enable-background="new 287.725 407.535 19.83 18"
          xml:space="preserve">

                        <g>

                         <path fill="#044B2D" d="M307.555,407.535h-9.108v10.264h9.108V407.535z M287.725,407.535v6.232h9.109v-6.232H287.725z

M296.834,415.271h-9.109v10.264h9.109V415.271z M307.555,419.303h-9.108v6.232h9.108V419.303z"/>

                        </g>

                    </svg>
    </i>

    <span class="media-body">Admin</span>

   </a>

  </li>
  </ilion:permissao>

  <li>

   <a class="pmd-ripple-effect" href="<ilion:url/>ilionnet/logout">

    <i class="media-left media-middle">

     <svg version="1.1"
          id="Layer_1"
          x="0px"
          y="0px"
          width="18px"
          height="18px"
          viewBox="288.64 337.535 18 18"
          enable-background="new 288.64 337.535 18 18"
          xml:space="preserve">

                <path fill="#044B2D" d="M295.39,337.535v2.25h9v13.5h-9v2.25h11.25v-18H295.39z M297.64,342.035v3.375h-9v2.25h9v3.375l3.375-3.375

                    l1.125-1.125l-1.125-1.125L297.64,342.035z"/>

                </svg>
    </i>

    <span class="media-body">Sair</span>

   </a>

  </li>


 </ul>

</aside>
<!-- End Left sidebar -->

<!-- Sidebar Ends -->
