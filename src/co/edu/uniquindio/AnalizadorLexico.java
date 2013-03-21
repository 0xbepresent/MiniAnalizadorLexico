package co.edu.uniquindio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.edu.uniquindio.categoriaslexicas.CategoriaLexica;

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
	private List<Token> tokens = new ArrayList<Token>();
	private List<CategoriaLexica> categorias = new ArrayList<CategoriaLexica>();
	
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
		
		TipoToken[] tiposToken = TipoToken.values();
		
		for (int i = 0; i < tiposToken.length; i++) {
			if(tiposToken[i].isTienePredicado()) {
				CategoriaLexica cl = null;
				try {
					cl = (CategoriaLexica)
						Class.forName("co.edu.uniquindio.categoriaslexicas." +
						tiposToken[i].toString()).newInstance();
					cl.setAnalizador(this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				categorias.add(cl);
			}
		}
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
				

				// Se trata de encontrar el token en alguna de las categorías.
				// Si no lo encuentra tokenActual sigue valiendo null.
				Token tokenActual = null;
				for (CategoriaLexica cl : categorias) {
					tokenActual = cl.aceptar();
					if(tokenActual != null) {
						break;
					}
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
		// TODO verificar que otros tokens se deben instalar.
		if(		token.getTipo() == TipoToken.Double
			|| 	token.getTipo() == TipoToken.Int
			|| 	token.getTipo() == TipoToken.Identificador
			|| 	token.getTipo() == TipoToken.Cadena) {
			String valor = tablaSimbolos.agregarSimbolo(token.getTipo(),
				new Simbolo(token.getLexema()));
			token.setValor(valor);
		}
		
	}
	
	private void avanzarLinea() {
		indiceCaracterActual = 0;
		regresoBacktracking = 0;
		lineaActual++;
		// El primer avance no se cuenta como fin de linea.
		if(lineaActual != 1) tokens.add(new Token(TipoToken.EOL, "\\n", TipoToken.EOL.toString()));
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
	
	public boolean isCaracterBlanco(char caracterActual) {
		return Arrays.binarySearch(CARACTERES_BLANCO, caracterActual) >= 0;
	}

	public void irSiguienteCaracter() {
		indiceCaracterActual++;
	}
	
	public void establecerRegresoBacktracking() {
		regresoBacktracking = indiceCaracterActual;
	}
	
	public void regresarBacktracking() {
		indiceCaracterActual = regresoBacktracking;
	}
	
	public char getCaracterActual() {
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