package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.Identificador;
import co.edu.uniquindio.categoriaslexicas.Separador;
import co.edu.uniquindio.categoriaslexicas.TipoDato;

public class ListaParametros extends CategoriaSintacticaBase {

//<ListaParametros>  ::= TipoDato Identificador, <ListaParametros> | TipoDato Identificador | e
	
	public ListaParametros() {
		producciones = new Class<?>[][]{
			{ TipoDato.class, Identificador.class, Separador.class, ListaParametros.class },
			{ TipoDato.class, Identificador.class },
			{ null },
		};
	}

}
