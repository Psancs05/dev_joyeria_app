package modelo.DAO;

import java.sql.*;
import java.util.ArrayList;

import globals.enums.TipoUsuario;
import modelo.VO.UsuarioVO;
import modelo.conexion.Conexion;

public class UsuarioDAO implements DAO {

    private static UsuarioDAO miUsuarioDAO;

    public static UsuarioDAO getInstance() {
        if (miUsuarioDAO == null) {
            miUsuarioDAO = new UsuarioDAO();
        }
        return miUsuarioDAO;
    }

    /**
     * @param object el objeto de tipo UsuarioVO que queremos introducir en la BBDD.
     *               Si el usuario que queremos meter en la base de datos ya esta
     *               dentro, no lo insertamos. Esto lo hace solo SQL ya que si
     *               introducimos dos registros con la misma PK salta excepcion.
     * @return boolean si la operacion de insercion en la base de datos se ha
     *         realizado con exito o no.
     * 
     */
    public boolean create(Object objeto) {
        try {

            UsuarioVO usuario = (UsuarioVO) objeto;
            String DNI = usuario.getDNI();
            String nombre = usuario.getNombre();
            String email = usuario.getEmail();
            String password = usuario.getPassword();
            TipoUsuario tipoUsuario = usuario.getTipoUsuario();

            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            Statement st = con.createStatement();

            st.executeUpdate(
                    "INSERT INTO usuario (DNI, Nombre, Email, Password, TipoUsuario) VALUES ('" + DNI + "','"
                            + nombre + "','" + email + "','" + password + "','" + tipoUsuario + "');");
            // con.close();
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            // se ha intentado introducir dos veces lo mismo (misma PK)
            System.out.println(e);
            return false;
        } catch (Exception e) {

            System.out.println(e);
            return false;
        }
    }

    /**
     * @param objeto objeto de tipo UsuarioVO que queremos buscar en la base de
     *               datos. Para que funcione solo necesita tener un DNI metido, no
     *               son necesarios el resto de atributos.
     * @return Object objeto de tipo UsuarioVO con la entrada correspondiente en la
     *         bbdd al usuario buscado pasado por parametro
     */
    public Object search(Object objeto) {
        try {
            UsuarioVO usuario = (UsuarioVO) objeto;
            String DNIusuario = usuario.getDNI();

            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE DNI='" + DNIusuario + "'");

            if (rs.next()) {
                String DNINuevoUsuario = rs.getString("DNI");
                String NombreNuevoUsuario = rs.getString("Nombre");
                String EmailNuevoUsuario = rs.getString("Email");
                String PasswordNuevoUsuario = rs.getString("Password");
                String TipoUsuarioNuevoUsuario = rs.getString("TipoUsuario");
                UsuarioVO nuevoUsuario = new UsuarioVO(DNINuevoUsuario, NombreNuevoUsuario, EmailNuevoUsuario,
                        PasswordNuevoUsuario, TipoUsuario.valueOf(TipoUsuarioNuevoUsuario));
                // con.close();
                return nuevoUsuario;
            } else {
                // con.close();
                throw new Exception("UsuarioDAO: No hay resultados");
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * @param objeto objeto con los parametros modificados pero con la misma PK
     * @return boolean si la operacion se ha realizado con exito o no. Si no existe
     *         ese objeto en la bbdd devolvera false (no se ha encontrado la PK)
     */
    public boolean update(Object objeto) {

        // sacamos info del usuario pasado como parametro
        UsuarioVO usuarioMod = (UsuarioVO) objeto;
        // comprobamos si esta en la BBDD
        if (!exist(usuarioMod)) {
            return false;
        }
        String DNIMod = usuarioMod.getDNI();
        String nombreMod = usuarioMod.getNombre();
        String emailMod = usuarioMod.getEmail();
        String passwordMod = usuarioMod.getPassword();
        TipoUsuario tipoUsuarioMod = usuarioMod.getTipoUsuario();

        // sacamos infor del usuario de la BBDD
        UsuarioVO usuarioBD = (UsuarioVO) search(usuarioMod);
        String nombreBD = usuarioBD.getNombre();
        String emailBD = usuarioBD.getEmail();
        String passwordBD = usuarioBD.getPassword();
        TipoUsuario tipoUsuarioBD = usuarioBD.getTipoUsuario();

        // comenzamos el rollazo SQL lets FUCKING GO
        if (!nombreMod.equals(nombreBD)) {
            // modificamos nombre
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE usuario SET Nombre=? WHERE DNI=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, nombreMod);
                pst.setString(2, DNIMod);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }

        }
        if (!emailMod.equals(emailBD)) {
            // modificamos email
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE usuario SET Email=? WHERE DNI=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, emailMod);
                pst.setString(2, DNIMod);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }
        if (!passwordMod.equals(passwordBD)) {
            // modificamos password
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE usuario SET Password=? WHERE DNI=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, passwordMod);
                pst.setString(2, DNIMod);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }
        if (!tipoUsuarioMod.toString().equals(tipoUsuarioBD.toString())) {
            // modificamos tipoUsuario
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE usuario SET TipoUsuario=? WHERE DNI=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, tipoUsuarioMod.toString());
                pst.setString(2, DNIMod);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param objeto objeto de tipo VO a eliminar de la base de datos.
     * @return boolean devuelve true si se ha eliminado con exito. False en caso de
     *         que no se haya eliminado o que directamente no exista ese objeto en
     *         la BBDD.
     */
    public boolean delete(Object objeto) {
        UsuarioVO usuarioDel = (UsuarioVO) objeto;
        // comprobamos si esta en la BBDD
        if (!exist(usuarioDel)) {
            return false;
        }
        String DNIDel = usuarioDel.getDNI();
        try {
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            String query = "DELETE FROM usuario WHERE DNI=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, DNIDel);
            pst.executeUpdate();
            // con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean exist(Object objeto) {
        UsuarioVO usuario = (UsuarioVO) objeto;
        String DNI = usuario.getDNI();
        try {
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE DNI='" + DNI + "'");
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean autenticarUsuario(String dni, String password) {
        try {
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            Statement st = con.createStatement();
            ResultSet rs = st
                    .executeQuery("SELECT * FROM usuario WHERE DNI='" + dni + "' AND Password='" + password + "'");
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object getUsuarioPorDNI(String dni) {
        UsuarioVO placeholder = new UsuarioVO(dni, "nombre", "email", "password", TipoUsuario.CAJERO);
        return search(placeholder);
    }

    public ArrayList<UsuarioVO> getListaUsuarios() {
        try {
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            String query = "SELECT * FROM usuario";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            ArrayList<UsuarioVO> listaUsuarios = new ArrayList<UsuarioVO>();
            while (rs.next()) {
                // Obtenemos los datos del producto de la db
                String DNI = rs.getString("DNI");
                String nombre = rs.getString("Nombre");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                TipoUsuario tUsuario = TipoUsuario.valueOf(rs.getString("TipoUsuario"));

                // Creamos el producto con los datos obtenidos
                UsuarioVO usuario = new UsuarioVO(DNI, nombre, email, password, tUsuario);
                // usuario.setIDProducto(usuario);

                // Añadimos el producto a la lista
                listaUsuarios.add(usuario);
            }
            // con.close();
            return listaUsuarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
