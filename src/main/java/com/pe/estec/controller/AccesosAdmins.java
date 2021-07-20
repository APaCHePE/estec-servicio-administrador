package com.pe.estec.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccesosAdmins {
	
	@GetMapping("login-interno")
	public ResponseEntity<Object> getOrdenes(Integer usuario, Integer pasword){
		Map<String, Object> respuesta = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
//			capaConsultas.guardarMesaAyuda(guardarMesaAyuda);
			respuesta.put("status", Boolean.TRUE);
			respuesta.put("codigo", status.value());
		}catch(Exception e) {
			status = HttpStatus.NOT_FOUND;
			respuesta.put("status", false);
			respuesta.put("errorMensaje",  e.getMessage());
			respuesta.put("codigo", status.value());
		}
		return new ResponseEntity(respuesta, status);
	}
}
