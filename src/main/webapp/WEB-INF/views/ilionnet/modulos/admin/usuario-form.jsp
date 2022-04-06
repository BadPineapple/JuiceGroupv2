<%@ include file="/ilionnet/taglibs.jsp"%>

<html>
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../include-head.jsp" flush="true"/>
</head>

<body>
<script type="text/javascript" language="javascript" src="design/script/jquery/jquery-1.2.6.js"></script>
<script type="text/javascript" language="javascript" src="design/script/jquery/jquery.maskedinput-1.2.1.js"></script>
<script type="text/javascript" language="javascript">
jQuery.noConflict();
jQuery(function(jQuery){
	jQuery("#telefone").mask("(99) 9999-9999");
	jQuery("#celular").mask("(99) 9999-9999");
    jQuery("#cnpj").mask("999.999/9999-99");
    jQuery("#nrFuncionarios").mask("999");
});
</script>
<table id="container" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td style="vertical-align: top;"><jsp:include page="../include-cabecalho.jsp" flush="true"/>
    <table width="100%" border='0' align='center' cellpadding='0' cellspacing='0'>
  <tr>
    <td class="bodyPrin">
<!-- inicio corpo -->    

<jsp:include page="include-admin-links.jsp" flush="true"/>

<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
  <tr> 
    <td class="tituloDestaque">Usu&aacute;rios &gt; Inclus&atilde;o / Altera&ccedil;&atilde;o</td>
  </tr>
</table>

<form:form commandName="usuario" method="post" action="usuario-form.sp" cssStyle="margin:0;">
<span style="color:#FF0000;"><form:errors path="*"/></span>

<div style="padding:5px 0 5px 0;">

<ilion:permissao tipo="usuario-form.sp">
<input type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/>
</ilion:permissao>
<ilion:permissao tipo="usuario-excluir.sp">
<c:if test="${not empty usuario.id}"> 
   <input name="acao" type="button" class="botao" value="Excluir" onClick="if(confirm('Deseja realmente EXCLUIR este usuário?')){location='usuario-excluir.sp?id=<c:out value='${usuario.id}'/>'}" style="margin-right: 5px;"/>
</c:if> 
</ilion:permissao>
<input type="button" class="botao" value="Sair" onClick="location='usuario.sp'" style="margin-right: 5px;"/>

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
    <td width="15%" align="right" class="linkCinzaEscuro">Cnpj:</td>
    <td width="35%" class="linkCinza"><form:input path="cnpj" cssClass="forms2" id="cnpj" cssStyle="width:80%;"/></td>
  </tr>
  <tr>
    <td width="15%" align="right" class="linkCinzaEscuro">Empresa:</td>
    <td width="35%" class="linkCinza"><form:input path="empresa" cssClass="forms2" cssStyle="width:80%;"/></td>
    <td width="15%" align="right" class="linkCinzaEscuro">Nr. de funcionários Empresa:</td>
    <td width="35%" class="linkCinza"><form:input path="nrFuncionarios" id="nrFuncionarios" cssClass="forms2" cssStyle="width:80%;"/></td>
  </tr>
  <tr> 
    <td align="right" valign="middle" class="linkCinzaEscuro">*Email:</td>
    <td class="linkCinza"><form:input path="email" cssClass="forms2" cssStyle="width:80%;"/></td>
    <td align="right" class="linkCinzaEscuro">Telefone / Celular:</td>
    <td class="linkCinza"><form:input path="telefone" cssClass="forms2" cssStyle="width:100px;"/> / <form:input path="celular" cssClass="forms2" cssStyle="width:100px;"/></td>
  </tr>
  <tr> 
    <td align="right" valign="middle" class="linkCinzaEscuro">Perfil:</td>
    <td class="linkCinza"><form:select path="perfil.id" cssClass="forms2">
    				<form:option value="" label="---"/>
    				
    				<c:forEach var="p" items="${perfis}">
    				<form:option value="${p.id}" label="${p.nome}"/>
    				</c:forEach>
    				
    				</form:select></td>
    <td align="right" class="linkCinzaEscuro">* Status:</td>
    <td class="linkCinza"><form:radiobutton path="status" value="Ativo"/> Ativo <form:radiobutton path="status" value="Inativo"/> Inativo </td>
  </tr>
  <tr> 
    <td align="right" valign="middle" class="linkCinzaEscuro">Login:</td>
    <td class="linkCinza"><form:input path="login" cssClass="forms2" cssStyle="width:50%;"/></td>
    <td align="right" class="linkCinzaEscuro">Senha:</td>
    <td class="linkCinza"><form:input path="senhaAux" type="password" cssClass="forms2" cssStyle="width:50%;"/></td>
  </tr>
   <tr> 
    <td align="right" valign="middle" class="linkCinzaEscuro">Admin:</td>
    <td class="linkCinza"><form:checkbox path="admin" value="admin"/></td>
  </tr>
  <tr> 
    <td align="right" valign="middle" class="linkCinzaEscuro">Convênio</td>
    <td class="linkCinza">
      <form:radiobutton path="convenio" value="56"/> R$56,00
    </td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td class="divisaoAzul"></td>
  </tr>
</table>

<ilion:permissao tipo="usuario-form.sp">
<input type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/>
</ilion:permissao>
<ilion:permissao tipo="usuario-excluir.sp">
<c:if test="${not empty usuario.id}"> 
   <input name="acao" type="button" class="botao" value="Excluir" onClick="if(confirm('Deseja realmente EXCLUIR este usuário?')){location='usuario-excluir.sp?id=<c:out value='${usuario.id}'/>'}" style="margin-right: 5px;"/>
</c:if> 
</ilion:permissao>
<input type="button" class="botao" value="Sair" onClick="location='usuario.sp'" style="margin-right: 5px;"/>

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
	alert("Usuário gravado com sucesso.");
}
</script>
</body>
</html>