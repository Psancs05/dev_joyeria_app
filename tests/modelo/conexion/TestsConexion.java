package modelo.conexion;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Target;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class TestsConexion {

    @Test
    public void testBackup() throws IOException {
        // En ordenador de nap (linux) funciona no se en los vuestros
        Conexion c = Conexion.getInstance();
        Path fileToDeletePath = Paths.get("backup.sql");
        Files.deleteIfExists(fileToDeletePath);
        assertTrue(c.generarBackup(""));
        assertTrue(Files.exists(fileToDeletePath));
        Files.deleteIfExists(fileToDeletePath);
    }

    @Test
    public void testRestaurarBackup() {
        Conexion c = Conexion.getInstance();
        assertTrue(c.restaurarBackup("etc/dbJoyeria.sql"));
    }
}
