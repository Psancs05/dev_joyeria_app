package modelo.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.ws.BindingType;

import org.junit.Before;
import org.junit.Test;

import globals.enums.TipoMaterial;
import globals.enums.TipoProducto;
import globals.enums.TipoUsuario;
import modelo.*;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;
import modelo.VO.UsuarioVO;
import modelo.VO.VentaVO;

public class TestsVentaDAO {

    private VentaDAO ventDAO = VentaDAO.getInstance();
    private ProductoDAO prodDAO = ProductoDAO.getInstance();
    private ProveedorDAO provDAO = ProveedorDAO.getInstance();
    private UsuarioDAO userDAO = UsuarioDAO.getInstance();
    private UsuarioVO user;
    private ProveedorVO proveedor;
    private ProductoVO gafas;
    private VentaVO venta;
    private ArrayList<ProductoVO> arrayProductos;

    @Before
    public void setUp() {
        user = new UsuarioVO("5656565", "UserTestVentaDAO", "vdao@lol.es", "contigopipo", TipoUsuario.ADMINISTRADOR);
        userDAO.create(user);
        proveedor = new ProveedorVO("999999", "Proveedor de testVentaDAO");
        provDAO.create(proveedor);
        gafas = new ProductoVO(TipoProducto.GAFAS, 30, null, TipoMaterial.PLATA, proveedor, "gafas de testVentaDAO");
        arrayProductos = new ArrayList<>();
        arrayProductos.add(gafas);
        venta = new VentaVO(Calendar.getInstance().getTime(), 1, gafas.getPrecio(), arrayProductos, user.getDNI(),
                "camino esperanza 6");
    }

    @Test
    public void testIDVentaProductos() {
        assertEquals(0, gafas.getIDVenta());
        prodDAO.create(gafas);
        assertEquals(0, gafas.getIDVenta());
        assertEquals(-1, venta.getID());
        assertTrue(ventDAO.create(venta));
        assertNotEquals(-1, venta.getID());
        assertEquals(gafas.getIDVenta(), venta.getID());
        ventDAO.delete(venta);
    }

}
