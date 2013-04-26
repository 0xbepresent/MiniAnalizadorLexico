package co.edu.uniquindio.categoriassintacticas;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.AnalizadorSintactico;
import co.edu.uniquindio.Derivacion;
import co.edu.uniquindio.Nodo;
import co.edu.uniquindio.Token;

public abstract class CategoriaSintacticaBase implements CategoriaSintactica {
	
	protected Class<?>[][] producciones;

	@Override
	public Derivacion construir(AnalizadorSintactico contexto) {
		try {
			for (Class<?>[] produccion : producciones) {
				
				// Se establece el punto de backtracking
				int posicionBacktracking = contexto.getIndiceTokenActual();
				
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
					Derivacion derivacion = new Derivacion(this.getClass());
					derivacion.setHijos(nodos);
					return derivacion;
				}
				// Sino, se regresa al punto de backtracking y se analiza
				// la siguiente producción.
				else {
					contexto.setIndiceTokenActual(posicionBacktracking);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Si no se encontró ninguna derivación se retorna null.
		return null;
	}
	
}
