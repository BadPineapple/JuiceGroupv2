<%@ include file="/ilionnet/taglibs.jsp"%>
<div class="header-cliente">
	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				
				<div class="logo"></div>
				
			</div><!-- fim col -->
			<div class="col-sm-6">
				<p class="header-data"><span>ILION</span></p>
			</div><!-- fim col -->
		</div><!-- fim row -->
	</div><!-- fim container -->
</div>

<nav class="navbar navbar-default" role="navigation">
	<div class="container">  
		<div class="row">
		<div class="navbar-header">
		  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		    <span class="sr-only">Menu</span>
		    <span class="icon-bar"></span>
		    <span class="icon-bar"></span>
		    <span class="icon-bar"></span>
		  </button>
		</div>
		<!-- /.navbar-header -->
	
		<div class="navbar-collapse collapse">
		 	
			  <!-- /.nav navbar-nav -->
			  <ul class="nav navbar-nav">
			  
			    <!-- li class="menu-agenda"><a href="usuarios.sp"><i class="fa fa-users"></i> Usu&aacute;rios</a></li-->
			  
			  </ul>
			  <!-- nav navbar-nav -->
			  
			  <ul class="nav navbar-nav navbar-right btn-group" ng-controller="usuarioLogadoInfoController">
			    <li class="menu-agenda"><a href="usuario.sp">Admin</a></li>
			    <li><a class="dropdown-toggle navbar-profile-avatar" data-toggle="dropdown" href="javascript:void(0);">${usuarioSessao.nome} <i class="fa fa-caret-down"></i></a>
			    	<ul class="dropdown-menu" role="menu">
			    		<li><a href="usuario-sessao-form.sp"><i class="fa fa-user"></i> &nbsp;&nbsp;Editar perfil</a></li>
			    		<li class="divider"></li>
			    		<li><a href="login-sair.sp"><i class="fa fa-sign-out"></i> Sair</a></li>
			    	</ul>
			    </li>
			  </ul>
			  <!-- nav navbar-nav -->
		  
		  
		</div>
		<!--/.nav-collapse -->


	</div>
	</div>
	<!-- /.container -->
	
	
</nav>