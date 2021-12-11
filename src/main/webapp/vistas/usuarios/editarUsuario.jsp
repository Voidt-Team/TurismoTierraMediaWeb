<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/nav.jsp"></jsp:include>

	<main class="container">

		<c:if test="${user != null && !user.isValid()}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al actualizar la atracción.</p>
			</div>
		</c:if>

		<form action="/ttm_web_voidteam/usuario/edit.do" method="post">
			<input type="hidden" name="id" value="${user.id}">
			
			<jsp:include page="/vistas/usuarios/formEdit.jsp"></jsp:include>
		</form>
	</main>
</body>
</html>
