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
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="/ttm_web_voidteam/stylesheets/index.css" />
<jsp:include page="head.jsp"></jsp:include>
</head>
<body>

	<div class="bd-example">
		<jsp:include page="nav.jsp"></jsp:include>
		<div id="carouselExampleCaptions" class="carousel slide"
			data-bs-ride="carousel">

			<div class="carousel-inner">
				<c:forEach items="${listaAtracciones}" var="atraccion">
					<c:if test="${ atraccion.cupo eq 12}">
						<div class="carousel-item active">
					</c:if>
					<c:if test="${atraccion.cupo ne 12}">
						<div class="carousel-item">
					</c:if>
					<img src=<c:out value="${atraccion.imagen}"></c:out>
						class="bd-placeholder-img bd-placeholder-img-lg d-block w-100"
						width="800" height="400" alt="${atraccion.nombre }">
					<div class="carousel-caption d-none d-md-block"
						style="opacity: 0.5">
						<div class="alert alert-success" role="alert">
							<h4 class="alert-heading">
								<c:out value="${atraccion.nombre }"></c:out>
								<br>
								<c:out value="${atraccion.costo }"></c:out>
							</h4>
						</div>

						<%-- <div style="background-color:white">
							<h4 style="color:black">
								<c:out value="${atraccion.nombre }"></c:out>
							</h4>
							<h5 style="color:black">${atraccion.costo }</h5>
						</div>	 --%>
					</div>
			</div>
			</c:forEach>
			<a class="carousel-control-prev" href="#carouselExampleCaptions"
				role="button" data-bs-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Anterior</span>
			</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
				role="button" data-bs-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Siguiente</span>
			</a>
		</div>
	</div>

	<!-- promociones -->
	<div class="bg-light p-4 mb-3 rounded">
		<div class=titulo>
			<h1 align="center">Listado de Promociones</h1>
		</div>

	</div>


	<!-- atracciones -->
	<div class="bg-light p-4 mb-3 rounded">
		<div class=titulo>
			<h1 align="center">Listado de Atracciones</h1>
		</div>
	</div>

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

							<td><c:choose>
									<c:when
										test="${usuario.tienedinero(Atraccion) && usuario.tienetiempo(Atraccion) && Atraccion.tienecupo(1)}">
										<a
											href="/ttm_web_voidteam/attractions/buy.do?id=${Atraccion.id}"
											class="btn btn-success rounded" role="button">Comprar</a>
									</c:when>
									<c:otherwise>
										<a href="#" class="btn btn-secondary rounded disabled"
											role="button">No se puede comprar</a>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


</body>
</html>

