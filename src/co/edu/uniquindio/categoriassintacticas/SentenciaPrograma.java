package co.edu.uniquindio.categoriassintacticas;


public class SentenciaPrograma extends CategoriaSintacticaBase {

//<SentenciaPrograma>      ::= <ListaSentencias> | <DeclaracionMetodo>	
	
	public SentenciaPrograma() {
		producciones = new Class<?>[][]{
			{ DeclaracionMetodo.class },
			{ Sentencia.class },
		};
	}

}
