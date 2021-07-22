package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.Proveedor;

public class ProveedorRowMapper implements RowMapper<Proveedor>{

	@Override
	public Proveedor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Proveedor proveedor = new Proveedor();
		proveedor.setId_proveedor(rs.getInt("id_proveedor"));
		proveedor.setNombre(rs.getString("nombre"));		
		return proveedor;
	}

	
}
