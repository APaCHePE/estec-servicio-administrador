package com.pe.estec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.estec.model.Archivo;
import com.pe.estec.repository.FileRepository;

@Service
public class ArchivoServiceImple implements ArchivoService{

	@Autowired
	private FileRepository fileRepo;
	@Override
	public Archivo recuperarEntidadArchivo(int tipoEntidad,int  idEntidad,int entidadArchivo) {
		Archivo archivo = fileRepo.recuperaEntidadArchivo(tipoEntidad, idEntidad, entidadArchivo);
		return archivo;
	}

}
