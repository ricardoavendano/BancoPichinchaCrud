package com.co.prueba.exception;

public class CampoInesperadoExepcion extends Exception {

	private static final long serialVersionUID = 378112175068394862L;
	private final String causaError;

	public CampoInesperadoExepcion(String causaError) {
		this.causaError = causaError;
	}

	public String getCausaError() {
		return causaError;
	}
}
