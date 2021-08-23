package com.pe.estec.services;

import com.pe.estec.model.Archivo;

public interface ArchivoService {

	public Archivo recuperarEntidadArchivo (int tipoEntidad, int idEntidad, int entidadArchivo);
}
