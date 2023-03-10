package com.co.prueba.exception;

public class ClienteExisteExcepcion extends Exception {

	private static final long serialVersionUID = -8098845069729105231L;
	private final String causaError;

	public ClienteExisteExcepcion(String causaError) {
		this.causaError = causaError;
	}

	public String getCausaError() {
		return causaError;
	}
}
