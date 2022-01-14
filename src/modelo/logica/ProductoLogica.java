package modelo.logica;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import globals.enums.TipoMaterial;
import globals.enums.TipoProducto;
import modelo.DAO.ProductoDAO;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;

public class ProductoLogica {
    private ProductoDAO productoDAO;

    public ProductoLogica() {
        this.productoDAO = ProductoDAO.getInstance();
    }

    public void imprimirEtiquetaProdcuto(ProductoVO producto, String ubicacion) {
        // TODO: implement
    }

    public ArrayList<ProductoVO> solicitarProductos() {
        return this.productoDAO.getListaProductos();
    }

    public boolean registrarProducto(String nombre, int numCuaderno, TipoProducto tipoDeProducto, double precio,
            String pathImg,
            TipoMaterial material,
            ProveedorVO proveedor, String descripcion) throws SerialException, SQLException {
        try {
            java.awt.image.BufferedImage img;
            img = ImageIO.read(new FileInputStream(pathImg));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            java.sql.Blob blob = new SerialBlob(bytes);
            ProductoVO nuevoProducto = new ProductoVO(nombre, numCuaderno, tipoDeProducto, precio, blob, material,
                    proveedor,
                    descripcion);
            return this.productoDAO.create(nuevoProducto);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
