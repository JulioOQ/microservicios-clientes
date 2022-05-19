package com.jvoq.microservicios.clientes.app.dtos;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "idCliente", "tipoDocumento", "numDocumento", "nombres", "telefono", "correo", "direccion",
		"tipoCliente", "representantes", "fechaCreacion" })
public class ClientDto {

	@JsonProperty("id_cliente")
	private String idCliente;
	@JsonProperty("tipo_documento")
	private String tipoDocumento;
	@JsonProperty("numero_documento")
	private String numDocumento;
	private String nombres;
	private String correo;
	private String direccion;
	private String telefono;
	@JsonProperty("tipo_cliente")
	private String tipoCliente;
	@JsonIgnoreProperties({ "id_cliente", "correo", "direccion", "telefono", "representantes", "fecha_creacion" })
	private List<ClientDto> representantes;
	@JsonProperty("fecha_creacion")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Lima")
	private Date fechaCreacion;
	private boolean juridico;
}
