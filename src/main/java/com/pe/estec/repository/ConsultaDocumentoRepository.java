package com.pe.estec.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.estec.config.Constantes;
import com.pe.estec.model.Archivo;
import com.pe.estec.model.Comprobante;
import com.pe.estec.model.ComprobanteDetalle;
import com.pe.estec.model.ComprobanteTrazabilidad;
import com.pe.estec.model.Contrato;
import com.pe.estec.model.Facturas;
import com.pe.estec.model.Orden;
import com.pe.estec.model.OrdenDetalle;
import com.pe.estec.rowmapper.ComprobanteDetalleRowMapper;
import com.pe.estec.rowmapper.ComprobanteRowMapper;
import com.pe.estec.rowmapper.ComprobanteTrazabilidadRowMapper;
import com.pe.estec.rowmapper.ContratoRowMapper;
import com.pe.estec.rowmapper.FacturasRowMapper;
import com.pe.estec.rowmapper.OrdenDetalleRowMapper;
import com.pe.estec.rowmapper.OrdenesRowMapper;
import com.pe.estec.util.SqlReturning;

@Repository
public class ConsultaDocumentoRepository {

	@Autowired
	JdbcTemplate sqlServer;
	
	
	
	public List<Orden> consultaOrdenes(Integer tipoDocumento, String nroOrden, String fecInicio, String fecFin,
			Integer estado, String nroDocumento) {
		StringBuilder sql = new StringBuilder();
		sql.append(" Select  ");
		sql.append(" MOVD.OC_CNUMORD AS ORDEN,  ");
		sql.append(" MOVD.OC_CCODPRO AS RUC,  ");
		sql.append(" MOVC.OC_CRAZSOC AS PROVEEDOR, ");
		sql.append(" MOVC.OC_CTIPDOC AS TIPO_DOCUMENTO, ");
		sql.append(" MOVC.OC_CCOTIZA AS DOCUMENTO, ");
		sql.append(" MOVC.OC_CCODMON AS MONEDA, ");
		sql.append(" MOVC.OC_CFORPA1 AS FORMA_PAGO, ");
		sql.append(" MOVC.OC_NTIPCAM AS TIPO_CAMBIO, ");
		sql.append(" MOVC.OC_CSOLICT AS SOLICITANTE, ");
		sql.append(" MOVC.OC_CNUMREF AS OBSERVACIONES, ");
		sql.append(" OC_CITEM AS ITEM,  ");
		sql.append(" OC_CCODIGO AS CODIGO,  ");
		sql.append(" OC_CCODREF AS DOC_REF, ");
		sql.append(" OC_CDESREF AS DESCRIPCION, ");
		sql.append(" OC_CUNIDAD AS UNIDAD,  ");
		sql.append(" OC_NCANORD AS CANTIDAD_ORD, ");
		sql.append(" OC_NPREUNI, OC_NPREUN2,  ");
		sql.append(" OC_NDSCPIT, OC_NDESCIT, OC_NDSCPAD, OC_NDESCAD, OC_NDSCPOR, "
				+ " OC_NDESCTO, OC_NIGV, OC_NIGVPOR, OC_NCANSAL, OC_NTOTUS, OC_NTOTMN, OC_COMENTA, ");
		sql.append(" OC_CESTADO, MOVD.OC_DFECDOC, MOVD.OC_CTIPORD, OC_CCENCOS, OC_CSOLICI,  ");
		sql.append(" MOVD.OC_DFECENT, OC_CITMPOR, OC_CDSCPOR, OC_CIGVPOR, OC_CISCPOR,  ");
		sql.append(" OC_NIMPUS AS IMPORTE_SOLES, OC_NIMPMN AS IMPORTE_DOLARES, "
				+ "	oc_nimpfac, oc_nimpfob, oc_nimpcf, oc_nimpcif  ");
		sql.append("  FROM RSFACCAR15..CO0002MOVD AS MOVD  ");
		sql.append("  left outer join RSFACCAR15..CO0002MOVC AS MOVC on MOVC.OC_CNUMORD = MOVD.OC_CNUMORD   ");
		sql.append("  left outer join RSFACCAR15..AL0002AADV AS AADV on AADV.AV_CCODART = MOVD.OC_CCODIGO      "
				+ "   and AADV.AV_CCODPAI = MOVC.OC_CCOPAIS   ");
		sql.append("  left outer join RSFACCAR15..AL0002TABL AS TABL on TABL.TG_CCLAVE = AADV.AV_CPARARA  ");
		sql.append("  and TABL.TG_CCOD = '78'   ");
		sql.append("   Where 1=1 ");
		if (nroOrden != null)
			sql.append(" And MOVD.OC_CNUMORD='" + nroOrden + "' ");
		if (fecFin != null && fecInicio != null)
			sql.append("");
		if (nroDocumento != null)
			sql.append("  and MOVD.OC_CCODPRO ='" + nroDocumento + "'  ");
		if (estado != null)
			sql.append(" AND MOVD.OC_CESTADO='1' ");
		if (tipoDocumento == null)
			sql.append(" and MOVC.OC_CTIPORD != 'I'  ");
		sql.append("  Order by MOVD.OC_CNUMORD  ");
		List<Orden> users = sqlServer.query(sql.toString(), new OrdenesRowMapper());
		return users;
	}

