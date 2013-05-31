package co.edu.uniquindio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.edu.uniquindio.categoriassintacticas.Declaracion;
import co.edu.uniquindio.categoriassintacticas.DeclaracionMetodo;

public class AnalizadorSemantico {

	private Nodo raiz;
	private TablaSimbolos tablaSimbolos;
	private List<String> errores = new ArrayList<String>();

	public AnalizadorSemantico(Nodo raiz, TablaSimbolos tablaSimbolos) {
		this.raiz = raiz;
		this.tablaSimbolos = tablaSimbolos;
	}
	
	private List<Nodo.RunnableNodo> runnables = Arrays.asList(new Nodo.RunnableNodo[]{
		// Reconocimiento y validación de las declaraciones de método.
		new Nodo.RunnableNodo() {
			public void run(Nodo nodo) {
				if( // Si es una derivación
				nodo instanceof Derivacion
				// Y si es una declaración de método.
				&& ((Derivacion) nodo).getCategoriaSintactica().equals(DeclaracionMetodo.class)) {
					try {
						tablaSimbolos.instalarDeclaracionMetodo(nodo);
					} catch (IllegalArgumentException e) {
						reportarError(e.getMessage());
					}
				}
			}
		},
		
		// Reconocimiento y validación de las declaraciones de variables.
		new Nodo.RunnableNodo() {
			public void run(Nodo nodo) {
				if( // Si es una derivación
				nodo instanceof Derivacion
				// Y si es una declaración.
				&& ((Derivacion) nodo).getCategoriaSintactica().equals(Declaracion.class)) {
					try {
						tablaSimbolos.instalarDeclaracionVariable(nodo);
					} catch (IllegalArgumentException e) {
						reportarError(e.getMessage());
					}
				}
			}
		}
	});
	
	public void analizar() {
		System.out.println("Analizando reglas semánticas...........");
		System.out.println("\n");
		raiz.recorrerArbolPreOrden(runnables);
	}
	
	private void reportarError(String error) {
		errores.add(error);
	}

	public List<String> getErrores() {
		return errores;
	}
	
}
