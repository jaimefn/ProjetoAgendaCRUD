<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<jsp:include page="IncludeBootStrap.jsp"/>

<title>Acesso Negado</title>

<style>
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
}

img.avatar {
  width: 20%;

}


/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  .btnBack {
     width: 100%;
  }
}
</style>
</head>
<body>

<jsp:include page="IncludeCabecalho.jsp"/>

<center>
	<div class="container">
	<br>
	<br>
	<br>
		<div class="alert alert-warning"><P>${param.msg}</P></div>
	<div class="imgcontainer">
		<img class="avatar" src="resources\images\cadeado.png" alt="cadeado">
	</div>
	
			<p>Caso necessário entre em contato com o suporte tecnico</p>
		<hr/>
		<a class="btn btn-success" href="index.jsp">Voltar a Pagina Inicial</a>
	</div>
	</center>
	<jsp:include page="IncludeRodaPe.jsp"/>
</body>
</html>