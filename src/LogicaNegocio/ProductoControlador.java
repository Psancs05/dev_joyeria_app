package LogicaNegocio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Vista.Producto.CRUDProductoVista;
import globals.enums.TipoMaterial;
import globals.enums.TipoProducto;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;
import modelo.VO.UsuarioVO;
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
		this.vistaCRUDProducto = new CRUDProductoVista(this);
		vistaCRUDProducto.pulsarBotonAniadir();
	}

	public void mostrarModificar(ProductoVO producto) {
		// this.vistaCRUDProducto = new CRUDProductoVista(this);
		// vistaCRUDProducto.mostrarListaProductos(true);
		vistaCRUDProducto.pulsarBotonModificar(producto);
	}

	public void mostrarEliminar() {
		this.vistaCRUDProducto = new CRUDProductoVista(this);
		vistaCRUDProducto.mostrarListaProductos(false);
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

	public void modificarProducto(ProductoVO productoAntiguo, String nombre, int numCuaderno, TipoProducto tipoProducto,
			String nombreProveedor,
			TipoMaterial material,
			double precio,
			String imagen, String descripcion) throws FileNotFoundException, IOException {
		ProveedorVO proveedor = ProveedorControlador.getInstance().getProveedorPorNombre(nombreProveedor);
		this.logicaProducto.actualizarProducto(productoAntiguo, nombre, numCuaderno, tipoProducto, proveedor, material,
				precio,
				imagen,
				descripcion);
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
