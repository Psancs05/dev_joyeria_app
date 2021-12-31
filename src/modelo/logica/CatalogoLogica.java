package modelo.logica;

import modelo.DAO.ProductoDAO;
import modelo.VO.ProductoVO;

public class CatalogoLogica {
    private ProductoDAO productoDAO;

    public CatalogoLogica(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public void filtrarCatalogo(ProductoVO parametros) {
        // TODO: implement
    }

    public void obtenerTodosProductos() {
        // TODO: implement
    }
}
