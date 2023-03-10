package com.co.prueba.exception;

public class CuentaExisteExcepcion extends Exception {

	private static final long serialVersionUID = -6020610014425127072L;
	private final String causaError;

	public CuentaExisteExcepcion(String causaError) {
		this.causaError = causaError;
	}

	public String getCausaError() {
		return causaError;
	}
}
