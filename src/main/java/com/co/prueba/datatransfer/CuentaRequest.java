package com.co.prueba.datatransfer;

public class CuentaRequest {

	private Long numeroCuenta;
	private String tipoCuenta;
	private Long saldoInicialCuenta;
	private Boolean estadoCuenta;
	private Long identificacionCliente;
	private String nombreCliente;

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Long getSaldoInicialCuenta() {
		return saldoInicialCuenta;
	}

	public void setSaldoInicialCuenta(Long saldoInicialCuenta) {
		this.saldoInicialCuenta = saldoInicialCuenta;
	}

	public Boolean getEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(Boolean estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	public Long getIdentificacionCliente() {
		return identificacionCliente;
	}

	public void setIdentificacionCliente(Long identificacionCliente) {
		this.identificacionCliente = identificacionCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

}
