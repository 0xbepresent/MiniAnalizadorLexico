package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.TipoToken;

public class OperadorAsignacion extends CategoriaLexicaUnCaracter {

	public OperadorAsignacion() {
		caracter = '=';
		tipoToken = TipoToken.OperadorAsignacion;
	}

}
