package co.edu.uniquindio.categoriassintacticas;


public class UnidadCompilacion extends CategoriaSintacticaBase {

	public UnidadCompilacion() {
		producciones = new Class<?>[][]{
			{ ListaSentenciasPrograma.class }
		};
	}

}
