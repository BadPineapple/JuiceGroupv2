# HomeBeauty #
#Exemplo Padrão#
## Requisitos ##

    Maven > 3
    Java > 7 ou superior

Instruções para desenvolvimento

    Na pasta "util", executar o comando abaixo: $ ./maven-local-repo-installs.sh. 

    Na raíz do projeto, executar o comando: $ mvn spring-boot:run

Antes de Rodar o gulp

1 - Instalar o nodejs, de preferência versão 8 LTS 2 - No windows, não usar GIT BASH, utilizar POWERSHELL ou CMDER 3 - No windows configurar gulpfile.js de acordo com o caminho do sistema do

#Gulp

    Instalar dependências globais (caso não tenha instalado ainda) sudo npm install -g gulp@3.9.1 open gulp-load-plugins gulp-sass gulp-sass-glob gulp-csso gulp-size jshint gulp-jshint gulp-uglify gulp-cache gulp-imagemin gulp-clean gulp-connect gulp-watch gulp-autoprefixer gulp-htmlmin gulp-rename gulp-concat gulp-sourcemaps gulp-flatten csscomb gulp-csscomb gulp-real-favicon fs browser-sync

    Entrar em src/main/webapp/assets/

    Criar link do gulp para o NPM nesta pasta

    $ sudo npm link gulp

Deploy na produção para projetos recém-criados ou nunca executados na máquina

OBS: Configuração de ambiente necessária somente na primeira vez. após isso basta rodar apenas "$ ./deploy.sh" toda vez que quiser enviar o projeto para produção

    Configurar o ambiente:

        Abrir o arquivo deploy.sh e editar as seguintes informações
            CUSTOMER ~> nome do cliente sem espaços e compatível com o nome do artefato no pom.xml
            WARNAME ~> nome do arquivo .war que será gerado e enviado pro servidor compatível com o nome do contexto (geralmente ROOT)
            CLEANCOMMAND ~> alterar o diretório que será limpo para que o novo arquivo .war seja enviado.
            WARDEST ~> local de destino do .war (geralmente mesmo que CLEANCOMMAND)
            RESTARTCOMMAND ~> comando utilizado para reiniciar o tomcat

        Editar o arquivo config do SSH
            $ vim ~/.ssh/config
        Adicionar as seguintes informações
            Host NOMEDOCLIENTE Hostname IPDOVPS User ilion

        Copiar chave pública local para o servidor para evitar autenticação por prompt.
            $ ssh-copy-id -i ~/.ssh/id_rsa.pub NOMEDOCLIENTE

        Editar Visudo para evitar prompt de autenticação no servidor.
            $ sudo visudo -f /etc/sudoers
        Adicionar as seguintes informações no visudo:
            ilion ALL = (ALL) NOPASSWD: ALL 2. Rodar deploy.sh
        $ ./deploy.sh

##Criando um projeto Java web com Maven de forma fácil##

http://tecnologiaeinovacao.com.br/blog/2017/03/15/como-criar-um-projeto-java-web-com-maven-de-forma-facil/

##Ambiente de desenvolvimento Java##

##Introdução##

Este documento apresenta uma passo a passo de como instalar e configurar as ferramentas necessárias para ter um ambiente de desenvolvimento Java.

Todas as ferramentas e tecnologias serão instaladas em ambiente Ubuntu, de preferência a versão Ubuntu Desktop 12.04 LTS ou superior.

##Instalação do Java##

A VM e? apenas uma especificac?a?o e devemos baixar uma implementac?a?o. Ha? muitas empresas que implementam uma VM, como a pro?pria Oracle, a IBM, a Apache e outros.

A da Oracle é a mais usada e possui versões para Windows, Linux e Solaris. Contudo, por estarmos trabalhando em ambiente de desenvolvimento com Ubuntu, nós utilizaremos a JVM da própria distribuição, a OpenJDK, por ser opensource, estável e pela mesma estar se popularizando rapidamente.

Em teoria, utilizar qualquer uma delas não deveria haver diferenças durante o desenvolvimento e em produção.

##Instalando o OpenJDK 8##

Cada distribuic?a?o Linux tem sua pro?pria forma de instalac?a?o. Algumas ja? trazem o Java junto, outras possibilitam que voce? instale pelos reposito?rios oficiais e em alguns casos voce? precisa baixar direto da Oracle e configurar tudo manualmente.

Nós utilizaremos o OpenJDK por ser opensource e padrão na maioria das distribuições Linux.

