package LogicaNegocio;

import java.util.ArrayList;

import modelo.VO.ProductoVO;
import modelo.VO.VentaVO;
import modelo.logica.VentaLogica;
import Vista.Facturas.ListadoFacturasVista;
import Vista.Venta.VentaVista;

public class VentaControlador {

	private static VentaControlador miVentaControlador;

	private VentaLogica logicaVenta;
	private VentaVista vistaVenta;
	private ListadoFacturasVista listado;
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
		this.listado = new ListadoFacturasVista();

	}

	public void registrarVenta(ArrayList<ProductoVO> productos) {
		logicaVenta.registrarVenta(productos);
	}

	public void mostrarListado() {
		listado.initialize();
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
		// actualizarVentaVista();
		this.vistaVenta = new VentaVista(this, productosVenta);
		vistaVenta.setVisible(true);
	}

	// public void actualizarVentaVista() {
	// vistaVenta.setProductos(productosVenta);
	// }

	public void seleccionProducto(ProductoVO producto) {
		if (productosVenta.contains(producto)) {
			productosVenta.remove(producto);
			System.out.println("Se elimino el producto de la venta");
		} else {
			productosVenta.add(producto);
			System.out.println("Se agrego el producto a la venta");
		}
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
