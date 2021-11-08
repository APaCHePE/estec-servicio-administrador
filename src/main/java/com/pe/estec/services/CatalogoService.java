package com.pe.estec.services;

import java.util.List;

import com.pe.estec.model.Catalogo;
import com.pe.estec.model.CatalogoContabilidad;

public interface CatalogoService {

	public List<Catalogo> consultaParametro(Integer idParametroTipo);
	public List<CatalogoContabilidad> catalogoContabilidad(String idParametroTipo);
}
