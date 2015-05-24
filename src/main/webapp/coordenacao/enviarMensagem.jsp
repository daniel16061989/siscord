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

<script type="text/javascript" >
$(document).ready(function() {
	
	$('#mensagem-sucesso').hide();
	$('#mensagem-erro').hide();
	
	$('#btn-aluno').click(function() {
		var mensagem = $('#aluno').val();
		
		if(mensagem.trim() == "") {
			mensagemErro("A mensagem não pode ser vazia.");
		} else {
			$.post("../enviarMensagem/enviarMensagemAluno", {
					mensagem : mensagem
				}, function(data){
	    			if(data['error']){
		    		} else {
		    			$('#mensagem-sucesso').empty();
		    			$('#mensagem-sucesso').show();
		    			$('#mensagem-sucesso').append("Mensagem enviada para todos os Alunos");
		    			setInterval(fecharMensagem, 4000);
		    		}
	    	});
		}
	});
	
	$('#btn-professor').click(function() {
		var mensagem = $('#professor').val();
		
		if(mensagem.trim() == "") {
			mensagemErro("A mensagem não pode ser vazia.");
		} else {
			$.post("../enviarMensagem/enviarMensagemProfessor", {
				mensagem : mensagem
			}, function(data) {
				if (data['error']) {
				} else {
					$('#mensagem-sucesso').empty();
	    			$('#mensagem-sucesso').show();
	    			$('#mensagem-sucesso').append("Mensagem enviada para todos os Professores");
	    			setInterval(fecharMensagem, 4000);
				}
			});
		}
	});
	
	function fecharMensagem() {
		$('#mensagem-sucesso').hide();
	}
	
	function fecharMensagemErro() {
		$('#mensagem-erro').hide();
	}
	
	function mensagem(mensagem) {
		$('#mensagem-sucesso').empty();
		$('#mensagem-sucesso').show();
		$('#mensagem-sucesso').append(mensagem);
		setInterval(fecharMensagem, 4000);
	}
	
	function mensagemErro(mensagem) {
		$('#mensagem-erro').empty();
		$('#mensagem-erro').show();
		$('#mensagem-erro').append(mensagem);
		setInterval(fecharMensagemErro, 4000);
	}

});
</script>

<style type="text/css">

.bloco-left {
	padding-left: 5%;
	padding-right: 5%;
	margin-top: 10px;
	margin-bottom: 15px;
	font-size: 0.9em;
	text-align: left;
	float: left;
	width: 50%;
	margin: 0;
}

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
					<li><a href="<s:url value="/principalCoordenacao"/>"><i class="fa fa-home fa"></i>Home</a></li>
					<li><a href="<s:url value="/iniciarSemestre"/>"><i class="fa-file fa"></i>Iniciar Semestre</a></li>
					<li><a href="<s:url value="/enviarMensagem"/>"><i class="fa-user fa"></i>Mensagens</a></li>
					<li><a href="<s:url value="/disciplinas"/>"><i class="fa-star fa"></i>Disciplinas</a></li>
					<li><a href="<s:url value="/usuarios"/>"><i class="fa-info-circle fa"></i> Usuários</a></li>
					<li><a href="<s:url value="/visualizarSimulacaoAjusteMatricula"/>"><i class="fa-file fa"></i>Grades Horarias</a></li>
					<li><a href="<s:url value="/fileUpload"/>"><i class="fa-info-circle fa"></i> Upload</a></li>
				</ul>
			</nav>
		</div>
		<div class="white-grid-layout">
			<div id="content-box">
			
				<div id="mensagem-sucesso" style="width: 100%; height:50px; background-color: #AFEEEE;"> </div>
			 	<div id="mensagem-erro" style="width: 100%; height:50px; background-color: #FF0000; color: #FFFFFF;"> </div>
			
				<div id="content">
					<div class="bloco-left">

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