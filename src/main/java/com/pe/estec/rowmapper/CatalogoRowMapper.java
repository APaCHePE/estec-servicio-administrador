package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.Catalogo;

public class CatalogoRowMapper implements RowMapper<Catalogo>{

	@Override
	public Catalogo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Catalogo catalogo = new Catalogo();
		catalogo.setIdParametro(rs.getInt("id_parametro"));
		catalogo.setIdParametroTipo(rs.getInt("id_parametro_tipo"));
		catalogo.setNombre(rs.getString("nombre"));
		catalogo.setAbreviatura(rs.getString("abreviatura"));
		catalogo.setEstado(rs.getInt("estado"));
		return catalogo;
	}

}
