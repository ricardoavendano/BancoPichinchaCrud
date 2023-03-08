package com.co.prueba.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.co.prueba.datatransfer.CuentaRequest;
import com.co.prueba.datatransfer.MovimientosDTO;
import com.co.prueba.datatransfer.MovimientosRequest;
import com.co.prueba.datatransfer.MovimientosResponse;
import com.co.prueba.datatransfer.Respuesta;
import com.co.prueba.domain.Cuenta;
import com.co.prueba.domain.Movimientos;
import com.co.prueba.exception.ControlException;
import com.co.prueba.repository.CuentaRepository;
import com.co.prueba.repository.MovimientosRepository;
import com.co.prueba.service.MovimientosService;

import fj.data.Either;

@Service
public class MovimientosServiceImpl implements MovimientosService {

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private MovimientosRepository movimientosRepository;

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Override
	public Either<Exception, List<MovimientosResponse>> consultarMovimiento(Long numeroCuenta) {

		Cuenta cuenta = cuentaRepository.findCuenta(numeroCuenta);

		if (null == cuenta) {
			return Either.left(
					new ControlException("Número de cuenta: " + numeroCuenta + " no existe", HttpStatus.BAD_REQUEST));
		}

		List<MovimientosResponse> listMovimientos = new ArrayList<>();

		MovimientosResponse movimientosResponse = mapearDatosCuenta(cuenta);

		if (null != cuenta.getMovimientoList() && !cuenta.getMovimientoList().isEmpty()) {
			crearListaMovimientos(cuenta, movimientosResponse);
		}

		listMovimientos.add(movimientosResponse);
		return Either.right(listMovimientos);
	}

	@Override
	public Either<Exception, List<MovimientosResponse>> consultarMovimientoFecha(Long numeroCuenta, Date fechaInicial,
			Date fechaFinal) throws ParseException {
		Cuenta cuenta = cuentaRepository.findCuenta(numeroCuenta);

		if (null == cuenta) {
			return Either.left(
					new ControlException("Número de cuenta: " + numeroCuenta + " no existe", HttpStatus.BAD_REQUEST));
		}

		List<MovimientosResponse> listMovimientos = new ArrayList<>();

		MovimientosResponse movimientosResponse = mapearDatosCuenta(cuenta);

		SimpleDateFormat formatoFecha = new SimpleDateFormat(DATE_FORMAT);

		if (!cuenta.getMovimientoList().isEmpty()) {

			List<Movimientos> movimientosFilter = cuenta.getMovimientoList().stream().filter(cuentaFilter -> {
				try {
					return (formatoFecha.parse(formatoFecha.format(cuentaFilter.getFecha()))
							.after(formatoFecha.parse(formatoFecha.format(fechaInicial)))
							|| formatoFecha.parse(formatoFecha.format(cuentaFilter.getFecha()))
									.equals(formatoFecha.parse(formatoFecha.format(fechaInicial))))
							&& (formatoFecha.parse(formatoFecha.format(cuentaFilter.getFecha()))
									.before(formatoFecha.parse(formatoFecha.format(fechaFinal)))
									|| formatoFecha.parse(formatoFecha.format(cuentaFilter.getFecha()))
											.equals(formatoFecha.parse(formatoFecha.format(fechaFinal))));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return false;
			}).collect(Collectors.toList());

			cuenta.setMovimientoList(new ArrayList<>());
			cuenta.setMovimientoList(movimientosFilter);

			crearListaMovimientos(cuenta, movimientosResponse);
		}

		listMovimientos.add(movimientosResponse);
		return Either.right(listMovimientos);
	}

	@Override
	public Either<Exception, Respuesta> crearMovimiento(MovimientosRequest movimientoRequest) throws ParseException {

		Cuenta cuenta = cuentaRepository.findCuenta(movimientoRequest.getNumeroCuenta());

		if (null == cuenta) {
			return Either.left(new ControlException(
					"Número de cuenta: " + movimientoRequest.getNumeroCuenta() + " no existe", HttpStatus.BAD_REQUEST));
		}

		MovimientosResponse movimientosResponse = mapearDatosCuenta(cuenta);

		Long saldo;

		if (!cuenta.getMovimientoList().isEmpty()) {
			crearListaMovimientos(cuenta, movimientosResponse);

			saldo = movimientosResponse.getMovimientos().get(movimientosResponse.getMovimientos().size() - 1)
					.getSaldo();

		} else {
			saldo = cuenta.getSaldoInicial();
		}

		saldo = saldo + movimientoRequest.getValor();

		if (saldo < 0) {
			return Either.left(new ControlException("Saldo no disponible", HttpStatus.BAD_REQUEST));
		}

		Movimientos movimientos = new Movimientos();
		movimientos.setIdMovimiento(Long.valueOf(0));
		if (movimientoRequest.getValor() < 0) {
			movimientos.setTipoMovimiento("Retiro");
		} else {
			movimientos.setTipoMovimiento("Deposito");
		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
		LocalDateTime now = LocalDateTime.now();

		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

		movimientos.setFecha(dateFormat.parse(dtf.format(now)));
		movimientos.setSaldo(saldo);
		movimientos.setValor(movimientoRequest.getValor());
		movimientos.setIdCuentaPK(cuenta);
		movimientosRepository.save(movimientos);

		Respuesta respuesta = new Respuesta("200", "Movimiento creado con exito", HttpStatus.OK);
		return Either.right(respuesta);
	}

	private MovimientosResponse mapearDatosCuenta(Cuenta cuenta) {
		CuentaRequest cuentaRequest = new CuentaRequest();
		cuentaRequest.setEstadoCuenta(cuenta.getEstado());
		cuentaRequest.setIdentificacionCliente(cuenta.getidClientePK().getidPersonaPK().getIdentificacion());
		cuentaRequest.setNombreCliente(cuenta.getidClientePK().getidPersonaPK().getNombre());
		cuentaRequest.setNumeroCuenta(cuenta.getNumeroCuenta());
		cuentaRequest.setSaldoInicialCuenta(cuenta.getSaldoInicial());
		cuentaRequest.setTipoCuenta(cuenta.getTipoCuenta());

		MovimientosResponse movimientosResponse = new MovimientosResponse();
		movimientosResponse.setCuentaRequest(cuentaRequest);
		movimientosResponse.setMovimientos(new ArrayList<>());
		return movimientosResponse;
	}

	private void crearListaMovimientos(Cuenta cuenta, MovimientosResponse movimientosResponse) {
		List<MovimientosDTO> listMovimientoDTO = new ArrayList<>();
		for (Movimientos movimiento : cuenta.getMovimientoList()) {
			MovimientosDTO movimientoDTO = new MovimientosDTO();
			movimientoDTO.setFecha(movimiento.getFecha());
			movimientoDTO.setSaldo(movimiento.getSaldo());
			movimientoDTO.setTipoMovimiento(movimiento.getTipoMovimiento());
			movimientoDTO.setValor(movimiento.getValor());
			listMovimientoDTO.add(movimientoDTO);
		}

		movimientosResponse.setMovimientos(listMovimientoDTO);
	}

}
