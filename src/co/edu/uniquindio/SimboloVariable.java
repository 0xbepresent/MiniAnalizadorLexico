package co.edu.uniquindio;


// TODO Se realiza una implementación básica.
public class SimboloVariable extends Simbolo {
	
	private final String tipoVariable;
	
	/** Si no tiene valor es porque su alcance es global */
	private final SimboloMetodo simboloMetodo;
	
	public SimboloVariable(String lexema, String tipoVariable, SimboloMetodo metodo) {
		super(lexema);
		this.tipoVariable = tipoVariable;
		this.simboloMetodo = metodo;
	}
	
	public String getTipoVariable() {
		return tipoVariable;
	}
	
	public SimboloMetodo getSimboloMetodo() {
		return simboloMetodo;
	}
	
	@Override
	public String toString() {
		return String.format("{DeclaracionVariable: Alcance = %s, Lexema = %s, TipoVariable = %s}"
			, simboloMetodo != null ? "Método[" + simboloMetodo.getLexema() + "]" : "Global"
			, lexema
			, tipoVariable);
	}

}
