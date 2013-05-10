package co.edu.uniquindio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.edu.uniquindio.categoriaslexicas.AgrupadorCodigoApertura;
import co.edu.uniquindio.categoriaslexicas.AgrupadorCodigoCierre;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosApertura;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosCierre;
import co.edu.uniquindio.categoriaslexicas.Booleano;
import co.edu.uniquindio.categoriaslexicas.Cadena;
import co.edu.uniquindio.categoriaslexicas.CategoriaLexica;
import co.edu.uniquindio.categoriaslexicas.Else;
import co.edu.uniquindio.categoriaslexicas.Eol;
import co.edu.uniquindio.categoriaslexicas.Flotante;
import co.edu.uniquindio.categoriaslexicas.For;
import co.edu.uniquindio.categoriaslexicas.Identificador;
import co.edu.uniquindio.categoriaslexicas.If;
import co.edu.uniquindio.categoriaslexicas.Int;
import co.edu.uniquindio.categoriaslexicas.InvocadorMetodo;
import co.edu.uniquindio.categoriaslexicas.OperadorAritmetico;
import co.edu.uniquindio.categoriaslexicas.OperadorAsignacion;
import co.edu.uniquindio.categoriaslexicas.OperadorBooleano;
import co.edu.uniquindio.categoriaslexicas.OperadorConcatenacion;
import co.edu.uniquindio.categoriaslexicas.OperadorRelacional;
import co.edu.uniquindio.categoriaslexicas.Separador;
import co.edu.uniquindio.categoriaslexicas.SeparadorFor;
import co.edu.uniquindio.categoriaslexicas.TipoDato;

/**
 * Analizador Lexico
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
		
		// Orden en el que se hace el análisis
		Class<?>[] categoriasClases = {
			Int.class,
			co.edu.uniquindio.categoriaslexicas.Flotante.class,
			Separador.class,
			AgrupadorCodigoApertura.class,
			AgrupadorCodigoCierre.class,
			AgrupadorParametrosApertura.class,
			AgrupadorParametrosCierre.class,
			OperadorAsignacion.class,
			SeparadorFor.class,
			Booleano.class,
			TipoDato.class,
			OperadorRelacional.class,
			OperadorAritmetico.class,
			OperadorBooleano.class,
			OperadorConcatenacion.class,
			If.class,
			Else.class,
			For.class,
			Identificador.class,
			Cadena.class,
			InvocadorMetodo.class,
		};
		
		for (int i = 0; i < categoriasClases.length; i++) {
			CategoriaLexica cl = null;
			try {
				cl = (CategoriaLexica)
					categoriasClases[i].newInstance();
				cl.setAnalizador(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			categorias.add(cl);
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
		Class<? extends CategoriaLexica> tipoToken = token.getTipoToken();
		if(		tipoToken.equals(Flotante.class)
			|| 	tipoToken.equals(Int.class)
			|| 	tipoToken.equals(Identificador.class)
			|| 	tipoToken.equals(Cadena.class)) {
			String valor = tablaSimbolos.agregarSimbolo(tipoToken,
				new Simbolo(token.getLexema()));
			token.setValor(valor);
		}
		
	}
	
	private void avanzarLinea() {
		indiceCaracterActual = 0;
		regresoBacktracking = 0;
		lineaActual++;
		// El primer avance no se cuenta como fin de linea.
		if(lineaActual != 1) tokens.add(new Token(Eol.class, "\\n", "EOL"));
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