package modelo.logica;

import globals.enums.TipoUsuario;
import modelo.DAO.UsuarioDAO;
import modelo.VO.UsuarioVO;

public class UsuarioLogica {
    private static UsuarioLogica miUsuarioLogica;
    private UsuarioDAO usuarioDAO;

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
        return response;
    }

    public void cerrarSesion() {
        // TODO: implement
    }

    public UsuarioVO registrarUsuario(String dni, String nombre, String email, String password,
            TipoUsuario tipoUsuario) {
        final UsuarioVO nuevoUsuario = new UsuarioVO(dni, nombre, email, password, tipoUsuario);
        boolean response = this.usuarioDAO.create(nuevoUsuario);

        System.out.println("Usuario registrado: " + nuevoUsuario.toString());

        if (response) {
            return nuevoUsuario;
        } else {
            return nuevoUsuario;
        }
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

}
