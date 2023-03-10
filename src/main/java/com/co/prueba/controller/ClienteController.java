package com.co.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.prueba.datatransfer.ClienteRequest;
import com.co.prueba.exception.CampoInesperadoExepcion;
import com.co.prueba.exception.CampoObligatorioExcepcion;
import com.co.prueba.exception.ClienteExisteExcepcion;
import com.co.prueba.exception.ClienteNoEncontradoExcepcion;
import com.co.prueba.service.ClienteService;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
@RestController
@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping(value = "/")
	public ResponseEntity<?> consultarCliente(@RequestParam Long identificacion) throws ClienteNoEncontradoExcepcion {

		ClienteRequest clienteRequest = clienteService.consultarCliente(identificacion);
		return new ResponseEntity<>(clienteRequest, HttpStatus.OK);

	}

	@PostMapping(value = "/")
	public ResponseEntity<?> crearCliente(@RequestBody ClienteRequest clienteRequest)
			throws ClienteExisteExcepcion, CampoObligatorioExcepcion, CampoInesperadoExepcion {

		clienteService.crearCliente(clienteRequest);
		return new ResponseEntity<>("Cliente creado con exito", HttpStatus.CREATED);
	}

	@PutMapping(value = "/")
	public ResponseEntity<?> actualizarCliente(@RequestBody ClienteRequest clienteRequest)
			throws ClienteNoEncontradoExcepcion, CampoObligatorioExcepcion, CampoInesperadoExepcion,
			ClienteExisteExcepcion {

		clienteService.actualizarCliente(clienteRequest);
		return new ResponseEntity<>("Cliente actualizado con exito", HttpStatus.OK);
	}

	@PatchMapping(value = "/actualizarDireccion")
	public ResponseEntity<?> actualizarDireccion(@RequestParam String direccion, @RequestBody Long identificacion)
			throws ClienteNoEncontradoExcepcion {

		clienteService.actualizarDireccionCliente(direccion, identificacion);

		return new ResponseEntity<>("Dirección del cliente actualizada con exito", HttpStatus.OK);
	}

	@DeleteMapping(value = "/")
	public ResponseEntity<?> eliminarCliente(@RequestParam Long identificacion) throws ClienteNoEncontradoExcepcion {

		clienteService.eliminarCliente(identificacion);

		return new ResponseEntity<>("Cliente eliminado con exito", HttpStatus.OK);
	}
}
