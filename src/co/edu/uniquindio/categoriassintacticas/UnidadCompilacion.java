package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.Derivacion;


public class UnidadCompilacion extends CategoriaSintacticaBase {

	public UnidadCompilacion() {
		producciones = new Class<?>[][]{
			{ ListaSentenciasPrograma.class }
		};
	}
	
	@Override
	public String traducir(Derivacion derivacion) {
		String encabezado = "#include <iostream>\n using namespace std;\n";
		return encabezado + super.traducir(derivacion);
	}

}
