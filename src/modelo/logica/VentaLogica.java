package modelo.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

import LogicaNegocio.UsuarioControlador;
import modelo.DAO.VentaDAO;
import modelo.VO.ProductoVO;
import modelo.VO.VentaVO;

public class VentaLogica {
    private VentaDAO ventaDAO;

    public VentaLogica() {
        this.ventaDAO = VentaDAO.getInstance();
    }

     /**
     * @param productos lista de todos los productos que componen en una venta
     * @return el precio total de la venta, siendo la suma de los precios de todos los productos que la componen
     */
    public double obtenerPrecioVenta(ArrayList<ProductoVO> productos) {
        double precioTotal = 0.0;
        for(ProductoVO producto: productos){
            precioTotal = precioTotal + producto.getPrecio();
        }
        return precioTotal;
    }

    /**
     * @param productos lista de todos los productos que componen en una venta
     * marca los productos de la lista como vendidos actualizando su campo IDVenta
     */
    public void retirarProductosDeBBDD(ArrayList<ProductoVO> productos,int IDVenta) {
        for(ProductoVO producto: productos){
        
        }
    }

     /**
     * @param productos lista de todos los productos que componen en una venta
     * registra la venta en la base de datos y devuelve el id de la venta creada
     */
    public int registrarVenta(ArrayList<ProductoVO> productos) {
        double precioVenta = obtenerPrecioVenta(productos);
        //String DNI = UsuarioControlador.getInstance().getUsuarioActual().getDNI();
        int cantidadArticulos = productos.size();
        Date date=java.util.Calendar.getInstance().getTime();

        VentaVO nuevaVenta = new VentaVO(date, cantidadArticulos, precioVenta, productos, "02770155C");
        this.ventaDAO.create(nuevaVenta);
        return nuevaVenta.getID();
    }

    public void realizarBackupFacturas(ArrayList<VentaVO> ventas, String ubicacion) {
        // TODO: implement
    }

    public void mostrarListadoFacturas() {
        // TODO: implement
    }

    public void copiaFactura(VentaVO venta) {
        // TODO: implement
    }
}
