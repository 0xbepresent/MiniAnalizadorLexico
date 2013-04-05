package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.Cadena;
import co.edu.uniquindio.categoriaslexicas.Identificador;

public class TerminoC extends CategoriaSintacticaBase {

	public TerminoC() {
		producciones = new Class<?>[][]{
			{ Identificador.class },
			{ Cadena.class }
		};
	}

}
