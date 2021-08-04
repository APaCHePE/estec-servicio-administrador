package com.pe.estec.model;

import lombok.Data;

@Data
public class OrdenDetalle {

	private String numeroOrdenDetalle;
	private String codigo;
	private String descripcionOrden;
	private String cantidadOrden;
	private String precioUnitario;
	private String precioDeIgv;
	private String totalDolares;
	private String totalSoles;
	private String comentarioOrden;
	private String estadoOrden;
	private String tipoOrdenItem;
	private String usuarioSolicitante;
	private String fechaOrden;
}
