package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.Asiento;

public class AsientoRowMapper implements RowMapper<Asiento> {
	@Override
	public Asiento mapRow(ResultSet rs, int rowNum) throws SQLException {
		Asiento asiento = new Asiento();
		asiento.setId_asiento_provision(rs.getInt("id_asiento_provision"));
		asiento.setId_comprobante(rs.getInt("id_comprobante"));
		asiento.setSub_diario(rs.getInt("sub_diario"));
		asiento.setSub_diario_detalle(rs.getString("sub_diario_detalle"));
		asiento.setFecha_asiento(rs.getString("fecha_asiento"));
		asiento.setConcepto(rs.getString("concepto"));
		asiento.setMoneda(rs.getString("moneda"));
		asiento.setConversion(rs.getString("conversion"));
		asiento.setTipo_conversion(rs.getString("tipo_conversion"));
		asiento.setTipo_cambio(rs.getString("tipo_cambio"));
		asiento.setEstado(rs.getInt("estado"));
		return asiento;
	}
}
