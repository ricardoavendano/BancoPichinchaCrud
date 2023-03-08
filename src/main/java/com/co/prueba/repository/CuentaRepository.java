package com.co.prueba.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.co.prueba.domain.Cuenta;

@Component
public interface CuentaRepository extends CrudRepository<Cuenta, Long> {

	@Query("SELECT c FROM Cuenta c WHERE c.numeroCuenta = :numeroCuenta")
	Cuenta findCuenta(@Param("numeroCuenta") Long numeroCuenta);
}
