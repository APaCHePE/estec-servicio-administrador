package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.FacturasDetalle;

public class FacturasDetalleRowMapper implements RowMapper<FacturasDetalle>{

	@Override
	public FacturasDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
		FacturasDetalle facturas = new FacturasDetalle();
		facturas.setCodigo_producto(rs.getString("CODIGO_PRODUCTO"));
		facturas.setProducto(rs.getString("PRODUCTO")); 
		facturas.setUnidad(rs.getString("UNIDAD"));	
		facturas.setPrecio(rs.getString("F6_NPRECIO"));
		facturas.setTipo_item(rs.getString("F6_CTIPITM"));
		return facturas;
	}
	
}
