package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.pe.estec.model.ComprobanteDetalle;

public class ComprobanteDetalleRowMapper implements RowMapper<ComprobanteDetalle> {

	@Override
	public ComprobanteDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
		ComprobanteDetalle comprobanteDetalle = new ComprobanteDetalle();
		comprobanteDetalle.setIdComprobanteDetalle(rs.getInt("id_comprobante_detalle"));
		comprobanteDetalle.setIdComprobante(rs.getInt("id_comprobante"));
		comprobanteDetalle.setCantidad(rs.getDouble("cantidad"));
		comprobanteDetalle.setUnidadMedida(rs.getString("unidad_medida"));
		comprobanteDetalle.setDescripcion(rs.getString("descripcion"));
		comprobanteDetalle.setValorUnitario(rs.getDouble("valor_unitario"));
		comprobanteDetalle.setIcbper(rs.getDouble("icbper"));
		return comprobanteDetalle;
	}
}
