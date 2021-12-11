<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="mb-3">
	<label for="name" class="col-form-label">Nombre:</label> <input
		type="text" class="form-control" id="name" name="nombre"
		required value="${attraction.nombre}">
</div>
<div class="mb-3">
	<label for="costo"
		class='col-form-label ${attraction.errors.get("cost") != null ? "is-invalid" : "" }'>Costo:</label>
	<input class="form-control" type="number" id="costo" name="costo"
		required value="${attraction.costo}"></input>
	<div class="invalid-feedback">
		<c:out value='${attraction.errors.get("costo")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="tiempo"
		class='col-form-label ${attraction.errors.get("tiempo") != null ? "is-invalid" : "" }'>Duracion:</label>
	<input class="form-control" type="number" id="duration" name="tiempo"
		required value="${attraction.tiempo}"></input>
	<div class="invalid-feedback">
		<c:out value='${attraction.errors.get("tiempo")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="capacity"
		class='col-form-label ${attraction.errors.get("cupo") != null ? "is-invalid" : "" }'>Cupo:</label>
	<input class="form-control" type="number" id="capacity" name="cupo"
		required value="${attraction.cupo}"></input>
	<div class="invalid-feedback">
		<c:out value='${attraction.errors.get("cupo")}'></c:out>
	</div>
</div>
<div class="mb-3">
	<label for="tipo" class="col-form-label">Tipo de atraccion:</label> <input
		type="text" class="form-control" id="tipo" name="tipo"
		required value="${attraction.tipo_atraccion}">
</div>

<div class="mb-3">
	<label for="descripcion" class="col-form-label">Descripcion:</label> <input
		type="text" class="form-control" id="descripcion" name="descripcion"
		required value="${attraction.descripcion}">
</div>
<div class="mb-3">
	<label for="imagen" class="col-form-label">Url de Imagen:</label> <input
		type="text" class="form-control" id="imagen" name="imagen"
		required value="${attraction.imagen}">
</div>

<div>
	<button type="submit" class="btn btn-primary">Guardar</button>
	<a onclick="window.history.back();" class="btn btn-secondary"
		role="button">Cancelar</a>
</div>
