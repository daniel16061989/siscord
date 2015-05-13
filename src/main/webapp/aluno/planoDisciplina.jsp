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
<script type="text/javascript" src='<s:url value="/resources/js/jquery-1.9.1.js" />'></script>

<script type="text/javascript" >

$(document).ready(function() {

	var idDisciplina = 0;
	
	$('#codDisciplina').change(function() {
		$('#ementa').val("");
		$('#metodologia').val("");
		$('#avaliacao').val("");
		$('#atendimento').val("");
		$('#recuperacao').val("");
		$('#bibliografia').val("");
		
		$('#blocos').empty();
		
		idDisciplina = $(this).val();
		
		$('#planos-aula').show();
		
		$.post("../planoDisciplinaAluno/buscarDadosDisciplina", {
			idDisciplina : idDisciplina
		}, function(data) {
			if (data['success']) {
				var jsonData = jQuery.parseJSON(data['success']);
				
				$.each(jsonData, function(key, value) {
					$('#ementa').val(value.planoDisciplina.ementa);
					$('#metodologia').val(value.planoDisciplina.metodologia);
					$('#avaliacao').val(value.planoDisciplina.avaliacao);
					$('#atendimento').val(value.planoDisciplina.atendimento);
					$('#recuperacao').val(value.planoDisciplina.recuperacao);
					$('#bibliografia').val(value.planoDisciplina.bibliografia);
				});
			}
		});
	});
	
	$('#planos-aula').click(function() {
		$.post("../planoDisciplinaAluno/buscarDadosDisciplinaAula", {
			idDisciplina : idDisciplina
		}, function(data) {
			if (data['success']) {
				$('#planos-aula').hide();
				
				var jsonData = jQuery.parseJSON(data['success']);
				var blocoDias = '';
				
				$.each(jsonData, function(key, value) {
					blocoDias = blocoDias +
					'<table class="bloco-plano-aula"> ' +
					'	<tr> ' +
					'		<td style="width: 25px;"> '+value.aulaSemestre+' </td> ' +
					'		<td style="width: 95px;"> '+value.dataAula+' </td> ' +
					'		<td> <textarea disabled maxlength="250" name="descricao-dia" id="descricao-dia" placeholder="Descreva este dia" style="background-color: white; max-width: 380px;">'+value.conteudoAula+'</textarea></td> ' +
					'	</tr> ' +
					'</table> ';
				});
				
				$('#blocos').html(blocoDias);
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

						<h1>Plano da Disciplina</h1>

						<div class="flex-div">
							<div-1> <label>Disciplina</label>
							<div class="dropdown">
								<select name="codDisciplina" id="codDisciplina" class="dropdown-select" required="">
									<option value="" disabled="" selected="" style="display: none;">Selecione a disciplina...</option>
									<s:iterator value="disciplinas" var="user" status="stat">
		                				<option value="<s:property value="idDisciplina"/>"> <s:property value="codigoDisciplina"/> - <s:property value="nomeDisciplina"/> </option>
		                			</s:iterator>
								</select>
							</div>
							</div-1>
						</div>
						
						<label>Ementa</label>
						<textarea disabled maxlength="250" name="ementa" id="ementa" placeholder="Descreva a ementa"></textarea>

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
						<h2>Plano das Aulas</h2>
							<center>
								<input id="planos-aula" name="planos-aula" type="submit" value="Visualizar Planos Aula">
							</center>
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