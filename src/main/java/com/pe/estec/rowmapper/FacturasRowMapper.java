package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pe.estec.model.Facturas;

public class FacturasRowMapper implements RowMapper<Facturas>{

	@Override
	public Facturas mapRow(ResultSet rs, int rowNum) throws SQLException {
		Facturas facturas = new Facturas();
		facturas.setNumeroFactura(rs.getString("NUMERO_FACTURA"));
		facturas.setFechaDocumento(rs.getString("FECHA_DOCUMENTO"));
		facturas.setFechaVenta(rs.getString("FECHA_DOCUMENTO"));
		facturas.setFechaRecibido(rs.getString("FECHA_DOCUMENTO"));
		facturas.setCodCliente(rs.getString("RUC"));
		facturas.setEstado(rs.getString("SITUACION_DOCUMENTO"));
		facturas.setMoneda(rs.getString("CODIGO_MONEDA"));
		facturas.setDesGasto(rs.getString("DESCRIPCION_FACTURA"));
		facturas.setImporteSoles(rs.getString("IMPORTE_SOLES"));
		facturas.setImporteDolares(rs.getString("IMPORTE_DOLARES"));
		facturas.setSaldo(rs.getString("SALDO_SOLES"));
		facturas.setIgv(rs.getString("IGV"));
		return facturas;
	}
	
}
