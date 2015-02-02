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

	$('#formulario-professor').hide();
	$('#formulario-aluno').hide();
	
	$('#tipoUsuario').change(function() {
		$('#lista_alunos').empty();
		$('#lista_professores').empty();
		
		$('#formulario-professor').hide();
		$('#formulario-aluno').hide();
		
		tipoUsuario = $(this).val();
		
		$.post("../usuarios/buscarDadosTipoUsuario", {
			tipoUsuario : tipoUsuario
		}, function(data) {
			if (data['success']) {
				var jsonData = jQuery.parseJSON(data['success']);
				if(tipoUsuario == 'A') {
					$.each(jsonData, function(key, value) {
						var dados = '' +
							'<table style="width:100%;"> ' +
							'	<tr> ' +
							'		<th>Nome</th> ' +
							'		<th> </th> ' +
							'		<th> </th> ' +
							'	</tr> ' +
							'	<tr> ' +
				            '       <td> '+value.nomeAluno+'</td> ' +
				            '       <td id="'+value.idAluno+'" class="editar-aluno"> <i class="fa fa-book fa-fw"></i> </td> ' +
				           	'       <td id="'+value.idAluno+'" class="apagar-aluno"> <i class="fa fa-times fa-fw"></i> </td> ' +
			                '	</tr> ' +
		               		'</table> ';
					$('#lista_alunos').append(dados);
					});
				} else if(tipoUsuario == 'P') {
					$.each(jsonData, function(key, value) {
						var dados = '' +
							'<table style="width:100%;"> ' +
							'	<tr> ' +
							'		<th>Nome</th> ' +
							'		<th> </th> ' +
							'		<th> </th> ' +
							'	</tr> ' +
							'	<tr> ' +
				            '       <td> '+value.nomeProfessor+'</td> ' +
				            '       <td id="'+value.idProfessor+'" class="editar-professor"> <i class="fa fa-book fa-fw"></i> </td> ' +
				           	'       <td id="'+value.idProfessor+'" class="apagar-professor"> <i class="fa fa-times fa-fw"></i> </td> ' +
			                '	</tr> ' +
		               		'</table> ';
					$('#lista_professores').append(dados);
					});
				}
			}
		});
	});
		
	$('#lista_professores').on('click', '.editar-professor', function() {
		var idProfessor = $(this).attr('id');
		
		$('#formulario-aluno').hide();
		$('#formulario-professor').show();
		
		$.post("../usuarios/buscarProfessor", {
				idProfessor : idProfessor
			}, function(data){
    			if(data['success']) {
    				var jsonData = jQuery.parseJSON(data['success']);
    				$('#idProfessor').val(jsonData.idProfessor);
    				$('#idUsuarioProfessor').val(jsonData.usuario.idUsuario);
    				$('#nomeProfessor').val(jsonData.nomeProfessor);
    				$('#loginProfessor').val(jsonData.usuario.login);
	    		} else { }
    	});
	});
	
	$('.apagar-professor').click(function() {
		var idProfessor = $(this).attr('id');
		
		$.post("../usuarios/excluirProfessor", {
				idProfessor : idProfessor
			}, function(data){
    			if(data['success']) {
	    		} else { }
    	});
	});
	
	$('#lista_alunos').on('click', '.editar-aluno', function() {
		var idAluno = $(this).attr('id');
		
		$('#formulario-professor').hide();
		$('#formulario-aluno').show();
		
		$.post("../usuarios/buscarAluno", {
				idAluno : idAluno
			}, function(data){
    			if(data['success']){
    				var jsonData = jQuery.parseJSON(data['success']);
    				$('#idAluno').val(jsonData.idAluno);
    				$('#idUsuarioAluno').val(jsonData.usuario.idUsuario);
    				$('#simulacaoAjuste').val(jsonData.simulacaoAjuste);
    				$('#nomeAluno').val(jsonData.nomeAluno);
    				$('#matricula').val(jsonData.matricula);
    				$('#loginAluno').val(jsonData.usuario.login);
	    		} else { }
    	});
	});
	
	$('.apagar-aluno').click(function() {
		var idAluno = $(this).attr('id');
		
		$.post("../usuarios/excluirAluno", {
				idAluno : idAluno
			}, function(data){
    			if(data['success']){
	    		} else { }
    	});
	});
	
});
</script>

<style type="text/css">

.editar-professor {
	cursor:pointer;
}

.apagar-professor {
	cursor:pointer;
}
.editar-aluno {
	cursor:pointer;
}

.apagar-aluno {
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

						<div class="dropdown">
							<select name="tipoUsuario" id="tipoUsuario" class="dropdown-select" required="">
								<option value="" disabled="" selected="" style="display: none;">Selecione o Tipo de Usuário...</option>
				                <option value="A"> Aluno </option>
				            	<option value="P"> Professor </option>
							</select>
						</div>

						<div id="lista_professores">
							
						</div>
						<div id="lista_alunos">
						
						</div>

					</form>

					<div class="form-help">
						<h2>Filtrar dados</h2>
						<div id="formulario-aluno">
							<form method="post" action="../usuarios/salvarAluno">
								<table style="width:100%;">
									<tr>
										<td>Matrícula:</td>
										<td>
											<input id="idAluno" name="salvarAluno.idAluno" type="hidden" />
											<input id="idUsuarioAluno" name="salvarAluno.usuario.idUsuario" type="hidden" />
											<input id="simulacaoAjuste" name="salvarAluno.simulacaoAjuste" type="hidden" />
											<input id="matricula" name="salvarAluno.matricula" type="text" placeholder="Matríicula" />
										</td>
									</tr>
									<tr>
										<td>Nome:</td>
										<td>
											<input id="nomeAluno" name="salvarAluno.nomeAluno" type="text" placeholder="Nome do Aluno" />
										</td>
									</tr>
									<tr>
										<td>Login:</td>
										<td>
											<input id="loginAluno" name="salvarAluno.usuario.login" type="text" placeholder="Login do Aluno" />
										</td>
									</tr>
								</table>
								<center>
									<input id="submitAluno" name="submitAluno" type="submit" value="Salvar">
								</center>
							</form>
						</div>
						
						<div id="formulario-professor">
							<form method="post" action="../usuarios/salvarProfessor">
								<table style="width:100%;">
									<tr>
										<td>Nome:</td>
										<td>
											<input id="idProfessor" name="salvarProfessor.idProfessor" type="hidden" />
											<input id="idUsuarioProfessor" name="salvarProfessor.usuario.idUsuario" type="hidden" />
											<input id="nomeProfessor" name="salvarProfessor.nomeProfessor" type="text" placeholder="Nome do Professor" />
										</td>
									</tr>
									<tr>
										<td>Login:</td>
										<td>
											<input id="loginProfessor" name="salvarProfessor.usuario.login" type="text" placeholder="Login do Professor" />
										</td>
									</tr>
								</table>
								<center>
									<input id="submitProfessor" name="submitProfessor" type="submit" value="Salvar">
								</center>
							</form>
						</div>
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