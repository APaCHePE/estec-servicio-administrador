package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.pe.estec.model.Comprobante;
import com.pe.estec.model.Facturas;

public class ComprobanteRowMapper implements RowMapper<Comprobante>{
	@Override
	public Comprobante mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comprobante comprobante = new Comprobante();
		return comprobante;
	}

}
