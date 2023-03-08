package com.co.prueba.service.imp;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.co.prueba.datatransfer.ClienteRequest;
import com.co.prueba.datatransfer.Respuesta;
import com.co.prueba.domain.Cliente;
import com.co.prueba.domain.Persona;
import com.co.prueba.repository.ClienteRepository;
import com.co.prueba.repository.PersonaRepository;
import com.co.prueba.service.ErrorService;
import com.co.prueba.service.impl.ClienteServiceImp;

import fj.data.Either;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImpTest {

	@InjectMocks
	private ClienteServiceImp clienteServiceImp;

	@Mock
	private ClienteRepository clienteRepository;

	@Mock
	private PersonaRepository personaRepository;

	@Test
	void deberiaConsultarClienteExitoso() {

		Persona persona = getPersona();

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(persona);

		Either<Exception, ClienteRequest> res = clienteServiceImp.consultarCliente(Long.valueOf(12345));

		assertTrue(res.isRight());
	}

	@Test
	void deberiaArrojarExcepcionAlConsultarCliente() {

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(null);

		Either<Exception, ClienteRequest> res = clienteServiceImp.consultarCliente(Long.valueOf(12345));

		ErrorService errorService = new ErrorService();
		Respuesta error = errorService.getError(res.left().value());

		assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
		assertTrue(res.isLeft());
	}

	@Test
	void deberiaCrearClienteExitoso() {

		Persona persona = getPersona();

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setContrasena("contrasena");
		clienteRequest.setDireccion("direccion");
		clienteRequest.setEdad(Long.valueOf(30));
		clienteRequest.setEstado(true);
		clienteRequest.setGenero("Masculino");
		clienteRequest.setIdentificacion(Long.valueOf(12345));
		clienteRequest.setNombre("Ricardo");
		clienteRequest.setTelefono(Long.valueOf(98765));

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(null);
		when(personaRepository.save(any())).thenReturn(persona);
		when(clienteRepository.save(any())).thenReturn(null);

		Either<Exception, Respuesta> res = clienteServiceImp.crearCliente(clienteRequest);

		assertTrue(res.isRight());
	}

	@Test
	void deberiaArrojarExcepcionAlCrearCliente() {

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setContrasena("contrasena");
		clienteRequest.setDireccion("direccion");
		clienteRequest.setEdad(Long.valueOf(30));
		clienteRequest.setEstado(true);
		clienteRequest.setGenero("Genero");
		clienteRequest.setIdentificacion(Long.valueOf(12345));
		clienteRequest.setNombre("Ricardo");
		clienteRequest.setTelefono(Long.valueOf(98765));

		Either<Exception, Respuesta> res = clienteServiceImp.crearCliente(clienteRequest);

		ErrorService errorService = new ErrorService();
		Respuesta error = errorService.getError(res.left().value());

		assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
		assertTrue(res.isLeft());
	}

	@Test
	void deberiaActualizarDireccionClienteExitoso() {

		Persona persona = getPersona();

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(persona);
		when(personaRepository.save(any())).thenReturn(persona);

		Either<Exception, Respuesta> res = clienteServiceImp.actualizarDireccionCliente("direccion",
				Long.valueOf(12345));

		assertTrue(res.isRight());
	}

	@Test
	void deberiaArrojarExcepcionAlActualizarDireccionCliente() {

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(null);

		Either<Exception, Respuesta> res = clienteServiceImp.actualizarDireccionCliente("direccion",
				Long.valueOf(12345));

		ErrorService errorService = new ErrorService();
		Respuesta error = errorService.getError(res.left().value());

		assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
		assertTrue(res.isLeft());
	}

	@Test
	void deberiaActualizarClienteExitoso() {

		Persona persona = getPersona();

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setContrasena("contrasena");
		clienteRequest.setDireccion("direccion");
		clienteRequest.setEdad(Long.valueOf(30));
		clienteRequest.setEstado(true);
		clienteRequest.setGenero("Masculino");
		clienteRequest.setIdentificacion(Long.valueOf(12345));
		clienteRequest.setNombre("Ricardo");
		clienteRequest.setTelefono(Long.valueOf(98765));

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(persona);
		when(personaRepository.save(any())).thenReturn(persona);

		Either<Exception, Respuesta> res = clienteServiceImp.actualizarCliente(clienteRequest);

		assertTrue(res.isRight());
	}

	@Test
	void deberiaArrojarExcepcionAlActualizarCliente() {

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setContrasena("contrasena");
		clienteRequest.setDireccion("direccion");
		clienteRequest.setEdad(Long.valueOf(0));
		clienteRequest.setEstado(true);
		clienteRequest.setGenero("Masculino");
		clienteRequest.setIdentificacion(Long.valueOf(12345));
		clienteRequest.setNombre("Ricardo");
		clienteRequest.setTelefono(Long.valueOf(98765));

		Either<Exception, Respuesta> res = clienteServiceImp.actualizarCliente(clienteRequest);

		ErrorService errorService = new ErrorService();
		Respuesta error = errorService.getError(res.left().value());

		assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
		assertTrue(res.isLeft());
	}

	@Test
	void deberiaEliminarClienteExitoso() {

		Persona persona = getPersona();

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(persona);
		doNothing().when(personaRepository).delete(any());

		Either<Exception, Respuesta> res = clienteServiceImp.eliminarCliente(Long.valueOf(12345));

		assertTrue(res.isRight());
	}

	@Test
	void deberiaArrojarExcepcionAlEliminarCliente() {

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(null);
		Either<Exception, Respuesta> res = clienteServiceImp.eliminarCliente(Long.valueOf(12345));

		ErrorService errorService = new ErrorService();
		Respuesta error = errorService.getError(res.left().value());

		assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());
		assertTrue(res.isLeft());
	}

	private Persona getPersona() {
		Cliente cliente = new Cliente();
		cliente.setContrasena("contrasena");
		cliente.setCuentaList(new ArrayList<>());
		cliente.setEstado(true);
		cliente.setIdCliente(Long.valueOf(0));

		Persona persona = new Persona();
		persona.setIdPersona(Long.valueOf(0));
		persona.setCliente(cliente);
		persona.setDireccion("direccion");
		persona.setEdad(Long.valueOf(30));
		persona.setGenero("Masculino");
		persona.setIdentificacion(Long.valueOf(12345));
		persona.setNombre("Ricardo");
		persona.setTelefono(Long.valueOf(98765));
		cliente.setidPersonaPK(persona);

		return persona;
	}

}
