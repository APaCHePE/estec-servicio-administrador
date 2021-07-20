package com.pe.estec.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccesosExternos {

	@GetMapping("login-externos")
	public ResponseEntity<Object> loginExternos(Integer usuario, Integer password){
		Map<String, Object> respuesta = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
//			capaConsultas.guardarMesaAyuda(guardarMesaAyuda);
			respuesta.put("status", Boolean.TRUE);
			respuesta.put("codigo", status.value());
			respuesta.put("alertErrores", "sin errores");
		}catch(Exception e) {
			status = HttpStatus.NOT_FOUND;
			respuesta.put("status", false);
			respuesta.put("errorMensaje",  e.getMessage());
			respuesta.put("codigo", status.value());
		}
		return new ResponseEntity(respuesta, status);
	}
}
