package com.pe.estec.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pe.estec.model.CuentaMensaje;
import com.pe.estec.model.FileAdjunto;
import com.pe.estec.model.Mensaje;
import com.pe.estec.model.MensajeVm;
import com.pe.estec.util.UtilString;


/**
 * Servicio que permitirá enviar los mensajes de correo.
 * @author Lizandro Alipazaga
 *
 */
@Service
@PropertySource("classpath:config.properties")
public class MailService{
	private final String CODIFICACION_ENVIO = "UTF-8";
	
	@Autowired
	Environment env;

	@Autowired
	private JavaMailSender javaMailSender;
	
//	@Autowired
//	private MensajeRepository mensajeRepository;
	
	/**
	 * El método permite preparar y enviar mensaje de correo electrónico.
	 * @author Lizandro Alipazaga
	 * @param mensaje Contiene los parametros que permiten gestionar la preparación del mensaje.
	 * @throws IOException 
	 * @throws MailException
	 */
	public String enviaMail(Mensaje mensaje)throws MessagingException,Exception{
		MimeMessage mail = javaMailSender.createMimeMessage();
		boolean siMensajeTieneAdjuntos = false;
		
		this.validaSiRemitente(mensaje);
		
		if(mensaje.getMensajeFileAdjunto()!=null && mensaje.getMensajeFileAdjunto().size()>0){
			siMensajeTieneAdjuntos = true;
		}
		
		MimeMessageHelper helper = new MimeMessageHelper(mail, siMensajeTieneAdjuntos, CODIFICACION_ENVIO);
		
		helper.setFrom(mensaje.getCuentaRemitente().getEmail(),mensaje.getCuentaRemitente().getNombre());
		
		helper.setTo(mensaje.getCuentaDestino().getEmail());
		
		if(mensaje.getCuentaCopia()!=null && mensaje.getCuentaCopia().size()>0){
			String[] cuentas = new String[mensaje.getCuentaCopia().size()];
			
			for(int i=0;i<mensaje.getCuentaCopia().size();i++){
				cuentas[i] = mensaje.getCuentaCopia().get(i).getEmail();
			}
			
			helper.setCc(cuentas);
		}
		
		if(mensaje.getCuentaOculta()!=null && mensaje.getCuentaOculta().size()>0){
			String[] cuentas = new String[mensaje.getCuentaOculta().size()];																												
			
			for(int i=0;i<mensaje.getCuentaOculta().size();i++){
				cuentas[i] = mensaje.getCuentaOculta().get(i).getEmail();
			}
			
			helper.setBcc(cuentas);
		}
		
		if(mensaje.getCuentaRespuesta()!=null){
			helper.setReplyTo(mensaje.getCuentaRespuesta().getEmail(), mensaje.getCuentaRespuesta().getNombre());
		}
		
		helper.setSubject(UtilString.reemplazaCaracteres(mensaje.getMensajeAsunto()));
		
		helper.setText(mensaje.getMensajeContenido(), mensaje.getConfigSiHtml());
		
		List<File> listaArchivoAdjunto = new ArrayList<File>();
		if(mensaje.getMensajeFileAdjunto()!=null && mensaje.getMensajeFileAdjunto().size()>0){
			for (FileAdjunto adjunto : mensaje.getMensajeFileAdjunto()) {
				File archivoAdjunto = new File(adjunto.getNombre());
				
				byte[] decodedBytes = Base64.getDecoder().decode(adjunto.getCadena());
				FileUtils.writeByteArrayToFile(archivoAdjunto, decodedBytes );
				
				
				helper.addAttachment(adjunto.getNombre(), archivoAdjunto);
				listaArchivoAdjunto.add(archivoAdjunto);
			}
		}
		
		javaMailSender.send(mail);
		
		listaArchivoAdjunto.stream().forEach(f->f.delete());
		
		//mensajeRepository.guardarMensajeEnviado(mensaje);
		
		return "";
	}
	
