package com.gml.client.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.gml.client.model.entity.Client;

/**
 * Repository para administracion de clientes
 */
public interface ClientRepository extends CrudRepository<Client, String> {

}
