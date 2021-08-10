package com.pe.estec.model;

import lombok.Data;

@Data
public class ComprobanteDetalle {

	private Integer idComprobanteDetalle;
	private Integer idComprobante;
	private Double cantidad;
	private String unidadMedida;
	private String codigo;
	private String descripcion;
	private Double valorUnitario;
	private Double icbper;

}
