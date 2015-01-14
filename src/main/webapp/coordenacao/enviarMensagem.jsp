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

<script type="text/javascript" >
$(document).ready(function() {
	$('#btn-aluno').click(function() {
		var mensagem = $('#aluno').val();

		$.post("../enviarMensagem/enviarMensagemAluno", {
			mensagem : mensagem
		}, function(data) {
			if (data['success']) {
				alert('Mensagem enviada com sucesso');
			}
		});
	});
	
	$('#btn-professor').click(function() {
		var mensagem = $('#professor').val();
		
		$.post("../enviarMensagem/enviarMensagemProfessor", {
			mensagem : mensagem
		}, function(data) {
			if (data['success']) {
				alert('Mensagem enviada com sucesso');
			}
		});
	});
});
</script>

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
					<li><a href="<s:url value="/principalCoordenacao"/>"><i class="fa fa-home fa"></i>Home</a></li>
					<li><a href="<s:url value="/iniciarSemestre"/>"><i class="fa-file fa"></i>Iniciar Semestre</a></li>
					<li><a href="<s:url value="/enviarMensagem"/>"><i class="fa-user fa"></i>Mensagens</a></li>
					<li><a href="<s:url value="/disciplinas"/>"><i class="fa-star fa"></i>Disciplinas</a></li>
					<li><a href="<s:url value="/usuarios"/>"><i class="fa-info-circle fa"></i> Usu�rios</a></li>
					<li><a href="<s:url value="/visualizarSimulacaoAjusteMatricula"/>"><i class="fa-file fa"></i>Grades Horarias</a></li>
				</ul>
			</nav>
		</div>
		<div class="white-grid-layout">
			<div id="content-box">
				<div id="content">
					<form class="form-left" id="solicitacao" name="solicitacao"
						action="#" method="POST"
						enctype="multipart/form-data" novalidate="novalidate">

						<h1>Bem Vindo</h1>
						
						<label>Mensagem Aluno</label>
						<textarea maxlength="250" name="aluno" id="aluno" placeholder="Escreva uma mensagem para os alunos"></textarea>
						
						<center>
							<input id="btn-aluno" name="btn-aluno" type="submit" value="Enviar">
						</center>
						
						<label>Mensagem Professor</label>
						<textarea maxlength="250" name="professor" id="professor" placeholder="Escreva uma mensagem para os Professores"></textarea>
						
						<center>
							<input id="btn-professor" name="btn-professor" type="submit" value="Enviar">
						</center>
					</form>

					<div class="form-help">
						<h2>Instru��es</h2>
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