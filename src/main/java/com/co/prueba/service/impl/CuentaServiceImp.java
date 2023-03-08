package com.co.prueba.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.co.prueba.datatransfer.CuentaRequest;
import com.co.prueba.datatransfer.Respuesta;
import com.co.prueba.domain.Cliente;
import com.co.prueba.domain.Cuenta;
import com.co.prueba.domain.Persona;
import com.co.prueba.exception.ControlException;
import com.co.prueba.repository.CuentaRepository;
import com.co.prueba.repository.PersonaRepository;
import com.co.prueba.service.CuentaService;

import fj.data.Either;

@Service
public class CuentaServiceImp implements CuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Either<Exception, CuentaRequest> consultarCuenta(Long numeroCuenta) {

		Cuenta cuenta = cuentaRepository.findCuenta(numeroCuenta);
		if (null == cuenta) {
			return Either.left(
					new ControlException("Número de cuenta: " + numeroCuenta + " no existe", HttpStatus.BAD_REQUEST));
		}

		CuentaRequest cuentaRequest = new CuentaRequest();
		cuentaRequest.setEstadoCuenta(cuenta.getEstado());
		cuentaRequest.setIdentificacionCliente(cuenta.getidClientePK().getidPersonaPK().getIdentificacion());
		cuentaRequest.setNombreCliente(cuenta.getidClientePK().getidPersonaPK().getNombre());
		cuentaRequest.setNumeroCuenta(cuenta.getNumeroCuenta());
		cuentaRequest.setSaldoInicialCuenta(cuenta.getSaldoInicial());
		cuentaRequest.setTipoCuenta(cuenta.getTipoCuenta());

