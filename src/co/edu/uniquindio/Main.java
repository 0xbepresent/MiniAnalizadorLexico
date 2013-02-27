package co.edu.uniquindio;

import java.util.List;

public class Main {
	
	/**
	 * Se pretende analizar una cadena para recuperar los siguientes tokens:
	 * 
	 * + Literal de entero:        d+
	 * + Literal de decimal:       d+.d+
	 * + Separador de parámetros:  ,
	 */
	public static void main(String[] args) {
		/*
		 * Resultado esperado:
		 * Token entero:    1 - 2 - 3
		 * Token decimal:   12.34
		 * Token entero:    1 - 2 - 3
		 * Token decimal:   43.21
		 * Token separador: ,
		 * Token entero:    1
		 * Token separador: ,
		 * Token entero:    2
		 * Token separador: ,
		 * Token entero:    3
		 */
		final String CADENA_ANALIZAR = 
				"1 2 3 12.34 1 2 3 43.21 , 1,2,3";

		AnalizadorLexico analizadorLexico = new AnalizadorLexico(CADENA_ANALIZAR);
		List<Token> tokens = analizadorLexico.analizar();

		System.out.println(tokens);
	}

}
