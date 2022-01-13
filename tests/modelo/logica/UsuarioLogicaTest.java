package modelo.logica;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import globals.enums.TipoUsuario;
import modelo.VO.UsuarioVO;
import modelo.logica.UsuarioLogica;

public class UsuarioLogicaTest {
    private UsuarioLogica userLogic = UsuarioLogica.getInstance();

    private String dni = "11111111A";
    private String nombre = "Juan";
    private String email = "juan@gmail.com";
    private String password = "juan";
    private TipoUsuario tipoUsuario = TipoUsuario.CAJERO;

    @Test
    public void testRegistrarUsuario() {
        UsuarioVO usuario = userLogic.registrarUsuario(dni, nombre, email, password, tipoUsuario);
        assertNotNull(usuario);
        assertNotNull(this.userLogic.getUsuarioActivo());
        userLogic.retirarUsuario(usuario);
    }

    @Test
    public void testRetirarUsuario() {
        UsuarioVO usuario = userLogic.registrarUsuario(dni, nombre, email, password, tipoUsuario);
        assertTrue(userLogic.retirarUsuario(usuario));
        userLogic.retirarUsuario(usuario);
    }

    @Test
    public void testComprobarUsuario() {
        UsuarioVO usuario = userLogic.registrarUsuario(dni, nombre, email, password, tipoUsuario);
        assertTrue(userLogic.comprobarUsuario("11111111A", "juan"));
        userLogic.retirarUsuario(usuario);
    }

}
