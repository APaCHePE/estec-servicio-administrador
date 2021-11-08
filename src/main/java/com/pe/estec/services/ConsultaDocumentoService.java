package com.pe.estec.services;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.pe.estec.model.Asiento;
import com.pe.estec.model.Comprobante;
import com.pe.estec.model.Contrato;
import com.pe.estec.model.Facturas;
import com.pe.estec.model.Orden;
import com.pe.estec.request.ServiceResult;

public interface ConsultaDocumentoService {

	public List<Orden> consultaOrdenes(Integer tipoDocumento, String nroOrden, 
			String fecInicio, String fecFin, Integer estado, String nroDocumento);
	public List<Contrato> getContrato( Integer nroContrato);
	public Integer grabarAsiento(Asiento asiento) throws Exception;
	public List<Facturas> consultaFacturas( String nroOrden, 
			String fecInicio, String fecFin, Integer estado, String nroDocumento);
	public ServiceResult<Map<String, Object>> guardarZip(MultipartFile archivoZip, MultipartFile archivoPdf,
			MultipartFile  archivoInforme, MultipartFile archivoGuia);
	public ServiceResult<String> estadoFactura(String usuarioResponsable,Integer estado,Integer idComprobante,Integer id008Trazabilidad ,String observacion, String usuarioModificador); 
	public ServiceResult<String> guardarComprobante(Comprobante Comprobante);
	public List<Comprobante> consultarComprobante(String usuariosresponsable, String nroOrden, 
			String fecInicio, String fecFin, Integer estado, String nroDocumento, Integer idComprobante, Integer tipoComprobante);
	public ServiceResult<Map<String, Object>> cargarFilesHonorarios(MultipartFile archivoZip, MultipartFile archivoPdf,
			MultipartFile  archivoInforme, Integer idDocumento);
	
}
