package com.pe.estec.services;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;

import com.pe.estec.model.Archivo;
import com.pe.estec.model.Comprobante;

public interface ArchivoService {

	public Archivo recuperarEntidadArchivo (int tipoEntidad, int idEntidad, int entidadArchivo, String token);
	public void guardarFile(List<Archivo> listaArchivos) throws IOException ;
	public void actualizarTemporal(String token, Integer idEntidad) throws Exception;
	public InputStreamResource obtenerEstadoCuentaRep(Integer secuencia, List<Comprobante> listComprobante, Integer igv) throws Exception ;
}
