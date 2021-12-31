package LogicaNegocio;

import modelo.VO.ProductoVO;
import modelo.logica.ProductoLogica;

public class ProductoControlador {

	private ProductoLogica logicaProducto;

	public ProductoControlador(ProductoLogica logicaProducto) {
		this.logicaProducto = logicaProducto;
	}

	public void aniadirProducto(String taller, String tipo, String material, int numCuaderno, int ID) {
		//TODO
	}
	
	public void modificarProducto(ProductoVO productoAntiguo, String taller, String tipo, String material, int numCuaderno, int ID) {
		//TODO
	}
	
	public void eliminarProducto(ProductoVO producto) {
		//TODO
	}

	public ProductoLogica getLogicaProducto() {
		return logicaProducto;
	}

	public void setLogicaProducto(ProductoLogica logicaProducto) {
		this.logicaProducto = logicaProducto;
	}
}
