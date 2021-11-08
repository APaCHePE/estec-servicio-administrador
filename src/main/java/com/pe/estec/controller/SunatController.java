package com.pe.estec.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pe.estec.dto.ConsultaDTO;
import com.pe.estec.model.RespuestaPIDE;
import com.pe.estec.request.ServiceResult;
import com.pe.estec.util.UtilMap;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET
		, RequestMethod.DELETE})
@RequestMapping("api/admin")
public class SunatController {

//	private 

	@Value("${config.pidesunat.princ.uri}")
	private String uriSUNATprinc;
	@Value("${config.pidesunat.sec.uri}")
	private String uriSUNATsec;
	@Value("${config.pidesunat.t1144.uri}")
	private String uriSUNATt1144;

	@Value("${config.pidesunat.t362.uri}")
	private String uriSUNATt362;

	@Value("${config.pidesunat.dom.uri}")
	private String uriSUNATdom;

	@Value("${config.pidesunat.anexo.uri}")
	private String uriSUNATanexo;

	@Value("${config.pidesunat.t1150.uri}")
	private String uriSUNATt1150;

	@Value("${config.pidesunat.replegal.uri}")
	private String uriSUNATreplegal;

	@PostMapping("/datos-sunat/{nroDocumento}")
	public ResponseEntity<ServiceResult<Map<String, Object>>> consultaDatosSunat(
			@PathVariable("nroDocumento") String nroDocumento) {
		HashMap<String, Object> respuesta = new HashMap<>();
		ServiceResult<Map<String, Object>> resultado = new ServiceResult<Map<String, Object>>();
		HttpStatus estado = HttpStatus.OK;
		RestTemplate restTemplate = new RestTemplate();
		ConsultaDTO consulta = new ConsultaDTO();
		consulta.setDocumentoConsulta(nroDocumento);

		// Datos Principales
		Map<String, Object> respuestaPIDEp = restTemplate.getForObject(uriSUNATprinc + nroDocumento, HashMap.class);
		Map<String, Object> _respuestaPIDEp = (HashMap) respuestaPIDEp.get("list");
		_respuestaPIDEp = (LinkedHashMap) _respuestaPIDEp.get("multiRef");

		RespuestaPIDE datosPrincipales = UtilMap.MapearSunatDatosPrincipales(_respuestaPIDEp);

		// Datos Secundarios
		Map<String, Object> respuestaPIDEs = restTemplate.getForObject(uriSUNATsec + nroDocumento, HashMap.class);
		Map<String, Object> _respuestaPIDEs = (HashMap) respuestaPIDEs.get("list");
		_respuestaPIDEs = (LinkedHashMap) _respuestaPIDEs.get("multiRef");

		RespuestaPIDE datosSecundarios = UtilMap.MapearSunatDatosSecundarios(_respuestaPIDEs);
		// Datos T1144
		RespuestaPIDE datosT1144 = new RespuestaPIDE();
		try {
			Map<String, Object> respuestaPIDEt1144 = restTemplate.getForObject(uriSUNATt1144 + nroDocumento,
					HashMap.class);
			Map<String, Object> _respuestaPIDEt1144 = (HashMap) respuestaPIDEt1144.get("list");
			_respuestaPIDEt1144 = (LinkedHashMap) _respuestaPIDEt1144.get("multiRef");

			if (_respuestaPIDEt1144 != null) {
				datosT1144 = UtilMap.MapearSunatDatosT1144(_respuestaPIDEt1144);
			} else {
				datosT1144 = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			datosT1144 = null;
			respuesta.put("Error en lista 3", e.getMessage());
		}
		// Datos T362
		RespuestaPIDE datosT362 = new RespuestaPIDE();
		try {
			Map<String, Object> respuestaPIDEt362 = restTemplate.getForObject(uriSUNATt362 + nroDocumento,
					HashMap.class);
			Map<String, Object> _respuestaPIDEt362 = (HashMap) respuestaPIDEt362.get("list");
			_respuestaPIDEt362 = (LinkedHashMap) _respuestaPIDEt362.get("multiRef");

			if (_respuestaPIDEt362 != null) {
				datosT362 = UtilMap.MapearSunatDatosT362(_respuestaPIDEt362);
			} else {
				datosT362 = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			datosT362 = null;
			respuesta.put("Error en lista 4", e.getMessage());
		}

		// Datos Domicilio
		RespuestaPIDE datosDomi = new RespuestaPIDE();
		try {
			Map<String, Object> respuestaPIDEdomi = restTemplate.getForObject(uriSUNATdom + nroDocumento,
					HashMap.class);
			Map<String, Object> _respuestaPIDEdomi = (HashMap) respuestaPIDEdomi.get("list");
			_respuestaPIDEdomi = (LinkedHashMap) _respuestaPIDEdomi.get("getDomicilioLegalResponse");

			if (_respuestaPIDEdomi != null) {
				datosDomi = UtilMap.MapearSunatDatosDomLegal(_respuestaPIDEdomi);
			} else {
				datosDomi = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			datosDomi = null;
			respuesta.put("Error en lista 5", e.getMessage());
		}

		// Datos Establecimiento Anexo
		List<RespuestaPIDE> datosEstabAnexo = new ArrayList<RespuestaPIDE>();
		try {
			Map<String, Object> respuestaPIDEestabAnexo = restTemplate.getForObject(uriSUNATanexo + nroDocumento,
					HashMap.class);
			Map<String, Object> _respuestaPIDEestabAnexo = (HashMap) respuestaPIDEestabAnexo.get("list");
			List<Map<String, Object>> _respuestaPIDEestabAnexoList = (ArrayList) _respuestaPIDEestabAnexo
					.get("multiRef");

			if (_respuestaPIDEestabAnexoList != null) {
				for (Map<String, Object> map : _respuestaPIDEestabAnexoList) {
					RespuestaPIDE establecimiento = new RespuestaPIDE();
					establecimiento = UtilMap.MapearSunatDatosEstablecimientoAnexo(map);
					datosEstabAnexo.add(establecimiento);
				}

			} else {
				datosEstabAnexo = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			datosEstabAnexo = null;
			respuesta.put("Error en lista 6", e.getMessage());
		}
		// Datos Establecimiento T1150
		List<RespuestaPIDE> datosT1150 = new ArrayList<RespuestaPIDE>();
		try {
			Map<String, Object> respuestaPIDEt1150 = restTemplate.getForObject(uriSUNATt1150 + nroDocumento,
					HashMap.class);
			Map<String, Object> _respuestaPIDEt1150 = (HashMap) respuestaPIDEt1150.get("list");
			List<Map<String, Object>> _respuestaPIDEt1150List = (ArrayList) _respuestaPIDEt1150.get("multiRef");

			if (_respuestaPIDEt1150List != null) {
				for (Map<String, Object> map : _respuestaPIDEt1150List) {
					RespuestaPIDE establecimiento = new RespuestaPIDE();
					establecimiento = UtilMap.MapearSunatDatosEstablecimientoT1150(map);
					datosT1150.add(establecimiento);
				}
			} else {
				datosT1150 = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			datosT1150 = null;
			respuesta.put("Error en lista 7", e.getMessage());
		}

		// Datos Representante Legal
		RespuestaPIDE datosRep = new RespuestaPIDE();
		try {
			Map<String, Object> respuestaPIDErep = restTemplate.getForObject(uriSUNATreplegal + nroDocumento,
					HashMap.class);
			Map<String, Object> _respuestaPIDErep = (HashMap) respuestaPIDErep.get("list");
			_respuestaPIDErep = (LinkedHashMap) _respuestaPIDErep.get("multiRef");

			if (_respuestaPIDErep != null) {
				datosRep = UtilMap.MapearSunatDatosRepreLegal(_respuestaPIDErep);
			} else {
				datosRep = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			datosRep = null;
			respuesta.put("Error en lista 8", e.getMessage());
		}

		respuesta.put("principal", datosPrincipales);
		respuesta.put("secundario", datosSecundarios);
		respuesta.put("t1144", datosT1144);
		respuesta.put("t362", datosT362);
		respuesta.put("domicilio", datosDomi);
		respuesta.put("establecimiento", datosEstabAnexo);
		respuesta.put("t1150", datosT1150);
		respuesta.put("representante", datosRep);
		respuesta.put("success", Boolean.TRUE);
		respuesta.put("code", estado.value());

//	        consultasPIDERepository.guardarConsulta("CONSULTA SUNAT", consulta, sunat.getCorreoUsuario());
		resultado.setResultado(respuesta);

		return new ResponseEntity<ServiceResult<Map<String, Object>>>(resultado,
				HttpStatus.valueOf(resultado.getHttpStatus()));

	}
}