	public List<Orden> getOrdenesCabecera(Integer tipoDocumento, String nroOrden, String fecInicio, String fecFin,
			String nroDocumento) {
		StringBuilder sql = new StringBuilder();
		sql.append(" Select  ");
		sql.append("  movC.OC_CNUMORD as NUMERO_ORDEN, ");
		sql.append("  movC.OC_CCODPRO AS RUC_PROVEEDOR, ");
		sql.append(" MOVC.OC_CTIPDOC AS TIPO_DOCUMENTO, ");
		sql.append(" TB_TIP_DOC.tg_cdescri as NOMBRE_TIPO_DOCUMENTO, ");
		sql.append(" MOVC.OC_CRAZSOC AS PROVEEDOR, ");
		sql.append(" MOVC.OC_CCOTIZA AS DOCUMENTO, ");
		sql.append(" MOVC.OC_CCODMON AS MONEDA, ");
		sql.append(" TB_TIP_MON.tg_cdescri as nombre_moneda, ");
		sql.append(" MOVC.OC_CFORPA1 AS FORMA_PAGO, ");
		sql.append(" MOVC.OC_NTIPCAM AS TIPO_CAMBIO, ");
		sql.append(" MOVC.OC_CCODSOL AS SOLICITANTE, ");
		sql.append(" MOVC.OC_CNUMREF AS OBSERVACIONES, ");
		sql.append(" movc.oc_ctipord as tipo_orden, ");
		sql.append(" TB_TIP_ORD.tg_cdescri as DESCRIPCION_ORDEN, MOVC.oc_dfecdoc as fecha_orden,");
		sql.append(" MOVC.oc_nimpmn as importe_soles, MOVC.oc_csitord as estado ");
		sql.append("  from RSFACCAR15..CO0002MOVC MOVC  ");
		sql.append(
				"  left outer join RSFACCAR15..AL0002TABL AS TB_TIP_MON on TB_TIP_MON.TG_CCLAVE = MOVC.OC_CCODMON AND TB_TIP_MON.TG_CCOD = '03'  ");
		sql.append(
				"  left outer join RSFACCAR15..AL0002TABL AS TB_TIP_ORD on TB_TIP_ORD.TG_CCLAVE = MOVC.oc_ctipord AND TB_TIP_ORD.TG_CCOD = '63'  ");
		sql.append(
				"  left outer join RSFACCAR15..AL0002TABL AS TB_TIP_DOC on TB_TIP_DOC.TG_CCLAVE = MOVC.OC_CTIPDOC   ");
		sql.append("  WHERE 1=1 ");
		if (nroDocumento != null)
			sql.append("  and MOVC.OC_CCODPRO ='" + nroDocumento + "'  ");
		if (nroOrden != null)
			sql.append(" And MOVC.OC_CNUMORD like('%" + nroOrden + "%') ");
		if (fecFin != null && fecInicio != null)
			sql.append("");
//		if(estado!=null)sql.append(" AND MOVD.OC_CESTADO='1' ");
		if (tipoDocumento == null)
			//sql.append(" and MOVC.OC_CTIPORD != 'I'  ");
		System.out.println(sql);
		List<Orden> users = sqlServer.query(sql.toString(), new OrdenesRowMapper());
		return users;
	}

