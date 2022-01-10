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

    public ArrayList<ProductoVO> solicitarProductos() {
        return this.productoDAO.getListaProductos();
    }

    public boolean registrarProducto(TipoProducto tipoDeProducto, double precio, Blob imagen, TipoMaterial material,
            ProveedorVO proveedor, String descripcion) {
        ProductoVO nuevoProducto = new ProductoVO(tipoDeProducto, precio, imagen, material, proveedor, descripcion);

        return this.productoDAO.create(nuevoProducto);
    }

    public void actualizarProducto(ProductoVO productoAntiguo, TipoProducto producto, double precio, Blob imagen,
            TipoMaterial material,
            ProveedorVO proveedor) {
        productoAntiguo.setTipoProducto(producto);
        productoAntiguo.setPrecio(precio);
        productoAntiguo.setImagen(imagen);
        productoAntiguo.setMaterial(material);
        productoAntiguo.setProveedor(proveedor);

        productoDAO.update(productoAntiguo);
    }

    public void generarProductoActualizado() {
        // TODO: implement
    }

    public boolean retirarProducto(ProductoVO producto) {
        return this.productoDAO.delete(producto);
    }
}
