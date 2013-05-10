package co.edu.uniquindio.categoriassintacticas;


public class ListaSentenciasPrograma extends CategoriaSintacticaBase {

//<ListaSentenciasPrograma>::= <SaltosLinea> <SentenciaPrograma> <SaltosLinea> <ListaSentenciasPrograma>
//                               | <SentenciaPrograma> <SaltosLinea> <ListaSentenciasPrograma> 
//	                             | <SentenciaPrograma> <SaltosLinea> | <SentenciaPrograma>
	
	public ListaSentenciasPrograma() {
		producciones = new Class<?>[][]{
//			<SaltosLinea> <SentenciaPrograma> <SaltosLinea> <ListaSentenciasPrograma>
			{ SaltosLinea.class, SentenciaPrograma.class, SaltosLinea.class, ListaSentenciasPrograma.class },
//			<SentenciaPrograma> <SaltosLinea> <ListaSentenciasPrograma>
			{ SentenciaPrograma.class, SaltosLinea.class, ListaSentenciasPrograma.class },
//			<SentenciaPrograma> <SaltosLinea>
			{ SentenciaPrograma.class, SaltosLinea.class },
//			<SentenciaPrograma>
			{ SentenciaPrograma.class }
		};
	}

}
