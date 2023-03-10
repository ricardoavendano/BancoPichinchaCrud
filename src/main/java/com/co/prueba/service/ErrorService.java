package com.co.prueba.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.co.prueba.datatransfer.RespuestaError;
import com.co.prueba.exception.CampoInesperadoExepcion;
import com.co.prueba.exception.CampoObligatorioExcepcion;
import com.co.prueba.exception.ClienteExisteExcepcion;
import com.co.prueba.exception.ClienteInactivoExcepcion;
import com.co.prueba.exception.ClienteNoEncontradoExcepcion;
import com.co.prueba.exception.CuentaExisteExcepcion;
import com.co.prueba.exception.CuentaNoEncontradaExcepcion;
import com.co.prueba.exception.CuentaSaldoInicialExcepcion;

@Service
public class ErrorService {

	public RespuestaError getError(Exception e) {

		if (e instanceof CampoInesperadoExepcion) {
			return new RespuestaError("CampoInesperadoExepcion", ((CampoInesperadoExepcion) e).getCausaError(),
					HttpStatus.BAD_REQUEST);
		}

		if (e instanceof CampoObligatorioExcepcion) {
			return new RespuestaError("CampoObligatorioExcepcion", ((CampoObligatorioExcepcion) e).getCausaError(),
					HttpStatus.BAD_REQUEST);
		}

		if (e instanceof ClienteExisteExcepcion) {
			return new RespuestaError("ClienteExisteExcepcion", ((ClienteExisteExcepcion) e).getCausaError(),
					HttpStatus.BAD_REQUEST);
		}

		if (e instanceof ClienteInactivoExcepcion) {
			return new RespuestaError("ClienteInactivoExcepcion", ((ClienteInactivoExcepcion) e).getCausaError(),
					HttpStatus.BAD_REQUEST);
		}

		if (e instanceof ClienteNoEncontradoExcepcion) {
			return new RespuestaError("ClienteNoEncontradoExcepcion",
					((ClienteNoEncontradoExcepcion) e).getCausaError(), HttpStatus.BAD_REQUEST);
		}

		if (e instanceof CuentaExisteExcepcion) {
			return new RespuestaError("CuentaExisteExcepcion", ((CuentaExisteExcepcion) e).getCausaError(),
					HttpStatus.BAD_REQUEST);
		}

		if (e instanceof CuentaNoEncontradaExcepcion) {
			return new RespuestaError("CuentaNoEncontradaExcepcion", ((CuentaNoEncontradaExcepcion) e).getCausaError(),
					HttpStatus.BAD_REQUEST);
		}

		if (e instanceof CuentaSaldoInicialExcepcion) {
			return new RespuestaError("CuentaSaldoInicialExcepcion", ((CuentaSaldoInicialExcepcion) e).getCausaError(),
					HttpStatus.BAD_REQUEST);
		}

		Map<String, String> params = new HashMap<>();
		params.put("Exception", e.getClass() + "-" + e.getMessage());
		params.put("Time", LocalDateTime.now().toString());

		return new RespuestaError("Exception", "Unknown RespuestaError", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
