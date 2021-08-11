<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR">

<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<style type="text/css">
	    
	   #area-white .titulo::before {
		    height: 1px;
			width: 126px;
			content: "";
			background-color: #BDBDBD;
			position: absolute;
			bottom: 5px;
			left: 7%;
			transform: translateX(-50%);
		}
		
		.divDadosAssinatura{
		   margin: 33px 20px;
	       margin-bottom: 0px;
			display: flex;
			align-items: center;
			flex-wrap: wrap;
			padding-bottom: 30px;
			font-size: 20px;
			font-weight: 300;
			border-bottom: 1px solid #c8c7c7;
			margin-bottom: 30px;
		  
		}
		
	</style>
	
</head>

<body id="index" class="home">
    <div id="app">
        <jsp:include page="includes/include-header-internas.jsp" flush="true" />
        <jsp:include page="includes/include-menu-painel.jsp" flush="true" />
        <jsp:include page="includes/include-painel-profissional.jsp" flush="true" />
        <div class="area-white" id="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center">
                        <h3 style="padding-bottom: 52px;">Minha Assinatura</h3>
                    </div>
					<div class="col-12 col-md-12 col-xl-12 divDadosAssinatura">
	                    <div class="col-12 col-md-4 col-xl-4 dados-assinatura">Vigência:<br><span id="data-inicio">${profissional.planoApresentar}</span></div>
	                    <div class="col-12 col-md-4 col-xl-4 dados-assinatura">Início da assinatura:<br><span id="data-inicio">${profissional.dataInicioPlano}</span></div>
	                    <div class="col-12 col-md-4 col-xl-4 dados-assinatura">Fim da assinatura:<br><span id="data-inicio">${profissional.dataFimPlano}</span></div>
	                </div> 
	                
	                <div class="col-12">
                        <a href="<ilion:url/>vitazure/alteraMinhaAssinatura">
	                         <button  class="button-secundary checkbox-button" style="width: 100%;">Alterar Plano</button>
	                    </a>  
                    </div>
	                   
                </div>
            </div>
        </div>

        <jsp:include page="includes/include-footer.jsp" flush="true" />
    </div>
</body>
</html>