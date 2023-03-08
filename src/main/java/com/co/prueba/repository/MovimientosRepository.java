package com.co.prueba.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.co.prueba.domain.Movimientos;

@Component
public interface MovimientosRepository extends CrudRepository<Movimientos, Long> {

}
