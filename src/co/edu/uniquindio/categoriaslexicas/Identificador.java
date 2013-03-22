package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.Token;

public class Identificador extends CategoriaLexicaBase {
	
	@Override
	public Token aceptar() {

		// Lexema recuperado.
		String lexema = "";

		if (!Character.isAlphabetic(analizadorLexico.getCaracterActual())) {
			return null;
		} else {
			// Estado 1.
			lexema += analizadorLexico.getCaracterActual();

			do {
				// Se consume los caracteres alfabéticos.
				analizadorLexico.irSiguienteCaracter();

				// Se puede haber llegado al caracter que rompe el ciclo.
				if (Character.isAlphabetic(analizadorLexico.getCaracterActual())) {
					lexema += analizadorLexico.getCaracterActual();
				}
			} while (Character.isAlphabetic(analizadorLexico.getCaracterActual()));
			
			// Si se detuvo porque hay un digito siga consumiendo.
			if(Character.isDigit(analizadorLexico.getCaracterActual())) {
				do {
					// Se consume los caracteres alfabéticos y numéricos.
					analizadorLexico.irSiguienteCaracter();
	
					// Se puede haber llegado al caracter que rompe el ciclo.
					if (Character.isAlphabetic(analizadorLexico.getCaracterActual())
						|| Character.isDigit(analizadorLexico.getCaracterActual())) {
						lexema += analizadorLexico.getCaracterActual();
					}
				} while (Character.isAlphabetic(analizadorLexico.getCaracterActual())
						|| Character.isDigit(analizadorLexico.getCaracterActual()));
			}

			// Se acepta el token y se retorna.
			return new Token(getClass().getSimpleName(), lexema);
		}
	}

}
