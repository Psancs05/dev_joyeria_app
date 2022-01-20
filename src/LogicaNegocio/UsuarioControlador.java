package LogicaNegocio;

import java.util.ArrayList;

import Vista.Usuario.CRUDUsuarioVista;
import Vista.Usuario.LoginVista;
import globals.enums.TipoUsuario;
import modelo.VO.UsuarioVO;
import modelo.logica.UsuarioLogica;

public class UsuarioControlador {

	private static UsuarioControlador miUsuarioControlador;
	private CRUDUsuarioVista vistaCRUDUsuario;
	private LoginVista vistaLogin;
	private UsuarioVO usuarioActual;
	private UsuarioLogica logicaUsuario;

	public UsuarioControlador() {
		this.vistaCRUDUsuario = new CRUDUsuarioVista(this);
		this.vistaLogin = new LoginVista(this);
		this.logicaUsuario = new UsuarioLogica();
		this.usuarioActual = null;
	}

	
	/** 
	 * Metodo que pide a la logica que compruebe si existe un usuario con el un par DNI contraseña
	 * @param dni dni del usuario a comprobar
	 * @param password contraseña a comprobar
	 * @return boolean  true si existe el usuario false en caso contrario.
	 */
	public boolean comprobarUsuario(String dni, String password) {
		boolean response = logicaUsuario.comprobarUsuario(dni, password);
		if (response) {
			usuarioActual = logicaUsuario.getUsuarioPorDNI(dni);
		}else{
			usuarioActual = null;
		}
		return response;

	}

	/** 
	 * Metodo que cambia el usuario actual a null ya que se cierra sesion
	 */
	public void cerrarSesion() {
		usuarioActual = null;
	}

	/** 
	 * Metodo que muestra la vista de login
	 */
	public void mostrarLogin() {
		this.vistaLogin = new LoginVista(this);
		vistaLogin.botonAutentificar();
	}

	/** 
	 * Muestra la vista de añadir usuario
	 */
	public void mostrarAniadir() {
		this.vistaCRUDUsuario = new CRUDUsuarioVista(this);
		vistaCRUDUsuario.pulsarBotonAniadir();
	}

	/** 
	 * Muestra la vista de eliminar usuario
	 */
	public void mostrarEliminar() {
		this.vistaCRUDUsuario = new CRUDUsuarioVista(this);
		vistaCRUDUsuario.mostrarListadoDeUsuarios(true);
	}

	
	/** 
	 * Metodo que devuelve un ArrayList con todos los usuarios en la base de datos
	 * @return ArrayList<UsuarioVO> lista con todos los usuarios
	 */
	public ArrayList<UsuarioVO> getUsuarios() {
		return this.logicaUsuario.solicitarUsuarios();
	}

	/** 
	 * Metodo que muestra la vista de modificar usuario
	 */
	public void mostrarModificar() {
		this.vistaCRUDUsuario = new CRUDUsuarioVista(this);
		vistaCRUDUsuario.mostrarListadoDeUsuarios(false);
	}

	/** 
	 * Metodo que recibe los datos de un usario y pide a la logica que ese usuario en la base de datos
	 * @param DNI dni
	 * @param nombre nombre
	 * @param correo correo
	 * @param password contraseña
	 * @param tipo tipo del usuario(administrado o cajero)
	 * @return boolean true si se ha podido añadir o false si no ha sido posible
	 */
	public boolean aniadirUsuario(String DNI, String nombre, String correo, String password, TipoUsuario tipo) {
		return this.logicaUsuario.registrarUsuario(DNI, nombre, correo, password, tipo);
	}

	
	/** 
	 * Metodo que recibe los datos de un usuario y un usuario antiguo  y pide a la logica que lo actulice
	 * @param usuarioAntiguo usuario que contiene los datos del usuario que se quiere modificar
	 * @param DNI dni
	 * @param nombre nombre
	 * @param correo correo
	 * @param password contraseña
	 * @param tipo tipo del usuario(administrado o cajero)
	 */
	public void modificarUsuario(UsuarioVO usuarioAntiguo, String DNI, String nombre, String correo, String password,
			TipoUsuario tipo) {
		this.logicaUsuario.actualizarUsuario(usuarioAntiguo, nombre, correo, password, tipo);
	}

	
	/** 
	 * Metodo que pide a la logica que se elimine el usuario pasado por parametro
	 * @param usuario usuario a eliminar 
	 * @return boolean true si se ha podido false en caso contrario
	 */
	public boolean eliminarUsuario(UsuarioVO usuario) {
		return this.logicaUsuario.retirarUsuario(usuario);
	}

	
	/** 
	 * Metodo singleton de usuarioControlador
	 * @return UsuarioControlador
	 */
	public static UsuarioControlador getInstance() {

		if (miUsuarioControlador == null) {
			miUsuarioControlador = new UsuarioControlador();
		}
		return miUsuarioControlador;
	}

	
	/** 
	 * Metodo que comprueba si el usuario por parametro es el mismo que el usuarioActual de la sesion
	 * @param usuario usuario a comprobar
	 * @return boolean true si son el mismo usuario false en caso contrario
	 */
	public boolean mismoUsuarioActual(UsuarioVO usuario){
		return usuario.getDNI().equals(this.usuarioActual.getDNI());
	}

	
	/** 
	 * Metodo que comprueba si hay mas de un admin
	 * @return boolean true si hay mas de un admin
	 */
	public boolean suficientesAdmins(){
		return logicaUsuario.suficientesAdmins();
	}

	/** 
	 * Get de UsuarioActual
	 * @return UsuarioVO
	 */
	public UsuarioVO getUsuarioActual(){
		return usuarioActual;
	}
}
