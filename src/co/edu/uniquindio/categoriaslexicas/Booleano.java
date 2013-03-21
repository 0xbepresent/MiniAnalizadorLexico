package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.TipoToken;

public class Booleano extends CategoriaLexicaPalabras {

	public Booleano() {
		palabras = new String[]{ "true", "false" };
		tipoToken = TipoToken.Booleano;
	}

}
