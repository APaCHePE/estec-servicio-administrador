package com.pe.estec.model;

import lombok.Data;

@Data
public class OrdenesCompra {

	private Integer idOrden;
	private String importe;
	private String fecha;
	private String nroOrden;
	private String tipoOrdenAbrev;
	private String tipoOrden;
	private String tipoMoneda;
	private String documento;
	private String saldo;
	private String estado;
	private String formaPago;
	private String importeFacturado;
	private String solicitante;
}
