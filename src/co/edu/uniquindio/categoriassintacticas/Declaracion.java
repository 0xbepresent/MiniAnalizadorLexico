package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.Identificador;
import co.edu.uniquindio.categoriaslexicas.TipoDato;

public class Declaracion extends CategoriaSintacticaBase {

	public Declaracion() {
		producciones = new Class<?>[][]{
			{ TipoDato.class, Asignacion.class },
			{ TipoDato.class, Identificador.class },
		};
	}
	
}
