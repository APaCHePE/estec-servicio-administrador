package com.pe.estec.model;

import java.util.List;

import lombok.Data;

@Data
public class Facturas {

	private String idFactura;
	private String codCliente;
	private String nombreCliente;
	private String fechaDocumento;
	private String fechaVenta;
	private String fechaRecibido;
	private String numeroFactura;
	private String importeSoles;
	private String importeDolares;
	private String saldo;
	private String estado ;
	private String detalle;
	private String subtotal;
	private String igv;
	private String total;
	private String moneda;
	private String simboloMoneda;
	private String desGasto;
	private String almacenCa;
	private String formaVenta;
	private List<FacturasDetalle> facturasDestalle;

	@Override
	public String toString() {
		return "Customer [id idFactura ="+idFactura+" nombre ="+nombreCliente+" ]";
	}
}
