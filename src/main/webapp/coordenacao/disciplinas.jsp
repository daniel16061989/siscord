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
var horarios = [];

function overlay() {
	horarios = [];
	el = document.getElementById("overlay");
	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible";
}

function overlayHide() {
	for(var i = 0; i < horarios.length; i++) {
		elementos = document.getElementById(horarios[i]);
		elementos.style.backgroundColor = "white";
	}
	horarios = [];
	el = document.getElementById("overlay");
	el.style.visibility = "hidden";
}

$(document).ready(function() {
	
	$('#mensagem-sucesso').hide();
	$('#mensagem-erro').hide();
	
	$('#lista-disciplinas').on('click', '.editar-disciplina', function() {
		var idDisciplina = $(this).attr('id');
		
		$.post("../disciplinas/buscarDisciplina", {
			idDisciplina : idDisciplina
		}, function(data) {
			if (data['success']) {
				var jsonData = jQuery.parseJSON(data['success']);
				$('#idDisciplina').val(jsonData.idDisciplina);
				$('#codigoDisciplina').val(jsonData.codigoDisciplina);
				$('#nomeDisciplina').val(jsonData.nomeDisciplina);
				$('#cargaHoraria').val(jsonData.cargaHoraria);
				$('#periodoDisciplina').val(jsonData.periodo);
				$('#idProfessor').val(jsonData.professor.idProfessor);
				$('#idTurma').val(jsonData.turma.idTurma);
				$('#horariosAula').val(jsonData.horariosAula);
				$('#ementa').val(jsonData.ementa);
				$('#bibliografia').val(jsonData.bibliografia);
			}
		});
	});
	
	$('#salvarDisciplina').click(function() {
		var idDisciplina = $('#idDisciplina').val();
		var codigoDisciplina = $('#codigoDisciplina').val();
		var nomeDisciplina = $('#nomeDisciplina').val();
		var cargaHoraria = $('#cargaHoraria').val();
		var periodoDisciplina = $('#periodoDisciplina').val();
		var idProfessor = $('#idProfessor').val();
		var idTurma = $('#idTurma').val();
		var horariosAula = $('#horariosAula').val();
		var ementa = $('#ementa').val();
		var bibliografia = $('#bibliografia').val();
		
		$.post("../disciplinas/salvarDisciplina", {
			idDisciplina : idDisciplina, codigoDisciplina : codigoDisciplina, nomeDisciplina : nomeDisciplina, cargaHoraria : cargaHoraria, 
			periodoDisciplina : periodoDisciplina, idProfessor : idProfessor, idTurma : idTurma, horariosAula : horariosAula, ementa : ementa,
			bibliografia : bibliografia
			}, function(data){
    			if(data['error']){
	    		} else {
	    			mensagem("Disciplina salva com Sucesso");
	    			var jsonData = jQuery.parseJSON(data['success']);
	    			montaListaDisciplina(jsonData);
	    		}
    	});
	});
	
	$('#lista-disciplinas').on('click', '.apagar-disciplina', function() {
		var idDisciplina = $(this).attr('id');
		
		$.post("../disciplinas/excluirDisciplina", {
			idDisciplina : idDisciplina
		}, function(data) {
			if (data['error']) {
			} else {
				mensagem("Disciplina Deletada com Sucesso");
				var jsonData = jQuery.parseJSON(data['success']);
    			montaListaDisciplina(jsonData);
			}
		});
	});
	
	$('#limparDisciplina').click(function() {
		$('#idDisciplina').val("");
		$('#codigoDisciplina').val("");
		$('#nomeDisciplina').val("");
		$('#cargaHoraria').val("");
		$('#periodoDisciplina').val("");
		$('#idProfessor').val("");
		$('#idTurma').val("");
		$('#horariosAula').val("");
		$('#ementa').val("");
		$('#bibliografia').val("");
	});
	
	$('#salvar-horarios').click(function() {
		var aux = "";
		for(var i = 0; i < horarios.length; i++) {
			aux = aux + horarios[i];
			if(i != horarios.length-1) {
				aux = aux + '-';
			}
		}
		$('#horariosAula').val(aux);
		horarios = [];
	});
	
	$('#limpar-horarios').click(function() {
		$('.horarios').css('background-color','white');
		horarios = [];
	});
	
	$('.horarios').click(function() {
		var id = $(this).attr('id');
		var aux = horarios.indexOf(id);
		if(aux > -1) {
			horarios.splice(aux, 1);
			$('#'+id).css('background-color', 'white');
		} else {
			horarios.push(id);
			$('#'+id).css('background-color', 'black');
		}
	});
	
	function montaListaDisciplina(jsonData) {
		var dados = '' +
		'<table style="width:100%;" id="t01"> ' +
		'	<tr> ' +
		'		<th>Sigla</th> ' +
		'		<th>Nome</th> ' +
		'		<th>Turma</th> ' +
		'		<th>Professor</th> ' +
		'		<th> </th> ' +
		'		<th> </th> ' +
		'	</tr> ';
		$.each(jsonData, function(key, value) {
			dados = dados +
			'	<tr> ' +
	        '		<td> '+value.codigoDisciplina+' </td> ' +
	  	    '    	<td> '+value.nomeDisciplina+' </td> ' +
	   	    '    	<td> '+value.turma.codigoTurma+' </td> ' +
	   	 	'    	<td> '+value.professor.nomeProfessor+' </td> ' +
	   	    '    	<td id="'+value.idDisciplina+'" class="editar-disciplina"> <i class="fa fa-book fa-fw"></i> </td> ' +
	   	    '    	<td id="'+value.idDisciplina+'" class="apagar-disciplina"> <i class="fa fa-times fa-fw"></i> </td> ' +
	      	'	</tr> ';
		});
		dados = dados + '</table>';
		
		$('#lista-disciplinas').empty();
		$('#lista-disciplinas').append(dados);
	}
	
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
		$('#mensagem-erro').append(mensagem);
		setInterval(fecharMensagem, 4000);
	}

});
	
