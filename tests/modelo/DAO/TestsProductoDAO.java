package modelo.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import globals.enums.TipoMaterial;
import globals.enums.TipoProducto;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;

public class TestsProductoDAO {
    private ProductoDAO prodDAO = ProductoDAO.getInstance();
    private ProveedorDAO provDAO = ProveedorDAO.getInstance();
    private ProductoVO anillo;
    private ProductoVO pulsera;
    private ProveedorVO distr;

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
        distr = new ProveedorVO("123456G", "distribuidor");
        provDAO.create(distr);
        anillo = new ProductoVO(TipoProducto.ANILLO, 200, null, TipoMaterial.ORO, distr, "anillo de oro");
        pulsera = new ProductoVO(TipoProducto.PULSERA, 120, null, TipoMaterial.OROPLATA, distr,
                "pulsera de plata y oro");
    }

    @Test
    public void testModificarID() {
        ProveedorVO perico = new ProveedorVO("dsadad", "nombre");
        provDAO.create(perico);
        ProductoVO prod1 = new ProductoVO(TipoProducto.ANILLO, 200, null, TipoMaterial.ORO, perico, "anillo de oro");
        System.out.println(prod1.getIDProducto());
        assertTrue(prodDAO.create(prod1));
        System.out.println("HEY" + prod1.getIDProducto());
    }

    @Test
    public void testExist() {
        assertFalse(prodDAO.exist(anillo));
        prodDAO.create(anillo);
        assertTrue(prodDAO.exist(anillo));
        prodDAO.delete(anillo);
    }

    @Test
    public void testCreateNuevoProductoEnBBDD() {
        ProductoVO prodRandom = new ProductoVO(TipoProducto.ANILLO, 200, null, TipoMaterial.ORO, distr,
                getAlphaNumericString(15));
        assertTrue(prodDAO.create(prodRandom));
        prodDAO.delete(prodRandom);
    }

    @Test
    public void testAddProductoQueYaExiste() {
        prodDAO.create(anillo);
        assertFalse(prodDAO.create(anillo));
    }

    @Test
    public void testSearch() {
        prodDAO.create(pulsera);
        ProductoVO copia = (ProductoVO) prodDAO.search(pulsera);
        assertTrue(copia.equals(pulsera));
    }

    @Test
    public void testUpdate() {
        prodDAO.create(pulsera);
        String nuevaDesc = getAlphaNumericString(6);
        String antiguaDesc = pulsera.getDescripcion();
        pulsera.setDescripcion(nuevaDesc);
        assertTrue(prodDAO.update(pulsera));
        ProductoVO productoDescModificado = (ProductoVO) prodDAO.search(pulsera);
        assertNotEquals(antiguaDesc, productoDescModificado.getDescripcion());
        assertEquals(nuevaDesc, productoDescModificado.getDescripcion());
        assertEquals(pulsera.getDescripcion(), productoDescModificado.getDescripcion());
        pulsera.setDescripcion(antiguaDesc);
        prodDAO.update(pulsera);
    }

    @Test
    public void testUpdateNoExist() {
        ProductoVO productoPlaceholder = new ProductoVO(TipoProducto.ANILLO, 666, null, TipoMaterial.PLATA, null,
                "descripcion");
        assertFalse(prodDAO.update(productoPlaceholder));
    }

    @Test
    public void testDelete() {
        prodDAO.create(pulsera);
        assertTrue(prodDAO.exist(pulsera));
        prodDAO.delete(pulsera);
        assertFalse(prodDAO.exist(pulsera));
        assertFalse(prodDAO.update(pulsera));
    }

    @Test
    public void testGetProductoPorID() {
        prodDAO.create(anillo);
        ProductoVO prodCopia = (ProductoVO) prodDAO.getProductoPorID(anillo.getIDProducto());
        assertTrue(prodCopia.equals(anillo));
    }

}
