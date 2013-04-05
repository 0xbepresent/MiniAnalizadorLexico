package co.edu.uniquindio;

import java.util.Arrays;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		
		final String CODIGO_ANALIZAR[] = new LectorFuente().leer(null); 

		System.out.println(Arrays.toString(CODIGO_ANALIZAR));
		System.out.println();
		
		AnalizadorLexico analizadorLexico = new AnalizadorLexico(CODIGO_ANALIZAR);
		List<Token> tokens = analizadorLexico.analizar();

		TablaSimbolos tablaSimbolos = analizadorLexico.getTablaSimbolos();
		List<Error> errores = analizadorLexico.getErrores();

		System.out.println("Tokens");
		for (Token token : tokens) {
			System.out.println(token);
		}
		
		System.out.println();
		System.out.println(String.format("Tabla de símbolos ---> Cantidad: %s, %s", tablaSimbolos.size(), tablaSimbolos));
		
		System.out.println();
		System.out.println("Errores");
		for (Error error : errores) {
			System.out.println(error);
		}
		
		System.out.println();
		AnalizadorSintactico analizadorSintactico = new AnalizadorSintactico(tokens);
		Nodo raiz = analizadorSintactico.analizar();
		
		System.out.println(raiz);
	}

}