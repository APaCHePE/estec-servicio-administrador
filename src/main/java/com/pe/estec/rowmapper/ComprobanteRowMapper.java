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
		comprobante.setIdComprobante(rs.getInt("id_comprobante"));
		comprobante.setId007TipoComprobante(rs.getInt("id_007_tipo_comprobante"));
		comprobante.setSerie(rs.getString("serie"));
		comprobante.setNumero(rs.getString("numero"));
		comprobante.setProveedorId003TipoDocumento(rs.getInt("proveedor_id_003_tipo_documento"));
		comprobante.setProveedorNumeroDocumento(rs.getString("proveedor_numero_documento"));
		comprobante.setProveedorNombre(rs.getString("proveedor_nombre"));
		comprobante.setProveedorNombreComercial(rs.getString("proveedor_nombre_comercial"));
		comprobante.setProveedorDireccion(rs.getString("proveedor_direccion"));
		comprobante.setProveedorZona(rs.getString("proveedor_zona"));
		comprobante.setFechaEmision(rs.getString("fecha_emision"));
		comprobante.setFechaVencimiento(rs.getString("fecha_vencimiento"));
		comprobante.setId006TipoMoneda(rs.getInt("id_006_tipo_moneda"));
		comprobante.setObservacion(rs.getString("observacion"));
		comprobante.setImporteSubTotal(rs.getDouble("importe_sub_total"));
		comprobante.setImporteAnticipios(rs.getDouble("importe_anticipios"));
		comprobante.setImporteDescuentos(rs.getDouble("importe_descuentos"));
		comprobante.setImporteValorVenta(rs.getDouble("importe_valor_venta"));
		comprobante.setImporteIsc(rs.getDouble("importe_isc"));
		comprobante.setImporteIgv(rs.getDouble("importe_igv"));
		comprobante.setImporteIcbper(rs.getDouble("importe_icbper"));
		comprobante.setImporteOtrosCargos(rs.getDouble("importe_otros_cargos"));
		comprobante.setImporteOtrosTributos(rs.getDouble("importe_otros_tributos"));
		comprobante.setImporteMontoRedondeo(rs.getDouble("importe_monto_redondeo"));
		comprobante.setImporteTotal(rs.getDouble("importe_total"));
		comprobante.setOrdenNumero(rs.getString("orden_numero"));
		comprobante.setOrdenContrato(rs.getInt("orden_contrato"));
		comprobante.setId004Estado(rs.getInt("id_004_estado"));
		comprobante.setNombreMoneda(rs.getString("nombre_moneda")); 
		comprobante.setNombreEstado(rs.getString("nombre_estado")); 
		comprobante.setNombreTipoComprobante(rs.getString("nombre_tipo_comprobante"));
		comprobante.setUsuarioResponsable(rs.getString("usuario_responsable")); 
		
		return comprobante;
	}

}
