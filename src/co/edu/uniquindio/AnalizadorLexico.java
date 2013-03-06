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
	
	//------ Atributos por línea.
	private String cadenaAnalizar;
	private int indiceCaracterActual;
	private int regresoBacktracking;

	//------ Atributos para todo el análisis.
	private final String[] codigoAnalizar;
	private int lineaActual;
	private List<Token> tokens = new ArrayList<>();
	
	//------ Tabla de símbolos y lista de errores.
	private TablaSimbolos tablaSimbolos = new TablaSimbolos();
	private List<Error> errores = new ArrayList<Error>();
	
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
	
	public AnalizadorLexico(String[] codigoAnalizar) {
		this.codigoAnalizar = codigoAnalizar;
	}
	
	public List<Token> getTokens() {
		return tokens;
	}
	
	public List<Token> analizar() {
		
		if(codigoAnalizar != null)
		for (int i = 0; i < codigoAnalizar.length; i++) {

			avanzarLinea();
			
			while (indiceCaracterActual < cadenaAnalizar.length()) {
				
				if(isCaracterBlanco(getCaracterActual())) {
					consumirEspacioBlanco();
					if(getCaracterActual() == EOF) {
						break;
					}
				}
				
				Token tokenActual = aceptarEntero();
				if(tokenActual == null) {
					tokenActual = aceptarDecimal();
				}
				if(tokenActual == null) {
					tokenActual = aceptarSeparador();
				}
				
				// Si no se encontró ninguna categoría léxica se reporta el error.
				if(tokenActual == null) {
					errores.add(new Error(lineaActual, indiceCaracterActual,
						"No se ha encontrado una categoría léxica para el token"));
					
					if(!isCaracterBlanco(getCaracterActual())) {
						irInicioSiguienteToken();
					}
				}
				// Sino se agrega el token a la lista.
				else {
					tokens.add(tokenActual);
					instalarTokenTablaSimbolos(tokenActual);
				}
			}
		}
		
		return getTokens();
	}

	private void instalarTokenTablaSimbolos(Token token) {
		
		if(		token.getTipo() == Tipo.DECIMAL
			|| 	token.getTipo() == Tipo.ENTERO) {
			String valor = tablaSimbolos.agregarSimbolo(token.getTipo(),
				new Simbolo(token.getLexema()));
			token.setValor(valor);
		}
		
	}
	
	private void avanzarLinea() {
		indiceCaracterActual = 0;
		regresoBacktracking = 0;
		lineaActual++;
		cadenaAnalizar = codigoAnalizar[lineaActual - 1];
	}

	/**
	 * Navega al inicio del siguiente token o a EOF.
	 */
	private void irInicioSiguienteToken() {
		boolean encontroEspacio = false;
		do {
			irSiguienteCaracter();
			if(isCaracterBlanco(getCaracterActual())) {
				encontroEspacio = true;
			}
		}while(isCaracterBlanco(getCaracterActual()) || !encontroEspacio && getCaracterActual() != EOF);
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
		String lexema = "";
		
		if(!Character.isDigit(getCaracterActual())) {
			return null;
		}
		else {
			// Estado 1.
			lexema += getCaracterActual();
			
			do {
				// Se consume los digitos.
				irSiguienteCaracter();
				
				// Se puede haber llegado al caracter que rompe el ciclo.
				if(Character.isDigit(getCaracterActual())) {
					lexema += getCaracterActual();
				}
			}while(Character.isDigit(getCaracterActual()));
			
			if(getCaracterActual() == '.') {
				// Se hace el backtracking.
				regresarBacktracking();
				return null;
			}
			else {
				// Se acepta el token y se retorna.
				return new Token(Tipo.ENTERO, lexema);
			}
		}
	}
	
	private Token aceptarDecimal() {
		
		// Lexema recuperado.
		String lexema = "";
		
		if(!Character.isDigit(getCaracterActual())) {
			return null;
		}
		else {
			// Estado 1.
			lexema += getCaracterActual();
			
			do {
				// Se consume los digitos.
				irSiguienteCaracter();

				// Se puede haber llegado al caracter que rompe el ciclo.
				if(Character.isDigit(getCaracterActual())) {
					lexema += getCaracterActual();
				}
			}while(Character.isDigit(getCaracterActual()));
			
			if(getCaracterActual() != '.') {
				// Se rechaza.
				return null;
			}
			else {
				lexema += getCaracterActual();
				irSiguienteCaracter();
				// Estado 2.
				if(!Character.isDigit(getCaracterActual())) {
					// Se rechaza.
					return null;
				}
				else {
					// Estado 3.
					lexema += getCaracterActual();
					
					do {
						// Se consume los digitos.
						irSiguienteCaracter();
						getCaracterActual();
						
						// Se puede haber llegado al caracter que rompe el ciclo.
						if(Character.isDigit(getCaracterActual())) {
							lexema += getCaracterActual();
						}
					}while(Character.isDigit(getCaracterActual()));
					
					// Se acepta el token y se retorna.
					return new Token(Tipo.DECIMAL, lexema);
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
			Token token = new Token(Tipo.SEPARADOR, ",");
			token.setValor("separador");
			return token;
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

	public TablaSimbolos getTablaSimbolos() {
		return tablaSimbolos;
	}

	public List<Error> getErrores() {
		return errores;
	}

}