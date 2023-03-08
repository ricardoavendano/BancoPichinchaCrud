package com.co.prueba.service.impl;

import java.util.ArrayList;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.co.prueba.datatransfer.ClienteRequest;
import com.co.prueba.datatransfer.Respuesta;
import com.co.prueba.domain.Cliente;
import com.co.prueba.domain.Persona;
import com.co.prueba.exception.ControlException;
import com.co.prueba.repository.ClienteRepository;
import com.co.prueba.repository.PersonaRepository;
import com.co.prueba.service.ClienteService;

import fj.data.Either;

@Service
public class ClienteServiceImp implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Either<Exception, ClienteRequest> consultarCliente(Long identificacion) {

		Persona persona = personaRepository.findPersonaIdentificacion(identificacion);

		if (null == persona) {
			return Either.left(new ControlException(
					"Cliente con número de identificacion: " + identificacion + " no existe", HttpStatus.BAD_REQUEST));
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

		return Either.right(clienteRequest);
	}

	@Override
	public Either<Exception, Respuesta> crearCliente(ClienteRequest clienteRequest) {

		if (validarRequest(clienteRequest)) {
			return Either.left(new ControlException("Revise el request, todos los campos son obligatorios",
					HttpStatus.BAD_REQUEST));
		}

		if (clienteRequest.getEdad().equals(Long.valueOf(0))) {
			return Either.left(new ControlException("La edad del cliente no puede ser 0", HttpStatus.BAD_REQUEST));
		}

		if (!clienteRequest.getGenero().equals("Masculino") && !clienteRequest.getGenero().equals("Femenino")) {
			return Either.left(new ControlException("El genero debe ser Masculino o Femenino", HttpStatus.BAD_REQUEST));
		}

		Persona persona = personaRepository.findPersonaIdentificacion(clienteRequest.getIdentificacion());

		if (null != persona) {
			return Either.left(new ControlException(
					"Cliente con número de identificacion: " + clienteRequest.getIdentificacion() + " ya existe",
					HttpStatus.BAD_REQUEST));
		}

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

		Respuesta respuesta = new Respuesta("200", "Cliente creado con exito", HttpStatus.OK);
		return Either.right(respuesta);
	}

	@Override
	public Either<Exception, Respuesta> actualizarDireccionCliente(String direccion, Long identificacion) {

		Persona persona = personaRepository.findPersonaIdentificacion(identificacion);

		if (null == persona) {
			return Either.left(new ControlException(
					"Cliente con número de identificacion: " + identificacion + " no existe", HttpStatus.BAD_REQUEST));
		}

		persona.setDireccion(direccion);
		personaRepository.save(persona);

		Respuesta respuesta = new Respuesta("200", "Cliente actualizado con exito", HttpStatus.OK);
		return Either.right(respuesta);
	}

	@Override
	public Either<Exception, Respuesta> actualizarCliente(ClienteRequest clienteRequest) {
		if (validarRequest(clienteRequest)) {
			return Either.left(new ControlException("Revise el request, todos los campos son obligatorios",
					HttpStatus.BAD_REQUEST));
		}

		if (clienteRequest.getEdad().equals(Long.valueOf(0))) {
			return Either.left(new ControlException("La edad del cliente no puede ser 0", HttpStatus.BAD_REQUEST));
		}

		if (!clienteRequest.getGenero().equals("Masculino") && !clienteRequest.getGenero().equals("Femenino")) {
			return Either.left(new ControlException("El genero debe ser Masculino o Femenino", HttpStatus.BAD_REQUEST));
		}

		Persona persona = personaRepository.findPersonaIdentificacion(clienteRequest.getIdentificacion());

		if (null == persona) {
			return Either.left(new ControlException(
					"Cliente con número de identificacion: " + clienteRequest.getIdentificacion() + " no existe",
					HttpStatus.BAD_REQUEST));
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

		Respuesta respuesta = new Respuesta("200", "Cliente actualizado con exito", HttpStatus.OK);
		return Either.right(respuesta);
	}

	@Override
	public Either<Exception, Respuesta> eliminarCliente(Long identificacion) {

		Persona persona = personaRepository.findPersonaIdentificacion(identificacion);

		if (null == persona) {
			return Either.left(new ControlException(
					"Cliente con número de identificacion: " + identificacion + " no existe", HttpStatus.BAD_REQUEST));
		}

		personaRepository.delete(persona);

		Respuesta respuesta = new Respuesta("200", "Cliente eliminado con exito", HttpStatus.OK);
		return Either.right(respuesta);
	}

	private boolean validarRequest(ClienteRequest clienteRequest) {

		return null == clienteRequest.getContrasena() || null == clienteRequest.getDireccion()
				|| null == clienteRequest.getEdad() || null == clienteRequest.getEstado()
				|| null == clienteRequest.getGenero() || null == clienteRequest.getIdentificacion()
				|| null == clienteRequest.getNombre() || null == clienteRequest.getTelefono();
	}

}
