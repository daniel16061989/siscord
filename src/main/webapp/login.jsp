<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>

<link rel="shortcut icon" href="<s:url value="/resources/images/favicon.ico"/>" type="image/x-icon">
<link rel="icon"  href="<s:url value="/resources/images/favicon.ico"/>" type="image/x-icon">

<title>Secretaria do Bacharelado em Sistemas de Informação - UFU</title>

<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta name="description" content="">
<meta name="keywords" content="">

<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/font-awesome.css"/>" />
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/bebas-neue.css"/>" />
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/normalize.css"/>" />
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/style.css"/>" />

<script type="text/javascript" src='<s:url value="/resources/js/function.js" />'></script>

<style type="text/css">

</style>

</head>

<body class="homepage">
	<div id="wrapper">
	
		<s:if test="hasActionErrors()">
	 		<div class="alert alert-danger">
	 			<a href="#" class="close" data-dismiss="alert">&times;</a>
	 			<strong>Erros</strong>
	 			<s:actionerror/>
	 		</div>
	 	</s:if>
	 	<s:if test="hasActionMessages()">
	 		<div class="alert alert-success">
	 			<a href="#" class="close" data-dismiss="alert">&times;</a>
	 			<strong>Sucesso</strong>
	 			<s:actionmessage/>
	 		</div>
	 	</s:if>
	
		<header id="header">
			<div class="top-bar">
				<div class="grid-layout">
					<ul class="top-bar-content">
						<li> <a href="http://www.portal.facom.ufu.br/node/42/" target="_blank"> <img src='<s:url value="/resources/images/logo-bsi.png" />'> </a> </li>
						<li><a href="vIndex.php"> Secretaria do Bacharelado em Sistemas de Informação - UFU</a></li>
					</ul>
				</div>
			</div>
		</header>
		<div class="login-layout">
			<div id="login-div1">
				<a href=""><i class="fa-user fa"></i> Login</a>
			</div>
			<div id="login-form">
				<form class="form-login" method="post" action="../login/fazerLogin">

					<label>Usuário</label> 
					<input name="login" type="text" id="login" placeholder="Digite o usuário" maxlength="32" autofocus="autofocus" required=""> 
					<label>Senha</label>
					<input name="senha" type="password" id="senha" placeholder="Digite a senha" maxlength="32" required="">

					<center>
						<input id="submit" name="submit" type="submit" value="Entrar">
					</center>
				</form>
			</div>
			<div id="login-div2">
				<a a href="<s:url value="/cadastrarAluno"/>"><i class="fa-envelope-o fa"></i> cadastrar-se</a>
			</div>
		</div>
	</div>

</body>
</html>