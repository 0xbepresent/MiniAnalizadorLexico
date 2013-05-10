package co.edu.uniquindio.categoriassintacticas;


public class EstructuraControl extends CategoriaSintacticaBase {

	public EstructuraControl() {
		producciones = new Class<?>[][]{
			{ If.class},
			{ For.class },
		};
	}

}
