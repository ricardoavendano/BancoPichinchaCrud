package com.co.prueba.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.co.prueba.datatransfer.ClienteRequest;
import com.co.prueba.datatransfer.Respuesta;
import com.co.prueba.exception.ControlException;
import com.co.prueba.service.ClienteService;
import com.co.prueba.service.ErrorService;

import fj.data.Either;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

	@InjectMocks
	private ClienteController clienteController;

	@Mock
	private ClienteService clienteService;

	@Mock
	private ErrorService errorService;

	@Test
	void deberiaCrearClienteExitoso() {

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setIdentificacion(Long.valueOf(1234));

		Respuesta respuesta = new Respuesta("", "", HttpStatus.OK);

		Either<Exception, Respuesta> resultEither = Either.right(respuesta);
		when(clienteService.crearCliente(any())).thenReturn(resultEither);

		ResponseEntity<?> res = clienteController.crearCliente(clienteRequest);

		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	void deberiaArrojarExcepcionAlCrearCliente() {

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setIdentificacion(Long.valueOf(1234));

		Either<Exception, Respuesta> resultEither = Either.left(new ControlException("", HttpStatus.BAD_REQUEST));

		Respuesta error = new Respuesta("", "", HttpStatus.BAD_REQUEST);

		when(errorService.getError(any())).thenReturn(error);
		when(clienteService.crearCliente(any())).thenReturn(resultEither);

		ResponseEntity<?> res = clienteController.crearCliente(clienteRequest);

		assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
	}

	@Test
	void deberiaEliminarClienteExitoso() {

		Respuesta respuesta = new Respuesta("", "", HttpStatus.OK);

		Either<Exception, Respuesta> resultEither = Either.right(respuesta);
		when(clienteService.eliminarCliente(any())).thenReturn(resultEither);

		ResponseEntity<?> res = clienteController.eliminarCliente(Long.valueOf(0));

		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	void deberiaArrojarExcepcionAlEliminarCliente() {

		Either<Exception, Respuesta> resultEither = Either.left(new ControlException("", HttpStatus.BAD_REQUEST));

		Respuesta error = new Respuesta("", "", HttpStatus.BAD_REQUEST);

		when(errorService.getError(any())).thenReturn(error);
		when(clienteService.eliminarCliente(any())).thenReturn(resultEither);

		ResponseEntity<?> res = clienteController.eliminarCliente(Long.valueOf(0));

		assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
	}

	@Test
	void deberiaConsultarClienteExitoso() {

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setIdentificacion(Long.valueOf(1234));

		Either<Exception, ClienteRequest> resultEither = Either.right(clienteRequest);
		when(clienteService.consultarCliente(anyLong())).thenReturn(resultEither);

		ResponseEntity<?> res = clienteController.consultarCliente(Long.valueOf(1234));

		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	void deberiaArrojarExcepcionAlConsultarCliente() {

		Either<Exception, ClienteRequest> resultEither = Either.left(new ControlException("", HttpStatus.BAD_REQUEST));
		Respuesta error = new Respuesta("", "", HttpStatus.BAD_REQUEST);

		when(errorService.getError(any())).thenReturn(error);
		when(clienteService.consultarCliente(anyLong())).thenReturn(resultEither);
		ResponseEntity<?> res = clienteController.consultarCliente(Long.valueOf(0));

		assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
	}

	@Test
	void deberiaActualizarClineteExitoso() throws ParseException {

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setIdentificacion(Long.valueOf(1234));

		Respuesta respuesta = new Respuesta("", "", HttpStatus.OK);

		Either<Exception, Respuesta> resultEither = Either.right(respuesta);
		when(clienteService.actualizarCliente(any())).thenReturn(resultEither);

		ResponseEntity<?> res = clienteController.actualizarCliente(clienteRequest);

		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	void deberiaArrojarExcepcionAlActualizarCliente() throws ParseException {

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setIdentificacion(null);

		Either<Exception, Respuesta> resultEither = Either.left(new ControlException("", HttpStatus.BAD_REQUEST));

		Respuesta error = new Respuesta("", "", HttpStatus.BAD_REQUEST);

		when(errorService.getError(any())).thenReturn(error);
		when(clienteService.actualizarCliente(any())).thenReturn(resultEither);

		ResponseEntity<?> res = clienteController.actualizarCliente(clienteRequest);

		assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
	}

	@Test
	void deberiaActualizarDireccionExitoso() {

		Respuesta respuesta = new Respuesta("", "", HttpStatus.OK);

		Either<Exception, Respuesta> resultEither = Either.right(respuesta);
		when(clienteService.actualizarDireccionCliente(anyString(), anyLong())).thenReturn(resultEither);

		ResponseEntity<?> res = clienteController.actualizarDireccion("direccion 1", Long.valueOf(0));

		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	void deberiaArrojarExcepcionAlAActualizarDireccion() throws ParseException {

		Either<Exception, Respuesta> resultEither = Either.left(new ControlException("", HttpStatus.BAD_REQUEST));

		Respuesta error = new Respuesta("", "", HttpStatus.BAD_REQUEST);

		when(errorService.getError(any())).thenReturn(error);
		when(clienteService.actualizarDireccionCliente(anyString(), anyLong())).thenReturn(resultEither);

		ResponseEntity<?> res = clienteController.actualizarDireccion("direccion 1", Long.valueOf(0));

		assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
	}

}
