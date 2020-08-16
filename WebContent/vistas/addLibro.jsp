
<%@page import="es.studium.Editoriales.ModeloEditoriales"%>
<%@page import="es.studium.Autores.ModeloAutores"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*,es.studium.Carrito.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Añadir libro</title>
<link rel="stylesheet" href="css/estilosLibros.css" type="text/css" />
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
				<h1>Añadir libros</h1>
			</div>

			<form class="formLibros" name="AgregarForm" action="LibrosServlet"
				method="POST">
				<table>

					<tr>
						<td><b>Título: &nbsp;&nbsp; </b></td>
						<td><input type="text" name="titulo" size="16" value=""
							required></td>
					</tr>

					<tr>
						<td><b>Precio (€): &nbsp;&nbsp; </b></td>
						<td><input type="text" name="precio" size="16" value=""
							required></td>
					</tr>

					<tr>
						<td><b>Cantidad: &nbsp;&nbsp;</b></td>
						<td><input type="text" name="cantidad" size="16" value=""
							required></td>
					</tr>

					<tr>
						<td><b>Autor:&nbsp;&nbsp;</b></td>
						<td><select class="select-css" name="idAutor">
								<%
									// Scriplet 1: Carga los autores en el SELECT
									for (int i = 0; i < ModeloAutores.tamano(); i++) {
										out.println("<option value='" + ModeloAutores.getIdAutor(i) + "'>");
										out.println(ModeloAutores.getAutor(i));
										out.println("</option>");
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td><b>Editorial:&nbsp;&nbsp;</b></td>
						<td><select class="select-css" name="idEditorial">
								<%
									// Scriplet 1: Carga las editoriales en el SELECT
									for (int i = 0; i < ModeloEditoriales.tamano(); i++) {
										out.println("<option value='" + ModeloEditoriales.getIdEditorial(i) + "'>");
										out.println(ModeloEditoriales.getEditorial(i));
										out.println("</option>");
									}
								%>
						</select></td>
					</tr>
				</table>

				<input name="todo" type="submit" value="Alta libro">
			</form>

			<form class="formLibros" name="AgregarForm" action="LibrosServlet"
				method="POST">
				<input type="submit" value="Volver al menú">
			</form>
		</section>
	</div>
</body>
</html>
