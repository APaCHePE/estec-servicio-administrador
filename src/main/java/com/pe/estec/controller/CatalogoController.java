package com.pe.estec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.estec.model.Catalogo;
import com.pe.estec.model.request.ServiceResult;
import com.pe.estec.services.CatalogoService;

@RestController
public class CatalogoController {
 
	@Autowired
	private CatalogoService catalogoService;
	
	@GetMapping("obtenerCatalago")
	public ResponseEntity obtenerCatalogo(String idElemento, String idGrupo){
		ServiceResult<List<Catalogo>> response = catalogoService.obtenerCatalogo(idElemento, idGrupo);
		return new ResponseEntity<Object>(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
}
