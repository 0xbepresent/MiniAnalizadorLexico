package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.Booleano;
import co.edu.uniquindio.categoriaslexicas.Identificador;

public class TerminoBooleano extends CategoriaSintacticaBase {

	public TerminoBooleano() {
		producciones = new Class<?>[][]{
			{ ExpresionRelacional.class },
			{ InvocacionMetodo.class },
			{ Identificador.class },
			{ Booleano.class },
		};
	}

}
