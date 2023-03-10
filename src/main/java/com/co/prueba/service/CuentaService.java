package com.co.prueba.service;

import org.springframework.stereotype.Service;

import com.co.prueba.datatransfer.CuentaRequest;
import com.co.prueba.exception.CampoInesperadoExepcion;
import com.co.prueba.exception.CampoObligatorioExcepcion;
import com.co.prueba.exception.ClienteInactivoExcepcion;
import com.co.prueba.exception.ClienteNoEncontradoExcepcion;
import com.co.prueba.exception.CuentaExisteExcepcion;
import com.co.prueba.exception.CuentaNoEncontradaExcepcion;
import com.co.prueba.exception.CuentaSaldoInicialExcepcion;

@Service
public interface CuentaService {

	public CuentaRequest consultarCuenta(Long numeroCuenta) throws CuentaNoEncontradaExcepcion;

	public void crearCuenta(CuentaRequest cuentaRequest) throws CampoObligatorioExcepcion,
			ClienteNoEncontradoExcepcion, ClienteInactivoExcepcion, CuentaExisteExcepcion, CampoInesperadoExepcion;

	public void actualizarEstadoCuenta(Boolean estado, Long numeroCuenta) throws CuentaNoEncontradaExcepcion;

	public void actualizarCuenta(CuentaRequest cuentaRequest)
			throws CampoObligatorioExcepcion, CuentaNoEncontradaExcepcion, ClienteNoEncontradoExcepcion, CuentaSaldoInicialExcepcion, CampoInesperadoExepcion;

	public void eliminarCuenta(Long numeroCuenta) throws CuentaNoEncontradaExcepcion;

}
