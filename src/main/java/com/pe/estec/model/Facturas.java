package com.pe.estec.model;

import lombok.Data;

@Data
public class Facturas {

	private Integer idFactura;
	private String nombreCliente;
	private String fecha;
	private String numeroFactura;
	private String importeFacturado;
	private String importePagado;
	private String saldo;
	private String estado ;
	private String detalle;
	private String subtotal;
	private String igv;
	private String total;  

	@Override
	public String toString() {
		return "Customer [id idFactura ="+idFactura+" nombre ="+nombreCliente+" ]";
	}
}
