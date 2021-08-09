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
	public Archivo recuperarEntidadArchivo() {
		Archivo archivo = fileRepo.recuperaEntidadArchivo();
		return archivo;
	}

}
