package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.Comprobante;
import com.pe.estec.model.ComprobanteDetalle;

public class ComprobanteDetalleRowMapper implements RowMapper<ComprobanteDetalle> {

	@Override
	public ComprobanteDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
		ComprobanteDetalle comprobanteDetalle = new ComprobanteDetalle();
		return comprobanteDetalle;
	}
}
