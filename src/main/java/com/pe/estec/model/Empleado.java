package com.pe.estec.model;

import lombok.Data;

@Data
public class Empleado {

	private Integer idProveedor;
	private String usuario;
	private String contrasenia;
	private String abrevPersona;
	private Integer estado;
	private String nombreEstado;
	private String fecRegistro;
	private String fecModifica;
	private Persona persona;
	private String observacion;
}
