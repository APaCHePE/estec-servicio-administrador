package com.pe.estec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pe.estec.config.Constantes;
import com.pe.estec.model.Proveedor;
import com.pe.estec.model.request.ServiceResult;
import com.pe.estec.repository.AccesosRepository;
import com.pe.estec.repository.UsuarioRepository;

@Service
public class AccesosExternoServiceImpl implements AccesosExternoService {

	@Autowired
	private AccesosRepository accesoRepository;

	@Autowired
	private UsuarioRepository userDao;

	@Override
	public ServiceResult<Proveedor> authentication(String usuario, String pass) {
		ServiceResult<Proveedor> response = new ServiceResult();
		try {
			List<Proveedor> proveedorAuth = userDao.listarProveedor(null, null, usuario, null, null);
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
				Boolean access = accesoRepository.validarPass(proveedorAuth.get(0).getUsuario(), pass);
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
}
