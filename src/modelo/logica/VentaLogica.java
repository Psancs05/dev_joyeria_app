package modelo.logica;

import java.util.ArrayList;

import modelo.DAO.VentaDAO;
import modelo.VO.ProductoVO;
import modelo.VO.VentaVO;

public class VentaLogica {
    private VentaDAO ventaDAO;

    public VentaLogica() {
        this.ventaDAO = VentaDAO.getInstance();
    }

    /**
     * Metodo que suma los precios de una lista de productos y devuelve el total
     * @producto productos listado de productos
     * @return precio total
     */
    public double obtenerPrecioVenta(ArrayList<ProductoVO> productos) {
        double precioTotal = 0.0;
        for (ProductoVO producto : productos) {
            precioTotal = precioTotal + producto.getPrecio();
        }
        return precioTotal;
    }

    /**
     * Metodo que crea una venta  con los productos por parametro
     * @param productos listado que productos que componen una venta
     * @return el id de la venta
     */
    public int registrarVenta(ArrayList<ProductoVO> productos) {
        double precioVenta = obtenerPrecioVenta(productos);
        // String dni = UsuarioControlador.getInstance().getUsuarioActual().getDNI();
        String dni = "12345678C";
        int cantidadArticulos = productos.size();
        java.util.Date date = new java.util.Date();

        VentaVO nuevaVenta = new VentaVO(date, cantidadArticulos, precioVenta, productos, dni, "C\\ Burgo Nuevo, 56");
        this.ventaDAO.create(nuevaVenta);
        return nuevaVenta.getID();
    }

    /**
     * @param productos lista de todos los productos que componen en una venta
     *                  registra la venta en la base de datos y devuelve el id de la
     *                  venta creada
     */
    public ArrayList<VentaVO> mostrarListadoFacturas() {
        ArrayList<VentaVO> ventas = ventaDAO.getListadoVentas();
        return ventas;
    }

}
