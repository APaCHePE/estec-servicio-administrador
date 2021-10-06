package com.pe.estec.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pe.estec.model.Comprobante;
import com.pe.estec.request.ServiceResult;
import com.pe.estec.services.ArchivoService;
import com.pe.estec.services.ConsultaDocumentoService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET
		, RequestMethod.DELETE})
@RequestMapping("api/admin")
public class ConsultaDocumento {

	@Autowired
	ConsultaDocumentoService consultaDocumentoServices;
	@Autowired
	ArchivoService fileService;

	@GetMapping("/getOrdenes")
	public ResponseEntity<Object> getOrdenes(Integer tipoDocumento, String nroOrden, String fecInicio, String fecFin,
			Integer estado, String nroDocumento) {
		Map<String, Object> respuesta = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
			respuesta.put("result",
					consultaDocumentoServices.consultaOrdenes(tipoDocumento, nroOrden, fecInicio, fecFin, estado, nroDocumento));
			respuesta.put("status", Boolean.TRUE);
			respuesta.put("codigo", status.value());
		} catch (Exception e) {
			status = HttpStatus.NOT_FOUND;
			respuesta.put("status", false);
			respuesta.put("errorMensaje", e.getMessage());
			respuesta.put("codigo", status.value());
		}
		return new ResponseEntity(respuesta, status);
	}
	
	@GetMapping("/getContrato")
	public ResponseEntity<Object> getContrato( Integer nroContrato) {
		Map<String, Object> respuesta = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
			respuesta.put("result",
					consultaDocumentoServices.getContrato(nroContrato));
			respuesta.put("status", Boolean.TRUE);
			respuesta.put("codigo", status.value());
		} catch (Exception e) {
			status = HttpStatus.NOT_FOUND;
			respuesta.put("status", false);
			respuesta.put("errorMensaje", e.getMessage());
			respuesta.put("codigo", status.value());
		}
		return new ResponseEntity(respuesta, status);
	}


	@GetMapping("/getFacturas")
	public ResponseEntity<Object> getFacturas(String numeroFac, String fecInicio, String fecFin, Integer estado, String nroDocumento) {
		Map<String, Object> respuesta = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
			respuesta.put("result",
					consultaDocumentoServices.consultaFacturas(numeroFac, fecInicio, fecFin, estado, nroDocumento));
			respuesta.put("status", Boolean.TRUE);
			respuesta.put("codigo", status.value());
		} catch (Exception e) {
			status = HttpStatus.NOT_FOUND;
			respuesta.put("status", false);
			respuesta.put("errorMensaje", e.getMessage());
			respuesta.put("codigo", status.value());
		}
		return new ResponseEntity(respuesta, status);
	}
	
	@PostMapping(value="crear-documento-comprobante-proveedor", consumes= {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> cargarZip(MultipartFile  archivoPdf, MultipartFile archivoZip,
			MultipartFile  archivoInforme, MultipartFile archivoGuia){
		ServiceResult<Map<String, Object>> response = consultaDocumentoServices.guardarZip(archivoZip, archivoPdf, archivoInforme, archivoGuia);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	
	@PostMapping(value="crear-recibo-honorarios-proveedor", consumes= {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> cargarFilesHonorarios(MultipartFile  archivoPdf, MultipartFile archivoZip,
			MultipartFile  archivoInforme, Integer idDocumento){
		ServiceResult<Map<String, Object>> response = consultaDocumentoServices.cargarFilesHonorarios(archivoZip, archivoPdf, archivoInforme, idDocumento);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	
	@GetMapping("estado-factura")
	public ResponseEntity<Object> estadoFactura(@RequestParam("usuarioResponsable") String usuarioResponsable,
			@RequestParam("idComprobante") Integer idComprobante,@RequestParam("estado") Integer estado,@RequestParam("id008Trazabilidad") Integer id008Trazabilidad,
			@RequestParam("observacion") String observacion,@RequestParam("usuarioModificador") String usuarioModificador ){
		ServiceResult<String> response = consultaDocumentoServices.estadoFactura(usuarioResponsable,estado, idComprobante,id008Trazabilidad,observacion,usuarioModificador);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	
	@PostMapping("guardar-comprobante")
	public ResponseEntity<Object> guardarComprobante(@RequestBody Comprobante Comprobante){
		ServiceResult<String> response = consultaDocumentoServices.guardarComprobante(Comprobante);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	 
	@GetMapping("/consultar-comprobante")
	public ResponseEntity<Object> consultarComprobante(String usuariosresponsable, String numeroFac, String fecInicio, 
			String fecFin, Integer estado, String nroDocumento, Integer idComprobante, Integer tipoComprobante) {
		Map<String, Object> respuesta = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
			respuesta.put("result",
					consultaDocumentoServices.consultarComprobante(usuariosresponsable, numeroFac, fecInicio, fecFin, estado, nroDocumento, idComprobante, tipoComprobante));
			respuesta.put("status", Boolean.TRUE);
			respuesta.put("codigo", status.value());
		} catch (Exception e) {
			status = HttpStatus.NOT_FOUND;
			respuesta.put("status", false);
			respuesta.put("errorMensaje", e.getMessage());
			respuesta.put("codigo", status.value());
		}
		return new ResponseEntity(respuesta, status);
	}

}
