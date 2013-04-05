package co.edu.uniquindio;

import java.util.List;

import co.edu.uniquindio.categoriassintacticas.CategoriaSintactica;
import co.edu.uniquindio.categoriassintacticas.ExpresionCadena;

public class AnalizadorSintactico {

	private final List<Token> tokens;
	
	private final Class<? extends CategoriaSintactica> claseRaiz = ExpresionCadena.class;
	
	private int indiceTokenActual = 0;

	private int regresoBacktracking;

	private List<Error> errores;

	public AnalizadorSintactico(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	public Nodo analizar(){
		try {
			CategoriaSintactica raiz = claseRaiz.newInstance();
			
			return raiz.construir(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Token> getTokens() {
		return tokens;
	}
	
	public void irSiguienteToken() {
		indiceTokenActual++;
	}
	
	public void establecerRegresoBacktracking() {
		regresoBacktracking = indiceTokenActual;
	}
	
	public void regresarBacktracking() {
		indiceTokenActual = regresoBacktracking;
	}
	
	public Token getTokenActual() {
		return indiceTokenActual >= tokens.size() ?
				null :
				tokens.get(indiceTokenActual);
	}

	public List<Error> getErrores() {
		return errores;
	}
	
}
