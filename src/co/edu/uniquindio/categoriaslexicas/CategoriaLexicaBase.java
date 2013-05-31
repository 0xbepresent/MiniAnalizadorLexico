package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.AnalizadorLexico;
import co.edu.uniquindio.Token;

public abstract class CategoriaLexicaBase implements CategoriaLexica {

	protected AnalizadorLexico analizadorLexico;

	@Override
	public void setAnalizador(AnalizadorLexico analizadorLexico) {
		this.analizadorLexico = analizadorLexico;
	}
	
	@Override
	public String traducir(Token token) {
		return token.getLexema();
	}
	
}
