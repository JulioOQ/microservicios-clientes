package com.jvoq.microservicios.clientes.app.services;

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
    return clientRepository.findAll().map(c -> this.convertEntityToDto(c));
  }

  @Override
  public Mono<ClientDto> findById(String id) {
    return clientRepository.findById(id).map(c -> this.convertEntityToDto(c));
  }

  @Override
  public Mono<ClientDto> save(ClientDto clientDto) {
    Client client =this.convertDtoToEntity(clientDto);
    return clientRepository.save(client).map(c -> this.convertEntityToDto(c));
  }
  
  @Override
  public Mono<ClientDto> actualize(ClientDto clientDto, String id) {  
    return  this.findById(id).flatMap(c -> {
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
    ClientDto clientDTO = new ClientDto();
    clientDTO = mapper.map(client, ClientDto.class);
    return clientDTO;
  }
  
  private Client convertDtoToEntity(ClientDto clietnDto) {
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    Client user = new Client();
    user = mapper.map(clietnDto, Client.class);
    return user;
  }
  

  
  
}