No Ubuntu, a instalação é bastante simples. Basta ir no terminal e digitar:

sudo apt-get install openjdk-8-jdk Instalando o JDK da Oracle Caso prefira utilizar o JDK da Oracle, basta fazer

sudo add-apt-repository ppa:webupd8team/java sudo apt-get update sudo apt-get install oracle-java8-installer Uma instalação mais braçal, sem usar repositório, pode ser feita baixando o instalador no próprio site da Oracle. É um tar.gz que possui um .bin que deve ser executado. Depois, é necessário apontar JAVA_HOME para esse diretório e adicionar JAVA_HOME/bin no seu PATH.

##Mais de uma VM instalada## Se você já tiver outras versões instaladas no seu Ubuntu, você pode utilizar o comando abaixo para escolher entre elas:

sudo update-alternatives --config java

##Instalação do Eclipse## A IDE Eclipse está disponível em vários sabores. Como iremos desenvolver aplicações web com Java EE, nós precisamos de uma versão com suporte a Java EE. Esta versão da IDE, entre vários plugins, possui o WTP (Web Tools Platform) que facilitará o desenvolvimento de aplicações web.

Até esta data a última versão do Eclipse é Eclipse Kepler (4.3).

Para baixar e instalar o Eclipse siga os passos abaixo:

Acesse a página de downloads do Eclipse; Baixe a versão Eclipse IDE for Java EE Developers de acordo com seu OS (32 ou 64 bits); Extraia o zip e mova o diretório para seu diretório HOME, algo como /home/<usuario>/java/eclipse; Instalação do Tomcat O servidor de aplicação Tomcat está disponível em várias realeses, cada versão suporta uma diferente versão da especificação JSP/Servlet. Como iremos trabalhar com Servlet 3.0, nós precisamos baixar a versão Tomcat 7.0.

Até esta data a última versão do Tomcat é Apache Tomcat 7.0.42.

Para baixar e instalar basta seguir os passos abaixo:

Acesse a página de downloads do Tomcat 7.0; Desça a barra de rolagem até "Binary Distributions > Core" e baixe o .zip ou .tar.gz; Extraia o zip ( tar -xvf nome_do_arquivo ) e mova o diretório para seu diretório HOME, algo como /home/<usuario>/java/apache-tomcat-7.0.42; Integrando o Tomcat no Eclipse Sempre que estamos trabalhando com o desenvolvimento de uma aplicação queremos ser o mais produtivos possível, e não é diferente com uma aplicação web. Uma das formas de aumentar a produtividade do desenvolvedor é utilizar uma ferramenta que auxilie no desenvolvimento e o torne mais ágil, no nosso caso, uma IDE.

Para integrar o Tomcat no Eclipse precisaremos do plugin WTP, na qual já vem por padrão com o Eclipse que baixamos e instalamos anteriormente, o Eclipse IDE for Java EE Developers.

O WTP, Web Tools Platform, é um conjunto de plugins para o Eclipse que auxilia o desenvolvimento de aplicações Java EE, em particular, de aplicações Web. Contém desde editores para JSP, CSS, JS e HTML até perspectivas e jeitos de rodar servidores de dentro do Eclipse.

##Configurando o Tomcat no WTP## Vamos primeiro configurar no WTP o servidor Tomcat que acabamos de descompactar.

Mude a perspectiva do Eclipse para Java (e não Java EE, por enquanto). Para isso, vá no canto direito superior e selecione Java; Abra a View de Servers na perspectiva atual. Aperte Ctrl + 3 e digite Servers: [imagem] Clique com o botão direito dentro da aba Servers e vá em New > Server: [imagem] Selecione o Apache Tomcat 7.0 e clique em Next: [imagem] Na próxima tela, selecione o diretório onde você descompactou o Tomcat e clique em Finish: [imagem] Instalando Maven 3 Maven é uma ferramenta para gerenciamento e automatização de projetos Java. Ela cuidará de tarefas como compilação, rodar bateria de testes, gerar o .war e configurar as dependências do projeto no Eclipse. Toda a configuração do Maven se encontra no arquivo pom.xml que fica dentro da raiz de cada projeto.

Para instala-lo é fáicl, basta executar o comando abaixo:

sudo apt-get --no-install-recommends install maven O argumento --no-install-recommends evita que o apt-get instale dependências desnecessárias, entre elas o OpenJDK 6 - pois utilizamos o OpenJDK 7.

Para verificar se o Maven foi instalado corretamente, execute o comando abaixo:

