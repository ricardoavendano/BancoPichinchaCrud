package com.co.prueba.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.prueba.datatransfer.MovimientosRequest;
import com.co.prueba.datatransfer.MovimientosResponse;
import com.co.prueba.datatransfer.Respuesta;
import com.co.prueba.service.ErrorService;
import com.co.prueba.service.MovimientosService;

import fj.data.Either;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping("/movimientos")
@RestController
@Controller
public class MovimientosController {

	@Autowired
	private MovimientosService movimientosService;

	@Autowired
	private ErrorService errorService;

	@GetMapping(value = "/consultarMovimiento")
	public ResponseEntity<?> consultarMovimiento(@RequestParam Long numeroCuenta) {

		Either<Exception, List<MovimientosResponse>> resultEither = movimientosService
				.consultarMovimiento(numeroCuenta);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Respuesta error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}

	@PostMapping(value = "/crearMovimiento")
	public ResponseEntity<?> crearMovimiento(@RequestBody MovimientosRequest movimientoRequest) throws ParseException {

		Either<Exception, Respuesta> resultEither = movimientosService.crearMovimiento(movimientoRequest);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Respuesta error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}

}
