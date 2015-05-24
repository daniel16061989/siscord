<%@page import="br.com.ufu.bsi.session.UsuarioLogado"%>
<%@page import="br.com.ufu.bsi.dto.Professor"%>
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
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/mensagem-sistema.css"/>" />

<script type="text/javascript" src='<s:url value="/resources/js/function.js" />'></script>
<script type="text/javascript" src='<s:url value="/resources/js/jquery-1.9.1.js" />'></script>
<script type="text/javascript" src='<s:url value="/resources/js/jquery-ui.min.js" />'></script>

<script type="text/javascript" >

$(document).ready(function() {
	
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
			isRTL: true,
			showMonthAfterYear: true,
			yearSuffix: ''};
	
	$.datepicker.setDefaults($.datepicker.regional['pt-BR']);
	
	$('#dataAula').datepicker();
	$('#dataAulaReposicao').datepicker();
});
</script>

<style type="text/css">
.ui-widget-content {
    z-index: 9999 !important;
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
			
				<div id="content">
					<div class="bloco-left">

						<h1>Solicitação</h1>

						<div class="flex-div">
							<div-1> <label>Disciplina</label>
							<div class="dropdown">
								<select name="codDisciplina" id="codDisciplina" class="dropdown-select" required="">
									<option value="" disabled="" selected="" style="display: none;">Selecione a disciplina...</option>
									<s:iterator value="disciplinasProfessor" var="user" status="stat">
		                				<option value="<s:property value="idDisciplina"/>"> <s:property value="codigoDisciplina"/> - <s:property value="nomeDisciplina"/> </option>
		                			</s:iterator>
								</select>
							</div>
							</div-1>
						</div>

						<label>Data da Aula</label>
						<input name="dataAula" type="text" id="dataAula" placeholder="Digite a data em que não haverá aula" maxlength="100" required="">

						<label>Data de Reposição</label>
						<input name="dataAulaReposicao" type="text" id="dataAulaReposicao" placeholder="Digite a data de reposição da aula" maxlength="100" required="">

						<label>Sala</label>
						<input name="sala" type="text" id="sala" placeholder="Digite a sala onde será ministrada a aula" maxlength="100" autofocus="" required="">

						<label>Justificativa</label>
						<textarea maxlength="250" name="justificativa" id="justificativa" placeholder="Descreva o motivo da reposição"></textarea>

						<center>
							<input id="submit" name="submit" type="submit" value="Salvar">
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