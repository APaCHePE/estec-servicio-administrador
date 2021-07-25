package com.pe.estec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.estec.model.Facturas;
import com.pe.estec.model.OrdenesCompra;
import com.pe.estec.repository.ConsultaDocumentoRepository;

@Service
public class ConsultaDocumentoServiceImpl implements ConsultaDocumentoService{
	@Autowired
	private ConsultaDocumentoRepository consultaDocRepository;

	@Override
	public List<OrdenesCompra> consultaOrdenes(Integer tipoDocumento, String nroOrden, 
			String fecInicio, String fecFin, Integer estado, String nroDocumento) {
		return consultaDocRepository.consultaOrdenes(tipoDocumento, nroOrden, fecInicio,
				fecFin, estado, nroDocumento);
	}

	@Override
	public List<Facturas> consultaFacturas(String nroOrden, String fecInicio, String fecFin,
			Integer estado) {
		return consultaDocRepository.consultaFacturas( nroOrden, fecInicio,
				fecFin, estado);
	}
}
