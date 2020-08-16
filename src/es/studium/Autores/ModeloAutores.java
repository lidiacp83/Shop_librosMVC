package es.studium.Autores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class ModeloAutores {
	private static final int MAX_SIZE = obtenerCantidadAutores();
	private static int[] ids = new int[MAX_SIZE];
	private static String[] autores = new String[MAX_SIZE];

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

	public static int obtenerCantidadAutores() {
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
			// Obtenemos la cantidad de autores
			String sqlStr = "SELECT COUNT(*) FROM autores";
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

	public static void ConsultaAutores() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			iniciarPool();
			// Obtener una conexión del pool
			conn = pool.getConnection();
			stmt = conn.createStatement();
			// Paso 4: Ejecutar las sentencias ordenada por el nombre del autor
			// Obtiene el listado de autores ordenados por nombre
			String sqlStr = "SELECT * FROM autores ORDER BY nombreAutor";
			ResultSet rs = stmt.executeQuery(sqlStr);
			// Paso 5: Recoger los resultados y procesarlos
			int cont = 0;
			//Cargamos los datos en los arrays de id y autores
			while (rs.next()) {
				ids[cont] = rs.getInt("idAutor");
				autores[cont] = rs.getString("nombreAutor");
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

	/**
	 * Devuelve el número de autores obtenidos
	 */
	public static int tamano() {
		return autores.length;
	}
	
	/**
	 * Devuelve el id de autor según la posición en el array de ids
	 */
	public static int getIdAutor(int idAutor) {
		return ids[idAutor];
	}
	
	/**
	 * Devuelve el nombre de autor según la posición en el array de autores
	 */
	public static String getAutor(int idAutor) {
		return autores[idAutor];
	}

}
