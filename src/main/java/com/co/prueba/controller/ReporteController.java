package com.co.prueba.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.prueba.datatransfer.MovimientosResponse;
import com.co.prueba.datatransfer.Respuesta;
import com.co.prueba.service.ErrorService;
import com.co.prueba.service.MovimientosService;

import fj.data.Either;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping("/reportes")
@RestController
@Controller
public class ReporteController {

	@Autowired
	private MovimientosService movimientosService;

	@Autowired
	private ErrorService errorService;

	@GetMapping(value = "/consultarMovimientoFecha")
	public ResponseEntity<?> consultarMovimiento(@RequestParam Long numeroCuenta,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicial,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFinal) throws ParseException {

		Either<Exception, List<MovimientosResponse>> resultEither = movimientosService
				.consultarMovimientoFecha(numeroCuenta, fechaInicial, fechaFinal);

		if (resultEither.isRight()) {
			return new ResponseEntity<>(resultEither.right().value(), HttpStatus.OK);
		}

		Respuesta error = errorService.getError(resultEither.left().value());

		return new ResponseEntity<>(error, error.getStatus());
	}

}
