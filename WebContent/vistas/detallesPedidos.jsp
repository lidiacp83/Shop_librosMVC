<%@page import="java.util.Iterator"%>
<%@page import="es.studium.Pedidos.DetallePedido"%>
<%@page import="es.studium.Pedidos.ModeloPedidos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalle del pedido</title>
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
				<h1>Detalles de pedidos</h1>
			</div>

			<form action="PedidosServlet" method="POST">
				<table class="listaPedidos" style="width: 700px">
					<tr class="cabeceraTabla">
						<td>Título libro</td>
						<td>Cantidad</td>
						<td>Precio</td>
					</tr>
					<%
						// Scriplet 1: Cargo los detalles del pedido
						Iterator<DetallePedido> i = ModeloPedidos.getListaDetalles().iterator();
						while (i.hasNext()) {
							DetallePedido detalle = i.next();
							out.println("<tr><td>" + detalle.getNombreLibro() + "</td>");
							out.println("<td>" + detalle.getCantidad() + "</td>");
							out.println("<td>" + detalle.getPrecio() + " &euro;</td></tr>");
						}
					%>				
					<tr class="total">
						<th  align="center">Total</th>
						<th><%= ModeloPedidos.getTotalCantidad() %> </th>
						<th><%= ModeloPedidos.getTotalPedido() %> &euro;</th>
					</tr>
				</table>
			</form>
 
			<form action="PedidosServlet">
				<input type="submit" value="Volver al listado">
			</form>
		</section>
	</div>
</body>
</html>