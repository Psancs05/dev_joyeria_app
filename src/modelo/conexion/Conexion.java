package modelo.conexion;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.*;

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
                System.out.println("Backup completado");
            } else {
                System.out.println("El backup ha fallado");
                System.out.println(
                        "Fallo al ejecutar el comando: " + comando + " con los siguientes errores:");
                try (final BufferedReader b = new BufferedReader(new InputStreamReader(
                        runtimeProcess.getErrorStream()))) {
                    String line;
                    while ((line = b.readLine()) != null)
                        System.out.println(line);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void restaurarBackup(String ubicacion) {
        try {

            
            try (
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/", bdUsuario, bdPassword);
            Statement stmt = conn.createStatement();) {
            String sql = "DROP DATABASE bdJoyeria";
            stmt.executeUpdate(sql);
            System.out.println("Database DELETED successfully...");
            String sql2 = "CREATE DATABASE bdJoyeria";
            stmt.executeUpdate(sql2);
            System.out.println("Database created successfully...");

            } catch (SQLException e) {
            e.printStackTrace();
            }

            // String comando = "mysqldump " + "--user=" + bdUsuario + " --password=" +
            // bdPassword + " -e 'CREATE DATABASE bdJoyeria'";

            // Process runtimeProcess = Runtime.getRuntime().exec(comando);
            // int resultado = runtimeProcess.waitFor();

            // if (resultado == 0) {
            // System.out.println("Restauramos BBDD correctamente");
            // } else {
            // System.out.println("No hemos restaurado la BBDD");
            // System.out.println(
            // "Fallo al ejecutar el comando: " + comando + " con los siguientes errores:");
            // try (final BufferedReader b = new BufferedReader(new InputStreamReader(
            // runtimeProcess.getErrorStream()))) {
            // String line;
            // while ((line = b.readLine()) != null)
            // System.out.println(line);
            // } catch (final IOException e) {
            // e.printStackTrace();
            // }
            // }
            String[] comando2 = new String[] { "mysql", "--user=" + bdUsuario, "--password=" + bdPassword, "-e",
                    "source " + ubicacion };
            String xd = "mysqldump " + "--user=" + bdUsuario + " --password=" + bdPassword + " " + bdNombre
                    + " < "
                    + ubicacion;

            Process runtimeProcess2 = Runtime.getRuntime().exec(comando2);
            int resultado2 = runtimeProcess2.waitFor();

            if (resultado2 == 0) {
                System.out.println("Restauramos BBDD correctamente");
            } else {
                System.out.println("No hemos restaurado la BBDD");
                System.out.println(
                        "Fallo al ejecutar el comando: " + comando2 + " con los siguientes errores:");
                try (final BufferedReader b = new BufferedReader(new InputStreamReader(
                        runtimeProcess2.getErrorStream()))) {
                    String line;
                    while ((line = b.readLine()) != null)
                        System.out.println(line);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
