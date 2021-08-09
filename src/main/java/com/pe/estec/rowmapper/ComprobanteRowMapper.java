package com.pe.estec.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.pe.estec.model.Comprobante;
import com.pe.estec.model.Facturas;

public class ComprobanteRowMapper implements RowMapper<Comprobante>{
	@Override
	public Comprobante mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comprobante comprobante = new Comprobante();
		comprobante.setId_comprobante(rs.getInt("id_comprobante"));
		comprobante.setId_007_tipo_comprobante(rs.getInt("id_007_tipo_comprobante"));
		comprobante.setSerie(rs.getString("serie"));
		comprobante.setNumero(rs.getString("numero"));
		comprobante.setProveedor_id_003_tipo_documento(rs.getInt("proveedor_id_003_tipo_documento"));
		comprobante.setProveedor_numero_documento(rs.getString("proveedor_numero_documento"));
		comprobante.setProveedor_nombre(rs.getString("proveedor_nombre"));
		comprobante.setProveedor_nombre_comercial(rs.getString("proveedor_nombre_comercial"));
		comprobante.setProveedor_direccion(rs.getString("proveedor_direccion"));
		comprobante.setProveedor_zona(rs.getString("proveedor_zona"));
		comprobante.setFecha_emision(rs.getString("fecha_emision"));
		comprobante.setFecha_vencimiento(rs.getString("fecha_vencimiento"));
		comprobante.setId_006_tipo_moneda(rs.getInt("id_006_tipo_moneda"));
		comprobante.setObservacion(rs.getString("observacion"));
		comprobante.setImporte_sub_total(rs.getDouble("importe_sub_total"));
		comprobante.setImporte_anticipios(rs.getDouble("importe_anticipios"));
		comprobante.setImporte_descuentos(rs.getDouble("importe_descuentos"));
		comprobante.setImporte_valor_venta(rs.getDouble("importe_valor_venta"));
		comprobante.setImporte_isc(rs.getDouble("importe_isc"));
		comprobante.setImporte_igv(rs.getDouble("importe_igv"));
		comprobante.setImporte_icbper(rs.getDouble("importe_icbper"));
		comprobante.setImporte_otros_cargos(rs.getDouble("importe_otros_cargos"));
		comprobante.setImporte_otros_tributos(rs.getDouble("importe_otros_tributos"));
		comprobante.setImporte_monto_redondeo(rs.getDouble("importe_monto_redondeo"));
		comprobante.setImporte_total(rs.getDouble("importe_total"));
		comprobante.setOrden_numero(rs.getString("orden_numero"));
		comprobante.setOrden_contrato(rs.getString("orden_contrato"));
		comprobante.setId_004_estado(rs.getInt("id_004_estado"));
		comprobante.setNombre_moneda(rs.getString("nombre_moneda")); 
		comprobante.setNombre_estado(rs.getString("nombre_estado")); 
		
		return comprobante;
	}

}
