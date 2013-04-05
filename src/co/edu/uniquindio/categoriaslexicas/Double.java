package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.Token;

public class Double extends CategoriaLexicaBase {

	@Override
	public Token aceptar() {
		// Lexema recuperado.
		String lexema = "";
		
		if(!Character.isDigit(analizadorLexico.getCaracterActual())) {
			return null;
		}
		else {
			// Estado 1.
			lexema += analizadorLexico.getCaracterActual();
			
			do {
				// Se consume los digitos.
				analizadorLexico.irSiguienteCaracter();

				// Se puede haber llegado al caracter que rompe el ciclo.
				if(Character.isDigit(analizadorLexico.getCaracterActual())) {
					lexema += analizadorLexico.getCaracterActual();
				}
			}while(Character.isDigit(analizadorLexico.getCaracterActual()));
			
			if(analizadorLexico.getCaracterActual() != '.') {
				// Se rechaza.
				return null;
			}
			else {
				lexema += analizadorLexico.getCaracterActual();
				analizadorLexico.irSiguienteCaracter();
				// Estado 2.
				if(!Character.isDigit(analizadorLexico.getCaracterActual())) {
					// Se rechaza.
					return null;
				}
				else {
					// Estado 3.
					lexema += analizadorLexico.getCaracterActual();
					
					do {
						// Se consume los digitos.
						analizadorLexico.irSiguienteCaracter();
						analizadorLexico.getCaracterActual();
						
						// Se puede haber llegado al caracter que rompe el ciclo.
						if(Character.isDigit(analizadorLexico.getCaracterActual())) {
							lexema += analizadorLexico.getCaracterActual();
						}
					}while(Character.isDigit(analizadorLexico.getCaracterActual()));
					
					// Se acepta el token y se retorna.
					return new Token(getClass(), lexema);
				}
			}
		}
	}

}
