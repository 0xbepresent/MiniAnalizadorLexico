package co.edu.uniquindio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Se pretende analizar una cadena para recuperar los siguientes tokens:
 * 
 * + Literal de entero:        d+
 * + Literal de decimal:       d+.d+
 * + Separador de parámetros:  ,
 */
public class AnalizadorLexico {
	
	private List<Token> tokens = new ArrayList<>();
	private final String cadenaAnalizar;
	private int indiceCaracterActual;
	private int regresoBacktracking;
	
	/**
	 *  Se escoge una constante de forma arbitraria para representar
	 *  el caracter final del archivo.
	 */
	private static final char EOF = Character.MAX_HIGH_SURROGATE;
	
	private static final char[] CARACTERES_BLANCO = {' ', '\t', '\r', '\n'};
	static {
		// Requisito de la busqueda binaria: Arrays.binarySearch.
		Arrays.sort(CARACTERES_BLANCO);
	}
	
	public AnalizadorLexico(String cadenaAnalizar) {
		this.cadenaAnalizar = cadenaAnalizar;
	}
	
	public List<Token> getTokens() {
		return tokens;
	}
	
	public List<Token> analizar() {
		
		while (indiceCaracterActual < cadenaAnalizar.length()) {
			Token tokenActual = aceptarEntero();
			if(tokenActual == null) {
				tokenActual = aceptarDecimal();
			}
			if(tokenActual == null) {
				tokenActual = aceptarSeparador();
			}
			
			if(tokenActual == null) {
				consumirEspacioBlanco();
			}
			else {
				tokens.add(tokenActual);
			}
		}
		
		return getTokens();
	}
	
	private void consumirEspacioBlanco() {
		// Si no es un caracter en blanco se rechaza.
		if(!isCaracterBlanco(getCaracterActual())) {
			return;
		}
		else {
			// Se consumen los espacion en blanco.
			do {
				irSiguienteCaracter();
			} while (isCaracterBlanco(getCaracterActual()));
		}
	}
	
	private boolean isCaracterBlanco(char caracterActual) {
		return Arrays.binarySearch(CARACTERES_BLANCO, caracterActual) >= 0;
	}

	private Token aceptarEntero() {

		// Como puede haber backtracking se almanenca en donde se debe regresar.
		establecerRegresoBacktracking();
		
		// Lexema recuperado.
		String valor = "";
		
		if(!Character.isDigit(getCaracterActual())) {
			return null;
		}
		else {
			// Estado 1.
			valor += getCaracterActual();
			
			do {
				// Se consume los digitos.
				irSiguienteCaracter();
				
				// Se puede haber llegado al caracter que rompe el ciclo.
				if(Character.isDigit(getCaracterActual())) {
					valor += getCaracterActual();
				}
			}while(Character.isDigit(getCaracterActual()));
			
			if(getCaracterActual() == '.') {
				// Se hace el backtracking.
				regresarBacktracking();
				return null;
			}
			else {
				// Se acepta el token y se retorna.
				return new Token(Tipo.ENTERO, valor);
			}
		}
	}
	
	private Token aceptarDecimal() {
		
		// Lexema recuperado.
		String valor = "";
		
		if(!Character.isDigit(getCaracterActual())) {
			return null;
		}
		else {
			// Estado 1.
			valor += getCaracterActual();
			
			do {
				// Se consume los digitos.
				irSiguienteCaracter();

				// Se puede haber llegado al caracter que rompe el ciclo.
				if(Character.isDigit(getCaracterActual())) {
					valor += getCaracterActual();
				}
			}while(Character.isDigit(getCaracterActual()));
			
			if(getCaracterActual() != '.') {
				// Se rechaza.
				return null;
			}
			else {
				valor += getCaracterActual();
				irSiguienteCaracter();
				// Estado 2.
				if(!Character.isDigit(getCaracterActual())) {
					// Se rechaza.
					return null;
				}
				else {
					// Estado 3.
					valor += getCaracterActual();
					
					do {
						// Se consume los digitos.
						irSiguienteCaracter();
						getCaracterActual();
						
						// Se puede haber llegado al caracter que rompe el ciclo.
						if(Character.isDigit(getCaracterActual())) {
							valor += getCaracterActual();
						}
					}while(Character.isDigit(getCaracterActual()));
					
					// Se acepta el token y se retorna.
					return new Token(Tipo.DECIMAL, valor);
				}
			}
		}
		
	}

	private Token aceptarSeparador() {
		if(getCaracterActual() != ',') {
			// Se rechaza.
			return null;
		}
		else {
			// Se acepta el token y se retorna.
			irSiguienteCaracter();
			return new Token(Tipo.SEPARADOR, ",");
		}
	}
	
	private void irSiguienteCaracter() {
		indiceCaracterActual++;
	}
	
	private void establecerRegresoBacktracking() {
		regresoBacktracking = indiceCaracterActual;
	}
	
	private void regresarBacktracking() {
		indiceCaracterActual = regresoBacktracking;
	}
	
	private char getCaracterActual() {
		return indiceCaracterActual >= cadenaAnalizar.length() ?
				EOF :
				cadenaAnalizar.charAt(indiceCaracterActual);
	}

}