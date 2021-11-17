package com.pe.estec.model;

import lombok.Data;

@Data
public class ArchivoBancoDetalle {
	
	private Integer idArchivoBancoDetalle;
	private Integer idArchivoBanco;
	private Integer id007TipoComprobante;
	private Integer idComprobante;
	private String fechaProgramacion;
	private Integer id001Estado;
	private Integer idUsuarioRegistro;
	private Integer id009Banco;
	private String fechaXreacion;
	private String fechaModificacion;
}
