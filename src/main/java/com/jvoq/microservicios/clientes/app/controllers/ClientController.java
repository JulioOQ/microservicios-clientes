package com.jvoq.microservicios.clientes.app.controllers;

import java.net.URI;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.jvoq.microservicios.clientes.app.dtos.ClientDto;
import com.jvoq.microservicios.clientes.app.models.entity.Client;
import com.jvoq.microservicios.clientes.app.services.ClientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RequestMapping("/clients")
@RefreshScope
@RestController
public class ClientController {

  @Autowired
  private ClientService clientService;

  @Autowired
  private ModelMapper mapper;

  @Value("${mensaje.verificacion:default}")
  private String mensaje;

  @GetMapping("verificar")
  public String viewDiscounts() {
    return "Mensaje -> " + mensaje;
  }

  @GetMapping
  public Mono<ResponseEntity<Flux<ClientDto>>> getAll() {
    return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(clientService.findAll()));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<ClientDto>> getById(@PathVariable String id) throws InterruptedException {
    if (id.equals("666666")) {
      TimeUnit.SECONDS.sleep(5L);
    }

    return clientService.findById(id).map(c -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(c))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<ResponseEntity<ClientDto>> create(@RequestBody ClientDto clientDto) {

    if (clientDto.getFechaCreacion() == null) {
      clientDto.setFechaCreacion(new Date());
    }

    return clientService.save(clientDto).map(c -> ResponseEntity.created(URI.create("/clients".concat(c.getIdCliente())))
        .contentType(MediaType.APPLICATION_JSON).body(c));

  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<ClientDto>> update(@RequestBody ClientDto clientDto, @PathVariable String id) {
    return clientService.actualize(clientDto, id).map(c -> ResponseEntity
        .created(URI.create("/clients".concat(c.getIdCliente()))).contentType(MediaType.APPLICATION_JSON).body(c))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> drop(@PathVariable String id) {
    return clientService.findById(id).flatMap(c -> {
      Client client = mapper.map(c, Client.class);

      return clientService.delete(client).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
    }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

  }

}
