package com.pe.estec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pe.estec.config.Constantes;
import com.pe.estec.model.Empleado;
import com.pe.estec.repository.AdministracionRepository;
import com.pe.estec.request.ServiceResult;

@Service
public class AdministracionServiceImpl implements AdministracionService {

//	@Autowired
//	private AdministraRepository accesoRepository;

	@Autowired
	private AdministracionRepository administracionRepository;

	@Override
	public ServiceResult<Empleado> authentication(String usuario, String pass) {
		ServiceResult<Empleado> response = new ServiceResult();
		try {
			List<Empleado> empleadoAuth = administracionRepository.listarEmpleado(null, null, usuario, null, null);
			if (empleadoAuth.size() == 0) {
				response.setMensajeError("Usuario o contraseña incorrectos.");
				response.setEsCorrecto(false);
				response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
			} else if (empleadoAuth.get(0).getEstado() == Constantes.ESTADO_COMPROBANTE_PENDIENTE) {
				response.setMensajeError("Su solicitud de acceso está siendo evaluada.");
				response.setEsCorrecto(false);
				response.setHttpStatus(HttpStatus.OK.value());
			} else if (empleadoAuth.get(0).getEstado() == Constantes.ESTADO_COMPROBANTE_APROBADO) {
				response.setMensajeError("Para completar el registro debe activar su cuenta.");
				response.setEsCorrecto(false);
				response.setHttpStatus(HttpStatus.OK.value());
			} else {
				Boolean access = administracionRepository.validarPass(empleadoAuth.get(0).getUsuario(), pass);
				System.out.println("acceso " + access);
				if (access) {
					response.setHttpStatus(HttpStatus.OK.value());
					response.setResultado(empleadoAuth.get(0));
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

}
