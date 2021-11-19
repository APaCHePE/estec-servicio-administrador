package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.ReglasDistribucion;

public class ReglasDistribucionesRowMapper implements RowMapper<ReglasDistribucion>{
	@Override
	public ReglasDistribucion mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReglasDistribucion reglasDistribucion = new ReglasDistribucion();
		
		return reglasDistribucion;
	}
	
	
}
