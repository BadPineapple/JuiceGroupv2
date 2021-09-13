<%@ include file="/ilionnet/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header class="header-internas">
<style>
.dropbtn {
  background-color: transparent;
  color: #18191F;
  padding: 16px;
  font-size: 16px;
  border: none;
  cursor: pointer;
}

.dropbtn:hover, .dropbtn:focus {
  background-color: transparent;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    top: 35px;
    left: 15px;
    background-color: #fff;
    min-width: 160px;
    overflow: auto;
    box-shadow: 0px 8px 16px 0px rgb(0 0 0 / 20%);
    z-index: 1;
    text-align: left;
    padding: 7px;
    border-radius: 5px;
}

.dropdown-content a {
    color: black;
    padding: 6px 10px;
    text-decoration: none;
    display: block;
    font-size: 14px;
    border-radius: 4px;
}

.dropdown-content a:hover {background-color: #6DCFF6;}

.show {display: block;}
</style>
<div class="divSpinner" id="spinner">   
   <img alt="" src="../../assets/images/logo-square.png" style="width: 126px;position: relative;top: 167px;left: 49%;">
   <div class="spinner"></div>
</div>
    <div class="container">
        <div class="row">
            <div class="col-6 col-md-8 col-xl-8">
                <div class="menu-list">
                    <a href="<ilion:url/>home"><img src="../../assets/images/logo.png" alt="Vitazure"></a>

                    <div class="menu d-none d-md-block">
                        <ul>
                            <li><a href="<ilion:url/>como-funciona" class="line">Como funciona</a></li>

                            <li><a href="<ilion:url/>aqui-e-para-voce" class="line">Aqui é para você</a></li>

                            <li><a href="<ilion:url/>sou-profissional" class="line">Sou Profissional</a></li>

                            <li><a href="<ilion:url/>para-sua-empresa" class="line">Para sua empresa</a></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="col-6 d-md-none text-right">
                <a href="#menu" class="menu-mobile d-md-none">
                    <svg width="26px" height="16px" viewBox="0 0 26 16" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                        <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                            <g id="home" transform="translate(-408.000000, -56.000000)" fill="#0097d6">
                                <rect id="Rectangle1" x="408" y="56" width="26" height="2"></rect>
                                <rect id="Rectangle2" x="408" y="63" width="26" height="2"></rect>
                                <rect id="Rectangle3" x="408" y="70" width="26" height="2"></rect>
                            </g>
                        </g>
                    </svg>
                </a>
            </div>
            <c:if test="${pessoa == null}">
	            <div class="col-12 col-md-4 col-xl-4 d-none d-md-block">
	                <div class="buttons">
	                    <a href="<ilion:url/>entrar" class="button-primary">Entrar</a>
	                    <a href="<ilion:url/>cadastre-se" class="button-secundary">Crie sua conta</a>
	                </div>
	            </div>
	          </c:if>
	           <c:if test="${pessoa != null}">
	              <div class="col-12 col-md-4 col-xl-4 d-none d-md-block text-right">
                <div class="logado">
                    <div class="dropdown">
					  <a onclick="myFunction()" class="dropbtn">
	                        Olá ${pessoa.nome} <img src="../../assets/images/arrow-down.png" alt="">
					  </a>
					  <div id="myDropdown" class="dropdown-content">
					    <a href="<ilion:url/>/vitazure/informacoes-perfil">Painel Principal</a>
					    <a href="<ilion:url/>/deslogar">Sair</a>
					  </div>
					</div>
                </div>
            </div>
	       </c:if> 
        </div>
    </div>
    <script>
function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
</script>
</header>