package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.Derivacion;

public class Sentencia extends CategoriaSintacticaBase {

	public Sentencia() {
		producciones = new Class<?>[][]{
			{ EstructuraControl.class },
			{ Declaracion.class },
			{ Asignacion.class },
			{ InvocacionMetodo.class},
		};
	}
	
	@Override
	public String traducir(Derivacion derivacion) {
//		<EstructuraControl> | <Declaracion> | <Asignacion> | <InvocacionMetodo>
		Derivacion derivacionSentencia = (Derivacion) derivacion.getHijos().get(0);
		boolean esEstructuraControl = derivacionSentencia.getCategoriaSintactica().equals(EstructuraControl.class);
		return super.traducir(derivacion) +
			(esEstructuraControl ? "" : ";");
	}
	
}
