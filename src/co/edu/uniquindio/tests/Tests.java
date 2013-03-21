package co.edu.uniquindio.tests;

import org.junit.Test;

import co.edu.uniquindio.AnalizadorLexico;
import co.edu.uniquindio.LectorFuente;

import junit.framework.TestCase;

public class Tests extends TestCase {
	
	@Test
	public void testCompilador() {
		String[] codigoAnalizar = new LectorFuente().leer(null);
		AnalizadorLexico analizadorLexico = new AnalizadorLexico(codigoAnalizar);
		assertTrue(true);
	}

}
