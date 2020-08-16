<%@page import="java.text.SimpleDateFormat"%>
<%@page import="es.studium.Pedidos.ModeloPedidos"%>
<%@page import="es.studium.Autores.ModeloAutores"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*,es.studium.Carrito.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pedidos</title>
<link rel="stylesheet" href="css/estilosPedidos.css" type="text/css" />
</head>
<body>
	<div class="container">
		<section id="content">
			<div id="cerrar">
				<form action="logout">
					<input type="submit" value="" width="5" name="borrar"
						style="background: url('img/logout.png') no-repeat; border: none; width: 38px; height: 38px;"
						title="Cerrar sesión" onclick="return confirm('¿Desea salir de la aplicación?')">
				</form>
				<h1>Lista de pedidos</h1>
			</div>

			<form action="PedidosServlet" name="formPedidos" method="POST">
				<table class="listaPedidos" style="width: 700px">
					<tr class="cabeceraTabla">
						<td>Nº Pedido</td>
						<td>Usuario</td>
						<td>Fecha</td>
						<td>Estado</td>
						<td>Opciones</td>
					</tr>
					<%
						SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");

						// Scriplet 1: Carga los libros en la tabla
						for (int i = 0; i < ModeloPedidos.tamano(); i++) {
							out.println("<tr><td>" + ModeloPedidos.getIdPedido(i) + "</td>");
							out.println("<td>" + ModeloPedidos.getUsuario(i) + "</td>");
							out.println("<td>" + formatter.format(ModeloPedidos.getFecha(i)) + "</td>");
							if (ModeloPedidos.getEstado(i)) {
								out.println("<td><input type=checkbox title='Pedido enviado' class=check checked disabled></td>");
							} else {
								out.println("<td><input type=checkbox title='Pedido no enviado' class=check disabled></td>");
							}
							out.println("<td><button name=detalles type=submit value=" + i + ">Detalle</button>");
							out.println("<button name=envio type=submit value=" + i + ">Enviar</button></td></tr>");
						}
					%>
				</table>
			</form>

			<form action="MenuServlet">
				<input type="submit" value="Volver al menú">
			</form>
		</section>
	</div>
</body>
</html>
