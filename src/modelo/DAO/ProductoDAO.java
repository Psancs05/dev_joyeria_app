package modelo.DAO;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

import globals.enums.*;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;
import modelo.conexion.Conexion;

public class ProductoDAO implements DAO {

    private static ProductoDAO miProductoDAO;

    public static ProductoDAO getInstance() {
        if (miProductoDAO == null) {
            miProductoDAO = new ProductoDAO();
        }
        return miProductoDAO;
    }

    /**
     * @param objeto objeto de tipo productoVO que queremos introducir en la base de
     *               datos. Si el producto que queremos meter ya se encuentra en la
     *               BBDD, no lo insertamos, ya que violaria la restriccion de PK de
     *               SQL.
     * @return boolean devuelve true si la operacion se ejecuta con exito. Devuelve
     *         false si no se ha podido insertar.
     */
    public boolean create(Object objeto) {
        ProductoVO producto = (ProductoVO) objeto;

        TipoProducto tipoProducto = producto.getTipoProducto();
        double precio = producto.getPrecio();
        // Imagen
        java.sql.Blob image = producto.getImagen();
        TipoMaterial material = producto.getMaterial();
        ProveedorVO proveedor = producto.getProveedor();
        String descripcion = producto.getDescripcion();

        if (exist(producto)) {
            return false;
        }
        try {
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();

            String query = "INSERT INTO producto (TipoProducto, Precio, Material, Proveedor, Descripcion, Imagen) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, tipoProducto.toString());
            pst.setDouble(2, precio);
            pst.setString(3, material.toString());
            pst.setString(4, proveedor.getCIF());
            pst.setString(5, descripcion);
            // Imagen
            pst.setBlob(6, image);
            pst.executeUpdate();

            int idProducto = -1;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

            if (rs.next()) {
                idProducto = rs.getInt(1);
            } else {
                // throw an exception from here
            }
            // con.close();
            producto.setIDProducto(idProducto);
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            // se ha intentado introducir dos veces lo mismo (misma PK)
            System.out.println(e);
            return false;
        } catch (Exception e) {

            System.out.println(e);
            return false;
        }
    }

