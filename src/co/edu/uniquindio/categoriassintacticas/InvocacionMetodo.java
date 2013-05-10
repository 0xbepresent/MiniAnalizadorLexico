package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosApertura;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosCierre;
import co.edu.uniquindio.categoriaslexicas.Identificador;
import co.edu.uniquindio.categoriaslexicas.InvocadorMetodo;

public class InvocacionMetodo extends CategoriaSintacticaBase {

	public InvocacionMetodo() {
		
		producciones = new Class<?>[][]{
			{ Identificador.class, InvocadorMetodo.class, Identificador.class, AgrupadorParametrosApertura.class, ListaArgumentos.class, AgrupadorParametrosCierre.class },
			{ Identificador.class, AgrupadorParametrosApertura.class, ListaArgumentos.class, AgrupadorParametrosCierre.class },
		};
	}

}
