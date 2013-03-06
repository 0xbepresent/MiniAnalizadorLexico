package co.edu.uniquindio;

public class Token {
	
	private final Tipo tipo;
	private final String lexema;
	private String valor;

	public Token(Tipo tipo, String lexema) {
		this.tipo = tipo;
		this.lexema = lexema;
	}
	
	public Tipo getTipo() {
		return tipo;
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
		return String.format("{Tipo: %s, Lexema: %s, Valor: %s}", tipo, lexema, valor);
	}

}
