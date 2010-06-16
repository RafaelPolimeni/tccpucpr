package br.com.exceptions;

public class BaseDadosException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	NullPointerException n = new NullPointerException();

	public BaseDadosException(String s) {
		super(s);
	}

	public BaseDadosException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public BaseDadosException(Throwable throwable) {
		super(throwable);
	}
}
