package co.edu.uniquindio.categoriassintacticas;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.Derivacion;
import co.edu.uniquindio.Nodo;
import co.edu.uniquindio.Token;


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
	
	@Override
	public String traducir(Derivacion derivacion) {
		// Se declaran las variables.
		// Se declaran los métodos.
		// Se escribe un main con las sentencias de programa pero sin las declaraciones de métodos
		// y de las declaraciones de variable se toma solo la asignación.
		// Se retorna 0 para el main.

		String traduccion = "";
		
		List<Derivacion> sentenciasPrograma     = getListaSentenciasPrograma(derivacion);
		List<Derivacion> declaracionesVariables = getDeclaracionesVariables(sentenciasPrograma);
		List<Derivacion> declaracionesMetodos   = getDeclaracionesMetodos(sentenciasPrograma);
		
		traduccion += getTraduccionDeclaracionVariables(declaracionesVariables);
		
		// Se declaran los métodos de clase.
		for (Derivacion declaracionMetodo: declaracionesMetodos) {
			traduccion += declaracionMetodo.traducir() + "\n";
		}
		
		// Se escribe un main con las sentencias de programa pero sin las declaraciones de métodos
		// y de las declaraciones de variable se toma sólo la asignación.
		List<Derivacion> filtroSentencias = filtrarSentencias(sentenciasPrograma);
		String traduccionSentenciasMain = "";
		
		for (Derivacion sentencia: filtroSentencias) {
			if(sentencia.getCategoriaSintactica().equals(Sentencia.class)) {
				traduccionSentenciasMain += sentencia.traducir() + "\n";
			}
			else {
				traduccionSentenciasMain += sentencia.traducir() + ";\n";
			}
		}
		
		String main = String.format("int main() {\n %s return 0;\n}", traduccionSentenciasMain);
		traduccion += main;
		
		return traduccion;
	}

	private List<Derivacion> filtrarSentencias(List<Derivacion> sentenciasPrograma) {
		
		List<Derivacion> sentenciasFiltradas = new ArrayList<Derivacion>();
		for (Derivacion derivacion : sentenciasPrograma) {
			if(
			// Si es una Sentencia
			((Derivacion) derivacion.getHijos().get(0)).getCategoriaSintactica().equals(Sentencia.class)
			// Y si es una declaración
			&& ((Derivacion) derivacion.getHijos().get(0).getHijos().get(0)).getCategoriaSintactica().equals(Declaracion.class)) {

				// Si tiene una asignación se agrega a la lista
				Derivacion derivacionDeclaracion = (Derivacion) derivacion.getHijos().get(0).getHijos().get(0);
				Nodo segundoHijo = derivacionDeclaracion.getHijos().get(1);
				if (segundoHijo instanceof Derivacion) {
					Derivacion asignacion = (Derivacion) segundoHijo;
					sentenciasFiltradas.add(asignacion);
				}
			}
			// Si es una Sentencia diferente de Declaracion
			else if(
			((Derivacion) derivacion.getHijos().get(0)).getCategoriaSintactica().equals(Sentencia.class)){
				sentenciasFiltradas.add((Derivacion) derivacion.getHijos().get(0));
			}
		}
		
		return sentenciasFiltradas;
	}

	private String getTraduccionDeclaracionVariables(List<Derivacion> declaracionesVariables) {
		// <Declaracion> ::= TipoDato <Asignacion> | TipoDato Identificador
		// <Asignacion>  ::= Identificador = <Expresion>
		String traduccion = "";
		for (Derivacion derivacion : declaracionesVariables) {
			String identificador = "";
			Nodo segundoHijo = derivacion.getHijos().get(1);
			if (segundoHijo instanceof Derivacion) {
				Derivacion asignacion = (Derivacion) segundoHijo;
				identificador = ((Token) asignacion.getHijos().get(0)).getLexema();
			}
			else {
				Token identificadorToken = (Token) segundoHijo;
				identificador = identificadorToken.getLexema();
			}
			traduccion += String.format("%s %s;\n", derivacion.getHijos().get(0).traducir(), identificador); 
		}
		return traduccion;
	}

	private List<Derivacion> getDeclaracionesMetodos(List<Derivacion> sentenciasPrograma) {
		List<Derivacion> declaracionesMetodos = new ArrayList<Derivacion>();
		for (Derivacion derivacion : sentenciasPrograma) {
			// Si es una DeclaracionMetodo
			if(((Derivacion) derivacion.getHijos().get(0)).getCategoriaSintactica().equals(DeclaracionMetodo.class)) {
				declaracionesMetodos.add((Derivacion) derivacion.getHijos().get(0));
			}
		}
		
		return declaracionesMetodos;
	}

	private List<Derivacion> getDeclaracionesVariables(List<Derivacion> sentenciasPrograma) {
		// Ej.:
		//  Sentencia 4: {
		//    Declaracion 4: {
		
		List<Derivacion> declaracionesVariables = new ArrayList<Derivacion>();
		for (Derivacion derivacion : sentenciasPrograma) {
			if(
			// Si es una Sentencia
			((Derivacion) derivacion.getHijos().get(0)).getCategoriaSintactica().equals(Sentencia.class)
			// Y si es una declaración
			&& ((Derivacion) derivacion.getHijos().get(0).getHijos().get(0)).getCategoriaSintactica().equals(Declaracion.class)) {
				declaracionesVariables.add((Derivacion) derivacion.getHijos().get(0).getHijos().get(0));
			}
		}
		
		return declaracionesVariables;
	}

	private List<Derivacion> getListaSentenciasPrograma(Derivacion listaSentenciasPrograma) {
		List<Derivacion> sentenciasPrograma = new ArrayList<Derivacion>();
		
		for (Nodo nodo : listaSentenciasPrograma.getHijos()) {
			// Si es una sentencia de programa se agrega a la lista.
			if (nodo instanceof Derivacion && ((Derivacion) nodo).getCategoriaSintactica().equals(SentenciaPrograma.class)) {
				sentenciasPrograma.add((Derivacion) nodo);
			}
			// Si es una lista de sentencias se agrega de forma recursiva.
			else if (nodo instanceof Derivacion && ((Derivacion) nodo).getCategoriaSintactica().equals(ListaSentenciasPrograma.class)) {
				sentenciasPrograma.addAll(getListaSentenciasPrograma((Derivacion) nodo));
			}
		}
		
		return sentenciasPrograma;
	}

}
