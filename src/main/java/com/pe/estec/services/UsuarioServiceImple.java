package com.pe.estec.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pe.estec.config.Constantes;
import com.pe.estec.model.Proveedor;
import com.pe.estec.model.request.ServiceResult;
import com.pe.estec.repository.UsuarioRepository;

@Service
public class UsuarioServiceImple implements UsuarioService {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private CorreoServiceImple correoService;

	@Autowired
	private Constantes constantes;
	Logger logger = LoggerFactory.getLogger(UsuarioServiceImple.class);

	@Override
	public ServiceResult<Boolean> validarProveedor(String nroDocumento) {
		ServiceResult<Boolean> response = new ServiceResult<>();
		try {
			response.setResultado(userRepository.validarNroDocumentoProveedor(nroDocumento));
			response.setHttpStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			response.setResultado(true);
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		}

		return response;
	}

	@Override
	public ServiceResult<String> guardarProveedor(Proveedor proveedor) {
		ServiceResult<String> response = new ServiceResult();
		try {
			if (userRepository.validarNroDocumentoProveedor(proveedor.getPersona().getNroDocumento())) {
				response.setResultado("El Nro de Documento ya se encuentra registrado");
				response.setMensajeError("El Nro de Documento ya se encuentra registrado");
				response.setHttpStatus(HttpStatus.CONFLICT.value());
				return response;
			}
			if (userRepository.validarCorreoProveedor(proveedor.getUsuario())) {
				response.setResultado("El usuario ya se encuentra registrado");
				response.setMensajeError("El usuario ya se encuentra registrado");
				response.setHttpStatus(HttpStatus.CONFLICT.value());
				return response;
			}
			if (!userRepository.validarPersona(proveedor.getPersona().getNroDocumento())) {
				try {
					proveedor.getPersona()
							.setIdPersona(userRepository.insertarPersona(proveedor.getPersona()).intValue());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				proveedor.getPersona()
						.setIdPersona(
								userRepository
										.listarProveedor(null, null, null, proveedor.getPersona().getNroDocumento(),
												proveedor.getPersona().getTipoDocumento())
										.get(0).getPersona().getIdPersona());
			}
			try {
				userRepository.insertarProveedor(proveedor);
			} catch (Exception e) {

				e.printStackTrace();
				response.setResultado("Ocurrió un problema al registrar");
				response.setMensajeError("Ocurrió un problema al registrar");
				response.setHttpStatus(HttpStatus.CONFLICT.value());
				return response;
			}
			try {
				//enviarCorreoRegistro(proveedor);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("No se pudo enviar correo de confiración");
			}
			response.setResultado("Se ha registrado correctamente.");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setEsCorrecto(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

/*private void enviarCorreoRegistro(Proveedor proveedor) throws Exception {
		String htmlTemplate = correoService.correoRegistro(proveedor.getPersona().getNombreCompleto(),
				proveedor.getUsuario(), null, "/tmpl-8642");
		correoService.enviaReporteNuevo(htmlTemplate, proveedor.getUsuario(), null,
				"Confirmación de solicitud de cuenta como proveedor:  ", null);
	}
*/
	@Override
	public ServiceResult<List<Proveedor>> listarProveedor(Integer estado, Integer tipoCuenta, String usuario,
			String nroDocumento, Integer tipoDocumento) {
		ServiceResult<List<Proveedor>> response = new ServiceResult<>();
		try {
			response.setResultado(
					userRepository.listarProveedor(estado, tipoCuenta, usuario, nroDocumento, tipoDocumento));
			response.setHttpStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			e.printStackTrace();
			response.setResultado(null);
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		}
		return response;
	}

	@Override
	public ServiceResult<String> activarProveedor(Proveedor proveedor) {
		ServiceResult<String> response = new ServiceResult();
		try {
<<<<<<< HEAD
			userRepository.activarProveedor(proveedor.getIdProveedor(), proveedor.getEstado(),
					proveedor.getObservacion());
			if (proveedor.getEstado() == Constantes.ESTADO_ACTIVO)
				enviarCorreoActivacion(proveedor);
=======
			userRepository.activarProveedor(proveedor.getIdProveedor(), proveedor.getEstado(), proveedor.getObservacion());
			if(proveedor.getEstado()==Constantes.ESTADO_ACTIVO)
				//enviarCorreoActivacion(proveedor);
>>>>>>> 308c92ff2d24a53a59fb63e5e8638756f115a52c
			response.setEsCorrecto(true);
			response.setHttpStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			e.printStackTrace();
			response.setEsCorrecto(false);
			response.setMensajeError("No se pudo modificar al usuario");
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		}
		return response;
	}
<<<<<<< HEAD

	private void enviarCorreoActivacion(Proveedor proveedor) throws Exception {
		String htmlTemplate = correoService.correoActivacion(proveedor.getPersona().getNombreCompleto(),
				proveedor.getContrasenia(), proveedor.getUsuario(), null, "/tmpl-8641");
		correoService.enviaReporteNuevo(htmlTemplate, proveedor.getUsuario(), null,
				"Activación de solicitud de cuenta como proveedor:  ", null);
	}

=======
/*	private void enviarCorreoActivacion(Proveedor proveedor) throws Exception {
		String htmlTemplate = correoService.correoActivacion(proveedor.getPersona().getNombreCompleto(), proveedor.getContrasenia(),
				proveedor.getUsuario(), null, "/tmpl-8641");
		correoService.enviaReporteNuevo(htmlTemplate, proveedor.getUsuario(), null,
				"Activación de solicitud de cuenta como proveedor:  ", null);
	}*/
>>>>>>> 308c92ff2d24a53a59fb63e5e8638756f115a52c
	@Override
	public ServiceResult<Proveedor> listarProveedorErp(String nroDocumento) {
		ServiceResult<Proveedor> response = new ServiceResult();
		try {
			List<Proveedor> lista = userRepository.listarProveedorErp(nroDocumento);
			if (lista.size() == 0) {
				response.setEsCorrecto(false);
				response.setMensajeError("No existe este usuario.");
				response.setHttpStatus(HttpStatus.OK.value());
				return response;
			} else {
				response.setResultado(lista.get(0));
			}
			response.setEsCorrecto(true);
			response.setHttpStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			e.printStackTrace();
			response.setMensajeError("Ocurrió un error al buscar proveedor. ");
			response.setEsCorrecto(false);
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		}
		return response;
	}

	@Override
	public ServiceResult<String> contrasenaProveedor(Integer idProveedor, String contrasena) {
		ServiceResult<String> response = new ServiceResult();
		try {
			userRepository.contrasenaProveedor(idProveedor, contrasena);
			response.setEsCorrecto(true);
			response.setResultado("Modificado correctamente");
			response.setHttpStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			e.printStackTrace();
			response.setMensajeError("Ocurrió un error al buscar proveedor. ");
			response.setEsCorrecto(false);
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		}
		return response;
	}

}
