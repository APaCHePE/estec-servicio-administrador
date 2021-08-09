package com.pe.estec.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Comprobante {
	private Integer id_comprobante;
	private Integer id_007_tipo_comprobante;
	private String serie;
	private String numero;
	private Integer proveedor_id_003_tipo_documento;
	private String proveedor_numero_documento;
	private String proveedor_nombre;
	private String proveedor_nombre_comercial;
	private String proveedor_direccion;
	private String proveedor_zona;
	private String fecha_emision;
	private String fecha_vencimiento;
	private Integer id_006_tipo_moneda;
	private String observacion;
	private Double importe_sub_total;
	private Double importe_anticipios;
	private Double importe_descuentos;
	private Double importe_valor_venta;
	private Double importe_isc;
	private Double importe_igv;
	private Double importe_icbper;
	private Double importe_otros_cargos;
	private Double importe_otros_tributos;
	private Double importe_monto_redondeo;
	private Double importe_total;
	private String orden_numero;
	private String orden_contrato;
	private Integer id_004_estado;
	private String nombre_moneda;
	private String nombre_estado;
	private List<ComprobanteDetalle> listaComprobanteDetalle;

	private Date fEmi;
	private Date fVen;
}
