<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-0">
		<div class="collapse navbar-collapse" id="navbarCollapse">

				<a class="navbar-brand" href="index.jsp">Turismo en la Tierra Media   </a>
		</div>
		<div class="d-flex justify-content-end">
					<i title="usuario" style="color: white;" class="bi bi-people-fill"><c:out value="${usuario.nombre}"></c:out></i>
					<i title="separador" style="color: black" >"   "</i>
					<i title="monedas" style="color: gold;" class="bi bi-coin"><c:out value="${usuario.presupuesto}"></c:out></i> 
					<i title="separador" style="color: black" >"   "</i>
					<i title="tiempo" style="color: #9cdfdf;" class="bi bi-clock-fill"><c:out value="${usuario.tiempo}h"></c:out>  </i> 
					<i title="separador" style="color: black" >"   "</i>
		</div>
		<a class="navbar-brand" href="/ttm_web_voidteam/logout" >Salir</a>
</nav>
