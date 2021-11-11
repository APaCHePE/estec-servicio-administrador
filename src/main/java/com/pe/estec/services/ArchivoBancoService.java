package com.pe.estec.services;

import java.util.List;

import com.pe.estec.model.ArchivoBanco;
import com.pe.estec.model.request.ServiceResult;

public interface ArchivoBancoService {

	public ServiceResult<String> crearLoteArchivo(ArchivoBanco loteArchivo);
	public ServiceResult<List<ArchivoBanco>> obtenerLoteArchivo(Integer idArhcivo);	
//	public ServiceResult<List<ArchivoBanco>> obtenerLoteArchivoDetalle();
	
}
