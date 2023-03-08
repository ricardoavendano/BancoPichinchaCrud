package com.co.prueba.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.co.prueba.domain.Cliente;

@Component
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

//	@Query("SELECT t FROM Cliente t WHERE t.fechaEjecucion <= :fecha AND (t.fechaCierre = NULL OR t.fechaCierre >= :fecha)")
//	List<Cliente> findAllTareasFecha(@Param("fecha") Date fecha);

}
