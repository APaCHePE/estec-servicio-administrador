package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.Empleado;
import com.pe.estec.model.Persona;
import com.pe.estec.model.Proveedor;

public class EmpleadoRowMapper implements RowMapper<Empleado>{

	@Override
	public Empleado mapRow(ResultSet rs, int rowNum) throws SQLException {
		Empleado proveedor = new Empleado(); 
		proveedor.setIdProveedor(rs.getInt("ID_EMPLEADO"));
		proveedor.setUsuario(rs.getString("USUARIO"));
		proveedor.setContrasenia(rs.getString("PASS"));
		proveedor.setAbrevPersona(rs.getString("ABREV_PERSONA"));
//		proveedor.setNombreTipoCuenta(rs.getString("DES_TIPO_CUENTA"));
		proveedor.setEstado(rs.getInt("ESTADO"));
//		proveedor.setNombreEstado(rs.getString("DES_ESTADO"));
		Persona persona = new Persona();
		persona.setIdPersona(rs.getInt("ID_PERSONA"));
		persona.setNroDocumento(rs.getString("NRO_DOCUMENTO"));
		persona.setNombreCompleto(rs.getString("NOMBRES_COMPLETOS"));
		persona.setTelefonoPrincipal(rs.getString("TELEFONO_PRINCIPAL"));
		proveedor.setPersona(persona);
		return proveedor;
	}

}
