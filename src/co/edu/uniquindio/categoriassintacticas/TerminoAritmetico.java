package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosApertura;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosCierre;
import co.edu.uniquindio.categoriaslexicas.Flotante;
import co.edu.uniquindio.categoriaslexicas.Identificador;
import co.edu.uniquindio.categoriaslexicas.Int;

public class TerminoAritmetico extends CategoriaSintacticaBase {

	public TerminoAritmetico() {
		producciones = new Class<?>[][]{
			{ AgrupadorParametrosApertura.class, ExpresionAritmetica.class, AgrupadorParametrosCierre.class },
			{ InvocacionMetodo.class },
			{ Identificador.class },
			{ Int.class },
			{ Flotante.class },
		};
	}

}
