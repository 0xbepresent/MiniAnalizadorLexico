package co.edu.uniquindio;

import java.util.List;

import co.edu.uniquindio.categoriassintacticas.CategoriaSintactica;

public interface Nodo {

	public Nodo getPadre();
	
	public void setPadre(Nodo padre);
	
	public List<Nodo> getHijos();
	
	public void setHijos(List<Nodo> hijos);
	
	public void recorrerArbolPreOrden(List<RunnableNodo> runnables);
	
	public Nodo buscarAncestro(Class<? extends CategoriaSintactica> categoriaSintactica);
	
	public static interface RunnableNodo {
		void run(Nodo nodo);
	}
	
	public String traducir();

}