mvn --version A saída no console será semelhante a isto:

Apache Maven 3.0.4 Maven home: /usr/share/maven Java version: 1.8.0, vendor: Oracle Corporation Java home: /usr/lib/jvm/java-7-openjdk-amd64/jre Default locale: pt_BR, platform encoding: UTF-8 OS name: "linux", version: "3.5.0-23-generic", arch: "amd64", family: "unix" Instalando Git Git é um controle de versão distribuído e um sistema de gerenciamento de código fonte com enfase em velocidade. Inicialmente ele foi desenhado e desenvolvido pelo Linus Torvalds para o desenvolvimento do kernel do Linux, desde então ele tem se popularizado no mundo opensource e é utilizado em milhares de projetos.

Para instalar o Git no Ubuntu basta executar o comando abaixo:

sudo apt-get install git Configurando sua identidade no Git A primeira coisa a se fazer logo após a instalação do Git é configurar seu nome de usuário e endereço de e-mail. Isto é importante pois cada commit no Git usa esta informação e ela é imutável a partir daí.

Se você não fornecer estas informações, o Git utilizará as informações locais da sua máquina - o que normalmente não é o que você quer.

Execute os comandos abaixo para configurar seu usuário e e-mail:

git config --global user.name "Your Name Here" git config --global user.email "your_email@example.com" Gerando sua SSH Key Para se conectar à um servidor Git de forma segura você precisa gerar uma SSH Key (chave pública e privada). Esta chave deverá ser registrada no servidor Git para que seja possível ter acesso aos repositórios dos projetos.

##ATENÇÃO: Caso você já possua uma SSH Key configurada, você não precisa regera-la, mas somente registra-la no servidor Git.## ##Para registrar sua chave você precisa executar os comandos abaixo##

ssh-keygen -t rsa -C "your_email@example.com" Tecle <ENTER> para todas as perguntas do comando acima - são 3 perguntas. Mais informações sobre como gerar SSH Keys? Veja em "Generating SSH Keys".

Caso precise de mais detalhes sobre o Git, como utiliza-lo e seus comandos básicos, por favor, leia este guia prático e sem complicação.

Caso tenha 3h sobrando no seu dia, você pode assistir o screencast gratuito do AkitaOnRails.

Instalando Postgresql PostgreSQL é um poderoso sistema de gerenciamento de banco de dados (SGBD) opensource. Com mais de 15 anos de desenvolvimento ativo, hoje ele está disponível em praticamente todos os sistemas operacionais.

Trabalharemos com o Postgresql 9.1.

Para instalar o Postgres basta executar o comando abaixo:

sudo apt-get install postgresql-9.1 Configurando a senha do Postgresql O ideal é que todos os desenvolvedores utilizem sempre o mesmo usuário e senha do banco no ambiente de desenvolvimento, evitando assim modificações no arquivo de configuração da aplicação.

sudo -u postgres psql postgres ALTER USER postgres WITH PASSWORD '123'; \q No comando acima, o usuário postgres terá sua senha configurada para "123".

Habilitando conexões TCP/IP no Postgresql Por padrão o Postgres não suporta conexões locais nem remotas via TCP/IP, sendo impossível abrir conexões JDBC. Logo se faz necessário configurar o Postgres para suportar este tipo de conexão. Siga os passos abaixo:

Primeiramente abra o arquivo postgresql.conf sudo nano /etc/postgresql/9.1/main/postgresql.conf Localize a linha abaixo dentro do arquivo #listen_addresses = 'localhost' E substitua a linha acima por esta outra linha: listen_addresses = '*' Depois abra o arquivo pg_hba.conf sudo nano /etc/postgresql/9.1/main/pg_hba.conf Localize a linha abaixo dentro do pg_hba.conf host all all 127.0.0.1/32 md5 E substitua a linha acima por esta outra linha: host all all 0.0.0.0/0 md5 Reniciei o Postgres sudo service postgresql restart Pronto! Desta forma será possível conectar no banco dados via JDBC tanto localmente como remotamente.

##Instalando o PgAdmin## PgAdmin é uma ferramenta de gerenciamento, desenvolvimento e administração do Postgres. Através dele é que poderemos acessar o banco de dados das nossas aplicações, executar comandos SQL e administrar nossos esquemas.

Para instala-lo, simplesmente execute o comando abaixo:

sudo apt-get install pgadmin3 Após instalar o PgAdmin, execute-o, crie uma conexão localhost e tente acessar seu banco para verificar se o Postgresql foi instalado e configurado corretamente.

