package com.pe.estec.model;

import org.springframework.stereotype.Component;

@Component
public class CuentaMensaje {
	private String email;
	private String nombre;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
}
