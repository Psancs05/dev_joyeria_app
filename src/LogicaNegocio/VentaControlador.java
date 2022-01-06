package LogicaNegocio;

import java.util.ArrayList;

import modelo.VO.ProductoVO;
import modelo.logica.VentaLogica;
import Vista.Venta.VentaVista;

public class VentaControlador {

	private VentaLogica logicaVenta;
	private VentaVista vistaVenta;
	private static VentaControlador miventaControlador;

    public static VentaControlador getInstance() {
        if (miventaControlador == null) {
            miventaControlador = new VentaControlador();
        }
        return miventaControlador;
    }

	private VentaControlador() {
		this.logicaVenta = new VentaLogica();
		this.vistaVenta = new VentaVista();
	}
	
	public void registrarVenta(ArrayList<ProductoVO> productos) {
		logicaVenta.registrarVenta(productos);
	}

	public VentaLogica getLogicaVenta() {
		return logicaVenta;
	}

	public void setLogicaVenta(VentaLogica logicaVenta) {
		this.logicaVenta = logicaVenta;
	}
}
