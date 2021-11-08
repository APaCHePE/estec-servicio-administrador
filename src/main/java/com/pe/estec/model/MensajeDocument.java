package com.pe.estec.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

//@Document(collection="MensajeDocument")
public class MensajeDocument implements Serializable{
	private static final long serialVersionUID = 1L;
	
//	@Id
//	@NotNull
	private String mensajeId;
	private String aplicacionEnvio;
	private Date fechaEnvio = new Date();
	private CuentaMensaje cuentaRemitente;
	private CuentaMensaje cuentaDestino;
	private List<CuentaMensaje> cuentaCopia;
	private CuentaMensaje cuentaRespuesta;
	private String mensajeAsunto;
	private String mensajeContenido;
	
	public String getMensajeId() {
		return mensajeId;
	}
	public void setMensajeId(String mensajeId) {
		this.mensajeId = mensajeId;
	}
	public String getAplicacionEnvio() {
		return aplicacionEnvio;
	}
	public void setAplicacionEnvio(String aplicacionEnvio) {
		this.aplicacionEnvio = aplicacionEnvio;
	}
	public Date getFechaEnvio() {
		return fechaEnvio;
	}
	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	public CuentaMensaje getCuentaRemitente() {
		return cuentaRemitente;
	}
	public void setCuentaRemitente(CuentaMensaje cuentaRemitente) {
		this.cuentaRemitente = cuentaRemitente;
	}
	public CuentaMensaje getCuentaDestino() {
		return cuentaDestino;
	}
	public void setCuentaDestino(CuentaMensaje cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	public List<CuentaMensaje> getCuentaCopia() {
		return cuentaCopia;
	}
	public void setCuentaCopia(List<CuentaMensaje> cuentaCopia) {
		this.cuentaCopia = cuentaCopia;
	}
	public CuentaMensaje getCuentaRespuesta() {
		return cuentaRespuesta;
	}
	public void setCuentaRespuesta(CuentaMensaje cuentaRespuesta) {
		this.cuentaRespuesta = cuentaRespuesta;
	}
	public String getMensajeAsunto() {
		return mensajeAsunto;
	}
	public void setMensajeAsunto(String mensajeAsunto) {
		this.mensajeAsunto = mensajeAsunto;
	}
	public String getMensajeContenido() {
		return mensajeContenido;
	}
	public void setMensajeContenido(String mensajeContenido) {
		this.mensajeContenido = mensajeContenido;
	}
}
