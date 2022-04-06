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

<jsp:include page="include-admin-links.jsp" flush="true"/>

<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
  <tr> 
    <td class="tituloDestaque">Perfil &gt; Inclus&atilde;o / Altera&ccedil;&atilde;o</td>
  </tr>
</table>

<form:form commandName="perfil" method="post" action="perfil-form.sp" cssStyle="margin:0;">
<span style="color:#FF0000;"><form:errors path="*"/></span>

<div style="padding:5px 0 5px 0;">

<ilion:permissao tipo="perfil-form.sp">
<input type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/>
</ilion:permissao>
<ilion:permissao tipo="perfil-excluir.sp">
<c:if test="${not empty perfil.id}"> 
   <input name="acao" type="button" class="botao" value="Excluir" onClick="if(confirm('Deseja realmente EXCLUIR este perfil?')){location='perfil-excluir.sp?id=<c:out value='${perfil.id}'/>'}" style="margin-right: 5px;"/>
</c:if> 
</ilion:permissao>
<input type="button" class="botao" value="Sair" onClick="location='perfil.sp'" style="margin-right: 5px;"/>
</div>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
  <tr> 
    <td class="tituloDestaque"><strong><font color="#003366">Dados do Perfil</font></strong></td>
  </tr>
</table>
      
<table width="100%" border="0" cellspacing="1" cellpadding="0">
  <tr> 
    <td width="15%" height="17" align="right" valign="middle" class="linkCinzaEscuro">*Nome:</td>
    <td width="35%" class="linkCinza"><form:input path="nome" cssClass="forms2" cssStyle="width:80%;"/></td>
    <td width="15%" align="right" class="linkCinzaEscuro">Acesso ao sistema:</td>
    <td width="35%" class="linkCinza"><form:radiobutton path="acessoAoSistema" value="true"/> Sim <form:radiobutton path="acessoAoSistema" value="false"/> N&atilde;o </td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
  <tr> 
    <td class="tituloDestaque"><strong><font color="#003366">M&oacute;dulo Admin</font></strong></td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
  <tr> 
    <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">Usu&aacute;rio:</td>
    <td width="*" class="linkCinza"><form:checkbox path="permissoes" value="usuario.sp"/> Visualiza <form:checkbox path="permissoes" value="usuario-form.sp"/> Incluir/Altera <form:checkbox path="permissoes" value="usuario-excluir.sp"/> Excluir </td>
    </tr>
  <tr>
    <td align="right" valign="middle" class="linkCinzaEscuro">Perfil:</td>
    <td class="linkCinza"><form:checkbox path="permissoes" value="perfil.sp"/> Visualiza <form:checkbox path="permissoes" value="perfil-form.sp"/> Incluir/Altera <form:checkbox path="permissoes" value="perfil-excluir.sp"/> Excluir </td>
  </tr>
  <tr>
    <td align="right" valign="middle" class="linkCinzaEscuro">Login:</td>
    <td class="linkCinza"><form:checkbox path="permissoes" value="ilionnet-login-form.sp"/> Gerenciar Imagens do Login </td>
  </tr>
  <tr>
    <td align="right" valign="middle" class="linkCinzaEscuro">E-mails:</td>
    <td class="linkCinza"><form:checkbox path="permissoes" value="emails"/> Visualiza e-mails </td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
  <tr> 
    <td class="tituloDestaque"><strong><font color="#003366">M&oacute;dulo Contato</font></strong></td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
  <tr> 
    <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">Contato:</td>
    <td width="*" class="linkCinza"><form:checkbox path="permissoes" value="contato.sp"/> Visualiza <form:checkbox path="permissoes" value="contato-form.sp"/> Incluir/Alterar <form:checkbox path="permissoes" value="contato-excluir.sp"/> Excluir</td>
    </tr>
  <tr>
    <td align="right" valign="middle" class="linkCinzaEscuro">Grupo de contato:</td>
    <td class="linkCinza"><form:checkbox path="permissoes" value="contato-grupo-form.sp"/> Visualiza/Incluir/Alterar <form:checkbox path="permissoes" value="contato-grupo-excluir.sp"/> Excluir <form:checkbox path="permissoes" value="contato-grupo-excluir-contatos.sp"/> Excluir por Grupo <form:checkbox path="permissoes" value="contato-grupo-transferir.sp"/> Transfer&ecirc;ncia de contatos</td>
  </tr>
  <tr>
    <td align="right" valign="middle" class="linkCinzaEscuro">Importar contatos:</td>
    <td class="linkCinza"><form:checkbox path="permissoes" value="contato-importar.sp"/> Importar contatos</td>
  </tr>
  <tr>
    <td align="right" valign="middle" class="linkCinzaEscuro">Exportar contatos:</td>
    <td class="linkCinza"><form:checkbox path="permissoes" value="contato-exportar.sp"/> Exportar contatos</td>
  </tr> 
