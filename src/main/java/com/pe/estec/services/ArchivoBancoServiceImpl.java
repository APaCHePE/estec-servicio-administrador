package com.pe.estec.services;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pe.estec.model.ArchivoBanco;
import com.pe.estec.model.ArchivoBancoDetalle;
import com.pe.estec.request.ServiceResult;
import com.pe.estec.repository.ArchivoBancoRepository;

@Service
public class ArchivoBancoServiceImpl implements ArchivoBancoService{

	@Autowired
	private ArchivoBancoRepository bancoRepository;
	
	@Override
	public ServiceResult<List<ArchivoBanco>> obtenerLoteArchivo(Integer idArhcivo) {

		ServiceResult<List<ArchivoBanco>> response = new ServiceResult<List<ArchivoBanco>>();
		try {
			List<ArchivoBanco> listaArchivos = bancoRepository.obtenerListaArchivos(idArhcivo);
			response.setResultado(listaArchivos);
			response.setHttpStatus(HttpStatus.OK.value());
		}catch (Exception e) {
			response.setMensajeError("Mensaje de respuesta"+e.fillInStackTrace());
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
			response.setEsCorrecto(false);
		}
		return response;
	}

	@Override
	public ServiceResult<String> crearLoteArchivo(ArchivoBanco loteArchivo) {
		ServiceResult<String> response = new ServiceResult();
		try {
			Integer idLoteArchivo = bancoRepository.crearLoteArchivo(loteArchivo);
			loteArchivo.setIdArchivoBanco(idLoteArchivo);
			loteArchivo.getListaArchivoBancoDetalle().forEach( item -> {
				System.out.println("listando");
				item.setIdArchivoBanco(idLoteArchivo);
				bancoRepository.crearLoteDetalleArchivo(item);
			});
//			try {
//				enviarCorreoRegistro(proveedor);
//			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error("No se pudo enviar correo de confirmación");
//			}
			response.setResultado("Se ha registrado correctamente.");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setEsCorrecto(true);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMensajeError("Mensaje de respuesta"+e.fillInStackTrace());
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
			response.setEsCorrecto(false);
		}
		return response;
	}

	@Override
	public ServiceResult<List<ArchivoBancoDetalle>> obtenerLoteDetalleArchivo(Integer idArchivo) {

		ServiceResult<List<ArchivoBancoDetalle>> response = new ServiceResult<List<ArchivoBancoDetalle>>();
		try {
			List<ArchivoBancoDetalle> listaArchivos = bancoRepository.obtenerListaArchivosDetalle(idArchivo);
			response.setResultado(listaArchivos);
			response.setHttpStatus(HttpStatus.OK.value());
			response.setEsCorrecto(true);
		}catch (Exception e) {
			response.setMensajeError("Mensaje de respuesta"+e.fillInStackTrace());
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
			response.setEsCorrecto(false);
		}
		
		return response;
	}

	@Override
	public ServiceResult<String> generarArchivo(Integer archivoSolicitud) {
		ServiceResult<String> response = new ServiceResult<String>();
		try {
			StringBuilder detalle = new StringBuilder();
			detalle.append(bancoRepository.cabeceraArchivoBanco(archivoSolicitud));
			detalle.append("\n");
			detalle.append("linea uno");
			detalle.append("\n");
			detalle.append("linea dos");
			detalle.append("\n");
			detalle.append("linea tres");
			detalle.append("\n");
			detalle.append("linea cuatro");
			response.setResultado(detalle.toString());
			response.setHttpStatus(HttpStatus.OK.value());
			response.setEsCorrecto(true);
		}catch (Exception e) {
			e.printStackTrace();
			response.setMensajeError("Mensaje de respuesta"+e.fillInStackTrace());
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
			response.setEsCorrecto(false);
		}
		
		return response;
	}

	
}
