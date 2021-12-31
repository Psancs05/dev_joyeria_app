package modelo.logica;

import modelo.DAO.ProductoDAO;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;

public class ProductoLogica {
    private ProductoDAO productoDAO;

    public ProductoLogica(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public void imprimirEtiquetaProdcuto(ProductoVO producto, String ubicacion) {
        // TODO: implement
    }

    public void filtrarProductos(ProductoVO parametros) {
        // TODO: implement
    }

    public void solicitarProductos() {
        // TODO: implement
    }

    public void registrarProducto(String tipoDePieza, String precio, String imagen, String material,
            ProveedorVO proveedor) {
        // TODO: implement
    }

    public void actualizarProducto(ProductoVO productoAntiguo, String tipoDePieza, String precio, String imagen,
            String material,
            ProveedorVO proveedor) {
        // TODO: implement
    }

    public void generarProductoActualizado() {
        // TODO: implement
    }

    public void retirarProducto(ProductoVO producto) {
        // TODO: implement
    }
}
