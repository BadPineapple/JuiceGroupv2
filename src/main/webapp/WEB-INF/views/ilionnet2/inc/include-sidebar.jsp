<%@ include file="/ilionnet/taglibs.jsp" %>
<link rel="stylesheet" href="../../assets/css/all.css">
<div class="pmd-sidebar-overlay"></div>

<aside class="pmd-sidebar sidebar-default pmd-sidebar-slide-push pmd-sidebar-left pmd-sidebar-open bg-fill-darkblue sidebar-with-icons"
       role="navigation">

 <ul class="nav pmd-sidebar-nav">

<li>

   <a class="pmd-ripple-effect" href="<ilion:url/>cliente">

    <i class="media-left media-middle">
     <i class="far fa-file-alt" style="font-size: 25px;"></i>
    </i>

    <span class="media-body">Cliente</span>

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
  
<!--   <li> -->

<%--    <a class="pmd-ripple-effect" href="<ilion:url/>ilionnet/logout"> --%>
<!--     <i class="media-left media-middle"> -->
<!--      <svg version="1.1" -->
<!--           id="Layer_1" -->
<!--           x="0px" -->
<!--           y="0px" -->
<!--           width="18px" -->
<!--           height="18px" -->
<!--           viewBox="288.64 337.535 18 18" -->
<!--           enable-background="new 288.64 337.535 18 18" -->
<!--           xml:space="preserve"> -->
<!--                 <path fill="#020202" d="M295.39,337.535v2.25h9v13.5h-9v2.25h11.25v-18H295.39z M297.64,342.035v3.375h-9v2.25h9v3.375l3.375-3.375 -->
<!--                     l1.125-1.125l-1.125-1.125L297.64,342.035z"/> -->
<!--                 </svg> -->
<!--     </i> -->

<!--     <span class="media-body">Empresa</span> -->

<!--    </a> -->

<!--   </li> -->

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
