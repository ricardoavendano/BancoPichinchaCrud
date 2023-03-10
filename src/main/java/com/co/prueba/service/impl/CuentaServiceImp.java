package com.co.prueba.service.impl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.co.prueba.datatransfer.CuentaRequest;
import com.co.prueba.domain.Cliente;
import com.co.prueba.domain.Cuenta;
import com.co.prueba.domain.Persona;
import com.co.prueba.exception.CampoInesperadoExepcion;
import com.co.prueba.exception.CampoObligatorioExcepcion;
import com.co.prueba.exception.ClienteInactivoExcepcion;
import com.co.prueba.exception.ClienteNoEncontradoExcepcion;
import com.co.prueba.exception.CuentaExisteExcepcion;
import com.co.prueba.exception.CuentaNoEncontradaExcepcion;
import com.co.prueba.exception.CuentaSaldoInicialExcepcion;
import com.co.prueba.repository.CuentaRepository;
import com.co.prueba.repository.PersonaRepository;
import com.co.prueba.service.CuentaService;

@Service
public class CuentaServiceImp implements CuentaService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CuentaServiceImp.class);

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public CuentaRequest consultarCuenta(Long numeroCuenta) throws CuentaNoEncontradaExcepcion {

		Cuenta cuenta = cuentaRepository.findCuenta(numeroCuenta);
		if (null == cuenta) {
			throw new CuentaNoEncontradaExcepcion("Número de cuenta: " + numeroCuenta + " no existe");
		}

		CuentaRequest cuentaRequest = new CuentaRequest();
		cuentaRequest.setEstadoCuenta(cuenta.getEstado());
		cuentaRequest.setIdentificacionCliente(cuenta.getidClientePK().getidPersonaPK().getIdentificacion());
		cuentaRequest.setNombreCliente(cuenta.getidClientePK().getidPersonaPK().getNombre());
		cuentaRequest.setNumeroCuenta(cuenta.getNumeroCuenta());
		cuentaRequest.setSaldoInicialCuenta(cuenta.getSaldoInicial());
		cuentaRequest.setTipoCuenta(cuenta.getTipoCuenta());

		return cuentaRequest;
	}

	@Override
	@Transactional
	public void crearCuenta(CuentaRequest cuentaRequest) throws CampoObligatorioExcepcion, ClienteNoEncontradoExcepcion,
			ClienteInactivoExcepcion, CuentaExisteExcepcion, CampoInesperadoExepcion {

		if (validarRequest(cuentaRequest)) {
			throw new CampoObligatorioExcepcion("Revise el request, todos los campos son obligatorios");
		}

		Persona persona = personaRepository.findPersonaIdentificacion(cuentaRequest.getIdentificacionCliente());

		if (null == persona) {
			throw new ClienteNoEncontradoExcepcion(
					"Cliente con número de identificacion: " + cuentaRequest.getIdentificacionCliente() + " no existe");
		}

		if (Boolean.FALSE.equals(persona.getCliente().getEstado())) {
			throw new ClienteInactivoExcepcion("Cliente se encuentra inactivo para crear cuentas");
		}

		Cuenta cuenta = cuentaRepository.findCuenta(cuentaRequest.getNumeroCuenta());

		if (null != cuenta) {
			throw new CuentaExisteExcepcion("Número de cuenta: " + cuentaRequest.getNumeroCuenta() + " ya existe");
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

			LOGGER.info("Cuenta {} creada con exito", cuentaRequest.getNumeroCuenta());

		} else {
			throw new CampoInesperadoExepcion("Las tipos de cuentas permitidas son: Ahorros, Corriente");
		}

	}

	@Override
	@Transactional
	public void actualizarCuenta(CuentaRequest cuentaRequest)
			throws CampoObligatorioExcepcion, CuentaNoEncontradaExcepcion, ClienteNoEncontradoExcepcion,
			CuentaSaldoInicialExcepcion, CampoInesperadoExepcion {

		if (validarRequest(cuentaRequest)) {
			throw new CampoObligatorioExcepcion("Revise el request, todos los campos son obligatorios");
		}

		Cuenta cuenta = cuentaRepository.findCuenta(cuentaRequest.getNumeroCuenta());
		if (null == cuenta) {
			throw new CuentaNoEncontradaExcepcion(
					"Número de cuenta: " + cuentaRequest.getNumeroCuenta() + " no existe");
		}

		Persona persona = personaRepository.findPersonaIdentificacion(cuentaRequest.getIdentificacionCliente());

		if (null == persona) {
			throw new ClienteNoEncontradoExcepcion(
					"Cliente con número de identificacion: " + cuentaRequest.getIdentificacionCliente() + " no existe");
		}

		if (!cuentaRequest.getSaldoInicialCuenta().equals(cuenta.getSaldoInicial())
				&& !cuenta.getMovimientoList().isEmpty()) {
			throw new CuentaSaldoInicialExcepcion("No se puede modificar el saldo inicial porque tiene movimientos");
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

			LOGGER.info("Cuenta {} actualizada con exito", cuentaRequest.getNumeroCuenta());

		} else {
			throw new CampoInesperadoExepcion("Las tipos de cuentas permitidas son: Ahorros, Corriente");
		}

	}

	@Override
	@Transactional
	public void actualizarEstadoCuenta(Boolean estado, Long numeroCuenta) throws CuentaNoEncontradaExcepcion {

		Cuenta cuenta = cuentaRepository.findCuenta(numeroCuenta);
		if (null == cuenta) {
			throw new CuentaNoEncontradaExcepcion("Número de cuenta: " + numeroCuenta + " no existe");
		}

		cuenta.setEstado(estado);
		cuentaRepository.save(cuenta);
		LOGGER.info("Estado de la cuenta {} cambiada a {} con exito", numeroCuenta, estado);
	}

	@Override
	@Transactional
	public void eliminarCuenta(Long numeroCuenta) throws CuentaNoEncontradaExcepcion {

		Cuenta cuenta = cuentaRepository.findCuenta(numeroCuenta);
		if (null == cuenta) {
			throw new CuentaNoEncontradaExcepcion("Número de cuenta: " + numeroCuenta + " no existe");
		}

		cuentaRepository.delete(cuenta);
		LOGGER.info("Cuenta {}eliminada con exito", numeroCuenta);
	}

	private boolean validarRequest(CuentaRequest cuentaRequest) {

		return null == cuentaRequest.getNumeroCuenta() || null == cuentaRequest.getEstadoCuenta()
				|| null == cuentaRequest.getIdentificacionCliente() || null == cuentaRequest.getSaldoInicialCuenta()
				|| null == cuentaRequest.getTipoCuenta();
	}

}
