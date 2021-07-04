<%@ include file="/ilionnet/taglibs.jsp"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-bottom: 5px;">
  <tr> 
    <td style="padding-left:10px;">
    
<ilion:permissao tipo="usuario.sp">
      <table border="0" align="left" cellpadding="0" cellspacing="1">
        <tr> 
          <td class="tituloDestaque"><a href="<ilion:url/>ilionnet/usuario.sp">Usu&aacute;rios</a>&nbsp;</td>
        </tr>
      </table>
</ilion:permissao>

<ilion:permissao tipo="perfil.sp">
      <table border="0" align="left" cellpadding="0" cellspacing="1">
        <tr> 
          <td class="tituloDestaque"><a href="<ilion:url/>ilionnet/perfil.sp">Perfis</a>&nbsp;</td>
        </tr>
      </table>
</ilion:permissao>

<ilion:permissao tipo="ilionnet-login-form.sp">
      <table border="0" align="left" cellpadding="0" cellspacing="1">
        <tr> 
          <td class="tituloDestaque"><a href="javascript:;" onClick="window.open('<ilion:url/>ilionnet/ilionnet-login-form.sp','ilionnetlogin','scrollbars=yes,resizable=yes,width=700,height=400,screenX=1,screenY=1,left=150,top=60')">Login</a>&nbsp;</td>
        </tr>
      </table>
</ilion:permissao>

<ilion:permissao tipo="emails">
      <table border="0" align="left" cellpadding="0" cellspacing="1">
        <tr> 
          <td class="tituloDestaque"><a href="<ilion:url/>ilionnet/emails">E-mails</a>&nbsp;</td>
        </tr>
      </table>
</ilion:permissao>

</td>
  </tr>
</table>