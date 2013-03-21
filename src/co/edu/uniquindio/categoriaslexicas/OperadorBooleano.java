package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.TipoToken;

public class OperadorBooleano extends CategoriaLexicaPalabras {

	public OperadorBooleano() {
		palabras = new String[]{ "&&", "||" };
		tipoToken = TipoToken.OperadorBooleano;
	}

}
