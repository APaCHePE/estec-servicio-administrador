package com.pe.estec.services;

import com.pe.estec.model.Empleado;
import com.pe.estec.request.ServiceResult;

public interface AdministracionService {

	public ServiceResult<Empleado> authentication(String usuario, String pass);
}
