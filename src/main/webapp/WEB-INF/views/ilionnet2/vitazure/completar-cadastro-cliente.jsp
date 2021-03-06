<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html class="no-js" lang="pt-BR">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="author" content="Ilion" />
	<title>Vitazure</title>
	<jsp:include page="includes/include-head.jsp" flush="true" />
</head>

<body id="index" class="home">

    <div id="app">
        <jsp:include page="includes/include-header.jsp" flush="true" />
        <div class="content-internas">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="title-internas">
                            <h3>Completar cadastro</h3>
                            <div class="pages-internas">
                                <a href="#">
                                    <svg width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" clip-rule="evenodd" d="M4.83331 17.3334C3.4526 17.3334 2.33332 16.2141 2.33332 14.8334V9.83342H1.49998C0.757561 9.83342 0.385756 8.9358 0.910726 8.41083L8.41073 0.910826C8.73616 0.585389 9.2638 0.585389 9.58924 0.910826L17.0892 8.41083C17.6142 8.9358 17.2424 9.83342 16.5 9.83342H15.6666V14.8334C15.6666 16.2141 14.5474 17.3334 13.1666 17.3334H4.83331ZM8.99998 2.67858L3.45908 8.21948C3.77507 8.33792 3.99998 8.64272 3.99998 9.00007V14.8334C3.99998 15.2936 4.37308 15.6667 4.83332 15.6667L6.49998 15.6659L6.49998 12.3334C6.49998 11.4129 7.24618 10.6667 8.16665 10.6667H9.83332C10.7538 10.6667 11.5 11.4129 11.5 12.3334L11.5 15.6659L13.1667 15.6667C13.6269 15.6667 14 15.2936 14 14.8334V9.00007C14 8.64272 14.2249 8.33792 14.5409 8.21948L8.99998 2.67858ZM9.83331 12.3334H8.16665L8.16664 15.6659H9.83331L9.83331 12.3334Z" fill="black"/>
                                    </svg>
                                    Home     
                                </a>
                                <span>></span>
                                <a href="#">Completar cadastro</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="area-white">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center">
                        <h3>Complete seu cadastro</h3>
                    </div>
                    <div class="col-12 col-md-6 offset-md-3 col-xl-6 offset-xl-3">
                        <form class="form-default" style="padding: 3rem 0; font-weight: 800;">
                            <div class="row">
                                <div class="col-12">
                                    <div class="input-block">
                                        <label>CPF</label>
                                        <input type="text" required />
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Data de Nascimento</label>
                                        <input type="text" required/>
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="input-block">
                                        <label>CEP</label>
                                        <input type="text" required />
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Endere??o</label>
                                        <input type="text" required />
                                    </div>
                                </div>

                                <!-- <div class="col-12 col-xl-6">
                                    <div class="input-block">
                                        <label>N??mero</label>
                                        <input type="text" required />
                                    </div>
                                </div> -->

                                <div class="col-12 col-xl-6">
                                    <div class="input-block">
                                        <label>Bairro</label>
                                        <input type="text" required />
                                    </div>
                                </div>

                                <div class="col-12 col-xl-6">
                                    <div class="input-block">
                                        <label>Estado</label>
                                        <input type="text" required />
                                    </div>
                                </div>

                                <div class="col-12">
                                    <div class="input-block">
                                        <label>Cidade</label>
                                        <input type="text" required />
                                    </div>
                                </div>

                                <div class="col-12">
                                    <button class="button-secundary" style="font-size: 1.8rem; height: 5.4rem; line-height: 5.4rem; text-transform: uppercase;">Completar cadastro</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="includes/include-footer.jsp" flush="true" />
    </div>
</body>
</html>