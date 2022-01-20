package modelo.conexion;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    private static Conexion miConexion;
    private Connection conexionBD;
    // cambiar en caso de otros datos
    private String bdNombre = "bdJoyeria";
    private String bdUsuario = "root";
    private String bdPassword = "rootroot";

    public Conexion() {
        try {
            conexionBD = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + bdNombre + "", bdUsuario,
                    bdPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    /** 
     * Singleton de conexion
     * @return Conexion
     */
    public static Conexion getInstance() {
        if (miConexion == null) {
            miConexion = new Conexion();
        }
        return miConexion;
    }

    
    /** 
     * Get
     * @return Connection
     */
    public Connection getConexion() {
        return conexionBD;
    }

    /** 
     * Metodo que cierra la conexion con la base de datos
     */
    public void disconnect() {
        try {
            conexionBD.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** 
     * Metodo que recibe un path donde guarda un backup de la base de datos.
     * @param ubicacion path donde crear el .sql
     * @return boolean true si se ha creado correctament
     */
    public boolean generarBackup(String ubicacion) {
        try {
            String fileSeparator = File.separator;
            String archivoBackup;
            if (ubicacion.length() == 0) {
                archivoBackup = ubicacion + "backup.sql";
            } else {
                archivoBackup = ubicacion + fileSeparator + "backup.sql";
            }

            String comando = "mysqldump -u " + bdUsuario + " --password=" + bdPassword + " " + bdNombre + " -r "
                    + archivoBackup;

            Process runtimeProcess = Runtime.getRuntime().exec(comando);
            int resultado = runtimeProcess.waitFor();

            if (resultado == 0) {
                return true;
            } else {
                try (final BufferedReader b = new BufferedReader(new InputStreamReader(
                        runtimeProcess.getErrorStream()))) {
                } catch (final IOException e) {
                    e.printStackTrace();
                }
                return false;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }

    }

    
    /** 
     * Metodo que busca un .sql backup en el path por parametro y lo restaura en la base de datos.
     * @param ubicacion
     * @return boolean
     */
    public boolean restaurarBackup(String ubicacion) {
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", bdUsuario, bdPassword);
            Statement stmt = conn.createStatement();
            String sqlBorrarBBDD = "DROP DATABASE " + bdNombre;
            stmt.executeUpdate(sqlBorrarBBDD);
            String sqlCrearBBDD = "CREATE DATABASE " + bdNombre;
            stmt.executeUpdate(sqlCrearBBDD);

            String[] comandoRestaurar = new String[] { "mysql", "--user=" + bdUsuario, "--password=" + bdPassword,
                    "--database=" + bdNombre, "-e",
                    "source " + ubicacion };

            Process runtimeProcess = Runtime.getRuntime().exec(comandoRestaurar);
            int resultado = runtimeProcess.waitFor();

            if (resultado == 0) {
                return true;
            } else {

                try (final BufferedReader b = new BufferedReader(new InputStreamReader(
                        runtimeProcess.getErrorStream()))) {
                } catch (final IOException e) {
                    e.printStackTrace();
                }
                return false;
            }

        } catch (IOException | InterruptedException | SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
