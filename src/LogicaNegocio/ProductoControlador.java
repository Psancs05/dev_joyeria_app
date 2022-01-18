package LogicaNegocio;

import java.sql.SQLException;
import java.util.ArrayList;

import Vista.Producto.CRUDProductoVista;
import globals.enums.TipoMaterial;
import globals.enums.TipoProducto;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;
import modelo.logica.ProductoLogica;

public class ProductoControlador {

	private static ProductoControlador miProductoControlador;
	private ProductoLogica logicaProducto;
	private CRUDProductoVista vistaCRUDProducto;
	private ProductoVO productoActual;

	private ProductoControlador() {
		this.logicaProducto = new ProductoLogica();
		this.vistaCRUDProducto = new CRUDProductoVista(this);
	}

	public void mostrarAniadir() {
		vistaCRUDProducto.pulsarBotonAniadir();
	}

	public void mostrarModificar() {
		// !SI QUEREIS PROBAR EL MODIFICAR DESCOMENTAD ESTO
		// TipoUsuario tipoUsuarioI;
		// tipoUsuarioI = TipoUsuario.ADMINISTRADOR;
		// usuarioActual = new UsuarioVO("12345678A", "Nahamahan",
		// "enreyesmandanga@gmail.com", "bombear", tipoUsuarioI);
		vistaCRUDProducto.pulsarBotonModificar(productoActual);
	}

	public void mostrarEliminar() {
		vistaCRUDProducto.pulsarBotonEliminar();
	}

	public void aniadirProducto(String nombre, int numCuaderno, TipoProducto tipoProducto, String nombreProveedor,
			TipoMaterial material,
			double precio,
			String imagen, String descripcion) {
		try {
			ProveedorVO proveedor = ProveedorControlador.getInstance().getProveedorPorNombre(nombreProveedor);
			this.logicaProducto.registrarProducto(nombre, numCuaderno, tipoProducto, precio, imagen, material,
					proveedor,
					descripcion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<ProductoVO> getProductos() {
		return this.logicaProducto.solicitarProductos();
	}

	public void modificarProducto(ProductoVO productoAntiguo, String taller, String tipo, String material,
			int numCuaderno, int ID) {
		// TODO
	}

	public void eliminarProducto(ProductoVO producto) {
		this.logicaProducto.retirarProducto(producto);
	}

	public ProductoLogica getLogicaProducto() {
		return this.logicaProducto;
	}

	public void setLogicaProducto(ProductoLogica logicaProducto) {
		this.logicaProducto = logicaProducto;
	}

	public static ProductoControlador getInstance() {

		if (miProductoControlador == null) {
			miProductoControlador = new ProductoControlador();
		}

		return miProductoControlador;

	}
}
