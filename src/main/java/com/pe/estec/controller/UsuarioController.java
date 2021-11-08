package com.pe.estec.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pe.estec.model.Proveedor;
import com.pe.estec.request.ServiceResult;
import com.pe.estec.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET
		, RequestMethod.DELETE})
@RequestMapping("api/admin")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("login-externos")
	public ResponseEntity<Object> loginExternos(String user, String clave){
		ServiceResult<Proveedor> response = usuarioService.authentication(user, clave);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	@GetMapping("validar-proveedor")
	public ResponseEntity<Object> validarProveedor(String nroDocumento){
		ServiceResult<Boolean> response = usuarioService.validarProveedor(nroDocumento);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	
	@PostMapping("guardar-proveedor")
	public ResponseEntity<Object> guardarProveedor(@RequestBody Proveedor proveedor){
		ServiceResult<String> response = usuarioService.guardarProveedor(proveedor);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	@GetMapping("listar-proveedores")
	public ResponseEntity<Object> listarProveedor(Integer estado, Integer tipoCuenta, String usuario, 
			String nroDocumento, Integer tipoDocumento){
		ServiceResult<List<Proveedor>> response = usuarioService.listarProveedor( estado,  tipoCuenta, usuario,
				nroDocumento, tipoDocumento);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	@PostMapping("activar-proveedor")
	public ResponseEntity<Object> activarProveedor(@RequestBody Proveedor proveedor){
		ServiceResult<String> response = usuarioService.activarProveedor(proveedor);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	@GetMapping("listar-proveedor-erp")
	public ResponseEntity<Object> listarProveedorErp(String nroDocumento){
		ServiceResult<Proveedor> response = usuarioService.listarProveedorErp(nroDocumento);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	@PostMapping("modificar-pass-proveedor")
	public ResponseEntity<Object> contrasenaProveedor(@RequestBody Map<String, Object> proveedor){
		ServiceResult<String> response = usuarioService.contrasenaProveedor(
				proveedor.get("user").toString(), 
				proveedor.get("contrasena").toString(),
				Integer.parseInt(proveedor.get("estado").toString()));
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
}
