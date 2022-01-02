package LogicaNegocio;

import Vista.Usuario.CRUDUsuarioVista;
import Vista.Usuario.LoginVista;
import modelo.VO.UsuarioVO;
import modelo.logica.UsuarioLogica;

public class UsuarioControlador {

	private static UsuarioControlador miUsuarioControlador;
	private CRUDUsuarioVista vistaCRUDUsuario;
	private LoginVista vistaLogin;
	private UsuarioVO usuarioActual;
	private UsuarioLogica logicaUsuario;
	
	public UsuarioControlador() {
		this.vistaCRUDUsuario = new CRUDUsuarioVista();
		this.vistaLogin = new LoginVista();
		this.logicaUsuario = UsuarioLogica.getInstance();
		this.usuarioActual = logicaUsuario.getUsuarioActivo();
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
	
	public void mostrarAniadir() {
		vistaCRUDUsuario.pulsarBotonAniadir();
	}
	
	public void mostrarModificar() {
		vistaCRUDUsuario.pulsarBotonModificar(usuarioActual);
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
	
	public static UsuarioControlador getInstance() {
		
		if(miUsuarioControlador == null) {
			miUsuarioControlador = new UsuarioControlador();
		}
		
		return miUsuarioControlador;
		
	}
}
