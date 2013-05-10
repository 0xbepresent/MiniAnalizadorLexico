package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.AgrupadorCodigoApertura;
import co.edu.uniquindio.categoriaslexicas.AgrupadorCodigoCierre;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosApertura;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosCierre;
import co.edu.uniquindio.categoriaslexicas.Eol;
import co.edu.uniquindio.categoriaslexicas.Identificador;
import co.edu.uniquindio.categoriaslexicas.TipoDato;

public class DeclaracionMetodo extends CategoriaSintacticaBase {

//<DeclaracionMetodo>::= TipoDato Identificador ( <ListaParametros> ) { EOL <ListaSentencias> <Expresion> <SaltosLinea> } 
//                     | TipoDato Identificador ( <ListaParametros> ) { EOL <ListaSentencias> }	
	
	public DeclaracionMetodo() {
		producciones = new Class<?>[][]{
			{
//				TipoDato Identificador ( <ListaParametros> )
				TipoDato.class, Identificador.class, AgrupadorParametrosApertura.class, ListaParametros.class, AgrupadorParametrosCierre.class,
//				{ EOL <ListaSentencias> <Expresion> }
				AgrupadorCodigoApertura.class, Eol.class, ListaSentencias.class, Expresion.class, SaltosLinea.class, AgrupadorCodigoCierre.class
			},
			{
//				TipoDato Identificador ( <ListaParametros> )
				TipoDato.class, Identificador.class, AgrupadorParametrosApertura.class, ListaParametros.class, AgrupadorParametrosCierre.class,
//				{ EOL <ListaSentencias> }
				AgrupadorCodigoApertura.class, Eol.class, ListaSentencias.class, AgrupadorCodigoCierre.class
			}
		};
	}

}
