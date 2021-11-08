package com.pe.estec.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@RefreshScope
@ConfigurationProperties
@Getter
public class Constantes {

	public static Integer ESTADO_ACTIVO = 1;
	public static Integer ESTADO_INACTIVO = 2;
	public static Integer ESTADO_PENDIENTE = 33;
	public static Integer ESTADO_APROBADO = 34;
	
	public static Integer ESTADO_COMPROBANTE_PENDIENTE= 9;
	public static Integer ESTADO_COMPROBANTE_APROBADO = 10;
	public static Integer ESTADO_COMPROBANTE_DENEGADO = 11;

	public static Integer EstadoTrazabilidad = 27;
	
	public static Integer ARCHIVOS_COMPROBANTE = 1;
	public static Integer ARCHIVOS_ORDEN = 2;
	
	public static String MONEDA_NACIONAL = "MN";
	public static String MONEDA_DOLAR = "US";
	public static String URL_PLANTILLA_PASS = "http://localhost:5051/estec-proveedores/pass/";

	
	@Value("${config.url.plantilla}")
	private String urlPlantillabase;
	@Value("${carpeta.plantilla}")
	private String carpetaMail;
	@Value("${config.url.mensajeria}")
	private String urlServicioMensajeria;
	
}
