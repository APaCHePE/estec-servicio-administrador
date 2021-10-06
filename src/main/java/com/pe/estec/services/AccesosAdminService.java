package com.pe.estec.services;

import com.pe.estec.model.Proveedor;
import com.pe.estec.model.request.ServiceResult;

public interface AccesosAdminService {

	public ServiceResult<Proveedor> authentication(String usuario, String pass);
}
