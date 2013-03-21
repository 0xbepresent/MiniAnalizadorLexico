package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.AnalizadorLexico;

public abstract class CategoriaLexicaBase implements CategoriaLexica {

	protected AnalizadorLexico analizadorLexico;
	
	@Override
	public void setAnalizador(AnalizadorLexico analizadorLexico) {
		this.analizadorLexico = analizadorLexico;
	}

}
