<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>
<title>ILIONnet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top" bgcolor="#F3F3F3">
<table width="100%" border="0" align="center" cellpadding="3" cellspacing="5" bgcolor="#F3F3F3">
        <tr> 
          <td align="center" bgcolor="#D6E4E9"><font size="2" face="Arial, Helvetica, sans-serif"><strong>Envio de senha por E-mail - <ilion:nomeEmpresa/></strong></font></td>
        </tr>
        <tr> 
          <td><table width="100%" border="0" cellpadding="3" cellspacing="0" bgcolor="#FFFFFF">
              <tr> 
                <td colspan="2" align="right">&nbsp;</td>
              </tr>
              <tr> 
                <td width="90" align="right"><strong><font size="2" face="Arial, Helvetica, sans-serif">Login:</font></strong></td>
                <td><font size="2" face="Arial, Helvetica, sans-serif"><c:out value="${usuario.login}" escapeXml="{true}"/></font></td>
              </tr>
              <tr> 
                <td align="right"><strong><font size="2" face="Arial, Helvetica, sans-serif">Senha:</font></strong></td>
                <td><font size="2" face="Arial, Helvetica, sans-serif">#senha#</font></td>
              </tr>
              <tr> 
                <td align="right"><strong><font size="2" face="Arial, Helvetica, sans-serif">Status:</font></strong></td>
                <td><font size="2" face="Arial, Helvetica, sans-serif"><c:out value="${usuario.status}"/></font></td>
              </tr>
              <tr> 
                <td align="right"><font size="2" face="Arial, Helvetica, sans-serif"><strong>Link:</strong></font></td>
                <td><font size="2" face="Arial, Helvetica, sans-serif"><a href="<ilion:url/>ilionnet/"><ilion:url/>ilionnet/</a></font></td>
              </tr>
              <tr>
                <td colspan="2" align="right">&nbsp;</td>
              </tr>
            </table></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
