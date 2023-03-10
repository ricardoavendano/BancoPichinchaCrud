package com.co.prueba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.co.prueba.datatransfer.RespuestaError;
import com.co.prueba.exception.CampoInesperadoExepcion;
import com.co.prueba.exception.CampoObligatorioExcepcion;
import com.co.prueba.exception.ClienteExisteExcepcion;
import com.co.prueba.exception.ClienteInactivoExcepcion;
import com.co.prueba.exception.ClienteNoEncontradoExcepcion;
import com.co.prueba.exception.CuentaExisteExcepcion;
import com.co.prueba.exception.CuentaNoEncontradaExcepcion;
import com.co.prueba.exception.CuentaSaldoInicialExcepcion;

@ExtendWith(MockitoExtension.class)
class ErrorServiceTest {

	@InjectMocks
	private ErrorService errorService;

	@Test
	void campoInesperadoExepcion() {

		RespuestaError error = errorService.getError(new CampoInesperadoExepcion(""));
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
	}

	@Test
	void campoObligatorioExcepcion() {

		RespuestaError error = errorService.getError(new CampoObligatorioExcepcion(""));
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
	}

	@Test
	void clienteExisteExcepcion() {

		RespuestaError error = errorService.getError(new ClienteExisteExcepcion(""));
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
	}

	@Test
	void clienteInactivoExcepcion() {

		RespuestaError error = errorService.getError(new ClienteInactivoExcepcion(""));
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
	}

	@Test
	void clienteNoEncontradoExcepcion() {

		RespuestaError error = errorService.getError(new ClienteNoEncontradoExcepcion(""));
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
	}

	@Test
	void cuentaExisteExcepcion() {

		RespuestaError error = errorService.getError(new CuentaExisteExcepcion(""));
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
	}

	@Test
	void cuentaNoEncontradaExcepcion() {

		RespuestaError error = errorService.getError(new CuentaNoEncontradaExcepcion(""));
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
	}

	@Test
	void cuentaSaldoInicialExcepcion() {

		RespuestaError error = errorService.getError(new CuentaSaldoInicialExcepcion(""));
		assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
	}

	@Test
	void errorDesconocido() {
		RespuestaError error = errorService.getError(new Exception());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, error.getStatus());
	}
}
