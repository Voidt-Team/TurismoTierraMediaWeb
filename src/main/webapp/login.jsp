<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>


<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap"
	rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

<!-- google font... -->
<link href="https://fonts.googleapis.com/css2?family=Kings&display=swap"
	rel="stylesheet">
<!-- /google font -->
<link rel="stylesheet" type="text/css"
	href="/ttm_web_voidteam/stylesheets/login.css" />
<title>Login - TTM</title>

<style>
body {
	background-image:
		url("https://i2.wp.com/elanillounico.com/wp-content/uploads/2015/11/Tierra-Media-vintage-Juan-M.-VillaSD.jpg");
	background-size: 100% auto;
}
</style>
</head>
<body>

	<form action="login" method="post">
		<h1 align=center>Bienvenidos a</h1>
		<h1 align=center>Turismo en la Tierra Media</h1>
		<!-- <div class="alerta" > -->
		<c:if test="${flash != null}">
			<p style="color: white; opacity: 0.5" align="center">
				<c:out value="${flash}" />
			</p>
		</c:if>



		<div class="mb-3">
			<label for="nombre" class="form-label">Usuario</label> <input
				class="form-control" name="nombre">
		</div>

		<div class="mb-3">
			<label for="password" class="form-label">Contraseña</label> <input
				type="password" class="form-control" name="password">
		</div>

		<div class="d-grid gap-2">
			<button type="submit" class="btn btn-lg btn-primary">Ingresar</button>
		</div>
	</form>

</body>
</html>
