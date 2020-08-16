<%@page import="es.studium.Editoriales.ModeloEditoriales"%>
<%@page import="es.studium.Autores.ModeloAutores"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*,es.studium.Carrito.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editoriales</title>
<link rel="stylesheet" href="css/estilosMenu.css" type="text/css" />
</head>
<body>
	<div class="container">
		<section id="content">
			<div id="cerrar">
				<form action="logout">
					<input type="submit" value="" width="5" name="borrar"
						style="background: url('img/logout.png') no-repeat; border: none; width: 38px; height: 38px;"
						title="Cerrar sesión"
						onclick="return confirm('¿Desea salir de la aplicación?')">
				</form>
				<h1>Lista editoriales</h1>
			</div>

			<table style="width: 700px">
				<tr class="cabeceraTabla">
					<td>ID.</td>
					<td>Nombre editorial</td>
				</tr>
				<%
					// Scriplet 1: Carga los libros en el SELECT
					for (int i = 0; i < ModeloEditoriales.tamano(); i++) {
						out.println("<tr><td>" + ModeloEditoriales.getIdEditorial(i) + "</td>");
						out.println("<td>" + ModeloEditoriales.getEditorial(i) + "</td></tr>");
					}
				%>
			</table>

			<form action="MenuServlet">
				<input type="submit" value="Volver al menú">
			</form>
		</section>
	</div>
</body>
</html>
