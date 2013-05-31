package co.edu.uniquindio;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ManejadorArchivos {
	
	private static final String RUTA_FUENTE_DEFECTO = "recursos/CodigoFuente.mal";

	public String[] leerCodigoFuente(String archivo) {
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
	
	public static void escribirArchivo(String archivo, String traduccion) {
		
		Path file = Paths.get("recursos/" + archivo);
	    final byte[] messageBytes = traduccion.getBytes(Charset.forName("UTF-8"));

	    try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING))) {
	        out.write(messageBytes, 0, messageBytes.length);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	} 
	
}
