package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.Persona;
import com.pe.estec.model.Proveedor;

public class ProveedorErpRowMapper implements RowMapper<Proveedor>{

	@Override
	public Proveedor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Proveedor proveedor = new Proveedor(); 
		Persona persona = new Persona();
		persona.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
		persona.setNombreCompleto(rs.getString("NOMBRE_COMPLETO"));
		persona.setTelefonoPrincipal(rs.getString("TELEFONO_PRINCIPAL"));
		persona.setDireccion(rs.getString("DIRECCION"));
		proveedor.setPersona(persona);
		return proveedor;
	}

}