</script>

<style type="text/css">

.editar-disciplina {
	cursor:pointer;
}

.apagar-disciplina {
	cursor:pointer;
}

#overlay {
     visibility: hidden;
     position: absolute;
     left: 0px;
     top: 0px;
     width:100%;
     height:100%;
     text-align:center;
     z-index: 1000;
}

#overlay div {
     width:300px;
     margin: 100px auto;
     background-color: #fff;
     border:1px solid #000;
     padding:15px;
     text-align:center;
}

.horarios {
	width: 35px;
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
		
		<div id="overlay">
		     <div>
		     	<table border="1">
		     		<tr>
		     			<th> </th>
						<th>Seg</th>
						<th>Ter</th>
						<th>Qua</th>
						<th>Qui</th>
						<th>Sex</th>
						<th>Sab</th>
					</tr>
					<tr>
                    	<td>19:00-19:50</td>
                    	<td class="horarios" id="d1-13"> </td>
                    	<td class="horarios" id="d2-13"> </td>
                    	<td class="horarios" id="d3-13"> </td>
                    	<td class="horarios" id="d4-13"> </td>
                    	<td class="horarios" id="d5-13"> </td>
                    	<td class="horarios" id="d6-13"> </td>
                    </tr>
                    <tr>
                    	<td>19:50-20:40</td>
                    	<td class="horarios" id="d1-14"> </td>
                    	<td class="horarios" id="d2-14"> </td>
                    	<td class="horarios" id="d3-14"> </td>
                    	<td class="horarios" id="d4-14"> </td>
                    	<td class="horarios" id="d5-14"> </td>
                    	<td class="horarios" id="d6-14"> </td>
                    </tr>
                    <tr>
                    	<td>20:50-21:40</td>
                    	<td class="horarios" id="d1-15"> </td>
                    	<td class="horarios" id="d2-15"> </td>
                    	<td class="horarios" id="d3-15"> </td>
                    	<td class="horarios" id="d4-15"> </td>
                    	<td class="horarios" id="d5-15"> </td>
                    	<td class="horarios" id="d6-15"> </td>
                    </tr>
                    <tr>
                    	<td>21:40-22:30</td>
                    	<td class="horarios" id="d1-16"> </td>
                    	<td class="horarios" id="d2-16"> </td>
                    	<td class="horarios" id="d3-16"> </td>
                    	<td class="horarios" id="d4-16"> </td>
                    	<td class="horarios" id="d5-16"> </td>
                    	<td class="horarios" id="d6-16"> </td>
                    </tr>
		     	</table>
		     	
		     	<center style="margin-top: 5px;">
					<button type="button" id="salvar-horarios">Salvar</button>
					<button type="button" id="limpar-horarios">Limpar</button>
					<button type="button" id="cancelar-horarios" onclick='overlayHide()'>Cancelar</button>
				</center>
		     </div>
		</div>
		
		<div class="white-grid-layout">
			<div id="content-box">
				
				<div id="mensagem-sucesso" style="width: 100%; height:50px; background-color: #AFEEEE;"> </div>
				
				<div id="content">
					<div class="bloco-left">

						<h1>Bem Vindo</h1>
	
						<div id="lista-disciplinas">
							<table style="width:100%;" id="t01">
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
						</div>
					</div>

					<div class="form-help">
						<h2>Adicionar ou Atualizar Disciplina</h2>
						<table style="width:100%;">
							<tr>
								<td>Codigo:</td>
								<td>
									<input id="idDisciplina" name="idDisciplina" type="hidden">
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
								<td>Ementa:</td>
								<td>
									<input id="ementa" name="ementa" type="text" placeholder="Ementa da Disciplina" />
								</td>
							</tr>
							<tr>
								<td>Bibliografia:</td>
								<td>
									<input id="bibliografia" name="bibliografia" type="text" placeholder="Bibliografia da Disciplina" />
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
									<input onclick='overlay()' id="horariosAula" name="horariosAula" type="text" placeholder="Horarios da aula" />
								</td>
							</tr>
							<tr>
								<td><input id="limparDisciplina" name="limparDisciplina" type="submit" value="Novo"></td>
								<td><input id="salvarDisciplina" name="salvarDisciplina" type="submit" value="Salvar"></td>
							</tr>
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