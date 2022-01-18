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

	public void modificarProveedor(ProveedorVO proveedor, String CIF, String nombre) {
		this.logicaProveedor.actualizarProveedorVO(proveedor, CIF, nombre);
	}

	public void eliminarProveedor(ProveedorVO proveedor) {
		this.logicaProveedor.retirarProveedorVO(proveedor);
	}

	public ProveedorVO getProveedorPorNombre(String nombre) {
		return this.logicaProveedor.getProveedorPorNombre(nombre);
	}

	// ? Que hace este metodo
	public void gestionarAccionProveedor() {

	}

	public ArrayList<ProveedorVO> getProveedores() {
		System.out.println(this.logicaProveedor);
		return this.logicaProveedor.getProveedores();
	}

	// * Metodos de la vista
	public void mostrarAniadir() {
		vistaCRUDProveedor.pulsarBotonAniadir();
	}

	public void mostrarModificar() {
		vistaCRUDProveedor.mostrarListadoDeProveedores(false);
	}

	public void mostrarEliminar() {
		vistaCRUDProveedor.mostrarListadoDeProveedores(true);
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
