package co.edu.uniquindio;

public enum TipoToken {
	
	// Orden en el que se hace el análisis
	Int,
	Double,
	Separador,
	AgrupadorCodigoApertura,
	AgrupadorCodigoCierre,
	AgrupadorParametrosApertura,
	AgrupadorParametrosCierre,
	OperadorAsignacion,
	SeparadorFor,
	Booleano,
	TipoDato,
	OperadorRelacional,
	OperadorAritmetico,
	OperadorBooleano,
	If,
	Else,
	For,
	Identificador,
	Cadena,
	InvocadorMetodo,
	EOL(false);
	
	private final boolean tienePredicado;
	
	private TipoToken() {
		tienePredicado = true;
	}
	
	private TipoToken(boolean tienePredicado) {
		this.tienePredicado = tienePredicado; 
	}

	public boolean isTienePredicado() {
		return tienePredicado;
	}
	
}
