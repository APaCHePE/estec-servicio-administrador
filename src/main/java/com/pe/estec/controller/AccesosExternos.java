package com.pe.estec.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pe.estec.model.Proveedor;
import com.pe.estec.model.request.ServiceResult;
import com.pe.estec.services.AccesosExternoService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET
		, RequestMethod.DELETE})
@RequestMapping("api/admin")
public class AccesosExternos {

	@Autowired
	private AccesosExternoService accesoService;
	
	@GetMapping("login-externos")
	public ResponseEntity<Object> loginExternos(String user, String clave){
		ServiceResult<Proveedor> response = accesoService.authentication(user, clave);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
}