	public List<OrdenDetalle> getOrdenesDetalle(String numeroOrden) {
		StringBuilder sql = new StringBuilder();
		sql.append(" Select  ");
		sql.append(" oc_citem as nroItem, ");
		sql.append(" oc_ccodigo as codigo, ");
		sql.append(" oc_cdesref as descripcion_orden, ");
		sql.append(" oc_ncanord as cantidad_orden, ");
		sql.append(" oc_npreuni as precio_unitario, ");
		sql.append(" oc_nigv as precio_de_igv, ");
		sql.append(" oc_ntotus as total_dolares, ");
		sql.append(" oc_ntotmn as total_soles, ");
		sql.append(" oc_comenta as comentario_orden, ");
		sql.append(" oc_cestado as estado_orden, ");
		sql.append(" oc_ctipord as tipo_orden_item, ");
		sql.append(" oc_csolici as usuario_solicitante, ");
		sql.append(" oc_dfecdoc as FECHA_ORDEN ");
		sql.append(" FROM RSFACCAR15..CO0002MOVD ");
		sql.append(" where 1=1");
		if (numeroOrden != null && !numeroOrden.equals("null"))
			sql.append(" and oc_cnumord = '" + numeroOrden + "' ");
		List<OrdenDetalle> users = sqlServer.query(sql.toString(), new OrdenDetalleRowMapper());
		return users;
	}
	
