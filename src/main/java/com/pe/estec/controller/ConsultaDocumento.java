package com.pe.estec.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pe.estec.model.Archivo;
import com.pe.estec.model.Comprobante;
import com.pe.estec.model.ComprobanteDetalle;
import com.pe.estec.model.request.ServiceResult;
import com.pe.estec.services.ArchivoService;
import com.pe.estec.services.ConsultaDocumentoService;

@RestController
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
	@RequestMapping(value="cargar-zip",method = RequestMethod.POST, consumes= {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE},produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> cargarZip(@RequestParam(name="documento",required=false) MultipartFile  dataFile){
		ServiceResult<Map<String, Object>> response = consultaDocumentoServices.guardarZip(dataFile);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	
	@GetMapping("/estado-factura")
	public ResponseEntity<Object> estadoFactura(
			@RequestParam("idComprobante") Integer idComprobante,@RequestParam("estado") Integer estado,
			@RequestParam("observacion") String observacion,@RequestParam("usuarioModificador") Integer usuarioModificador ){
		ServiceResult<String> response = consultaDocumentoServices.estadoFactura(estado, idComprobante,observacion,usuarioModificador);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	
	@PostMapping("guardar-comprobante")
	public ResponseEntity<Object> guardarComprobante(@RequestBody Comprobante Comprobante){
		ServiceResult<String> response = consultaDocumentoServices.guardarComprobante(Comprobante);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	
	@PostMapping("guardar-detalle-comprobante")
	public ResponseEntity<Object> guardarComprobanteDetalle(@RequestBody ComprobanteDetalle ComprobanteDetalle){
		ServiceResult<String> response = consultaDocumentoServices.guardarComprobanteDetalle(ComprobanteDetalle);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	
	@GetMapping("/consultar-comprobante")
	public ResponseEntity<Object> consultarComprobante(String numeroFac, String fecInicio, String fecFin, Integer estado, String nroDocumento, Integer idComprobante) {
		Map<String, Object> respuesta = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
			respuesta.put("result",
					consultaDocumentoServices.consultarComprobante(numeroFac, fecInicio, fecFin, estado, nroDocumento, idComprobante));
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
	
//	@CrossOrigin(origins = "*")
	@GetMapping("/recuperarEntidadArchivo/{tipoEntidad}/{idEntidad}/{entidadArchivo}")
	public ResponseEntity<byte[]> recuperarEntidadArchivo(@PathVariable("tipoEntidad") int tipoEntidad,
			@PathVariable("idEntidad") int idEntidad, @PathVariable("entidadArchivo") int entidadArchivo) {

		Archivo archivoRecuperado = null;

		byte archivoByteRecuperado[] = null;
		try {
			archivoRecuperado = fileService.recuperarEntidadArchivo();
//tipoEntidad, idEntidad, entidadArchivo
			archivoByteRecuperado = Base64.getDecoder().decode(archivoRecuperado.getArchivo());
		} catch (Exception ex) {
//			logger.error("Ocurrió un error " + ex.fillInStackTrace());
			ex.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", "inline; filename=" + archivoRecuperado.getNombreArchivo().replace(",", ""));
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		ResponseEntity respuesta = ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE,
				ObtenerContentType(archivoRecuperado.getNombreArchivo().replace(",", "")))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"inline; filename=" + archivoRecuperado.getNombreArchivo().replace(",", ""))
				.body(archivoByteRecuperado);
		return respuesta;
	}
	private String ObtenerContentType(String nombreArchivo) {
		String contenType = "application/octet-stream";
		if (nombreArchivo.toUpperCase().endsWith(".PDF")) {
			contenType = "application/pdf";
		}
		if (nombreArchivo.toUpperCase().endsWith(".JPG")) {
			contenType = "image/jpeg";
		}
		if (nombreArchivo.toUpperCase().endsWith(".PNG")) {
			contenType = "image/png";
		}
		return contenType;
	}


}