</table>
<!-- <table width="100%" border="0" cellspacing="1" cellpadding="0"> -->
<!--   <tr> -->
<!--     <td class="tituloDestaque"><strong><font color="#003366">M&oacute;dulo Ger&ecirc;ncia de Conte&uacute;do </font></strong></td> -->
<!--   </tr> -->
<!-- </table> -->
<!-- <table width="100%" border="0" cellspacing="1" cellpadding="0"> -->
<!--   <tr> -->
<!--     <td align="right" valign="middle" class="linkCinzaEscuro">Categorias Vis&iacute;veis: </td> -->
<!--     <td class="linkCinza"> -->
    
<%--     <form:checkboxes items="${categoriasArtigoTodas}" path="categorias" itemLabel="nomeSite" itemValue="id" delimiter="<br/>"/> --%>
    
<!--     </td> -->
<!--   </tr> -->
<!--   <tr>  -->
<!--     <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">Artigo:</td> -->
<%--     <td width="*" class="linkCinza"><form:checkbox path="permissoes" value="gc.sp"/> Visualiza <form:checkbox path="permissoes" value="artigo-form.sp"/> Incluir/Alterar <form:checkbox path="permissoes" value="artigo-excluir.sp"/> Excluir <form:checkbox path="permissoes" value="artigo-publicar.sp"/> Publicar (<form:checkbox path="permissoes" value="comentarios.sp"/> Coment&aacute;rios <form:checkbox path="permissoes" value="comentario-status.sp"/> Publicar Coment&aacute;rios <form:checkbox path="permissoes" value="comentario-excluir.sp"/> Excluir Coment&aacute;rios)</td> --%>
<!--   </tr> -->
<!-- </table> -->

<table width="100%" border="0" cellspacing="1" cellpadding="0">
  <tr>
    <td class="tituloDestaque"><strong><font color="#003366">M&oacute;dulo Vitazure </font></strong></td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="1" cellpadding="0">
  <tr>
    <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">Agendamentos:</td>
    <td width="*" class="linkCinza"><form:checkbox path="permissoes" value="vitazure_agendamentos"/> Visualizar</td>
  </tr>
  <tr>
    <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">Cliente/Funcionario:</td>
    <td width="*" class="linkCinza"><form:checkbox path="permissoes" value="vitazure_cliente"/> Visualizar</td>
  </tr>
  <tr>
    <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">Movimentações:</td>
    <td width="*" class="linkCinza"><form:checkbox path="permissoes" value="vitazure_movimentacoes"/> Visualizar</td>
  </tr>
  <tr>
    <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">Profissional:</td>
    <td width="*" class="linkCinza"><form:checkbox path="permissoes" value="vitazure_profissional"/> Visualizar</td>
  </tr>
  <tr>
    <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">Importar Funcionario:</td>
    <td width="*" class="linkCinza"><form:checkbox path="permissoes" value="vitazure_importar_funcionario"/> Incluir</td>
  </tr>
  <tr>
    <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">Relatórios:</td>
    <td width="*" class="linkCinza"><form:checkbox path="permissoes" value="vitazure_rel_resumo_atendimento"/> Visualizar</td>
  </tr>
  <tr>
    <td width="15%" align="right" valign="middle" class="linkCinzaEscuro">Vitazure:</td>
    <td width="*" class="linkCinza"><form:checkbox path="permissoes" value="vitazure"/> Visualizar</td>
  </tr>
</table>

<div style="padding:5px 0 5px 0;">

<ilion:permissao tipo="perfil-form.sp">
<input type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/>
</ilion:permissao>
<ilion:permissao tipo="perfil-excluir.sp">
<c:if test="${not empty perfil.id}"> 
   <input name="acao" type="button" class="botao" value="Excluir" onClick="if(confirm('Deseja realmente EXCLUIR este perfil?')){location='perfil-excluir.sp?id=<c:out value='${perfil.id}'/>'}" style="margin-right: 5px;"/>
</c:if> 
</ilion:permissao>
<input type="button" class="botao" value="Sair" onClick="location='perfil.sp'" style="margin-right: 5px;"/>
</div>

</form:form>



<!-- fim corpo --></td>
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
	alert("Perfil gravado com sucesso.");
}
</script>
</body>
</html>