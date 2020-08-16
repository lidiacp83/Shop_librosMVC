<%-- Página de confirmación del pedido --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*,es.studium.Carrito.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<link rel="stylesheet" href="css/estilosCarrito.css" type="text/css" />
<title>Confirmación</title>
</head>
<body>
	<div id="container">
			
			<section id="content">
			<div id="cerrar">
				<form action="logout">
				<input type="submit" value="" width="5" name="borrar"
						style="background: url('img/logout.png') no-repeat; border: none; width: 38px; height:38px;"
						title="Cerrar sesión" onclick="return confirm('¿Desea salir de la aplicación?')">
				</form>
				<h1>Confirmación pedido</h1>
			</div>
			
			<div class="degradado">
			<h3>Vas a comprar los siguientes artículos:</h3>
					
					<div id="cesta">
					<table class="tabla" cellspacing="0" cellpadding="4">
						<tr>
							<th align="left">Título</th>
							<th align="left">Autor</th>
							<th>Cantidad</th>
							<th>Precio</th>
						</tr>
						<%
							// Muestra los elementos del carrito
							ArrayList<ElementoPedido> cesta = (ArrayList<ElementoPedido>) session.getAttribute("carrito");
							for (ElementoPedido item : cesta) {
						%>
						<tr class="campos">
							<td align="left"><%=item.getTitulo()%></td>
							<td align="left"><%=item.getAutor()%></td>
							<td><%=item.getCantidad()%></td>
							<td><%=item.getPrecio()%> €</td>
						</tr>
						<%
							}
						%>
						<tr class="total">
							<th colspan="2" align="right">Total</th>
							<th><%=request.getAttribute("cantidadTotal")%></th>
							<th><%=request.getAttribute("precioTotal")%> €</th>
						</tr>
					</table><br>
				</div>
					<form action="shopping">
						<input type="submit" value="Seguir comprando">
						<input name="todo" value="Confirmar" type="submit" onclick="return confirm('¿Está seguro que desea confirmar el pedido? ')">
					</form><br>
			</div>
		</section>
	</div>
	
</body>
</html>