package es.studium.Pedidos;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PedidosServlet")
public class PedidosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPage = "/vistas/pedidos.jsp";
		String detalles = request.getParameter("detalles");
		
		if (detalles != null) {
			int idPedido = Integer.parseInt(detalles);
			ModeloPedidos.detallesPedido(idPedido);
			nextPage = "/vistas/detallesPedidos.jsp";
		}
		String envio = request.getParameter("envio");
		if (envio !=null) {
			int idPedido = Integer.parseInt(envio);
			ModeloPedidos.pedidoEnviado(idPedido);
			nextPage = "/vistas/pedidos.jsp";
		}
		ModeloPedidos.ConsultaPedidos();
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}
}