package com.pe.estec.services;

import java.util.List;

import com.pe.estec.model.Comprobante;
import com.pe.estec.model.ComprobanteDetalle;
import com.pe.estec.model.Facturas;
import com.pe.estec.model.Orden;
import com.pe.estec.model.request.ServiceResult;

public interface ConsultaDocumentoService {

	public List<Orden> consultaOrdenes(Integer tipoDocumento, String nroOrden, 
			String fecInicio, String fecFin, Integer estado, String nroDocumento);
	public List<Facturas> consultaFacturas( String nroOrden, 
			String fecInicio, String fecFin, Integer estado, String nroDocumento);
	////////////////////////////////////////////////////////////////////////////
	
	public ServiceResult<String> estadoFactura(Integer estado,Integer idComprobante, String observacion, Integer usuarioModificador); 
	public ServiceResult<String> guardarComprobante(Comprobante Comprobante);
	public ServiceResult<String> guardarComprobanteDetalle(ComprobanteDetalle ComprobanteDetalle);
	public List<Comprobante> consultarComprobante( String nroOrden, 
			String fecInicio, String fecFin, Integer estado, String nroDocumento);
	
}
