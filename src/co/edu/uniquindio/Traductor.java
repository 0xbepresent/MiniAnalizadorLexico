package co.edu.uniquindio;

public class Traductor {

	private Nodo raiz;
	private TablaSimbolos tablaSimbolos;

	public Traductor(Nodo raiz, TablaSimbolos tablaSimbolos) {
		this.raiz = raiz;
		this.tablaSimbolos = tablaSimbolos;
	}
	
	public String traducir() {
		return raiz.traducir();
	}
	
}
