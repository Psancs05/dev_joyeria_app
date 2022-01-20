package LogicaNegocio;

import Vista.Facturas.ListadoFacturasVista;

public class FacturaControlador {

	private ListadoFacturasVista vistaListadoFacturas;
	private FacturaControlador controladorFacturas;

	public FacturaControlador(ListadoFacturasVista vistaListadoFacturas, FacturaControlador controladorFacturas) {
		this.vistaListadoFacturas = vistaListadoFacturas;
		this.controladorFacturas = controladorFacturas;
	}

	
	/** 
	 * Get
	 * @return ListadoFacturasVista
	 */
	public ListadoFacturasVista getVistaListadoFacturas() {
		return vistaListadoFacturas;
	}

	
	/** 
	 * Set
	 * @param vistaListadoFacturas
	 */
	public void setVistaListadoFacturas(ListadoFacturasVista vistaListadoFacturas) {
		this.vistaListadoFacturas = vistaListadoFacturas;
	}

	
	/** 
	 * Singleton de factura controlador
	 * @return FacturaControlador
	 */
	public FacturaControlador getControladorFacturas() {
		return controladorFacturas;
	}

	
	/** 
	 * Set
	 * @param controladorFacturas
	 */
	public void setControladorFacturas(FacturaControlador controladorFacturas) {
		this.controladorFacturas = controladorFacturas;
	}
}
