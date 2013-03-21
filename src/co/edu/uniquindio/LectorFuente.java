package co.edu.uniquindio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorFuente {
	
	private static final String RUTA_FUENTE_DEFECTO = "recursos/CodigoFuente.mal";

	public String[] leer(String archivo) {
		archivo = archivo != null ? "recursos/" + archivo : RUTA_FUENTE_DEFECTO;
		List<String> lineasList = new ArrayList<String>(); 
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(RUTA_FUENTE_DEFECTO));
	        String linea = null;

	        while ((linea = br.readLine()) != null) {
	        	lineasList.add(linea);
	        }
	    }
		catch(Exception e) {
			// TODO
			e.printStackTrace();
			System.err.println("No se pudo leer el archivo de código fuente: " + e.getMessage());
		}
		finally {
	        try {
	        	if(br != null) {
	        		br.close();
	        	}
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		
		return lineasList.toArray(new String[lineasList.size()]);
	}
	
}
