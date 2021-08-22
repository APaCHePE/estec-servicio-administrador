package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.Contrato;

public class ContratoRowMapper  implements RowMapper<Contrato> {
	@Override
	public Contrato mapRow(ResultSet rs, int rowNum) throws SQLException {
		Contrato contrato = new Contrato();
		contrato.setIdContrato(rs.getInt("id_contrato"));
		contrato.setDescripcion(rs.getString("descripcion"));
		contrato.setFechaContrato(rs.getDate("fecha_contrato"));
		return contrato;
	}

}
