package LogicaNegocio;

import java.util.ArrayList;

import Vista.Proveedor.CRUDProveedorVista;
import modelo.VO.ProveedorVO;
import modelo.logica.ProveedorLogica;

public class ProveedorControlador {
	// * Atributos
	private static ProveedorControlador miProveedorControlador;
	private CRUDProveedorVista vistaCRUDProveedor;
	private ProveedorVO proveedorActual;
	private ProveedorLogica logicaProveedor;

	// * Constructor y Singleton
	private ProveedorControlador() {
		this.vistaCRUDProveedor = new CRUDProveedorVista(this);
		this.logicaProveedor = new ProveedorLogica();
	}

	public static ProveedorControlador getInstance() {
		if (miProveedorControlador == null) {
			miProveedorControlador = new ProveedorControlador();
		}
		return miProveedorControlador;
	}

	// * Metodos de la logica
	public void aniadirProveedor(String CIF, String nombre) {
		this.logicaProveedor.registrarProveedorVO(CIF, nombre);
	}

	public void modificarProveedor(ProveedorVO proveedorAntiguo, String CIF, String nombre) {
		// TODO
	}

	public void eliminarProveedor(ProveedorVO proveedor) {
		this.logicaProveedor.retirarProveedorVO(proveedor);
	}

	// ? Que hace este metodo
	public void gestionarAccionProveedor() {

	}

	public ArrayList<ProveedorVO> getProveedores() {
		return logicaProveedor.getProveedores();
	}

	// * Metodos de la vista
	public void mostrarAniadir() {
		vistaCRUDProveedor.pulsarBotonAniadir();
	}

	public void mostrarModificar() {
		// * Propongo aqui mostrar una lista con los proveedores
		proveedorActual = new ProveedorVO("12345678A", "Nahamahan");
		vistaCRUDProveedor.pulsarBotonModificar(proveedorActual);
	}

	public void mostrarEliminar() {
		this.vistaCRUDProveedor.pulsarBotonEliminar();
	}

	// * Getters y setters
	public ProveedorLogica getLogicaProveedor() {
		return logicaProveedor;
	}

	public void setLogicaProveedor(ProveedorLogica logicaProveedor) {
		this.logicaProveedor = logicaProveedor;
	}

	public CRUDProveedorVista getVistaCRUDProveedor() {
		return vistaCRUDProveedor;
	}

	public void setVistaCRUDProveedor(CRUDProveedorVista vistaCRUDProveedor) {
		this.vistaCRUDProveedor = vistaCRUDProveedor;
	}

}
