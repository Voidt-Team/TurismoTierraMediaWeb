<!DOCTYPE html>
<html lang="es">
<head>

<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="/ttm_web_voidteam/stylesheets/login.css" />
<title>Login - TTM</title>


</head>
<body>

	<c:if test="${flash != null}">
		<div class="alert alert-danger">
			<p>
				<c:out value="${flash}" />
			</p>
		</div>
	</c:if>
	
	<div class="background">
		<div class="shape"></div>
		<div class="shape"></div>
	</div>

	<form action="login" method="post">
		<h2 align=center>Bienvenidos a </h2>
		<h2 align=center>Turismo en la Tierra Media</h2>
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
	
	</form>
</body>
</html>
