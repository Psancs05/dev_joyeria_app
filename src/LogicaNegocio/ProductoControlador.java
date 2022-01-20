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
import modelo.logica.ProductoLogica;

public class ProductoControlador {

	private static ProductoControlador miProductoControlador;
	private ProductoLogica logicaProducto;
	private CRUDProductoVista vistaCRUDProducto;
	ArrayList<ProductoVO> productosEtiqueta;

	private ProductoControlador() {
		this.productosEtiqueta = new ArrayList<ProductoVO>();
		this.logicaProducto = new ProductoLogica();
		this.vistaCRUDProducto = new CRUDProductoVista(this);
	}

	/** 
	 * Metodo que muestra la vista de añadir producto
	 */
	public void mostrarAniadir() {
		this.vistaCRUDProducto = new CRUDProductoVista(this);
		vistaCRUDProducto.pulsarBotonAniadir();
	}

	
	/** 
	 * Metodo que muestra la vista de modificar
	 * @param producto producto a modificar
	 */
	public void mostrarModificar(ProductoVO producto) {
		vistaCRUDProducto.pulsarBotonModificar(producto);
	}

	/** 
	 * Metodo que muestra la vista CRUD
	 */
	public void mostrarEliminar() {
		this.vistaCRUDProducto = new CRUDProductoVista(this);
		vistaCRUDProducto.mostrarListaProductos(false);
	}

	
	/** 
	 * Metodo que pide a la logica que cree un producto con los datos por parametro
	 * @param productoAntiguo producto a modificar
	 * @param nombre nombre
	 * @param numCuaderno numero de cuaderno
	 * @param tipoProducto tipo de producto
	 * @param nombreProveedor nombre del proveedor
	 * @param material material
	 * @param precio precio
	 * @param imagen path de la imagen
	 * @param descripcion descripcion
	 */
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
			e.printStackTrace();
		}
	}

	
	/** 
	 * Metodo que devuelve el listado de todos los productos
	 * @return ArrayList<ProductoVO> listado de productos
	 */
	public ArrayList<ProductoVO> getProductos() {
		return this.logicaProducto.solicitarProductos();
	}

	
	/** 
	 * Metodo pide a la logica que actualice el producto pasado por parametro con los datos que recibe
	 * @param productoAntiguo producto a modificar
	 * @param nombre nombre
	 * @param numCuaderno numero de cuaderno
	 * @param tipoProducto tipo de producto
	 * @param nombreProveedor nombre del proveedor
	 * @param material material
	 * @param precio precio
	 * @param imagen path de la imagen
	 * @param descripcion descripcion
	 * @throws FileNotFoundException excepcion al elegir el archivo
	 */
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

	
	/** 
	 * Metodo que pide a la logica que elimine el producto pasado por parametro
	 * @param producto producto a eliminar
	 */
	public void eliminarProducto(ProductoVO producto) {
		this.logicaProducto.retirarProducto(producto);
	}

	
	/** 
	 * Get
	 * @return ProductoLogica
	 */
	public ProductoLogica getLogicaProducto() {
		return this.logicaProducto;
	}

	
	/** 
	 * Set
	 * @param logicaProducto
	 */
	public void setLogicaProducto(ProductoLogica logicaProducto) {
		this.logicaProducto = logicaProducto;
	}

	
	/** 
	 * Metodo que comprueba si el producto por parametro se encuentra entre los productos selecionados. Si es asi lo borra en caso contrario lo añade a la seleccion
	 * @param producto producto a comprobar
	 */
	public void seleccionProductoEtiqueta(ProductoVO producto) {
		if (this.productosEtiqueta.contains(producto)) {
			this.productosEtiqueta.remove(producto);
			System.out.println("Se elimino el producto de la lista para etiqueta");
		} else {
			this.productosEtiqueta.add(producto);
			System.out.println("Se agrego el producto a la lista para etiqueta");
		}
	}

	public void vaciarSeleccionEtiquetas() {
		productosEtiqueta.clear();
	}

	/** 
	 * Get
	 * @return ArrayList<ProductoVO>
	 */
	public ArrayList<ProductoVO> getProductosEtiqueta() {
		return this.productosEtiqueta;
	}

	/** 
	 * Metodo que limpia los productos selecionados para hacer etiquetas
	 */
	public void clearProductosEtiqueta() {
		this.productosEtiqueta.clear();
	}

	/** 
	 * Metodo Singleton de producto controlador
	 * @return ProductoControlador
	 */
	public static ProductoControlador getInstance() {

		if (miProductoControlador == null) {
			miProductoControlador = new ProductoControlador();
		}
		return miProductoControlador;
	}
}
