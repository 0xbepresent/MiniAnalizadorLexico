package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.TipoToken;

public class Separador extends CategoriaLexicaUnCaracter {

	public Separador() {
		caracter = ',';
		tipoToken = TipoToken.Separador;
	}

}
