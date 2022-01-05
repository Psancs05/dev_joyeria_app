package modelo.VO;

import java.util.ArrayList;
import java.text.SimpleDateFormat; 
import java.util.Date;  

public class VentaVO {
    private int ID;
    private Date fecha;
    private int cantidadArticulos;
    private double precioTotal;
    private ArrayList<ProductoVO> productos;
    private String DNIUsuario;
    private String direccionFacturacion;

    public VentaVO(Date date, int cantidadArticulos, double precioTotal, ArrayList<ProductoVO> productos, String DNIUsuario, String direccionFacturacion) {
        this.ID = -1;
        this.fecha = date;
        this.cantidadArticulos = cantidadArticulos;
        this.precioTotal = precioTotal;
        this.productos = productos;
        this.DNIUsuario = DNIUsuario;
        this.direccionFacturacion = direccionFacturacion;
    }

    public VentaVO(int ID,Date date, int cantidadArticulos, double precioTotal, ArrayList<ProductoVO> productos, String DNIUsuario, String direccionFacturacion) {
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

    public String getDireccionFacturacion(){
        return this.direccionFacturacion;
    }

    public void setDireccionFacturacion(String direccion){
        this.direccionFacturacion = direccion;
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("IDventa: " + ID + "\nFecha: " + fecha.toString() + "\nCantidad de articulos: " + cantidadArticulos + "\nPrecio: " + precioTotal + "\nDNIEmpleado: " + DNIUsuario 
        + "\nDireccion de facturacion\n");
        for(ProductoVO producto: productos){
            buffer.append("ID producto: " + producto.getIDProducto() + " , " );
            buffer.append("Material:  " + producto.getMaterial() + "\n");
        }
        return buffer.toString();
    }
}
