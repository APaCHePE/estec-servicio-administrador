package com.pe.estec.model;

import java.util.List;

import lombok.Data;

@Data
public class Comprobante {
	
	private Integer idComprobante;
	private Integer id007TipoComprobante;
	private String serie;
	private String numero;
	private Integer proveedorId003TipoDocumento;
	private String proveedorNumeroDocumento;
	private String proveedorNombre;
	private String proveedorNombreComercial;
	private String proveedorDireccion;
	private String proveedorZona;
	private String fechaEmision;
	private String fechaVencimiento;
	private Integer id006TipoMoneda;
	private String observacion;
	private Double importeSubTotal;
	private Double importeAnticipios;
	private Double importeDescuentos;
	private Double importeValorVenta;
	private Double importeIsc;
	private Double importeIgv;
	private Double importeIcbper;
	private Double importeOtrosCargos;
	private Double importeOtrosTributos;
	private Double importeMontoRedondeo;
	private Double importeTotal;
	private String ordenNumero;
	private String ordenContrato;
	private Integer id004Estado;
	private String nombreMoneda;
	private String nombreEstado;
	private String nombreTipoComprobante;
	private String usuarioResponsable;
	private Boolean requiereValidación;
	private List<ComprobanteDetalle> listaComprobanteDetalle;
	private List<ComprobanteTrazabilidad> listaComprobanteTrazabilidad;

}
