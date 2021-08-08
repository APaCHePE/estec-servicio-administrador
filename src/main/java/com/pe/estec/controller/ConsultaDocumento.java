package com.pe.estec.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pe.estec.model.Proveedor;
import com.pe.estec.model.request.ServiceResult;
import com.pe.estec.services.ConsultaDocumentoService;

@RestController
public class ConsultaDocumento {

	@Autowired
	ConsultaDocumentoService consultaDocumentoServices;

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
	
	@PostMapping("/estado-factura")
	public ResponseEntity<Object> estadoFactura(
			@RequestParam("numeroFactura") String numeroFactura,@RequestParam("estado") Integer estado){
		System.out.println(numeroFactura);
		System.out.println(estado);
		ServiceResult<String> response = consultaDocumentoServices.estadoFactura(numeroFactura);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	
}
