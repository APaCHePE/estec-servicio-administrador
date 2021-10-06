package com.pe.estec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.pe.estec.config.Constantes;
import com.pe.estec.util.Util;

import com.pe.estec.comunes.model.bean.CuentaMensaje;
import com.pe.estec.comunes.model.bean.FileAdjunto;
import com.pe.estec.comunes.model.bean.Mensaje;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CorreoServiceImple {

	Logger logger = LoggerFactory.getLogger(CorreoServiceImple.class);
	
	@Autowired
	private Constantes constantes;

	public String correoRegistro(String nombreUsuario, String correo, String correoCopia, String plantilla)
			throws Exception {
		System.out.println(constantes.getCarpetaMail()+"");
		// Obtenemos y actualizamos la plantilla
		logger.info("Correo registro " + constantes.getUrlPlantillabase()+""+constantes.getCarpetaMail());
		String htmlTemplate;
		
		htmlTemplate = Util.obtenerPlantillaCorreoMiraflores(constantes.getUrlPlantillabase(),
				constantes.getCarpetaMail(),plantilla);

		logger.info("plantilla " + htmlTemplate);
		htmlTemplate = Util.reemplazaSeccionPlantilla(htmlTemplate, "{usuario}", nombreUsuario.toUpperCase());
		return htmlTemplate;
//		List<FileAdjunto> adjunto = jasper(solicitante, c, area, ubicacion, codigoReserva, imagenqr);

	}
	public String correoActivacion(String nombreUsuario, String contrasenia, String correo, String correoCopia, String plantilla)
			throws Exception {
		String htmlTemplate;
		
		htmlTemplate = Util.obtenerPlantillaCorreoMiraflores(constantes.getUrlPlantillabase(),
				constantes.getCarpetaMail(),plantilla);

		htmlTemplate = Util.reemplazaSeccionPlantilla(htmlTemplate, "{usuario}", nombreUsuario.toUpperCase());
		htmlTemplate = Util.reemplazaSeccionPlantilla(htmlTemplate, "{pass}", contrasenia);
		return htmlTemplate;

	}
	public void enviaReporteNuevo(String htmlTemplate, String correoDestino, String correoCopia, String asunto,
			List<FileAdjunto> adjunto) throws Exception{
		Mensaje mensaje = new Mensaje();
		CuentaMensaje destino = new CuentaMensaje();
		destino.setEmail(correoDestino.toUpperCase());

		mensaje.setCuentaDestino(destino);

		if (correoCopia!= null && !correoCopia.equals("0")) {
			List<CuentaMensaje> listaCuentaCopia = new ArrayList();
			if (correoCopia.contains(",")) {
				String[] copiasCorreo = correoCopia.split(",");

				for (String destinoCopia : copiasCorreo) {
					CuentaMensaje cuentaCopia = new CuentaMensaje();
					cuentaCopia.setEmail(destinoCopia);

					listaCuentaCopia.add(cuentaCopia);
				}
			} else {
				CuentaMensaje cuentaCopia = new CuentaMensaje();
				cuentaCopia.setEmail(correoCopia);

				listaCuentaCopia.add(cuentaCopia);
			}
			mensaje.setCuentaCopia(listaCuentaCopia);
		}

		mensaje.setMensajeAsunto(asunto);
		mensaje.setMensajeContenido(htmlTemplate);
		mensaje.setConfigSiHtml(true);
		mensaje.setAplicacionEnvio("Desarrollo");
		if (adjunto != null) {
			mensaje.setMensajeFileAdjunto(adjunto);
		}

		StringBuilder uriMensajeria = new StringBuilder();

		uriMensajeria.append(constantes.getUrlServicioMensajeria());
		uriMensajeria.append("envia-email");

		RestTemplate rest = new RestTemplate();

		rest.postForObject(uriMensajeria.toString(), mensaje, HashMap.class);
	}

//	public String crearQR(String textQR) {
//		String encodeImage;
//		ByteArrayOutputStream out = QRCode.from(textQR).to(ImageType.PNG).stream();
//		ImageIcon imageIcon = new ImageIcon(out.toByteArray());
//		byte[] imageData = out.toByteArray();
//		String encodedFile = Base64.getEncoder().encodeToString(imageData);
//		return encodedFile;
//	}

}
