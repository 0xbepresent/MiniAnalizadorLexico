package co.edu.uniquindio;

import co.edu.uniquindio.categoriassintacticas.CategoriaSintactica;

public class Derivacion extends NodoImplemetado {
	
	private final Class<? extends CategoriaSintactica> categoriaSintactica;

	public Derivacion(Class<? extends CategoriaSintactica> categoria) {
		this.categoriaSintactica = categoria;
	}

	public Class<? extends CategoriaSintactica> getCategoriaSintactica() {
		return categoriaSintactica;
	}
	
}
