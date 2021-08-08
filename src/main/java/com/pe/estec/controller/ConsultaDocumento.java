package com.pe.estec.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pe.estec.model.Comprobante;
import com.pe.estec.model.ComprobanteDetalle;
import com.pe.estec.model.Facturas;
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
	
	/////////////////////////////////////////////
	
	@PostMapping("/estado-factura")
	public ResponseEntity<Object> estadoFactura(
			@RequestParam("numeroFactura") String numeroFactura,@RequestParam("estado") Integer estado){
		System.out.println(numeroFactura);
		System.out.println(estado);
		ServiceResult<String> response = consultaDocumentoServices.estadoFactura(estado, numeroFactura);
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
	public ResponseEntity<Object> consultarComprobante(String numeroFac, String fecInicio, String fecFin, Integer estado, String nroDocumento) {
		Map<String, Object> respuesta = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
			respuesta.put("result",
					consultaDocumentoServices.consultarComprobante(numeroFac, fecInicio, fecFin, estado, nroDocumento));
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
