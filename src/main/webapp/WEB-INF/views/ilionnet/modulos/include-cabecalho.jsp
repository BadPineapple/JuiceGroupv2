<%@ include file="/ilionnet/taglibs.jsp"%>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="bodyTop">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0" id="cabecalho">
        <tr> 
          <td width="*" align="right" valign="middle">
                  <table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="10" colspan="6"> &nbsp; </td>
                  </tr>
                  <tr>
                    <td align="right" class="usuariologadocabecalho">Usu&aacute;rio: <strong><c:out value="${usuarioSessao.nome}" /></strong></td>
                    <td width="14"> &nbsp; </td>
                    <td width="14"><a href="<ilion:url/>ilionnet/home" target="_parent" title="Home"><img src="<ilion:url/>ilionnet/clientes/<c:out value='${pastaCliente}' default='ilionnet'/>/<c:out value='${pastaCliente}' default='ilionnet'/>_design_top_i_home.gif" width="24" height="19" border="0" align="absmiddle"></a></td>
                    <td width="25"><a href="<ilion:url/>ilionnet/usuario-sessao-form" target="_parent" title="Meu Perfil"><img src="<ilion:url/>ilionnet/clientes/<c:out value='${pastaCliente}' default='ilionnet'/>/<c:out value='${pastaCliente}' default='ilionnet'/>_design_top_i_user.gif" width="25" height="19" border="0"></a></td>
                    <td width="24"><a href="<ilion:url/>ilionnet/logout" target="_parent" title="Logoff"><img src="<ilion:url/>ilionnet/clientes/<c:out value='${pastaCliente}' default='ilionnet'/>/<c:out value='${pastaCliente}' default='ilionnet'/>_design_top_i_sair.gif" width="24" height="19" border="0" align="absmiddle"></a></td>
                    <td width="40">&nbsp;</td>
                  </tr>
          </table></td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="top_combo_meio">
        <tr>         
              <td height="20"> 

<ilion:permissao tipo="usuario.sp">
          	<table border="0" align="left" cellpadding="0" cellspacing="0">
              <tr> 
                    <td class="top_combo_esquerdo"></td>
	                <td class="top_combo_meio"><a href="<ilion:url/>ilionnet/usuario">Admin</a></td>
                    <td class="top_combo_direito"></td>
              </tr>
            </table>
</ilion:permissao>

<ilion:permissao tipo="contato.sp">
			<table border="0" align="left" cellpadding="0" cellspacing="0">
              <tr> 
                    <td class="top_combo_esquerdo"></td>
	                <td class="top_combo_meio"><a href="<ilion:url/>ilionnet/contato">Contato</a></td>
                    <td class="top_combo_direito"></td>
              </tr>
            </table>
</ilion:permissao>

<ilion:permissao tipo="gc.sp">
			<table border="0" align="left" cellpadding="0" cellspacing="0">
              <tr> 
                    <td class="top_combo_esquerdo"></td>
	                <td class="top_combo_meio"><jsp:include page="gc/include-gc-dropdownmenu.jsp" flush="true"/></td>
                    <td class="top_combo_direito"></td>
              </tr>
            </table>
</ilion:permissao>

<ilion:permissao tipo="vitazure">
			<table border="0" align="left" cellpadding="0" cellspacing="0">
              <tr> 
                    <td class="top_combo_esquerdo"></td>
	                <td class="top_combo_meio"><a href="<ilion:url/>ilionnet/home2">Vitazure</a></td>
                    <td class="top_combo_direito"></td>
              </tr>
            </table>
</ilion:permissao>

              </td>
        </tr>
      </table></td>
  </tr>
</table>

<script type="text/javascript">
try {
	//SYNTAX: tabdropdown.init("menu_id", [integer OR "auto"])
	//tabdropdown.init("nav", "auto")
} catch(e) {
	console.log('tabdropdown.init("nav", "auto"): '+e);
}
</script>