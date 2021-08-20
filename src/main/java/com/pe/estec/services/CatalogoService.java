package com.pe.estec.services;

import java.util.List;

import com.pe.estec.model.Catalogo;

public interface CatalogoService {

	public List<Catalogo> consultaParametro(Integer idParametroTipo);
}
