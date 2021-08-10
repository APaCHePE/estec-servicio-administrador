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
	public static Integer ESTADO_DESACTIVADO = 2;
	public static Integer ESTADO_PENDIENTE= 8;
	public static Integer ESTADO_DENEGADO = 12;

	public static Integer ESTADO_PENDIENTE_FACTURA= 9;
	public static String MONEDA_NACIONAL = "MN";
	public static String MONEDA_DOLAR = "US";

	@Value("${config.url.plantilla}")
	private String urlPlantillabase;
	@Value("${carpeta.plantilla}")
	private String carpetaMail;
	@Value("${config.url.mensajeria}")
	private String urlServicioMensajeria;
	
}
