package co.edu.uniquindio;

import java.util.List;

public interface Nodo {

	public Nodo getPadre();
	
	public void setPadre(Nodo padre);
	
	public List<Nodo> getHijos();
	
	public void setHijos(List<Nodo> hijos);

}
