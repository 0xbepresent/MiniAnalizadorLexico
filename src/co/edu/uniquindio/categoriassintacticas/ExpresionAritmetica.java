package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.OperadorAritmetico;

public class ExpresionAritmetica extends CategoriaSintacticaBase {

	public ExpresionAritmetica() {
		
		producciones = new Class<?>[][]{
			{ TerminoAritmetico.class, OperadorAritmetico.class, ExpresionAritmetica.class },
			{ TerminoAritmetico.class },
		};
	}

}
