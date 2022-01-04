package modelo.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import modelo.VO.VentaVO;
import modelo.VO.ProductoVO;

public class VentaDAO  implements DAO {
    final String direccionFacturacion = "Mi puta casa";
    private static VentaDAO miVentaDAO;
    
    public static VentaDAO getInstance() {
        if (miVentaDAO == null) {
            miVentaDAO = new VentaDAO();
        }
        return miVentaDAO;
    }
    
    public boolean create(Object objeto) {
        try{
            VentaVO venta = (VentaVO) objeto;
            java.util.Date fecha = venta.getFecha();
            int articulos = venta.getCantidadArticulos();
            double precioTotal = venta.getPrecioTotal();
            String DNI = venta.getDNIUsuario();

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root", "rootroot");
            
            String query = "INSERT INTO venta (Fecha,CantidadArticulos, PrecioVenta, DireccionFacturacion, DNIUsuario) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, fecha.toString());
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
                //exception
            }
            venta.setID(idVenta);

            con.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return false;
        }
    }

    public Object search(Object objeto) {
        throw new UnsupportedOperationException();
        // TODO: Implement
    }

    public boolean update(Object objeto) {
        throw new UnsupportedOperationException();
        // TODO: Implement
    }

    public boolean delete(Object objeto) {
        throw new UnsupportedOperationException();
        // TODO: Implement
    }

    @Override
    public boolean exist(Object objeto) {
        // TODO Auto-generated method stub
        return false;
    }
}
