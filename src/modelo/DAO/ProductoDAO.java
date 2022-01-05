package modelo.DAO;

import java.sql.Blob;
import java.util.ArrayList;
import java.sql.*;

import globals.enums.*;
import modelo.VO.ProductoVO;
import modelo.VO.ProveedorVO;

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
        // ya veremos
        // Blob imagen = producto.getImagen();
        TipoMaterial material = producto.getMaterial();
        ProveedorVO proveedor = producto.getProveedor();
        String descripcion = producto.getDescripcion();

        if (exist(producto)) {
            return false;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root", "rootroot");
            // TODO: imagen
            String query = "INSERT INTO producto (TipoProducto, Precio, Material, Proveedor, Descripcion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, tipoProducto.toString());
            pst.setDouble(2, precio);
            pst.setString(3, material.toString());
            pst.setString(4, proveedor.getCIF());
            pst.setString(5, descripcion);
            pst.executeUpdate();

            int idProducto = -1;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

            if (rs.next()) {
                idProducto = rs.getInt(1);
            } else {
                // throw an exception from here
            }
            con.close();
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root", "rootroot");
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
                ProductoVO nuevoProd = new ProductoVO(tipoProducto, precio, null, material, proveedor, IDVenta,
                        descripcion);
                con.close();
                return nuevoProd;
            } else {
                con.close();
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

        // info del de la bbdd
        ProductoVO prodBD = (ProductoVO) search(prodMod);
        TipoProducto tipoProductoBD = prodBD.getTipoProducto();
        double precioBD = prodBD.getPrecio();
        TipoMaterial materialBD = prodBD.getMaterial();
        ProveedorVO proveedorBD = prodBD.getProveedor();
        String descripcionBD = prodBD.getDescripcion();
        int IDVentaBD = prodBD.getIDVenta();

        if (!tipoProductoMod.toString().equals(tipoProductoBD.toString())) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root",
                        "rootroot");
                String query = "UPDATE producto SET TipoProducto=? WHERE IDProducto=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, tipoProductoMod.toString());
                pst.setInt(2, IDProdMod);
                pst.executeUpdate();
                con.close();
            } catch (Exception e) {
                return false;
            }
        }
        // cambiamos precio
        if (precioMod != precioBD) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root",
                        "rootroot");
                String query = "UPDATE producto SET Precio=? WHERE IDProducto=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setDouble(1, precioMod);
                pst.setInt(2, IDProdMod);
                pst.executeUpdate();
                con.close();
            } catch (Exception e) {
                return false;
            }
        }
        // cambiamos material
        if (!materialMod.toString().equals(materialBD.toString())) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root",
                        "rootroot");
                String query = "UPDATE producto SET Material=? WHERE IDProducto=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, materialMod.toString());
                pst.setInt(2, IDProdMod);
                pst.executeUpdate();
                con.close();
            } catch (Exception e) {
                return false;
            }
        }
        // cambiamos proveedor
        if (!proveedorMod.equals(proveedorBD)) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root",
                        "rootroot");
                String query = "UPDATE producto SET Proveedor=? WHERE IDProducto=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, proveedorMod.getCIF());
                pst.setInt(2, IDProdMod);
                pst.executeUpdate();
                con.close();
            } catch (Exception e) {
                return false;
            }
        }
        // cambiamos descripcion
        if (!descripcionMod.equals(descripcionBD)) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root",
                        "rootroot");
                String query = "UPDATE producto SET Descripcion=? WHERE IDProducto=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, descripcionMod);
                pst.setInt(2, IDProdMod);
                pst.executeUpdate();
                con.close();
            } catch (Exception e) {
                return false;
            }
        }
        // cambiamos IDVenta
        if (IDVentaMod != IDVentaBD) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root",
                        "rootroot");
                String query = "UPDATE producto SET IDVenta=? WHERE IDProducto=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setInt(1, IDVentaMod);
                pst.setInt(2, IDProdMod);
                pst.executeUpdate();
                con.close();
            } catch (Exception e) {
                return false;
            }
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root",
                    "rootroot");
            String query = "DELETE FROM producto WHERE IDProducto=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, IDProd);
            pst.executeUpdate();
            con.close();
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

    // ? Deberia ser un ArrayList de ProductoVO o de Object??
    public ArrayList<ProductoVO> getListaProductos() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdJoyeria", "root",
                    "rootroot");
            String query = "SELECT * FROM producto";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            ArrayList<ProductoVO> listaProductos = new ArrayList<ProductoVO>();
            while (rs.next()) {
                // Obtenemos los datos del producto de la db
                int iDProducto = rs.getInt("IDproducto");
                TipoProducto tipo = TipoProducto.valueOf(rs.getString("TipoPieza"));
                double precio = rs.getDouble("Precio");
                Blob imagen = rs.getBlob("Imagen");
                TipoMaterial material = TipoMaterial.valueOf(rs.getString("Material"));
                ProveedorVO proveedor = new ProveedorVO(rs.getString("Proveedor"), "nombre");
                int iDVenta = rs.getInt("IDVenta");
                String descripcion = rs.getString("Descripcion");

                // Creamos el producto con los datos obtenidos
                ProductoVO producto = new ProductoVO(tipo, precio, imagen, material, proveedor, iDVenta, descripcion);
                producto.setIDProducto(iDProducto);

                // Añadimos el producto a la lista
                listaProductos.add(producto);
            }
            con.close();
            return listaProductos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
