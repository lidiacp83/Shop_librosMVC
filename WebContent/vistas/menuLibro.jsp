<%@page import="es.studium.Editoriales.ModeloEditoriales"%>
<%@page import="es.studium.Autores.ModeloAutores"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*,es.studium.Carrito.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Añadir Libros</title>
<link rel="stylesheet" href="css/estilosMenu.css" type="text/css" />
</head>
<body>
	<div class="container">
		<section id="content">
			<div id="cerrar">
				<form action="logout">
					<input type="submit" value="cerrar"  name="todo" width="5" name="borrar"
						style="background: url('img/logout.png') no-repeat; border: none; width: 38px; height: 38px;"
						title="Cerrar sesión" onclick="return confirm('¿Desea salir de la aplicación?')">
				</form>
				<h1>Añadir libros</h1>
			</div>

			<div class="listaLibros">
				<form action="MenuServlet">
					<input type="submit" value="Añadir un libro">
				</form>
				
				<form action="MenuServlet">
					<input type="submit" value="Modificar un Libro">
				</form>
				
				<form action="MenuServlet">
					<input type="submit" value="Volver al menú">
				</form>
			</div>
		</section>
	</div>
</body>
</html>