	public List<Contrato> getContrato(Integer nroContrato) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select id_contrato, descripcion, fecha_contrato  ");
		sql.append(" from pruebas.dbo.CONTRATO ");
		sql.append(" where 1=1");
		if (nroContrato != null && !nroContrato.equals("null"))
			sql.append(" and id_contrato = " + nroContrato );
		List<Contrato> listaContrato = sqlServer.query(sql.toString(), new ContratoRowMapper());
		return listaContrato;
	}

	public List<Facturas> getFacturasCabecera(String nroFact, String fecInicio, String fecFin, Integer estado,
			String nroDocumento) {
//		addElement();
		StringBuilder sql = new StringBuilder();
		sql.append(" Select   ");
		sql.append(" PROV.CP_CNUMDOC AS NUMERO_FACTURA , ");
		sql.append(" PROV.CP_CCODIGO AS RUC, ");
		sql.append(" PROV.CP_CTIPDOC AS TIP_DOC_CODIGO, ");
		sql.append(" PROV.CP_NIGVMN AS IGV, ");
		sql.append(" TIPDOC.TG_CDESCRI AS TIP_DOC_DESCRIPCION, ");
		sql.append(" CP_CFECDOC AS FECHA_DOCUMENTO, ");
		sql.append(" CP_CFECVEN AS FECHA_VENTA, ");
		sql.append(" CP_CFECREC AS FECHA_RECIBIDO, ");
		sql.append(" CP_CSITUAC AS SITUACION_DOCUMENTO, ");
		sql.append(" SIT.TG_CDESCRI AS DESCRI_SITUACION, ");
		sql.append(" CP_CCOMPRO AS CODIGO_COMPRA, ");
		sql.append(" CP_CDEBHAB AS CODIGO_PAGO, ");
		sql.append(" CODPAGO.TG_CDESCRI AS DESCRIPCION_PAGO, ");
		sql.append(" CP_CCODMON AS CODIGO_MONEDA, ");
		sql.append(" TB_TIP_MON.TG_CDESCRI AS NOMBRE_MONEDA, ");
		sql.append(" CP_NTIPCAM AS _TIPO_CAMBIO, ");
		sql.append(" CP_NIMPOMN AS IMPORTE_SOLES, ");
		sql.append(" CP_NIMPOUS AS IMPORTE_DOLARES, ");
		sql.append(" CP_NSALDMN AS SALDO_SOLES, ");
		sql.append(" CP_NSALDUS AS SALDO_DOLARES, ");
		sql.append(" CP_CAREA AS COD_AREA, ");
		sql.append(" AREA.TG_CDESCRI AS NOMBRE_AREA, ");
		sql.append(" CP_CTDOCRE AS DOC_REFERENCIA, ");
		sql.append(" DOCREF.TG_DESCRI AS DES_DOC_REFERENCIA, ");
		sql.append(" CP_CCOGAST AS CODIGO_GASTO, ");
		sql.append(" CODGASTO.TG_DESCRI AS DESCRIPVION_COD_GASTO, ");
		sql.append(" CP_CDESCRI AS DESCRIPCION_FACTURA ");
		sql.append(" From RSCONCAR..CP0002CART PROV  ");
		sql.append(
				" LEFT JOIN RSFACCAR15..AL0002TABL TIPDOC ON TIPDOC.TG_cclave =PROV.CP_CTIPDOC  AND TIPDOC.TG_CCOD = '04'  ");
		sql.append(" LEFT JOIN RSFACCAR15..AL0002TABL SIT ON SIT.TG_cclave = PROV.CP_CSITUAC AND SIT.TG_CCOD = '54'  ");
		sql.append(
				" LEFT JOIN RSFACCAR15..AL0002TABL CODPAGO ON SIT.TG_cclave = PROV.CP_CDEBHAB AND CODPAGO.TG_CCOD = '52'  ");
		sql.append(
				" LEFT JOIN RSCONCAR_TEST..CP0002TAGP DOCREF ON DOCREF.TG_CODIGO = PROV.CP_CTDOCRE AND DOCREF.TG_INDICE = '25'  ");
		sql.append(
				" left join RSFACCAR15..AL0002TABL AS TB_TIP_MON on TB_TIP_MON.TG_CCLAVE = CP_CCODMON AND TB_TIP_MON.TG_CCOD = '03'  ");
		sql.append(
				" LEFT JOIN RSFACCAR15..AL0002TABL AREA ON AREA.TG_cclave = PROV.CP_CDEBHAB AND AREA.TG_CCOD = 'R3'  ");
		sql.append(
				" LEFT JOIN RSCONCAR_TEST..CP0002TAGP CODGASTO ON CODGASTO.TG_CODIGO = PROV.CP_CCOGAST AND CODGASTO.TG_INDICE = '45'  ");
		sql.append(" where cp_Ctipdoc = ('FT')  ");
//		if(nroFact!= null)sql.append(" and f5_cnumdoc like('%"+nroFact+"')");
		if (nroDocumento != null)
			sql.append(" and prov.CP_CCODIGO = '" + nroDocumento + "' ");
		sql.append(" order by CP_CFECDOC desc ");
		System.out.println(sql);
		List<Facturas> users = sqlServer.query(sql.toString(), new FacturasRowMapper());
		return users;
	}

	/*
	 * public void estadoFactura(String usuarioResponsable, Integer estado, Integer
	 * idComprobante, String observacion, Integer usuarioModificador) throws
	 * Exception { StringBuilder sql = new StringBuilder();
	 * System.out.println("numero de factura:" + idComprobante);
	 * sql.append(" update pruebas.dbo.comprobante ");
	 * sql.append(" set id_004_estado=" + estado +
	 * ", fecha_modificacion = GETDATE (), "); if (observacion != null)
	 * sql.append(" observacion_estado_usuario = '" + observacion + "', "); if
	 * (usuarioResponsable != null) sql.append(" usuario_responsable = '" +
	 * usuarioResponsable + "', "); if (usuarioModificador != null)
	 * sql.append(" id_usuario_modificador = " + usuarioModificador + " ");
	 * sql.append(" where id_comprobante=" + idComprobante + " ");
	 * 
	 * System.out.println(sql.toString()); // Object[] params= new Object[] {estado,
	 * idComprobante}; sqlServer.update(sql.toString()); }
	 */

	public void estadoFactura(String usuarioResponsable, Integer estado, Integer idComprobante) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update pruebas.dbo.comprobante ");
		sql.append(" set id_004_estado=" + estado + " ");
		if (usuarioResponsable != null)
			sql.append(" ,usuario_responsable = '" + usuarioResponsable + "'");
		sql.append(" where id_comprobante=" + idComprobante + " ");
		sqlServer.update(sql.toString());
	}

	public void estadoFacturaTrazabilidad(Integer idComprobante, Integer id008Trazabilidad, String observacionTra,
			String usuarioModificador) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into pruebas.dbo.COMPROBANTE_TRAZABILIDAD ");
		sql.append(" (id_comprobante, id_008_estado_trazabilidad, fecha_registro, observacion ,usuario_registro )");
		sql.append(" values(" + idComprobante + "," + id008Trazabilidad + ",GETDATE(),'" + observacionTra + "','"
				+ usuarioModificador + "') ");

		sqlServer.update(sql.toString());
	}

	public Integer guardarComprobante(Comprobante comprobante) throws Exception {
		StringBuilder sql = new StringBuilder();
		comprobante.setSerie(comprobante.getSerie().substring(0, 4));
		String fec1 = comprobante.getFechaEmision().substring(8, 10) + "-"
				+ comprobante.getFechaEmision().substring(5, 7) + "-" + comprobante.getFechaEmision().substring(0, 4);
		String fec2 = comprobante.getFechaEmision().substring(8, 10) + "-"
				+ comprobante.getFechaEmision().substring(5, 7) + "-" + comprobante.getFechaEmision().substring(0, 4);
		comprobante.setFechaEmision(fec1);
		comprobante.setFechaVencimiento(fec2);

		sql.append(" INSERT INTO pruebas..COMPROBANTE ");
		sql.append(
				" (id_007_tipo_comprobante, serie, numero, proveedor_id_003_tipo_documento, proveedor_numero_documento ");
		sql.append(" ,proveedor_nombre, proveedor_nombre_comercial, fecha_emision ");
		sql.append(" ,fecha_vencimiento,  id_006_tipo_moneda,  importe_sub_total,  importe_descuentos ");
		sql.append(" ,importe_valor_venta,  importe_igv, importe_total ");
		if (comprobante.getOrdenNumero() != null)
			sql.append(" ,orden_numero ");
		if (comprobante.getOrdenContrato() != null)
			sql.append(" ,orden_contrato ");
		sql.append(" , id_004_estado, usuario_responsable, proveedor_direccion, proveedor_zona, fecha_creacion) ");
		sql.append(" VALUES(" + comprobante.getId007TipoComprobante() + ",'" + comprobante.getSerie() + "',"
				+ comprobante.getNumero());
		sql.append("," + comprobante.getProveedorId003TipoDocumento() + ",");
		sql.append("'" + comprobante.getProveedorNumeroDocumento() + "','" + comprobante.getProveedorNombre() + "' ");
		sql.append(",'" + comprobante.getProveedorNombreComercial()+"'");
		sql.append(",CONVERT(datetime, '"+ comprobante.getFechaEmision() + "', 103) ");
		sql.append(",CONVERT(datetime, '" + comprobante.getFechaVencimiento() + "', 103) ");
		sql.append("," + comprobante.getId006TipoMoneda() + "," + comprobante.getImporteSubTotal());
		sql.append(", "+comprobante.getImporteDescuentos());
		sql.append(" ," + comprobante.getImporteValorVenta());
		sql.append("," + comprobante.getImporteIgv() + "," + comprobante.getImporteTotal() + "");
		if (comprobante.getOrdenNumero() != null)
			sql.append(", '" + comprobante.getOrdenNumero()+"' ");
		if (comprobante.getOrdenContrato() != null)
			sql.append(", '" + comprobante.getOrdenContrato()+"' ");
		sql.append(", 9 ,  '" + comprobante.getUsuarioResponsable() + "' ");
		sql.append(", '" + comprobante.getProveedorDireccion() + "', '" + comprobante.getProveedorZona() + "', GETDATE()  ) ");
		System.out.println(sql.toString());
		SqlReturning db = new SqlReturning(sqlServer);

		Long idGenerado = db.insertaDataParams(sql.toString());
		return idGenerado.intValue();
	}

	public void guardarComprobanteDetalle(ComprobanteDetalle comprobanteDetalle, Integer idComprobante)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO pruebas.dbo.COMPROBANTE_DETALLE ");
		sql.append(" (id_comprobante,cantidad,unidad_medida ");
		sql.append(" ,descripcion,valor_unitario,icbper) ");
		sql.append(" VALUES(?,?,?,?,?,?) ");
		Object[] params = new Object[] { idComprobante, comprobanteDetalle.getCantidad(),
				comprobanteDetalle.getUnidadMedida(), comprobanteDetalle.getDescripcion(),
				comprobanteDetalle.getValorUnitario(), comprobanteDetalle.getIcbper() };
		sqlServer.update(sql.toString(), params);
	}

	public List<Comprobante> consultarComprobante(String usuariosresponsable, String nroFact, String fecInicio,
			String fecFin, Integer estado, String nroDocumento, Integer idComprobante, Integer tipoComprobante) {
		StringBuilder sql = new StringBuilder();
		sql.append(" Select ");
		sql.append(
				" id_comprobante,id_007_tipo_comprobante,serie,numero,proveedor_id_003_tipo_documento,proveedor_numero_documento ");
		sql.append(" ,proveedor_nombre,proveedor_nombre_comercial,proveedor_direccion,proveedor_zona,fecha_emision ");
		sql.append(
				" ,fecha_vencimiento,id_006_tipo_moneda,observacion,importe_sub_total,importe_anticipios,importe_descuentos ");
		sql.append(
				" ,importe_valor_venta,importe_isc,importe_igv,importe_icbper,importe_otros_cargos,importe_otros_tributos ");
		sql.append(
				" ,importe_monto_redondeo,importe_total,orden_numero,orden_contrato,id_004_estado,  p.nombre as nombre_moneda, p2.nombre as nombre_estado, p3.nombre as nombre_tipo_comprobante");
		sql.append(" ,trim(usuario_responsable) as usuario_responsable ");
		sql.append(" FROM pruebas.dbo.COMPROBANTE com ");
		sql.append(" left join pruebas.dbo.parametro p on com.id_006_tipo_moneda = p.id_parametro");
		sql.append(" left join pruebas.dbo.parametro p2 on com.id_004_estado = p2.id_parametro");
		sql.append(" left join pruebas.dbo.parametro p3 on com.id_007_tipo_comprobante = p3.id_parametro");
		sql.append(" where 1=1");
		if (usuariosresponsable != null)
			sql.append(" and usuario_responsable = '" + usuariosresponsable + "' ");
		if (estado != null)
			sql.append(" and id_004_estado = " + estado + " ");
		if (idComprobante != null)
			sql.append(" and id_comprobante = " + idComprobante + " ");
		if (nroDocumento != null)
			sql.append(" and proveedor_numero_documento = '" + nroDocumento + "' ");
		if (tipoComprobante != null)
			sql.append(" and id_007_tipo_comprobante =" + tipoComprobante );
		if (nroFact != null)
			sql.append(" and numero = '" + nroFact + "' ");
		if (fecInicio != null && fecFin != null)
			sql.append(" and fecha_emision BETWEEN '"+fecInicio+"' AND '"+fecFin+"'");
		sql.append(" order by fecha_emision desc ");
		List<Comprobante> users = sqlServer.query(sql.toString(), new ComprobanteRowMapper());
		return users;
	}

	public List<ComprobanteDetalle> consultarComprobanteDetalle(Integer idComprobante) {
		StringBuilder sql = new StringBuilder();
		sql.append(" Select  ");
		sql.append(" id_comprobante_detalle,id_comprobante,cantidad,unidad_medida ");
		sql.append(" ,descripcion,valor_unitario,icbper ");
		sql.append(" FROM pruebas.dbo.COMPROBANTE_DETALLE ");
		sql.append(" where 1=1");
		if (idComprobante != null)
			sql.append(" and id_comprobante = '" + idComprobante + "' ");
		List<ComprobanteDetalle> users = sqlServer.query(sql.toString(), new ComprobanteDetalleRowMapper());
		return users;
	}

	public List<ComprobanteTrazabilidad> consultarComprobanteTrazabilidad(Integer idComprobante) {
		StringBuilder sql = new StringBuilder();
		sql.append(" Select  ");
		sql.append(" id_comprobante_trazabilidad,id_comprobante,id_008_estado_trazabilidad ");
		sql.append(" ,fecha_registro,observacion,usuario_registro, p.nombre as nombre_estado ");
		sql.append(" FROM pruebas.dbo.COMPROBANTE_TRAZABILIDAD tr ");
		sql.append(" left join pruebas.dbo.parametro p on tr.id_008_estado_trazabilidad = p.id_parametro");
		sql.append(" where 1=1");
		if (idComprobante != null)
			sql.append(" and id_comprobante = '" + idComprobante + "' ");
		List<ComprobanteTrazabilidad> users = sqlServer.query(sql.toString(), new ComprobanteTrazabilidadRowMapper());
		return users;
	}
	
	public Integer guardarAdjunto(Archivo archivo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO PRUEBAS..ADJUNTOS ");
		sql.append(" ( ID_DOCUMENTO, NOMBRE_ADJUNTO, FEC_CREACION ) ");
		sql.append(" VALUES( "+archivo.getIdDocumento()+", '"+archivo.getNombreArchivo()+"', GETDATE()) ");
//		Object[] params = new Object[] { archivo.getIdArchivo(), archivo.getArchivo() };
		SqlReturning db = new SqlReturning(sqlServer);

		Long idGenerado = db.insertaDataParams(sql.toString());
		return idGenerado.intValue();
	}
	public Integer guardarArchivo(Archivo archivo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO PRUEBAS..ARCHIVO  ");
		sql.append(" (ID_PARAMETRO,ID_DOCUMENTO, ID_DOCUMENTO_AUXILIAR, VERSION, FECHA_CREACION, ESTADO) ");
		sql.append(" VALUES( "+archivo.getIdParametro()+", "+archivo.getIdDocumento()+", "+archivo.getIdDocumentoArchivo()+", 1, GETDATE(), 1) ");
//		Object[] params = new Object[] { archivo.getIdArchivo(), archivo.getArchivo() };
		SqlReturning db = new SqlReturning(sqlServer);
		Long idGenerado = db.insertaDataParams(sql.toString());
		return idGenerado.intValue();
	}
	public void guardarArchivoRepo(Archivo archivo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO PRUEBAS..ARCHIVO_REPOSITORIO  ");
		sql.append(" (ID_ARCHIVO,ARCHIVO) ");
		sql.append(" VALUES(?,?) ");
		Object[] params = new Object[] { archivo.getIdArchivo(), archivo.getArchivo() };
		sqlServer.update(sql.toString(), params);
	}

}
