package com.pe.estec.model;

import java.sql.Date;

import lombok.Data;

@Data
public class Contrato {
	private Integer idContrato;
	private String descripcion;
	private Date fechaContrato;
	

}
