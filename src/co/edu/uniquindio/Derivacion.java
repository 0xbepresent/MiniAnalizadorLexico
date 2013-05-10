package co.edu.uniquindio;

import java.util.List;

import co.edu.uniquindio.categoriassintacticas.CategoriaSintactica;

public class Derivacion extends NodoImplemetado {
	
	private final Class<? extends CategoriaSintactica> categoriaSintactica;
	
	private int totalTokens = -1;

	public Derivacion(Class<? extends CategoriaSintactica> categoria) {
		this.categoriaSintactica = categoria;
	}

	public Class<? extends CategoriaSintactica> getCategoriaSintactica() {
		return categoriaSintactica;
	}
	
	public int getTotalTokens() {
		// Para poner en cache el valor.
		if(this.totalTokens == -1) {
			int totalTokens = 0;
			List<Nodo> hijos = getHijos();
			for (Nodo nodo : hijos) {
				if (nodo instanceof Token) {
					totalTokens++;
				}
				else if (nodo instanceof Derivacion) {
					Derivacion derivacion = (Derivacion) nodo;
					totalTokens += derivacion.getTotalTokens();
				}
			}
			this.totalTokens = totalTokens;
			return totalTokens;
		}
		else {
			return totalTokens;
		}
	}
	
}
