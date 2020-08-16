package es.studium.Pedidos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

public class ModeloPedidos {
	private static final int MAX_SIZE = obtenerCantidadPedidos();
	private static int[] ids = new int[MAX_SIZE];
	private static String[] usuarios = new String[MAX_SIZE];
	private static Date[] fecha = new Date[MAX_SIZE];
	private static boolean[] estado = new boolean[MAX_SIZE];
	private static ArrayList<DetallePedido> listaDetalles;
	private static float totalPedido;
	private static int totalCantidad;

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

	public static int obtenerCantidadPedidos() {
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
			// Obtiene la cantidad de pedidos
			String sqlStr = "SELECT COUNT(*) FROM pedidos";
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

	public static void ConsultaPedidos() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;
		try {
			iniciarPool();
			// Obtener una conexión del pool
			conn = pool.getConnection();
			stmt = conn.createStatement();
			// Paso 4: Ejecutar las sentencias
			// JOIN para mostrar el id de pedido, el nombre de usuario, la fecha y el estado del pedido
			String sqlStr = "SELECT p.idPedido, u.nombreUsuario, p.fechaPedido, p.estadoPedido FROM pedidos p, usuarios u WHERE p.idUsuarioFK = u.idUsuario";
			ResultSet rs = stmt.executeQuery(sqlStr);
			// Paso 5: Recoger los resultados y procesarlos
			int cont = 0;
			while (rs.next()) {
				ids[cont] = rs.getInt("idPedido");
				usuarios[cont] = rs.getString("nombreUsuario");
				fecha[cont] = rs.getDate("fechaPedido");
				estado[cont] = rs.getBoolean("estadoPedido");
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
	
	public static void detallesPedido(int idPedido) {
		// Creamos objetos para la conexión
				Connection conn = null;
				Statement stmt = null;
				try {
					iniciarPool();
					// Obtener una conexión del pool
					conn = pool.getConnection();
					stmt = conn.createStatement();
					// Paso 4: Ejecutar las sentencias
					// JOIN para mostrar el titulo, cantidad y precio de cada libro del pedido
					String sqlStr = "SELECT l.tituloLibro, pl.cantidadLibro, l.precioLibro FROM pedidoslibros pl, libros l WHERE pl.idLibroFK = l.idLibro AND pl.idPedidoFK = " + ids[idPedido];
					ResultSet rs = stmt.executeQuery(sqlStr);
					listaDetalles = new ArrayList<DetallePedido>();
					totalPedido = 0;
					totalCantidad = 0;
					// Paso 5: Recoger los resultados y procesarlos
					while (rs.next()) {
						DetallePedido detalle = new DetallePedido();
						detalle.setNombreLibro(rs.getString("tituloLibro"));
						int cantidad = rs.getInt("cantidadLibro");
						detalle.setCantidad(cantidad);
						float precio = rs.getFloat("precioLibro");
						detalle.setPrecio(precio);
						listaDetalles.add(detalle);
						totalPedido = totalPedido + (cantidad * precio);
						totalCantidad = totalCantidad + cantidad;
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
	
	public static void pedidoEnviado(int idPedido) {
		// Creamos objetos para la conexión
				Connection conn = null;
				Statement stmt = null;
				try {
					iniciarPool();
					// Obtener una conexión del pool
					conn = pool.getConnection();
					stmt = conn.createStatement();
					// Paso 4: Ejecutar las sentencias
					// Actualiza el estado del pedido de 0 a 1, es decir de no enviado a enviado
					String sqlStr = "UPDATE pedidos SET estadoPedido = 1 WHERE idPedido = " + ids[idPedido];
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
	
	public static int tamano() {
		return ids.length;
	}
	

	public static int getIdPedido(int idPedido) {
		return ids[idPedido];
	}


	public static String getUsuario(int idUsuario) {
		return usuarios[idUsuario];
	}
	
	public static Date getFecha(int fechaPedido) {
		return fecha[fechaPedido];
	}
	
	public static boolean getEstado(int estadoPedido) {
		return estado[estadoPedido];
	}

	public static ArrayList<DetallePedido> getListaDetalles() {
		return listaDetalles;
	}

	public static void setListaDetalles(ArrayList<DetallePedido> listaDetalles) {
		ModeloPedidos.listaDetalles = listaDetalles;
	}

	public static float getTotalPedido() {
		return totalPedido;
	}

	public static void setTotalPedido(float totalPedido) {
		ModeloPedidos.totalPedido = totalPedido;
	}

	public static int getTotalCantidad() {
		return totalCantidad;
	}

	public static void setTotalCantidad(int totalCantidad) {
		ModeloPedidos.totalCantidad = totalCantidad;
	}
	


	
}
