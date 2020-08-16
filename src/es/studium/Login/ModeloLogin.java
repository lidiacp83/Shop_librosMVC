package es.studium.Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class ModeloLogin {
	private static int id = 0;
	private static String nombreUsuario = null;
	private static String tipoUsuario = null;

	// Pool de conexiones a la base de datos
	private static DataSource pool;

	private static void iniciarPool() throws ServletException {
		try {
			// Crea un contexto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			// Busca el recurso DataSource en el contexto
			pool = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql_tiendalibros");
			if (pool == null) {
				throw new ServletException("DataSource desconocida 'mysql_tiendalibros'");
			}
		} catch (NamingException ex) {
		}
	}

	public static void acceso(String usuario, String password) throws ServletException, SQLException {
		Connection conn = null;
		Statement stmt = null;

		try {
			iniciarPool();

			// Obtener una conexión del pool
			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Ejecuto la Query
			StringBuilder sqlStr = new StringBuilder();
			sqlStr.append(
					"SELECT u.idUsuario, u.nombreUsuario, p.tipoPerfil FROM usuarios AS u, perfilesUsuario AS p WHERE ");
			sqlStr.append("STRCMP(u.nombreUsuario,'").append(usuario).append("') = 0");
			sqlStr.append(" AND STRCMP(u.claveUsuario,MD5('").append(password).append("')) = 0");
			sqlStr.append(" AND u.idPerfilFK = p.idPerfil");
			ResultSet rset = stmt.executeQuery(sqlStr.toString());
			while (rset.next()) {
				id = rset.getInt("idUsuario");
				nombreUsuario = rset.getString("nombreUsuario");
				tipoUsuario = rset.getString("tipoPerfil");
			}
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					// Esto devolvería la conexión al pool
					conn.close();
				}
			} catch (SQLException ex) {
			}
		}
	}

	public static int getId() {
		return id;
	}

	public static void setId(int identificador) {
		id = identificador;
	}

	public static String getNombreUsuario() {
		return nombreUsuario;
	}

	public static void setNombreUsuario(String nombre) {
		nombreUsuario = nombre;
	}

	public static String getTipoUsuario() {
		return tipoUsuario;
	}

	public static void setTipoUsuario(String tipo) {
		tipoUsuario = tipo;
	}

}
