package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.Token;

public class CategoriaLexicaPalabras extends CategoriaLexicaBase {

	protected String[] palabras;

	@Override
	public Token aceptar() {
		
		for (String palabra : palabras) {
			analizadorLexico.establecerRegresoBacktracking();
			
			boolean completoPalabra = true;
			String lexema = "";
			for (int i = 0; i < palabra.length(); i++) {
				char caracterActual = analizadorLexico.getCaracterActual();
				lexema += caracterActual;
				if(caracterActual == palabra.charAt(i)) {
					analizadorLexico.irSiguienteCaracter();
				}
				else {
					completoPalabra = false;
					break;
				}
			}
			
			if(completoPalabra) {
				return new Token(getClass(), lexema, getClass().getSimpleName());
			}
			else {
				analizadorLexico.regresarBacktracking();
			}
		}
		
		// Si no pudo encontrar ninguna palabra.
		return null;
	}

}
