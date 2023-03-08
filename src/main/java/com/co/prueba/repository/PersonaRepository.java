package com.co.prueba.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.co.prueba.domain.Persona;

@Component
public interface PersonaRepository extends CrudRepository<Persona, Long> {

	@Query("SELECT p FROM Persona p WHERE p.identificacion = :identificacion")
	Persona findPersonaIdentificacion(@Param("identificacion") Long identificacion);
}
