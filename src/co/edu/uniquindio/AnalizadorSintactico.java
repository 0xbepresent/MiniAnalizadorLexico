package co.edu.uniquindio;

import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import co.edu.uniquindio.categoriassintacticas.CategoriaSintactica;
import co.edu.uniquindio.categoriassintacticas.UnidadCompilacion;

public class AnalizadorSintactico {

	private final List<Token> tokens;
	
	private final Class<? extends CategoriaSintactica> claseRaiz = UnidadCompilacion.class;
	
	private int indiceTokenActual = 0;

	private List<Error> errores;

	private Derivacion derivacionRaiz;

	public AnalizadorSintactico(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	public Nodo analizar(){
		try {
			CategoriaSintactica raiz = claseRaiz.newInstance();
			
			derivacionRaiz = raiz.construir(this);
			return derivacionRaiz;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Token> getTokens() {
		return tokens;
	}
	
	public void irSiguienteToken() {
		indiceTokenActual++;
	}
	
	public Token getTokenActual() {
		return indiceTokenActual >= tokens.size() ?
				null :
				tokens.get(indiceTokenActual);
	}

	public List<Error> getErrores() {
		return errores;
	}

	public int getIndiceTokenActual() {
		return indiceTokenActual;
	}

	public void setIndiceTokenActual(int indiceTokenActual) {
		this.indiceTokenActual = indiceTokenActual;
	}
	
	public JTree getRepresentacionVisual() {
		DefaultMutableTreeNode raiz = obtenerRaizJTree(derivacionRaiz);
		JTree jTree = new JTree(raiz);
		return jTree;
	}

	private DefaultMutableTreeNode obtenerRaizJTree(Nodo nodo) {
		DefaultMutableTreeNode actual = new DefaultMutableTreeNode();
		
		if (nodo instanceof Derivacion) {
			Derivacion derivacion = (Derivacion) nodo;
			actual.setUserObject(derivacion.getCategoriaSintactica().getSimpleName() + ": " + derivacion.getTotalTokens());
			
			for (Nodo nodoI : derivacion.getHijos()) {
				actual.add(obtenerRaizJTree(nodoI));
			}
		}
		else {
			if (nodo instanceof Token) {
				Token token = (Token) nodo;
				actual.setUserObject(token);
			}
		}
		
		return actual;
	}
	
}
