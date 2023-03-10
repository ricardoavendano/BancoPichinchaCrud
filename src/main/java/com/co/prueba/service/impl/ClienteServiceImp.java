package com.co.prueba.service.impl;

import java.util.ArrayList;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.prueba.datatransfer.ClienteRequest;
import com.co.prueba.domain.Cliente;
import com.co.prueba.domain.Persona;
import com.co.prueba.exception.CampoInesperadoExepcion;
import com.co.prueba.exception.CampoObligatorioExcepcion;
import com.co.prueba.exception.ClienteExisteExcepcion;
import com.co.prueba.exception.ClienteNoEncontradoExcepcion;
import com.co.prueba.repository.ClienteRepository;
import com.co.prueba.repository.PersonaRepository;
import com.co.prueba.service.ClienteService;

@Service
public class ClienteServiceImp implements ClienteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImp.class);

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public ClienteRequest consultarCliente(Long identificacion) throws ClienteNoEncontradoExcepcion {

		Persona persona = personaRepository.findPersonaIdentificacion(identificacion);

		if (null == persona) {
			throw new ClienteNoEncontradoExcepcion(
					"Cliente con número de identificacion: " + identificacion + " no existe");
		}

		ClienteRequest clienteRequest = new ClienteRequest();
		clienteRequest.setContrasena(persona.getCliente().getContrasena());
		clienteRequest.setEstado(persona.getCliente().getEstado());
		clienteRequest.setDireccion(persona.getDireccion());
		clienteRequest.setEdad(persona.getEdad());
		clienteRequest.setGenero(persona.getGenero());
		clienteRequest.setIdentificacion(persona.getIdentificacion());
		clienteRequest.setNombre(persona.getNombre());
		clienteRequest.setTelefono(persona.getTelefono());

		return clienteRequest;
	}

	@Override
	@Transactional
	public void crearCliente(ClienteRequest clienteRequest)
			throws ClienteExisteExcepcion, CampoObligatorioExcepcion, CampoInesperadoExepcion {

		validarCamposCliente(clienteRequest);

		Persona persona = personaRepository.findPersonaIdentificacion(clienteRequest.getIdentificacion());

		if (null != persona) {
			throw new ClienteExisteExcepcion(
					"Cliente con número de identificacion: " + clienteRequest.getIdentificacion() + " ya existe");
		}

		mapearCliente(clienteRequest);
		LOGGER.info("Cliente con identificación {} creado con exito", clienteRequest.getIdentificacion());
	}

	@Override
	@Transactional
	public void actualizarDireccionCliente(String direccion, Long identificacion) throws ClienteNoEncontradoExcepcion {

		Persona persona = personaRepository.findPersonaIdentificacion(identificacion);

		if (null == persona) {
			throw new ClienteNoEncontradoExcepcion(
					"Cliente con número de identificacion: " + identificacion + " no existe");
		}

		persona.setDireccion(direccion);
		personaRepository.save(persona);
		LOGGER.info("Dirección {} actualizada con exito para el cliente {}", direccion, identificacion);
	}

	@Override
	@Transactional
	public void actualizarCliente(ClienteRequest clienteRequest) throws ClienteNoEncontradoExcepcion,
			CampoObligatorioExcepcion, CampoInesperadoExepcion, ClienteExisteExcepcion {

		validarCamposCliente(clienteRequest);

		Persona persona = personaRepository.findPersonaIdentificacion(clienteRequest.getIdentificacion());

		if (null == persona) {
			throw new ClienteNoEncontradoExcepcion(
					"Cliente con número de identificacion: " + clienteRequest.getIdentificacion() + " no existe");
		}

		persona.setDireccion(clienteRequest.getDireccion());
		persona.setEdad(clienteRequest.getEdad());
		persona.setGenero(clienteRequest.getGenero());
		persona.setIdentificacion(clienteRequest.getIdentificacion());
		persona.setNombre(clienteRequest.getNombre());
		persona.setTelefono(clienteRequest.getTelefono());

		persona.getCliente()
				.setContrasena(Base64.getEncoder().encodeToString(clienteRequest.getContrasena().getBytes()));
		persona.getCliente().setEstado(clienteRequest.getEstado());
		persona.getCliente().setidPersonaPK(persona);
		personaRepository.save(persona);
		LOGGER.info("Cliente con identificación {} actualizado con exito", clienteRequest.getIdentificacion());

	}

	@Override
	@Transactional
	public void eliminarCliente(Long identificacion) throws ClienteNoEncontradoExcepcion {

		Persona persona = personaRepository.findPersonaIdentificacion(identificacion);

		if (null == persona) {
			throw new ClienteNoEncontradoExcepcion(
					"Cliente con número de identificacion: " + identificacion + " no existe");
		}

		personaRepository.delete(persona);

		LOGGER.info("Cliente con identificación {} eliminado con exito", identificacion);

	}

	private void mapearCliente(ClienteRequest clienteRequest) {
		Persona personaNueva = new Persona();
		personaNueva.setIdPersona(Long.valueOf(0));
		personaNueva.setDireccion(clienteRequest.getDireccion());
		personaNueva.setEdad(clienteRequest.getEdad());
		personaNueva.setGenero(clienteRequest.getGenero());
		personaNueva.setIdentificacion(clienteRequest.getIdentificacion());
		personaNueva.setNombre(clienteRequest.getNombre());
		personaNueva.setTelefono(clienteRequest.getTelefono());

		Persona personaGuardada = personaRepository.save(personaNueva);

		Cliente clienteNuevo = new Cliente();
		clienteNuevo.setIdCliente(Long.valueOf(0));
		clienteNuevo.setContrasena(Base64.getEncoder().encodeToString(clienteRequest.getContrasena().getBytes()));
		clienteNuevo.setCuentaList(new ArrayList<>());
		clienteNuevo.setEstado(clienteRequest.getEstado());
		clienteNuevo.setidPersonaPK(personaGuardada);
		clienteRepository.save(clienteNuevo);
	}

	private void validarCamposCliente(ClienteRequest clienteRequest)
			throws CampoObligatorioExcepcion, CampoInesperadoExepcion {
		if (validarRequest(clienteRequest)) {

			throw new CampoObligatorioExcepcion("Revise el request, todos los campos son obligatorios");
		}

		if (clienteRequest.getEdad().equals(Long.valueOf(0))) {

			throw new CampoInesperadoExepcion("La edad del cliente no puede ser 0");
		}

		if (!clienteRequest.getGenero().equals("Masculino") && !clienteRequest.getGenero().equals("Femenino")) {
			throw new CampoInesperadoExepcion("El genero debe ser Masculino o Femenino");
		}
	}

	private boolean validarRequest(ClienteRequest clienteRequest) {

		return null == clienteRequest.getContrasena() || null == clienteRequest.getDireccion()
				|| null == clienteRequest.getEdad() || null == clienteRequest.getEstado()
				|| null == clienteRequest.getGenero() || null == clienteRequest.getIdentificacion()
				|| null == clienteRequest.getNombre() || null == clienteRequest.getTelefono();
	}

}
