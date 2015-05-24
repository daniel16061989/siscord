<%@page import="br.com.ufu.bsi.dto.Semestre"%>
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
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/jquery-ui.min.css"/>" />

<script type="text/javascript" src='<s:url value="/resources/js/function.js" />'></script>
<script type="text/javascript" src='<s:url value="/resources/js/jquery-1.9.1.js" />'></script>
<script type="text/javascript" src='<s:url value="/resources/js/jquery-ui.min.js" />'></script>

<script type="text/javascript" >

$(document).ready(function() {
	$('#mensagem-sucesso').hide();
	$('#mensagem-erro').hide();
	
	$.datepicker.regional['pt-BR'] = {
			closeText: 'Fechar',
			prevText: '&#x3c;Anterior',
			nextText: 'Pr&oacute;ximo&#x3e;',
			currentText: 'Hoje',
			monthNames: ['Janeiro','Fevereiro','Mar&ccedil;o','Abril','Maio','Junho',
			'Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun',
			'Jul','Ago','Set','Out','Nov','Dez'],
			dayNames: ['Domingo','Segunda-feira','Ter&ccedil;a-feira','Quarta-feira','Quinta-feira','Sexta-feira','Sabado'],
			dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sab'],
			dayNamesMin: ['Dom','Seg','Ter','Qua','Qui','Sex','Sab'],
			weekHeader: 'Sm',
			dateFormat: 'dd/mm/yy',
			firstDay: 0,
			isRTL: false,
			showMonthAfterYear: false,
			yearSuffix: ''};
	
	$.datepicker.setDefaults($.datepicker.regional['pt-BR']);
	
	$('#data-inicio').datepicker();
	$('#data-fim').datepicker();
	$('#data-recesso').datepicker();
	$('#inicio-reajuste').datepicker();
	$('#fim-reajuste').datepicker();
	
	$('#btn-salvar-data').click(function() {
		var ano = $('#ano').val();
		var semestre = $('#semestre').val();
		var dataInicio = $('#data-inicio').val();
		var dataFim = $('#data-fim').val();
		var iReajuste = $('#inicio-reajuste').val();
		var fReajuste = $('#fim-reajuste').val();
		
		$.post("../iniciarSemestre/salvarDados", {
			fReajuste : fReajuste, iReajuste : iReajuste, ano : ano, semestre : semestre, dataInicio : dataInicio, dataFim : dataFim
		}, function(data) {
			if (data['success']) {
				$('#mensagem-sucesso').empty();
				$('#mensagem-sucesso').show();
				$('#mensagem-sucesso').append("Semestre Salvo com Sucesso");
				setInterval(fecharMensagem, 4000);
			}
		});
	});
	
	$('#btn-salvar-data-recesso').click(function() {
		var dataRecesso = $('#data-recesso').val();
		$.post("../iniciarSemestre/salvarRecesso", {
			dataRecesso : dataRecesso
		}, function(data) {
			if (data['success']) {
				$('#mensagem-sucesso').empty();
				$('#mensagem-sucesso').show();
				$('#mensagem-sucesso').append("Recesso Salvo com Sucesso")
				setInterval(fecharMensagem, 4000);
			}
		});
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
		//window.location.replace("http://localhost:8080/siscord/usuarios/");
		setInterval(fecharMensagem, 4000);
	}
	
	function mensagemErro(mensagem) {
		$('#mensagem-erro').empty();
		$('#mensagem-erro').show();
		$('#mensagem-erro').append(mensagem)
		setInterval(fecharMensagem, 4000);
	}

});
</script>

<style type="text/css">
#ui-datepicker-div {
	z-index: 999 !important;
}

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

						<table style="width:100%;">
							<tr>
								<td style="width:30%;">
									<input type="text" name="ano" id="ano" placeholder="Ano. Ex. 2014" style="width:85%;"/>
								</td>
								<td style="width:30%;">
									<input type="text" name="semestre" id="semestre" placeholder="Semestre. Ex. 2"/>
								</td>
							</tr>
							<tr>
								<td>
									<input type="text" name="data-inicio" id="data-inicio" placeholder="Data inicio do semestre" style="width:85%;"/>
								</td>
								<td>
									<input type="text" name="data-fim" id="data-fim" placeholder="Data final do semestre"/>
								</td>
							</tr>
							<tr>
								<td>
									<input type="text" name="inicio-reajuste" id="inicio-reajuste" placeholder="inicio Reajuste Matrícula" style="width:85%;"/>
								</td>
								<td>
									<input type="text" name="fim-reajuste" id="fim-reajuste" placeholder="Fim Reajuste Matrícula"/>
								</td>
							</tr>
						</table>
						
						<center>
							<input id="btn-salvar-data" name="btn-salvar-data" type="submit" value="Salvar">
						</center>
						
						<table style="width:100%;">
							<tr>
								<td>
									<input type="text" name="data-recesso" id="data-recesso" placeholder="Data de recesso durante o semstre" style="max-width:100%;" />
								</td>
							</tr>
						</table>
						<center>
							<input id="btn-salvar-data-recesso" name="btn-salvar-data-recesso" type="submit" value="Salvar Recesso">
						</center>
					</div>

					<div class="form-help">
						<h2>Semestre Atual</h2>
						<%
						Semestre s = new Semestre();
						s = (Semestre) request.getAttribute("semestreAtual");
						if(s.getIdSemestre() != null) { %>
						<text>
						<p> Semestre: ${semestreAtual.anoSemestre} - ${semestreAtual.nrSemestre}</p>
						<p> Início Semestre: ${semestreAtual.dataInicioFormatada} - Fim Semestre: ${semestreAtual.dataFimFormatada}</p>
						
						<p> Recessos:
						<s:iterator value="recessosSemestre" var="user" status="stat">
		                	<s:property value="dataRecessoFormatada"/>; 
		                </s:iterator>
		                </p>
						</text>
						<%} %>
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