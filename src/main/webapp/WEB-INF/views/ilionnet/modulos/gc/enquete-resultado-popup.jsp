<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Enquete</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
<link href="<ilion:link uri=''/>ilionnet/design/css/cssPrincipal.css" rel="stylesheet" type="text/css"/>
</head>
<c:set var="total" value="${enquete.votosOpcao1+enquete.votosOpcao2+enquete.votosOpcao3+enquete.votosOpcao4+enquete.votosOpcao5}"/>
<c:set var="maior" value="0"/>
<c:if test="${enquete.votosOpcao1>maior}"><c:set var="maior" value="${enquete.votosOpcao1}"/></c:if>
<c:if test="${enquete.votosOpcao2>maior}"><c:set var="maior" value="${enquete.votosOpcao2}"/></c:if>
<c:if test="${enquete.votosOpcao3>maior}"><c:set var="maior" value="${enquete.votosOpcao3}"/></c:if>
<c:if test="${enquete.votosOpcao4>maior}"><c:set var="maior" value="${enquete.votosOpcao4}"/></c:if>
<c:if test="${enquete.votosOpcao5>maior}"><c:set var="maior" value="${enquete.votosOpcao5}"/></c:if>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<table width="300" height="200" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
  <tr>
   <td valign="top" bgcolor="#FFFFFF">
<table width="300" border="0" cellspacing="2" cellpadding="0">
        <tr> 
          <td height="19" align="center" valign="middle" class="texto"><strong><font color="#990000"><c:out value="${enqueteMSG}"/></font></strong> 
          </td>
        </tr>
        <tr> 
          <td height="7" align="left" class="text"><img src="ilionnet/design/imagens/top_icon_meio.gif" width="100%" height="7"></td>
        </tr>
        <tr> 
          <td height="30" align="center" valign="middle" class="texto"><b><c:out value="${enquete.assunto}"/></b></td>
        </tr>
        <tr>
          <td align="center" class="texto"><img src="ilionnet/design/imagens/top_icon_meio.gif" width="100%" height="2"/></td>
        </tr>
        <tr> 
          <td align="center" class="texto"><table width="290" border="0" cellspacing="2" cellpadding="1">
              <tr align="center" valign="bottom" class="texto"> 
                <td height="200" colspan="4" align="center" valign="middle"> 
				<c:if test="${enquete.opcao1 != ''}">
                  <table width="290" border="0" cellpadding="0" cellspacing="2">
                    <tr align="center"> 
                      <td height="17" class="texto"><strong><c:out value="${enquete.opcao1}"/> - <fmt:formatNumber value="${enquete.votosOpcao1/total*100}" pattern="0.00"/> %</strong></td>
                    </tr>
                    <tr> 
                      <td align="center" class="texto"><table width="290" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="40" align="center" bgcolor="#E4EDF1" class="texto"><c:out value="${enquete.votosOpcao1}"/></td>
                            <td width="246" bgcolor="#F2F2F2"><img src="ilionnet/design/imagens/grafico.gif" width="<c:out value='${enquete.votosOpcao1*246/total}'/>" height="15"></td>
                          </tr>
                        </table>
                        </td>
                    </tr>
                  </table>
				 </c:if>
				 
				 <c:if test="${enquete.opcao2 != ''}">
                  <table width="290" border="0" cellpadding="0" cellspacing="2">
                    <tr align="center"> 
                      <td height="17" class="texto"><strong><c:out value="${enquete.opcao2}"/> - <fmt:formatNumber value="${enquete.votosOpcao2/total*100}" pattern="0.00"/> %</strong></td>
                    </tr>
                    <tr> 
                      <td align="center" class="texto"><table width="290" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="40" align="center" bgcolor="#E4EDF1" class="texto"><c:out value="${enquete.votosOpcao2}"/></td>
                            <td width="246" bgcolor="#F2F2F2"><img src="ilionnet/design/imagens/grafico.gif" width="<c:out value='${enquete.votosOpcao2*246/total}'/>" height="15"></td>
                          </tr>
                        </table>
                        </td>
                    </tr>
                  </table>
				 </c:if>
				 
				 <c:if test="${enquete.opcao3 != ''}">
                  <table width="290" border="0" cellpadding="0" cellspacing="2">
                    <tr align="center"> 
                      <td height="17" class="texto"><strong><c:out value="${enquete.opcao3}"/> - <fmt:formatNumber value="${enquete.votosOpcao3/total*100}" pattern="0.00"/> %</strong></td>
                    </tr>
                    <tr> 
                      <td align="center" class="texto"><table width="290" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="40" align="center" bgcolor="#E4EDF1" class="texto"><c:out value="${enquete.votosOpcao3}"/></td>
                            <td width="246" bgcolor="#F2F2F2"><img src="ilionnet/design/imagens/grafico.gif" width="<c:out value='${enquete.votosOpcao3*246/total}'/>" height="15"></td>
                          </tr>
                        </table>
                        </td>
                    </tr>
                  </table>
				 </c:if>
				 
				 <c:if test="${enquete.opcao4 != ''}">
                  <table width="290" border="0" cellpadding="0" cellspacing="2">
                    <tr align="center"> 
                      <td height="17" class="texto"><strong><c:out value="${enquete.opcao4}"/> - <fmt:formatNumber value="${enquete.votosOpcao4/total*100}" pattern="0.00"/> %</strong></td>
                    </tr>
                    <tr> 
                      <td align="center" class="texto"><table width="290" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="40" align="center" bgcolor="#E4EDF1" class="texto"><c:out value="${enquete.votosOpcao4}"/></td>
                            <td width="246" bgcolor="#F2F2F2"><img src="ilionnet/design/imagens/grafico.gif" width="<c:out value='${enquete.votosOpcao4*246/total}'/>" height="15"></td>
                          </tr>
                        </table>
                        </td>
                    </tr>
                  </table>
				 </c:if>
				 
				 <c:if test="${enquete.opcao5 != ''}">
                  <table width="290" border="0" cellpadding="0" cellspacing="2">
                    <tr align="center"> 
                      <td height="17" class="texto"><strong><c:out value="${enquete.opcao5}"/> - <fmt:formatNumber value="${enquete.votosOpcao5/total*100}" pattern="0.00"/> %</strong></td>
                    </tr>
                    <tr> 
                      <td align="center" class="texto"><table width="290" border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td width="40" align="center" bgcolor="#E4EDF1" class="texto"><c:out value="${enquete.votosOpcao5}"/></td>
                            <td width="246" bgcolor="#F2F2F2"><img src="ilionnet/design/imagens/grafico.gif" width="<c:out value='${enquete.votosOpcao5*246/total}'/>" height="15"></td>
                          </tr>
                        </table>
                        </td>
                    </tr>
                  </table>
				 </c:if>
				 
				 
                  </td>
              </tr>
              <tr align="center" valign="bottom" bgcolor="#E9E7CF"> 
                <td colspan="4" bgcolor="#EFEFEF" class="texto">Total de votos: 
                  <strong><c:out value="${total}"/></strong></td>
              </tr>
              <tr align="center" valign="bottom" bgcolor="#DDDDDD"> 
                <td colspan="4" bgcolor="#FFFFFF" class="texto"><a href="javascript:parent.window.close();" class="infoTexto"><strong>Fechar</strong></a></td>
              </tr>
            </table></td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</body>
</html>