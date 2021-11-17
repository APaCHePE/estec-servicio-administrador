package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.ArchivoBancoDetalle;

public class ArchivoBancoDetalleRowMapper implements RowMapper<ArchivoBancoDetalle>{

	@Override
	public ArchivoBancoDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return null;
	}

}
