package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.Catalogo;

public class CatalogoRowMapper implements RowMapper<Catalogo>{

	@Override
	public Catalogo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Catalogo catalogo = new Catalogo();
		return catalogo;
	}

}
