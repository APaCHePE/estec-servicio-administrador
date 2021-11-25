package com.pe.estec.model;

import lombok.Data;

@Data
public class AsientoDetalle {
	private Integer id_detalle_asiento_provision;
	private Integer id_asiento_provision;
	private Integer id_asiento_regla;
	private String cuenta;
	private String anexo;
	private String descripcion;
	private String tp;
	private String cc;
	private String debe;
	private String haber;
	private String documento;
	private String fecha_asiento_detalle;
	private String vencimiento_asiento_detalle;
	private String area;
	private Integer estado;
	
}
