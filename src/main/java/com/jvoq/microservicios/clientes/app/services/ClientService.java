package com.jvoq.microservicios.clientes.app.services;

import com.jvoq.microservicios.clientes.app.dtos.ClientDto;
import com.jvoq.microservicios.clientes.app.models.entity.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

	public Flux<ClientDto> findAll();

	public Mono<ClientDto> findById(String id);

	public Mono<ClientDto> save(ClientDto clientDto);

	public Mono<ClientDto> update(ClientDto clientDto, String id);

	public Mono<Void> delete(Client client);
}
