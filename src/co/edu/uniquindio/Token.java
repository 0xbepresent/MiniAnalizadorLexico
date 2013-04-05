package co.edu.uniquindio;

import co.edu.uniquindio.categoriaslexicas.CategoriaLexica;

public class Token extends NodoImplemetado {
	
	private final Class<? extends CategoriaLexica> tipoToken;
	private final String lexema;
	private String valor;

	public Token(Class<? extends CategoriaLexica> tipoToken, String lexema) {
		this.tipoToken = tipoToken;
		this.lexema = lexema;
	}
	
	public Token(Class<? extends CategoriaLexica> tipoToken, String lexema, String valor) {
		this.tipoToken = tipoToken;
		this.lexema = lexema;
		this.valor = valor;
	}
	
	public Class<? extends CategoriaLexica> getTipoToken() {
		return tipoToken;
	}
	
	public String getValor() {
		return valor;
	}
	
	public String getLexema() {
		return lexema;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return String.format("[TipoToken: %s, Lexema: %s, Valor: %s]", tipoToken.getSimpleName(), lexema, valor);
	}

}
