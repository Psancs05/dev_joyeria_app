package LogicaNegocio;

import java.util.ArrayList;

import Vista.Catalogo.CatalogoVista;
import Vista.Catalogo.FiltrarVista;
import globals.enums.TipoCatalogo;
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

	
	/** 
	 * Singleton de CatalogoControlador
	 * @return CatalogoControlador
	 */
	public static CatalogoControlador getInstance() {
		if (controlador == null) {
			controlador = new CatalogoControlador();
		}
		return controlador;
	}

	
	/** 
	 * Metodo que actualiza la vista de catalogo al estado pasado por parametro
	 * @param estado tipo de catalogo que queremos mostrar
	 */
	public void actualizar(TipoCatalogo estado) {
		this.catalogo = new CatalogoVista(estado);
	}

	/** 
	 * Metodo que muestra un catalogo con los productos pasados por parametro
	 * @param productos productos a mostrar
	 */
	public void mostrarCatalogo(ArrayList<ProductoVO> productos) {
		catalogo.mostrarCatalogo(productos);
	}

	/** 
	 * Metodo que muestra la vista para filtrar el catalogo
	 */
	public void mostrarCatalogoFiltar() {
		filtrar = new FiltrarVista();
		filtrar.mostrarVistaFiltrado();
	}

	/** 
	 * Get
	 * @return CatalogoLogica
	 */
	public CatalogoLogica getLogicaCatalogo() {
		return logicaCatalogo;
	}

	
	/** 
	 * Get
	 * @param parametros
	 * @return ArrayList<ProductoVO>
	 */
	public ArrayList<ProductoVO> getListadaFiltrada(ProductoVO parametros) {
		return logicaCatalogo.filtrarCatalogo(parametros);
	}

	
	/** 
	 * Set
	 * @param logicaCatalogo
	 */
	public void setLogicaCatalogo(CatalogoLogica logicaCatalogo) {
		this.logicaCatalogo = logicaCatalogo;
	}
}