		return Either.right(cuentaRequest);
	}

	@Override
	public Either<Exception, Respuesta> crearCuenta(CuentaRequest cuentaRequest) {

		if (validarRequest(cuentaRequest)) {
			return Either.left(new ControlException("Revise el request, todos los campos son obligatorios",
					HttpStatus.BAD_REQUEST));
		}

		Persona persona = personaRepository.findPersonaIdentificacion(cuentaRequest.getIdentificacionCliente());

		if (null == persona) {
			return Either.left(new ControlException(
					"Cliente con número de identificacion: " + cuentaRequest.getIdentificacionCliente() + " no existe",
					HttpStatus.BAD_REQUEST));
		}

		if (Boolean.FALSE.equals(persona.getCliente().getEstado())) {
			return Either.left(new ControlException("Cliente se encuentra inactivo para crear cuentas", HttpStatus.OK));
		}

		Cuenta cuenta = cuentaRepository.findCuenta(cuentaRequest.getNumeroCuenta());

		if (null != cuenta) {
			return Either.left(new ControlException(
					"Número de cuenta: " + cuentaRequest.getNumeroCuenta() + " ya existe", HttpStatus.BAD_REQUEST));
		}

		if (cuentaRequest.getTipoCuenta().equals("Ahorros") || cuentaRequest.getTipoCuenta().equals("Corriente")) {

			Cuenta cuentaNueva = new Cuenta();
			cuentaNueva.setIdCuenta(Long.valueOf(0));
			cuentaNueva.setEstado(cuentaRequest.getEstadoCuenta());

			Cliente cliente = new Cliente();
			cliente.setIdCliente(persona.getCliente().getIdCliente());
			cuentaNueva.setidClientePK(cliente);

			cuentaNueva.setNumeroCuenta(cuentaRequest.getNumeroCuenta());
			cuentaNueva.setSaldoInicial(cuentaRequest.getSaldoInicialCuenta());
			cuentaNueva.setTipoCuenta(cuentaRequest.getTipoCuenta());
			cuentaNueva.setMovimientoList(new ArrayList<>());

			cuentaRepository.save(cuentaNueva);

			Respuesta respuesta = new Respuesta("200", "Cuenta creada con exito", HttpStatus.OK);
			return Either.right(respuesta);

		} else {
			return Either.left(new ControlException("Las tipos de cuentas permitidas son: Ahorros, Corriente",
					HttpStatus.BAD_REQUEST));
		}

	}

	@Override
	public Either<Exception, Respuesta> actualizarCuenta(CuentaRequest cuentaRequest) {

		if (validarRequest(cuentaRequest)) {
			return Either.left(new ControlException("Revise el request, todos los campos son obligatorios",
					HttpStatus.BAD_REQUEST));
		}

		Cuenta cuenta = cuentaRepository.findCuenta(cuentaRequest.getNumeroCuenta());
		if (null == cuenta) {
			return Either.left(new ControlException(
					"Número de cuenta: " + cuentaRequest.getNumeroCuenta() + " no existe", HttpStatus.BAD_REQUEST));
		}

		Persona persona = personaRepository.findPersonaIdentificacion(cuentaRequest.getIdentificacionCliente());

		if (null == persona) {
			return Either.left(new ControlException(
					"Cliente con número de identificacion: " + cuentaRequest.getIdentificacionCliente() + " no existe",
					HttpStatus.BAD_REQUEST));
		}

		if (!cuentaRequest.getSaldoInicialCuenta().equals(cuenta.getSaldoInicial())
				&& !cuenta.getMovimientoList().isEmpty()) {
			return Either.left(new ControlException("No se puede modificar el saldo inicial porque tiene movimientos",
					HttpStatus.BAD_REQUEST));
		}

		if (cuentaRequest.getTipoCuenta().equals("Ahorros") || cuentaRequest.getTipoCuenta().equals("Corriente")) {

			cuenta.setEstado(cuentaRequest.getEstadoCuenta());
			cuenta.setNumeroCuenta(cuentaRequest.getNumeroCuenta());
			cuenta.setSaldoInicial(cuentaRequest.getSaldoInicialCuenta());
			cuenta.setTipoCuenta(cuentaRequest.getTipoCuenta());

			Cliente cliente = new Cliente();
			cliente.setIdCliente(persona.getCliente().getIdCliente());
			cuenta.setidClientePK(cliente);

			cuentaRepository.save(cuenta);

			Respuesta respuesta = new Respuesta("200", "Cuenta actualizada con exito", HttpStatus.OK);
			return Either.right(respuesta);

		} else {
			return Either.left(new ControlException("Las tipos de cuentas permitidas son: Ahorros, Corriente",
					HttpStatus.BAD_REQUEST));
		}

	}

	@Override
	public Either<Exception, Respuesta> actualizarEstadoCuenta(Boolean estado, Long numeroCuenta) {

		Cuenta cuenta = cuentaRepository.findCuenta(numeroCuenta);
		if (null == cuenta) {
			return Either.left(
					new ControlException("Número de cuenta: " + numeroCuenta + " no existe", HttpStatus.BAD_REQUEST));
		}

		cuenta.setEstado(estado);
		cuentaRepository.save(cuenta);

		Respuesta respuesta = new Respuesta("200", "Cuenta actualizada con exito", HttpStatus.OK);
		return Either.right(respuesta);
	}

	@Override
	public Either<Exception, Respuesta> eliminarCuenta(Long numeroCuenta) {

		Cuenta cuenta = cuentaRepository.findCuenta(numeroCuenta);
		if (null == cuenta) {
			return Either.left(
					new ControlException("Número de cuenta: " + numeroCuenta + " no existe", HttpStatus.BAD_REQUEST));
		}

		cuentaRepository.delete(cuenta);

		Respuesta respuesta = new Respuesta("200", "Cuenta eliminada con exito", HttpStatus.OK);
		return Either.right(respuesta);
	}

	private boolean validarRequest(CuentaRequest cuentaRequest) {

		return null == cuentaRequest.getNumeroCuenta() || null == cuentaRequest.getEstadoCuenta()
				|| null == cuentaRequest.getIdentificacionCliente() || null == cuentaRequest.getSaldoInicialCuenta()
				|| null == cuentaRequest.getTipoCuenta();
	}

}
