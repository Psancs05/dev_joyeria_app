package modelo.logica;

import java.sql.Blob;
import java.util.ArrayList;

import globals.enums.TipoMaterial;
import globals.enums.TipoProducto;
import modelo.DAO.ProductoDAO;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;

public class ProductoLogica {
    private static ProductoLogica instance;
    private ProductoDAO productoDAO;

    private ProductoLogica() {
        this.productoDAO = ProductoDAO.getInstance();
    }

    public static ProductoLogica getInstance() {
        if (instance == null) {
            instance = new ProductoLogica();
        }
        return instance;
    }

    public void imprimirEtiquetaProdcuto(ProductoVO producto, String ubicacion) {
        // TODO: implement
    }

    public void filtrarProductos(ProductoVO parametros) {
        // TODO: implement
    }

    public ArrayList<ProductoVO> solicitarProductos() {
        return this.productoDAO.getListaProductos();
    }

    public boolean registrarProducto(TipoProducto tipoDeProducto, double precio, Blob imagen, TipoMaterial material,
            ProveedorVO proveedor, String descripcion) {
        ProductoVO nuevoProducto = new ProductoVO(tipoDeProducto, precio, imagen, material, proveedor, descripcion);

        return this.productoDAO.create(nuevoProducto);
    }

    public void actualizarProducto(ProductoVO productoAntiguo, String tipoDePieza, String precio, String imagen,
            String material,
            ProveedorVO proveedor) {
        // TODO: implement
    }

    public void generarProductoActualizado() {
        // TODO: implement
    }

    public boolean retirarProducto(ProductoVO producto) {
        return this.productoDAO.delete(producto);
    }
}
