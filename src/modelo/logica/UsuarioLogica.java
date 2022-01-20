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

    public boolean comprobarUsuario(String dni, String password) {
        boolean response = this.usuarioDAO.autenticarUsuario(dni, password);
        return response;
    }

    public UsuarioVO getUsuarioPorDNI(String dni) {
        return (UsuarioVO) usuarioDAO.getUsuarioPorDNI(dni);
    }


    public boolean registrarUsuario(String dni, String nombre, String email, String password,
            TipoUsuario tipoUsuario) {
        final UsuarioVO nuevoUsuario = new UsuarioVO(dni, nombre, email, password, tipoUsuario);
        boolean response = this.usuarioDAO.create(nuevoUsuario);
        if (response)
            System.out.println("Usuarios registrado: " + nuevoUsuario.toString());
        return response;
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

    public boolean retirarUsuario(UsuarioVO usuario) {
        boolean response = this.usuarioDAO.delete(usuario);
        return response;
    }

    public boolean suficientesAdmins(){
        ArrayList<UsuarioVO> listaUsuarios = usuarioDAO.getListaUsuarios();
        int numAdmins = 0;
        for(UsuarioVO usuario: listaUsuarios){
            if(usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR){
                numAdmins++;
            }
        }
        if(numAdmins > 1){
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<UsuarioVO> solicitarUsuarios() {
        return this.usuarioDAO.getListaUsuarios();
    }
}
