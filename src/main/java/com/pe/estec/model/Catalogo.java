package com.pe.estec.model;

import lombok.Data;

@Data
public class Catalogo {

	private Integer idParametro;
	private Integer idParametroTipo;
	private String nombre;
	private String abreviatura;
	private Integer estado;
}