    /**
     * @param objeto objeto de tipo ProductoVO que queremos buscar en la base de
     *               datos. Para que funcione solo tenemos que introducir un objeto
     *               con el ID relleno, el resto pueden ser valores placeholder, no
     *               son necesarios.
     * @return Object objeto de tipo ProductoVO que se corresponde en la BBDD con el
     *         productoVO pasado como parametro.
     */
    public Object search(Object objeto) {
        ProductoVO producto = (ProductoVO) objeto;
        int IDProd = producto.getIDProducto();
        try {
            if (!exist(producto)) {
                throw new Exception("ProductoDAO: No hay resultados");
            }
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            String query = "SELECT * FROM producto WHERE IDProducto=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, IDProd);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                TipoProducto tipoProducto = TipoProducto.valueOf(rs.getString("TipoProducto"));
                double precio = rs.getDouble("Precio");
                TipoMaterial material = TipoMaterial.valueOf(rs.getString("Material"));
                ProveedorDAO provDAO = ProveedorDAO.getInstance();
                ProveedorVO proveedor = (ProveedorVO) provDAO.getProveedorPorCIF(rs.getString("Proveedor"));
                String descripcion = rs.getString("Descripcion");
                int IDVenta = rs.getInt("IDVenta");
                java.sql.Blob blob = rs.getBlob("Imagen");
                ProductoVO nuevoProd = new ProductoVO(tipoProducto, precio, blob, material, proveedor, IDVenta,
                        descripcion);
                // con.close();
                nuevoProd.setIDProducto(IDProd);
                return nuevoProd;
            } else {
                // con.close();
                throw new Exception("ProveedorDAO: No hay resultados");
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * @param objeto objeto con los parametros modificados pero con la misma PK
     * @return boolean devuelve true si la operacion se ha completado con exito. En
     *         caso contrario devuelve false, por ejemplo, si ese objeto no existe
     *         en la BBDD.
     */
    public boolean update(Object objeto) {
        ProductoVO prodMod = (ProductoVO) objeto;
        if (!exist(prodMod)) {
            return false;
        }

        // info del modificado
        // TODO: imagen
        int IDProdMod = prodMod.getIDProducto();
        TipoProducto tipoProductoMod = prodMod.getTipoProducto();
        double precioMod = prodMod.getPrecio();
        TipoMaterial materialMod = prodMod.getMaterial();
        ProveedorVO proveedorMod = prodMod.getProveedor();
        String descripcionMod = prodMod.getDescripcion();
        int IDVentaMod = prodMod.getIDVenta();
        java.sql.Blob blob = prodMod.getImagen();

        // info del de la bbdd
        ProductoVO prodBD = (ProductoVO) search(prodMod);
        TipoProducto tipoProductoBD = prodBD.getTipoProducto();
        double precioBD = prodBD.getPrecio();
        TipoMaterial materialBD = prodBD.getMaterial();
        ProveedorVO proveedorBD = prodBD.getProveedor();
        String descripcionBD = prodBD.getDescripcion();
        int IDVentaBD = prodBD.getIDVenta();
        java.sql.Blob blobBD = prodBD.getImagen();

        if (!tipoProductoMod.toString().equals(tipoProductoBD.toString())) {
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE producto SET TipoProducto=? WHERE IDProducto=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, tipoProductoMod.toString());
                pst.setInt(2, IDProdMod);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }
        // cambiamos precio
        if (precioMod != precioBD) {
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE producto SET Precio=? WHERE IDProducto=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setDouble(1, precioMod);
                pst.setInt(2, IDProdMod);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }
        // cambiamos material
        if (!materialMod.toString().equals(materialBD.toString())) {
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE producto SET Material=? WHERE IDProducto=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, materialMod.toString());
                pst.setInt(2, IDProdMod);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }
        // cambiamos proveedor
        if (!proveedorMod.equals(proveedorBD)) {
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE producto SET Proveedor=? WHERE IDProducto=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, proveedorMod.getCIF());
                pst.setInt(2, IDProdMod);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }
        // cambiamos descripcion
        if (!descripcionMod.equals(descripcionBD)) {
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE producto SET Descripcion=? WHERE IDProducto=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, descripcionMod);
                pst.setInt(2, IDProdMod);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }
        // cambiamos idventa
        if (IDVentaMod != IDVentaBD) {
            try {
                Conexion conexionBD = Conexion.getInstance();
                Connection con = conexionBD.getConexion();
                String query = "UPDATE producto SET IDVenta=? WHERE IDProducto=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, IDVentaMod);
                pst.setInt(2, IDProdMod);
                pst.executeUpdate();
                // con.close();
            } catch (Exception e) {
                return false;
            }
        }

        // cambiamos imagen

        try {
            String strBlob1;
            String strBlob2;
            if (blob != null) {
                byte[] bdata1 = blob.getBytes(1, (int) blob.length());
                strBlob1 = new String(bdata1);
            } else {
                strBlob1 = new String();
            }
            if (blobBD != null) {
                byte[] bdata2 = blobBD.getBytes(1, (int) blobBD.length());
                strBlob2 = new String(bdata2);
            } else {
                strBlob2 = new String();
            }

            if (!(strBlob1.equals(strBlob2))) {
                try {
                    Conexion conexionBD = Conexion.getInstance();
                    Connection con = conexionBD.getConexion();
                    String query = "UPDATE producto SET Imagen=? WHERE IDProducto=?";
                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setBlob(1, blob);
                    pst.setInt(2, IDProdMod);
                    pst.executeUpdate();
                    // con.close();
                } catch (Exception e) {
                    return false;
                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return true;
    }

    /**
     * @param objeto objeto de tipo VO a eliminar de la base de datos
     * @return boolean devuelve true si se ha eliminado con exito. False en caso de
     *         que no se haya eliminado o que directamente no exista ese objeto en
     *         la BBDD.
     */
    public boolean delete(Object objeto) {
        ProductoVO prodDel = (ProductoVO) objeto;
        if (!exist(prodDel)) {
            return false;
        }
        int IDProd = prodDel.getIDProducto();
        try {
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            String query = "DELETE FROM producto WHERE IDProducto=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, IDProd);
            pst.executeUpdate();
            // con.close();
            prodDel.setIDProducto(-1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param objeto objeto de tipo VO para comprobar si existe en la BBDD
     * @return boolean true o false dependiendo de si existe o no en la BBDD
     */
    @Override
    public boolean exist(Object objeto) {
        ProductoVO producto = (ProductoVO) objeto;
        return (producto.getIDProducto() != -1);
    }

    public Object getProductoPorID(int id) {
        ProductoVO placeholder = new ProductoVO(TipoProducto.ANILLO, 100, null, TipoMaterial.OROPLATA, null,
                "descripcion");
        placeholder.setIDProducto(id);
        return search(placeholder);
    }

    public ArrayList<ProductoVO> getListaProductos() {
        try {
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            String query = "SELECT * FROM producto";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            ArrayList<ProductoVO> listaProductos = new ArrayList<ProductoVO>();
            ProveedorDAO provDAO = ProveedorDAO.getInstance();
            while (rs.next()) {
                // Obtenemos los datos del producto de la db
                int iDProducto = rs.getInt("IDproducto");
                TipoProducto tipo = TipoProducto.valueOf(rs.getString("TipoProducto"));
                double precio = rs.getDouble("Precio");
                Blob imagen = rs.getBlob("Imagen");
                TipoMaterial material = TipoMaterial.valueOf(rs.getString("Material"));
                ProveedorVO proveedor = (ProveedorVO) provDAO
                        .search(new ProveedorVO(rs.getString("Proveedor"), "nombre"));
                int iDVenta = rs.getInt("IDVenta");
                String descripcion = rs.getString("Descripcion");

                // Creamos el producto con los datos obtenidos
                ProductoVO producto = new ProductoVO(tipo, precio, imagen, material, proveedor, iDVenta, descripcion);
                producto.setIDProducto(iDProducto);

                // Añadimos el producto a la lista
                listaProductos.add(producto);
            }
            // con.close();
            return listaProductos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<ProductoVO> getProductosSegunIDVenta(int IDVenta) {
        try {
            Conexion conexionBD = Conexion.getInstance();
            Connection con = conexionBD.getConexion();
            String query = "SELECT * FROM producto WHERE IDVenta=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, IDVenta);
            ResultSet rs = pst.executeQuery();
            ArrayList<ProductoVO> listaProductos = new ArrayList<ProductoVO>();
            ProveedorDAO provDAO = ProveedorDAO.getInstance();

            while (rs.next()) {
                // Obtenemos los datos del producto de la db
                int iDProducto = rs.getInt("IDproducto");
                TipoProducto tipo = TipoProducto.valueOf(rs.getString("TipoProducto"));
                double precio = rs.getDouble("Precio");
                Blob imagen = rs.getBlob("Imagen");
                TipoMaterial material = TipoMaterial.valueOf(rs.getString("Material"));
                ProveedorVO proveedor = (ProveedorVO) provDAO
                        .search(new ProveedorVO(rs.getString("Proveedor"), "nombre"));
                int iDVenta = rs.getInt("IDVenta");
                String descripcion = rs.getString("Descripcion");

                // Creamos el producto con los datos obtenidos
                ProductoVO producto = new ProductoVO(tipo, precio, imagen, material, proveedor, iDVenta, descripcion);
                producto.setIDProducto(iDProducto);

                // Añadimos el producto a la lista
                listaProductos.add(producto);
            }
            // con.close();
            return listaProductos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

// Codigo para convertir una imagen del ordenador en un blob que se le pasa al
// producto que se crea
// java.awt.image.BufferedImage img = ImageIO.read(new
// FileInputStream("pathDeLaImagen"));
// ByteArrayOutputStream baos = new ByteArrayOutputStream();
// ImageIO.write(img, "jpg", baos); en jpg se pone el formato no se si es
// totalmente necesario
// byte[] bytes = baos.toByteArray();
// java.sql.Blob blob = new SerialBlob(bytes);

// Codigo para coger un blob de un producto y guardarlo en una imagen en tu
// ordenador
// java.sql.Blob blob = prod.getImagen();
// java.io.InputStream in = blob.getBinaryStream();
// java.awt.image.BufferedImage image = ImageIO.read(in);
// File outputfile = new File("pathDelArhivoDondeSeEscribiraLaImagen");
// ImageIO.write(image, "jpg", outputfile);
