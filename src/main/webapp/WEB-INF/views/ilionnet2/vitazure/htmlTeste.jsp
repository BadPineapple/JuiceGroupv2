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
      .botaoReagendar {
            height: 4rem;
		    line-height: 4rem;
		    padding: 8px 2.5rem;
		    font-size: 18px;
		    color: #ffffff;
		    background: #218838;
		    border-radius: 7px;
		    text-decoration: none;
		    margin: 0 1rem;
		    transition: all .3s ease-in-out;
		    border: 1px solid transparent;
      }
     a[href*="#"] {display: none;}
	</style>
</head>
<body>
<div class="container" style="padding-top: 5%;">
	<div class="row">
		<div class="col-12">
		 <img style="width:140px" src="https://www.vitazure.com.br/assets/images/VITAZURE_LOGO_COR.png" alt="" /></p>
		</div>
		<div class="col-12" style="border-bottom: solid 2px;border-bottom-color: currentcolor;border-bottom-color: currentcolor;width: 95% !important;padding-bottom: 6px;border-color: #1795d4;margin-bottom: 20px;">
		 <h3>Prezado(a) ${agenda.paciente.nome}, </h3>
		</div>
		<div class="col-12">
			<p>Olá!</p>
			<p>Sua agenda de atendimento foi alterada acesse o link e confira a nova data.</p>		  
		<div class="col-12" style="padding-top: 20px;text-align: center;">
			   <a href="<ilion:url/>/entrar" class="botaoReagendar">Visualizar agenda</a></font></td>
		</div>			              
		</div>
		<div id="triangulo-para-cima"></div>
		<div class="col-12" style="border-bottom: solid 2px;border-bottom-color: currentcolor;border-bottom-color: currentcolor;border-bottom-color: currentcolor;width: 95% !important;padding-bottom: 6px;border-color: #1795d4;margin-bottom: 20px;">
		</div>	
	</div>
</div>	
</body>
</html>
