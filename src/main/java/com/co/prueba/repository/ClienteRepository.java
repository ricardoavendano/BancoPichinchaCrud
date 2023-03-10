package com.co.prueba.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.co.prueba.domain.Cliente;

@Component
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
