package co.edu.uniquindio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;

public class Main {
	
	public static void main(String[] args) {
		
		final String CODIGO_ANALIZAR[] = new ManejadorArchivos().leerCodigoFuente(null); 

		System.out.println(Arrays.toString(CODIGO_ANALIZAR));
		System.out.println();
		
		AnalizadorLexico analizadorLexico = new AnalizadorLexico(CODIGO_ANALIZAR);
		List<Token> tokens = analizadorLexico.analizar();

		TablaSimbolos tablaSimbolos = analizadorLexico.getTablaSimbolos();
		List<Error> errores = analizadorLexico.getErrores();

		System.out.println("Tokens: " + tokens.size());
		for (Token token : tokens) {
			System.out.println(token);
		}
		
		System.out.println();
		System.out.println(String.format("Tabla de símbolos ---> Cantidad: %s, %s", tablaSimbolos.size(), tablaSimbolos));
		
		System.out.println();
		System.out.println("Errores");
		for (Error error : errores) {
			System.out.println(error);
		}
		
		System.out.println();
		AnalizadorSintactico analizadorSintactico = new AnalizadorSintactico(tokens);
		Nodo raiz = analizadorSintactico.analizar();
		
		System.out.println(raiz);
		
		AnalizadorSemantico analizadorSemantico = new AnalizadorSemantico(raiz, tablaSimbolos);
		analizadorSemantico.analizar();
		System.out.println(String.format("Tabla de símbolos ---> Cantidad: %s, %s", tablaSimbolos.size(), tablaSimbolos));
		
		List<String> erroresSemanticos = analizadorSemantico.getErrores();
		System.out.println("Errores semánticos: " + erroresSemanticos.size());
		for (String error : erroresSemanticos) {
			System.err.println(error);
		}
		
		Traductor traductor = new Traductor(raiz, tablaSimbolos);
		System.out.println();
		System.out.println("Traducción");
		String traduccion = traductor.traducir();
		System.out.println(traduccion);
		
//		mostrarRepresentacionVisual(analizadorSintactico.getRepresentacionVisual());

		ManejadorArchivos.escribirArchivo("traduccion.cpp", traduccion);
		
		System.out.println();
		System.out.println("Ejecutando traducción...");
		
		System.out.println();
		System.out.println("Salida:\n");
		String salida = ejecutarTraduccion();
		System.out.println(salida);
	}

	private static String ejecutarTraduccion() {
		try {
			Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec("g++ recursos/traduccion.cpp -o recursos/ejecutable.o");
			pr.waitFor();
			if(pr.exitValue() != 0) {
				return "Falló la compilación de la traducción por el g++";
			}
			
			pr = rt.exec("recursos/ejecutable.o");
			pr.waitFor();
			
			BufferedReader stdInput = new BufferedReader(new 
		             InputStreamReader(pr.getInputStream()));

			String salida = "";
			String linea = "";
	        while ((linea = stdInput.readLine()) != null) {
	        	salida += linea + "\n";
	        }
			return salida;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void mostrarRepresentacionVisual(JTree representacionVisual) {
		JFrame jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
		JPanel jPanel = (JPanel) jFrame.getContentPane();
		
		for (int i = 0; i < representacionVisual.getRowCount(); i++) {
	         representacionVisual.expandRow(i);
		}
		
		jPanel.add(representacionVisual);
		jFrame.pack();
	}

}