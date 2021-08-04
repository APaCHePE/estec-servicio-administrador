package com.pe.estec.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pe.estec.model.Facturas;
import com.pe.estec.model.FacturasDetalle;
import com.pe.estec.model.Orden;
import com.pe.estec.model.OrdenDetalle;
import com.pe.estec.rowmapper.FacturasDetalleRowMapper;
import com.pe.estec.rowmapper.FacturasRowMapper;
import com.pe.estec.rowmapper.OrdenDetalleRowMapper;
import com.pe.estec.rowmapper.OrdenesRowMapper;

@Repository
public class ConsultaDocumentoRepository {

	@Autowired
	JdbcTemplate dao;
	
	public List<Orden> consultaOrdenes(Integer tipoDocumento, String nroOrden, 
			String fecInicio, String fecFin, Integer estado, String nroDocumento) {
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
		if(nroOrden!=null)sql.append(" And MOVD.OC_CNUMORD='"+nroOrden+"' ");
		if(fecFin!=null && fecInicio!= null)sql.append("");
		if(nroDocumento!= null)sql.append("  and MOVD.OC_CCODPRO ='"+nroDocumento+"'  ");
		if(estado!=null)sql.append(" AND MOVD.OC_CESTADO='1' ");
		if(tipoDocumento==null)sql.append(" and MOVC.OC_CTIPORD != 'I'  ");
		sql.append("  Order by MOVD.OC_CNUMORD  ");
		List<Orden> users = dao.query(sql.toString(),new OrdenesRowMapper());
		return users;
	}
	public List<Orden> getOrdenesCabecera(Integer tipoDocumento, String nroOrden, 
			String fecInicio, String fecFin, String nroDocumento) {
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
		sql.append("  left outer join RSFACCAR15..AL0002TABL AS TB_TIP_MON on TB_TIP_MON.TG_CCLAVE = MOVC.OC_CCODMON AND TB_TIP_MON.TG_CCOD = '03'  ");
		sql.append("  left outer join RSFACCAR15..AL0002TABL AS TB_TIP_ORD on TB_TIP_ORD.TG_CCLAVE = MOVC.oc_ctipord AND TB_TIP_ORD.TG_CCOD = '63'  ");
		sql.append("  left outer join RSFACCAR15..AL0002TABL AS TB_TIP_DOC on TB_TIP_DOC.TG_CCLAVE = MOVC.OC_CTIPDOC AND TB_TIP_DOC.TG_CCOD = '04'  ");
		sql.append("  WHERE 1=1 ");
		if(nroDocumento!= null)sql.append("  and MOVC.OC_CCODPRO ='"+nroDocumento+"'  ");
		if(nroOrden!=null)sql.append(" And MOVC.OC_CNUMORD='"+nroOrden+"' ");
		if(fecFin!=null && fecInicio!= null)sql.append("");
//		if(estado!=null)sql.append(" AND MOVD.OC_CESTADO='1' ");
		if(tipoDocumento==null)sql.append(" and MOVC.OC_CTIPORD != 'I'  ");
		System.out.println(sql);
		List<Orden> users = dao.query(sql.toString(),new OrdenesRowMapper());
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
		if(numeroOrden!=null)sql.append(" and oc_cnumord = '"+numeroOrden+"' ");
		System.out.println(sql);
		List<OrdenDetalle> users = dao.query(sql.toString(),new OrdenDetalleRowMapper());
		return users;
	}
	
	public List<Facturas> getFacturasCabecera( String nroFact, 
			String fecInicio, String fecFin, Integer estado, String nroDocumento) {
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
		sql.append(" LEFT JOIN RSFACCAR15..AL0002TABL TIPDOC ON TIPDOC.TG_cclave =PROV.CP_CTIPDOC  AND TIPDOC.TG_CCOD = '04'  ");
		sql.append(" LEFT JOIN RSFACCAR15..AL0002TABL SIT ON SIT.TG_cclave = PROV.CP_CSITUAC AND SIT.TG_CCOD = '54'  ");
		sql.append(" LEFT JOIN RSFACCAR15..AL0002TABL CODPAGO ON SIT.TG_cclave = PROV.CP_CDEBHAB AND CODPAGO.TG_CCOD = '52'  ");
		sql.append(" LEFT JOIN RSCONCAR_TEST..CP0002TAGP DOCREF ON DOCREF.TG_CODIGO = PROV.CP_CTDOCRE AND DOCREF.TG_INDICE = '25'  ");
		sql.append(" left join RSFACCAR15..AL0002TABL AS TB_TIP_MON on TB_TIP_MON.TG_CCLAVE = CP_CCODMON AND TB_TIP_MON.TG_CCOD = '03'  ");
		sql.append(" LEFT JOIN RSFACCAR15..AL0002TABL AREA ON AREA.TG_cclave = PROV.CP_CDEBHAB AND AREA.TG_CCOD = 'R3'  ");
		sql.append(" LEFT JOIN RSCONCAR_TEST..CP0002TAGP CODGASTO ON CODGASTO.TG_CODIGO = PROV.CP_CCOGAST AND CODGASTO.TG_INDICE = '45'  ");
		sql.append(" where cp_Ctipdoc = ('FT')  ");
//		if(nroFact!= null)sql.append(" and f5_cnumdoc like('%"+nroFact+"')");
		if(nroDocumento!= null)sql.append(" and prov.CP_CCODIGO = '"+nroDocumento+"' ");
		sql.append(" order by CP_CFECDOC desc ");
		System.out.println(sql);
		List<Facturas> users = dao.query(sql.toString(),new FacturasRowMapper());
		return users;
	}
	public List<FacturasDetalle> getFacturasDetalle( String nroFact) {
//		addElement();
		StringBuilder sql = new StringBuilder();

		sql.append(" (select  ");
		sql.append(" F6_CCODAGE, F6_CTD,F6_CNUMSER, F6_CNUMDOC,   ");

		System.out.println(sql);
		List<FacturasDetalle> users = dao.query(sql.toString(),new FacturasDetalleRowMapper());
		return users;
	}
}
