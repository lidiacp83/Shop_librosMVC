package es.studium.MenuOpciones;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "/vistas/menuPrincipal.jsp";
		
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);

	}

}
