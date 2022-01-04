package LogicaNegocio;

import java.util.ArrayList;

import Vista.Proveedor.CRUDProveedorVista;
import modelo.VO.ProveedorVO;
import modelo.logica.ProveedorLogica;

public class ProveedorControlador {

	private CRUDProveedorVista vistaCRUDProveedor;
	private ProveedorLogica logicaProveedor;

	private ProveedorControlador() {
		this.vistaCRUDProveedor = new CRUDProveedorVista(this);
		this.logicaProveedor = ProveedorLogica.getInstance();
	}

	public static ProveedorControlador getInstance() {
		return new ProveedorControlador();
	}

	public void aniadirProducto(String tipo) {
		// TODO
	}

	public void modificarProducto(ProveedorVO proveedorAntiguo, String tipo) {
		// TODO
	}

	public void eliminarProducto(ProveedorVO proveedor) {
		// TODO
	}

	public void gestionarAccionProveedor() {

	}

	public ArrayList<ProveedorVO> getProveedores() {
		return logicaProveedor.getProveedores();
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
