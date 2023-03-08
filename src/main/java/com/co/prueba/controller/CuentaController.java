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

import com.co.prueba.datatransfer.CuentaRequest;
import com.co.prueba.datatransfer.Respuesta;
import com.co.prueba.service.CuentaService;
import com.co.prueba.service.ErrorService;

import fj.data.Either;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping("/cuentas")
@RestController
@Controller
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;

	@Autowired
	private ErrorService errorService;

	@GetMapping(value = "/consultarCuenta")
	public ResponseEntity<?> consultarCuenta(@RequestParam Long numeroCuenta) {

		Either<Exception, CuentaRequest> resultEither = cuentaService.consultarCuenta(numeroCuenta);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Respuesta error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}

	@PostMapping(value = "/crearCuenta")
	public ResponseEntity<?> crearCuenta(@RequestBody CuentaRequest cuentaRequest) {

		Either<Exception, Respuesta> resultEither = cuentaService.crearCuenta(cuentaRequest);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Respuesta error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}

	@PutMapping(value = "/actualizarCuenta")
	public ResponseEntity<?> actualizarCuenta(@RequestBody CuentaRequest cuentaRequest) {

		Either<Exception, Respuesta> resultEither = cuentaService.actualizarCuenta(cuentaRequest);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Respuesta error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}

	@PatchMapping(value = "/actualizarEstado")
	public ResponseEntity<?> actualizarEstado(@RequestParam Boolean estado, @RequestBody Long numeroCuenta) {

		Either<Exception, Respuesta> resultEither = cuentaService.actualizarEstadoCuenta(estado, numeroCuenta);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Respuesta error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}

	@DeleteMapping(value = "/eliminarCuenta")
	public ResponseEntity<?> eliminarCuenta(@RequestParam Long numeroCuenta) {

		Either<Exception, Respuesta> resultEither = cuentaService.eliminarCuenta(numeroCuenta);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Respuesta error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}
}
