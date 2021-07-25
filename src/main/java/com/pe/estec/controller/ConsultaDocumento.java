package com.pe.estec.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pe.estec.model.Facturas;
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
	public ResponseEntity<Object> getFacturas(String numeroFac, String fecInicio, String fecFin, Integer estado) {
		Map<String, Object> respuesta = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
			respuesta.put("result",
					consultaDocumentoServices.consultaFacturas(numeroFac, fecInicio, fecFin, estado));
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
