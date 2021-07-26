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
		sql.append(" TB_TIP_ORD.tg_cdescri as DESCRIPCION_ORDEN ");
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
		sql.append(" oc_csolici as usuario_solicitante ");
//		sql.append(" oc_dfecdoc as FECHA_ORDEN ");
		sql.append(" FROM RSFACCAR15..CO0002MOVD ");
		sql.append(" where 1=1");
		if(numeroOrden!=null)sql.append(" and oc_cnumord = '00010002738' ");
		List<OrdenDetalle> users = dao.query(sql.toString(),new OrdenDetalleRowMapper());
		return users;
	}
	public List<Facturas> consultaFacturas( String nroFact, 
			String fecInicio, String fecFin, Integer estado) {
//		addElement();
		StringBuilder sql = new StringBuilder();
		sql.append(" (Select  ");
		sql.append(" F6_CTD+F6_CNUMSER+F6_CNUMDOC FACTURA, ");
		sql.append(" F6_CCODAGE, F6_CTD,F6_CNUMSER, F6_CNUMDOC,   ");
		sql.append(" Convert(datetime,F5_DFECDOC,103) AS FECHA, ");
		sql.append(" F5_CCODCLI AS CODIGO_CLIENTE, ");
		sql.append(" F5_CNOMBRE AS CLIENTE, ");
		sql.append(" F5_CFORVEN AS FORMA_VENTA, ");
		sql.append(" F5_CALMA AS ALMACEN_CA, ");
		sql.append(" F7_CALMA AS ALMACEN_SE, ");
		sql.append(" F6_CITEM, ");
		sql.append(" F7_CSERIE AS LOTE, ");
		sql.append(" F6_CCODIGO AS CODIGO_PRODUCTO,  ");
		sql.append(" F6_CDESCRI AS PRODUCTO,  ");
		sql.append(" F6_CUNIDAD AS UNIDAD, ");
		sql.append(" CANTIDAD = CASE WHEN F6_CUNIDAD = 'TM' THEN F7_NCANTID*1000  ");
		sql.append(" 				WHEN F6_NCANTID = '0.000' THEN F6_NCANTID - 1 ");
		sql.append(" 				ELSE F7_NCANTID END, ");
		sql.append(" F6_NPRECIO , ");
		sql.append(" F5_CCODMON, ");
		sql.append(" PRECIO = CASE  ");
		sql.append(" 					WHEN F6_CUNIDAD = 'TM' THEN F6_NPRECIO/1000 ");
		sql.append(" 					WHEN F5_CCODMON = 'MN' THEN F6_NPRECIO/F5_NTIPCAM ");
		sql.append(" 					ELSE F6_NPRECIO ");
		sql.append(" 				END, ");
		sql.append(" IMPORTE = CASE  ");
		sql.append(" 					WHEN F6_CUNIDAD = 'TM' THEN F7_NCANTID*1000  ");
		sql.append(" 					WHEN F6_NCANTID = '0.000' THEN F6_NPRECIO ");
		sql.append(" 					ELSE F7_NCANTID ");
		sql.append(" 				END ");
		sql.append(" 				* ");
		sql.append(" 				CASE  ");
		sql.append(" 					WHEN F5_CCODMON = 'MN' THEN F6_NPRECIO/F5_NTIPCAM ");
		sql.append(" 					WHEN F6_CUNIDAD = 'TM' THEN F6_NPRECIO/1000 ");
		sql.append(" 					WHEN F6_NCANTID = '0.000' THEN F6_NCANTID - 1 ");
		sql.append(" 					ELSE F6_NPRECIO ");
		sql.append(" 				END, ");
		sql.append("  ");
		sql.append(" F6_CTIPITM, ");
		sql.append(" F5_CVENDE AS VENDEDOR ");
		sql.append(" From (RSFACCAR15..FT0002ACUD LEFT JOIN RSFACCAR15..FT0002ACUC ON F5_CNUMDOC=F6_CNUMDOC AND  ");
		sql.append(" F5_CTD = F6_CTD AND F5_CESTADO = F6_CESTADO) ");
		sql.append(" LEFT JOIN RSFACCAR15..FT0002ACUS ON F7_CNUMDOC=F6_CNUMDOC "
				+ "AND F7_CITEM = F6_CITEM AND F7_CCODIGO = F6_CCODIGO  ");
		sql.append(" Where  F5_CFORVEN <> 'O' AND F6_CCODAGE= '0001' AND F6_CNUMSER='F001' "
				+ "AND F6_CTIPITM <> 'FLT'   ");
		sql.append(" AND F6_CCODIGO <> 'TXT'  AND F6_CCODIGO <> 'V000000001' "
				+ "AND F6_CCODIGO <> 'ANTD01US'  ");
		if(fecFin!=null && fecInicio!= null)sql.append(" AND CONVERT(DATETIME,F6_DFECDOC,103) "+
				" BETWEEN Convert(datetime,'"+"01/01/2018"+"',103)  "+
				" AND Convert(datetime,'"+"30/12/2025"+"',103) ");
		if(estado!=null)sql.append(" AND F5_CESTADO = 'V' ");
//		if(tipoDocumento==null)sql.append(" MOVC.OC_CTIPORD != 'I'  ");
		if(nroFact!=null)sql.append(" AND F6_CNUMDOC = '"+nroFact+"' ");
		sql.append(" AND F6_CCODIGO <> 'ANTH01US' AND F6_CCODIGO <> 'M000000001' ");
		sql.append("  ");
		sql.append(" ) ");
		sql.append(" UNION ");
		sql.append(" ( ");
		sql.append(" Select  ");
		sql.append(" F6_CTD+F6_CNUMSER+F6_CNUMDOC FACTURA, ");
		sql.append(" F6_CCODAGE, F6_CTD,F6_CNUMSER, F6_CNUMDOC,   ");
		sql.append(" Convert(datetime,F5_DFECDOC,103) AS FECHA, ");
		sql.append(" F5_CCODCLI AS CODIGO_CLIENTE, ");
		sql.append(" F5_CNOMBRE AS CLIENTE, ");
		sql.append(" F5_CFORVEN AS FORMA_VENTA, ");
		sql.append(" F5_CALMA AS ALMACEN_CA, ");
		sql.append(" F7_CALMA AS ALMACEN_SE, ");
		sql.append(" F6_CITEM, ");
		sql.append(" F7_CSERIE AS LOTE, ");
		sql.append(" F6_CCODIGO AS CODIGO_PRODUCTO,  ");
		sql.append(" F6_CDESCRI AS PRODUCTO,  ");
		sql.append(" F6_CUNIDAD AS UNIDAD, ");
		sql.append(" CANTIDAD = CASE WHEN F6_CUNIDAD = 'TM' THEN F7_NCANTID*1000  ");
		sql.append(" 				WHEN F6_NCANTID = '0.000' THEN F6_NCANTID - 1 ");
		sql.append(" 				ELSE F7_NCANTID END, ");
		sql.append(" F6_NPRECIO , ");
		sql.append(" F5_CCODMON, ");
		sql.append(" PRECIO = CASE  ");
		sql.append(" 					WHEN F6_CUNIDAD = 'TM' THEN F6_NPRECIO/1000 ");
		sql.append(" 					WHEN F5_CCODMON = 'MN' THEN F6_NPRECIO/F5_NTIPCAM ");
		sql.append(" 					ELSE F6_NPRECIO ");
		sql.append(" 				END, ");
		sql.append(" IMPORTE = CASE  ");
		sql.append(" 					WHEN F6_CUNIDAD = 'TM' THEN F7_NCANTID*1000  ");
		sql.append(" 					WHEN F6_NCANTID = '0.000' THEN F6_NPRECIO ");
		sql.append(" 					ELSE F7_NCANTID ");
		sql.append(" 				END ");
		sql.append(" 				* ");
		sql.append(" 				CASE  ");
		sql.append(" 					WHEN F5_CCODMON = 'MN' THEN F6_NPRECIO/F5_NTIPCAM ");
		sql.append(" 					WHEN F6_CUNIDAD = 'TM' THEN F6_NPRECIO/1000 ");
		sql.append(" 					WHEN F6_NCANTID = '0.000' THEN F6_NCANTID - 1 ");
		sql.append(" 					ELSE F6_NPRECIO ");
		sql.append(" 				END, ");
		sql.append("  ");
		sql.append(" F6_CTIPITM, ");
		sql.append(" F5_CVENDE AS VENDEDOR ");
		sql.append(" From (RSFACCAR15..FT0002FACD LEFT JOIN RSFACCAR15..FT0002FACC ON F5_CNUMDOC=F6_CNUMDOC AND F5_CTD = F6_CTD  ");
		sql.append(" AND F5_CESTADO = F6_CESTADO) ");
		sql.append(" LEFT JOIN RSFACCAR15..FT0002FSER ON F7_CNUMDOC=F6_CNUMDOC AND F7_CITEM = F6_CITEM AND F7_CCODIGO = F6_CCODIGO  ");
		sql.append(" Where  F5_CFORVEN <> 'O' AND F6_CCODAGE= '0001' AND F6_CNUMSER='F001' AND F6_CTIPITM <> 'FLT'  ");
		sql.append(" AND F6_CCODIGO <> 'TXT'  AND F6_CCODIGO <> 'V000000001' AND F6_CCODIGO <> 'ANTD01US' AND F6_CCODIGO <> 'ANTH01US'  ");
		sql.append(" AND F6_CCODIGO <> 'M000000001' ");
		sql.append(" ");
		if(fecFin!=null && fecInicio!= null)sql.append(" AND CONVERT(DATETIME,F6_DFECDOC,103) "+
				" BETWEEN Convert(datetime,'"+"01/01/2018"+"',103)  "+
				" AND Convert(datetime,'"+"30/12/2025"+"',103) ");
		
		if(estado!=null)sql.append(" AND F5_CESTADO = 'V' ");
//		if(tipoDocumento==null)sql.append(" MOVC.OC_CTIPORD != 'I'  ");
		if(nroFact!=null)sql.append(" AND F6_CNUMDOC = '"+nroFact+"' ");
		sql.append("  ");
		sql.append(" ) ");
		sql.append(" ORDER BY F6_CCODAGE,F6_CTD,F6_CNUMSER,F6_CNUMDOC,F6_CITEM  ");
		System.out.println(sql);
		List<Facturas> users = dao.query(sql.toString(),new FacturasRowMapper());
		return users;
	}
	public List<Facturas> getFacturasCabecera( String nroFact, 
			String fecInicio, String fecFin, Integer estado) {
//		addElement();
		StringBuilder sql = new StringBuilder();
		sql.append(" ( ");
		sql.append(" select  ");
		sql.append(" F5_CTD+F5_CNUMSER+F5_CNUMDOC as factura  ");
		sql.append(" , F5_CTD,F5_CNUMSER, F5_CNUMDOC,   ");
		sql.append(" Convert(datetime,F5_DFECDOC,103) AS FECHA, ");
		sql.append(" F5_CCODCLI AS CODIGO_CLIENTE, ");
		sql.append(" F5_CNOMBRE AS CLIENTE, ");
		sql.append(" F5_CFORVEN AS FORMA_VENTA, ");
		sql.append(" F5_CALMA AS ALMACEN_CA, ");
		sql.append(" F5_CCODMON as moneda, ");
		sql.append(" F5_CVENDE AS VENDEDOR ");
		sql.append(" from RSFACCAR15..FT0002ACUC where F5_CFORVEN <> 'O' AND F5_CESTADO = 'V' and f5_cnumdoc='0001155' ");
		sql.append(" )union ALL  ");
		sql.append(" ( ");
		sql.append(" select  ");
		sql.append(" F5_CTD+F5_CNUMSER+F5_CNUMDOC as factura  ");
		sql.append(" , F5_CTD,F5_CNUMSER, F5_CNUMDOC,   ");
		sql.append(" Convert(datetime,F5_DFECDOC,103) AS FECHA, ");
		sql.append(" F5_CCODCLI AS CODIGO_CLIENTE, ");
		sql.append(" F5_CNOMBRE AS CLIENTE, ");
		sql.append(" F5_CFORVEN AS FORMA_VENTA, ");
		sql.append(" F5_CALMA AS ALMACEN_CA, ");
		sql.append(" F5_CCODMON as moneda, ");
		sql.append(" F5_CVENDE AS VENDEDOR ");
		sql.append(" from RSFACCAR15..FT0002FACC ");
		sql.append(" where F5_CFORVEN <> 'O' AND F5_CESTADO = 'V' and f5_cnumdoc='0001155' ");
		sql.append(" ) ");
		sql.append(" ORDER BY F5_CTD,F5_CNUMSER,F5_CNUMDOC  ");
		System.out.println(sql);
		List<Facturas> users = dao.query(sql.toString(),new FacturasRowMapper());
		return users;
	}
	public List<FacturasDetalle> getFacturasDetalle( String nroFact) {
//		addElement();
		StringBuilder sql = new StringBuilder();
		

		sql.append(" (select  ");
		sql.append(" F6_CCODAGE, F6_CTD,F6_CNUMSER, F6_CNUMDOC,   ");
		sql.append(" F6_CCODIGO AS CODIGO_PRODUCTO,  ");
		sql.append(" F6_CDESCRI AS PRODUCTO,  ");
		sql.append(" F6_CUNIDAD AS UNIDAD, ");
		sql.append(" F6_CTIPITM, ");
		sql.append(" F6_NPRECIO ");
		sql.append(" from RSFACCAR15..FT0002ACUD ");
		sql.append(" where f6_cnumdoc='"+nroFact+"' ");
		sql.append(" AND F6_CCODAGE= '0001' AND F6_CNUMSER='F001' AND F6_CTIPITM <> 'FLT' AND F6_CCODIGO <> 'TXT'  AND F6_CCODIGO <> 'V000000001'  ) ");
		sql.append(" union ALL  ");
		sql.append(" (select  ");
		sql.append(" F6_CCODAGE, F6_CTD,F6_CNUMSER, F6_CNUMDOC,   ");
		sql.append(" F6_CCODIGO AS CODIGO_PRODUCTO,  ");
		sql.append(" F6_CDESCRI AS PRODUCTO,  ");
		sql.append(" F6_CUNIDAD AS UNIDAD, ");
		sql.append(" F6_CTIPITM, ");
		sql.append(" F6_NPRECIO ");
		sql.append(" From RSFACCAR15..FT0002FACD ");
		sql.append(" where f6_cnumdoc='"+nroFact+"' ");
		sql.append(" AND F6_CCODAGE= '0001' AND F6_CNUMSER='F001' AND F6_CTIPITM <> 'FLT' AND F6_CCODIGO <> 'TXT'  AND F6_CCODIGO <> 'V000000001'  )  ");
		sql.append(" ORDER BY F6_CCODAGE,F6_CTD,F6_CNUMSER,F6_CNUMDOC ");
		
		
//		if(fecFin!=null && fecInicio!= null)sql.append(" AND CONVERT(DATETIME,F6_DFECDOC,103) "+
//				" BETWEEN Convert(datetime,'"+"01/01/2018"+"',103)  "+
//				" AND Convert(datetime,'"+"30/12/2025"+"',103) ");
		
		
		System.out.println(sql);
		List<FacturasDetalle> users = dao.query(sql.toString(),new FacturasDetalleRowMapper());
		return users;
	}
}
