package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.Identificador;
import co.edu.uniquindio.categoriaslexicas.OperadorAsignacion;

public class Asignacion extends CategoriaSintacticaBase {

	public Asignacion() {
		
		producciones = new Class<?>[][]{
			{ Identificador.class, OperadorAsignacion.class, Expresion.class }
		};
	}

}
