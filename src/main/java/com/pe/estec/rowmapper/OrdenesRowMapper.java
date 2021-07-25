package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.pe.estec.model.OrdenesCompra;

public class OrdenesRowMapper implements RowMapper<OrdenesCompra> {

	@Override
	public OrdenesCompra mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrdenesCompra ordenes = new OrdenesCompra();
		ordenes.setIdOrden(rs.getInt("ORDEN"));
		ordenes.setTipoMoneda(rs.getString("MONEDA"));
		ordenes.setTipoOrdenAbrev(rs.getString("TIPO_DOCUMENTO"));
//		ordenes.setNroOrden(rs.getString("moneda"));
		ordenes.setFecha(rs.getString("OC_DFECDOC"));
		ordenes.setImporte(rs.getString("OC_NTOTMN"));
		ordenes.setFormaPago(rs.getString("FORMA_PAGO"));
		return ordenes;
	}

}
