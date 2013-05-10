package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.AnalizadorSintactico;
import co.edu.uniquindio.Derivacion;


public class ListaSentencias extends CategoriaSintacticaBase {

	public ListaSentencias() {
//		<SaltosLinea> <Sentencia> <SaltosLinea> <ListaSentencias> | <Sentencia> <SaltosLinea> <ListaSentencias> | <Sentencia>		
		producciones = new Class<?>[][]{
			{ SaltosLinea.class, Sentencia.class, SaltosLinea.class, ListaSentencias.class },
			{ Sentencia.class, SaltosLinea.class, ListaSentencias.class },
			{ Sentencia.class, SaltosLinea.class },
			{ Sentencia.class },
		};
	}
	
	@Override
	public Derivacion construir(AnalizadorSintactico contexto) {
		// TODO Quitar Auto-generated method stub
		return super.construir(contexto);
	}

}
