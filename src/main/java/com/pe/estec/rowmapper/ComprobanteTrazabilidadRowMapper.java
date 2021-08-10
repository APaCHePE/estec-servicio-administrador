package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.ComprobanteTrazabilidad;

public class ComprobanteTrazabilidadRowMapper implements RowMapper<ComprobanteTrazabilidad> {
	@Override
	public ComprobanteTrazabilidad mapRow(ResultSet rs, int rowNum) throws SQLException {
		ComprobanteTrazabilidad comprobanteTrazabilidad = new ComprobanteTrazabilidad();
		comprobanteTrazabilidad.setIdComprobanteTrazabilidad(rs.getInt("id_comprobante_trazabilidad"));
		comprobanteTrazabilidad.setIdComprobante(rs.getInt("id_comprobante"));
		comprobanteTrazabilidad.setId008EstadoTrazabilidad(rs.getInt("id_008_estado_trazabilidad"));
		comprobanteTrazabilidad.setFechaRegistro(rs.getDate("fecha_registro"));
		comprobanteTrazabilidad.setObservacion(rs.getString("observacion"));
		comprobanteTrazabilidad.setUsuarioRegistro(rs.getString("usuario_registro"));
		comprobanteTrazabilidad.setNombreEstado(rs.getString("nombre_estado")); 
		return comprobanteTrazabilidad;
		}

	}
