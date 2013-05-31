package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.Token;

public class OperadorConcatenacion extends CategoriaLexicaUnCaracter {

	public OperadorConcatenacion() {
		caracter = '@';
	}
	
	@Override
	public String traducir(Token token) {
		return "+";
	}

}
