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
<!-- inicio corpo usuario-busca -->    

<jsp:include page="include-admin-links.jsp" flush="true"/>

<table width="100%" border="0" cellspacing="1" cellpadding="0" style="margin:7px 0 7px 0;">
  <tr> 
    <td class="tituloDestaque">Usu&aacute;rios </td>
  </tr>
</table>

<valuelist:root value="usuarios" url="?setarParametros=true&" includeParameters="#"> 
      <table>
        <tr> 
          <td width="100%" align="right" class="texto"><c:out value="${usuarios.valueListInfo.totalNumberOfEntries}"/>
            item(ns)</td>
          <td class="texto"><valuelist:paging pages="5">&nbsp;<span align="center"><c:out value="${page}"/>&nbsp;</span></valuelist:paging></td>
        </tr>
      </table>
      <table width="100%" class="vlhTabela1" border="0" cellspacing="1" cellpadding="0">
        <valuelist:row bean="usuario"> 
		<valuelist:column title="Nome" sortable="asc" property="nome"> 
		<valuelist:attribute name="align" value="left"/>
			<a href="usuario-form.sp?id=<c:out value='${usuario.id}'/>"><c:out value="${usuario.nome}"/></a>
		</valuelist:column> 
		<valuelist:column title="Telefone"> 
	        <c:out value="${usuario.telefone}"/>
        </valuelist:column> 
		<valuelist:column title="E-mail" sortable="asc" property="email"> 

        	<a href="mailto:<c:out value='${usuario.email}'/>" class="texto"><c:out value='${usuario.email}'/></a> 
        </valuelist:column> 
		<valuelist:column title="Empresa"> 
	        <c:out value="${usuario.empresa}"/>
        </valuelist:column> 
		<valuelist:column title="Perfil"> 
	        <c:out value="${usuario.perfil.nome}"/>
        </valuelist:column> 
		<valuelist:column title="Status"> 
	        <c:out value="${usuario.status}"/>
        </valuelist:column> 
		</valuelist:row> 
      </table>
      <table>
        <tr> 
          <td width="100%" align="right" class="texto"><c:out value="${usuarios.valueListInfo.totalNumberOfEntries}"/>
            item(ns)</td>
          <td class="texto"><valuelist:paging pages="5">&nbsp;<span align="center"><c:out value="${page}"/>&nbsp;</span></valuelist:paging></td>
        </tr>
      </table>
</valuelist:root> 

      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr> 
          <td class="divisaoAzul"></td>
        </tr>
      </table>

<ilion:permissao tipo="usuario-form.sp">
<input type="button" class="botao" value="Incluir Novo" onclick="location='usuario-form.sp'"/>
</ilion:permissao>


<!-- fim corpo usuario busca -->
    </td>
  </tr>
</table>
</td>
  </tr>
    <tr>
     <td class="rodape">Ilion - <a href="http://www.ilion.com.br/" target="_blank" title="ILION.com.br">www.ilion.com.br</a></td>
  </tr>
</table>
</body>
</html>