package com.pe.estec.model;

import java.util.List;

import lombok.Data;

@Data
public class Facturas {

	private String idFactura;
	private String codCliente;
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
	private String moneda;
	private String simboloMoneda;
	private String vendedor;
	private String almacenCa;
	private String formaVenta;
	private List<FacturasDetalle> facturasDestalle;

	@Override
	public String toString() {
		return "Customer [id idFactura ="+idFactura+" nombre ="+nombreCliente+" ]";
	}
}
