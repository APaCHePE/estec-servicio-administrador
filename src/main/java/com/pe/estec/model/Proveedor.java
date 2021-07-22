package com.pe.estec.model;

import lombok.Data;

@Data
public class Proveedor {

	Integer id_proveedor;
	String nombre;
	
	public Integer getId_proveedor() {
		return id_proveedor;
	}

	public void setId_proveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Customer [id proveedor ="+id_proveedor+" nombre ="+nombre+" ]";
	}
}
