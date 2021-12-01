<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%> 
<%@page import="ttm.Usuario"%>    
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

<title>Turismo en la Tierra Media</title>
</head>
<body>
<h2>Turismo en la Tierra Media</h2>
<h3>Seleccione un usuario</h3>
<% UsuarioDAO usuarioDao = new UsuarioDAO(); 
	List<Usuario> usuarios = usuarioDao.findAll();
%>
<h3><% for(Usuario usuario :usuarios) 
		usuario.getNombre();%>
</h3>
</body>
</html>

