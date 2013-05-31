package co.edu.uniquindio.categoriassintacticas;


import co.edu.uniquindio.AnalizadorSintactico;
import co.edu.uniquindio.Derivacion;

public interface CategoriaSintactica {
	
	public Derivacion construir(AnalizadorSintactico contexto);
	
	public String traducir(Derivacion derivacion);
	
}
