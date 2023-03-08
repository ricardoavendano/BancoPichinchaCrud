package com.co.prueba.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.co.prueba.datatransfer.MovimientosRequest;
import com.co.prueba.datatransfer.MovimientosResponse;
import com.co.prueba.datatransfer.Respuesta;

import fj.data.Either;

@Service
public interface MovimientosService {

	public Either<Exception, List<MovimientosResponse>> consultarMovimiento(Long numeroCuenta);

	public Either<Exception, List<MovimientosResponse>> consultarMovimientoFecha(Long numeroCuenta, Date fechaInicio,
			Date fechaFin) throws ParseException;

	public Either<Exception, Respuesta> crearMovimiento(MovimientosRequest movimientoRequest) throws ParseException;

}
