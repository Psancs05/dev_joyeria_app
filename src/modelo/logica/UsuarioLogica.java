package modelo.logica;

import globals.enums.TipoUsuario;
import modelo.DAO.UsuarioDAO;
import modelo.VO.UsuarioVO;

public class UsuarioLogica {
    private static UsuarioLogica miUsuarioLogica;
    private UsuarioDAO usuarioDAO;
    private UsuarioVO usuarioActivo;

    public static UsuarioLogica getInstance() {
        if (miUsuarioLogica == null) {
            miUsuarioLogica = new UsuarioLogica();
        }
        return miUsuarioLogica;
    }

    private UsuarioLogica() {
        this.usuarioDAO = UsuarioDAO.getInstance();
    }

    public boolean comprobarUsuario(String dni, String password) {
        boolean response = this.usuarioDAO.autenticarUsuario(dni, password);
        UsuarioVO usuario = (UsuarioVO) usuarioDAO.search(new UsuarioVO(dni, ".", ".", password, TipoUsuario.CAJERO));
        if(response) {
        	this.usuarioActivo = usuario;
        }
        return response;
    }

    public void cerrarSesion() {
        // TODO: implement
    }

    public UsuarioVO registrarUsuario(String dni, String nombre, String email, String password,
            TipoUsuario tipoUsuario) {
        final UsuarioVO nuevoUsuario = new UsuarioVO(dni, nombre, email, password, tipoUsuario);
        boolean response = this.usuarioDAO.create(nuevoUsuario); //no vale para nada

        System.out.println("Usuario registrado: " + nuevoUsuario.toString());

        return nuevoUsuario;
    }

    public void actualizarUsuario(UsuarioVO usarioAntiguo, String nombre, String email, String password,
            TipoUsuario tipoUsuario) {
        // Modificamos todos los campos menos el dni que es la PK de Usuario en la bd
        usarioAntiguo.setNombre(nombre);
        usarioAntiguo.setEmail(email);
        usarioAntiguo.setPassword(password);
        usarioAntiguo.setTipoUsuario(tipoUsuario);

        this.usuarioDAO.update(usarioAntiguo);

    }

    public void generarUsuarioActuializado() {
        // TODO: implement
    }

    public boolean retirarUsuario(UsuarioVO usuario) {
        boolean response = this.usuarioDAO.delete(usuario);
        return response;
    }

	public UsuarioVO getUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(UsuarioVO usuarioActivo) {
		this.usuarioActivo = usuarioActivo;
	}

}
