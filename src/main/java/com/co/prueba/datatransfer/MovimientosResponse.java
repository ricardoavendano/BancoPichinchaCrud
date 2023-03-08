package com.co.prueba.datatransfer;

import java.util.List;

public class MovimientosResponse {

	private CuentaRequest cuentaRequest;
	private List<MovimientosDTO> movimientos;

	public CuentaRequest getCuentaRequest() {
		return cuentaRequest;
	}

	public void setCuentaRequest(CuentaRequest cuentaRequest) {
		this.cuentaRequest = cuentaRequest;
	}

	public List<MovimientosDTO> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovimientosDTO> movimientos) {
		this.movimientos = movimientos;
	}

}
