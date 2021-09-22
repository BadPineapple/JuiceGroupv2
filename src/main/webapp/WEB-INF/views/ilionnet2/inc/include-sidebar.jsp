<%@ include file="/ilionnet/taglibs.jsp" %>
<link rel="stylesheet" href="../../assets/css/all.css">
<div class="pmd-sidebar-overlay"></div>

<aside class="pmd-sidebar sidebar-default pmd-sidebar-slide-push pmd-sidebar-left pmd-sidebar-open bg-fill-darkblue sidebar-with-icons"
       role="navigation">

 <ul class="nav pmd-sidebar-nav">

<li>

   <a class="pmd-ripple-effect" href="<ilion:url/>agenda">

    <i class="media-left media-middle">
     <i class="far fa-calendar-alt" style="font-size: 25px;"></i>
    </i>

    <span class="media-body">Agendamentos</span>

   </a>

  </li>  
<li>

   <a class="pmd-ripple-effect" href="<ilion:url/>cliente">

    <i class="media-left media-middle">
     <i class="far fa-file-alt" style="font-size: 25px;"></i>
    </i>

    <span class="media-body">Cliente</span>

   </a>

</li>  

<li>

   <a class="pmd-ripple-effect" href="<ilion:url/>movimentacoes">

    <i class="media-left media-middle">
     <i class="far fa-file-alt" style="font-size: 25px;"></i>
    </i>

    <span class="media-body">Movimentações</span>

   </a>

</li> 
  
<li>

   <a class="pmd-ripple-effect" href="<ilion:url/>profissional">

    <i class="media-left media-middle">
      <i class="fas fa-user-nurse" style="font-size: 25px;"></i>
    </i>
    <span class="media-body">Profissional</span>

   </a>

  </li>
  
 <li>

   <a class="pmd-ripple-effect" href="<ilion:url/>importarFuncionario">

    <i class="media-left media-middle">
      <i class="fas fa-file-upload" style="font-size: 25px;"></i>
    </i>
    <span class="media-body">Importar Funcionário</span>

   </a>

  </li> 


<ilion:permissao tipo="usuario.sp">
  <li>

   <a class="pmd-ripple-effect" href="<ilion:url/>ilionnet/usuario">

    <i class="media-left media-middle">
      <i class="fas fa-users-cog" style="font-size: 25px;"></i>
    </i>
    <span class="media-body">Admin</span>
   </a>

  </li>
  </ilion:permissao>

  <li>

   <a class="pmd-ripple-effect" href="<ilion:url/>ilionnet/logout">

    <i class="media-left media-middle">
     <i class="fas fa-sign-out" style="font-size: 25px;"></i>
    </i>

    <span class="media-body">Sair</span>

   </a>

  </li>


 </ul>

</aside>
<!-- End Left sidebar -->

<!-- Sidebar Ends -->
