package LogicaNegocio;

import com.mysql.cj.jdbc.Blob;

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

	public ProductoControlador() {
		this.logicaProducto = ProductoLogica.getInstance();
		this.vistaCRUDProducto = new CRUDProductoVista(this);
	}

	public void mostrarAniadir() {
		vistaCRUDProducto.pulsarBotonAniadir();
	}

	public void mostrarModificar() {
		// !SI QUEREIS PROBAR EL MODIFICAR DESCOMENTAD ESTO
		// TipoUsuario tipoUsuarioI;
		// tipoUsuarioI = TipoUsuario.ADMINISTRADOR;
		// usuarioActual = new UsuarioVO("12345678A", "Nahamahan", "enreyesmandanga@gmail.com", "bombear", tipoUsuarioI);
		vistaCRUDProducto.pulsarBotonModificar(productoActual);
	}

	public void aniadirProducto(TipoProducto tipoProducto, ProveedorVO proveedor, TipoMaterial material, double precio, Blob imagen, String descripcion, int numCuaderno, int ID) {
		// !ESTO DA ERROR CERDAS
		//this.logicaProducto.registrarProducto(tipoProducto, precio, imagen, material, proveedor, descripcion);
	}
	
	public void modificarProducto(ProductoVO productoAntiguo, String taller, String tipo, String material, int numCuaderno, int ID) {
		//TODO
	}
	
	public void eliminarProducto(ProductoVO producto) {
		//TODO
	}

	public ProductoLogica getLogicaProducto() {
		return logicaProducto;
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
