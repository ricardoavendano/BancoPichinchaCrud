package com.co.prueba.service.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.co.prueba.datatransfer.ClienteRequest;
import com.co.prueba.domain.Cliente;
import com.co.prueba.domain.Persona;
import com.co.prueba.exception.CampoInesperadoExepcion;
import com.co.prueba.exception.CampoObligatorioExcepcion;
import com.co.prueba.exception.ClienteExisteExcepcion;
import com.co.prueba.exception.ClienteNoEncontradoExcepcion;
import com.co.prueba.repository.ClienteRepository;
import com.co.prueba.repository.PersonaRepository;
import com.co.prueba.service.impl.ClienteServiceImp;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImpTest {

	@InjectMocks
	private ClienteServiceImp clienteServiceImp;

	@Mock
	private ClienteRepository clienteRepository;

	@Mock
	private PersonaRepository personaRepository;

	@Test
	void deberiaConsultarClienteExitoso() throws ClienteNoEncontradoExcepcion {

		Persona persona = getPersona();

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(persona);

		ClienteRequest res = clienteServiceImp.consultarCliente(Long.valueOf(12345));

		assertEquals(Long.valueOf(12345), res.getIdentificacion());
	}

	@Test
	void deberiaArrojarExcepcionAlConsultarCliente() throws ClienteNoEncontradoExcepcion {

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(null);

		assertThrows(ClienteNoEncontradoExcepcion.class, () -> {
			clienteServiceImp.consultarCliente(Long.valueOf(12345));
		});

	}

	@Test
	void deberiaCrearClienteExitoso()
			throws ClienteExisteExcepcion, CampoObligatorioExcepcion, CampoInesperadoExepcion {

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

		clienteServiceImp.crearCliente(clienteRequest);

	}

	@Test
	void deberiaArrojarExcepcionAlCrearCliente()
			throws ClienteExisteExcepcion, CampoObligatorioExcepcion, CampoInesperadoExepcion {

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setContrasena("contrasena");
		clienteRequest.setDireccion("direccion");
		clienteRequest.setEdad(Long.valueOf(30));
		clienteRequest.setEstado(true);
		clienteRequest.setGenero("Genero");
		clienteRequest.setIdentificacion(Long.valueOf(12345));
		clienteRequest.setNombre("Ricardo");
		clienteRequest.setTelefono(Long.valueOf(98765));

		assertThrows(CampoInesperadoExepcion.class, () -> {
			clienteServiceImp.crearCliente(clienteRequest);
		});

	}

	@Test
	void deberiaActualizarDireccionClienteExitoso() throws ClienteNoEncontradoExcepcion {

		Persona persona = getPersona();

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(persona);
		when(personaRepository.save(any())).thenReturn(persona);

		clienteServiceImp.actualizarDireccionCliente("direccion", Long.valueOf(12345));
	}

	@Test
	void deberiaArrojarExcepcionAlActualizarDireccionCliente() throws ClienteNoEncontradoExcepcion {

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(null);

		assertThrows(ClienteNoEncontradoExcepcion.class, () -> {
			clienteServiceImp.actualizarDireccionCliente("direccion", Long.valueOf(12345));
		});
	}

	@Test
	void deberiaActualizarClienteExitoso() throws ClienteNoEncontradoExcepcion, CampoObligatorioExcepcion,
			CampoInesperadoExepcion, ClienteExisteExcepcion {

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

		clienteServiceImp.actualizarCliente(clienteRequest);
	}

	@Test
	void deberiaArrojarExcepcionAlActualizarCliente() throws ClienteNoEncontradoExcepcion, CampoObligatorioExcepcion,
			CampoInesperadoExepcion, ClienteExisteExcepcion {

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setContrasena("contrasena");
		clienteRequest.setDireccion("direccion");
		clienteRequest.setEdad(Long.valueOf(0));
		clienteRequest.setEstado(true);
		clienteRequest.setGenero("Masculino");
		clienteRequest.setIdentificacion(Long.valueOf(12345));
		clienteRequest.setNombre("Ricardo");
		clienteRequest.setTelefono(Long.valueOf(98765));

		assertThrows(CampoInesperadoExepcion.class, () -> {
			clienteServiceImp.actualizarCliente(clienteRequest);
		});
	}

	@Test
	void deberiaEliminarClienteExitoso() throws ClienteNoEncontradoExcepcion {

		Persona persona = getPersona();

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(persona);
		doNothing().when(personaRepository).delete(any());

		clienteServiceImp.eliminarCliente(Long.valueOf(12345));
	}

	@Test
	void deberiaArrojarExcepcionAlEliminarCliente() throws ClienteNoEncontradoExcepcion {

		when(personaRepository.findPersonaIdentificacion(any())).thenReturn(null);

		assertThrows(ClienteNoEncontradoExcepcion.class, () -> {
			clienteServiceImp.eliminarCliente(Long.valueOf(12345));
		});
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
