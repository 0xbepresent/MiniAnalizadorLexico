package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.TipoToken;
import co.edu.uniquindio.Token;

public class CategoriaLexicaUnCaracter extends CategoriaLexicaBase {
	
	protected char caracter;
	protected TipoToken tipoToken;
	
	@Override
	public Token aceptar() {
		if(analizadorLexico.getCaracterActual() != caracter) {
			// Se rechaza.
			return null;
		}
		else {
			// Se acepta el token y se retorna.
			analizadorLexico.irSiguienteCaracter();
			Token token = new Token(tipoToken, "" + caracter);
			token.setValor(tipoToken.toString());
			return token;
		}
	}

}
