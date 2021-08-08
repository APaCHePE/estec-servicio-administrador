package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.pe.estec.model.ComprobanteDetalle;

public class ComprobanteDetalleRowMapper implements RowMapper<ComprobanteDetalle> {

	@Override
	public ComprobanteDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
		ComprobanteDetalle comprobanteDetalle = new ComprobanteDetalle();
		comprobanteDetalle.setId_comprobante_detalle(rs.getInt("id_comprobante_detalle"));
		comprobanteDetalle.setId_comprobante(rs.getInt("id_comprobante"));
		comprobanteDetalle.setCantidad(rs.getDouble("cantidad"));
		comprobanteDetalle.setUnidad_medida(rs.getString("unidad_medida"));
		comprobanteDetalle.setDescripcion(rs.getString("descripcion"));
		comprobanteDetalle.setValor_unitario(rs.getDouble("valor_unitario"));
		comprobanteDetalle.setIcbper(rs.getDouble("icbper"));
		return comprobanteDetalle;
	}
}
