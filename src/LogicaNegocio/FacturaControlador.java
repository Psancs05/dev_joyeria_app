package LogicaNegocio;

import java.util.ArrayList;

import Vista.Facturas.ListadoFacturasVista;
import modelo.VO.VentaVO;

public class FacturaControlador {
	
	private ListadoFacturasVista vistaListadoFacturas;
	private FacturaControlador controladorFacturas;
	
	public FacturaControlador(ListadoFacturasVista vistaListadoFacturas, FacturaControlador controladorFacturas) {
		this.vistaListadoFacturas = vistaListadoFacturas;
		this.controladorFacturas = controladorFacturas;
	}
	
	public void realizarBackupFacturas(ArrayList<VentaVO> ventas, String ubicacion) {
		//TODO
	}
	
	public void copiaFactura(VentaVO venta) {
		//TODO
	}
	
	public void generarFactura(VentaVO venta) {
		//TODO
	}
	
	public void mostrarFacturaEnVentana(VentaVO venta) {
		//TODO
	}
	
	public void mostrarFactura() {
		//TODO
	}

	public ListadoFacturasVista getVistaListadoFacturas() {
		return vistaListadoFacturas;
	}

	public void setVistaListadoFacturas(ListadoFacturasVista vistaListadoFacturas) {
		this.vistaListadoFacturas = vistaListadoFacturas;
	}

	public FacturaControlador getControladorFacturas() {
		return controladorFacturas;
	}

	public void setControladorFacturas(FacturaControlador controladorFacturas) {
		this.controladorFacturas = controladorFacturas;
	}
}
