package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.categoriaslexicas.AgrupadorCodigoApertura;
import co.edu.uniquindio.categoriaslexicas.AgrupadorCodigoCierre;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosApertura;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosCierre;
import co.edu.uniquindio.categoriaslexicas.Else;
import co.edu.uniquindio.categoriaslexicas.Eol;

public class If extends CategoriaSintacticaBase {

	public If() {
		producciones = new Class<?>[][]{
			{ 
//				if ( <ExpresioBooleana> )
				co.edu.uniquindio.categoriaslexicas.If.class, AgrupadorParametrosApertura.class, ExpresionBooleana.class, AgrupadorParametrosCierre.class,
//				{ EOL <ListaSentencias> } EOL
				AgrupadorCodigoApertura.class, Eol.class, ListaSentencias.class, AgrupadorCodigoCierre.class, Eol.class,
//				else { EOL <ListaSentencias> }
				Else.class, AgrupadorCodigoApertura.class, Eol.class, ListaSentencias.class, AgrupadorCodigoCierre.class 
			},
			{
//				if ( <ExpresioBooleana> )
				co.edu.uniquindio.categoriaslexicas.If.class, AgrupadorParametrosApertura.class, ExpresionBooleana.class, AgrupadorParametrosCierre.class,
//				{ EOL <ListaSentencias> }
				AgrupadorCodigoApertura.class, Eol.class, ListaSentencias.class, AgrupadorCodigoCierre.class 
			},
		};
	}
	
}
