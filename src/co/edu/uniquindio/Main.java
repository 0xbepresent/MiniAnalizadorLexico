package co.edu.uniquindio;

import java.util.Arrays;
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
		
		final String CODIGO_ANALIZAR[] = new LectorFuente().leer(); 

		System.out.println(Arrays.toString(CODIGO_ANALIZAR));
		System.out.println();
		
		AnalizadorLexico analizadorLexico = new AnalizadorLexico(CODIGO_ANALIZAR);
		List<Token> tokens = analizadorLexico.analizar();

		TablaSimbolos tablaSimbolos = analizadorLexico.getTablaSimbolos();
		List<Error> errores = analizadorLexico.getErrores();
		
		System.out.println(String.format("Tokens --------------> Cantidad: %s, %s", tokens.size(), tokens));
		System.out.println(String.format("Tabla de símbolos ---> Cantidad: %s, %s", tablaSimbolos.size(), tablaSimbolos));
		System.out.println(String.format("Errorres ------------> Cantidad: %s, %s", errores.size(), errores));
	}

}