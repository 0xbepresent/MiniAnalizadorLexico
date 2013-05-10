package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.Eol;


public class SaltosLinea extends CategoriaSintacticaBase {

	public SaltosLinea() {
		producciones = new Class<?>[][]{
			{ Eol.class, SaltosLinea.class },
			{ Eol.class },
		};
	}

}
