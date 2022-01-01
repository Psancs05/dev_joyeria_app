package modelo.DAO;

import java.sql.*;

import com.itextpdf.text.log.SysoLogger;

import globals.MiSingleton;
import globals.enums.TipoUsuario;
import modelo.VO.UsuarioVO;

public class UsuarioDAO extends MiSingleton implements DAO {

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

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root", "rootroot");
            Statement st = con.createStatement();

            st.executeUpdate(
                    "INSERT INTO usuario (DNI, Nombre, Email, Password, TipoUsuario) VALUES ('" + DNI + "','"
                            + nombre + "','" + email + "','" + password + "','" + tipoUsuario + "');");
            con.close();
            return true;

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

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root", "rootroot");
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
                con.close();
                return nuevoUsuario;
            } else {
                con.close();
                throw new Exception("UsuarioDAO: No hay resultados");
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean update(Object objeto) {
        throw new UnsupportedOperationException();
        // TODO: Implement
    }

    public boolean delete(Object objeto) {
        throw new UnsupportedOperationException();
        // TODO: Implement
    }

}
