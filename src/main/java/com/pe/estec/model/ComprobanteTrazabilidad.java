package com.pe.estec.model;

import java.util.Date;

import lombok.Data;

@Data
public class ComprobanteTrazabilidad {
	private Integer idComprobanteTrazabilidad;
	private Integer idComprobante;
	private Integer id008EstadoTrazabilidad;
	private Date fechaRegistro;
	private String observacion;
	private String usuarioRegistro;
	private String nombreEstado;

}
