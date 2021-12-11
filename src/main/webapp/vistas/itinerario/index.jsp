<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="ttm.model.Usuario"%>
<%@page import="ttm.dao.UsuarioDAO"%>
<%@page import="ttm.manager.UsuarioManager"%>

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

<!-- google font -->
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap"
	rel="stylesheet">

<!-- /google font -->

<link rel="stylesheet" type="text/css"
	href="/ttm_web_voidteam/stylesheets/index.css" />

<jsp:include page="/head.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/nav.jsp"></jsp:include>
	<main class="container">


		<div class="bg-light p-4 mb-3 rounded">
			<div class=titulo>
				<h1 align="center">Promociones compradas</h1>
			</div>
		</div>


		<div>
			<div class="container" style="width: 95%" align="center">
				<table class="table table-stripped table-hover">
					<thead>

						<tr>
							<th>Promoci&oacute;n</th>
							<th>Costo</th>
							<th>Duraci&oacute;n</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaPromocionescompradas}" var="Promocion">
							<tr>
								<td><strong><c:out value="${Promocion.nombre}"></c:out></strong></td>
								<td>$ <c:out value="${Promocion.costo}"></c:out></td>
								<td><c:out value="${Promocion.tiempo}"></c:out> hs.</td>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
		</div>


		<div class="bg-light p-4 mb-3 rounded">
			<div class=titulo>
				<h1 align="center">Atracciones compradas</h1>
			</div>
		</div>


		<div>
			<div class="container" style="width: 95%" align="center">
				<table class="table table-stripped table-hover">
					<thead>

						<tr>
							<th>Atracci&oacute;n</th>
							<th>Costo</th>
							<th>Duraci&oacute;n</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaAtraccionesCompradas}" var="Atraccion">
							<tr>
								<td><strong><c:out value="${Atraccion.nombre}"></c:out></strong></td>
								<td>$ <c:out value="${Atraccion.costo}"></c:out></td>
								<td><c:out value="${Atraccion.tiempo}"></c:out> hs.</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</main>
</body>
</html>

