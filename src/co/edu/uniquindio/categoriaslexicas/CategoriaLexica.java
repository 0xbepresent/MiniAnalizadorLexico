package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.AnalizadorLexico;
import co.edu.uniquindio.Token;

public interface CategoriaLexica {

	/**
	 * Se configura el contexto de la categoría.
	 * @param analizadorLexico Contexto.
	 */
	public void setAnalizador(AnalizadorLexico analizadorLexico);
	
	/**
	 * Para aceptar un token según su categoría.
	 * @return Token aceptado, de lo contrario null.
	 */
	public Token aceptar();

	public String traducir(Token token);
	
}
