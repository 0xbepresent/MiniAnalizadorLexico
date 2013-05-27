package co.edu.uniquindio;

// TODO Se realiza una implementación básica.
public class SimboloMetodo extends Simbolo {
	
	private final String tipoRetorno;

	public SimboloMetodo(String lexema, String tipoRetorno) {
		super(lexema);
		this.tipoRetorno = tipoRetorno;
	}
	
	public String getTipoRetorno() {
		return tipoRetorno;
	}
	
	@Override
	public String toString() {
		return String.format("{DeclaracionMetodo: Lexema = %s, TipoRetorno = %s}", lexema, tipoRetorno);
	}

}
