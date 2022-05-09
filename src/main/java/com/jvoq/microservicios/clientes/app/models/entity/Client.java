package com.jvoq.microservicios.clientes.app.models.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clients")
public class Client {
	
	@Id
	private String idCliente;
	private String tipoDocumento;
	private String nombres;
	private String correo;
	private String direccion;
	private Long telefono;	
	private String numDocumento;
	private String tipoCliente;
	@JsonIgnoreProperties({"idCliente","correo","telefono","direccion","tipoCliente","representantes","fechaCreacion"})
	private List<Client> representantes;
	private Date fechaCreacion;
	

}
