package com.jvoq.microservicios.clientes.app.services;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jvoq.microservicios.clientes.app.dtos.ClientDto;
import com.jvoq.microservicios.clientes.app.models.entity.Client;
import com.jvoq.microservicios.clientes.app.models.repository.ClientRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImplement implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Flux<ClientDto> findAll() {
		return clientRepository.findAll().map(this::convertEntityToDto);
	}

	@Override
	public Mono<ClientDto> findById(String id) {
		return clientRepository.findById(id).map(this::convertEntityToDto);
	}

	@Override
	public Mono<ClientDto> save(ClientDto clientDto) {
		clientDto.setFechaCreacion(new Date());
		Client client = this.convertDtoToEntity(clientDto);
		return clientRepository.save(client).map(this::convertEntityToDto);
	}

	@Override
	public Mono<ClientDto> update(ClientDto clientDto, String id) {
		return this.findById(id).flatMap(c -> {
			c.setNombres(clientDto.getNombres());
			c.setCorreo(clientDto.getCorreo());
			c.setDireccion(clientDto.getDireccion());
			c.setTelefono(clientDto.getTelefono());
			c.setTipoDocumento(clientDto.getTipoDocumento());
			c.setNumDocumento(clientDto.getNumDocumento());
			c.setTipoCliente(clientDto.getTipoCliente());
			c.setRepresentantes(clientDto.getRepresentantes());
			c.setJuridico(clientDto.isJuridico());
			return this.save(c);
		});
	}

	@Override
	public Mono<Void> delete(Client client) {
		return clientRepository.delete(client);
	}

	private ClientDto convertEntityToDto(Client client) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return mapper.map(client, ClientDto.class);
	}

	private Client convertDtoToEntity(ClientDto clietnDto) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return mapper.map(clietnDto, Client.class);
	}
}
