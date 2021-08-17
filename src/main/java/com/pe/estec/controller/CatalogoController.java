package com.pe.estec.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pe.estec.services.CatalogoService;

@RestController
public class catalogoController {
	
	@Autowired
	CatalogoService catalogoService;
	

		@GetMapping("/consultar-parametro")
		public ResponseEntity<Object> consultaParametro(@RequestParam("idParametroTipo") Integer idParametroTipo) {
			Map<String, Object> respuesta = new HashMap<>();
			HttpStatus status = HttpStatus.OK;
			try {
				respuesta.put("result",
						catalogoService.consultaParametro(idParametroTipo));
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
