package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.TipoToken;

public class OperadorAritmetico extends CategoriaLexicaPalabras {

	public OperadorAritmetico() {
		palabras = new String[]{ "+", "-", "*", "/", "%" };
		tipoToken = TipoToken.OperadorAritmetico;
	}

}
