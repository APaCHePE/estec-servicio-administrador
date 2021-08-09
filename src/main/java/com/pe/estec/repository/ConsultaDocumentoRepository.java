package com.pe.estec.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.estec.config.Constantes;
import com.pe.estec.model.Archivo;
import com.pe.estec.model.Comprobante;
import com.pe.estec.model.ComprobanteDetalle;
import com.pe.estec.model.Facturas;
import com.pe.estec.model.Orden;
import com.pe.estec.model.OrdenDetalle;
import com.pe.estec.rowmapper.ComprobanteDetalleRowMapper;
import com.pe.estec.rowmapper.ComprobanteRowMapper;
import com.pe.estec.rowmapper.FacturasRowMapper;
import com.pe.estec.rowmapper.OrdenDetalleRowMapper;
import com.pe.estec.rowmapper.OrdenesRowMapper;

@Repository
public class ConsultaDocumentoRepository {

	@Autowired
	JdbcTemplate dao;

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
		sql.append(" MOVD.OC_DFECENT, OC_CITMPOR, OC_CDSCPOR, OC_CIGVPOR, OC_CISCPOR  ");
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
		List<Orden> users = dao.query(sql.toString(), new OrdenesRowMapper());
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
		sql.append(" MOVC.OC_CSOLICT AS SOLICITANTE, ");
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
				"  left outer join RSFACCAR15..AL0002TABL AS TB_TIP_DOC on TB_TIP_DOC.TG_CCLAVE = MOVC.OC_CTIPDOC AND TB_TIP_DOC.TG_CCOD = '04'  ");
		sql.append("  WHERE 1=1 ");
		if (nroDocumento != null)
			sql.append("  and MOVC.OC_CCODPRO ='" + nroDocumento + "'  ");
		if (nroOrden != null)
			sql.append(" And MOVC.OC_CNUMORD='" + nroOrden + "' ");
		if (fecFin != null && fecInicio != null)
			sql.append("");
