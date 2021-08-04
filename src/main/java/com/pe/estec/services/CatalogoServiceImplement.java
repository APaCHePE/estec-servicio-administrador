package com.pe.estec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.estec.model.Catalogo;
import com.pe.estec.model.request.ServiceResult;
import com.pe.estec.repository.CatalogoRepository;
@Service
public class CatalogoServiceImplement implements CatalogoService{

	@Autowired
	CatalogoRepository catalogoReposotory;
	@Override
	public ServiceResult<List<Catalogo>> obtenerCatalogo(String idElemento, String idGrupo) {
		ServiceResult<List<Catalogo>> response = new ServiceResult();
		response.setResultado(catalogoReposotory.obtenerCatalogo(idElemento, idGrupo));
		return response;
	}

}
