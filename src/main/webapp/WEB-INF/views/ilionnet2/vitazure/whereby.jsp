<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/ilionnet/taglibs.jsp"%>
<!doctype html>
<html class="no-js" lang="pt-BR">
<head>
  <jsp:include page="includes/include-head.jsp" flush="true" />
  <style>
    .entrar-consulta {
      display: none;
    }

    .dados-atualizados {
      display: none;
    }

  </style>
</head>
<body id="index" class="home">
<div id="app">
  <jsp:include page="includes/include-header-internas.jsp" flush="true" />
  <jsp:include page="includes/include-menu-painel.jsp" flush="true" />
  <jsp:include page="includes/include-painel-profissional.jsp" flush="true" />
  <div class="area-white">
    <div class="container">
      <div class="row">
        <div class="col-12">
          <!-- Não funciona em localhost -->
          <iframe src="${iframeSource}?embed&floatSelf&participantCount=off&people=off" allow="" style="width: 100%; height: 600px; margin: 0 auto; left: 20px"></iframe>
          ${iframeSourceFull}
        </div>
      </div>
    </div>
  </div>

  <jsp:include page="includes/include-footer.jsp" flush="true" />
</div>
</body>
</html>

<!-- <script>

document.getElementsByClassName("marcar")[0].onclick = function() {teste()};
function teste() {
alert("Deu Certo!");
}


</script> -->