//		if(estado!=null)sql.append(" AND MOVD.OC_CESTADO='1' ");
		if (tipoDocumento == null)
			sql.append(" and MOVC.OC_CTIPORD != 'I'  ");
		System.out.println(sql);
		List<Orden> users = dao.query(sql.toString(), new OrdenesRowMapper());
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
		List<OrdenDetalle> users = dao.query(sql.toString(), new OrdenDetalleRowMapper());
		return users;
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
		List<Facturas> users = dao.query(sql.toString(), new FacturasRowMapper());
		return users;
	}

	public void estadoFactura(Integer estado, Integer idComprobante, String observacion, Integer usuarioModificador)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		System.out.println("numero de factura:" + idComprobante);
		sql.append(" update pruebas.dbo.comprobante ");
		sql.append(" set id_004_estado=" + estado + ", fecha_modificacion = GETDATE (), ");
		if (observacion != null)
			sql.append(" observacion_estado_usuario = '" + observacion + "', ");
		if (usuarioModificador != null)
			sql.append(" id_usuario_modificador = " + usuarioModificador + " ");
		sql.append(" where id_comprobante=" + idComprobante + " ");
		
		System.out.println(sql.toString());
		// Object[] params= new Object[] {estado, idComprobante};
		dao.update(sql.toString());
	}

	public void guardarComprobante(Comprobante comprobante) throws Exception {
		StringBuilder sql = new StringBuilder();
		comprobante.setSerie( comprobante.getSerie().substring(0,4) );
		System.out.println("fec dia  "+comprobante.getFecha_emision().substring(8,10));
		System.out.println("fec mes "+comprobante.getFecha_emision().substring(5,7));
		System.out.println("fec anio "+comprobante.getFecha_emision().substring(0,4));
		String fec1 =  comprobante.getFecha_emision().substring(8,10)+"-"+comprobante.getFecha_emision().substring(5,7)+"-" +comprobante.getFecha_emision().substring(0,4);
		String fec2 =  comprobante.getFecha_emision().substring(8,10)+"-"+comprobante.getFecha_emision().substring(5,7)+"-" +comprobante.getFecha_emision().substring(0,4);
		comprobante.setFecha_emision(fec1);
		comprobante.setFecha_vencimiento(fec2);
		
		sql.append(" INSERT INTO pruebas..COMPROBANTE ");
		sql.append(
				" (id_007_tipo_comprobante, serie, numero, proveedor_id_003_tipo_documento, proveedor_numero_documento ");
		sql.append(" ,proveedor_nombre, proveedor_nombre_comercial, fecha_emision ");
		sql.append(" ,fecha_vencimiento,  id_006_tipo_moneda,  importe_sub_total,  importe_descuentos ");
		sql.append(" ,importe_valor_venta,  importe_igv ");
		sql.append(" ,importe_total, id_004_estado) ");
		sql.append(" VALUES(" + comprobante.getId_007_tipo_comprobante() + ",'" + comprobante.getSerie() + "',"+ comprobante.getNumero());
		sql.append("," + comprobante.getProveedor_id_003_tipo_documento() + ",");
		sql.append("'"+comprobante.getProveedor_numero_documento() + "','" + comprobante.getProveedor_nombre()+"'");
		sql.append(",'" + comprobante.getProveedor_nombre_comercial() + "',CONVERT(datetime, '" + comprobante.getFecha_emision() + "', 103),");
		sql.append("CONVERT(datetime, '"+comprobante.getFecha_vencimiento()+"', 103)");
		sql.append("," + comprobante.getId_006_tipo_moneda() + "," + comprobante.getImporte_sub_total() + ","
				+ comprobante.getImporte_descuentos());
		sql.append("," + comprobante.getImporte_valor_venta() + "," + comprobante.getImporte_igv() + "," + comprobante.getImporte_total() + "," + 9
				+ ") ");
		
		System.out.println(sql.toString());

		dao.update(sql.toString());
	}

	public void guardarComprobanteDetalle(ComprobanteDetalle ComprobanteDetalle) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO pruebas.dbo.COMPROBANTE_DETALLE ");
		sql.append(" (id_comprobante_detalle,id_comprobante,cantidad,unidad_medida ");
		sql.append(" ,descripcion,valor_unitario,icbper) ");
		sql.append(" VALUES(?,?,?,?,?,?,?) ");
		Object[] params = new Object[] { ComprobanteDetalle.getId_comprobante_detalle(),
				ComprobanteDetalle.getId_comprobante(), ComprobanteDetalle.getCantidad(),
				ComprobanteDetalle.getUnidad_medida(), ComprobanteDetalle.getDescripcion(),
				ComprobanteDetalle.getValor_unitario(), ComprobanteDetalle.getIcbper() };
		dao.update(sql.toString(), params);
	}

	public List<Comprobante> consultarComprobante(String nroFact, String fecInicio, String fecFin, Integer estado,
			String nroDocumento) {
		StringBuilder sql = new StringBuilder();
		System.out.println("entrooooooooooo");
		sql.append(" Select ");
		sql.append(
				" id_comprobante,id_007_tipo_comprobante,serie,numero,proveedor_id_003_tipo_documento,proveedor_numero_documento ");
		sql.append(" ,proveedor_nombre,proveedor_nombre_comercial,proveedor_direccion,proveedor_zona,fecha_emision ");
		sql.append(
				" ,fecha_vencimiento,id_006_tipo_moneda,observacion,importe_sub_total,importe_anticipios,importe_descuentos ");
		sql.append(
				" ,importe_valor_venta,importe_isc,importe_igv,importe_icbper,importe_otros_cargos,importe_otros_tributos ");
		sql.append(
				" ,importe_monto_redondeo,importe_total,orden_numero,orden_contrato,id_004_estado,  p.nombre as nombre_moneda, p2.nombre as nombre_estado ");
		sql.append(" FROM pruebas.dbo.COMPROBANTE com ");
		sql.append(" left join pruebas.dbo.parametro p on com.id_006_tipo_moneda = p.id_parametro");
		sql.append(" left join pruebas.dbo.parametro p2 on com.id_004_estado = p2.id_parametro");
		sql.append(" where 1=1");
		if (estado != null)
			sql.append(" and id_004_estado = " + estado + " ");
		if (nroDocumento != null)
			sql.append(" and proveedor_numero_documento = '" + nroDocumento + "' ");
		if (nroFact != null)
			sql.append(" and numero = '" + nroFact + "' ");
		sql.append(" order by fecha_emision desc ");
		System.out.println(sql);
		List<Comprobante> users = dao.query(sql.toString(), new ComprobanteRowMapper());
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
		List<ComprobanteDetalle> users = dao.query(sql.toString(), new ComprobanteDetalleRowMapper());
		return users;
	}

	public void guardarFile(Archivo archivo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO PRUEBAS..ARCHIVO_REPOSITORIO  ");
		sql.append(" (ID_ARCHIVO,ARCHIVO) ");
		sql.append(" VALUES(?,?) ");
		Object[] params = new Object[] { archivo.getIdArchivo(), archivo.getArchivo() };
		dao.update(sql.toString(), params);
	}

}