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

<script type="text/javascript" src='<s:url value="/resources/js/function.js" />'></script>
<script type="text/javascript" src='<s:url value="/resources/js/jquery-1.9.1.js" />'></script>
<script type="text/javascript" src='<s:url value="/resources/js/jquery-ui.min.js" />'></script>

<script type="text/javascript" >
$(document).ready(function() {

	$('.selecionar-plano-disciplina').click(function() {
		var idProgramaPlanoDisciplina = $(this).attr('id');
		
		$.post("../analisePlanoDisciplina/buscarPlanoDisciplina", {
			idProgramaPlanoDisciplina : idProgramaPlanoDisciplina
		}, function(data) {
			if (data['success']) {
				
				var jsonData = jQuery.parseJSON(data['success']);
				
				$('#idProgramaPlanoDisciplina').val(jsonData.objectA.idProgramaPlanoDisciplina);
				$('#disciplina').val(jsonData.objectA.disciplina.nomeDisciplina);
				$('#ementa').val(jsonData.objectA.disciplina.ementa);
				$('#metodologia').val(jsonData.objectA.planoDisciplina.metodologia);
				$('#avaliacao').val(jsonData.objectA.planoDisciplina.avaliacao);
				$('#atendimento').val(jsonData.objectA.planoDisciplina.atendimento);
				$('#recuperacao').val(jsonData.objectA.planoDisciplina.recuperacao);
				$('#bibliografia').val(jsonData.objectA.disciplina.bibliografia);
				
				var blocoDias = '';
				quantDias = 1;
				$.each(jsonData.objectB, function(key, value) {
					var dateFormat = value.dataAula;
			        var dateFormat = $.datepicker.formatDate('dd/mm/yy', new Date(dateFormat));
					
					blocoDias = blocoDias +
					'<table class="bloco-plano-aula"> ' +
					'	<tr> ' +
					'		<td style="width: 25px;"> '+quantDias+' </td> ' +
					'		<td style="width: 95px;"> '+dateFormat+' </td> ' +
					'		<td> <textarea type="text" disabled id="'+quantDias+'" maxlength="250" style="background-color: white; max-width: 380px;">'+value.conteudoAula+'</textarea></td> ' +
					'	</tr> ' +
					'</table> ';
					
					quantDias++;
				});
				$('#blocos').html(blocoDias);
			}
		});
	});
	
	$('#aprovar').click(function() {
		var idProgramaPlanoDisciplina = $('#idProgramaPlanoDisciplina').val();
		var justificativa = $('#justificativa').val();
		
		$.post("../analisePlanoDisciplina/aprovarPlanoDisciplina", {
			idProgramaPlanoDisciplina : idProgramaPlanoDisciplina, justificativa : justificativa
		}, function(data) {
			if (data['success']) {
				alert("Aprovado com sucesso");
				window.location.replace("http://localhost:8080/siscord/analisePlanoDisciplina/");
			}
		});
	});
	
	$('#reprovar').click(function() {
		var idProgramaPlanoDisciplina = $('#idProgramaPlanoDisciplina').val();
		var justificativa = $('#justificativa').val();
		
		$.post("../analisePlanoDisciplina/reprovarPlanoDisciplina", {
			idProgramaPlanoDisciplina : idProgramaPlanoDisciplina, justificativa : justificativa
		}, function(data) {
			if (data['success']) {
				alert("Re-Provado com sucesso");
				window.location.replace("http://localhost:8080/siscord/analisePlanoDisciplina/");
			}
		});
	});

});
	
</script>

<style type="text/css">

.selecionar-plano-disciplina {
	cursor:pointer;
}

.bloco-plano-aula {
	background-color: #eee;
	width: 100%;
	border-style: solid;
	border-width: 1px;
	height: 51px;
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
				<div id="content">
					<div class="bloco-left">
						<h1>Plano da Disciplina</h1>
						
						<div>
							<div style="float: left; min-width: 30%; color: white;">a</div>
							<div style="float: left;">
								<input id="aprovar" name="aprovar" type="submit" value="Aprovar">
							</div>
							<div style="float: left;">
								<input id="reprovar" name="reprovar" type="submit" value="Reprovar">
							</div>
						</div>
						
						<textarea maxlength="250" name="justificativa" id="justificativa" placeholder="Justificativa"></textarea>
						
						<input type="hidden" name="idProgramaPlanoDisciplina" id="idProgramaPlanoDisciplina" />
						
						<label>Disciplina</label>
						<textarea disabled maxlength="250" name="disciplina" id="disciplina"></textarea>
						
						<label>Ementa</label>
						<textarea disabled maxlength="250" name="ementa" id="ementa"></textarea>

						<label>Metodologia</label>
						<textarea disabled maxlength="250" name="metodologia" id="metodologia" placeholder="Descreva a metodologia"></textarea>
						
						<label>Avaliação</label>
						<textarea disabled maxlength="250" name="avaliacao" id="avaliacao" placeholder="Descreva as avaliações"></textarea>
						
						<label>Atendimento</label>
						<textarea disabled maxlength="250" name="atendimento" id="atendimento" placeholder="Digite os dias e horários de atendimento"></textarea>
						
						<label>Recuperação</label>
						<textarea disabled maxlength="250" name="recuperacao" id="recuperacao" placeholder="Descreva os meios de recuperacao"></textarea>
						
						<label>Bibliografia</label>
						<textarea disabled maxlength="250" name="bibliografia" id="bibliografia" placeholder="Descreva a bibliografia"></textarea>
					</div>
											
					<div class="form-help">
						<h2>Adicionar ou Atualizar Disciplina</h2>
						<table style="width:100%;">
							<tr>
								<th>Discplina</th>
								<th>Turma</th>
								<th>Professor</th>
								<th> </th>
							</tr>
							<s:iterator value="programaPlanoDisciplinas" var="user" status="stat">
								<tr>
				                    <td> <s:property value="disciplina.codigoDisciplina"/> </td>
				           	        <td> <s:property value="disciplina.turma.codigoTurma"/> </td>
				           	        <td> <s:property value="planoDisciplina.professor.nomeProfessor"/> </td>
				           	        <td id="<s:property value="idProgramaPlanoDisciplina"/>" class="selecionar-plano-disciplina"> <i class="fa fa-book fa-fw"></i> </td>
			               		</tr>
				            </s:iterator>
						</table>
					</div>
					<div class="form-help">
						<h2>Plano das Aulas</h2>
							<div id="blocos"> </div>
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