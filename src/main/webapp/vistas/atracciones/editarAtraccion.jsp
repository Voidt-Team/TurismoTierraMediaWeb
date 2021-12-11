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

		<c:if test="${attraction != null && !attraction.isValid()}">
			<div class="alert alert-danger">
				<p>Se encontraron errores al actualizar la atracción.</p>
			</div>
		</c:if>

		<form action="/ttm_web_voidteam/attractions/edit.do" method="post">
			<input type="hidden" name="id" value="${attraction.id}">
			
			<jsp:include page="/vistas/atracciones/formAtraccion.jsp"></jsp:include>
		</form>
	</main>
</body>
</html>
