package es.studium.Carrito;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import es.studium.Login.ModeloLogin;

public class ModeloCarrito {
	private static final int MAX_SIZE = obtenerCantidadLibros();
	private static int[] ids = new int[MAX_SIZE];
	private static String[] titulos = new String[MAX_SIZE];
	private static String[] autores = new String[MAX_SIZE];
	private static String[] precios = new String[MAX_SIZE];

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

	public static int obtenerCantidadLibros() {
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
			// Obtiene la cantidad de libros
			String sqlStr = "SELECT COUNT(*) FROM libros";
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

	public static void cargarDatos() {
// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			iniciarPool();
			// Obtener una conexión del pool
			conn = pool.getConnection();
			stmt = conn.createStatement();
			// Paso 4: Ejecutar las sentencias
			// JOIN para obtener los datos de los libros con el nombre del Autor en vez del id del autor
			String sqlStr = "SELECT l.idLibro, l.tituloLibro, l.precioLibro, l.cantidadLibro, a.nombreAutor FROM libros AS l, autores AS a WHERE l.idAutorFK = a.idAutor";
			ResultSet rs = stmt.executeQuery(sqlStr);
			// Paso 5: Recoger los resultados y procesarlos
			int cont = 0;
			while (rs.next()) {
				ids[cont] = rs.getInt("idLibro");
				autores[cont] = rs.getString("nombreAutor");
				titulos[cont] = rs.getString("tituloLibro");
				precios[cont] = rs.getString("precioLibro");
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

	public static void crearPedido(ArrayList<ElementoPedido> elCarrito) {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			iniciarPool();
			// Obtener una conexión del pool
			conn = pool.getConnection();
			stmt = conn.createStatement();
			// Paso 4: Ejecutar las sentencias
			// Inserta un nuevo pedido Id del cliente, la fecha y el estado que inicialmente es 0 (no enviado)
			String sqlStr = "INSERT INTO pedidos VALUES (null, " + ModeloLogin.getId() + ", CURDATE(), 0)";
			// Ejecutamos el insert indicando que nos devuelva el id del pedido insertado
			stmt.executeUpdate(sqlStr, Statement.RETURN_GENERATED_KEYS);
			// Paso 5: Obtenemos el id del pedido insertado
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			long idPedido = rs.getLong(1); 
			//Paso 6: Insertamos los libros para el pedido
			Iterator<ElementoPedido> iter = elCarrito.iterator();
			while (iter.hasNext()) {
				//Recorremos el carrito y vamos insertando en la tabla de pedidoslibros el id de pedido, 
				// el id del libro y la cantidad para cada uno de los libros que hay en el pedido
				ElementoPedido unElementoPedido = (ElementoPedido) iter.next();
				sqlStr = "INSERT INTO pedidosLibros VALUES (" + idPedido + "," + unElementoPedido.getCantidad() + "," + getId(unElementoPedido.getIdLibro()) + ")";
				stmt.executeUpdate(sqlStr);
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
	 * Devuelve el número de libros obtenidos
	 */
	public static int tamano() {
		return titulos.length;
	}
	
	/**
	 * Devuelve el id del libro identificado con idLibro
	 */
	public static int getId(int idLibro) {
		return ids[idLibro];
	}
	
	/**
	 * Devuelve el título del libro identificado con idLibro
	 */
	public static String getTitulo(int idLibro) {
		return titulos[idLibro];
	}

	/**
	 * Devuelve el autor del libro identificado con idLibro
	 */
	public static String getAutor(int idLibro) {
		return autores[idLibro];
	}

	/**
	 * Devuelve el precio del libro identificado con idLibro
	 */
	public static String getPrecio(int idLibro) {
		return precios[idLibro];
	}
}
