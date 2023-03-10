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
import com.co.prueba.exception.CuentaNoEncontradaExcepcion;
import com.co.prueba.service.MovimientosService;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping("/reportes")
@RestController
@Controller
public class ReporteController {

	@Autowired
	private MovimientosService movimientosService;

	@GetMapping(value = "/consultarMovimientoFecha")
	public ResponseEntity<?> consultarMovimiento(@RequestParam Long numeroCuenta,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicial,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFinal)
			throws ParseException, CuentaNoEncontradaExcepcion {

		List<MovimientosResponse> listMovimientoResponse = movimientosService.consultarMovimientoFecha(numeroCuenta,
				fechaInicial, fechaFinal);

		return new ResponseEntity<>(listMovimientoResponse, HttpStatus.OK);
	}

}
