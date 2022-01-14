package modelo.VO;

import globals.enums.TipoMaterial;
import globals.enums.TipoProducto;

public class ProductoVO {
    int IDProducto;
    int numCuaderno;
    String nombre;
    TipoProducto tipoProducto;
    double precio;
    java.sql.Blob imagen;
    TipoMaterial material;
    ProveedorVO proveedor;
    int IDVenta;
    String descripcion;

    // iniciamos el IDProd a -1 eso quiere decir que AUN no esta en la BBDD
    public ProductoVO(String nombre, int numCuaderno, TipoProducto tipoProducto, double precio, java.sql.Blob imagen,
            TipoMaterial material,
            ProveedorVO proveedor, int IDVenta, String descripcion) {
        this.nombre = nombre;
        this.numCuaderno = numCuaderno;
        this.tipoProducto = tipoProducto;
        this.precio = precio;
        this.imagen = imagen;
        this.material = material;
        this.proveedor = proveedor;
        this.IDVenta = IDVenta;
        this.IDProducto = -1;
        this.descripcion = descripcion;
    }

    // constructor para crear un producto sin venta. Si el ID de la venta es 0, el
    // producto NO esta vendido
    // iniciamos el IDProd a -1 eso quiere decir que AUN no esta en la BBDD
    public ProductoVO(String nombre, int numCuaderno, TipoProducto tipoProducto, double precio, java.sql.Blob imagen,
            TipoMaterial material, ProveedorVO proveedor, String descripcion) {
        this.nombre = nombre;
        this.numCuaderno = numCuaderno;
        this.tipoProducto = tipoProducto;
        this.precio = precio;
        this.imagen = imagen;
        this.material = material;
        this.proveedor = proveedor;
        this.IDVenta = 0;
        this.IDProducto = -1;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumCuaderno() {
        return numCuaderno;
    }

    public void setNumCuaderno(int numCuaderno) {
        this.numCuaderno = numCuaderno;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public java.sql.Blob getImagen() {
        return imagen;
    }

    public void setImagen(java.sql.Blob imagen) {
        this.imagen = imagen;
    }

    public TipoMaterial getMaterial() {
        return material;
    }

    public void setMaterial(TipoMaterial material) {
        this.material = material;
    }

    public ProveedorVO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorVO proveedor) {
        this.proveedor = proveedor;
    }

    public int getIDVenta() {
        return IDVenta;
    }

    public void setIDVenta(int iDVenta) {
        IDVenta = iDVenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIDProducto() {
        return IDProducto;
    }

    public void setIDProducto(int iDProducto) {
        IDProducto = iDProducto;
    }

    @Override
    public String toString() {
        return "ProductoVO [Nombre= " + nombre + "numero de cuaderno= " + numCuaderno + "IDVenta=" + IDVenta
                + ", imagen=" + imagen + ", material=" + material + ", precio=" + precio
                + ", proveedor=" + proveedor + ", tipoProducto=" + tipoProducto + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductoVO other = (ProductoVO) obj;
        if (IDVenta != other.IDVenta)
            return false;
        if (!nombre.equals(other.nombre)) {
            return false;
        }
        if (numCuaderno != other.numCuaderno)
            return false;
        if (imagen == null) {
            if (other.imagen != null)
                return false;
        } else if (!imagen.equals(other.imagen))
            return false;
        if (material == null) {
            if (other.material != null)
                return false;
        } else if (!material.equals(other.material))
            return false;
        if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
            return false;
        if (proveedor == null) {
            if (other.proveedor != null)
                return false;
        } else if (!proveedor.equals(other.proveedor))
            return false;
        if (tipoProducto != other.tipoProducto)
            return false;
        return true;
    }

}
