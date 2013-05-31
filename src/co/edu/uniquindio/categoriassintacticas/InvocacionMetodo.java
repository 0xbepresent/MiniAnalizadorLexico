package co.edu.uniquindio.categoriassintacticas;

import co.edu.uniquindio.Derivacion;
import co.edu.uniquindio.Nodo;
import co.edu.uniquindio.Token;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosApertura;
import co.edu.uniquindio.categoriaslexicas.AgrupadorParametrosCierre;
import co.edu.uniquindio.categoriaslexicas.Identificador;
import co.edu.uniquindio.categoriaslexicas.InvocadorMetodo;

public class InvocacionMetodo extends CategoriaSintacticaBase {

	public InvocacionMetodo() {
		
		producciones = new Class<?>[][]{
			{ Identificador.class, InvocadorMetodo.class, Identificador.class, AgrupadorParametrosApertura.class, ListaArgumentos.class, AgrupadorParametrosCierre.class },
			{ Identificador.class, AgrupadorParametrosApertura.class, ListaArgumentos.class, AgrupadorParametrosCierre.class },
		};
	}
	
	@Override
	public String traducir(Derivacion derivacion) {
		// Para traducir las impresiones.
		// puts(<Expresion>) --> cout << <Expresion> << endl
		Token primerToken  = (Token) derivacion.getHijos().get(0);
		Token segundoToken = (Token) derivacion.getHijos().get(1);
		
		boolean esArgumentoUnicoExpresion = false;
		Nodo tercerNodo = derivacion.getHijos().get(2);
		Derivacion expresion = null;
		if (tercerNodo instanceof Derivacion) {
			Derivacion listaArgumentos = (Derivacion) tercerNodo;
			Nodo primerNodoListaArgumentos = listaArgumentos.getHijos().size() == 0 ? null : listaArgumentos.getHijos().get(0);
			if(
			// Si tiene un primer nodo
			primerNodoListaArgumentos != null
			// Si sólo tiene un argumento que es una expresión.
			&& listaArgumentos.getHijos().size() == 1
			// Y si es una expresion.
			&& primerNodoListaArgumentos instanceof Derivacion
			&& ((Derivacion) primerNodoListaArgumentos).getCategoriaSintactica().equals(Expresion.class)) {
				esArgumentoUnicoExpresion = true;
				expresion = (Derivacion) primerNodoListaArgumentos;
			}
		}
		
		if(
		// Si empieza por puts
		primerToken.getLexema().equals("puts")
		// y está seguido por (
		&& segundoToken.getTipoToken().equals(AgrupadorParametrosApertura.class)
		// y tiene un única expresión como argumento 
		&& esArgumentoUnicoExpresion) {
			return String.format("cout <<%s << endl", expresion.traducir()); 
		}
		
		// Sino, se recurre a la traducción por defecto.
		return super.traducir(derivacion);
	}

}
