package LogicaNegocio;

import java.util.ArrayList;

import Vista.Proveedor.CRUDProveedorVista;
import modelo.VO.ProveedorVO;
import modelo.logica.ProveedorLogica;

public class ProveedorControlador {

	private static ProveedorControlador miProveedorControlador;
	private CRUDProveedorVista vistaCRUDProveedor;
	private ProveedorVO proveedorActual;
	private ProveedorLogica logicaProveedor;

	public ProveedorControlador() {
		this.vistaCRUDProveedor = new CRUDProveedorVista(this);
		this.logicaProveedor = ProveedorLogica.getInstance();
	}


	public static ProveedorControlador getInstance() {
		return new ProveedorControlador();
	}

	public void aniadirProveedor(String CIF, String nombre) {
		// TODO
	}

	public void modificarProveedor(ProveedorVO proveedorAntiguo, String CIF, String nombre) {
		// TODO
	}

	public void eliminarProveedor(ProveedorVO proveedor) {
		// TODO
	}

	public void mostrarAniadir() {
		vistaCRUDProveedor.pulsarBotonAniadir();
	}

	public void mostrarModificar() {
		// !SI QUEREIS PROBAR EL MODIFICAR DESCOMENTAD LA SIGUIENTE LINEA
		proveedorActual = new ProveedorVO("12345678A", "Nahamahan");
		vistaCRUDProveedor.pulsarBotonModificar(proveedorActual);
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
