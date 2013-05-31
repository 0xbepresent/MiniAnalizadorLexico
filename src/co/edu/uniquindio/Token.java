package co.edu.uniquindio;

import co.edu.uniquindio.categoriaslexicas.CategoriaLexica;
import co.edu.uniquindio.categoriaslexicas.Eol;

public class Token extends NodoImplemetado {
	
	private final Class<? extends CategoriaLexica> tipoToken;
	private final String lexema;
	private int linea;
	private int columna;
	private Simbolo simbolo;

	public Token(Class<? extends CategoriaLexica> tipoToken, String lexema) {
		this.tipoToken = tipoToken;
		this.lexema = lexema;
	}
	
	public Token(Class<? extends CategoriaLexica> tipoToken, String lexema, Simbolo simbolo) {
		this.tipoToken = tipoToken;
		this.lexema = lexema;
		this.simbolo = simbolo;
	}
	
	@Override
	public String traducir() {
		try {
			CategoriaLexica categoriaLexica = (CategoriaLexica) tipoToken.newInstance();
			return categoriaLexica.traducir(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public Class<? extends CategoriaLexica> getTipoToken() {
		return tipoToken;
	}
	
	public Simbolo getSimbolo() {
		return simbolo;
	}
	
	public String getLexema() {
		return lexema;
	}
	
	public void setSimbolo(Simbolo simbolo) {
		this.simbolo = simbolo;
	}
	
	@Override
	public String toString() {
		return
			String.format("[TipoToken: %s, Lexema: %s, Símbolo: %s, Línea: %s, Columna: %s]"
			, tipoToken.getSimpleName()
			, tipoToken.equals(Eol.class) ? "\\n" : lexema
			, simbolo
			, linea
			, columna);
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

}
