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
	
	$('#formulario-professor').hide();
	$('#formulario-aluno').hide();
	
	$('#mensagem-sucesso').hide();
	$('#mensagem-erro').hide();
	
	$('#tipoUsuario').change(function() {
		$('#lista_alunos').empty();
		$('#lista_professores').empty();
		
		tipoUsuario = $(this).val();
		
		$.post("../usuarios/buscarDadosTipoUsuario", {
			tipoUsuario : tipoUsuario
		}, function(data) {
			if (data['success']) {
				var jsonData = jQuery.parseJSON(data['success']);
				if(tipoUsuario == 'A') {
					$('#formulario-aluno').show();
					$('#formulario-professor').hide();
					
					montaListaAluno(jsonData);
					
				} else if(tipoUsuario == 'P') {
					$('#formulario-aluno').hide();
					$('#formulario-professor').show();
					
					montaListaProfessor(jsonData);
				}
			}
		});
	});
		
	$('#lista_professores').on('click', '.editar-professor', function() {
		var idProfessor = $(this).attr('id');
		
		$.post("../usuarios/buscarProfessor", {
				idProfessor : idProfessor
			}, function(data){
    			if(data['success']){
    				var jsonData = jQuery.parseJSON(data['success']);
    				$('#idProfessor').val(jsonData.idProfessor);
					$('#matriculaProfessor').val(jsonData.codigo);
					$('#nomeUsuarioProfessor').val(jsonData.nomeProfessor);
					$('#loginProfessor').val(jsonData.usuario.login);
	    		} else { }
    	});
	});
	
	$('#lista_professores').on('click', '.apagar-professor', function() {
		var idProfessor = $(this).attr('id');
		
		$.post("../usuarios/excluirProfessor", {
				idProfessor : idProfessor
			}, function(data){
    			if(data['error']){
    				mensagemErro("Erro ao Deletar o Professor");
	    		} else {
	    			mensagem("Professor Deletado com Sucesso");
	    			var jsonData = jQuery.parseJSON(data['success']);
	    			montaListaProfessor(jsonData);
	    		}
    		});
	});
	
	$('#salvarProfessor').click(function() {
		var idProfessor = $('#idProfessor').val();
		var matriculaProfessor = $('#matriculaProfessor').val();
		var nomeUsuarioProfessor = $('#nomeUsuarioProfessor').val();
		var loginProfessor = $('#loginProfessor').val();
		var loginProfessor = $('#loginProfessor').val();
		var emailProfessor = $('#emailProfessor').val();
		
		$.post("../usuarios/salvarProfessor", {
				idProfessor : idProfessor, matriculaProfessor : matriculaProfessor, nomeUsuarioProfessor : nomeUsuarioProfessor, 
				loginProfessor : loginProfessor, emailProfessor : emailProfessor
			}, function(data){
    			if(data['error']){
	    		} else {
	    			mensagem("Professor Salvo com Sucesso");
	    			var jsonData = jQuery.parseJSON(data['success']);
	    			montaListaProfessor(jsonData);
	    		}
    	});
	});
	
	$('#limparProfessor').click(function() {
		$('#idProfessor').val("");
		$('#matriculaProfessor').val("");
		$('#nomeUsuarioProfessor').val("");
		$('#loginProfessor').val("");
		$('#emailProfessor').val("");
	});
	
	$('#lista_alunos').on('click', '.editar-aluno', function() {
		var idAluno = $(this).attr('id');
		
		$.post("../usuarios/buscarAluno", {
				idAluno : idAluno
			}, function(data){
    			if(data['success']){
    				var jsonData = jQuery.parseJSON(data['success']);
    				$('#idAluno').val(jsonData.idAluno);
					$('#matriculaAluno').val(jsonData.matricula);
					$('#nomeUsuarioAluno').val(jsonData.nomeAluno);
					$('#loginAluno').val(jsonData.usuario.login);
	    		} else { }
    	});
	});
	
	$('#lista_alunos').on('click', '.apagar-aluno', function() {
		var idAluno = $(this).attr('id');
		
		$.post("../usuarios/excluirAluno", {
				idAluno : idAluno
			}, function(data){
    			if(data['error']){
    				mensagemErro("Erro ao Deletar o Aluno");
	    		} else {
	    			mensagem("Aluno Deletado com Sucesso");
	    			var jsonData = jQuery.parseJSON(data['success']);
	    			montaListaAluno(jsonData);
	    		}
    	});
	});
	
	$('#salvarAluno').click(function() {
		var idAluno = $('#idAluno').val();
		var matriculaAluno = $('#matriculaAluno').val();
		var nomeAluno = $('#nomeUsuarioAluno').val();
		var loginAluno = $('#loginAluno').val();
		var emailAluno = $('#emailAluno').val();
		
		$.post("../usuarios/salvarAluno", {
				idAluno : idAluno, matriculaAluno : matriculaAluno, nomeAluno : nomeAluno,
				loginAluno : loginAluno, emailAluno : emailAluno
			}, function(data){
				console.log(data);
    			if(data['error']){
    				console.log("ERRO");
	    		} else {
	    			mensagem("Aluno Salvo com Sucesso");
	    			var jsonData = jQuery.parseJSON(data['success']);
	    			montaListaAluno(jsonData);
	    		}
    	});
	});
	
	$('#limparAluno').click(function() {
		$('#idAluno').val("");
		$('#matriculaAluno').val("");
		$('#nomeUsuarioAluno').val("");
		$('#loginAluno').val("");
		$('#emailAluno').val("");
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
	
	function montaListaAluno(jsonData) {
		var dados = '' +
				'<table style="width:100%;"> ' +
				'	<tr> ' +
				'		<th>Nome</th> ' +
				'		<th> </th> ' +
				'		<th> </th> ' +
				'	</tr> ';
		$.each(jsonData, function(key, value) {
			dados = dados +
				'	<tr> ' +
	            '       <td> '+value.nomeAluno+'</td> ' +
	            '       <td id="'+value.idAluno+'" class="editar-aluno"> <i class="fa fa-book fa-fw"></i> </td> ' +
	           	'       <td id="'+value.idAluno+'" class="apagar-aluno"> <i class="fa fa-times fa-fw"></i> </td> ' +
                '	</tr> ';
		});
		dados = dados + ' </table> ';
		
		$('#lista_alunos').empty();
		$('#lista_alunos').append(dados);
	}
	
	function montaListaProfessor(jsonData) {
		var dados = '' +
		'<table style="width:100%;"> ' +
		'	<tr> ' +
		'		<th>Nome</th> ' +
		'		<th> </th> ' +
		'		<th> </th> ' +
		'	</tr> ';
		$.each(jsonData, function(key, value) {
			dados = dados +
				'	<tr> ' +
		        '       <td> '+value.nomeProfessor+'</td> ' +
		        '       <td id="'+value.idProfessor+'" class="editar-professor"> <i class="fa fa-book fa-fw"></i> </td> ' +
		       	'       <td id="'+value.idProfessor+'" class="apagar-professor"> <i class="fa fa-times fa-fw"></i> </td> ' +
		        '	</tr> ';
		});
		dados = dados + ' </table> ';
		
		$('#lista_professores').empty();
		$('#lista_professores').append(dados);
	}
	
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

					</div>

					<div class="form-help">
						<h2>Filtrar dados</h2>
						<div id="formulario-aluno">
							<table style="width:100%;">
								<tr>
									<td>Matrícula:</td>
									<td>
										<input id="idAluno" name="idAluno" type="hidden" />
										<input id="matriculaAluno" name="matriculaAluno" type="text" placeholder="Matríicula" />
									</td>
								</tr>
								<tr>
									<td>Nome:</td>
									<td>
										<input id="nomeUsuarioAluno" name="nomeUsuarioAluno" type="text" placeholder="Nome do usuário" />
									</td>
								</tr>
								<tr>
									<td>Login:</td>
									<td>
										<input id="loginAluno" name="loginAluno" type="text" placeholder="Login do usuário" />
									</td>
								</tr>
								<tr>
									<td>E-mail:</td>
									<td>
										<input id="emailAluno" name="emailAluno" type="text" placeholder="Email do usuário" />
									</td>
								</tr>
								<tr>
									<td><input id="limparAluno" name="limparAluno" type="submit" value="Novo"></td>
									<td><input id="salvarAluno" name="salvarAluno" type="submit" value="Salvar"></td>
								</tr>
							</table>
						</div>
						
						<div id="formulario-professor">
							<table style="width:100%;">
								<tr>
									<td>Matrícula:</td>
									<td>
										<input id="idProfessor" name="idProfessor" type="hidden" />
										<input id="matriculaProfessor" name="matriculaProfessor" type="text" placeholder="Matríicula" value="" />
									</td>
								</tr>
								<tr>
									<td>Nome:</td>
									<td>
										<input id="nomeUsuarioProfessor" name="nomeUsuarioProfessor" type="text" placeholder="Nome do usuário" />
									</td>
								</tr>
								<tr>
									<td>Login:</td>
									<td>
										<input id="loginProfessor" name="loginProfessor" type="text" placeholder="Login do usuário" />
									</td>
								</tr>
								<tr>
									<td>E-mail:</td>
									<td>
										<input id="emailProfessor" name="emailProfessor" type="text" placeholder="Email do usuário" />
									</td>
								</tr>
								<tr>
									<td><input id="limparProfessor" name="limparProfessor" type="submit" value="Novo"></td>
									<td><input id="salvarProfessor" name="salvarProfessor" type="submit" value="Salvar"></td>
								</tr>
							</table>
						</div>
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