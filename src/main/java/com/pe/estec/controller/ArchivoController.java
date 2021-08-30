package com.pe.estec.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pe.estec.model.Archivo;
import com.pe.estec.services.ArchivoService;

@Controller
public class ArchivoController {

	@Autowired
	ArchivoService fileService;

	@PostMapping("guardar-file")
	public ResponseEntity<Object> guardarFile() {
		return null;
	}

	@GetMapping("/recuperarEntidadArchivo/{tipoEntidad}/{idEntidad}/{entidadArchivo}/{token}")
	public ResponseEntity<byte[]> recuperarEntidadArchivo(@PathVariable("tipoEntidad") int tipoEntidad,
			@PathVariable("idEntidad") int idEntidad, @PathVariable("entidadArchivo") int entidadArchivo,
			@PathVariable("token") String token) {

		Archivo archivoRecuperado = null;

		byte archivoByteRecuperado[] = null;
		try {
			archivoRecuperado = fileService.recuperarEntidadArchivo(tipoEntidad, idEntidad, entidadArchivo, token);
			archivoByteRecuperado = Base64.getDecoder().decode(archivoRecuperado.getArchivoBase64());
		} catch (Exception ex) {
//			logger.error("Ocurrió un error " + ex.fillInStackTrace());
			ex.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", "inline; filename=" + archivoRecuperado.getNombreArchivo().replace(",", ""));
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
//		respuesta = null;
//		try {
		ResponseEntity respuesta = ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE,
						ObtenerContentType(archivoRecuperado.getNombreArchivo().replace(",", "")))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"inline; filename=" + archivoRecuperado.getNombreArchivo().replace(",", ""))
				.body(archivoByteRecuperado);
//		}catch (Exception e) {
//			respuesta = ResponseEntity.badRequest("No se pudo encontrar archivo").build();
//		}
		return respuesta;
	}

	private String ObtenerContentType(String nombreArchivo) {
		String contenType = "application/octet-stream";
		if (nombreArchivo.toUpperCase().endsWith(".PDF")) {
			contenType = "application/pdf";
		}
		if (nombreArchivo.toUpperCase().endsWith(".JPG")) {
			contenType = "image/jpeg";
		}
		if (nombreArchivo.toUpperCase().endsWith(".PNG")) {
			contenType = "image/png";
		}
//		if (nombreArchivo.toUpperCase().endsWith(".XML")) {
//			contenType = "application/xml";
//		}
		return contenType;
	}
}
