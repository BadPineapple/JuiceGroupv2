<%@ include file="/ilionnet/taglibs.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Home Beauty - Pol&iacute;tica de Privacidade</title>
    <link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: 'Oswald', sans-serif;
            background-color: lightgray;
        }

        .cabecalho {
            display: flex;
            background-color: #1e4770;
            border-bottom: solid 5px #afcbbf;
            height: 160px;
            align-items: center;
            flex-direction: column;
        }

        .logo {
            height: 150px;
            padding: 12px;
            align-items: center;
        }
        
        .logo img {
            height: 130px;
        }

        .cabecalho a {
            text-decoration: none;
            color: #eee;
            font-size: 1.3em;
        }

        h1 {
            font-size: 2.5em;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        section {
            padding: 0px 40px;
        }

        footer {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
            background-color: #afcbbf;
            margin: 50px 0px 0px 0px;
        }


    </style>
</head>

<body>
    <header class="cabecalho">
        <div class="logo">
            <a href="#inicio">
                <img src="<ilion:url/>assets/images/logo.png" alt="Logo" />
            </a>
        </div>
    </header>

    <section>

        <h1>${artigo.titulo}</h1>
        
        <ilion:artigoConteudoImprime obj="${artigo}" layout="texto" />

    </section>

    <footer>
        HomeBeauty | Todos os Direitos Reservados
    </footer>


</body>

</html>