package com.pe.estec.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pe.estec.model.Archivo;
import com.pe.estec.model.Asiento;
import com.pe.estec.model.Comprobante;
import com.pe.estec.services.ArchivoService;
import com.pe.estec.services.ConsultaDocumentoService;

import net.sf.jasperreports.engine.JasperExportManager;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET
		, RequestMethod.DELETE})
@RequestMapping("api/admin")
public class ArchivoController {

	@Autowired
	ArchivoService fileService;
	
	@Autowired
	ConsultaDocumentoService consultaDocumentoServices;

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
//			logger.error("Ocurri?? un error " + ex.fillInStackTrace());
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
//		if (nombreArchivo.toUpperCase().endsWith(".TXT")) {
//			contenType = "application/json; charset=UTF-8";
//		}
		return contenType;
	}
	
	@GetMapping("/visualizar-asiento/{idComprobante}/{igv}/{detraccion}/{distribucioncod}")
	public ResponseEntity<InputStreamResource> obtenerEECC(@PathVariable("idComprobante") int idComprobante, @PathVariable("igv") int igv ,@PathVariable("detraccion") String detraccion,@PathVariable("distribucioncod") String distribucioncod) {
		try {
			System.out.println(idComprobante);
			List<Asiento> listAsiento = consultaDocumentoServices.consultarAsiento(idComprobante);
			InputStreamResource filePdf = fileService.obtenerEstadoCuentaRep(idComprobante, listAsiento, igv, detraccion, distribucioncod);
			HttpHeaders respHeaders = new HttpHeaders();
			MediaType mediaType = MediaType.parseMediaType("application/pdf");
			respHeaders.setContentType(mediaType);

			ContentDisposition contentDisposition = ContentDisposition.builder("inline").filename("Asiento-documento.pdf")
					.build();

			respHeaders.setContentDisposition(contentDisposition);

			return new ResponseEntity<InputStreamResource>(filePdf, respHeaders, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return new ResponseEntity<InputStreamResource>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
