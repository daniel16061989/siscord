package br.com.ufu.bsi.dao.excecoes;

public class SiscordGenericException extends Exception {

	private static final long serialVersionUID = 2368530564187295103L;

	public SiscordGenericException() {
		super();
	}

	public SiscordGenericException(String message, Throwable cause) {
		super(message, cause);
	}

	public SiscordGenericException(String message) {
		super(message);
	}

	public SiscordGenericException(Throwable cause) {
		super(cause);
	}

}