	/**
	 * El método permite preparar y enviar mensaje de correo electrónico.
	 * @author Lizandro Alipazaga
	 * @param mensaje Contiene los parametros que permiten gestionar la preparación del mensaje.
	 * @throws IOException 
	 * @throws MailException
	 */
	public String enviaMailVm(MensajeVm mensaje)throws MessagingException,Exception{
		MimeMessage mail = javaMailSender.createMimeMessage();
		boolean siMensajeTieneAdjuntos = false;
		
		this.validaSiRemitente(mensaje);
		
		if(mensaje.getMensajeFileAdjunto()!=null && mensaje.getMensajeFileAdjunto().size()>0){
			siMensajeTieneAdjuntos = true;
		}
		
		MimeMessageHelper helper = new MimeMessageHelper(mail, siMensajeTieneAdjuntos, CODIFICACION_ENVIO);
		
		helper.setFrom(mensaje.getCuentaRemitente().getEmail(),mensaje.getCuentaRemitente().getNombre());
		
//		helper.setTo(mensaje.getCuentaDestino().getEmail());
		helper.setTo(llenarArrayDestinatarios(mensaje));
		
		if(mensaje.getCuentaCopia()!=null && mensaje.getCuentaCopia().size()>0){
			String[] cuentas = new String[mensaje.getCuentaCopia().size()];
			
			for(int i=0;i<mensaje.getCuentaCopia().size();i++){
				cuentas[i] = mensaje.getCuentaCopia().get(i).getEmail();
			}
			
			helper.setCc(cuentas);
		}
		
		if(mensaje.getCuentaOculta()!=null && mensaje.getCuentaOculta().size()>0){
			String[] cuentas = new String[mensaje.getCuentaOculta().size()];
			
			for(int i=0;i<mensaje.getCuentaOculta().size();i++){
				cuentas[i] = mensaje.getCuentaOculta().get(i).getEmail();
			}
			
			helper.setBcc(cuentas);
		}
		
		if(mensaje.getCuentaRespuesta()!=null){
			helper.setReplyTo(mensaje.getCuentaRespuesta().getEmail(), mensaje.getCuentaRespuesta().getNombre());
		}
		
		helper.setSubject(UtilString.reemplazaCaracteres(mensaje.getMensajeAsunto()));
		
		helper.setText(mensaje.getMensajeContenido(), mensaje.getConfigSiHtml());
		
		List<File> listaArchivoAdjunto = new ArrayList<File>();
		if(mensaje.getMensajeFileAdjunto()!=null && mensaje.getMensajeFileAdjunto().size()>0){
			for (FileAdjunto adjunto : mensaje.getMensajeFileAdjunto()) {
				File archivoAdjunto = new File(adjunto.getNombre());
				
				byte[] decodedBytes = Base64.getDecoder().decode(adjunto.getCadena());
				FileUtils.writeByteArrayToFile(archivoAdjunto, decodedBytes );
				
				
				helper.addAttachment(adjunto.getNombre(), archivoAdjunto);
				listaArchivoAdjunto.add(archivoAdjunto);
			}
		}
		
		javaMailSender.send(mail);
		
		listaArchivoAdjunto.stream().forEach(f->f.delete());
		
		//mensajeRepository.guardarMensajeEnviado(mensaje);
		
		return "";
	}
	
	private void validaSiRemitente(Mensaje mensaje){
		if(mensaje.getCuentaRemitente()==null){
			CuentaMensaje cuentaRemitente = new CuentaMensaje();
			cuentaRemitente.setEmail(env.getProperty("mensaje.cuentaRemitente.email"));
			cuentaRemitente.setNombre(env.getProperty("mensaje.cuentaRemitente.nombre"));
			
			mensaje.setCuentaRemitente(cuentaRemitente);
		}
		
		if(mensaje.getCuentaRemitente().getEmail()==null){
			mensaje.getCuentaRemitente().setEmail(env.getProperty("mensaje.cuentaRemitente.email"));
		}
		
		if(mensaje.getCuentaRemitente().getNombre()==null){
			mensaje.getCuentaRemitente().setNombre(env.getProperty("mensaje.cuentaRemitente.nombre"));
		}
	}
	
	private void validaSiRemitente(MensajeVm mensaje){
		if(mensaje.getCuentaRemitente()==null){
			CuentaMensaje cuentaRemitente = new CuentaMensaje();
			cuentaRemitente.setEmail(env.getProperty("mensaje.cuentaRemitente.email"));
			cuentaRemitente.setNombre(env.getProperty("mensaje.cuentaRemitente.nombre"));
			
			mensaje.setCuentaRemitente(cuentaRemitente);
		}
		
		if(mensaje.getCuentaRemitente().getEmail()==null){
			mensaje.getCuentaRemitente().setEmail(env.getProperty("mensaje.cuentaRemitente.email"));
		}
		
		if(mensaje.getCuentaRemitente().getNombre()==null){
			mensaje.getCuentaRemitente().setNombre(env.getProperty("mensaje.cuentaRemitente.nombre"));
		}
	}
	
	private String[] llenarArrayDestinatarios(MensajeVm mensaje) {
		List<CuentaMensaje> destinatarios = mensaje.getCuentaDestinovm();
		String destinos[] = new String[destinatarios.size()];
		
		for (int i = 0; i < destinatarios.size(); i++) {
			destinos[i] = destinatarios.get(i).getEmail();
		}
		
		return destinos;
	}
}
