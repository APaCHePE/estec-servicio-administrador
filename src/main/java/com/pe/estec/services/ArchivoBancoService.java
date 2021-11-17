package com.pe.estec.services;

import java.util.List;

import com.pe.estec.model.ArchivoBanco;
import com.pe.estec.model.ArchivoBancoDetalle;
import com.pe.estec.request.ServiceResult;

public interface ArchivoBancoService {

	public ServiceResult<String> crearLoteArchivo(ArchivoBanco loteArchivo);
	public ServiceResult<List<ArchivoBanco>> obtenerLoteArchivo(Integer idArhcivo);	
	public ServiceResult<List<ArchivoBancoDetalle>> obtenerLoteDetalleArchivo(Integer idArhcivo);
	
}
