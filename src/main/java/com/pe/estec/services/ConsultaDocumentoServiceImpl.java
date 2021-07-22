package com.pe.estec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.estec.repository.ConsultaDocumentoRepository;

@Service
public class ConsultaDocumentoServiceImpl implements ConsultaDocumentoService{
	@Autowired
	private ConsultaDocumentoRepository consultaDocRepository;

	@Override
	public Integer consultaOrdenes() {
		return consultaDocRepository.consultaOrdenes();
	}
}
