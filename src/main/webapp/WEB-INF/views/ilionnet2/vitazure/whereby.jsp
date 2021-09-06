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
  </style>
  <script src="../assets/js/vitazure/moment.js"></script>
</head>
<body id="index" class="home"  ng-init="atualizarCronometro();agenda.id='${agenda.id}'">
<div id="app">
  <jsp:include page="includes/include-header-internas.jsp" flush="true" />
  <div class="area-white">
    <div class="container">
        <div class="col-12 col-md-12 col-xl-12" style="display: flex;">
   		  <input type="text" value="${horaFimAtendimento}" id="horaFim" style="display: contents;"/>
   		  <input type="text" value="${agenda.horaInicioAgenda}" id="horaInicio" style="display: contents;"/>
   		  <input type="text" value="${agenda.id}" id="id" style="display: contents;"/>
   		  <i class="fas fa-stopwatch" style="font-size: 24px;padding-right: 6px;"></i>
   		  <p style="padding-right: 6px;">Tempo restante para esta consulta:</p>
   		  <p id="spanRelogio"></p>
        </div>
      <div class="row">
        <div class="col-12">
          <iframe src="${pessoa.cliente ? agenda.urlAtendimentoOnline : agenda.hostUrlAtendimentoOnline}&?displayName=${pessoa.nome}" allow="camera; microphone;" style="width: 100%; height: 600px; margin: 0 auto; left: 20px"></iframe> 
        </div>
      </div>
      <p style="padding-right: 6px;">Sua conexão é protegida com a criptógracia de ponta a ponta</p>
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


</html>

<!-- <script>

document.getElementsByClassName("marcar")[0].onclick = function() {teste()};
function teste() {
alert("Deu Certo!");
}


</script> -->