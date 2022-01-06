package modelo.conexion;

import java.sql.*;

public class Conexion {
    private static Conexion miConexion;
    private Connection conexionBD;

    public Conexion() {
        try {
            conexionBD = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root", "rootroot");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Conexion getInstance() {
        if (miConexion == null) {
            miConexion = new Conexion();
        }
        return miConexion;
    }

    public Connection getConexion() {
        return conexionBD;
    }

    public void disconnect() {
        try {
            conexionBD.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generarBackup(String ubicacion) {
        // TODO: implement
    }

    public void restaurarBackup(String ubicacion) {
        // TODO: implement
    }
}
