package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.OrdenDetalle;

public class OrdenDetalleRowMapper implements RowMapper<OrdenDetalle>{

	@Override
	public OrdenDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrdenDetalle orden = new OrdenDetalle();
		
		orden.setNumeroOrdenDetalle(rs.getString("nroItem"));
		orden.setTotalSoles(rs.getString("total_soles"));
		orden.setTotalDolares(rs.getString("total_dolares"));
		orden.setNumeroOrdenDetalle(rs.getString("comentario_orden"));
		orden.setNumeroOrdenDetalle(rs.getString("estado_orden"));
		orden.setNumeroOrdenDetalle(rs.getString("nroItem"));
		orden.setNumeroOrdenDetalle(rs.getString("usuario_solicitante"));
		orden.setNumeroOrdenDetalle(rs.getString("tipo_orden_item"));
		orden.setNumeroOrdenDetalle(rs.getString("usuario_solicitante"));
		return orden;
	}

}
