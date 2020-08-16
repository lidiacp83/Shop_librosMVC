package es.studium.Autores;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AutoresServlet")
public class AutoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Cargamos el listado de autores
		ModeloAutores.ConsultaAutores();
		// Reenviamos a la página de autores
		String nextPage = "";
		nextPage ="/vistas/autores.jsp";
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}
}
