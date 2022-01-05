package LogicaNegocio;

import java.util.ArrayList;

import modelo.VO.ProductoVO;
import modelo.logica.VentaLogica;
import Vista.Venta.VentaVista;

public class VentaControlador {

	private VentaLogica logicaVenta;
	private VentaLogica vistaVenta;
	private static VentaControlador miventaControlador;

    public static VentaControlador getInstance() {
        if (miventaControlador == null) {
            miventaControlador = new VentaControlador();
        }
        return miventaControlador;
    }

	private VentaControlador() {
		this.logicaVenta = new VentaLogica();
		this.vistaVenta = new VentaLogica();
	}
	
	public void registrarVenta(ArrayList<ProductoVO> productos) {
		int idVenta = logicaVenta.registrarVenta(productos);
		logicaVenta.retirarProductosDeBBDD(productos,idVenta);
	}

	public VentaLogica getLogicaVenta() {
		return logicaVenta;
	}

	public void setLogicaVenta(VentaLogica logicaVenta) {
		this.logicaVenta = logicaVenta;
	}
}
