package com.jvoq.microservicios.clientes.app.models.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "idCliente", "tipoDocumento", "numDocumento", "nombres", "telefono", "correo", "direccion",
		"tipoCliente", "representantes", "fechaCreacion" })
public class Client {

	@Id
	@JsonProperty("id_cliente")
	private String idCliente;

	@Field("tipo_documento")
	@JsonProperty("tipo_documento")
	private String tipoDocumento;

	@Field("numero_documento")
	@JsonProperty("numero_documento")
	private String numDocumento;
	private String nombres;
	private String correo;
	private String direccion;
	private String telefono;

	@Field("tipo_cliente")
	@JsonProperty("tipo_cliente")
	private String tipoCliente;
	@JsonIgnoreProperties({ "id_cliente", "correo", "direccion", "telefono", "representantes", "fecha_creacion" })
	private List<Client> representantes;

	@Field("fecha_creacion")
	@JsonProperty("fecha_creacion")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Lima")
	private Date fechaCreacion;
	private boolean juridico;
}