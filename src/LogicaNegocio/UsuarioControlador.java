package LogicaNegocio;

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

	public void gestionarAccionUsuario() {
		// TODO
	}

	public boolean comprobarUsuario(String dni, String password) {
		UsuarioVO usuarioActivo = logicaUsuario.comprobarUsuario(dni, password);
		if(usuarioActivo == null){
			return false;
		} else {
			usuarioActual = usuarioActivo;
			return true;
		}

	}

	public void cerrarSesion() {
		usuarioActual = null;
	}

	public void mostrarLogin() {
		vistaLogin.botonAutentificar();
	}

	public void mostrarAniadir() {
		vistaCRUDUsuario.pulsarBotonAniadir();
	}

	public void mostrarModificar() {
		// !SI QUEREIS PROBAR EL MODIFICAR DESCOMENTAD ESTO
		// TipoUsuario tipoUsuarioI;
		// tipoUsuarioI = TipoUsuario.ADMINISTRADOR;
		// usuarioActual = new UsuarioVO("12345678A", "Nahamahan", "enreyesmandanga@gmail.com", "bombear", tipoUsuarioI);
		vistaCRUDUsuario.pulsarBotonModificar(usuarioActual);
	}

	public void aniadirUsuario(String DNI, String nombre, String correo, String password, TipoUsuario tipo) {
		this.logicaUsuario.registrarUsuario(DNI, nombre, correo, password, tipo);
	}

	public void modificarUsuario(UsuarioVO usuarioAntiguo, String DNI, String nombre, String correo, String password, TipoUsuario tipo) {
		// TODO
	}

	public void eliminarUsuario(UsuarioVO usuarioAntiguo) {
		// TODO
	}

	public static UsuarioControlador getInstance() {

		if (miUsuarioControlador == null) {
			miUsuarioControlador = new UsuarioControlador();
		}

		return miUsuarioControlador;

	}
}
