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
import com.co.prueba.exception.CuentaNoEncontradaExcepcion;
import com.co.prueba.exception.CuentaSaldoInicialExcepcion;
import com.co.prueba.service.MovimientosService;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping("/movimientos")
@RestController
@Controller
public class MovimientosController {

	@Autowired
	private MovimientosService movimientosService;

	@GetMapping(value = "/")
	public ResponseEntity<?> consultarMovimiento(@RequestParam Long numeroCuenta) throws CuentaNoEncontradaExcepcion {

		List<MovimientosResponse> listMovimientoResponse = movimientosService.consultarMovimiento(numeroCuenta);

		return new ResponseEntity<>(listMovimientoResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/")
	public ResponseEntity<?> crearMovimiento(@RequestBody MovimientosRequest movimientoRequest)
			throws ParseException, CuentaNoEncontradaExcepcion, CuentaSaldoInicialExcepcion {

		movimientosService.crearMovimiento(movimientoRequest);
		return new ResponseEntity<>("Movimiento creado con exito", HttpStatus.CREATED);

	}

}
