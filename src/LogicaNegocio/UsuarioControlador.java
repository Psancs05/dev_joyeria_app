package LogicaNegocio;

import Vista.Usuario.CRUDUsuarioVista;
import Vista.Usuario.LoginVista;
import modelo.VO.UsuarioVO;
import modelo.logica.UsuarioLogica;

public class UsuarioControlador {

	private CRUDUsuarioVista vistaCRUDUsuario;
	private LoginVista vistaLogin;
	private UsuarioVO usuarioActual;
	private UsuarioLogica logicaUsuario;
	
	public UsuarioControlador(CRUDUsuarioVista vistaCRUDUsuario, LoginVista vistaLogin, UsuarioVO usuarioActual,
			UsuarioLogica logicaUsuario) {
		super();
		this.vistaCRUDUsuario = vistaCRUDUsuario;
		this.vistaLogin = vistaLogin;
		this.usuarioActual = usuarioActual;
		this.logicaUsuario = logicaUsuario;
	}
	
	public void gestionarAccionUsuario() {
		//TODO
	}
	
	public void comprobarUsuario(UsuarioVO usuario, String contrasenia) {
		//TODO
	}
	
	public void cerrarSesion() {
		//TODO
	}
	
	public void mostrarLogin() {
		//TODO
	}
	
	public void aniadirUsuario(String tipo, String nombre, String correo, String DNI) {
		//TODO 
	}
	
	public void modificarUsuario(UsuarioVO usuarioAntiguo, String tipo, String nombre, String correo, String DNI) {
		//TODO
	}
	
	public void eliminarUsuario(UsuarioVO usuarioAntiguo) {
		//TODO
	}
}
