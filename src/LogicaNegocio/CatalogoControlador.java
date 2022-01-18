package LogicaNegocio;

import java.util.ArrayList;

import Vista.Catalogo.CatalogoVista;
import Vista.Catalogo.FiltrarVista;
import modelo.VO.ProductoVO;
import modelo.logica.CatalogoLogica;

public class CatalogoControlador {

	private static CatalogoControlador controlador;
	private CatalogoLogica logicaCatalogo;
	private FiltrarVista filtrar;
	private CatalogoVista catalogo;
	

	private CatalogoControlador() {
		this.logicaCatalogo = new CatalogoLogica();
		this.filtrar = new FiltrarVista();
	}

	public static CatalogoControlador getInstance() {
		if (controlador == null) {
			controlador = new CatalogoControlador();
		}
		return controlador;
	}

	
	public void gestionarAccionCatalogo() {
		//TODO
	}
	
	public void mostrarCatalogo(ArrayList<ProductoVO> productos) {
		catalogo.mostrarCatalogo(productos);
	}

	public void mostrarCatalogoFiltar() {
		filtrar.mostrarVistaFiltrado();
	}

	public CatalogoLogica getLogicaCatalogo() {
		return logicaCatalogo;
	}

	public ArrayList<ProductoVO> getListadaFiltrada(ProductoVO parametros){
		return logicaCatalogo.filtrarCatalogo(parametros);
	}

	public void setLogicaCatalogo(CatalogoLogica logicaCatalogo) {
		this.logicaCatalogo = logicaCatalogo;
	}
}
