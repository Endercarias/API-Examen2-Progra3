package com.examen.api.reposities;

import org.springframework.data.repository.CrudRepository;

import com.examen.api.entities.Cliente;

public interface ClientRepository extends CrudRepository<Cliente, Long>{

}
