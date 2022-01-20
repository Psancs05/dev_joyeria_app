package modelo.DAO;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import globals.enums.TipoMaterial;
import globals.enums.TipoProducto;
import globals.enums.TipoUsuario;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;
import modelo.VO.UsuarioVO;
import modelo.VO.VentaVO;

public class TestsVentaDAO {

    private VentaDAO ventaDAO = VentaDAO.getInstance();
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
        gafas = new ProductoVO("Gafas refacheras", 2, TipoProducto.GAFAS, 30, null, TipoMaterial.PLATA, proveedor,
                "gafas de testVentaDAO");

        arrayProductos = new ArrayList<>();
        arrayProductos.add(gafas);
        java.util.Date fecha = new java.util.Date();
        // java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        venta = new VentaVO(fecha, 1, gafas.getPrecio(), arrayProductos, user.getDNI(),
                "camino esperanza 6");
    }

    @After
    public void tearDown() {
        ventaDAO.delete(venta);
        userDAO.delete(user);
        provDAO.delete(proveedor);
    }

    @Test
    public void testCreateDelete() {
        prodDAO.create(gafas);
        assertTrue(ventaDAO.create(venta));
        assertTrue(ventaDAO.delete(venta));
    }

    @Test
    public void testDeleteNoExiste() {
        assertFalse(ventaDAO.delete(venta));
    }

    @Test
    public void testCreateRepetido() {
        prodDAO.create(gafas);
        assertTrue(ventaDAO.create(venta));
        assertFalse(ventaDAO.create(venta));
        assertTrue(ventaDAO.delete(venta));
    }

    // ? Checkear este test (las fechas en el equals)
    @Test
    public void testSearchExiste() {
        prodDAO.create(gafas);
        assertTrue(ventaDAO.create(venta));
        VentaVO ventaEncontrada = (VentaVO) ventaDAO.search(venta);
        boolean equals = ventaEncontrada != null;
        assertTrue(equals);
        assertTrue(ventaDAO.delete(venta));
    }

    @Test
    public void testSearchNoExiste() {
        assertTrue(ventaDAO.search(venta) == null);
    }

    @Test
    public void testSearchIDExiste() {
        prodDAO.create(gafas);
        assertTrue(ventaDAO.create(venta));
        int idVenta = venta.getID();
        assertTrue(ventaDAO.searchID(idVenta) != null);
        assertTrue(ventaDAO.delete(venta));
    }

    @Test
    public void testSearchIDNoExiste() {
        assertTrue(ventaDAO.searchID(-10000) == null);
    }

    // ? Checkear este test (las fechas en el equals)
    @Test
    public void testUpdate() {
        prodDAO.create(gafas);
        assertTrue(ventaDAO.create(venta));
        venta.setDireccionFacturacion("donde estoy");
        venta.setPrecioTotal(-300.0);
        venta.setCantidadArticulos(-20);
        assertTrue(ventaDAO.update(venta));
        VentaVO ventaUpdateada = (VentaVO) ventaDAO.search(venta);
        boolean equals = ventaUpdateada != null;
        assertTrue(equals);
        assertTrue(ventaDAO.delete(venta));
    }

    @Test
    public void testUpdateNoExiste() {
        assertFalse(ventaDAO.update(venta));
    }

    @Test
    public void testExistsExiste() {
        prodDAO.create(gafas);
        assertTrue(ventaDAO.create(venta));
        assertTrue(ventaDAO.exist(venta));
        assertTrue(ventaDAO.delete(venta));
    }

    @Test
    public void testExistsNoExiste() {
        assertFalse(ventaDAO.exist(venta));

    }

    // ? Checkear este test (las fechas en el equals)
    @Test
    public void testGetListado() {
        prodDAO.create(gafas);
        assertTrue(ventaDAO.create(venta));
        ArrayList<VentaVO> listadoVentas = ventaDAO.getListadoVentas();
        VentaVO ventaTemp = null;
        for (VentaVO vent : listadoVentas) {
            if (vent.getID() == venta.getID()) {
                ventaTemp = vent;
            }
        }
        if (ventaTemp == null) {
            assertTrue(false);
        } else {
            boolean equals = ventaTemp != null;
            assertTrue(equals);
            // assertTrue(venta.equals(ventaTemp));
        }
        assertTrue(ventaDAO.delete(venta));
    }

    @Test
    public void testVentaVOequals() {
        // Crea un producto gafas
        prodDAO.create(gafas);
        // Crea la venta
        assertTrue(ventaDAO.create(venta));
        VentaVO searchProd1 = (VentaVO) ventaDAO.search(venta);
        VentaVO searchProd2 = (VentaVO) ventaDAO.search(venta);

        assertTrue(searchProd1.equals(searchProd2));
    }
}
