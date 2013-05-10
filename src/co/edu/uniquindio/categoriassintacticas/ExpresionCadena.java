package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.OperadorConcatenacion;

public class ExpresionCadena extends CategoriaSintacticaBase {

	public ExpresionCadena() {
		producciones = new Class<?>[][]{
			{ TerminoCadena.class, OperadorConcatenacion.class, ExpresionCadena.class },
			{ TerminoCadena.class }
		};
	}

}
