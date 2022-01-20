package modelo.logica;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import globals.enums.TipoUsuario;
import modelo.VO.UsuarioVO;

public class UsuarioLogicaTest {
    private UsuarioLogica userLogic = new UsuarioLogica();

    private String dni = "11111111A";
    private String nombre = "Juan";
    private String email = "juan@gmail.com";
    private String password = "juan";
    private TipoUsuario tipoUsuario = TipoUsuario.CAJERO;

    @Test
    public void testRetirarUsuario() {
        assertTrue(userLogic.registrarUsuario(dni, nombre, email, password, tipoUsuario));
        UsuarioVO usuario = new UsuarioVO(dni, nombre, email, password, tipoUsuario);
        assertTrue(userLogic.retirarUsuario(usuario));
        userLogic.retirarUsuario(usuario);
    }

}
