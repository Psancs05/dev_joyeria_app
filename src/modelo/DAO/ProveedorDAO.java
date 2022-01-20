package modelo.DAO;

import java.sql.*;
import java.util.ArrayList;

import modelo.VO.ProveedorVO;
import modelo.conexion.Conexion;

public class ProveedorDAO implements DAO {

    // * Atributos
    private static ProveedorDAO miProveedorDAO;

    // * Sinlgeton
    public static ProveedorDAO getInstance() {
        if (miProveedorDAO == null) {
            miProveedorDAO = new ProveedorDAO();
        }
        return miProveedorDAO;
    }

    /**
     * @param objeto Objeto de tipo proovedorVO que queremos introducir en la BBDD.
     *               Si el proveedor que queremos meter ya esta introducido, no lo
     *               insertamos, esto lo hace solo SQL por violacion de las PK
     * @return boolean devuelve true si la operacion se ejecuta cone exito, false si
     *         no se ha podido insertar.
     */
    public boolean create(Object objeto) {
        ProveedorVO proveedor = (ProveedorVO) objeto;
        String CIF = proveedor.getCIF();
        String nombre = proveedor.getNombre();
        if (exist(proveedor) || comprobarSiExisteOtroMismoNombre(proveedor)) {
            return false;
        }

        try {
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            String query = "INSERT INTO proveedor (CIF, Nombre) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, CIF);
            pst.setString(2, nombre);
            pst.executeUpdate();
            // con.close();
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            // se ha intentado introducir dos veces lo mismo (misma PK)
            return false;
        } catch (Exception e) {

            return false;
        }
    }

    /**
     * @param objeto objeto de tipo ProveedorVO que queremos buscar en la base de
     *               datos. Para que funciones solo hace falta que tenga en DNI
     *               metido, el resto de atributos no son necesarios.
     * @return Object objeto de tipo ProveedorVO que se corresponde con el
     *         proveedorVO pasado por parametro.
     */
    public Object search(Object objeto) {
        ProveedorVO proveedor = (ProveedorVO) objeto;
        String CIFProv = proveedor.getCIF();

        try {
            if (!exist(proveedor)) {
                throw new Exception("ProveedorDAO: No hay resultados");
            }
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            String query = "SELECT * FROM proveedor WHERE CIF=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, CIFProv);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String CIFNuevoProv = rs.getString("CIF");
                String NombreNuevoProv = rs.getString("Nombre");
                ProveedorVO nuevoProv = new ProveedorVO(CIFNuevoProv, NombreNuevoProv);
                // con.close();
                return nuevoProv;
            } else {
                // con.close();
                throw new Exception("ProveedorDAO: No hay resultados");
            }

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param objeto objeto con los parametros modificados pero la misma PK
     * @return boolean devuelve true si la operacion se ha completado con exito o
     *         no. Si no existe ese objeto en la BBDD, la funcion devolvera false.
     */
    public boolean update(Object objeto) {
        ProveedorVO provMod = (ProveedorVO) objeto;
        if (!exist(provMod)) {
            return false;
        }
        // Info del modificado
        String CIFMod = provMod.getCIF();
        String nombreMod = provMod.getNombre();
        // Info del de la bbdd
        ProveedorVO provBD = (ProveedorVO) search(provMod);
        String nombreBD = provBD.getNombre();

        if (!nombreMod.equals(nombreBD)) {
            // modificamos nombre
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE proveedor SET Nombre=? WHERE CIF=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, nombreMod);
                pst.setString(2, CIFMod);
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
        ProveedorVO provDel = (ProveedorVO) objeto;
        if (!exist(provDel)) {
            return false;
        }
        String CIFDel = provDel.getCIF();
        try {
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            String query = "DELETE FROM proveedor WHERE CIF=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, CIFDel);
            pst.executeUpdate();
            // con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param objeto objeto de tipo VO para comprobar si existe en la BBDD
     * @return boolean true o false dependiendo de si existe o no en la BBDD
     */
    @Override
    public boolean exist(Object objeto) {
        ProveedorVO proveedor = (ProveedorVO) objeto;
        String CIF = proveedor.getCIF();
        try {
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM proveedor WHERE CIF='" + CIF + "'");
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

    public boolean comprobarSiExisteOtroMismoNombre(Object objeto) {
        ProveedorVO proveedor = (ProveedorVO) objeto;
        String nombre = proveedor.getNombre();
        try {
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM proveedor WHERE Nombre='" + nombre + "'");
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

    public Object getProveedorPorCIF(String cif) {
        ProveedorVO placeholder = new ProveedorVO(cif, "name");
        return search(placeholder);
    }

     /**
     * @return lista de todos los proveedores
     */
    public ArrayList<ProveedorVO> getListaProveedores() {
        ArrayList<ProveedorVO> listaProveedores = new ArrayList<ProveedorVO>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root", "rootroot");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM proveedor");
            while (rs.next()) {
                String CIF = rs.getString("CIF");
                String Nombre = rs.getString("Nombre");
                ProveedorVO nuevoProv = new ProveedorVO(CIF, Nombre);
                listaProveedores.add(nuevoProv);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProveedores;
    }

     /**
     * @param nombre nombre del proveedor
     * @return proveedor que coincide con el nombre
     */
    public ProveedorVO getProveedorPorNombre(String nombre) {
        ProveedorVO nuevoProv = null;
        try {
            Connection con = Conexion.getInstance().getConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM proveedor WHERE Nombre LIKE '%" + nombre + "%'");

            if (rs.next()) {

                String CIF = rs.getString("CIF");
                String Nombre = rs.getString("Nombre");
                nuevoProv = new ProveedorVO(CIF, Nombre);
            } else {
                throw new Exception("No hay resultados");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return nuevoProv;

    }
}
