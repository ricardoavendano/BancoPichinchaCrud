package com.co.prueba.service;

import org.springframework.stereotype.Service;

import com.co.prueba.datatransfer.ClienteRequest;
import com.co.prueba.datatransfer.Respuesta;

import fj.data.Either;

@Service
public interface ClienteService {

	public Either<Exception, ClienteRequest> consultarCliente(Long identificacion);

	public Either<Exception, Respuesta> crearCliente(ClienteRequest clienteRequest);

	public Either<Exception, Respuesta> actualizarDireccionCliente(String direccion, Long identificacion);

	public Either<Exception, Respuesta> actualizarCliente(ClienteRequest clienteRequest);

	public Either<Exception, Respuesta> eliminarCliente(Long identificacion);
}
