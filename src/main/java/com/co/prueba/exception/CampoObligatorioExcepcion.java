package com.co.prueba.exception;

public class CampoObligatorioExcepcion extends Exception {

	private static final long serialVersionUID = 9121152831432096635L;

	private final String causaError;

	public CampoObligatorioExcepcion(String causaError) {
		this.causaError = causaError;
	}

	public String getCausaError() {
		return causaError;
	}
}