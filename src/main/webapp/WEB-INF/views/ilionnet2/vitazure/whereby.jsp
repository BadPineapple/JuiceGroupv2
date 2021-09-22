<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR" ng-app="consultaWherebyApp" ng-controller="ConsultaWherebyController">
<head>
	<jsp:include page="includes/include-head.jsp" flush="true" />
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>	
	<script src="../assets/js/vitazure/whereby.js"></script>

	<style>
	.entrar-consulta {
		display: none;
	}
	.dados-atualizados {
		display: none;
	}
	header {
		position: absolute;
	}
	.area-white {
		padding: 16.2rem 0 2.4rem 0;
	} 
	button#finalizarAtendimento {
		font-size: 14px;
		padding: 7px 15px 9px 15px;
		border-radius: 7px;
		margin-top: -4px
	}
		
	@media (max-width: 769px) {
		.area-white {
			padding: 9.3rem 0 2.4rem 0;
		} 
		button#finalizarAtendimento {
			margin-top: 5px
		}
	}
	</style>

	<script src="../assets/js/vitazure/moment.js"></script>
</head>
	
<body id="index" class="home"  ng-init="atualizarCronometro();agenda.id='${agenda.id}'">
	
	<div id="app">

	  <jsp:include page="includes/include-header-internas.jsp" flush="true" />

		<div class="area-white">
			<div class="container">
				<div class="row" style="margin-bottom: 8px;">
					<div class="col-8" style="display: flex;">
						<input type="text" value="${horaFimAtendimento}" id="horaFim" style="display: contents;"/>
						<input type="text" value="${agenda.horaInicioAgenda}" id="horaInicio" style="display: contents;"/>
						<input type="text" value="${agenda.id}" id="id" style="display: contents;"/>
						<i class="fas fa-stopwatch" style="font-size: 24px; padding-right: 6px;"></i>
						<p style="padding-right: 6px; line-height: 22px;">
							Tempo restante para esta consulta: 
							<span id="spanRelogio" style="color: red; font-weight: 700; display: contents;"></span>
						</p>
					</div>
					<div class="col-4" style="text-align: right;">
						<button id="finalizarAtendimento" class="btn btn-danger" ng-click="finalizacaoAtendimento()">
							<i class="fas fa-sign-out-alt" style="padding-right: 4px;"></i> Sair
						</button>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
					  <iframe id="frameWhereby" src="${agenda.urlAtendimentoOnline}&?displayName=${pessoa.nome}" allow="camera; microphone;" style="width: 100%; margin: 0 auto; left: 20px; height:600px; border: none; border-radius: 12px;"></iframe> 
					</div>
				</div>
				<p style="padding-top: 10px; text-align: center; font-size: 14px; line-height: 20px; font-style: italic; color: #3F8608"><i class="fas fa-lock" style="padding-right: 5px"></i> Sua conexão é protegida com a criptógracia de ponta a ponta.</p>
			</div>
		</div>

	  <jsp:include page="includes/include-footer.jsp" flush="true" />

		<script src="../assets/js/bundle.libs.ilionnet.js"></script>
		<script src="../assets/js/bundle.scripts.ilionnet.js"></script>
		<script src="../assets/js/bundle.libs.angular.js"></script>

		<c:if test="${param.m == 'ok'}">

			<button 
				type="button" 
				data-positionX="right" 
				data-positionY="top" 
				data-effect="fadeInUp" 
				data-message="Dados gravados com sucesso."
				data-type="success" 
				class="btn pmd-ripple-effect btn-success pmd-z-depth pmd-alert-toggle"
				id="alertSucess"
				style="display:none;">
				Sucesso
			</button>

			<script type="text/javascript">
				(function() {
					setTimeout(function() {
						$('#alertSucess').click();
					}, 300);
				})();
			</script>

		</c:if>

	</div>
	
</body>

<script type="application/javascript">
    function TimeCounter() {
        this.startDate = null;
        this.ellapsedTime = null;

        this.start = function() {
            this.startDate = new Date();
        }
       
        this.stop = function() {
            return (new Date() – this.startDate)/1000;
        }
    }
   
    var timeCounter = new TimeCounter();
    window.onload = function() {
        timeCounter.start();
        for(var i=0; i<1000; i++) {
            var div = document.createElement("div");
            div.appendChild(document.createTextNode(i));
            document.body.appendChild(div);
        }
        alert(timeCounter.stop());
    }
</script>
<!-- <script> -->

<!--  		$( window ).resize(function() { -->
<!--  			var windowWidth = window.innerWidth; -->
<!--  			var windowHeight = window.innerHeight; -->
<!--  			var heightFrameWhereby = windowHeight - 300; -->
<!--          	document.getElementById("frameWhereby").style.height = heightFrameWhereby+"px"; -->
<!--  		}); -->

<!--          $(document).ready(function () { -->
<!--          	var windowWidth = window.innerWidth; -->
<!--          	var windowHeight = window.innerHeight; -->
<!--          	var heightFrameWhereby = windowHeight - 300; -->
<!--          	document.getElementById("frameWhereby").style.height = heightFrameWhereby+"px"; -->
<!--          }) -->
<!--     </script> -->

</html>

<!-- <script>

document.getElementsByClassName("marcar")[0].onclick = function() {teste()};
function teste() {
alert("Deu Certo!");
}


</script> -->