package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.TipoToken;

public class OperadorRelacional extends CategoriaLexicaPalabras {

	public OperadorRelacional() {
		palabras = new String[]{ "::", "!:", ">", ":>", "<", ":<" };
		tipoToken = TipoToken.OperadorRelacional;
	}

}
