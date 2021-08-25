package com.pe.estec.model;

import lombok.Data;

@Data
public class DocumentoOrigen {

	private Integer idContrato;
	private String descripcion;
	private String fechaInicio;
	private String usuarioResponsable;
	private String numeroDocumento;
}
