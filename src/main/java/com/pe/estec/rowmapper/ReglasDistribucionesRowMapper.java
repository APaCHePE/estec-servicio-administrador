package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.ReglasDistribucion;
public class ReglasDistribucionesRowMapper implements RowMapper<ReglasDistribucion>{
	@Override
	public ReglasDistribucion mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReglasDistribucion reglasDistribucion = new ReglasDistribucion();
		
		reglasDistribucion.setTT_CENCOS(rs.getString("TT_CENCOS"));
		reglasDistribucion.setTT_CTACAR(rs.getString("TT_CTACAR"));
		reglasDistribucion.setTT_FACTOR(rs.getString("TT_FACTOR"));
		reglasDistribucion.setTT_FECCRE(rs.getString("TT_FECCRE"));
		reglasDistribucion.setTT_CTAABO(rs.getString("TT_CTAABO"));
		reglasDistribucion.setTT_FECACT(rs.getString("TT_FECACT"));
		reglasDistribucion.setTT_USUARI(rs.getString("TT_USUARI"));
		reglasDistribucion.setTT_COSDIS(rs.getString("TT_COSDIS"));
		reglasDistribucion.setCADEBUS(rs.getString("CADEBUS"));
		reglasDistribucion.setDESCRI(rs.getString("DESCRI"));		
		return reglasDistribucion;
	}
	
	
}
