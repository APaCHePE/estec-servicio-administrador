package com.pe.estec.model;

import java.util.List;

import lombok.Data;

@Data
public class Orden {

	private Integer idOrden;
	private String importe;
	private String fecha;
	private String nroOrden;
	private String tipoOrdenAbrev;
	private String tipoOrden;
	private String tipoOrdenDescripcion;
	private String tipoDocumentoAbrev;
	private String tipoDocumentoNombre;
	private String tipoMoneda;
	private String tipoCambio;
	private String nombreMoneda;
	private String documentoProveedor;
	private String nombreProveedor;
	private String saldo;
	private String estado;
	private String formaPago;
	private String importeFacturado;
	private String solicitante;
	private List<OrdenDetalle> listaDetalles;
}
