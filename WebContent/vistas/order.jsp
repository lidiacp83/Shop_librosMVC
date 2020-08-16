<%-- Página de órdenes de pedido --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true" import="java.util.*,es.studium.Carrito.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<link rel="stylesheet" href="css/estilosCarrito.css" type="text/css" />
<title>Pedido</title>
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
				<h1>Tienda Online</h1>
			</div>

			<div class="degradado">
				<form class="tablaCarrito" name="AgregarForm" action="shopping"
					method="POST">

					<input type="hidden" name="todo" value="add"> <b>Título:&nbsp;&nbsp;</b>
					<select class="select-css" name="idLibro">
						<%
							// Scriplet 1: Carga los libros en el SELECT
							for (int i = 0; i < ModeloCarrito.tamano(); i++) {
								out.println("<option value='" + i + "'>");
								out.println(ModeloCarrito.getTitulo(i) + "  |  " + ModeloCarrito.getAutor(i) + "  |  "
										+ ModeloCarrito.getPrecio(i) + " €");
								out.println("</option>");
							}
						%>
					</select> <br> <b>Cantidad: &nbsp;&nbsp; </b> <input type="text"
						name="cantidad" size="5" value="1"> <input type="submit"
						value="Añadir a la cesta"> <br /> <br />
				</form>

				<%
					// Scriplet 2: Chequea el contenido de la cesta
					ArrayList<ElementoPedido> cesta = (ArrayList<ElementoPedido>) session.getAttribute("carrito");
					if (cesta != null && cesta.size() > 0) {
				%>

				<div id="cesta">
					<hr />
					<h3>Tu cesta contiene:</h3>

					<table class="tabla" cellspacing="0" cellpadding="5">
						<tr>
							<th>Título</th>
							<th>Autor</th>
							<th>Precio</th>
							<th>Qty.</th>
							<th>&nbsp;</th>
						</tr>
						<%
							// Scriplet 3: Muestra los libros del carrito
								for (int i = 0; i < cesta.size(); i++) {
									ElementoPedido elementoPedido = cesta.get(i);
						%>
						<tr>
							<form name="borrarForm" action="shopping" method="POST">
								<input type="hidden" name="todo" value="remove"> <input
									type="hidden" name="indiceElemento" value="<%=i%>">
								<td><%=elementoPedido.getTitulo()%></td>
								<td><%=elementoPedido.getAutor()%></td>
								<td align="right"><%=elementoPedido.getPrecio()%> €</td>
								<td align="right"><%=elementoPedido.getCantidad()%></td>
								<td><input type="submit" value="Eliminar"></td>
							</form>
						</tr>
						<%
							}
						%>
					</table>

					<br />
				</div>
				<form name="checkoutForm" action="shopping" method="POST">
					<input type="hidden" name="todo" value="checkout"> <input
						type="submit" value="" width="5" name="borrar"
						style="background: url('img/IconComprar.png') no-repeat; border: none; width: 150px; height: 52px; margin-bottom: 30px;"
						title="Comprar">
				</form>
			</div>
		</section>
	</div>

	<%
		}
	%>

</body>
</html>