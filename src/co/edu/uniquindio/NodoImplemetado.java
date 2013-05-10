package co.edu.uniquindio;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import co.edu.uniquindio.categoriassintacticas.CategoriaSintactica;

public class NodoImplemetado implements Nodo {

	protected Nodo padre;
	
	protected List<Nodo> hijos = new ArrayList<Nodo>();
	
	@Override
	public void setPadre(Nodo padre) {
		this.padre = padre;
	}
	
	@Override
	public Nodo getPadre() {
		return padre;
	}
	
	@Override
	public void setHijos(List<Nodo> hijos) {
		this.hijos = hijos;
	}
	
	@Override
	public List<Nodo> getHijos() {
		return hijos;
	}
	
	@Override
	public String toString() {
		// Si es una categoría sintáctica.
		if(Derivacion.class.isAssignableFrom(getClass())) {
			Derivacion derivacion = (Derivacion) this;
			Class<? extends CategoriaSintactica> categoriaSintactica = derivacion.getCategoriaSintactica();
			
			String toStringHijos = "";
			String totalTokens   = "";
			for (Nodo nodo : derivacion.getHijos()) {
				
				totalTokens = "" + derivacion.getTotalTokens();
				
				String nodoToString = nodo.toString();
				StringTokenizer tokenizer = new StringTokenizer(nodoToString, "\n");
				if(tokenizer.countTokens() > 1) {
					while (tokenizer.hasMoreElements()) {
						String linea = tokenizer.nextToken();
						toStringHijos += String.format("  %s\n", linea);
					}
				}
				else {
					toStringHijos += String.format("  %s\n", nodoToString); 
				}
			}
			
			return String.format(
				"%s %s: {\n" +
					"%s" +
				"}", categoriaSintactica.getSimpleName(), totalTokens, toStringHijos);
		}
		
		return super.toString();
	}

}
