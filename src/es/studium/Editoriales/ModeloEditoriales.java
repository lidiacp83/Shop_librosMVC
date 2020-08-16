package es.studium.Editoriales;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class ModeloEditoriales {
	private static final int MAX_SIZE = obtenerCantidadEditoriales();
	private static int[] ids = new int[MAX_SIZE];
	private static String[] editoriales = new String[MAX_SIZE];

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

	public static int obtenerCantidadEditoriales() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		int count = 0;
		try {
			iniciarPool();
			// Obtener una conexión del pool
			conn = pool.getConnection();
			stmt = conn.createStatement();
			// Paso 4: Ejecutar las sentencias
			String sqlStr = "SELECT COUNT(*) FROM editoriales";
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Cerramos el resto de recursos
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return count;
	}

	public static void ConsultaEditoriales() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			iniciarPool();
			// Obtener una conexión del pool
			conn = pool.getConnection();
			stmt = conn.createStatement();
			// Paso 4: Ejecutar las sentencias

			String sqlStr = "SELECT * FROM editoriales ORDER BY nombreEditorial";
			ResultSet rs = stmt.executeQuery(sqlStr);
			// Paso 5: Recoger los resultados y procesarlos
			int cont = 0;
			while (rs.next()) {
				ids[cont] = rs.getInt("idEditorial");
				editoriales[cont] = rs.getString("nombreEditorial");
				cont++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Cerramos el resto de recursos
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static int tamano() {
		return editoriales.length;
	}

	public static int getIdEditorial(int idEditorial) {
		return ids[idEditorial];
	}

	public static String getEditorial(int idEditorial) {
		return editoriales[idEditorial];
	}

}
