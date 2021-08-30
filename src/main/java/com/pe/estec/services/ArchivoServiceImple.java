package com.pe.estec.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.estec.model.Archivo;
import com.pe.estec.repository.ArchivoRepository;
import com.pe.estec.repository.FileRepository;

@Service
public class ArchivoServiceImple implements ArchivoService{

	@Autowired
	private FileRepository fileRepo;
	@Autowired
	private ArchivoRepository archivoRepo;
	@Override
	public Archivo recuperarEntidadArchivo(int tipoEntidad,int  idEntidad,int entidadArchivo, String token) {
		Archivo archivo = fileRepo.recuperaEntidadArchivo(tipoEntidad, idEntidad, entidadArchivo, token);
		archivo.setArchivoBase64(Base64.getEncoder().encodeToString(archivo.getArchivo()));
		return archivo;
	}
	@Override
	public void guardarFile(List<Archivo> listaArchivos) throws IOException {
		listaArchivos.forEach(item ->{
			item.setIdArchivo(archivoRepo.guardarArchivo(item));
			archivoRepo.guardarArchivoRepo(item);
		});
	}
	@Override
	public void actualizarTemporal(String token, Integer idEntidad) throws Exception {
		archivoRepo.actualizarTemporal(token, idEntidad);
	}

}
