package com.pe.estec.services;

import java.util.List;

import com.pe.estec.model.Catalogo;
import com.pe.estec.model.request.ServiceResult;

public interface CatalogoService {

	public ServiceResult<List<Catalogo>> obtenerCatalogo(String idElemento, String idGrupo);
}
