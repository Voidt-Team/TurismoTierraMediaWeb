<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-0">
	<div class="collapse navbar-collapse" id="navbarCollapse">

		<a class="navbar-brand" style="color:white">Turismo en la Tierra
			Media </a>
		<c:if test="${usuario.isAdmin()}">
			<a class="navbar-brand"
				href="/ttm_web_voidteam/vistas/administracion/index.jsp">Administracion</a>
		</c:if>
	</div>

	<div class="d-flex justify-content-end">
		<a class="navbar-brand"style="color:white;"><i style="color:white;"class="bi bi-people-fill"></i><c:out value="${usuario.nombre}"></c:out></a>
		<a class="navbar-brand"style="color:gold;"><i style="color:gold;"class="bi bi-coin"></i><c:out value="${usuario.presupuesto}"></c:out> </a>
		<a class="navbar-brand"style="color:#9cdfdf;"><i style="color:#9cdfdf;"class="bi bi-clock-fill"></i><c:out value="${usuario.tiempo}h"></c:out></a>
		<a class="navbar-brand" href="/ttm_web_voidteam/itinerario/ACompras.do">Itinerario</a> 
		<a class="navbar-brand" href="/ttm_web_voidteam/logout">Salir</a>
	</div>
</nav>
