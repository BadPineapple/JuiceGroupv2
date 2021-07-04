<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ILIONnet - <ilion:nomeEmpresa/></title>
<jsp:include page="../include-head.jsp" flush="true"/>
</head>

<body>
<script type="text/javascript" language="javascript" src="design/script/jquery/jquery-1.2.6.js"></script>
<script type="text/javascript" language="javascript" src="design/script/jquery/jquery.maskedinput-1.2.1.js"></script>

<table id="container" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td style="vertical-align: top;"><jsp:include page="../include-cabecalho.jsp" flush="true"/>
    <table width="100%" border='0' align='center' cellpadding='0' cellspacing='0'>
  <tr>
    <td class="bodyPrin">
<!-- inicio corpo -->

<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
  <tr> 
    <td class="tituloDestaque">Emails Para Formul&aacute;rios - Inclus&atilde;o/Altera&ccedil;&atilde;o </td>
  </tr>
</table>

<form:form commandName="emailFormularios" method="post" action="emailformularios-form.sp" cssStyle="margin:0;">

<span style="color:red;"><form:errors path="*"/></span>

<div style="margin: 0 0 8px 0;">
<ilion:permissao tipo="emailformularios-form.sp">
<input type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/>
</ilion:permissao>
<c:if test="${not empty emailformularios.id}">
<ilion:permissao tipo="emailformularios-excluir.sp">
<input type="button" class="botao" value="Excluir" onclick="if(confirm('Deseja realmente EXCLUIR este email')){location='emailformularios-excluir.sp?id=<c:out value='${andamento-obra.id}'/>';}" style="margin-right: 5px;"/>
</ilion:permissao>
</c:if>
<ilion:permissao tipo="emailformularios-form.sp">
<input type="button" class="botao" value="Incluir Novo" onclick="location='emailformularios-form.sp'" style="margin-right: 5px;"/>
</ilion:permissao>
</div>


<table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr> 
            <td class="tituloDestaque">Dados</td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="1" cellpadding="0">
          <tr> 
            <td width="20%" align="right" valign="middle" class="linkCinzaEscuro">Nome:</td>
            <td width="30%" colspan="3" class="linkCinza"><form:input path="nome" cssClass="forms2" cssStyle="width:90%;" maxlength="255"/></td>
          </tr> 
          <tr> 
            <td width="20%" align="right" valign="middle" class="linkCinzaEscuro">Email:</td>
            <td width="30%" class="linkCinza"><form:input path="email" cssClass="forms2" cssStyle="width:90%;" maxlength="255"/></td>
            <td width="10%" align="right" valign="middle" class="linkCinzaEscuro">Formul&aacute;rio:</td>
            <td width="15%" class="linkCinza">
            	
            	<form:select path="emailTipoEnum">
            		<form:option value="null">-Selecione-</form:option>
            		<form:options items="${tiposEmailEnum}" itemLabel="valor"/>
            	</form:select>
            
            </td>
          </tr>
         <%--  <tr> 
            <td width="20%" align="right" valign="middle" class="linkCinzaEscuro">Departamento:</td>
            <td width="30%" colspan="3" class="linkCinza"><form:input path="departamento" cssClass="forms2" cssStyle="width:70%;" maxlength="255"/>(Departamento caso haja)</td>
          </tr> --%>
        </table>
<c:if test="${not empty emailsCadastradosMap}">
       <table width="100%" border="0" cellspacing="1" cellpadding="0">
		  <tr> 
		    <td class="tituloDestaque"><strong><font color="#003366">Emails cadastrados</font></strong></td>
		  </tr>
		</table>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<c:forEach items="${emailsCadastradosMap}" var="map">
		  <tr> 
		    <td width="100%" colspan="5" align="center" valign="middle" class="linkCinzaEscuro"><c:out value="${map.key.valor}"/> </td>
		    
		    <c:forEach items="${map.value}" var="email">
			  <tr> 
			    <td  width="5%" class="linkCinza" align="center"><a href="<ilion:link/>ilionnet/emailformularios-form.sp?id=${email.id}">editar </a></td>
			    <td  width="30%" class="linkCinza"><c:out value="${email.nome}"/></td>
			    <td  width="30%" class="linkCinza"><c:out value="${email.email}"/></td>
			    <td  width="30%" class="linkCinza"><c:out value="${email.emailTipoEnum.valor}"/></td>
			    <td  width="5%" class="linkCinza" align="center"><a href="<ilion:link/>ilionnet/emailformularios-excluir.sp?id=${email.id}">excluir </a></td>
			  </tr>
		    </c:forEach>
		  </tr>
		</c:forEach>
		
		</table>

</c:if>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td class="divisaoAzul"></td>
  </tr>
</table>

<div style="margin: 0 0 8px 0;">
<ilion:permissao tipo="emailformularios-form.sp">
<input type="submit" class="botao" value="Salvar" style="margin-right: 5px;"/>
</ilion:permissao>
<c:if test="${not empty emailFormularios.id}">
<ilion:permissao tipo="emailformularios-excluir.sp">
<input type="button" class="botao" value="Excluir" onclick="if(confirm('Deseja realmente EXCLUIR este Email?')){location='emailformularios-excluir.sp?id=<c:out value='${emailFormularios.id}'/>';}" style="margin-right: 5px;"/>
</ilion:permissao>
</c:if>
<ilion:permissao tipo="emailformularios-form.sp">
<input type="button" class="botao" value="Incluir Novo" onclick="location='emailformularios-form.sp'" style="margin-right: 5px;"/>
</ilion:permissao>
</div>

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

var m = "<c:out value='${param.m}'/>";
if(m == 'ok') {
	alert("Executado com sucesso!");
}else if(m == 'excluido'){
	
	alert("Executado com sucesso!");
}
</script>
</body>
</html>