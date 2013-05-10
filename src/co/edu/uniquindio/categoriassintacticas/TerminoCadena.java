package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.Cadena;
import co.edu.uniquindio.categoriaslexicas.Identificador;

public class TerminoCadena extends CategoriaSintacticaBase {

	public TerminoCadena() {
		producciones = new Class<?>[][]{
			{ Identificador.class },
			{ Cadena.class }
		};
	}

}
