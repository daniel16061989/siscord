<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>

<link rel="shortcut icon"
	href="<s:url value="/resources/images/favicon.ico"/>"
	type="image/x-icon">
<link rel="icon" href="<s:url value="/resources/images/favicon.ico"/>"
	type="image/x-icon">

<title>Secretaria do Bacharelado em Sistemas de Informa��o - UFU</title>

<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta name="description" content="">
<meta name="keywords" content="">

<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/font-awesome.css"/>" />
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/bebas-neue.css"/>" />
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/normalize.css"/>" />
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/style.css"/>" />

<script type="text/javascript" src='<s:url value="/resources/js/function.js" />'></script>
<script type="text/javascript" src='<s:url value="/resources/js/jquery-1.9.1.js" />'></script>

<style type="text/css"> 

</style>

</head>

<body class="homepage">
	<div id="wrapper">

		<header id="header">
			<div class="top-bar">
				<div class="grid-layout">
					<div class="grid-topbar-left">
						<ul class="top-bar-content">
							<li><a href="http://www.portal.facom.ufu.br/node/42/" target="_blank"><img src='<s:url value="/resources/images/logo-bsi.png" />' ></a></li>
							<li><a href="vIndex.php"> Secretaria do Bacharelado em Sistemas de Informa��o - UFU</a></li>
						</ul>
					</div>
					<div class="grid-topbar-right">
						<a href="<s:url value="/login/fazerLogout"/>"><i class="fa-sign-out fa"></i>Logout</a>
					</div>
				</div>
			</div>
		</header>
		<div class="grid-layout">
			<nav id="navigation">
				<ul id="main-menu">
					<li><a href="<s:url value="/principalAluno"/>"><i class="fa fa-home fa"></i> Home </a></li>
					<li><a href="<s:url value="/simulacaoAjusteMatricula"/>"><i class="fa-file fa"></i> Simula��o Matr�cula </a></li>
					<li><a href="<s:url value="/planoDisciplinaAluno"/>"><i class="fa-user fa"></i> Plano de Disciplina </a></li>
					<li><a href=""><i class="fa-star fa"></i> Links �teis</a></li>
					<li><a href=""><i class="fa-info-circle fa"></i> Sobre</a></li>
				</ul>
			</nav>
		</div>
		<div class="white-grid-layout">
			<div id="content-box">
				<div id="content">
					<form class="form-left" id="solicitacao" name="solicitacao" action="../reposicaoAula/salvarNovaReposicao" method="POST"
						enctype="multipart/form-data" novalidate="novalidate">

						<h1>Aviso</h1>
						<label> A grade horaria j� foi enviada a Coordena��o aguarde uma resposta! </label>
						
					</form>
				</div>
			</div>
		</div>

		<footer>
			<div class="grid-layout">
				<div class="grid-footer-left">
					<text>Desenvolvido pelo PET-SI</text>
					<text>Faculdade de Computa��o</text>
					<text>Universidade Federal de Uberl�ndia</text>
					<text>Copyright � 2013 - Todos os direitos reservados</text>
				</div>
				<div class="grid-footer-right">
					<a href="http://www.petsi.facom.ufu.br/" target="_blank"><img src='<s:url value="/resources/images/petsi.png" />' ></a>
				</div>
			</div>
		</footer>
	</div>


	<iframe src="about:blank"
		style="height: 0px; width: 0px; visibility: hidden; border: none;">This
		frame prevents back/forward cache problems in Safari.</iframe>
</body>
</html>