##Configurando o Projeto## Utilizaremos o projeto vraptor-blank-project como arquitetura base para uma aplicação que utiliza VRaptor, Spring e Hibernate (JPA2). As tecnologias principais utilizadas no projeto podem ser vistas logo abaixo:

Maven 3 VRaptor 3.5.0 Spring 3.1.4 Hibernate 3.6.10 (JPA 2.0) Sitemesh 2.4.2 jQuery 1.8.2 Twitter Bootstrap v2.2.1 jUnit 4.11, Mockito 1.9.0, DbUnit 2.4.9 e DbUnitManager 1.0 (testes automatizados) Para configurar o projeto siga os passos:

Baixe o projeto do GitHub já definindo o nome do seu novo projeto (neste caso, "myproject"): $ git clone https://github.com/triadworks/vraptor-blank-project.git myproject $ cd myproject Configure o pom.xml com as informações básicas do seu projeto, como groupId, artifactId, name. O pom.xml ficará semelhante a este: <modelVersion>4.0.0</modelVersion> <groupId>br.com.mycompany.myproject</groupId> <artifactId>myproject</artifactId> <packaging>war</packaging> <version>0.0.1-SNAPSHOT</version> <name>My Project</name> Configure o projeto para ser importado pelo Eclipse (os arquivos .project, .classpath e .settings serão criados). Pode demorar um pouco ao executar este comando pela primeira vez, pois o Maven precisará baixar todas as dependências do projeto. $ mvn eclipse:clean eclipse:eclipse Importe o projeto no Eclipse Pronto! Agora é possível desenvolver uma aplicação utilizando esta arquitetura base. Algumas classes e artefatos já existem no projeto para auxiliar o desenvolvimento - estas classes e artefatos (controllers, daos, services, jsps etc) são provenientes do projeto vraptor-issuetracker-mvn-project.

##Série Continuous Integration: Maven + Jenkins + Git##

Olá, pessoal!

No artigo de hoje vamos ver como integrar maven + jenkis + git. É algo aparentemente simples, mas que na primeira vez me deu umas dores de cabeça até deixar tudo redondo. Então, resolvi compartilhar minha experiência com vocês.

##O problema## Sempre temos que ter um problema para que o nosso dia fique mais emocionante. Então eu tinha alguns:

Queria montar um ambiente automatizado com o jenkins; Queria conectar o jenkins com o git; Queria fazer meu jenkins buildar pelo maven; Não ter um Java Project apenas; Não queria ter mais de um repositório no Git, ou seja, nada de um repositório por projeto. Bem, do problema 1 ao 3 eu já tinha resolvido facilmente, mas os pontos 4 e 5 estavam me incomodando, porque eu queria ter mais de um projeto para ter as coisas organizadas e ter uma melhor forma para trabalhar ? sem falar que a manutenção fica mais fácil. Também há outro fator, eu sou preguiçoso para coisas repetitivas. Odeio ter que fazer mais de uma vez o mesmo trabalho. Gosto de fazer o trabalho chato uma vez e se precisar mudar algo amanhã, que o esforço seja o mínimo possível.

##A solução## Após horas de tentativas de montar a estrutura ideal que queria, cheguei a uma que atendeu ao que precisava, ou seja, atingi os 5 pontos citados anteriormente. Como? Simples assim:

Repositório: criei um repositório git, que aqui chamei de trunk; Parent: criei um maven Project chamado parent. Nele coloquei as dependências base e comum nos projetos. Adicionei os outros projetos como module do projeto parent; Filho 1: criei um Project maven chamado readers-core que será um projeto focado apenas no back-end da aplicação; Filho 2 : criei um Project maven com suporte JSF que será focado na parte de front-end, porém esse projeto tem uma dependência do projeto filho 1 (readers-core). Cada projeto tem seu próprio pom.xml, isso evita de ter um pom.xml com tudo e sabemos que quando a configuração de XML começa a crescer é um inferno pra ficar dando manutenção e se achar nele. Então, cada projeto terá no seu pom.xml aquilo que ele precisa de fato, por exemplo, o projeto ereaders-core não precisa ter uma dependência JSF definida no pom.xml, porque esse é um projeto que não foca em front-end.

A estrutura ficou assim:

?meu repositório git ?parent-project maven ?projeto Java A ?projeto Java B Na prática Bem, não vou explicar como instalar o jenkins na máquina, nem o Git.

