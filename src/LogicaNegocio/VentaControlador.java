package LogicaNegocio;

import java.util.ArrayList;

import modelo.VO.ProductoVO;
import modelo.VO.VentaVO;
import modelo.logica.VentaLogica;
import Vista.Catalogo.CatalogoVista;
import Vista.Facturas.ListadoFacturasVista;
import Vista.Venta.VentaVista;
import globals.enums.TipoCatalogo;

public class VentaControlador {

	private static VentaControlador miVentaControlador;

	private VentaLogica logicaVenta;
	private VentaVista vistaVenta;
	private ArrayList<ProductoVO> productosVenta;

	
	/** 
	 * Metodo Singleton de VentaControlador
	 * @return VentaControlador
	 */
	public static VentaControlador getInstance() {
		if (miVentaControlador == null) {
			miVentaControlador = new VentaControlador();
		}
		return miVentaControlador;
	}

	private VentaControlador() {
		this.productosVenta = new ArrayList<ProductoVO>();
		this.logicaVenta = new VentaLogica();
	}

	/** 
	 * Metodo que crea una vista de catalogo en modo venta
	 */
	public void mostrarCatalogo() {
		new CatalogoVista(TipoCatalogo.VENDER);
	}

	/** 
	 * Metodo que limpia los productos de la venta
	 */
	public void clearProductosVenta() {
		this.productosVenta.clear();
	}

	
	/** 
	 * Metodo que pide a la logica que cree una venta con una lista de productos
	 * @param productos lista de productos a registrar en la venta
	 */
	public void registrarVenta(ArrayList<ProductoVO> productos) {
		logicaVenta.registrarVenta(productos);
	}

	/** 
	 * Metodo que muestra la vista de listadoFacturas
	 */
	public void mostrarListado() {
		new ListadoFacturasVista(this);
	}

	/** 
	 * Metodo que pide a la logica el listado de facturas y lo devuelve
	 * @return ArrayList<VentaVO> listado de todas las facturas
	 */
	public ArrayList<VentaVO> getListadoVentas() {
		return logicaVenta.mostrarListadoFacturas();
	}

	
	/** 
	 * Get
	 * @return VentaLogica
	 */
	public VentaLogica getLogicaVenta() {
		return logicaVenta;
	}

	
	/** 
	 * Set
	 * @param logicaVenta
	 */
	public void setLogicaVenta(VentaLogica logicaVenta) {
		this.logicaVenta = logicaVenta;
	}

	/** 
	 * Metodo que muestra la vista de venta
	 */
	public void mostrarVenta() {
		this.vistaVenta = new VentaVista(this, productosVenta);
		vistaVenta.setVisible(true);
	}
	
	/** 
	 * Metodo que recibe un producto y comprueba si esta el la lista de productos venta. Si es asi lo borra en caso contrario lo añade
	 * @param producto producto a comprobar en la lista de productos de la venta
	 */
	public void seleccionProducto(ProductoVO producto) {
		if (productosVenta.contains(producto)) {
			productosVenta.remove(producto);
			System.out.println("Se elimino el producto de la venta");
		} else {
			productosVenta.add(producto);
			System.out.println("Se agrego el producto a la venta");
		}
	}
	
	/** 
	 * Metodo que muestra la vista de venta
	 */
	public void vaciarSeleccionProductos() {
		productosVenta.clear();
	}

	/** 
	 * Metodo que devuelve el precio total de una lista de productos
	 * @return double precio total de la lista
	 */
	public double getPrecioTotal() {
		double total = 0.0;
		for (ProductoVO producto : productosVenta) {
			total = total + producto.getPrecio();
		}
		return total;
	}

	/** 
	 * Metodo que pide a la logica que registre la venta
	 */
	public void aceptarVenta() {
		this.logicaVenta.registrarVenta(productosVenta);
		this.vistaVenta.dispose();
	}

}
