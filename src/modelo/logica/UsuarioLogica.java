package modelo.logica;

import java.util.ArrayList;

import globals.enums.TipoUsuario;
import modelo.DAO.UsuarioDAO;
import modelo.VO.UsuarioVO;

public class UsuarioLogica {
    private UsuarioDAO usuarioDAO;

    public UsuarioLogica() {
        this.usuarioDAO = UsuarioDAO.getInstance();
    }

    public UsuarioVO comprobarUsuario(String dni, String password) {
        boolean response = this.usuarioDAO.autenticarUsuario(dni, password);
        UsuarioVO usuario = (UsuarioVO) usuarioDAO.search(new UsuarioVO(dni, ".", ".", password, TipoUsuario.CAJERO));
        return usuario;
    }

    public void cerrarSesion() {
        // TODO: implement
    }

    public boolean registrarUsuario(String dni, String nombre, String email, String password,
            TipoUsuario tipoUsuario) {
        final UsuarioVO nuevoUsuario = new UsuarioVO(dni, nombre, email, password, tipoUsuario);
        boolean response = this.usuarioDAO.create(nuevoUsuario); // no vale para nada
        return response;
    }

    public boolean actualizarUsuario(UsuarioVO usarioAntiguo, String nombre, String email, String password,
            TipoUsuario tipoUsuario) {
        // Modificamos todos los campos menos el dni que es la PK de Usuario en la bd
        usarioAntiguo.setNombre(nombre);
        usarioAntiguo.setEmail(email);
        usarioAntiguo.setPassword(password);
        usarioAntiguo.setTipoUsuario(tipoUsuario);

        return this.usuarioDAO.update(usarioAntiguo);

    }

    public void generarUsuarioActuializado() {
        // TODO: implement
    }

    public boolean retirarUsuario(UsuarioVO usuario) {
        boolean response = this.usuarioDAO.delete(usuario);
        return response;
    }

    public ArrayList<UsuarioVO> solicitarUsuarios() {
        return this.usuarioDAO.getListaUsuarios();
    }

}
