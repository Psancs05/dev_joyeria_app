package LogicaNegocio;

import java.util.ArrayList;

import Vista.Proveedor.CRUDProveedorVista;
import modelo.VO.ProveedorVO;
import modelo.logica.ProveedorLogica;

public class ProveedorControlador {
	// * Atributos
	private static ProveedorControlador miProveedorControlador;
	private CRUDProveedorVista vistaCRUDProveedor;
	private ProveedorLogica logicaProveedor;

	// * Constructor y Singleton
	private ProveedorControlador() {
		this.vistaCRUDProveedor = new CRUDProveedorVista(this);
		this.logicaProveedor = new ProveedorLogica();
	}

	
	/** 
	 * Singleton de ProveedorControlador
	 * @return ProveedorControlador
	 */
	public static ProveedorControlador getInstance() {
		if (miProveedorControlador == null) {
			miProveedorControlador = new ProveedorControlador();
		}
		return miProveedorControlador;
	}

	/** 
	 * Metodo que pide a la logica que se añada un proveedor
	 * @param CIF cif
	 * @param nombre nombre
	 * @return boolean true si se ha podido añadir
	 */
	// * Metodos de la logica
	public boolean aniadirProveedor(String CIF, String nombre) {
		return this.logicaProveedor.registrarProveedorVO(CIF, nombre);
	}

	
	/** 
	 * Metodo que pide a la logica que modifique el proveedor pasado por parametro
	 * @param proveedor proveedor antiguo
	 * @param CIF cif
	 * @param nombre nombre
	 */
	public void modificarProveedor(ProveedorVO proveedor, String CIF, String nombre) {
		this.logicaProveedor.actualizarProveedorVO(proveedor, CIF, nombre);
	}

	
	/** 
	 * Metodo que pide a la logica  que elimine el proveedor pasado por parametro
	 * @param proveedor proveedor a eliminar
	 * @return boolean true si se ha podido eliminar
	 */
	public boolean eliminarProveedor(ProveedorVO proveedor) {
		return this.logicaProveedor.retirarProveedorVO(proveedor);
	}

	
	/** 
	 * Metodo que devuelve un proveedor con el nombre pasado por parametro
	 * @param nombre nombre
	 * @return ProveedorVO proveedor encontrado en la base de datos con ese nombre
	 */
	public ProveedorVO getProveedorPorNombre(String nombre) {
		return this.logicaProveedor.getProveedorPorNombre(nombre);
	}

	/** 
	 * Metodo que devuelve el listado de todos los proveedores
	 * @return ArrayList<ProveedorVO> listado de todos los proveedores
	 */
	public ArrayList<ProveedorVO> getProveedores() {
		return this.logicaProveedor.getProveedores();
	}

	/** 
	 * Metodo que muestra la vista de añadir proveedor
	 */
	public void mostrarAniadir() {
		this.vistaCRUDProveedor = new CRUDProveedorVista(this);
		vistaCRUDProveedor.pulsarBotonAniadir();
	}

	/** 
	 * Metodo que muestra la vista de modificar proveedor
	 */
	public void mostrarModificar() {
		this.vistaCRUDProveedor = new CRUDProveedorVista(this);
		vistaCRUDProveedor.mostrarListadoDeProveedores(false);
	}

	/** 
	 * Metodo que muestra la vista de eliminar proveedor
	 */
	public void mostrarEliminar() {
		this.vistaCRUDProveedor = new CRUDProveedorVista(this);
		vistaCRUDProveedor.mostrarListadoDeProveedores(true);
	}

	/** 
	 * Get
	 */
	public ProveedorLogica getLogicaProveedor() {
		return logicaProveedor;
	}

	
	/** 
	 * Set
	 * @param logicaProveedor
	 */
	public void setLogicaProveedor(ProveedorLogica logicaProveedor) {
		this.logicaProveedor = logicaProveedor;
	}

	
	/** 
	 * Get
	 * @return CRUDProveedorVista
	 */
	public CRUDProveedorVista getVistaCRUDProveedor() {
		return vistaCRUDProveedor;
	}

	
	/** 
	 * Set
	 * @param vistaCRUDProveedor
	 */
	public void setVistaCRUDProveedor(CRUDProveedorVista vistaCRUDProveedor) {
		this.vistaCRUDProveedor = vistaCRUDProveedor;
	}

}
