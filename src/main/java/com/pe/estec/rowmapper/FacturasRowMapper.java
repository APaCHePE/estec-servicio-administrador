package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.Facturas;

public class FacturasRowMapper implements RowMapper<Facturas>{

	@Override
	public Facturas mapRow(ResultSet rs, int rowNum) throws SQLException {
		Facturas facturas = new Facturas();
		facturas.setIdFactura(rs.getString("F5_CNUMDOC"));
		facturas.setNumeroFactura(rs.getString("FACTURA")); 
		facturas.setFecha(rs.getString("FECHA"));	
		facturas.setCodCliente(rs.getString("CODIGO_CLIENTE"));
		facturas.setMoneda(rs.getString("moneda"));
		facturas.setVendedor(rs.getString("vendedor"));
		facturas.setFormaVenta(rs.getString("factura"));
		return facturas;
	}
	
}
