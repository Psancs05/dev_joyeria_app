package modelo.VO;

import java.sql.Date;
import java.util.ArrayList;

public class VentaVO {
    private int ID;
    private java.util.Date fecha;
    private int cantidadArticulos;
    private double precioTotal;
    private ArrayList<ProductoVO> productos;
    private String DNIUsuario;

    public VentaVO(java.util.Date date, int cantidadArticulos, double precioTotal, ArrayList<ProductoVO> productos, String DNIUsuario) {
        this.ID = -1;
        this.fecha = date;
        this.cantidadArticulos = cantidadArticulos;
        this.precioTotal = precioTotal;
        this.productos = productos;
        this.DNIUsuario = DNIUsuario;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public java.util.Date getFecha() {
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
}
