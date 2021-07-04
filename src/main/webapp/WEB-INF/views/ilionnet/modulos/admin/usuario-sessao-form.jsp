<%@ include file="/ilionnet/taglibs.jsp"%>

<html>
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../include-head.jsp" flush="true"/>
</head>

<body>
<table id="container" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td style="vertical-align: top;"><jsp:include page="../include-cabecalho.jsp" flush="true"/>
    <table width="100%" border='0' align='center' cellpadding='0' cellspacing='0'>
  <tr>
    <td class="bodyPrin">
<!-- inicio corpo -->    

<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
  <tr> 
    <td class="tituloDestaque">Usu&aacute;rio Sess&atilde;o &gt; Dados cadastrais </td>
  </tr>
</table>

<form:form commandName="usuario" method="post" action="usuario-sessao-form.sp" cssStyle="margin:0;">
<span style="color:#FF0000;"><form:errors path="*"/></span>

<div style="padding:5px 0 5px 0;">

<input type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/>

</div>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
  <tr> 
    <td class="tituloDestaque"><strong><font color="#003366">Dados do Usu&aacute;rio</font></strong></td>
  </tr>
</table>
      
<table width="100%" border="0" cellspacing="1" cellpadding="0">
  <tr> 
    <td width="15%" height="17" align="right" valign="middle" class="linkCinzaEscuro">*Nome:</td>
    <td width="35%" class="linkCinza"><form:input path="nome" cssClass="forms2" cssStyle="width:80%;"/></td>
    <td width="15%" align="right" class="linkCinzaEscuro">Empresa:</td>
    <td width="35%" class="linkCinza"><form:input path="empresa" cssClass="forms2" cssStyle="width:80%;"/></td>
  </tr>
  <tr> 
    <td align="right" valign="middle" class="linkCinzaEscuro">*Email:</td>
    <td class="linkCinza"><form:input path="email" cssClass="forms2" cssStyle="width:80%;"/></td>
    <td align="right" class="linkCinzaEscuro">Telefone / Celular:</td>
    <td class="linkCinza"><form:input path="telefone" cssClass="forms2" cssStyle="width:100px;"/> / <form:input path="celular" cssClass="forms2" cssStyle="width:100px;"/></td>
  </tr>
  <tr> 
    <td align="right" valign="middle" class="linkCinzaEscuro">Login:</td>
    <td class="linkCinza"><form:input path="login" cssClass="forms2" cssStyle="width:50%;"/></td>
    <td align="right" class="linkCinzaEscuro">Senha / Confirmar:</td>
    <td class="linkCinza"><form:password path="senha" showPassword="true" cssClass="forms2" cssStyle="width:100px;"/> / <form:password path="confirmar" showPassword="true" cssClass="forms2" cssStyle="width:100px;"/></td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td class="divisaoAzul"></td>
  </tr>
</table>

<input type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/>

</form:form>



<!-- fim corpo -->
    </td>
  </tr>
</table>
</td>
  </tr>
    <tr>
     <td class="rodape">Ilion - <a href="http://www.ilion.com.br/" target="_blank" title="ILION.com.br">www.ilion.com.br</a></td>
  </tr>
</table>
<script type="text/javascript">
var m = "<%= request.getParameter("m") %>";
if(m == "ok") {
	alert("Dados atualizados com sucesso.");
}
</script>
</body>
</html>