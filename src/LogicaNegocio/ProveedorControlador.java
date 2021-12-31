package LogicaNegocio;

import Vista.Proveedor.CRUDProveedorVista;
import modelo.VO.ProveedorVO;
import modelo.logica.ProveedorLogica;

public class ProveedorControlador {

	private CRUDProveedorVista vistaCRUDProveedor;
	private ProveedorLogica logicaProveedor;

	public ProveedorControlador(CRUDProveedorVista vistaCRUDProveedor, ProveedorLogica logicaProveedor) {
		super();
		this.vistaCRUDProveedor = vistaCRUDProveedor;
		this.logicaProveedor = logicaProveedor;
	}
	
	public void aniadirProducto(String tipo) {
		//TODO
	}
	
	public void modificarProducto(ProveedorVO proveedorAntiguo, String tipo) {
		//TODO
	}
	
	public void eliminarProducto(ProveedorVO proveedor) {
		//TODO
	}
	
	public void gestionarAccionProveedor() {
		
	}

	public CRUDProveedorVista getVistaCRUDProveedor() {
		return vistaCRUDProveedor;
	}

	public void setVistaCRUDProveedor(CRUDProveedorVista vistaCRUDProveedor) {
		this.vistaCRUDProveedor = vistaCRUDProveedor;
	}

	public ProveedorLogica getLogicaProveedor() {
		return logicaProveedor;
	}

	public void setLogicaProveedor(ProveedorLogica logicaProveedor) {
		this.logicaProveedor = logicaProveedor;
	}
}
