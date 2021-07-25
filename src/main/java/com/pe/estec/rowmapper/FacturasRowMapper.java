package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.Facturas;

public class FacturasRowMapper implements RowMapper<Facturas>{

	@Override
	public Facturas mapRow(ResultSet rs, int rowNum) throws SQLException {
		Facturas facturas = new Facturas();
		facturas.setIdFactura(rs.getInt("F6_CNUMDOC"));
		facturas.setNumeroFactura(rs.getString("FACTURA")); 
//		facturas.set
		return facturas;
	}
	
}
