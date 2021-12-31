package LogicaNegocio;

import modelo.logica.CatalogoLogica;

public class CatalogoControlador {

	private CatalogoLogica logicaCatalogo;

	public CatalogoControlador(CatalogoLogica logicaCatalogo) {
		this.logicaCatalogo = logicaCatalogo;
	}
	
	public void gestionarAccionCatalogo() {
		//TODO
	}
	
	public void mostrarCatalogo() {
		//TODO
	}

	public CatalogoLogica getLogicaCatalogo() {
		return logicaCatalogo;
	}

	public void setLogicaCatalogo(CatalogoLogica logicaCatalogo) {
		this.logicaCatalogo = logicaCatalogo;
	}
}
