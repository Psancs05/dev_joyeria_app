package modelo.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import modelo.VO.ProveedorVO;

public class TestsProveedorDAO {
    private ProveedorVO proveedor1;
    private ProveedorVO proveedor2;
    ProveedorDAO provDAO = ProveedorDAO.getInstance();

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
        proveedor1 = new ProveedorVO("244668", "Joyas S.L.");
        proveedor2 = new ProveedorVO("133557", "Joyas S.A.");
    }

    @Test
    public void testExist() {
        provDAO.create(proveedor1);
        assertTrue(provDAO.exist(proveedor1));
        ProveedorVO prov3 = new ProveedorVO("place", "holder");
        assertFalse(provDAO.exist(prov3));
    }

    @Test
    public void testCreateNuevoProveedor() {
        ProveedorVO provRandom = new ProveedorVO(getAlphaNumericString(7), getAlphaNumericString(7));
        assertTrue(provDAO.create(provRandom));
        provDAO.delete(provRandom);
    }

    @Test
    public void testAddProveedorYaExistente() {
        provDAO.create(proveedor1);
        assertFalse(provDAO.create(proveedor1));
    }

    @Test
    public void testSearch() {
        provDAO.create(proveedor1);
        ProveedorVO copia1 = (ProveedorVO) provDAO.search(proveedor1);
        assertTrue(copia1.equals(proveedor1));

    }

    @Test
    public void testUpdate() {
        provDAO.create(proveedor2);
        String nuevoNombre = getAlphaNumericString(6);
        String antiguoNombre = proveedor2.getNombre();
        proveedor2.setNombre(nuevoNombre);
        assertTrue(provDAO.update(proveedor2));
        ProveedorVO proveedorNombreModificado = (ProveedorVO) provDAO.search(proveedor2);
        assertNotEquals(antiguoNombre, proveedorNombreModificado.getNombre());
        assertEquals(nuevoNombre, proveedorNombreModificado.getNombre());
        assertEquals(proveedor2.getNombre(), proveedorNombreModificado.getNombre());
        proveedor2.setNombre(antiguoNombre);
        provDAO.update(proveedor2);
    }

    @Test
    public void testUpdateNoExist() {
        ProveedorVO provPlaceholder = new ProveedorVO("place", "holder");
        assertFalse(provDAO.update(provPlaceholder));
    }

    @Test
    public void testDelete() {
        provDAO.create(proveedor2);
        assertTrue(provDAO.exist(proveedor2));
        provDAO.delete(proveedor2);
        assertFalse(provDAO.exist(proveedor2));
        assertFalse(provDAO.update(proveedor2));
    }

    @Test
    public void testGetProveedorPorCIF() {
        provDAO.create(proveedor1);
        ProveedorVO provCopia = (ProveedorVO) provDAO.getProveedorPorCIF(proveedor1.getCIF());
        assertTrue(provCopia.equals(proveedor1));
    }

    @Test
    public void testGetProveedorPorNombre() {
        provDAO.create(proveedor1);
        ProveedorVO prov = provDAO.getProveedorPorNombre("Joyas S.L.");
        assertTrue(prov.equals(proveedor1));
    }

}
