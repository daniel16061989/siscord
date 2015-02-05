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
<script type="text/javascript" src='<s:url value="/resources/js/jquery-1.9.1.js" />'></script>

<style type="text/css">
.bloco-cadastro {
	float: left;
	width: 90%;
	padding-left: 25% !important;
	padding-right: 25%;
	margin-top: 10px;
	margin-bottom: 15px;
	font-size: 0.9em;
	text-align: left;
}

</style>

</head>

<body class="homepage">
	<div id="wrapper">

		<s:if test="hasActionErrors()">
	 		<div class="alert alert-danger">
	 			<a href="#" class="close" data-dismiss="alert">&times;</a>
	 			<strong>Erros</strong>
	 			<s:actionerror/>
	 		</div>
	 	</s:if>
	 	<s:if test="hasActionMessages()">
	 		<div class="alert alert-success">
	 			<a href="#" class="close" data-dismiss="alert">&times;</a>
	 			<strong>Sucesso</strong>
	 			<s:actionmessage/>
	 		</div>
	 	</s:if>

		<header id="header">
			<div class="top-bar">
				<div class="grid-layout">
					<div class="grid-topbar-left">
						<ul class="top-bar-content">
							<li><a href="http://www.portal.facom.ufu.br/node/42/" target="_blank"><img src='<s:url value="/resources/images/logo-bsi.png" />' ></a></li>
							<li><a href="vIndex.php"> Secretaria do Bacharelado em Sistemas de Informação - UFU</a></li>
						</ul>
					</div>
				</div>
			</div>
		</header>
		<div class="white-grid-layout">
			<div id="content-box">
				<div id="content">

						<h2>Cadastre-se</h2>
						<div id="formulario-aluno">
							<form method="post" action="../cadastrarAluno/salvarNovoAluno" class="bloco-cadastro">
								<table style="width:55%;">
									<tr>
										<td>Nome:</td>
										<td>
											<input id="nomeAluno" name="aluno.nomeAluno" type="text" placeholder="Nome do Aluno" />
										</td>
									</tr>
									<tr>
										<td>Matrícula:</td>
										<td>
											<input id="matricula" name="aluno.matricula" type="text" placeholder="Matríicula" />
										</td>
									</tr>
									<tr>
										<td>Senha:</td>
										<td>
											<input id="loginAluno" name="aluno.usuario.senha" type="password" placeholder="Login do Aluno" />
										</td>
									</tr>
								</table>
									<input id="submitAluno" name="submitAluno" type="submit" value="Salvar" style="margin-left: 22%;">
							</form>
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