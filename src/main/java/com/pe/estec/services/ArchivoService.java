package com.pe.estec.services;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;

import com.pe.estec.model.Archivo;

public interface ArchivoService {

	public Archivo recuperarEntidadArchivo (int tipoEntidad, int idEntidad, int entidadArchivo, String token);
	public void guardarFile(List<Archivo> listaArchivos) throws IOException ;
	public void actualizarTemporal(String token, Integer idEntidad) throws Exception;
	public InputStreamResource obtenerEstadoCuentaRep(Integer secuencia) throws Exception ;
}
