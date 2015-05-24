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

<script type="text/javascript">
	$(document).ready(function() {

		var turmas = new Array()
		
		$('#t01').hide();
		$('#submit').hide();
		
		$('.radio-periodo').click(function() {
			var periodo = $(this).val();

			$.post("../simulacaoAjusteMatricula/buscarDisciplinasPeriodo", {
				periodo : periodo
			}, function(data) {
				if (data['success']) {
					$("#disciplinas-periodo").empty();
					
					var blocoDisciplina = '<form method="post" action="../simulacaoAjusteMatricula/adicionarDisciplina">';
					
					var jsonData = jQuery.parseJSON(data['success']);
					
					$.each(jsonData, function(key, value) {
						blocoDisciplina = blocoDisciplina +
							'	<div class="bloco-disciplina"> ' +
							'		<div style="float: left"> ' +
							'			<input type="checkbox" name="'+value.id+'" value="'+value.id+'" class="'+value.id+'" id="checkbox-disciplina" style="width: 60px; height: 25px; margin-top: 10px" /> ' +
							'		</div> ' +
							'		<div style="float: left; font-size: 20px; margin-top: 10px;">'+value.codigo+'</div> ' +
							'		<div style="float: left; font-size: 20px; margin-top: 10px; margin-left: 10px; margin-right: 10px;"> - </div> ' +
							'		<div style="float: left; font-size: 20px; margin-top: 10px;">'+value.nome+'</div> ' +
							'	</div> ';
					});
					
					$("#disciplinas-periodo").append(blocoDisciplina);
					
					$.each(jsonData, function(key, value) {
						var aux = false;
						for	(var i = 0; i < turmas.length; i++) {
							if(turmas[i] == value.id) {
								aux = true;
							}
						}
	
						if(aux) {
							$("."+value.id).attr("checked",true);
						} 
					});
				} else {
					alert('Turma não existe');
				}
			});
		});
		
		$('#disciplinas-periodo').on('click', '#checkbox-disciplina', function() {
			$('#t01').show();
			$('#submit').show();
			
			var checked = $(this).is(":checked");
			
			// adicionar disciplina
			if(checked) {
				var idTurma = $(this).val();
				
				// marca disciplina no checkbox
				turmas.push(idTurma);
				
				$.post("../simulacaoAjusteMatricula/adicionarDisciplina", {
					idTurma : idTurma
				}, function(data) {
					if (data['success']) {
						var aux = data['success'].split("-");
	
						var dia = '';
						for(var i = 1; i < aux.length; i++) {
							if(aux[i].substring(0, 1) == 'd') {
								dia = aux[i];
							} else {
								var text = $('#'+dia+'-'+aux[i]).text();
								if(text.trim() == '') {
									$('#'+dia+'-'+aux[i]).html(aux[0]);
								} else {
									alert("A disciplina "+text+" bateu horário com a disciplina "+aux[0]);
									break;
								}
							}
						}
					} else {
						//alert('A disciplina bate horario com outra.');
					}
				});
				
			// remover disciplina
			} else {
				var idTurma = $(this).val();
				
				$.post("../simulacaoAjusteMatricula/adicionarDisciplina", {
					idTurma : idTurma
				}, function(data) {
					if (data['success']) {
						var aux = data['success'].split("-");
	
						var dia = '';
						for(var i = 1; i < aux.length; i++) {
							if(aux[i].substring(0, 1) == 'd') {
								dia = aux[i];
							} else {
								// limpa a tabela
								$('#'+dia+'-'+aux[i]).empty();
								
								// retira marcacao no checkbox
								$.each(turmas, function(i){
								    if(turmas[i] === idTurma) {
								    	turmas.splice(i,1);
								        return false;
								    }
								});
							}
						}
					} else {
						//alert('A disciplina bate horario com outra.');
					}
				});
			}
		});
		
		$('#submit').click(function() {
			var cargaHoraria = '';
			
			for(var i = 1; i < 17; i++) {
				for(var j = 1; j < 6; j++) {
					var aux = $('#d'+j+'-'+i).text();
					if(aux.trim() != '') {
						if(cargaHoraria.trim() == '') {
							cargaHoraria = aux+'-d'+j+'-'+i;
						} else {
							cargaHoraria = cargaHoraria+','+aux+'-d'+j+'-'+i;
						}
					}
				}
			}
			
			/*
			$(location).attr('href',"../simulacaoAjusteMatricula/enviarGrade?cargaHoraria=" + cargaHoraria);
			*/
			$.post("../simulacaoAjusteMatricula/enviarGrade", {
				cargaHoraria : cargaHoraria
			}, function(data) {
				if (data['success']) {
					alert('Grade horária enviada com sucesso');
				} else {
					alert('Erro na transmissao');
				}
			});
		});

	});
</script>

<style type="text/css">
.bloco-disciplina {
	background-color: #eee;
	width: 110%;
	height: 45px;
	border-radius: 10px;
	border-style: solid;
	border-width: 1px;
	height: 45px;
	border-radius: 10px;
	border-style: solid;
}

.radio-periodo {
	width: 17px;
}

#disciplinas-periodo{
	margin-top: 20px;
}

#nome-periodo {
	margin-right: 15px;
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
							<li><a href="http://www.portal.facom.ufu.br/node/42/"
								target="_blank"><img
									src='<s:url value="/resources/images/logo-bsi.png" />'></a></li>
							<li><a href="vIndex.php"> Secretaria do Bacharelado em
									Sistemas de Informação - UFU</a></li>
						</ul>
					</div>
					<div class="grid-topbar-right">
						<a href="<s:url value="/login/fazerLogout"/>"><i
							class="fa-sign-out fa"></i>Logout</a>
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

						<h1>Selecione as Disciplinas</h1>

						<div>
							<input type="radio" name="periodo" value="1" class="radio-periodo" /> <span id="nome-periodo" >1° Periodo </span>
							<input type="radio" name="periodo" value="2" class="radio-periodo" /> <span id="nome-periodo" >2° Periodo </span> 
							<input type="radio" name="periodo" value="3" class="radio-periodo" /> <span id="nome-periodo" >3° Periodo </span> 
							<input type="radio" name="periodo" value="4" class="radio-periodo" /> <span id="nome-periodo" >4° Periodo </span> 
							<input type="radio" name="periodo" value="5" class="radio-periodo" /> <span id="nome-periodo" >5° Periodo </span> 
							<input type="radio" name="periodo" value="6" class="radio-periodo" /> <span id="nome-periodo" >6° Periodo </span> 
							<input type="radio" name="periodo" value="7" class="radio-periodo" /> <span id="nome-periodo" >7° Periodo </span> 
							<input type="radio" name="periodo" value="8" class="radio-periodo" /> <span>8° Periodo </span>
						</div>
						<div id="disciplinas-periodo"> </div>
						
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
						
						<center>
							<input id="submit" name="submit" type="submit" value="Enviar Grade">
						</center>
						
					</div>

					<div class="form-help">
						<h2>Dúvidas</h2>
						<text>
						<p>
							Para mais informações sobre este sistema acesse este <a href="#">link</a>.
						</p>
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