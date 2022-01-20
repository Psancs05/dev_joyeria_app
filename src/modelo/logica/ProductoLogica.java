package modelo.logica;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    
    /** 
     * Metodo necesario para mostrar los productos en el catalogo. Primero solicita todo el listado y luego elimina los productos eliminados
     * @return ArrayList<ProductoVO> listado con los productos menos los que han sido vendidos
     */
    public ArrayList<ProductoVO> solicitarProductos() {
        ArrayList<ProductoVO> productos = this.productoDAO.getListaProductos();
        ArrayList<ProductoVO> productosInventario = new ArrayList<ProductoVO>();

        for (ProductoVO producto : productos) {
            if (producto.getIDVenta().equals(-1))
                productosInventario.add(producto);

        }
        return productosInventario;

    }

    
    /** 
     * Metodo que registra un producto en la base de datos recibiendo todos los datos
     * @param nombre
     * @param numCuaderno
     * @param tipoDeProducto
     * @param precio
     * @param pathImg
     * @param material
     * @param proveedor
     * @param descripcion
     * @return boolean true si se ha registrado el producto
     */
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

    
    /** 
     * Metodo que actuliza el producto pasado por parametro en la base de datos
     * @param productoAntiguo
     * @param nombre
     * @param numCuaderno
     * @param tipoProducto
     * @param proveedor
     * @param material
     * @param precio
     * @param imagen
     * @param descripcion
     */
    public void actualizarProducto(ProductoVO productoAntiguo, String nombre, int numCuaderno,
            TipoProducto tipoProducto, ProveedorVO proveedor,
            TipoMaterial material,
            double precio,
            String imagen, String descripcion) throws FileNotFoundException, IOException {
        productoAntiguo.setTipoProducto(tipoProducto);
        productoAntiguo.setNumCuaderno(numCuaderno);
        productoAntiguo.setPrecio(precio);
        java.awt.image.BufferedImage img;
        img = ImageIO.read(new FileInputStream(imagen));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        // byte[] bytes = baos.toByteArray();
        // java.sql.Blob blob = new SerialBlob(blob); //!ESTA LINEA DA EROR
        // productoAntiguo.setImagen(blob);
        productoAntiguo.setMaterial(material);
        productoAntiguo.setProveedor(proveedor);
        productoAntiguo.setDescripcion(descripcion);
        productoAntiguo.setNombre(nombre);
        productoDAO.update(productoAntiguo);
    }

    
    /** 
     * Metodo que elimina un producto por parametro de la base de datos
     * @param producto producto a eliminar
     * @return boolean true si se ha eliminado
     */
    public boolean retirarProducto(ProductoVO producto) {
        return this.productoDAO.delete(producto);
    }
}
