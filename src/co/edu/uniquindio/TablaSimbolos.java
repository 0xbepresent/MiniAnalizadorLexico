package co.edu.uniquindio;

import java.util.HashMap;
import java.util.Map;

import co.edu.uniquindio.categoriaslexicas.CategoriaLexica;

public class TablaSimbolos {
	
	private Map<String, Simbolo> tablaSimbolos = new HashMap<String, Simbolo>();
	
	public TablaSimbolos() {
		// Se inicializan las palabras reservadas y las tiras específicas.
		tablaSimbolos.put("separador", new Simbolo(","));
	}
	
	public String agregarSimbolo(Class<? extends CategoriaLexica> tipoToken, Simbolo simbolo) {
		String valor = tipoToken.getSimpleName() + simbolo.hashCode();
		tablaSimbolos.put(valor, simbolo);
		return valor;
	}
	
	public int size() {
		return tablaSimbolos.size();
	}
	
	@Override
	public String toString() {
		return tablaSimbolos.toString();
	}

}
