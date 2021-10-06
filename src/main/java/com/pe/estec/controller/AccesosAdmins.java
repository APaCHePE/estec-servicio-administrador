package com.pe.estec.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pe.estec.model.Proveedor;
import com.pe.estec.request.ServiceResult;
import com.pe.estec.services.AccesosAdminService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET
		, RequestMethod.DELETE})
@RequestMapping("api/admin")
public class AccesosAdmins {
	@Autowired
	private AccesosAdminService accesoService;

	@GetMapping("login-interno")
	public ResponseEntity<Object> loginExternos(String user, String clave) {
		ServiceResult<Proveedor> response = accesoService.authentication(user, clave);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
}
