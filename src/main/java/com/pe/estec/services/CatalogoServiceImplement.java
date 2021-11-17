package com.pe.estec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.estec.model.Catalogo;
import com.pe.estec.model.CatalogoContabilidad;
import com.pe.estec.repository.CatalogoRepository;
@Service
public class CatalogoServiceImplement implements CatalogoService{

	@Autowired
	CatalogoRepository catalogoReposotory;
	@Override
	public List<Catalogo> consultaParametro(Integer idParametroTipo) {
		List<Catalogo> listaCatalogo = catalogoReposotory.consultaParametro(idParametroTipo);
		return listaCatalogo;
	}
	
	@Override
	public List<CatalogoContabilidad> catalogoContabilidad(String idParametro){
		List<CatalogoContabilidad> listaCatalogo = catalogoReposotory.catalogoContabilidad(idParametro);		
		return listaCatalogo;
	}
	

}
