package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.OperadorConcatenacion;

public class ExpresionCadena extends CategoriaSintacticaBase {

	public ExpresionCadena() {
		producciones = new Class<?>[][]{
			{ TerminoC.class, OperadorConcatenacion.class, TerminoC.class },
			{ TerminoC.class }
		};
	}

}
