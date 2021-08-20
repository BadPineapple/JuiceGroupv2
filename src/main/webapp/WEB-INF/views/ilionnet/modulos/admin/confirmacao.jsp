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
			<p>S&oacute;  mais  um  passo  necess&aacute;rio  para  finalizar  a  cria&ccedil;&atilde;o  de  sua  conta.
				Clique  no  bot&atilde;o  abaixo  para  confirmar  que  o  e-mail  informado  &eacute;
				realmente seu.</p>
		</div>
		<div class="col-12" style="padding-top: 45px;text-align: center;">
		   <a href="<ilion:url/>/ilionnet/confirmacao?token=${pessoa.token}" style="border: 1px solid;padding: 28px;">Confirmar e-mail</a></font></td>
		</div>
		<div class="col-12" style="padding-top: 45px;">
			<p>Aten&ccedil;&atilde;o:  Caso  o  bot&atilde;o  acima  n&atilde;o  funcione,  favor  entre  em  contato  conosco.
				Temos uma equipe pronta para auxili&aacute;-lo.
				Atenciosamente,</p>
		</div>
		<div class="col-12" style="text-align: end;">
		 <img style="width:140px" src="https://www.vitazure.com.br/assets/images/VITAZURE_LOGO_COR.png" alt="" /></p>
		</div>
		<div id="triangulo-para-cima"></div>
		<div class="col-12" style="border-bottom: solid 2px;border-bottom-color: currentcolor;border-bottom-color: currentcolor;border-bottom-color: currentcolor;width: 95% !important;padding-bottom: 6px;border-color: #1795d4;margin-bottom: 20px;">
		</div>	
	</div>
</div>	
</body>
</html>
