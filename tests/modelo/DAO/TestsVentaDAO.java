package modelo.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

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
        gafas = new ProductoVO(TipoProducto.GAFAS, 30, null, TipoMaterial.PLATA, proveedor, "gafas de testVentaDAO");
        prodDAO.create(gafas);
        arrayProductos = new ArrayList<>();
        arrayProductos.add(gafas);
        java.util.Date fecha = new java.util.Date();
        venta = new VentaVO(fecha, 1, gafas.getPrecio(), arrayProductos, user.getDNI(),
                "camino esperanza 6");
    }

    @Test
    public void testIDVentaProductos() {
        assertEquals(0, gafas.getIDVenta());
        assertEquals(0, gafas.getIDVenta());
        assertEquals(-1, venta.getID());
        assertTrue(ventDAO.create(venta));
        assertNotEquals(-1, venta.getID());
        assertEquals(gafas.getIDVenta(), venta.getID());
        ventDAO.delete(venta);
        
    }

    @Test
    public void testCreateDelete() {
        assertTrue(ventaDAO.create(venta));
        assertTrue(ventaDAO.delete(venta));
    }

    @Test
    public void testDeleteNoExiste() {
        assertFalse(ventaDAO.delete(venta));
    }

    @Test
    public void testCreateRepetido() {
        assertTrue(ventaDAO.create(venta));
        assertFalse(ventaDAO.create(venta));
        assertTrue(ventaDAO.delete(venta));
    }

    @Test
    public void testSearchExiste() {
        assertTrue(ventaDAO.create(venta));
        VentaVO ventaEncontrada = assertTrue(ventaDAO.search(venta));
        assertTrue(ventaEncontrada.equals(venta));
        assertTrue(ventaDAO.delete(venta));
    }

    @Test
    public void testSearchNoExiste() {
        assertFalse(ventaDAO.search(venta));
    }

    @Test
    public void testSearchIDExiste() {
        assertTrue(ventaDAO.create(venta));
        int idVenta = venta.getID();
        assertTrue(ventaDAO.search(idVenta));
        assertTrue(ventaDAO.delete(venta));
    }

    @Test
    public void testSearchIDNoExiste() {
        assertFalse(ventaDAO.search(-10000));
    }

    @Test
    public void testUpdate() {
        assertTrue(ventaDAO.create(venta));
        venta.setDireccionFacturacion("donde estoy");
        venta.setPrecioTotal(-300.0);
        venta.setCantidadArticulos(-20);
        assertTrue(ventaDAO.update(venta));
        VentaVO ventaUpdateada = ventaDao.search(venta);
        assertTrue(venta.equals(ventaUpdateada));
        assertTrue(ventaDAO.delete(venta));
    }

    @Test
    public void testUpdateNoExiste() {
        assertFalse(ventaDAO.update(venta));
    }

    @Test
    public void testExistsExiste() {
        assertTrue(ventaDAO.create(venta));
        assertTrue(ventaDAO.exists(venta));
        assertTrue(ventaDAO.delete(venta));
    }

    @Test
    public void testExistsNoExiste() {
        assertFalse(ventaDAO.exists(venta));
    
    }

    @Test
    public void testGetListado() {
        assertTrue(ventaDAO.create(venta));
        ArrayList<VentaVO> listadoVentas = ventaDAO.getListadoVentas();
        for(VentaVO vent: listadoVentas){
            if(vent.getID() ==venta.getID()){
                VentaVO ventaTemp = vent;
            }
        }
        assertTrue(venta.equals(ventaTemp));
        assertTrue(ventaDAO.delete(venta));
    
    }
}

