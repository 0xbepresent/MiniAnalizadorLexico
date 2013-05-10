package co.edu.uniquindio.categoriassintacticas;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.AnalizadorSintactico;
import co.edu.uniquindio.Derivacion;
import co.edu.uniquindio.Nodo;
import co.edu.uniquindio.Token;

public abstract class CategoriaSintacticaBase implements CategoriaSintactica {
	
	protected Class<?>[][] producciones;
	
	// Por defecto las categorías se derivan buscando la derivación que haya
	// consumido más tokens
	protected ModoAceptacion modo = ModoAceptacion.PRIORIDAD_ORDEN_PRODUCCIONES;

	protected enum ModoAceptacion {
		/** Buscando la derivación que haya consumido más tokens. Ante la duda usa el orden de las producciones como prioridad. */
		MAYOR_CONSUMO_TOKENS,
		
		/** Retorna la derivación siguiendo la prioridad establecida por el orden. */
		PRIORIDAD_ORDEN_PRODUCCIONES
	}

	@Override
	public Derivacion construir(AnalizadorSintactico contexto) {
		try {
			// Almacena las derivaciones para posteriormente quedarse con la que
			// consumió más tokens en el caso de que se use dicha estrategia.
			List<Derivacion> derivaciones = new ArrayList<Derivacion>();
			
			// Se establece el punto de backtracking
			int posicionBacktracking = contexto.getIndiceTokenActual();
			
			for (Class<?>[] produccion : producciones) {
				
				// Si la producción es épsilon. 
				if(produccion.length == 1 && produccion[0] == null) {
					Derivacion derivacionEpsilon = new Derivacion(this.getClass());
					if      (modo == ModoAceptacion.MAYOR_CONSUMO_TOKENS) {
						derivaciones.add(derivacionEpsilon);
						contexto.setIndiceTokenActual(posicionBacktracking);
						continue;
					}
					else if (modo == ModoAceptacion.PRIORIDAD_ORDEN_PRODUCCIONES) {
						return derivacionEpsilon;
					}
				}
				
				// Se analiza una produccion
				List<Nodo> nodos = new ArrayList<Nodo>();
				for (Class<?> itemProduccion : produccion) {
					
					Nodo nodo = null;
					// Si es una categoría sintáctica
					if(CategoriaSintactica.class.isAssignableFrom(itemProduccion)) {
						CategoriaSintactica categoria =  (CategoriaSintactica) itemProduccion.newInstance();
						
						nodo = categoria.construir(contexto);
					}
					else {
						Token token = contexto.getTokenActual();
						if(token != null && token.getTipoToken().equals(itemProduccion)) {
							nodo = token;
						}
						
						contexto.irSiguienteToken();
					}
					
					if(nodo != null) {
						nodos.add(nodo);
					}
					else {
						// Se rechaza la derivación.
						break;
					}
				}
				
				// Si se analizaron todos los items de una producción correctamente
				// se acepta la derivación.
				if(nodos.size() == produccion.length) {
					Derivacion derivacion = new Derivacion(getClass());
					derivacion.setHijos(nodos);
					for (Nodo nodo : nodos) {
						nodo.setPadre(derivacion);
					}
					
					if      (modo == ModoAceptacion.MAYOR_CONSUMO_TOKENS) {
						derivaciones.add(derivacion);
						contexto.setIndiceTokenActual(posicionBacktracking);
					}
					else if (modo == ModoAceptacion.PRIORIDAD_ORDEN_PRODUCCIONES) {
						return derivacion;
					}
					
				}
				// Sino, se regresa al punto de backtracking y se analiza
				// la siguiente producción.
				else {
					contexto.setIndiceTokenActual(posicionBacktracking);
				}
			}
			
			if(modo == ModoAceptacion.MAYOR_CONSUMO_TOKENS) {
				if(!derivaciones.isEmpty()) {
					Derivacion masConsume = derivaciones.get(0);
					for (Derivacion derivacion : derivaciones) {
						if(derivacion.getTotalTokens() > masConsume.getTotalTokens()) {
							masConsume = derivacion;
						}
					}

					int indiceTokenActual = posicionBacktracking + masConsume.getTotalTokens();
					contexto.setIndiceTokenActual(indiceTokenActual);
					return masConsume;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Si no se encontró ninguna derivación se retorna null.
		return null;
	}
	
}
