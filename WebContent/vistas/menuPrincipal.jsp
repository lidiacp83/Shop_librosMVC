<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<link rel="stylesheet" href="css/estilosMenu.css" type="text/css" />
<title>Menú principal</title>
</head>
<body>

	<div id="container">
		<section id="content">
			<div id="cerrar">
				<form action="logout">
					<input type="submit" value="" width="5" name="borrar"
						style="background: url('img/logout.png') no-repeat; border: none; width: 38px; height: 38px;"
						title="Cerrar sesión" onclick="return confirm('¿Desea salir de la aplicación?')">
				</form>
				<h1>Opciones Menú</h1>
			</div>

				<div id="listaMenu">
					<form class="opciones" name="MostrarAutores" action="AutoresServlet" method="POST">
						<input type="hidden" name="todo" value="autores">
						<input class="imagenes"  value="" type="submit" style="background: url('img/autores.jpg') no-repeat;" title="Ir a autores">
					</form>
					
					<form class="opciones" name="MostrarEditoriales" action="EditorialesServlet" method="POST">
						<input type="hidden" name="todo" value="editoriales">
						<input class="imagenes"  value=""  type="submit" style="background: url('img/editoriales.jpg') no-repeat;" title="Ir a editoriales">
					</form>
					
					<form class="opciones" name="MostrarLibros" action="LibrosServlet" method="POST">
						<input type="hidden" name="todo" value="libros">
						<input class="imagenes" value="" type="submit" style="background: url('img/libros.jpg') no-repeat;" title="Ir a libros">
					</form>
					
					<form class="opciones" name="MostrarPedidos" action="PedidosServlet" method="POST">
						<input type="hidden" name="todo" value="pedidos">
						<input class="imagenes"  value="" type="submit" style="background: url('img/pedidos.jpg') no-repeat;" title="Ir a pedidos">
					</form>
				</div>

		</section>
	</div>

</body>
</html>