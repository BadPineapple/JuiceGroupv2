<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="pt" dir="ltr" style="background: #f9f9f9;">

<%-- 	<jsp:include page="inc/include-head.jsp" /> --%>

<body>

	<style>
		body {
			overflow: hidden;
		}
	
		.logotipo {
			margin: 150px 0 50px 0;
			
		}
		
		.logotipo img {
			margin-left: 48px;
		}
		
		@media (max-width: 500px) {
			img {
				width: 300px;
			}
		}
		
		.mensagem {
			color: #555553;
			font-weight: 100;
    		font-size: 32px;
	    	transform: scale(.75, 1);
	    	text-shadow: 1px 1px white, -1px -1px #16739a;
		}
		
		.row {
		     margin-right: -15px;
		     margin-left: -15px;
			 text-align: center;
		}
		
	</style>

	<div class="row">
		<div class="col-md-12 logotipo">
			<img src="assets/images/logo.png" alt="logotipo"/>
		</div>
		
		<div class="col-md-12">
			<h3 class="mensagem">Site em constru&ccedil;&atilde;o. Em breve, novidades.</h4>
		</div>
	</div>

</body>

</html>