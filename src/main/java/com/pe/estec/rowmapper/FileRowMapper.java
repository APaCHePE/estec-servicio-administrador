package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pe.estec.model.Archivo;

@Repository
public class FileRowMapper implements RowMapper<Archivo>{

	@Override
	public Archivo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Archivo archivo = new Archivo();
		archivo.setArchivo(rs.getBytes("archivo"));
		archivo.setIdArchivo(rs.getInt("idArchivo"));
		archivo.setNombreArchivo(rs.getString("nombre_archivo"));
		return archivo;
	}

	
}
