package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.Token;

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
			Token token = new Token(getClass().getSimpleName(), "" + caracter, getClass().getSimpleName());
			return token;
		}
	}

}
