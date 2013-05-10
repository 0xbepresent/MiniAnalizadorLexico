package co.edu.uniquindio.categoriassintacticas;



public class Sentencia extends CategoriaSintacticaBase {

	public Sentencia() {
		producciones = new Class<?>[][]{
			{ EstructuraControl.class },
			{ Declaracion.class },
			{ Asignacion.class },
			{ InvocacionMetodo.class},
		};
	}
	
}
