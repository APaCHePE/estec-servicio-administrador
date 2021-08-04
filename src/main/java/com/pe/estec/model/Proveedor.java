package com.pe.estec.model;

import lombok.Data;

@Data
public class Proveedor {

	private Integer idProveedor;
	private String usuario;
	private String contrasenia;
	private Integer tipoCuenta;
	private String nombreTipoCuenta;
	private Integer idProveeAsoc;
	private Integer estado;
	private String nombreEstado;
	private String fecRegistro;
	private String fecModifica;
	private Persona persona;
	private String observacion;
}
