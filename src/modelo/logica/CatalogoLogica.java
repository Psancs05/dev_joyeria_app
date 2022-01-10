package modelo.logica;

import java.util.ArrayList;

import globals.enums.TipoMaterial;
import globals.enums.TipoProducto;
import modelo.DAO.ProductoDAO;
import modelo.VO.ProductoVO;

public class CatalogoLogica {
    private ProductoDAO productoDAO;

    public CatalogoLogica() {
        this.productoDAO = ProductoDAO.getInstance();
    }

    public ArrayList<ProductoVO> filtrarCatalogo(ProductoVO parametros) {
        ArrayList<ProductoVO> listaCompleta = productoDAO.getListaProductos();
        ArrayList<ProductoVO> listaFiltrada = new ArrayList<>();

        TipoProducto tipo = parametros.getTipoProducto();
        double precio = parametros.getPrecio();
        TipoMaterial material = parametros.getMaterial();
        String nombreProveedor = null;
        String CIF = null;
        if(parametros.getProveedor() != null){
            nombreProveedor = parametros.getProveedor().getNombre();
            CIF = parametros.getProveedor().getCIF();
        }
        int idVenta = parametros.getIDVenta();

        for(ProductoVO producto: listaCompleta){
            boolean aniadir = true;
            if(tipo != null){
                if(!(tipo.equals(producto.getTipoProducto()))){
                    aniadir = false;
                }
            } 
            if(precio != -1.0){
                if(precio != producto.getPrecio()){
                    aniadir = false;
                }
            } 
            if(material != null){
                if(!(material.equals(producto.getMaterial()))){
                    aniadir = false;
                }
            } 
            if(nombreProveedor != null){
                if(!(nombreProveedor.equals(producto.getProveedor().getNombre()))){
                    aniadir = false;
                }
            } 
            if(CIF != null){
                if(!(CIF.equals(producto.getProveedor().getCIF()))){
                    aniadir = false;
                }
            } 
            if(idVenta != -1){
                if(idVenta != producto.getIDVenta()){
                    aniadir = false;
                }
            }

            if(aniadir)
                listaFiltrada.add(producto);
        }
        return listaFiltrada;
    }

    public ArrayList<ProductoVO> obtenerTodosProductos() {
        return productoDAO.getListaProductos();
    }
}
