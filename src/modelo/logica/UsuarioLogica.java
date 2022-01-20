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

    
    /** 
     * Metodo que comprueba si existe un usuario en la base de datos con un dni y una contraseña
     * @param dni dni
     * @param password contraseña
     * @return boolean true si existe ese usuario
     */
    public boolean comprobarUsuario(String dni, String password) {
        boolean response = this.usuarioDAO.autenticarUsuario(dni, password);
        return response;
    }

    
    /** 
     * Metodo que devuelve un usuario con un DNI determinado
     * @param dni dni
     * @return UsuarioVO usuario con el dni
     */
    public UsuarioVO getUsuarioPorDNI(String dni) {
        return (UsuarioVO) usuarioDAO.getUsuarioPorDNI(dni);
    }

    /** 
     * Metodo que crea un nuevo usuario en la base de datos
     * @param dni
     * @param nombre
     * @param email
     * @param password
     * @param tipoUsuario
     * @return boolean true si se ha podido registrar el usuario
     */
    public boolean registrarUsuario(String dni, String nombre, String email, String password,
            TipoUsuario tipoUsuario) {
        final UsuarioVO nuevoUsuario = new UsuarioVO(dni, nombre, email, password, tipoUsuario);
        boolean response = this.usuarioDAO.create(nuevoUsuario);
        return response;
    }

    
    /** 
     * Metodo que actualiza en la base de datos el usuario pasado por parametro
     * @param usarioAntiguo usuario a actualizar
     * @param nombre
     * @param email
     * @param password
     * @param tipoUsuario
     */
    public void actualizarUsuario(UsuarioVO usarioAntiguo, String nombre, String email, String password,
            TipoUsuario tipoUsuario) {
        // Modificamos todos los campos menos el dni que es la PK de Usuario en la bd
        usarioAntiguo.setNombre(nombre);
        usarioAntiguo.setEmail(email);
        usarioAntiguo.setPassword(password);
        usarioAntiguo.setTipoUsuario(tipoUsuario);

        this.usuarioDAO.update(usarioAntiguo);

    }

    
    /** 
     * Metodo que elimina el usuario pasado por parametro de la base de datos
     * @param usuario usuario a eliminar
     * @return boolean true si se ha podido eliminar
     */
    public boolean retirarUsuario(UsuarioVO usuario) {
        boolean response = this.usuarioDAO.delete(usuario);
        return response;
    }

    
    /** 
     * Metodo que comprueba si hay mas de un admin en la base de datos
     * @return boolean true si hay mas de un admin
     */
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

    
    /** 
     * Metood que devuelve un listado con todos los usuarios
     * @return ArrayList<UsuarioVO>
     */
    public ArrayList<UsuarioVO> solicitarUsuarios() {
        return this.usuarioDAO.getListaUsuarios();
    }
}
