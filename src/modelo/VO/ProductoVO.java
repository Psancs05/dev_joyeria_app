package modelo.VO;

import java.sql.Blob;

public class ProductoVO {
    String tipoPieza;
    double precio;
    Blob imagen;
    String material;
    ProveedorVO proveedor;
    int IDVenta;

    public ProductoVO(String tipoPieza, double precio, Blob imagen, String material, ProveedorVO proveedor,
            int IDVenta) {
        this.tipoPieza = tipoPieza;
        this.precio = precio;
        this.imagen = imagen;
        this.material = material;
        this.proveedor = proveedor;
        this.IDVenta = IDVenta;
    }

    // constructor para crear un producto sin venta. Si el ID de la venta es 0, el
    // producto NO esta vendido
    public ProductoVO(String tipoPieza, double precio, Blob imagen, String material, ProveedorVO proveedor) {
        this.tipoPieza = tipoPieza;
        this.precio = precio;
        this.imagen = imagen;
        this.material = material;
        this.proveedor = proveedor;
        this.IDVenta = 0;
    }

    public String getTipoPieza() {
        return tipoPieza;
    }

    public void setTipoPieza(String tipoPieza) {
        this.tipoPieza = tipoPieza;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Blob getImagen() {
        return imagen;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public ProveedorVO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorVO proveedor) {
        this.proveedor = proveedor;
    }

}
