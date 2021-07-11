<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="pt-BR"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="pt-BR" <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="pt-BR"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="pt-BR">
<!--<![endif]-->

<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="author" content="Ilion" />
	
	<title>Vitazure</title>
	
	<jsp:include page="includes/include-head.jsp" flush="true" />
	
</head>

<body id="index" class="home">
	
    <div id="app">
        <jsp:include page="includes/include-header-internas.jsp" flush="true" />


        <jsp:include page="includes/include-menu-painel.jsp" flush="true" />

        <jsp:include page="includes/include-painel-profissional.jsp" flush="true" />

        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center">
                        <h2>Minhas Consultas</h2>

                        <p>Confira suas consultas. É necessário o aceite.</p>
                    </div>

                    <div class="col-12 col-xl-4">
                        <div class="search">
                            <form>
                                <input type="text" placeholder="Buscar">
                                
                                <button href="#" class="search-button">
                                    <img src="images/search.png" alt="">
                                </button>
                            </form>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="table-responsive">
                            <table class="tabela-consultas">
                                <thead>
                                    <tr>
                                        <td style="width: 12.5%;">Número</td>
                                        <td style="width: 16%;">Status</td>
                                        <td style="width: 16%;">Cliente</td>
                                        <td style="width: 19.5%;">Localização</td>
                                        <td style="width: 16%;">Data</td>
                                        <td style="width: 10%;">Hora Fim</td>
                                        <td style="width: 10%;">Duração</td>
                                    </tr>
                                </thead>
    
                                <tbody>
                                    <tr>
                                        <td>#2458</td>
                                        <td>
                                            <div class="status pendente">
                                                Pendente
                                            </div>
                                        </td>
                                        <td>Darrell Williamson</td>
                                        <td>Endereço 1</td>
                                        <td>01/12/21 17:15</td>
                                        <td>18:15</td>
                                        <td>01:00</td>
                                    </tr>
    
                                    <tr>
                                        <td>#2458</td>
                                        <td>
                                            <div class="status cancelada">
                                                cancelada
                                            </div>
                                        </td>
                                        <td>Dustin Wilson</td>
                                        <td>online</td>
                                        <td>12/02/21 14:58</td>
                                        <td>12:18</td>
                                        <td>01:00</td>
                                    </tr>
    
                                    <tr>
                                        <td>#2458</td>
                                        <td>
                                            <div class="status realizada">
                                                realizada
                                            </div>
                                        </td>
                                        <td>Darrell Williamson</td>
                                        <td>Endereço 1</td>
                                        <td>01/12/21 17:15</td>
                                        <td>18:15</td>
                                        <td>01:00</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="includes/include-footer.jsp" flush="true" />
    </div>
</body>
</html>