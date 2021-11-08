package com.pe.estec.dto;

import com.pe.estec.model.PideSunarp;

import lombok.Data;

@Data
public class ConsultaDTO {

	private String tipoDocumentoConsulta;
	private String documentoConsulta;
	private String codigoUsuario;
	private String codigoOpcion;
	private PideSunarp sunarp;

}
