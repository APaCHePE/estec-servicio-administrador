package com.pe.estec.model;

import java.util.List;

import lombok.Data;

@Data
public class Asiento {
	private Integer id_asiento_provision;
	private Integer id_comprobante;
	private Integer sub_diario;
	private String sub_diario_detalle;
	private String fecha_asiento;
	private String concepto;
	private String moneda;
	private String conversion;
	private String tipo_conversion;
	private String tipo_cambio;
	private Integer estado;
	private String importeSinDetraccion;
	private Integer afectoTipoComprobante;
	private Boolean afectoIgv;
	private Boolean afectoDetraccion;
	private List<AsientoDetalle> listAsientoDetalle;
}
