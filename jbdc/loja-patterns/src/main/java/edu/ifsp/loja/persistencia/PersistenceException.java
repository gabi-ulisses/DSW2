package edu.ifsp.loja.persistencia;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = 1L;

	public PersistenceException(String msg) {
		super(msg);
	}

	private PersistenceException(Throwable t) {
		super(t);
	}

	public static PersistenceException wrap(Throwable t) {
		if (t instanceof PersistenceException) {
			return (PersistenceException) t;
		} else {
			return new PersistenceException(t);
		}
	}
}
