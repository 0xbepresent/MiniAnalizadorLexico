package co.edu.uniquindio;

public class Simbolo {
	
	private final String lexema;
	
	public Simbolo(String lexema) {
		this.lexema = lexema;
	}

	public String getLexema() {
		return lexema;
	}
	
	@Override
	public String toString() {
		return String.format("{Lexema = %s}", lexema);
	}
}
