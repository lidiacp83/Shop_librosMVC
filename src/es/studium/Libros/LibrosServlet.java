package es.studium.Libros;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.studium.Autores.ModeloAutores;
import es.studium.Editoriales.ModeloEditoriales;

/**
 * Servlet implementation class LibrosServlet
 */
@WebServlet("/LibrosServlet")
public class LibrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String nextPage = "/vistas/libros.jsp";
		String todo = request.getParameter("todo");

		if (todo == null) {
			nextPage = "/vistas/libros.jsp";
		} else if (todo.equals("Modificar")) {
			nextPage = "/vistas/modificarLibro.jsp";
		} else if (todo.equals("Añadir")) {
			nextPage = "/vistas/addLibro.jsp";
		} else if (todo.equals("Alta libro")) {
			String titulo = request.getParameter("titulo");
			String precio = request.getParameter("precio");
			String cantidad = request.getParameter("cantidad");
			String idAutor = request.getParameter("idAutor");
			String idEditorial = request.getParameter("idEditorial");
			ModeloLibro.altaLibros(titulo, precio, cantidad, idAutor, idEditorial);
			nextPage = "/vistas/confirmarAltaLibro.jsp";
		} else if (todo.equals("Buscar")) {
			String idlibro = request.getParameter("idlibro");
			ModeloLibro.obtenerLibros(idlibro);
			nextPage = "/vistas/modificarLibro.jsp";
		} else if (todo.equals("Modificar libro")) {
			int id = Integer.parseInt(request.getParameter("idlibro"));
			String titulo = request.getParameter("titulo");
			float precio = Float.parseFloat(request.getParameter("precio"));
			int cantidad = Integer.parseInt(request.getParameter("cantidad"));
			int idAutor = Integer.parseInt(request.getParameter("idAutor"));
			int idEditorial = Integer.parseInt(request.getParameter("idEditorial"));
			ModeloLibro.modificarLibros(id, titulo, precio, cantidad, idAutor, idEditorial);
			// Limpio los datos de la página para que el formulario esté vacío
			ModeloLibro.setId(0);
			ModeloLibro.setTitulo("");
			ModeloLibro.setPrecio("");
			ModeloLibro.setCantidad("");
			ModeloLibro.setIdAutor(0);
			ModeloLibro.setIdEditorial(0);
			nextPage = "/vistas/confirmarModif.jsp";
		} else if (todo.equals("Volver")) {
			nextPage = "/vistas/modificarLibro.jsp";
		}
		
		// Cargamos los select de autores y editoriales
		ModeloAutores.ConsultaAutores();
		ModeloEditoriales.ConsultaEditoriales();
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}
