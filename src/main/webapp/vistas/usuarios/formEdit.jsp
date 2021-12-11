<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="mb-3">
	<label for="name" class="col-form-label">Nombre:</label> <input
		type="text" class="form-control" id="name" name="nombre"
		required value="${user.nombre}">
</div>
<div class="mb-3">
	<label for="presupuesto"
		class='col-form-label ${user.errors.get("cost") != null ? "is-invalid" : "" }'>Presupuesto:</label>
	<input class="form-control" type="number" id="presupuesto" name="presupuesto"
		required value="${user.presupuesto}"></input>
	<div class="invalid-feedback">
		<c:out value='${promocion.errors.get("costo")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="tiempo"
		class='col-form-label ${user.errors.get("tiempo") != null ? "is-invalid" : "" }'>Tiempo:</label>
	<input class="form-control" type="number" id="duration" name="tiempo"
		required value="${user.tiempo}"></input>
	<div class="invalid-feedback">
		<c:out value='${user.errors.get("tiempo")}'></c:out>
	</div>
</div>

<div class="mb-3">
	<label for="admin" class="col-form-label">Es Admin:</label> <input
		type="text" class="form-control" id="admin" name="admin"
		required value="${user.getAdmin()}">
</div>
<div class="mb-3">
	<label for="password" class="col-form-label">Password:</label> <input
		type="text" class="form-control" id="password" name="password"
		required value="${user.password}">
</div>


<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>

