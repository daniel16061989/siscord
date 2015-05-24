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

	var aluno = 0;
	
	$('.buscar-grade-horaria').click(function() {
		var idAluno = $(this).attr('id');
		aluno = idAluno;
		
		$.post("../visualizarSimulacaoAjusteMatricula/buscarGradeHoraria", {
			idAluno : idAluno
		}, function(data) {
			if (data['success']) {
				var jsonData = jQuery.parseJSON(data['success']);
				
				$.each(jsonData, function(key, value) {
					var aux = value.horario.split('-');
					
					var dia = '';
					for(var i = 0; i < aux.length; i++) {
						if(aux[i].substring(0, 1) == 'd') {
							dia = aux[i];
						} else {
							$('#'+dia+'-'+aux[i]).html(value.disciplina);
						}
					}
				});
			}
		});
	});
	
	$('#aprovar').click(function() {
		enviarRespostaAluno(1);
	});
	
	$('#reprovar').click(function() {
		enviarRespostaAluno(0);
	});
	
	function enviarRespostaAluno(tipo) {
		var motivo = $('#motivo').val();
		
		$.post("../visualizarSimulacaoAjusteMatricula/enviarResposta", {
			tipo : tipo, aluno : aluno, motivo : motivo
		}, function(data) {
			if (data['success']) {
				if(tipo == 0) {
					alert("Reprova com sucesso");
				} else {
					alert("Aprova com sucesso");
				}
			}
		});
	}

});
</script>

<style type="text/css">

.buscar-grade-horaria {
	cursor:pointer;
}

table {
	width: 100%;
}

table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
table#t01 tr:nth-child(even) {
    background-color: #eee;
}
table#t01 tr:nth-child(odd) {
   background-color:#fff;
}
table#t01 th	{
    background-color: black;
    color: white;
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
				<div id="content">
					<div class="bloco-left">

						<h1>Grade Horaria</h1>

						<table id="t01" style="margin-top: 15px;">
							<tr>
								<th></th>
							    <th>Segunda</th>		
							    <th>Terça</th>
							    <th>Quarta</th>
							    <th>Quinta</th>
							    <th>Sexta</th>
						  	</tr>
						  	<tr>
							    <td>19h00-19h50</td>
							    <td id="d1-13"></td>		
							    <td id="d2-13"></td>
							    <td id="d3-13"></td>
								<td id="d4-13"></td>
								<td id="d5-13"></td>
						  	</tr>
						  	<tr>
							    <td>19h50-20h40</td>
							    <td id="d1-14"></td>		
							    <td id="d2-14"></td>
							    <td id="d3-14"></td>
								<td id="d4-14"></td>
								<td id="d5-14"></td>
						  	</tr>
						  	<tr>
							    <td>20h50-21h40</td>
							    <td id="d1-15"></td>		
							    <td id="d2-15"></td>
							    <td id="d3-15"></td>
								<td id="d4-15"></td>
								<td id="d5-15"></td>
						  	</tr>
						  	<tr>
							    <td>21h40-22h30</td>
							    <td id="d1-16"></td>		
							    <td id="d2-16"></td>
							    <td id="d3-16"></td>
								<td id="d4-16"></td>
								<td id="d5-16"></td>
						  	</tr>
						</table>
						
						<textarea maxlength="250" name="motivo" id="motivo" placeholder="Escreva o Motivo da ReProvação"></textarea>
						<div>
							<div style="float: left; min-width: 30%; color: white;">a</div>
							<div style="float: left;">
								<input id="aprovar" name="aprovar" type="submit" value="Aprovar">
							</div>
							<div style="float: left;">
								<input id="reprovar" name="reprovar" type="submit" value="Reprovar">
							</div>
						</div>
					</div>

					<div class="form-help">
						<h2>Lista de Grades Horarias</h2>
						
						<table id="t01" style="margin-top: 15px;">
							<tr>
								<th>Matrícula</th>
							    <th>Nome</th>		
							    <th></th>
						  	</tr>
						  	<s:iterator value="alunosGradeHoraria" var="user" status="stat">
						  		<tr>
							    	<td> <s:property value="matricula"/> </td>
							    	<td> <s:property value="nomeAluno"/> </td>
							    	<td id="<s:property value="idAluno"/>" class="buscar-grade-horaria"> <i class="fa fa-book fa-fw"></i> </td>
							    </tr>
						 	</s:iterator>
						 </table>
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