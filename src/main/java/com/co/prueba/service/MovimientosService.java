package com.co.prueba.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.co.prueba.datatransfer.MovimientosRequest;
import com.co.prueba.datatransfer.MovimientosResponse;
import com.co.prueba.exception.CuentaNoEncontradaExcepcion;
import com.co.prueba.exception.CuentaSaldoInicialExcepcion;

@Service
public interface MovimientosService {

	public List<MovimientosResponse> consultarMovimiento(Long numeroCuenta) throws CuentaNoEncontradaExcepcion;

	public List<MovimientosResponse> consultarMovimientoFecha(Long numeroCuenta, Date fechaInicio, Date fechaFin)
			throws ParseException, CuentaNoEncontradaExcepcion;

	public void crearMovimiento(MovimientosRequest movimientoRequest)
			throws ParseException, CuentaNoEncontradaExcepcion, CuentaSaldoInicialExcepcion;

}
