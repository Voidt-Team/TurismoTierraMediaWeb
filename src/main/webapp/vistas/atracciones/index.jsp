<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="ttm.model.Usuario"%>
<%@page import="ttm.dao.UsuarioDAO"%>

<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="/ttm_web_voidteam/stylesheets/index.css" />

<jsp:include page="/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/nav.jsp"></jsp:include>
	<main class="container">

		<c:if test="${flash != null}">
			<div class="alert alert-danger">
				<p>
					<c:out value="${flash}" />
					<c:if test="${errors != null}">
						<ul>
							<c:forEach items="${errors}" var="entry">
								<li><c:out value="${entry.getValue()}"></c:out></li>
							</c:forEach>
						</ul>
					</c:if>
				</p>
			</div>
		</c:if>
		
		<div class="bg-light p-4 mb-3 rounded">
			<h1>Promociones</h1>
		</div>

		<c:if test="${usuario.isAdmin()}">
			<div class="mb-3">
				<a href="/ttm_web_voidteam/promocion/create.do" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nueva Promocion
				</a>
			</div>
		</c:if>

		<div>
			<div class="container" style="width: 95%" align="center">
				<table class="table table-stripped table-hover">
					<thead>
						<tr>
							<th>Imagen</th>
							<th>Promoci&oacute;n</th>
							<th>Costo</th>
							<th>Duraci&oacute;n</th>
							<th>Bonificacion</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaPromociones}" var="Promocion">
							<tr>
								<td><img src="<c:out value="${Promocion.imagen}"></c:out>"
									class="rounded-circle" width="75" height="75" alt="imagen"></td>
								<td><strong><c:out value="${Promocion.nombre}"></c:out></strong>
									<p>
										<c:out value="${Promocion.descripcion}"></c:out>
									</p></td>
								<td>$ <c:out value="${Promocion.costo}"></c:out></td>
								<td><c:out value="${Promocion.tiempo}"></c:out> hs.</td>
								<td><c:out value="${Promocion.bonificacion}"></c:out></td>


								<td><c:if test="${usuario.admin}">
								<a href="/ttm_web_voidteam/promocion/edit.do?id=${Promocion.id}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="<%-- /turismo/attractions/delete.do?id=${attraction.id} --%>"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="bg-light p-4 mb-3 rounded">
			<h1>Atracciones</h1>
		</div>

		<c:if test="${usuario.isAdmin()}">
			<div class="mb-3">
				<a href="/ttm_web_voidteam/attractions/create.do" class="btn btn-primary"
					role="button"> <i class="bi bi-plus-lg"></i> Nueva Atraccion
				</a>
			</div>
		</c:if>

		<div>
			<div class="container" style="width: 95%" align="center">
				<table class="table table-stripped table-hover">
					<thead>
						<tr>
							<th>Imagen</th>
							<th>Atracci&oacute;n</th>
							<th>Costo</th>
							<th>Duraci&oacute;n</th>
							<th>Cupo</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaAtracciones}" var="Atraccion">
							<tr>
								<td><img src="<c:out value="${Atraccion.imagen}"></c:out>"
									class="rounded-circle" width="75" height="75" alt="imagen"></td>
								<td><strong><c:out value="${Atraccion.nombre}"></c:out></strong>
									<p>
										<c:out value="${Atraccion.descripcion}"></c:out>
									</p></td>
								<td>$ <c:out value="${Atraccion.costo}"></c:out></td>
								<td><c:out value="${Atraccion.tiempo}"></c:out> hs.</td>
								<td><c:out value="${Atraccion.cupo}"></c:out></td>


								<td><c:if test="${usuario.admin}">
								<a href="/ttm_web_voidteam/attractions/edit.do?id=${Atraccion.id}"
									class="btn btn-light rounded-0" role="button"><i
									class="bi bi-pencil-fill"></i></a>
								<a href="/ttm_web_voidteam/attractions/delete.do?id=${Atraccion.id}"
									class="btn btn-danger rounded" role="button"><i
									class="bi bi-x-circle-fill"></i></a>
							</c:if></td>



							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</main>
</body>
</html>

