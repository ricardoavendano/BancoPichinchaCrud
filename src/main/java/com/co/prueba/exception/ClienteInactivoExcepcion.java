package com.co.prueba.exception;

public class ClienteInactivoExcepcion extends Exception {

	private static final long serialVersionUID = -8098845069729105231L;
	private final String causaError;

	public ClienteInactivoExcepcion(String causaError) {
		this.causaError = causaError;
	}

	public String getCausaError() {
		return causaError;
	}
}
