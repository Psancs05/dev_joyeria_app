package LogicaNegocio;

import java.util.ArrayList;

import modelo.VO.ProductoVO;
import modelo.logica.VentaLogica;

public class VentaControlador {

	private VentaLogica logicaVenta;
		
	public VentaControlador(VentaLogica logicaVenta) {
		this.logicaVenta = logicaVenta;
	}
	
	public void registrarVenta(ArrayList<ProductoVO> productos) {
		//TODO
	}

	public VentaLogica getLogicaVenta() {
		return logicaVenta;
	}

	public void setLogicaVenta(VentaLogica logicaVenta) {
		this.logicaVenta = logicaVenta;
	}
}
