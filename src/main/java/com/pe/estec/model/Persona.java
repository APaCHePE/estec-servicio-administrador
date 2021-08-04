package com.pe.estec.model;

import lombok.Data;

@Data
public class Persona {

	private Integer idPersona;
	private String nombreCompleto;
	private String nombre;
	private String apePaterno;
	private String apeMaterno;
	private String nroDocumento;
	private Integer tipoDocumento;
	private String desTipoDocumento;
	private String direccion;
	private String telefonoPrincipal;
	private Integer estado;
	private Integer idSistema;
	private String fecRegistro;
	private String fecModificacion;
	
}
