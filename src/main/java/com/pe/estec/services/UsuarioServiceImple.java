package com.pe.estec.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pe.estec.config.Constantes;
import com.pe.estec.model.Proveedor;
import com.pe.estec.request.ServiceResult;
import com.pe.estec.util.UtilString;
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
	public ServiceResult<Proveedor> authentication(String usuario, String pass) {
		ServiceResult<Proveedor> response = new ServiceResult();
		try {
			List<Proveedor> proveedorAuth = userRepository.listarProveedor(null, null, usuario, null, null);
			if (proveedorAuth.size() == 0) {
				response.setMensajeError("Usuario o contraseña incorrectos.");
				response.setEsCorrecto(false);
				response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
			} else if (proveedorAuth.get(0).getEstado() == Constantes.ESTADO_PENDIENTE) {
				response.setMensajeError("Su solicitud de acceso está siendo evaluada.");
				response.setEsCorrecto(false);
				response.setHttpStatus(HttpStatus.OK.value());
			} else if (proveedorAuth.get(0).getEstado() == Constantes.ESTADO_APROBADO) {
				response.setMensajeError("Para completar el registro debe activar su cuenta.");
				response.setEsCorrecto(false);
				response.setHttpStatus(HttpStatus.OK.value());
			} else {
				Boolean access = userRepository.validarPass(proveedorAuth.get(0).getUsuario(), pass);
				System.out.println("acceso " + access);
				if (access) {
					response.setHttpStatus(HttpStatus.OK.value());
					response.setResultado(proveedorAuth.get(0));
					response.setEsCorrecto(true);
				} else {
					response.setMensajeError("EL usuario o contraseña ingresada es incorrecta.");
					response.setEsCorrecto(false);
					response.setHttpStatus(HttpStatus.OK.value());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		}
		return response;
	}
	
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
			if(proveedor.getUsuario() == null || proveedor.getPersona() == null || proveedor.getPersona().getNroDocumento() == null) {
				response.setResultado("Ingrese sus datos correctamente");
				response.setMensajeError("Ingrese sus datos correctamente");
				response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
				return response;
			}
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
				enviarCorreoRegistro(proveedor);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("No se pudo enviar correo de confirmación");
			}
			response.setResultado("Se ha registrado correctamente.");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setEsCorrecto(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private void enviarCorreoRegistro(Proveedor proveedor) throws Exception {
		String htmlTemplate = correoService.correoRegistro(proveedor.getPersona().getNombreCompleto(),
				proveedor.getUsuario(), null, "/tmpl-8642");
		correoService.enviaReporteNuevo(htmlTemplate, proveedor.getUsuario(), null,
				"Confirmación de solicitud de cuenta como proveedor:  ", null);
	}

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
			userRepository.estadoProveedor(proveedor.getIdProveedor(), proveedor.getEstado(), proveedor.getObservacion());
			if(proveedor.getEstado()==Constantes.ESTADO_APROBADO)
				enviarCorreoActivacion(proveedor);
			
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

	private void enviarCorreoActivacion(Proveedor proveedor) throws Exception {
		String htmlTemplate = correoService.correoActivacion(proveedor.getPersona().getNombreCompleto(),
				Constantes.URL_PLANTILLA_PASS+proveedor.getIdProveedor()+UtilString.retornarEncryptMd5(proveedor.getUsuario()), proveedor.getUsuario(), null, "/tmpl-8641");
		correoService.enviaReporteNuevo(htmlTemplate, proveedor.getUsuario(), null,
				"Activación de cuenta de proveedor:  ", null);
	}

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
	public ServiceResult<String> contrasenaProveedor(String user, String contrasena, Integer estado) {
		ServiceResult<String> response = new ServiceResult();
		try {
			Integer idProveedor = (user != null && user.length()>32)?Integer.parseInt(user.substring(0, user.length()-32)): Integer.parseInt(user );
			userRepository.contrasenaProveedor(idProveedor, contrasena);
			userRepository.estadoProveedor(idProveedor, estado, "");
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
