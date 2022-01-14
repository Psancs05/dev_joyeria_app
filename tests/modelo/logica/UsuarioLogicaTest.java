
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import globals.enums.TipoUsuario;
import modelo.VO.UsuarioVO;
import modelo.logica.UsuarioLogica;

public class UsuarioLogicaTest {
    private UsuarioLogica userLogic = new UsuarioLogica();

    private String dni = "11111111A";
    private String nombre = "Juan";
    private String email = "juan@gmail.com";
    private String password = "juan";
    private TipoUsuario tipoUsuario = TipoUsuario.CAJERO;

    @Test
    public void testRetirarUsuario() {
        UsuarioVO usuario = userLogic.registrarUsuario(dni, nombre, email, password, tipoUsuario);
        assertTrue(userLogic.retirarUsuario(usuario));
        userLogic.retirarUsuario(usuario);
    }

}
