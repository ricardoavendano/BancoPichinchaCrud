package com.co.prueba.service;

import org.springframework.stereotype.Service;

import com.co.prueba.datatransfer.CuentaRequest;
import com.co.prueba.datatransfer.Respuesta;

import fj.data.Either;

@Service
public interface CuentaService {

	public Either<Exception, CuentaRequest> consultarCuenta(Long numeroCuenta);

	public Either<Exception, Respuesta> crearCuenta(CuentaRequest cuentaRequest);

	public Either<Exception, Respuesta> actualizarEstadoCuenta(Boolean estado, Long numeroCuenta);

	public Either<Exception, Respuesta> actualizarCuenta(CuentaRequest cuentaRequest);

	public Either<Exception, Respuesta> eliminarCuenta(Long numeroCuenta);

}
