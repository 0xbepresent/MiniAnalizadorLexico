package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.Separador;

public class ListaArgumentos extends CategoriaSintacticaBase {

	public ListaArgumentos() {
		
		producciones = new Class<?>[][]{
			{ Expresion.class, Separador.class, ListaArgumentos.class },
			{ Expresion.class },
			{ null },
		};
	}

}
