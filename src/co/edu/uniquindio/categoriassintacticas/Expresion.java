package co.edu.uniquindio.categoriassintacticas;


public class Expresion extends CategoriaSintacticaBase {

	public Expresion() {
		
		modo = ModoAceptacion.MAYOR_CONSUMO_TOKENS;
		
		producciones = new Class<?>[][]{
			{ ExpresionAritmetica.class },
			{ ExpresionBooleana.class },
			{ ExpresionCadena.class },
		};
	}
	
}