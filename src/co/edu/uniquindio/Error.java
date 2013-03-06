package co.edu.uniquindio;

public class Error {

	private final int linea;
	private final int posicion;
	private final String mensaje;
	
	public Error(int linea, int posicion, String mensaje) {
		this.linea = linea;
		this.posicion = posicion;
		this.mensaje = mensaje;
	}
	
	@Override
	public String toString() {
		return String.format("{Linea: %s, Posicion: %s}", linea, posicion);
	}
	
	public int getLinea() {
		return linea;
	}

	public int getPosicion() {
		return posicion;
	}

	public String getMensaje() {
		return mensaje;
	}
	
}