Crie um repositório Git (O meu chamei de trunk.); Criando o projeto parent. Esse cara aqui é o mais importante, pois é pra ele que vamos apontar o Jenkins. Portanto, crie um maven Project:

https://static.imasters.com.br/wp-content/uploads/2013/09/mavenproject-300x282.png

Você vai precisar ter o Jboss Tools instalado Plugin instalado na sua IDE Eclipse.

No próximo passo https://static.imasters.com.br/wp-content/uploads/2013/09/mavenstep2project-300x146.png

Observe que criei uma pasta chamada parent-project dentro de trunk. Faça isso.

No último passo, deixe conforme a imagem a seguir:

https://static.imasters.com.br/wp-content/uploads/2013/09/mavenconfigureproject1-300x223.png

Agora crie dois maven project, porém no segundo passo deixe assim:

https://static.imasters.com.br/wp-content/uploads/2013/09/mavenprojectchildren-300x127.png

O package, você decide. Eu criei um .jar para o projeto core e .war para o projeto web.

No meu caso: https://static.imasters.com.br/wp-content/uploads/2013/09/mavenprojectsmine.png

Abra o arquivo pom.xml do parent-project e adicione os dois outros projetos como modules:

https://static.imasters.com.br/wp-content/uploads/2013/09/moduleparentproejct-300x87.png

Abra o pom.xml de um dos projetos que você criou (no meu caso ereaders-core.) e em parent deixe assim:

https://static.imasters.com.br/wp-content/uploads/2013/09/parentconfigproject-300x90.png

Faça o mesmo para o outro projeto.

Para projetos web Se você criou um projeto web, terá que alterar seu pom.xml conforme a seguir. No meu caso, criei um maven project e depois adicionei ?capacidades de JSF?, isso porque o Jboss tools nos permite fazer isso. Daí a estrutura do projeto fica assim.

<img readersweb>

Nós precisamos informar ao maven onde está o web.xml para que ele possa gerar o .war.

Para isso, clique com o botão direito no pom.xml do projeto web e vá na opção maven >> add plugin. Na janela que aparece, informe o seguinte:

[xml]

<groupId>org.apache.maven.plugins</groupId>

<artifactId>maven-war-plugin</artifactId>[/xml]

Feito isso, vá no modo de edição do pom.xml e adicione o código abaixo:

[xml]

<configuration>

<webXml>..\readers-web\WebContent\WEB-INF\web.xml</webXml>

</configuration>

O código completo:

<plugin>

<groupId>org.apache.maven.plugins</groupId>

<artifactId>maven-war-plugin</artifactId>

<configuration>

<webXml>..\readers-web\WebContent\WEB-INF\web.xml</webXml>

</configuration>

</plugin>[/xml]

Se não o fizer, o seu build vai falhar, porque ele não saberá o caminho do web.xml. Por default, o maven vai procurar em seuprojeto\ WEB-INF.

Alterando a versão do Java no maven Se você estiver usando um projeto web com suporte servlet 3.0 é requerido Java 6, mas o maven por default usa Java 5. Para alterar, basta adicionar o plugin no pom.xml do projeto web:

groupId>org.apache.maven.plugins</groupId>

<artifactId>maven-compiler-plugin</artifactId>

<version>3.0</version>

E depois editar adicionando as informações a seguir:

[xml]

<configuration>

<source>1.6</source>

<target>1.6</target>

</configuration>[/xml]

Feito isso, podemos buildar nosso projeto dentro do eclipse e ver o que acontece. Portanto, clique com o botão direito no parent-project, vá em Run As >> Maven Install:

https://static.imasters.com.br/wp-content/uploads/2013/09/resultmavenbuildinstall-300x98.png

O resultado final deve ser conforme a imagem acima. Simples, não? Agora foi, mas antes disso tive problemas chatos. Um deles foi esse do .war.

No Jenkins Agora vamos para o Jenkins e criar um job para buildar esse projeto. O meu chamei de parent-project e escolhi a opção maven.

As configurações são bem simples, a mais chata foi a do build, como veremos a seguir. Como estou usando o Git, então informei onde está o repositório:

https://static.imasters.com.br/wp-content/uploads/2013/09/jenkinsrepostrunkmaven-300x71.png

E no build configurei:

https://static.imasters.com.br/wp-content/uploads/2013/09/jenkinsparentprojectpom-300x68.png

Salve as alterações e clique em Build Now:

https://static.imasters.com.br/wp-content/uploads/2013/09/resultparentprojectjenkins-300x84.png

E o resultado é o build construído.