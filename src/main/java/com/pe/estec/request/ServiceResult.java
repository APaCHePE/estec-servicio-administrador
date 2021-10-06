package com.pe.estec.request;

import java.util.ArrayList;

import lombok.Data;
@Data
public class ServiceResult<T> {
//
	private T resultado;
    private Boolean esCorrecto;
    private String mensajeError;
    private Integer httpStatus;
    private ArrayList<String> advertencias;
}
