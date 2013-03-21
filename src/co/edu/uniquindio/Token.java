package co.edu.uniquindio;

public class Token {
	
	private final TipoToken tipoToken;
	private final String lexema;
	private String valor;

	public Token(TipoToken tipoToken, String lexema) {
		this.tipoToken = tipoToken;
		this.lexema = lexema;
	}
	
	public Token(TipoToken tipoToken, String lexema, String valor) {
		this.tipoToken = tipoToken;
		this.lexema = lexema;
		this.valor = valor;
	}
	
	public TipoToken getTipo() {
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
		return String.format("{TipoToken: %s, Lexema: %s, Valor: %s}", tipoToken, lexema, valor);
	}

}
