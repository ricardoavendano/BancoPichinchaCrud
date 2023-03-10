package com.co.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.co.prueba.datatransfer.RespuestaError;
import com.co.prueba.exception.CampoInesperadoExepcion;
import com.co.prueba.exception.CampoObligatorioExcepcion;
import com.co.prueba.exception.ClienteExisteExcepcion;
import com.co.prueba.exception.ClienteInactivoExcepcion;
import com.co.prueba.exception.ClienteNoEncontradoExcepcion;
import com.co.prueba.exception.CuentaExisteExcepcion;
import com.co.prueba.exception.CuentaNoEncontradaExcepcion;
import com.co.prueba.exception.CuentaSaldoInicialExcepcion;
import com.co.prueba.service.ErrorService;

@RestControllerAdvice
public class ControllerAdvice {

	@Autowired
	private ErrorService errorService;

	@ExceptionHandler(CampoInesperadoExepcion.class)
	public final ResponseEntity<?> campoInesperadoExepcion(CampoInesperadoExepcion ex) {

		RespuestaError error = errorService.getError(ex);

		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler(CampoObligatorioExcepcion.class)
	public final ResponseEntity<?> campoObligatorioExcepcion(CampoObligatorioExcepcion ex) {

		RespuestaError error = errorService.getError(ex);

		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler(ClienteExisteExcepcion.class)
	public final ResponseEntity<?> clienteExisteExcepcion(ClienteExisteExcepcion ex) {

		RespuestaError error = errorService.getError(ex);

		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler(ClienteInactivoExcepcion.class)
	public final ResponseEntity<?> clienteInactivoExcepcion(ClienteInactivoExcepcion ex) {

		RespuestaError error = errorService.getError(ex);

		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler(ClienteNoEncontradoExcepcion.class)
	public final ResponseEntity<?> clienteNoEncontradoExcepcion(ClienteNoEncontradoExcepcion ex) {

		RespuestaError error = errorService.getError(ex);

		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler(CuentaExisteExcepcion.class)
	public final ResponseEntity<?> cuentaExisteExcepcion(CuentaExisteExcepcion ex) {

		RespuestaError error = errorService.getError(ex);

		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler(CuentaNoEncontradaExcepcion.class)
	public final ResponseEntity<?> cuentaNoEncontradaExcepcion(CuentaNoEncontradaExcepcion ex) {

		RespuestaError error = errorService.getError(ex);

		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler(CuentaSaldoInicialExcepcion.class)
	public final ResponseEntity<?> cuentaSaldoInicialExcepcion(CuentaSaldoInicialExcepcion ex) {

		RespuestaError error = errorService.getError(ex);

		return new ResponseEntity<>(error, error.getStatus());
	}
}