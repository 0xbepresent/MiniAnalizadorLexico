package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.Token;

// TODO Refactorizar ésta categoría base para que no exista
// y las categoría léxicas de éste tipo se manejen como cadenas de un solo
// caracter de la clase CategoriaLexicaPalabras
public class CategoriaLexicaUnCaracter extends CategoriaLexicaBase {
	
	protected char caracter;
	
	@Override
	public Token aceptar() {
		if(analizadorLexico.getCaracterActual() != caracter) {
			// Se rechaza.
			return null;
		}
		else {
			// Se acepta el token y se retorna.
			analizadorLexico.irSiguienteCaracter();
			Token token = new Token(getClass(), "" + caracter);
			return token;
		}
	}

}
