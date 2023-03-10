package com.co.prueba.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
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
import com.co.prueba.exception.CampoInesperadoExepcion;
import com.co.prueba.exception.CampoObligatorioExcepcion;
import com.co.prueba.exception.ClienteExisteExcepcion;
import com.co.prueba.exception.ClienteNoEncontradoExcepcion;
import com.co.prueba.service.ClienteService;
import com.co.prueba.service.ErrorService;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

	@InjectMocks
	private ClienteController clienteController;

	@Mock
	private ClienteService clienteService;

	@Mock
	private ErrorService errorService;

	@Test
	void deberiaCrearClienteExitoso()
			throws ClienteExisteExcepcion, CampoObligatorioExcepcion, CampoInesperadoExepcion {

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setIdentificacion(Long.valueOf(1234));

		doNothing().when(clienteService).crearCliente(any());

		ResponseEntity<?> res = clienteController.crearCliente(clienteRequest);

		assertEquals(HttpStatus.CREATED, res.getStatusCode());
	}

	@Test
	void deberiaEliminarClienteExitoso() throws ClienteNoEncontradoExcepcion {

		doNothing().when(clienteService).eliminarCliente(any());

		ResponseEntity<?> res = clienteController.eliminarCliente(Long.valueOf(0));

		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	void deberiaConsultarClienteExitoso() throws ClienteNoEncontradoExcepcion {

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setIdentificacion(Long.valueOf(1234));

		when(clienteService.consultarCliente(anyLong())).thenReturn(clienteRequest);

		ResponseEntity<?> res = clienteController.consultarCliente(Long.valueOf(1234));

		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	void deberiaActualizarClineteExitoso() throws ParseException, ClienteNoEncontradoExcepcion,
			CampoObligatorioExcepcion, CampoInesperadoExepcion, ClienteExisteExcepcion {

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setIdentificacion(Long.valueOf(1234));

		doNothing().when(clienteService).actualizarCliente(any());

		ResponseEntity<?> res = clienteController.actualizarCliente(clienteRequest);

		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@Test
	void deberiaActualizarDireccionExitoso() throws ClienteNoEncontradoExcepcion {

		doNothing().when(clienteService).actualizarDireccionCliente(anyString(), anyLong());

		ResponseEntity<?> res = clienteController.actualizarDireccion("direccion 1", Long.valueOf(0));

		assertEquals(HttpStatus.OK, res.getStatusCode());
	}

}
