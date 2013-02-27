package co.edu.uniquindio;

public class Token {
	
	private final Tipo tipo;
	private final String valor;

	public Token(Tipo tipo, String valor) {
		this.tipo = tipo;
		this.valor = valor;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public String getValor() {
		return valor;
	}
	
	@Override
	public String toString() {
		return String.format("{Tipo: %s, Valor: %s}", tipo, valor);
	}

}
