package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.DocumentoOrigen;

public class DocumentoOrigneContratoRowMapper implements RowMapper<DocumentoOrigen>{

	@Override
	public DocumentoOrigen mapRow(ResultSet rs, int rowNum) throws SQLException {
		DocumentoOrigen contrato = new DocumentoOrigen();
		contrato.setIdContrato(rs.getInt("id_contrato"));
		contrato.setDescripcion(rs.getString("descripcion"));
		contrato.setFechaInicio(rs.getString("fecha_contrato"));
		contrato.setUsuarioResponsable(rs.getString("usuario_responsable"));
		return contrato;
	}

}
