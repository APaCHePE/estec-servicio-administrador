package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.CatalogoContabilidad;

public class CatalogoContabilidadRowMappper implements RowMapper<CatalogoContabilidad> {
	@Override
	public CatalogoContabilidad mapRow(ResultSet rs, int rowNum) throws SQLException {
		CatalogoContabilidad catalogoContabilidad = new CatalogoContabilidad();
		catalogoContabilidad.setTCOD(rs.getString("TCOD"));
		catalogoContabilidad.setTDESCRI(rs.getString("TCLAVE"));
		catalogoContabilidad.setTCLAVE(rs.getString("TDESCRI"));
		catalogoContabilidad.setTDATE(rs.getString("TDATE"));
		catalogoContabilidad.setTHORA(rs.getString("THORA"));
		catalogoContabilidad.setCADEBUS(rs.getString("CADEBUS"));	
		return catalogoContabilidad;
		
	}
	

}
