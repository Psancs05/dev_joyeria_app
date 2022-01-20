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

	public void mostrarCatalogo() {
		new CatalogoVista(TipoCatalogo.VENDER);
	}

	public void clearProductosVenta() {
		this.productosVenta.clear();
	}

	public void registrarVenta(ArrayList<ProductoVO> productos) {
		logicaVenta.registrarVenta(productos);
	}

	public void mostrarListado() {
		new ListadoFacturasVista(this);
	}

	public ArrayList<VentaVO> getListadoVentas() {
		return logicaVenta.mostrarListadoFacturas();
	}

	public VentaLogica getLogicaVenta() {
		return logicaVenta;
	}

	public void setLogicaVenta(VentaLogica logicaVenta) {
		this.logicaVenta = logicaVenta;
	}

	public void mostrarVenta() {
		this.vistaVenta = new VentaVista(this, productosVenta);
		vistaVenta.setVisible(true);
	}


	public void seleccionProducto(ProductoVO producto) {
		if (productosVenta.contains(producto)) {
			productosVenta.remove(producto);
		} else {
			productosVenta.add(producto);
		}
	}
	
	public void vaciarSeleccionProductos() {
		productosVenta.clear();
	}

	

	public double getPrecioTotal() {
		double total = 0.0;
		for (ProductoVO producto : productosVenta) {
			total = total + producto.getPrecio();
		}
		return total;
	}

	public void aceptarVenta() {
		this.logicaVenta.registrarVenta(productosVenta);
		this.vistaVenta.dispose();
	}

}
