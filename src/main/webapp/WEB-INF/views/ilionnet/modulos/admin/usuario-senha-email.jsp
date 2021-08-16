<%@ include file="/ilionnet/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>
<title>ILIONnet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jQuery.mmenu/8.5.6/mmenu.min.css" />
	<style type="text/css">
	  #triangulo-para-cima {
		  width: 0; 
		  height: 0; 
		  border-left: 25px solid transparent;
		  border-right: 25px solid transparent;
		  border-bottom: 25px solid #82D0F5;
		  margin-left: 46%;
		  margin-top: 6px;
		  padding-top: 45px;
}
	</style>
</head>
<body>
<div class="container" style="padding-top: 5%;">
	<div class="row">
		<div class="col-12">
		 <img style="width:140px" src="https://www.vitazure.com.br/assets/images/VITAZURE_LOGO_COR.png" alt="" /></p>
		</div>
		<div class="col-12" style="border-bottom: solid 2px;border-bottom-color: currentcolor;border-bottom-color: currentcolor;width: 95% !important;padding-bottom: 6px;border-color: #1795d4;margin-bottom: 20px;">
		 <h3>Prezado(a) ${pessoa.nome}, </h3>
		</div>
		<div class="col-12" style="padding-top: 45px;">
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
			                <td width="90" align="right"><strong style="margin-right: 3px;"><font size="2" face="Arial, Helvetica, sans-serif">Login:</font></strong></td>
			                <td><font size="2" face="Arial, Helvetica, sans-serif"><c:out value="${pessoa.email}" escapeXml="{true}"/></font></td>
			              </tr>
			              <tr> 
			                <td align="right"><strong><font size="2" face="Arial, Helvetica, sans-serif">Senha:</font></strong></td>
			                <td><font size="2" face="Arial, Helvetica, sans-serif">#senha#</font></td>
			              </tr>
			              <tr> 
			                <td align="right" ><font size="2" face="Arial, Helvetica, sans-serif"><strong style="margin-right: 11px;">Link:</strong></font></td>
			                <td><font size="2" face="Arial, Helvetica, sans-serif"><a href="<ilion:url/>entrar"><ilion:url/>entrar</a></font></td>
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
		</div>
		<div id="triangulo-para-cima"></div>
		<div class="col-12" style="border-bottom: solid 2px;border-bottom-color: currentcolor;border-bottom-color: currentcolor;border-bottom-color: currentcolor;width: 95% !important;padding-bottom: 6px;border-color: #1795d4;margin-bottom: 20px;">
		</div>	
	</div>
</div>	
</body>
</html>
