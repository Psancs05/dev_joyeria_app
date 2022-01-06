package modelo.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import modelo.VO.VentaVO;
import modelo.VO.ProductoVO;
import modelo.conexion.Conexion;

public class VentaDAO implements DAO {
    private static VentaDAO miVentaDAO;

    public static VentaDAO getInstance() {
        if (miVentaDAO == null) {
            miVentaDAO = new VentaDAO();
        }
        return miVentaDAO;
    }

    public boolean create(Object objeto) {
        try {
            VentaVO venta = (VentaVO) objeto;
            java.util.Date fecha = venta.getFecha();
            java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
            int articulos = venta.getCantidadArticulos();
            double precioTotal = venta.getPrecioTotal();
            String DNI = venta.getDNIUsuario();
            String direccionFacturacion = venta.getDireccionFacturacion();

            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            String query = "INSERT INTO venta (Fecha,CantidadArticulos, PrecioVenta, DireccionFacturacion, DNIUsuario) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setDate(1, sqlDate);
            pst.setInt(2, articulos);
            pst.setDouble(3, precioTotal);
            pst.setString(4, direccionFacturacion);
            pst.setString(5, DNI);
            pst.executeUpdate();

            int idVenta = -1;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            if (rs.next()) {
                idVenta = rs.getInt(1);
            } else {
                // exception
            }
            venta.setID(idVenta);
            // con.close();
            ArrayList<ProductoVO> listaProds = venta.getProductos();
            ProductoDAO prodDAO = ProductoDAO.getInstance();
            for (ProductoVO producto : listaProds) {
                producto.setIDVenta(idVenta);
                prodDAO.update(producto);
            }
            venta.setID(idVenta);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public Object search(Object objeto) {
        VentaVO venta = (VentaVO) objeto;
        int idVenta = venta.getID();
        // ArrayList<ProductoVO> listaProductos = venta.getProductos();

        try {
            if (!exist(venta)) {
                throw new Exception("VentaDAO: No hay resultados");
            }
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            String query = "SELECT * FROM venta WHERE IDVenta=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idVenta);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                java.util.Date fecha = new Date(rs.getDate("Fecha").getTime());
                int articulos = rs.getInt("CantidadArticulos");
                double precioTotal = rs.getDouble("PrecioVenta");
                String DNI = rs.getString("DNIUsuario");
                String direccion = rs.getString("DireccionFacturacion");
                int ID = rs.getInt("IDVenta");
                // con.close();
                // Cogemos la lista de productos correspondiente a la venta
                ProductoDAO pDAO = ProductoDAO.getInstance();
                ArrayList<ProductoVO> nuevaLista = pDAO.getProductosSegunIDVenta(ID);
                System.out.println("LISTA EN SEARCH: " + nuevaLista.toString());

                // for (ProductoVO producto : listaProductos) {
                // ProductoVO nuevo = (ProductoVO) pDAO.search(producto);
                // nuevaLista.add(nuevo);
                // }

                VentaVO nuevaVenta = new VentaVO(ID, fecha, articulos, precioTotal, nuevaLista, DNI, direccion);
                return nuevaVenta;
            } else {
                // con.close();
                throw new Exception("VentaDAO: No hay resultados");
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Object searchID(int id) {
        VentaVO ventaPlaceholder = new VentaVO(Calendar.getInstance().getTime(), 1, 1, null, "777",
                "direccionFacturacion");
        ventaPlaceholder.setID(id);
        return search(ventaPlaceholder);
    }

    public boolean update(Object objeto) {
        VentaVO venta = (VentaVO) objeto;
        if (!exist(venta)) {
            return false;
        }

        // Info del objeto como parametro
        int idVenta = venta.getID();
        String DNI = venta.getDNIUsuario();
        int cantidadArticulos = venta.getCantidadArticulos();
        double precio = venta.getPrecioTotal();
        String direccion = venta.getDireccionFacturacion();
        java.util.Date date = venta.getFecha();
        ArrayList<ProductoVO> listaProductos = venta.getProductos();

        // Info del objeto correspondiente en la base de datos
        VentaVO ventaBD = (VentaVO) search(venta);
        String DNIBD = ventaBD.getDNIUsuario();
        int idVentaBD = ventaBD.getID();
        int cantidadArticulosBD = ventaBD.getCantidadArticulos();
        double precioBD = ventaBD.getPrecioTotal();
        String direccionBD = ventaBD.getDireccionFacturacion();
        java.util.Date dateBD = ventaBD.getFecha();
        ArrayList<ProductoVO> listaProductosBD = ventaBD.getProductos();

        if (!(cantidadArticulos == cantidadArticulosBD)) {
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE venta SET CantidadArticulos=? WHERE IDVenta=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, cantidadArticulos);
                pst.setInt(2, idVenta);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }

        if (!(precio == precioBD)) {
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE venta SET PrecioVenta=? WHERE IDVenta=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setDouble(1, precio);
                pst.setInt(2, idVenta);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }

        if (!(direccion.equals(direccionBD))) {
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE venta SET DireccionFacturacion=? WHERE IDVenta=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, direccion);
                pst.setInt(2, idVenta);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }

        if (date.compareTo(dateBD) != 0) {
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE venta SET Fecha=? WHERE IDVenta=?";
                PreparedStatement pst = con.prepareStatement(query);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                pst.setDate(1, sqlDate);
                pst.setInt(2, idVenta);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }

        if (!(DNI.equals(DNIBD))) {
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE venta SET DNIUsuario=? WHERE IDVenta=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, DNI);
                pst.setInt(2, idVenta);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public boolean delete(Object objeto) {
        VentaVO venta = (VentaVO) objeto;
        ArrayList<ProductoVO> listaProductos = venta.getProductos();

        if (!exist(venta)) {
            return false;
        }
        int idVenta = venta.getID();
        try {
            // Borramos los productos relacionados con la venta
            ProductoDAO pDAO = ProductoDAO.getInstance();
            for (ProductoVO producto : listaProductos) {
                pDAO.delete(producto);
            }
            // Borramos la venta
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            String query = "DELETE FROM venta WHERE IDventa=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, idVenta);
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
        VentaVO venta = (VentaVO) objeto;
        int idVenta = venta.getID();

        try {
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM venta WHERE IDventa='" + idVenta + "'");
            if (rs.next()) {
                // con.close();
                return true;
            } else {
                // con.close();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    // public ArrayList<VentaVO> getListadoVentas() {
    // ArrayList<VentaVO> listaVentas = new ArrayList<VentaVO>();
    // try {
    // Class.forName("com.mysql.cj.jdbc.Driver");
    // Connection con =
    // DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root",
    // "rootroot");
    // String query = "SELECT * FROM venta";
    // PreparedStatement pst = con.prepareStatement(query);
    // ResultSet rs = pst.executeQuery();
    // while(rs.next()) {
    // java.util.Date fecha = new Date(rs.getDate("Fecha").getTime());
    // int articulos = rs.getInt("CantidadArticulos");
    // double precioTotal = rs.getDouble("PrecioVenta");
    // String DNI = rs.getString("DNIUsuario");
    // String direccion = rs.getString("DireccionFacturacion");
    // int id = rs.getInt("IDVenta");

    // // Cogemos la lista de productos correspondiente a la venta
    // ArrayList<ProductoVO> nuevaLista = new ArrayList<ProductoVO>();
    // ProductoDAO pDAO = ProductoDAO.getInstance();

    // VentaVO nuevaVenta = new VentaVO(id, fecha, articulos, precioTotal, null,
    // DNI, direccion);
    // listaVentas.add(nuevaVenta);
    // }
    // con.close();
    // return listaVentas;

    // } catch (Exception e) {
    // System.out.println(e);
    // return null;
    // }
    // }
}
