<%@page import="java.util.ArrayList"%>
<%@page import="br.com.ufu.bsi.dto.ProgramaPlanoDisciplina"%>
<%@page import="java.util.List"%>
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

<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/util.css"/>" />
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
	
	var idDisciplina = 0;
	var quantDias = 0;
	
	$('#codDisciplina').change(function() {
		idDisciplina = $(this).val();
		
		$.post("../planoDisciplina/buscarDataAula", {
			idDisciplina : idDisciplina
		}, function(data) {
			if (data['success']) {
				
				var jsonData = jQuery.parseJSON(data['success']);
				
				var blocoDias = '';
				var datas = jsonData.objectB.split(';');
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
				
				$('#ementa').val(jsonData.objectA.ementa);
				$('#bibliografia').val(jsonData.objectA.bibliografia);
				$('#blocos').html(blocoDias);
			}
		});
	});
	
	$('#salvar').click(function() {
		var disciplina = idDisciplina;
		var idProgramaPlanoDisciplina = $('#idProgramaPlanoDisciplina').val();
		var idPlanoDisciplina = $('#idPlanoDisciplina').val();;
		/*var ementa = $('#ementa').val();
		var bibliografia = $('#bibliografia').val();*/
		var metodologia = $('#metodologia').val();
		var avaliacao = $('#avaliacao').val();
		var atendimento = $('#atendimento').val();
		var recuperacao = $('#recuperacao').val();
		var descricaoDia = '';
		
		for(var i = 1; i <= quantDias; i++) {
			descricaoDia = descricaoDia + $('#'+i).val()+';';
		}
		
		$.post("../planoDisciplina/salvarPlanoDisciplina", {
			disciplina : disciplina, metodologia : metodologia, avaliacao : avaliacao, atendimento : atendimento, 
			recuperacao : recuperacao, descricaoDia : descricaoDia, idPlanoDisciplina : idPlanoDisciplina, 
			idProgramaPlanoDisciplina: idProgramaPlanoDisciplina
		}, function(data) {
			$('#idProgramaPlanoDisciplina').val("");
			if (data['success']) {
				mensagem("Plano disciplina salvo com sucesso");
			} 
		});
	});
	
	$('.selecionar-plano-disciplina').click(function() {
		var idProgramaPlanoDisciplina = $(this).attr('id');
		
		$.post("../planoDisciplina/buscarPlanoDisciplinaProfessor", {
			idProgramaPlanoDisciplina : idProgramaPlanoDisciplina
		}, function(data) {
			if (data['success']) {
				var jsonData = jQuery.parseJSON(data['success']);
				
				$('#codDisciplina').val(jsonData.objectA.disciplina.idDisciplina);
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
					'		<td> <textarea type="text" placeholder="Descreva este dia" name="descricao-dia" class="descricao-dia" id="'+quantDias+'" maxlength="250" style="background-color: white; max-width: 380px;">'+value.conteudoAula+'</textarea></td> ' +
					'	</tr> ' +
					'</table> ';
					
					quantDias++;
				});
				$('#blocos').html(blocoDias);
			} 
		});
		
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

.selecionar-plano-disciplina {
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
						<h1>Minhas Disciplinas</h1>
						<table style="width:100%;">
							<tr>
								<th>Discplina</th>
								<th>Turma</th>
								<th>Professor</th>
								<th> </th>
							</tr>
							<s:iterator value="programaPlanoDisciplinasProfessor" var="user" status="stat">
								<tr>
				                    <td> <s:property value="disciplina.codigoDisciplina"/> </td>
				           	        <td> <s:property value="disciplina.turma.codigoTurma"/> </td>
				           	        <td> <s:property value="planoDisciplina.professor.nomeProfessor"/> </td>
				           	        <td id="<s:property value="idProgramaPlanoDisciplina"/>" class="selecionar-plano-disciplina"> <i class="fa fa-book fa-fw"></i> </td>
			               		</tr>
				            </s:iterator>
						</table>
					</div>
					
					<%  List<ProgramaPlanoDisciplina> ppds = new ArrayList<ProgramaPlanoDisciplina>();
		            	ppds = (List<ProgramaPlanoDisciplina>) request.getAttribute("programaPlanoDisciplinasReprovado");
					    if(ppds.size() > 0) { %>
					    	<div class="bloco-left">
							<h1>Disciplinas Recusadas</h1>
					    	
						    	<table style="width:100%;">
									<tr>
										<th>Discplina</th>
										<th>Turma</th>
										<th>Professor</th>
										<th> </th>
									</tr>
						    
							    	<s:iterator value="programaPlanoDisciplinasReprovado" var="user" status="stat">
										<tr>
						                    <td> <s:property value="disciplina.codigoDisciplina"/> </td>
						           	        <td> <s:property value="disciplina.turma.codigoTurma"/> </td>
						           	        <td> <s:property value="planoDisciplina.professor.nomeProfessor"/> </td>
						           	        <td id="<s:property value="idProgramaPlanoDisciplina"/>" class="selecionar-plano-disciplina"> <i class="fa fa-book fa-fw"></i> </td>
					               		</tr>
					           	 	</s:iterator>
					           	 </table>
				          	 </div>
					<%  }
					%>
					
					<div class="bloco-left">
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

						<input type="hidden" name="idProgramaPlanoDisciplina" id="idProgramaPlanoDisciplina" />
						<input type="hidden" name="idPlanoDisciplina" id="idPlanoDisciplina" />

						<label>Ementa</label>
						<textarea disabled maxlength="250" name="ementa" id="ementa" placeholder="Descreva a ementa"></textarea>

						<label>Metodologia</label>
						<textarea maxlength="250" name="metodologia" id="metodologia" placeholder="Descreva a metodologia"></textarea>
						
						<label>Avaliação</label>
						<textarea maxlength="250" name="avaliacao" id="avaliacao" placeholder="Descreva as avaliações"></textarea>
						
						<label>Atendimento</label>
						<textarea maxlength="250" name="atendimento" id="atendimento" placeholder="Digite os dias e horários de atendimento"></textarea>
						
						<label>Recuperação</label>
						<textarea maxlength="250" name="recuperacao" id="recuperacao" placeholder="Descreva os meios de recuperacao"></textarea>
						
						<label>Bibliografia</label>
						<textarea disabled maxlength="250" name="bibliografia" id="bibliografia" placeholder="Descreva a bibliografia"></textarea>
						
						<center>
							<input id="salvar" name="salvar" type="submit" value="Salvar">
						</center>
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