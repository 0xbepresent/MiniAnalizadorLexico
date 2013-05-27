package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.AgrupadorCodigoApertura;
import co.edu.uniquindio.categoriaslexicas.AgrupadorCodigoCierre;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosApertura;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosCierre;
import co.edu.uniquindio.categoriaslexicas.Eol;
import co.edu.uniquindio.categoriaslexicas.SeparadorFor;

public class For extends CategoriaSintacticaBase {

	public For() {
		// for(<Declaracion>; <ExpresionBooleana>; <Asignacion>) { EOL <ListaSentencias> }
		producciones = new Class<?>[][]{
			{ 
				// for
				co.edu.uniquindio.categoriaslexicas.For.class,
				// (<Declaracion>;
				AgrupadorParametrosApertura.class, Declaracion.class, SeparadorFor.class,
				// <ExpresionBooleana>; <Asignacion>)
				ExpresionBooleana.class, SeparadorFor.class, Asignacion.class, AgrupadorParametrosCierre.class, 
				// { EOL <ListaSentencias> }
				AgrupadorCodigoApertura.class, Eol.class, ListaSentencias.class, AgrupadorCodigoCierre.class
			}
		};
	}

}
