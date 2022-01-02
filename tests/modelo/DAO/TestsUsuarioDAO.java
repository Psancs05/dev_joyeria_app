package modelo.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.*;
import org.w3c.dom.UserDataHandler;

import java.sql.*;

import globals.enums.TipoUsuario;
import modelo.VO.UsuarioVO;

public class TestsUsuarioDAO {
    private UsuarioVO usuario1;
    private UsuarioVO usuario2;
    UsuarioDAO userDAO = UsuarioDAO.getInstance();

    public static String getAlphaNumericString(int n) {

        // lower limit for LowerCase Letters
        int lowerLimit = 97;

        // lower limit for LowerCase Letters
        int upperLimit = 122;

        Random random = new Random();

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer(n);

        for (int i = 0; i < n; i++) {

            // take a random value between 97 and 122
            int nextRandomChar = lowerLimit
                    + (int) (random.nextFloat()
                            * (upperLimit - lowerLimit + 1));

            // append a character at the end of bs
            r.append((char) nextRandomChar);
        }

        // return the resultant string
        return r.toString();
    }

    @Before
    public void setUp() {
        usuario1 = new UsuarioVO("12345678N", "Napuh", "nhuerp00", "rootroot", TipoUsuario.ADMINISTRADOR);
        usuario2 = new UsuarioVO("87654321C", "Claudio", "cfern69", "ClaudioJoyas", TipoUsuario.CAJERO);

    }

    @Test
    public void testCreateNuevoUsuario() {
        UsuarioVO usuarioRandom = new UsuarioVO(getAlphaNumericString(9), "random", "randomite", "randomito",
                TipoUsuario.CAJERO);
        assertTrue(userDAO.create(usuarioRandom));
        userDAO.delete(usuarioRandom);
    }

    @Test
    public void testAddUsuarioYaExistente() {
        userDAO.create(usuario1);
        assertFalse(userDAO.create(usuario1));
    }

    @Test
    public void testSearch() {
        UsuarioVO copia1 = (UsuarioVO) userDAO.search(usuario1);
        assertTrue(copia1.equals(usuario1));
        System.out.println(copia1.toString());
        System.out.println(usuario1.toString());
    }

    @Test
    public void testExist() {
        assertTrue(userDAO.exist(usuario1));
        UsuarioVO usuarioNoExiste = new UsuarioVO("sdadasda", "nombre", "email", "password", TipoUsuario.CAJERO);
        assertFalse(userDAO.exist(usuarioNoExiste));
    }

    @Test
    public void testUpdate(){
        String randomPass = getAlphaNumericString(10);
        String passAntigua = usuario1.getPassword();
        usuario1.setPassword(randomPass);
        assertTrue(userDAO.update(usuario1));
        UsuarioVO usuarioPassModificada = (UsuarioVO) userDAO.search(usuario1);
        assertNotEquals(passAntigua, usuarioPassModificada.getPassword());
        assertEquals(randomPass, usuarioPassModificada.getPassword());
        assertEquals(usuario1.getPassword(), usuarioPassModificada.getPassword());
        usuario1.setPassword(passAntigua);
        userDAO.update(usuario1);
    }

    @Test
    public void testUpdateNoExist(){
        UsuarioVO usuarioNoExiste = new UsuarioVO("sdadasda", "nombre", "email", "password", TipoUsuario.CAJERO);
        assertFalse(userDAO.update(usuarioNoExiste));
    }

    @Test
    public void testDelete(){
        userDAO.create(usuario2);
        assertTrue(userDAO.exist(usuario2));
        userDAO.delete(usuario2);
        assertFalse(userDAO.exist(usuario2));
        assertFalse(userDAO.update(usuario2));
    }
}
