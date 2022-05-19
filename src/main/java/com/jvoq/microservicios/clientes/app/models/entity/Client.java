package com.jvoq.microservicios.clientes.app.models.entity;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
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
	private String idCliente;
	@JsonProperty("tipo_documento")
	@Field("tipo_documento")
	private String tipoDocumento;
	@JsonProperty("tipo_documento")
	@Field("numero_documento")
	private String numDocumento;
	private String nombres;
	private String correo;
	private String direccion;
	private String telefono;
	@JsonProperty("tipo_documento")
	@Field("tipo_cliente")
	private String tipoCliente;
	private List<Client> representantes;
	@Field("fecha_creacion")
	private Date fechaCreacion;
	private boolean juridico;
}