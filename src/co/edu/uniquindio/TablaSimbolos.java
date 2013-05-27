package co.edu.uniquindio;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.categoriassintacticas.DeclaracionMetodo;

public class TablaSimbolos {
	
	private final List<Simbolo> tablaSimbolos = new ArrayList<Simbolo>();
	
	public TablaSimbolos() {
		// Se inicializan las palabras reservadas y las tiras específicas.
		// TODO Completar las tiras específicas. Encontrar una forma de
		// representar está información sin duplicarla con las categorías
		// léxicas.
		tablaSimbolos.add(new Simbolo(","));
	}
	
	public void agregarSimbolo(Simbolo simbolo) {
		tablaSimbolos.add(simbolo);
	}
	
	public int size() {
		return tablaSimbolos.size();
	}
	
	@Override
	public String toString() {
		return tablaSimbolos.toString();
	}

	public List<Simbolo> getTablaSimbolos() {
		return tablaSimbolos;
	}

	/**
	 * Se valida que sólo haya una declaración de método por archivo de código fuente.
	 * Es decir, no se permite la sobrecarga de métodos.
	 */
	public void instalarDeclaracionMetodo(Nodo declaracionMetodo) throws IllegalArgumentException {
		// TipoDato Identificador ( <ListaParametros> )...
		List<Nodo> hijos = declaracionMetodo.getHijos();
		Token tokenIdentificadorMetodo = (Token)hijos.get(1);
		String identificador = tokenIdentificadorMetodo.getLexema();
		
		if(!existeMetodo(identificador)) {
			String tipoRetorno = ((Token)hijos.get(0)).getLexema();
			SimboloMetodo simboloMetodo = new SimboloMetodo(identificador, tipoRetorno);
			agregarSimbolo(simboloMetodo);
			// Se agrega el símbolo al token identificador del método.
			tokenIdentificadorMetodo.setSimbolo(simboloMetodo);
		}
		else {
			throw new IllegalArgumentException(
				String.format("Ya existe una declaración para el método %s. Token: %s."
					, identificador
					, tokenIdentificadorMetodo));
		}
	}

	private boolean existeMetodo(String identificador) {
		for (Simbolo simbolo : tablaSimbolos) {
			if (simbolo instanceof SimboloMetodo) {
				SimboloMetodo simboloMetodo = (SimboloMetodo) simbolo;
				if(simboloMetodo.getLexema().equals(identificador)) {
					return true;
				}
			}
		}
		return false;
	}

	public void instalarDeclaracionVariable(Nodo declaracionVariable) throws IllegalArgumentException {
		// TipoDato <Asignacion> | TipoDato Identificador
		List<Nodo> hijos = declaracionVariable.getHijos();
		Nodo segundoHijo = hijos.get(1);
		Token tokenIdentificador = null;
		
		if (segundoHijo instanceof Derivacion) {
			// Identificador = <Expresion>
			Derivacion asignacion = (Derivacion) segundoHijo;
			tokenIdentificador = (Token) asignacion.getHijos().get(0);
		}
		else {
			tokenIdentificador = ((Token) segundoHijo);
		}
		
		String identificador = tokenIdentificador.getLexema();
		
		SimboloMetodo simboloMetodo = null;
		Nodo declaracionMetodo = declaracionVariable.buscarAncestro(DeclaracionMetodo.class);
		if(declaracionMetodo != null) {
			Token tokenIdentificadorMetodo = (Token) declaracionMetodo.getHijos().get(1);
			simboloMetodo = (SimboloMetodo) tokenIdentificadorMetodo.getSimbolo();
		}
		
		if(!existeVariable(identificador, simboloMetodo)) {
			String tipoVariable = ((Token) hijos.get(0)).getLexema();
			SimboloVariable simboloVariable = new SimboloVariable(identificador, tipoVariable, simboloMetodo);
			agregarSimbolo(simboloVariable);
			
			// Se agrega el símbolo al token.
			tokenIdentificador.setSimbolo(simboloVariable);
		}
		else {
			throw new IllegalArgumentException(
				String.format("Ya existe una declaración para la variable %s en el contexto %s. Token: %s."
					, identificador
					, simboloMetodo != null ? "Método[" + simboloMetodo.getLexema() + "]" : "Global"
					, tokenIdentificador));
		}
	}

	private boolean existeVariable(String identificador, SimboloMetodo simboloMetodo) {
		// Si está declarada en el alcance global
		if(simboloMetodo == null) {
			for (Simbolo simbolo : tablaSimbolos) {
				if (// Si es un símbolo de variable
				simbolo instanceof SimboloVariable
				// Y si es de contexto global
				&& ((SimboloVariable) simbolo).getSimboloMetodo() == null) {
					SimboloVariable simboloVariable = (SimboloVariable) simbolo;
					if(simboloVariable.getLexema().equals(identificador)) {
						return true;
					}
				}
			}
		}
		// Si está declarada en el alcance de un método.
		else {
			for (Simbolo simbolo : tablaSimbolos) {
				if (// Si es un símbolo de variable
				simbolo instanceof SimboloVariable
				// Y si está en el contexto de un método
				&& ((SimboloVariable) simbolo).getSimboloMetodo() != null) {
					SimboloVariable simboloVariable = (SimboloVariable) simbolo;
					
					if(
					// Se verifica el identificador
					simboloVariable.getLexema().equals(identificador)
					// y el método al que pertenece.
					&& simboloVariable.getSimboloMetodo().equals(simboloMetodo)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}

}
