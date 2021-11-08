package com.pe.estec.model;

public class FieldCustomError {
	private String atributo;
	private String mensaje;
	
	public String getAtributo() {
		return atributo;
	}
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	@Override
	public String toString() {
		return "FieldCustomError [atributo=" + atributo + ", mensaje=" + mensaje + "]";
	}
}
