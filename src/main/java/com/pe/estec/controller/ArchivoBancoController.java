package com.pe.estec.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pe.estec.model.ArchivoBanco;
<<<<<<< HEAD
import com.pe.estec.model.ArchivoBancoDetalle;
=======
>>>>>>> origin/jackleine_aplica_detraccion
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
	
	@GetMapping("/generar-archivo")
	public ResponseEntity<byte[]> generarArchivoBanco(Integer archivoSolicitud){
		ServiceResult<String> response = archivoService.generarArchivo(archivoSolicitud);
			try {
				String demoContent = response.getResultado(); // (2) Dynamic content
		        HttpHeaders httpHeaders = new HttpHeaders();
		        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE); // (3) Content-Type: application/octet-stream
		        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename("archivo-generado.txt").build().toString()); // (4) Content-Disposition: attachment; filename="demo-file.txt"
		        return ResponseEntity.ok().headers(httpHeaders).body(demoContent.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

}
