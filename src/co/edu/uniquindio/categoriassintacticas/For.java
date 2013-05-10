package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosApertura;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosCierre;
import co.edu.uniquindio.categoriaslexicas.SeparadorFor;
import co.edu.uniquindio.categoriaslexicas.TipoDato;

public class For extends CategoriaSintacticaBase {

	public For() {
		// TODO probar este gramática.
//		for(TipoDato <Asignacion>; <ExpresionBooleana>; <Asignacion>) { EOL <ListaSentencias> }
		producciones = new Class<?>[][]{
			{ 
				// for
				co.edu.uniquindio.categoriaslexicas.For.class,
				// (TipoDato <Asignacion>;
				AgrupadorParametrosApertura.class, TipoDato.class, Asignacion.class, SeparadorFor.class,
				// <ExpresionBooleana>; <Asignacion>)
				ExpresionBooleana.class, SeparadorFor.class, Asignacion.class, AgrupadorParametrosCierre.class 
				//
//				AgrupadorCodigoApertura.class, Eol.class, ListaSe
			}
		};
	}

}
