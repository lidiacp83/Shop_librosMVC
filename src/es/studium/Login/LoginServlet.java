package es.studium.Login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// Recuperar los par�metros usuario y password de la petici�n request
			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");

			// Verificar que existe el usuario y su correspondiente clave
			ModeloLogin.acceso(usuario, password);
			if (ModeloLogin.getTipoUsuario() == null) {
				// Si el resultset no est� vac�o
				out.println("<script type='text/javascript'>");
				out.println("alert('Nombre de usuario o contrase�a incorrectos');");
				out.println("location.href='http://localhost:8080/LibreriaMVC/'");
				out.println("</script>");
			} else {
				// Si los datos introducidos son correctos
				// Crear una sesi�n nueva y guardar el usuario como variable de sesi�n
				// Primero, invalida la sesi�n si ya existe
				HttpSession session = request.getSession(false);
				if (session != null) {
					session.invalidate();
				}
				session = request.getSession(true);
				synchronized (session) {
					session.setAttribute("usuario", usuario);
				}
				if (ModeloLogin.getTipoUsuario().equals("administrador")) {
					response.sendRedirect("http://localhost:8080/LibreriaMVC/MenuServlet");
				} else if (ModeloLogin.getTipoUsuario().equals("cliente")) {
					response.sendRedirect("http://localhost:8080/LibreriaMVC/shopping");
				}

			}

		} catch (SQLException ex) {

			out.println("<script type='text/javascript'>");
			out.println("alert('Servicio no disponible');");
			out.println("location.href='http://localhost:8080/LibreriaMVC/index.jsp'");
			out.println("</script>");

		} finally {
			// Cerramos objetos
			out.close();
		}
		response.getWriter().append("Served at:").append(request.getContextPath());
	}
}