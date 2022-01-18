package helpers;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import globals.enums.TipoMaterial;
import globals.enums.TipoProducto;
import globals.enums.TipoUsuario;
import modelo.DAO.ProveedorDAO;
import modelo.DAO.UsuarioDAO;
import modelo.DAO.VentaDAO;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;
import modelo.VO.UsuarioVO;
import modelo.VO.VentaVO;

public class TestsPDFHelper {
        private VentaDAO ventaDAO = VentaDAO.getInstance();
        private ProveedorDAO provDAO = ProveedorDAO.getInstance();
        private UsuarioDAO userDAO = UsuarioDAO.getInstance();
        private UsuarioVO user;
        private ProveedorVO proveedor;
        private ProductoVO gafas;
        private VentaVO venta;
        private ArrayList<ProductoVO> arrayProductos;
        private static String ubicacionFactura = new File("PDFPruebaFactura.pdf").getAbsolutePath();
        private static String ubicacionEtiqueta = new File("PDFPruebaEtiquetas.pdf").getAbsolutePath();

        @Before
        public void setUp() {
                user = new UsuarioVO("5656565", "UserTestVentaDAO", "vdao@lol.es", "contigopipo",
                                TipoUsuario.ADMINISTRADOR);
                userDAO.create(user);
                proveedor = new ProveedorVO("999999", "Proveedor de testVentaDAO");
                provDAO.create(proveedor);
                gafas = new ProductoVO("Gafas refacheras", 1, TipoProducto.GAFAS, 30, null, TipoMaterial.PLATA,
                                proveedor,
                                "gafas de testVentaDAO");
                ProductoVO anillo = new ProductoVO("Anillo", 2, TipoProducto.ANILLO, 50, null, TipoMaterial.ORO,
                                proveedor,
                                "Anillo de oro modelo 03");
                ProductoVO pulsera = new ProductoVO("Pulsera para vender", 3, TipoProducto.PULSERA, 99.98, null,
                                TipoMaterial.ORO, proveedor,
                                "pulsera con indentaciones de oro");
                ProductoVO anillo2 = new ProductoVO("Anillo de oro blanco", 2, TipoProducto.ANILLO, 80, null,
                                TipoMaterial.PLATA, proveedor,
                                "anillo plata de ley");
                ProductoVO collar = new ProductoVO("Colgante persa", 1, TipoProducto.COLGANTE, 34.99, null,
                                TipoMaterial.ORO, proveedor,
                                "Cadena anuel AA");
                arrayProductos = new ArrayList<>();
                arrayProductos.add(gafas);
                arrayProductos.add(anillo);
                arrayProductos.add(pulsera);
                arrayProductos.add(anillo2);
                arrayProductos.add(collar);
                java.util.Date fecha = new java.util.Date();
                venta = new VentaVO(fecha, 1,
                                gafas.getPrecio() + anillo.getPrecio() + pulsera.getPrecio() + anillo2.getPrecio()
                                                + collar.getPrecio(),
                                arrayProductos, user.getDNI(),
                                "camino esperanza 6");
                ventaDAO.create(venta);
        }

        @Test
        public void testGenerarPDF() {
                System.out.println("Busca el PDF en: " + ubicacionFactura);
                PDFHelper.generarPDFVenta(venta, ubicacionFactura);
        }

        @Test
        public void testGenerarEtiquetas() {
                System.out.println("Busca el pdf en: " + ubicacionEtiqueta);
                System.out.println(arrayProductos.toString());
                PDFHelper.generarPDFEtiqueta(arrayProductos, ubicacionEtiqueta);
        }

        @After
        public void tearDown() {
                ventaDAO.delete(venta);
        }
}
