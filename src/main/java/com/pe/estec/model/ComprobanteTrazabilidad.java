package com.pe.estec.model;

import java.util.Date;

import lombok.Data;

@Data
public class ComprobanteTrazabilidad {
	private Integer id_comprobante_trazabilidad;
	private Integer id_comprobante;
	private Integer id_008_estado_trazabilidad;
	private Date fecha_registro;
	private String observacion;
	private String usuario_registro;
	private String nombre_estado;

}
