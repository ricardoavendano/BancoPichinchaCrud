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
import com.co.prueba.datatransfer.Respuesta;
import com.co.prueba.service.ClienteService;
import com.co.prueba.service.ErrorService;

import fj.data.Either;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping("/clientes")
@RestController
@Controller
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ErrorService errorService;

	@GetMapping(value = "/consultarCliente")
	public ResponseEntity<?> consultarCliente(@RequestParam Long identificacion) {

		Either<Exception, ClienteRequest> resultEither = clienteService.consultarCliente(identificacion);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Respuesta error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}

	@PostMapping(value = "/crearCliente")
	public ResponseEntity<?> crearCliente(@RequestBody ClienteRequest clienteRequest) {

		Either<Exception, Respuesta> resultEither = clienteService.crearCliente(clienteRequest);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Respuesta error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}

	@PutMapping(value = "/actualizarCliente")
	public ResponseEntity<?> actualizarCliente(@RequestBody ClienteRequest clienteRequest) {

		Either<Exception, Respuesta> resultEither = clienteService.actualizarCliente(clienteRequest);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Respuesta error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}

	@PatchMapping(value = "/actualizarDireccion")
	public ResponseEntity<?> actualizarDireccion(@RequestParam String direccion, @RequestBody Long identificacion) {

		Either<Exception, Respuesta> resultEither = clienteService.actualizarDireccionCliente(direccion,
				identificacion);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Respuesta error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}

	@DeleteMapping(value = "/eliminarCliente")
	public ResponseEntity<?> eliminarCliente(@RequestParam Long identificacion) {

		Either<Exception, Respuesta> resultEither = clienteService.eliminarCliente(identificacion);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Respuesta error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}
}
