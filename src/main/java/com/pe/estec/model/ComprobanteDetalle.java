package com.pe.estec.model;

import lombok.Data;

@Data
public class ComprobanteDetalle {
	
private Integer id_comprobante_detalle;
private Integer id_comprobante;
private Double cantidad;
private String unidad_medida;
private String descripcion;
private Double valor_unitario;
private Double icbper;

}
