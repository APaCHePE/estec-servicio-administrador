package com.pe.estec.model;

import lombok.Data;

@Data
public class Archivo {

	private Integer idArchivo;
	private String nombreArchivo;
	private byte[] archivo;
	private Integer idParametro;
	private Integer idDocmento;
	private Integer idDocumentoArchivo;
	
}
