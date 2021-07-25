package com.pe.estec.services;

import java.util.List;

import com.pe.estec.model.Facturas;
import com.pe.estec.model.OrdenesCompra;

public interface ConsultaDocumentoService {

	public List<OrdenesCompra> consultaOrdenes(Integer tipoDocumento, String nroOrden, 
			String fecInicio, String fecFin, Integer estado, String nroDocumento);
	public List<Facturas> consultaFacturas( String nroOrden, 
			String fecInicio, String fecFin, Integer estado);
}
