package modelo.logica;

import java.util.ArrayList;

import modelo.DAO.VentaDAO;
import modelo.VO.ProductoVO;
import modelo.VO.VentaVO;

public class VentaLogica {
    private VentaDAO ventaDAO;

    public VentaLogica(VentaDAO ventaDAO) {
        this.ventaDAO = ventaDAO;
    }

    public double obtenerPrecioVenta(ArrayList<ProductoVO> productos) {
        throw new UnsupportedOperationException();
        // TODO: implement
    }

    public void retirarProductosDeBBDD(ArrayList<ProductoVO> productos) {
        // TODO: implement
    }

    public void registrarVenta(ArrayList<ProductoVO> productos) {
        // TODO: implement
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
