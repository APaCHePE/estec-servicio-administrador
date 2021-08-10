package com.pe.estec.services;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

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
	public ServiceResult<Map<String, Object>> guardarZip(MultipartFile dataFile);
	public ServiceResult<String> estadoFactura(Integer estado,Integer idComprobante,Integer id008Trazabilidad ,String observacion, String usuarioModificador); 
	public ServiceResult<String> guardarComprobante(Comprobante Comprobante);
	public ServiceResult<String> guardarComprobanteDetalle(ComprobanteDetalle ComprobanteDetalle);
	public List<Comprobante> consultarComprobante( String nroOrden, 
			String fecInicio, String fecFin, Integer estado, String nroDocumento, Integer idComprobante);
	
}
