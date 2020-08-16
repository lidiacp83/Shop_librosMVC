package es.studium.Libros;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class ModeloLibro {

	private static int id = 0;
	private static String titulo = "";
	private static String precio = "";
	private static String cantidad = "";
	private static int idAutor = 0;
	private static int idEditorial = 0;

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

	public static void altaLibros(String titulo, String precio, String cantidad, String idAutor, String idEditorial) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			iniciarPool();
			// Obtener una conexión del pool
			conn = pool.getConnection();
			stmt = conn.createStatement();
			// Paso 4: Ejecutar las sentencias
			// Insertamos los datos del libro 
			String sqlStr = "INSERT INTO libros VALUES (null, '" + titulo + "', " + precio + "," + cantidad + ","
					+ idAutor + "," + idEditorial + " )";
			stmt.executeUpdate(sqlStr);
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

	public static void obtenerLibros(String idlibro) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			iniciarPool();
			// Obtener una conexión del pool
			conn = pool.getConnection();
			stmt = conn.createStatement();
			// Paso 4: Ejecutar las sentencias
			// Se obtiene los datos del libro por el id de libro que se ha indicado
			String sqlStr = "SELECT * FROM libros WHERE idLibro = " + idlibro;
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				id = rs.getInt("idlibro");
				titulo = rs.getString("tituloLibro");
				precio = rs.getString("precioLibro");
				cantidad = rs.getString("cantidadLibro");
				idAutor = rs.getInt("idAutorFK");
				idEditorial = rs.getInt("idEditorialFK");
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
	
	public static void modificarLibros(int id, String titulo, float precio, int cantidad, int idAutor, int idEditorial) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			iniciarPool();
			// Obtener una conexión del pool
			conn = pool.getConnection();
			stmt = conn.createStatement();
			// Paso 4: Ejecutar las sentencias
			// Actualiza los datos del libro indicado por el id del libro
			String sqlStr = "UPDATE libros SET tituloLibro='" + titulo + "', precioLibro= " + precio + ", cantidadLibro= "+ cantidad + ", idAutorFK= " 
			+ idAutor + ", idEditorialFK=" + idEditorial + " WHERE idLibro=" + id;
			stmt.executeUpdate(sqlStr);
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

	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		ModeloLibro.id = id;
	}

	public static String getTitulo() {
		return titulo;
	}

	public static void setTitulo(String titulo) {
		ModeloLibro.titulo = titulo;
	}

	public static String getPrecio() {
		return precio;
	}

	public static void setPrecio(String precio) {
		ModeloLibro.precio = precio;
	}

	public static String getCantidad() {
		return cantidad;
	}

	public static void setCantidad(String cantidad) {
		ModeloLibro.cantidad = cantidad;
	}

	public static int getIdAutor() {
		return idAutor;
	}

	public static void setIdAutor(int idAutor) {
		ModeloLibro.idAutor = idAutor;
	}

	public static int getIdEditorial() {
		return idEditorial;
	}

	public static void setIdEditorial(int idEditorial) {
		ModeloLibro.idEditorial = idEditorial;
	}

}
