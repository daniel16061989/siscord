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

	$('.editar-disciplina').click(function() {
		var idDisciplina = $(this).attr('id');
		
		$.post("../disciplinas/buscarDisciplina", {
			idDisciplina : idDisciplina
		}, function(data) {
			if (data['success']) {
				var jsonData = jQuery.parseJSON(data['success']);
				$('#codigoDisciplina').val(jsonData.codigoDisciplina);
				$('#nomeDisciplina').val(jsonData.nomeDisciplina);
				$('#cargaHoraria').val(jsonData.cargaHoraria);
				$('#periodoDisciplina').val(jsonData.periodo);
				$('#idProfessor').val(jsonData.professor.idProfessor);
				$('#idTurma').val(jsonData.turma.idTurma);
				$('#horariosAula').val(jsonData.horariosAula);
			}
		});
	});
	
	$('.apagar-disciplina').click(function() {
		var idDisciplina = $(this).attr('id');
		
		$.post("../disciplinas/excluirDisciplina", {
			idDisciplina : idDisciplina
		}, function(data) {
			if (data['success']) {
				alert("Disciplina removida com sucesso!");
			}
		});
	});

});
	
</script>

<style type="text/css">

.editar-disciplina {
	cursor:pointer;
}

.apagar-disciplina {
	cursor:pointer;
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
				</ul>
			</nav>
		</div>
		<div class="white-grid-layout">
			<div id="content-box">
				<div id="content">
					<form class="form-left" id="solicitacao" name="solicitacao"
						action="../controller/cSubmeterSolicitacao.php" method="POST"
						enctype="multipart/form-data" novalidate="novalidate">

						<h1>Bem Vindo</h1>

						<table style="width:100%;">
							<tr>
								<th>Sigla</th>
								<th>Nome</th>
								<th>Turma</th>
								<th>Professor</th>
								<th> </th>
								<th> </th>
							</tr>
							<s:iterator value="disciplinas" var="user" status="stat">
								<tr>
				                    <td> <s:property value="codigoDisciplina"/> </td>
				           	        <td> <s:property value="nomeDisciplina"/> </td>
				           	        <td> <s:property value="turma.codigoTurma"/> </td>
				           	        <td> <s:property value="professor.nomeProfessor"/> </td>
				           	        <td id="<s:property value="idDisciplina"/>" class="editar-disciplina"> <i class="fa fa-book fa-fw"></i> </td>
				           	        <td id="<s:property value="idDisciplina"/>" class="apagar-disciplina"> <i class="fa fa-times fa-fw"></i> </td>
			               		</tr>
				            </s:iterator>
						</table>
					</form>

					<div class="form-help">
						<h2>Adicionar ou Atualizar Disciplina</h2>
						<form method="post" action="../disciplinas/salvarDisciplina">
						<table style="width:100%;">
							<tr>
								<td>Codigo:</td>
								<td>
									<input id="codigoDisciplina" name="codigoDisciplina" type="text" placeholder="Sigla da Disciplina" />
								</td>
							</tr>
							<tr>
								<td>Nome:</td>
								<td>
									<input id="nomeDisciplina" name="nomeDisciplina" type="text" placeholder="Nome da Disciplina" />
								</td>
							</tr>
							<tr>
								<td>Carga Horaria:</td>
								<td>
									<input id="cargaHoraria" name="cargaHoraria" type="text" placeholder="Carga Horaria da Disciplina" />
								</td>
							</tr>
							<tr>
								<td>Periodo:</td>
								<td>
									<div class="dropdown">
										<select name="periodoDisciplina" id="periodoDisciplina" class="dropdown-select" required="">
											<option value="" disabled="" selected="" style="display: none;">Selecione o periodo...</option>
				                			<option value="1"> 1° Periodo </option>
				                			<option value="2"> 2° Periodo </option>
				                			<option value="3"> 3° Periodo </option>
				                			<option value="4"> 4° Periodo </option>
				                			<option value="5"> 5° Periodo </option>
				                			<option value="6"> 6° Periodo </option>
				                			<option value="7"> 7° Periodo </option>
				                			<option value="8"> 8° Periodo </option>
				                			<option value="9"> Outro </option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td>Professor:</td>
								<td>
									<div class="dropdown">
										<select name="idProfessor" id="idProfessor" class="dropdown-select" required="">
											<option value="" disabled="" selected="" style="display: none;">Selecione o Professor...</option>
											<s:iterator value="professorDisciplinas" var="user" status="stat">
				                				<option value="<s:property value="idProfessor"/>"> <s:property value="nomeProfessor"/> </option>
				                			</s:iterator>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td>Turma:</td>
								<td>
									<div class="dropdown">
										<select name="idTurma" id="idTurma" class="dropdown-select" required="">
											<option value="" disabled="" selected="" style="display: none;">Selecione a Turma...</option>
											<s:iterator value="turmaDisciplinas" var="user" status="stat">
				                				<option value="<s:property value="idTurma"/>"> <s:property value="codigoTurma"/> </option>
				                			</s:iterator>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td>Horarios:</td>
								<td>
									<input id="horariosAula" name="horariosAula" type="text" placeholder="Horarios da aula" />
								</td>
							</tr>
						</table>
						<center>
							<input id="submit" name="submit" type="submit" value="Salvar">
						</center>
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