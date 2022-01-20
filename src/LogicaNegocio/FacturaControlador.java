package LogicaNegocio;

import java.util.ArrayList;

import Vista.Facturas.ListadoFacturasVista;

public class FacturaControlador {
	
	private ListadoFacturasVista vistaListadoFacturas;
	private FacturaControlador controladorFacturas;
	
	public FacturaControlador(ListadoFacturasVista vistaListadoFacturas, FacturaControlador controladorFacturas) {
		this.vistaListadoFacturas = vistaListadoFacturas;
		this.controladorFacturas = controladorFacturas;
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
