<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="IncludeBootStrap.jsp" />

<title>Login</title>

<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

form {
	border: 3px solid #f1f1f1;
}

input[type=text], input[type=password] {
	width: 100%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
}

button:hover {
	opacity: 0.8;
}

.cancelbtn {
	width: auto;
	padding: 10px 18px;
	background-color: #f44336;
}

.imgcontainer {
	text-align: center;
	margin: 24px 0 12px 0;
}

img.avatar {
	width: 7%;
	border-radius: 50%;
}

.container {
	padding: 16px;
}

span.psw {
	
	padding: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
	span.psw {
		display: block;
		float: none;
	}
	.cancelbtn {
		width: 100%;
	}
}
</style>
</head>
<body>
<jsp:include page="IncludeCabecalho.jsp"/>
	<h2>Efetuar Login</h2>

	<form action="Login" method="post">
	<input type="hidden" name="acao" value="Entrar">
		<div class="imgcontainer">
			<img src="resources\images\img_avatar2.png" alt="Avatar"
				class="avatar">
		</div>

		<div class="container">
			<label for="usuario"><b>Usuario</b></label> <input type="text"
				placeholder="Digite o Usuario" name="usuario" required> <label
				for="senha"><b>Senha</b></label> <input type="password"
				placeholder="Digite a Senha" name="senha" required>

			<button type="submit">Entrar</button>

			<div class="container" style="background-color: #f1f1f1">
				<span class="psw">N�o tem Cadastro? <a href="TelaCadastrarUsuario.jsp">Cadastre-se</a></span>
			</div>

		</div>


	</form>
<jsp:include page="IncludeRodaPe.jsp"/>
</body>
</html>