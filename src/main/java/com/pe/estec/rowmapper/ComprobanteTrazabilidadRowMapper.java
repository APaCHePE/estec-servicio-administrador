package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;
import com.pe.estec.model.ComprobanteTrazabilidad;

public class ComprobanteTrazabilidadRowMapper implements RowMapper<ComprobanteTrazabilidad> {
	@Override
	public ComprobanteTrazabilidad mapRow(ResultSet rs, int rowNum) throws SQLException {
		ComprobanteTrazabilidad comprobanteTrazabilidad = new ComprobanteTrazabilidad();
		comprobanteTrazabilidad.setId_comprobante_trazabilidad(rs.getInt("id_comprobante_trazabilidad"));
		comprobanteTrazabilidad.setId_comprobante(rs.getInt("id_comprobante"));
		comprobanteTrazabilidad.setId_008_estado_trazabilidad(rs.getInt("id_008_estado_trazabilidad"));
		comprobanteTrazabilidad.setFecha_registro(rs.getDate("fecha_registro"));
		comprobanteTrazabilidad.setObservacion(rs.getString("observacion"));
		comprobanteTrazabilidad.setUsuario_registro(rs.getString("usuario_registro"));
		comprobanteTrazabilidad.setNombre_estado(rs.getString("nombre_estado")); 
		return comprobanteTrazabilidad;
		}

	}
