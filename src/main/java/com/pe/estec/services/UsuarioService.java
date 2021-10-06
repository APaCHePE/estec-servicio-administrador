package com.pe.estec.services;

import java.util.List;

import com.pe.estec.model.Proveedor;
import com.pe.estec.request.ServiceResult;

public interface UsuarioService {

	public ServiceResult<Boolean> validarProveedor(String nroDocumento);
	public ServiceResult<String> guardarProveedor(Proveedor proveedor );
	public ServiceResult<List<Proveedor>> listarProveedor(Integer estado, Integer tipoCuenta,
			String usuario, String nroDocumento, Integer tipoDocumento);
	public ServiceResult<String> activarProveedor(Proveedor proveedor);
	public ServiceResult<Proveedor> listarProveedorErp(String nroDocumento );
	public ServiceResult<String> contrasenaProveedor(String user, String contrasena, Integer estado);
}
