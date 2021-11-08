package com.pe.estec.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pe.estec.model.Mensaje;
import com.pe.estec.model.MensajeVm;
import com.pe.estec.services.MailService;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET
		, RequestMethod.DELETE})
@RequestMapping("api/admin")
public class MensajeriaController {
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value="envia-email", method = RequestMethod.POST)
	public ResponseEntity<String> enviaEmail(@Valid @RequestBody Mensaje mensaje){
		try {
			mailService.enviaMail(mensaje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="envia-email-vm", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> enviaEmailMultiple(@Valid @RequestBody MensajeVm mensaje){
		Map<String, Object> respuesta = new HashMap<String, Object>();
		HttpStatus estadoProceso;
		
		try {
			mailService.enviaMailVm(mensaje);

			estadoProceso = HttpStatus.OK;
			respuesta.put("message", "El correo se envio correctamente");
			
		} catch (Exception e) {
			e.printStackTrace();
			estadoProceso = HttpStatus.BAD_REQUEST;
			respuesta.put("message", "El mensaje no se pudo enviar");
		}
		
		respuesta.put("codigo", estadoProceso.value());

		return new ResponseEntity<Map<String, Object>>(respuesta, estadoProceso);
	}
}
