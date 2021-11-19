package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.AsientoDetalle;


public class AsientoDetalleRowMapper  implements RowMapper<AsientoDetalle>{
	@Override
	public AsientoDetalle mapRow(ResultSet rs, int rowNum) throws SQLException {
		AsientoDetalle asientoDetalle = new AsientoDetalle();
		asientoDetalle.setId_detalle_asiento_provision(rs.getInt("id_detalle_asiento_provision"));
		asientoDetalle.setId_asiento_provision(rs.getInt("id_asiento_provision"));
		asientoDetalle.setId_asiento_regla(rs.getInt("id_asiento_regla"));
		asientoDetalle.setCuenta(rs.getString("cuenta"));
		asientoDetalle.setAnexo(rs.getString("anexo"));
		asientoDetalle.setDescripcion(rs.getString("descripcion"));
		asientoDetalle.setCc(rs.getInt("cc"));
		asientoDetalle.setTp(rs.getString("tp"));
		asientoDetalle.setDebe(rs.getString("debe"));
		asientoDetalle.setHaber(rs.getString("haber"));
		asientoDetalle.setDocumento(rs.getString("fecha_asiento_detalle"));
		asientoDetalle.setVencimiento_asiento_detalle(rs.getString("vencimiento_asiento_detalle"));
		asientoDetalle.setArea(rs.getString("area"));
		asientoDetalle.setEstado(rs.getInt("estado"));
		return asientoDetalle;
		
	}
}
