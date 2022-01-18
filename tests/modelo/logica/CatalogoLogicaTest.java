package modelo.logica;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import globals.enums.TipoMaterial;
import globals.enums.TipoProducto;
import modelo.DAO.ProductoDAO;
import modelo.DAO.ProveedorDAO;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;
import modelo.logica.CatalogoLogica;

public class CatalogoLogicaTest {
    private ProductoDAO prodDAO = ProductoDAO.getInstance();
    private ProveedorDAO provDAO = ProveedorDAO.getInstance();
    private ProductoVO pendiente;
    private ProductoVO colgante;
    private ProveedorVO distr;
    private CatalogoLogica cL;

    @Before
    public void setUp() {
        cL = new CatalogoLogica();
        distr = new ProveedorVO("123456G", "distribuidorTestProdDAO");
        provDAO.create(distr);
        pendiente = new ProductoVO("Pulsera de Max", 3, TipoProducto.PULSERA, 200, null, TipoMaterial.ORO, distr,
                "anillo de oro");
        colgante = new ProductoVO("Pulsera de los lloros", 2, TipoProducto.PULSERA, 200, null, TipoMaterial.OROPLATA,
                distr, "pulsera de plata y oro");
    }

    @Test
    public void testListadoFiltradoPorMaterial() {
        prodDAO.create(pendiente);
        prodDAO.create(colgante);
        ProductoVO parametros = new ProductoVO("Pendiente", 2, TipoProducto.PENDIENTE, -1.0, null, null, null, null);

        ArrayList<ProductoVO> listaFiltrada = cL.filtrarCatalogo(parametros);
        for (ProductoVO producto : listaFiltrada) {
            assertTrue(producto.getTipoProducto() == TipoProducto.PENDIENTE);
        }
        prodDAO.delete(pendiente);
        prodDAO.delete(colgante);
    }

    @Test
    public void testListadoFiltradoPorPrecio() {
        prodDAO.create(pendiente);
        prodDAO.create(colgante);
        ProductoVO parametros = new ProductoVO(null, 0, null, 120, null, null, null, null);

        ArrayList<ProductoVO> listaFiltrada = cL.filtrarCatalogo(parametros);
        for (ProductoVO producto : listaFiltrada) {
            assertTrue(producto.getPrecio() == 120);
        }
        prodDAO.delete(pendiente);
        prodDAO.delete(colgante);
    }

    @Test
    public void testListadoFiltradoPorCIFProveedor() {
        prodDAO.create(pendiente);
        prodDAO.create(colgante);

        ProveedorVO prov = new ProveedorVO("12345678", null);
        ProductoVO parametros = new ProductoVO(null, 0, null, -1.0, null, null, prov, null);

        ArrayList<ProductoVO> listaFiltrada = cL.filtrarCatalogo(parametros);
        for (ProductoVO producto : listaFiltrada) {
            assertTrue(producto.getProveedor().getCIF().equals("12345678"));
        }
        prodDAO.delete(pendiente);
        prodDAO.delete(colgante);
    }

    @Test
    public void testListadoFiltradoPorNombreProveedor() {
        prodDAO.create(pendiente);
        prodDAO.create(colgante);

        ProveedorVO prov = new ProveedorVO(null, "Mani Manitas");
        ProductoVO parametros = new ProductoVO(null, 0, null, -1.0, null, null, prov, null);

        ArrayList<ProductoVO> listaFiltrada = cL.filtrarCatalogo(parametros);
        for (ProductoVO producto : listaFiltrada) {
            assertTrue(producto.getProveedor().getNombre().equals("ManiManitas"));
        }
        prodDAO.delete(pendiente);
        prodDAO.delete(colgante);
    }

    @Test
    public void testListadoFiltradoPorIdVenta() {
        prodDAO.create(pendiente);
        prodDAO.create(colgante);

        ProductoVO parametros = new ProductoVO(null, 0, null, -1.0, null, null, null, null);
        parametros.setIDVenta(1);

        ArrayList<ProductoVO> listaFiltrada = cL.filtrarCatalogo(parametros);
        for (ProductoVO producto : listaFiltrada) {
            assertTrue(producto.getIDVenta() == 1);
        }
        prodDAO.delete(pendiente);
        prodDAO.delete(colgante);
    }

    @Test
    public void testListadoFiltradoCombinado() {
        prodDAO.create(pendiente);
        prodDAO.create(colgante);

        ProveedorVO prov = new ProveedorVO("123456G", null);
        ProductoVO parametros = new ProductoVO(null, 0, TipoProducto.PULSERA, 200, null, null, prov, null);

        ArrayList<ProductoVO> listaFiltrada = cL.filtrarCatalogo(parametros);
        for (ProductoVO producto : listaFiltrada) {
            assertTrue(producto.getTipoProducto() == TipoProducto.PULSERA);
            assertTrue(producto.getPrecio() == 200);
            assertTrue(producto.getProveedor().getCIF().equals("123456G"));
        }
        prodDAO.delete(pendiente);
        prodDAO.delete(colgante);
    }
}
