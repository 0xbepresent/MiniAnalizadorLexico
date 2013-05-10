package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosApertura;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosCierre;
import co.edu.uniquindio.categoriaslexicas.OperadorBooleano;

public class ExpresionBooleana extends CategoriaSintacticaBase {

	public ExpresionBooleana() {
		producciones = new Class<?>[][]{
			{ TerminoBooleano.class, OperadorBooleano.class, ExpresionBooleana.class },
			{ TerminoBooleano.class },
			{ AgrupadorParametrosApertura.class, ExpresionBooleana.class, AgrupadorParametrosCierre.class }
		};
	}

}
