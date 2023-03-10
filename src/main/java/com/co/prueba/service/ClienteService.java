package com.co.prueba.service;

import org.springframework.stereotype.Service;

import com.co.prueba.datatransfer.ClienteRequest;
import com.co.prueba.exception.CampoInesperadoExepcion;
import com.co.prueba.exception.CampoObligatorioExcepcion;
import com.co.prueba.exception.ClienteExisteExcepcion;
import com.co.prueba.exception.ClienteNoEncontradoExcepcion;

@Service
public interface ClienteService {

	public ClienteRequest consultarCliente(Long identificacion) throws ClienteNoEncontradoExcepcion;

	public void crearCliente(ClienteRequest clienteRequest)
			throws ClienteExisteExcepcion, CampoObligatorioExcepcion, CampoInesperadoExepcion;

	public void actualizarDireccionCliente(String direccion, Long identificacion) throws ClienteNoEncontradoExcepcion;

	public void actualizarCliente(ClienteRequest clienteRequest) throws ClienteNoEncontradoExcepcion,
			CampoObligatorioExcepcion, CampoInesperadoExepcion, ClienteExisteExcepcion;

	public void eliminarCliente(Long identificacion) throws ClienteNoEncontradoExcepcion;
}
