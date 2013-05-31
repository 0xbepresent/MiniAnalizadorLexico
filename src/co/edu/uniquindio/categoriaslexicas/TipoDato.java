package co.edu.uniquindio.categoriaslexicas;

import co.edu.uniquindio.Token;

public class TipoDato extends CategoriaLexicaPalabras {

	public TipoDato() {
		palabras = new String[]{ "int", "double", "string", "void" };
	}
	
	@Override
	public String traducir(Token token) {
		boolean esString = token.getLexema().equals("string");
		return esString ? "std::string" : super.traducir(token);
	}
	
}
