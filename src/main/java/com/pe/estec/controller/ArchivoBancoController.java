package com.pe.estec.controller;

import java.util.List;

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

import com.pe.estec.model.ArchivoBanco;
import com.pe.estec.model.ArchivoBancoDetalle;
import com.pe.estec.request.ServiceResult;
import com.pe.estec.services.ArchivoBancoService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET
		, RequestMethod.DELETE})
@RequestMapping("api/admin")
public class ArchivoBancoController {

	@Autowired
	private ArchivoBancoService archivoService;
	
	@PostMapping("nuevo-lote-archivo")
	public ResponseEntity<ServiceResult<String>> crearLoteArchivo(@RequestBody ArchivoBanco nuevoArchivo){
		ServiceResult<String> response = archivoService.crearLoteArchivo(nuevoArchivo);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	
	@GetMapping("consulta-lote-archivo")
	public ResponseEntity<ServiceResult<List<ArchivoBanco>>> obtenerLoteArchivo(Integer idArchivo){
		ServiceResult<List<ArchivoBanco>> response = archivoService.obtenerLoteArchivo(idArchivo);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	
	@GetMapping("consulta-lote-detalle-archivo")
	public ResponseEntity<ServiceResult<List<ArchivoBancoDetalle>>> obtenerLoteDetalleArchivo(Integer idArchivo){
		ServiceResult<List<ArchivoBancoDetalle>> response = archivoService.obtenerLoteDetalleArchivo(idArchivo);
		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
	}
	
//	@GetMapping("nuevo-lote")
//	public ResponseEntity<ServiceResult<>> obtenerLoteArchivoDetalle(@RequestBody ArchivoBanco nuevoArchivo){
//		ServiceResult<String> response = archivoService.obtenerLoteArchivoDetalle(nuevoArchivo);
//		return new ResponseEntity(response, HttpStatus.valueOf(response.getHttpStatus()));
//	}
}
