<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>

<link rel="shortcut icon" href="<s:url value="/resources/images/favicon.ico"/>" type="image/x-icon">
<link rel="icon" href="<s:url value="/resources/images/favicon.ico"/>" type="image/x-icon">

<title>Secretaria do Bacharelado em Sistemas de Informação - UFU</title>

<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta name="description" content="">
<meta name="keywords" content="">

<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/font-awesome.css"/>" />
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/bebas-neue.css"/>" />
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/normalize.css"/>" />
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/style.css"/>" />

<script type="text/javascript" src='<s:url value="/resources/js/function.js" />'></script>

<style type="text/css"> </style>

</head>

<body class="homepage">
	<div id="wrapper">

		<header id="header">
			<div class="top-bar">
				<div class="grid-layout">
					<div class="grid-topbar-left">
						<ul class="top-bar-content">
							<li><a href="http://www.portal.facom.ufu.br/node/42/" target="_blank"><img src='<s:url value="/resources/images/logo-bsi.png" />' ></a></li>
							<li><a href="vIndex.php"> Secretaria do Bacharelado em Sistemas de Informação - UFU</a></li>
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
					<li><a href="<s:url value="/simulacaoAjusteMatricula"/>"><i class="fa-file fa"></i> Simulação Matrícula </a></li>
					<li><a href="<s:url value="/planoDisciplinaAluno"/>"><i class="fa-user fa"></i> Plano de Disciplina </a></li>
					<li><a href="<s:url value="/aterarSenhaAluno"/>"><i class="fa-star fa"></i> Trocar Senha </a></li>
					<li><a href=""><i class="fa-info-circle fa"></i> Sobre</a></li>
				</ul>
			</nav>
		</div>
		<div class="white-grid-layout">
			<div id="content-box">
				<div id="content">
					<div class="bloco-left">

						<h1>Notícias</h1>

						<s:iterator value="mensagensGeraisAluno" var="user" status="stat">
							<div style="background-color: #F5F5F5; width: 100%; margin-bottom: 5px; font-family: 'coda'; font-size: 1em;">
								<div id="titulo" style="text-align: center;"> <s:property value="assunto"/> </div>
								<div> <s:property value="mensagem"/> </div>
							</div>
		                </s:iterator>

					</div>
					<div class="form-help">
						<h2>Notícias Específicas</h2>
						<text>
						<p>Para mais informações sobre este sistema acesse este <a href="#">link</a>.</p>
						</text>
						<s:iterator value="mensagensEspecificasAluno" var="user" status="stat">
							<div style="background-color: #F5F5F5; width: 100%; margin-bottom: 5px;">
								<div id="titulo" style="text-align: center;"> <s:property value="assunto"/> </div>
								<div> <s:property value="mensagem"/> </div>
							</div>
						</s:iterator>
					</div>
				</div>
			</div>
		</div>

		<footer>
			<div class="grid-layout">
					<text>Faculdade de Computação</text>
					<text>Universidade Federal de Uberlândia</text>
					<text>Copyright © 2015 - Todos os direitos reservados</text>
			</div>
		</footer>
	</div>


	<iframe src="about:blank"
		style="height: 0px; width: 0px; visibility: hidden; border: none;">This
		frame prevents back/forward cache problems in Safari.</iframe>
</body>
</html>