<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>ILIONnet - Ger&ecirc;ncia de Conte&uacute;do</title>
<link href="<ilion:url/>/ilionnet/design/css/css-login-ilionnet.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="<ilion:url/>/ilionnet/design/imagens/favicon-ilionnet.ico" />
</head>
<body>
<!--[if lt IE 7]>  <div style='border: 1px solid #F7941D; background: #FEEFDA; text-align: center; clear: both; height: 75px; position: relative;'>    <div style='position: absolute; right: 3px; top: 3px; font-family: courier new; font-weight: bold;'><a href='#' onclick='javascript:this.parentNode.parentNode.style.display="none"; return false;'><img src='http://www.ie6nomore.com/files/theme/ie6nomore-cornerx.jpg' style='border: none;' alt='Close this notice'/></a></div>    <div style='width: 640px; margin: 0 auto; text-align: left; padding: 0; overflow: hidden; color: black;'>      <div style='width: 75px; float: left;'><img src='http://www.ie6nomore.com/files/theme/ie6nomore-warning.jpg' alt='Warning!'/></div>      <div style='width: 275px; float: left; font-family: Arial, sans-serif;'>        <div style='font-size: 14px; font-weight: bold; margin-top: 12px;'>Seu navegador est&aacute; desatualizado</div>        
        <div style='font-size: 12px; margin-top: 6px; line-height: 12px;'>Para uma melhor performance neste site, por favor, atualize para um navegador mais moderno.</div>      
  </div>      <div style='width: 75px; float: left;'><a href='http://www.firefox.com' target='_blank'><img src='http://www.ie6nomore.com/files/theme/ie6nomore-firefox.jpg' style='border: none;' alt='Get Firefox 3.5'/></a></div>      <div style='width: 75px; float: left;'><a href='http://www.browserforthebetter.com/download.html' target='_blank'><img src='http://www.ie6nomore.com/files/theme/ie6nomore-ie8.jpg' style='border: none;' alt='Get Internet Explorer 8'/></a></div>      <div style='width: 73px; float: left;'><a href='http://www.apple.com/safari/download/' target='_blank'><img src='http://www.ie6nomore.com/files/theme/ie6nomore-safari.jpg' style='border: none;' alt='Get Safari 4'/></a></div>      <div style='float: left;'><a href='http://www.google.com/chrome' target='_blank'><img src='http://www.ie6nomore.com/files/theme/ie6nomore-chrome.jpg' style='border: none;' alt='Get Google Chrome'/></a></div>    </div>  </div><![endif]-->

<ilion:clienteImagemConsulta nomeClasse="login-topo" imagemDefault="/ilionnet/design/imagens/top-ilionnet.jpg" varRetorno="imagemTopo"/>
<ilion:clienteImagemConsulta nomeClasse="login-logo" imagemDefault="/ilionnet/design/imagens/logo-cliente.jpg" varRetorno="imagemLogo"/>

<script type="text/javascript">
function mostra(qual) {
	var obj = document.getElementById(qual);
	if(obj != null) {
		obj.style.display= ""
	}
}
function esconde(qual) {
	var obj = document.getElementById(qual);
	if(obj != null) {
		obj.style.display= "none"
	}
}
function escondeTodas() {
	esconde("esqueciMinhaSenha");esconde("login");
}
</script>
<div id="container">
    <div id="topo"><img src="<c:out value='${imagemTopo}'/>" alt="Website e Portal Din&acirc;micos" width="562" height="125" /></div>
    <div id="middle">
      <table width="542" height="224" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="45%" align="center" valign="middle" style="padding:0 0 0 40px;"><img src="${imagemLogo}" /></td>
    <td align="center" valign="middle">

	   	<form method="post" action="<ilion:url/>/ilionnet/login-logar.sp">

        <table border="0" cellspacing="0" cellpadding="0" class="tableLogin" id="login">

          <tr>

            <td align="right">Login:</td>

            <td><input class="inputLogin" style="width:176px;" id="inputLogin" name="login" type="text" /></td>

          </tr>

          <tr>

            <td align="right">Senha:</td>

            <td><input class="inputLogin" style="width:104px;vertical-align:middle;margin:0 5px 0 0;" name="senha" type="password" /><input style="vertical-align:middle;" type="image" name="imageField" id="imageField" src="<ilion:url/>/ilionnet/design/imagens/btn-login.gif" alt="Login" /></td>

          </tr>

          <tr>

            <td align="right"></td>

            <td><a href="javascript:escondeTodas();mostra('esqueciMinhaSenha');document.getElementById('inputEmail').focus();"><img border="0" src="<ilion:url/>/ilionnet/design/imagens/btn-esqueci-minha-senha.gif" alt="Esqueci minha senha" width="172" height="24" /></a></td>

          </tr>

        </table>

        </form>

        <form method="post" action="<ilion:url/>/ilionnet/esqueci-minha-senha.sp">

		<table border="0" cellspacing="0" cellpadding="0" class="tableLogin" id="esqueciMinhaSenha" style="display:none;">

          <tr>

            <td align="left">Digite seu e-mail:</td>

          </tr>

          <tr>

            <td align="left"><input class="inputLogin" style="width:149px;vertical-align:middle;margin:0 5px 0 0;" id="inputEmail" name="email" type="text" /><input style="vertical-align:middle;" type="image" name="imageField" id="imageField" src="<ilion:url/>/ilionnet/design/imagens/btn-enviar.gif" alt="Enviar" /></td>

          </tr>

          

          <tr>

            <td align="left"><a href="javascript:escondeTodas();mostra('login');document.getElementById('inputLogin').focus();"><img border="0" src="<ilion:url/>/ilionnet/design/imagens/btn-fazer-login.gif" alt="Fazer Login" width="115" height="24" /></a></td>

          </tr>

        </table>

		</form>

    </td>

  </tr>

</table>



    </div>

    <div id="rodape">
	<ilion:dataAtual varRetorno="dataAtual" />
   	  <div class="txt">Copyright &copy; 2002-<fmt:formatDate value="${dataAtual}" pattern="yyyy"/> <span class="ilion">ILION</span>. Todos os direitos reservados.</div>

        <div class="logo"><a href="http://www.ilion.com.br/" target="_blank"><img border="0" src="<ilion:url/>/ilionnet/design/imagens/logo-ilion.jpg" alt="ILION.com.br" /></a></div>

    </div>

</div>
<c:if test="${not empty msg}">
	<script type="text/javascript">
		(function() {
			alert("${msg}");
		})();
	</script>
</c:if>
<script type="text/javascript">

setTimeout(function() {
	document.getElementById('inputLogin').focus();
}, 100);
</script>
</body>
</html>