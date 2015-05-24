<%@page import="br.com.ufu.bsi.session.UsuarioLogado"%>
<%@page import="br.com.ufu.bsi.dto.Professor"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>

<link rel="shortcut icon"
	href="<s:url value="/resources/images/favicon.ico"/>"
	type="image/x-icon">
<link rel="icon" href="<s:url value="/resources/images/favicon.ico"/>"
	type="image/x-icon">

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
					<li><a href="<s:url value="/principalProfessor"/>"><i class="fa fa-home fa"></i> Home </a></li>
					<li><a href="<s:url value="/reposicaoAula"/>"><i class="fa-file fa"></i>Reposição de Aula </a></li>
					<li><a href="<s:url value="/planoDisciplina"/>"><i class="fa-user fa"></i>Plano de Disciplina </a></li>
					<% UsuarioLogado u = (UsuarioLogado) request.getAttribute("usuarioLogado");
					   Professor p = (Professor) u.getObject();
					   if(!(p.getTipoProfessor().equals(Professor.TIPO_PROFESSOR_NORMAL))) {
					%>
						<li><a href="<s:url value="/analisePlanoDisciplina"/>"><i class="fa-user fa"></i>Analisar Planos Disciplina </a></li>
					<% } %>
					<li><a href="<s:url value="/aterarSenhaProfessor"/>"><i class="fa-star fa"></i> Trocar Senha</a></li>
					<li><a href=""><i class="fa-info-circle fa"></i> Sobre</a></li>
				</ul>
			</nav>
		</div>
		<div class="white-grid-layout">
			<div id="content-box">
				<div id="content">
					<div class="bloco-left">

						<h1>Notícias</h1>

						<s:iterator value="mensagensGeraisProfessor" var="user" status="stat">
							<div style="background-color: #F5F5F5; width: 100%; margin-bottom: 5px; font-family: 'coda'; font-size: 1em;">
								<div id="titulo" style="text-align: center;"> <s:property value="assunto"/> </div>
								<div> <s:property value="mensagem"/> </div>
							</div>
		                </s:iterator>

					</div>
					<div class="form-help">
						<h2>Instruções</h2>
						<text>
						<p>Lorem Ipsum is simply dummy text of the printing and
							typesetting industry. Lorem Ipsum has been the industry's
							standard dummy text ever since the 1500s, when an unknown printer
							took a galley of type and scrambled it to make a type specimen
							book. It has survived not only five centuries, but also the leap
							into electronic typesetting, remaining essentially unchanged. It
							was popularised in the 1960s with the release of Letraset sheets
							containing Lorem Ipsum passages, and more recently with desktop
							publishing software like Aldus PageMaker including versions of
							Lorem Ipsum.</p>
						</text>
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