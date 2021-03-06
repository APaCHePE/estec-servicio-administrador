package com.pe.estec.model;


import java.util.List;

import lombok.Data;

@Data
public class ArchivoBanco {
	
	private Integer idArchivoBanco;
	private Integer id007TipoComprobante;
	private String fechaProgramacion;
	private Integer id001Estado;
	private Integer idUsuarioRegistro;
	private String usuarioRegistro;
	private Integer id009Banco;
	private String fechaCreacion;
	private String fechaModificacion;
	private Integer cantidadRegistros;
	private List<ArchivoBancoDetalle> listaArchivoBancoDetalle;
	
}
