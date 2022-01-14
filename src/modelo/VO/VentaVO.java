package modelo.VO;

import java.util.ArrayList;
import java.util.Date;

public class VentaVO {
    private int ID;
    private Date fecha;
    private int cantidadArticulos;
    private double precioTotal;
    private ArrayList<ProductoVO> productos;
    private String DNIUsuario;
    private String direccionFacturacion;

    public VentaVO(Date date, int cantidadArticulos, double precioTotal, ArrayList<ProductoVO> productos,
            String DNIUsuario, String direccionFacturacion) {
        this.ID = -1;
        this.fecha = date;
        this.cantidadArticulos = cantidadArticulos;
        this.precioTotal = precioTotal;
        this.productos = productos;
        this.DNIUsuario = DNIUsuario;
        this.direccionFacturacion = direccionFacturacion;
    }

    public VentaVO(int ID, Date date, int cantidadArticulos, double precioTotal, ArrayList<ProductoVO> productos,
            String DNIUsuario, String direccionFacturacion) {
        this.ID = ID;
        this.fecha = date;
        this.cantidadArticulos = cantidadArticulos;
        this.precioTotal = precioTotal;
        this.productos = productos;
        this.DNIUsuario = DNIUsuario;
        this.direccionFacturacion = direccionFacturacion;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidadArticulos() {
        return cantidadArticulos;
    }

    public void setCantidadArticulos(int cantidadArticulos) {
        this.cantidadArticulos = cantidadArticulos;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public ArrayList<ProductoVO> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<ProductoVO> productos) {
        this.productos = productos;
    }

    public String getDNIUsuario() {
        return DNIUsuario;
    }

    public void setDNIUsuario(String DNIUsuario) {
        this.DNIUsuario = DNIUsuario;
    }

    public String getDireccionFacturacion() {
        return this.direccionFacturacion;
    }

    public void setDireccionFacturacion(String direccion) {
        this.direccionFacturacion = direccion;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VentaVO other = (VentaVO) obj;
        if (ID != other.ID)
            return false;
        if (fecha.compareTo(other.fecha) != 0)
            return false;
        if (Double.doubleToLongBits(precioTotal) != Double.doubleToLongBits(other.precioTotal))
            return false;
        if (cantidadArticulos != other.cantidadArticulos)
            return false;
        if (!(DNIUsuario.equals(other.DNIUsuario)))
            return false;
        if (!(direccionFacturacion.equals(other.direccionFacturacion)))
            return false;
        if (productos.size() != other.productos.size()) {
            return false;
        }
        for (int i = 0; i < productos.size(); i++) {
            ProductoVO prod1 = productos.get(i);
            ProductoVO prod2 = other.productos.get(i);
            if (!(prod1.equals(prod2))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("IDventa: " + ID + "\nFecha: " + fecha.toString() + "\nCantidad de articulos: "
                + cantidadArticulos + "\nPrecio: " + precioTotal + "\nDNIEmpleado: " + DNIUsuario
                + "\nDireccion de facturacion\n");
        for (ProductoVO producto : productos) {
            buffer.append("ID producto: " + producto.getIDProducto() + " , ");
            buffer.append("Material:  " + producto.getMaterial() + "\n");
        }
        return buffer.toString();
    }
}