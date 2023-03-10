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
import com.co.prueba.exception.CampoInesperadoExepcion;
import com.co.prueba.exception.CampoObligatorioExcepcion;
import com.co.prueba.exception.ClienteInactivoExcepcion;
import com.co.prueba.exception.ClienteNoEncontradoExcepcion;
import com.co.prueba.exception.CuentaExisteExcepcion;
import com.co.prueba.exception.CuentaNoEncontradaExcepcion;
import com.co.prueba.exception.CuentaSaldoInicialExcepcion;
import com.co.prueba.service.CuentaService;

@EnableAutoConfiguration
@CrossOrigin(origins = "*")
@RequestMapping("/cuentas")
@RestController
@Controller
public class CuentaController {

	@Autowired
	private CuentaService cuentaService;

	@GetMapping(value = "/")
	public ResponseEntity<?> consultarCuenta(@RequestParam Long numeroCuenta) throws CuentaNoEncontradaExcepcion {

		CuentaRequest cuentaRequest = cuentaService.consultarCuenta(numeroCuenta);
		return new ResponseEntity<>(cuentaRequest, HttpStatus.OK);
	}

	@PostMapping(value = "/")
	public ResponseEntity<?> crearCuenta(@RequestBody CuentaRequest cuentaRequest) throws CampoObligatorioExcepcion,
			ClienteNoEncontradoExcepcion, ClienteInactivoExcepcion, CuentaExisteExcepcion, CampoInesperadoExepcion {

		cuentaService.crearCuenta(cuentaRequest);
		return new ResponseEntity<>("Cuenta creada con exito", HttpStatus.CREATED);
	}

	@PutMapping(value = "/")
	public ResponseEntity<?> actualizarCuenta(@RequestBody CuentaRequest cuentaRequest)
			throws CampoObligatorioExcepcion, CuentaNoEncontradaExcepcion, ClienteNoEncontradoExcepcion,
			CuentaSaldoInicialExcepcion, CampoInesperadoExepcion {

		cuentaService.actualizarCuenta(cuentaRequest);
		return new ResponseEntity<>("Cuenta actualizada con exito", HttpStatus.OK);
	}

	@PatchMapping(value = "/actualizarEstado")
	public ResponseEntity<?> actualizarEstado(@RequestParam Boolean estado, @RequestBody Long numeroCuenta)
			throws CuentaNoEncontradaExcepcion {

		cuentaService.actualizarEstadoCuenta(estado, numeroCuenta);
		return new ResponseEntity<>("Estado de la cuenta actualizado con exito", HttpStatus.OK);
	}

	@DeleteMapping(value = "/")
	public ResponseEntity<?> eliminarCuenta(@RequestParam Long numeroCuenta) throws CuentaNoEncontradaExcepcion {

		cuentaService.eliminarCuenta(numeroCuenta);
		return new ResponseEntity<>("Cuenta eliminada con exito", HttpStatus.OK);
	}
}
