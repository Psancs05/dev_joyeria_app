package modelo.logica;

public class UsuarioLogica {
    private UsuarioDAO usuarioDAO;

    public UsuarioLogica(UsuarioDao usuarioDAO) {
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
