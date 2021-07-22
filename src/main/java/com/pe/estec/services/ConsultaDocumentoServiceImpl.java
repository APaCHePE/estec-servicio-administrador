package com.pe.estec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.estec.model.Proveedor;
import com.pe.estec.repository.ConsultaDocumentoRepository;

@Service
public class ConsultaDocumentoServiceImpl implements ConsultaDocumentoService{
	@Autowired
	private ConsultaDocumentoRepository consultaDocRepository;

	@Override
	public List<Proveedor> consultaOrdenes() {
		return consultaDocRepository.consultaOrdenes();
	}
}
