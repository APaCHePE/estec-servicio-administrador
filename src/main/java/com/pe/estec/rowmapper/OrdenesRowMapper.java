package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.pe.estec.model.Orden;

public class OrdenesRowMapper implements RowMapper<Orden> {

	@Override
	public Orden mapRow(ResultSet rs, int rowNum) throws SQLException {
		Orden ordenes = new Orden();
		ordenes.setIdOrden(rs.getInt("NUMERO_ORDEN"));
		ordenes.setNroOrden(rs.getString("NUMERO_ORDEN"));
		ordenes.setDocumentoProveedor(rs.getString("RUC_PROVEEDOR"));
		ordenes.setTipoDocumentoAbrev(rs.getString("TIPO_DOCUMENTO"));
		ordenes.setTipoDocumentoNombre(rs.getString("NOMBRE_TIPO_DOCUMENTO"));
		ordenes.setNombreProveedor(rs.getString("PROVEEDOR"));
		ordenes.setTipoMoneda(rs.getString("MONEDA"));
		ordenes.setNombreMoneda(rs.getString("NOMBRE_MONEDA"));
//		ordenes.setFecha(rs.getString("FECHA_ORDEN"));
		ordenes.setFormaPago(rs.getString("FORMA_PAGO"));
//		ordenes.setImporte(rs.getString("TOTAL_SOLES"));
		ordenes.setTipoCambio(rs.getString("TIPO_CAMBIO"));
		ordenes.setSolicitante(rs.getString("SOLICITANTE"));
		ordenes.setTipoOrdenAbrev(rs.getString("TIPO_ORDEN"));
		ordenes.setTipoOrdenDescripcion(rs.getString("DESCRIPCION_ORDEN"));
		
		return ordenes;
	}

}
