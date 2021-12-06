<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%> 
<%@page import="ttm.model.Usuario"%>    
<%@page import="ttm.dao.UsuarioDAO"%>  
<%@page import="ttm.dao.UsuarioDAO"%> 
<%@page import="ttm.manager.UsuarioManager"%> 


<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" 
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
	<link rel="stylesheet" 
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ=="
	crossorigin="anonymous"
	referrerpolicy="no-referrer" />
	
	<link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">
	 
	<link rel="stylesheet" type="text/css" href="/ttm_web_voidteam/stylesheets/index.css"/>
	 

<title>Turismo en la Tierra Media</title>
 <link rel="icon" type="image/png" href="https://upload.wikimedia.org/wikipedia/commons/e/ef/One_ring.png" />
</head>
<body>
<div class="background">
	<div class="cuadro">
			<h5>Bienvenido <%= session.getAttribute("nombre") %> al Turismo en la Tierra Media!!!</h5>
			<br>¿Que actividad deseas hacer hoy?</br>
				
			<%
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Usuario> usuarios = new ArrayList<Usuario>();
			usuarios = usuarioDAO.findAll();
			int option = 0;
			%>
			<select class="form-select form-select-lg mb-3">
			<option selected>Seleccione un usuario </option>
				<%for (Usuario usuario : usuarios) {
					option++;%>
				<option value=option>
					<%=usuario.getNombre()%>
				</option><% } %>
			</select>
			<div class="d-grid gap-2">
					<button type="submit" class="btn btn-outline-secondary" value="Submit">Siguiente</button>
			</div>
		</div>
		
</div>
</body>
</html>

