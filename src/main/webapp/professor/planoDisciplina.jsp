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

<script type="text/javascript" src='<s:url value="/resources/js/function.js" />'></script>
<script type="text/javascript" src='<s:url value="/resources/js/jquery-1.9.1.js" />'></script>

<script type="text/javascript" >

$(document).ready(function() {
	
	var idDisciplina = 0;
	var quantDias = 0;
	
	$('#codDisciplina').change(function() {
		idDisciplina = $(this).val();
		
		$.post("../planoDisciplina/buscarDataAula", {
			idDisciplina : idDisciplina
		}, function(data) {
			if (data['success']) {
				var blocoDias = '';
				var datas = data['success'].split(';');
				quantDias = datas.length;
				
				for(var i = 1; i <= datas.length; i++) {
					blocoDias = blocoDias +
					'<table class="bloco-plano-aula"> ' +
					'	<tr> ' +
					'		<td style="width: 25px;"> '+i+' </td> ' +
					'		<td style="width: 95px;"> '+datas[i-1]+' </td> ' +
					'		<td> <textarea maxlength="250" name="descricao-dia" class="descricao-dia" id="'+i+'" placeholder="Descreva este dia" style="background-color: white; max-width: 380px;"></textarea></td> ' +
					'	</tr> ' +
					'</table> ';
				}
				
				$('#blocos').html(blocoDias);
			}
		});
	});
	
	$('#salvar').click(function() {
		var disciplina = idDisciplina;
		var idPlanoDisciplina = $('#idPlanoDisciplina');
		var ementa = $('#ementa').val();
		var metodologia = $('#metodologia').val();
		var avaliacao = $('#avaliacao').val();
		var atendimento = $('#atendimento').val();
		var recuperacao = $('#recuperacao').val();
		var bibliografia = $('#bibliografia').val();
		var descricaoDia = '';
		
		for(var i = 1; i <= quantDias; i++) {
			descricaoDia = descricaoDia + $('#'+i).val()+';';
		}
		
		$.post("../planoDisciplina/salvarPlanoDisciplina", {
			disciplina : disciplina, ementa : ementa, metodologia : metodologia, avaliacao : avaliacao, atendimento : atendimento, 
			recuperacao : recuperacao, bibliografia : bibliografia, descricaoDia : descricaoDia, idPlanoDisciplina : idPlanoDisciplina
		}, function(data) {
			if (data['success']) {
				alert("Plano disciplina salvo com sucesso");
			} 
		});
	});
	
});

</script>


<style type="text/css"> 

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
					<li><a href=""><i class="fa-star fa"></i> Links Úteis</a></li>
					<li><a href=""><i class="fa-info-circle fa"></i> Sobre</a></li>
				</ul>
			</nav>
		</div>
		<div class="white-grid-layout">
			<div id="content-box">
				<div id="content">
					<form class="form-left" id="solicitacao" name="solicitacao" action="#" method="POST">

						<h1>Plano da Disciplina</h1>

						<div class="flex-div">
							<div-1> <label>Disciplina</label>
							<div class="dropdown">
								<select name="codDisciplina" id="codDisciplina" class="dropdown-select" required="">
									<option value="" disabled="" selected="" style="display: none;">Selecione a disciplina...</option>
									<s:iterator value="professorDisciplinas" var="user" status="stat">
		                				<option value="<s:property value="idDisciplina"/>"> <s:property value="codigoDisciplina"/> - <s:property value="nomeDisciplina"/> </option>
		                			</s:iterator>
								</select>
							</div>
							</div-1>
						</div>

						<input type="hidden" name="idPlanoDisciplina" id="idPlanoDisciplina" />

						<label>Ementa</label>
						<textarea maxlength="250" name="ementa" id="ementa" placeholder="Descreva a ementa"></textarea>

						<label>Metodologia</label>
						<textarea maxlength="250" name="metodologia" id="metodologia" placeholder="Descreva a metodologia"></textarea>
						
						<label>Avaliação</label>
						<textarea maxlength="250" name="avaliacao" id="avaliacao" placeholder="Descreva as avaliações"></textarea>
						
						<label>Atendimento</label>
						<textarea maxlength="250" name="atendimento" id="atendimento" placeholder="Digite os dias e horários de atendimento"></textarea>
						
						<label>Recuperação</label>
						<textarea maxlength="250" name="recuperacao" id="recuperacao" placeholder="Descreva os meios de recuperacao"></textarea>
						
						<label>Bibliografia</label>
						<textarea maxlength="250" name="bibliografia" id="bibliografia" placeholder="Descreva a bibliografia"></textarea>
						
						<center>
							<input id="salvar" name="salvar" type="submit" value="Salvar">
						</center>
					</form>

					<div class="form-help">
						<h2>Plano das Aulas</h2>
							<div id="blocos"> </div>
					</div>
				</div>
			</div>
		</div>

		<footer>
			<div class="grid-layout">
				<div class="grid-footer-left">
					<text>Desenvolvido pelo PET-SI</text>
					<text>Faculdade de Computação</text>
					<text>Universidade Federal de Uberlândia</text>
					<text>Copyright © 2013 - Todos os direitos reservados</text>
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