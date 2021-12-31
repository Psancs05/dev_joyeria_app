package modelo.logica;

import globals.enums.TipoUsuario;
import modelo.DAO.UsuarioDAO;
import modelo.VO.UsuarioVO;

public class UsuarioLogica {
    private UsuarioDAO usuarioDAO;

    public UsuarioLogica(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void comprobarUsuario(String usuario, String password) {
        // TODO: implement
    }

    public void cerrarSesion() {
        // TODO: implement
    }

    public void registrarUsuario(String dni, String nombre, String email, String password, TipoUsuario tipoUsuario) {
        // TODO: implement
    }

    public void actualizarUsuario(UsuarioVO usarioAntiguo, String dni, String nombre, String email, String password,
            TipoUsuario tipoUsuario) {
        // TODO: implement
    }

    public void generarUsuarioActuializado() {
        // TODO: implement
    }

    public void retirarUsuario(UsuarioVO usuario) {
        // TODO: implement
    }
}
