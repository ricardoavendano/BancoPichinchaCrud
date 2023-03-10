package com.co.prueba.exception;

public class ClienteNoEncontradoExcepcion extends Exception {

	private static final long serialVersionUID = 7511705567600939046L;

	private final String causaError;

	public ClienteNoEncontradoExcepcion(String causaError) {
		this.causaError = causaError;
	}

	public String getCausaError() {
		return causaError;
	}

}
