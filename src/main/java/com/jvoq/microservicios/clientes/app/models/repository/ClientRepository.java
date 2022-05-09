package com.jvoq.microservicios.clientes.app.models.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.jvoq.microservicios.clientes.app.models.entity.Client;

public interface ClientRepository extends ReactiveMongoRepository<Client, String> {

}
