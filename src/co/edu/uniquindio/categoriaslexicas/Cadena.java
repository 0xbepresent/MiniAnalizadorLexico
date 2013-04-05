package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.Token;

public class Cadena extends CategoriaLexicaBase {

	@Override
	public Token aceptar() {

		// Lexema recuperado.
		String lexema = "";

		if (analizadorLexico.getCaracterActual() != '\"') {
			return null;
		} else {
			// Estado 1.
			lexema += analizadorLexico.getCaracterActual();

			do {
				// Se consume los caracteres alfabéticos y numéricos.
				analizadorLexico.irSiguienteCaracter();

				// Se puede haber llegado al caracter que rompe el ciclo.
				if (	Character.isAlphabetic(analizadorLexico.getCaracterActual())
					|| 	Character.isDigit(analizadorLexico.getCaracterActual())
					||	analizadorLexico.isCaracterBlanco(analizadorLexico.getCaracterActual())) {
					lexema += analizadorLexico.getCaracterActual();
				}
			} while (	Character.isAlphabetic(analizadorLexico.getCaracterActual())
					|| 	Character.isDigit(analizadorLexico.getCaracterActual())
					||	analizadorLexico.isCaracterBlanco(analizadorLexico.getCaracterActual()));

			if(analizadorLexico.getCaracterActual() == '\"') {
				lexema += analizadorLexico.getCaracterActual();
				analizadorLexico.irSiguienteCaracter();
				// Se acepta el token y se retorna.
				return new Token(getClass(), lexema);
			}
			else {
				return null;
			}
		}

	}

}
