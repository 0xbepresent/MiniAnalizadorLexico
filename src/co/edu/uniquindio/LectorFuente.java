package co.edu.uniquindio;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LectorFuente {
	
	private static final String RUTA_FUENTE_DEFECTO = "recursos/CodigoFuente.mal";

	public String[] leer(String archivo) {
		archivo = archivo != null ? "recursos/" + archivo : RUTA_FUENTE_DEFECTO;
		List<String> lineasList = null;
		
		try {
			lineasList = Files.readAllLines(Paths.get(archivo), Charset.defaultCharset());
	    }
		catch(Exception e) {
			e.printStackTrace();
			System.err.println("No se pudo leer el archivo de código fuente: " + e.getMessage());
		}
		
		return lineasList.toArray(new String[lineasList.size()]);
	}
	
}
