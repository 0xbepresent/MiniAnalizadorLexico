package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.TipoToken;
import co.edu.uniquindio.Token;

public class Int extends CategoriaLexicaBase {

	@Override
	public Token aceptar() {

		// Como puede haber backtracking se almanenca en donde se debe regresar.
		analizadorLexico.establecerRegresoBacktracking();
		
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
			
			if(analizadorLexico.getCaracterActual() == '.') {
				// Se hace el backtracking.
				analizadorLexico.regresarBacktracking();
				return null;
			}
			else {
				// Se acepta el token y se retorna.
				return new Token(TipoToken.Int, lexema);
			}
		}
	}

}
