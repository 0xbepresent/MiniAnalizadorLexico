package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.OperadorRelacional;

public class ExpresionRelacional extends CategoriaSintacticaBase {

	public ExpresionRelacional() {
		producciones = new Class<?>[][]{
			{ ExpresionAritmetica.class, OperadorRelacional.class, ExpresionAritmetica.class },
		};
	}

}
