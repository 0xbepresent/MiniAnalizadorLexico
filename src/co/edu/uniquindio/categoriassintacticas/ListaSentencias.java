package co.edu.uniquindio.categoriassintacticas;

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
	
}
