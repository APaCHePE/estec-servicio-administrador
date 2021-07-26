package com.pe.estec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.estec.model.Facturas;
import com.pe.estec.model.Orden;
import com.pe.estec.repository.ConsultaDocumentoRepository;

@Service
public class ConsultaDocumentoServiceImpl implements ConsultaDocumentoService{
	@Autowired
	private ConsultaDocumentoRepository consultaDocRepository;

	@Override
	public List<Orden> consultaOrdenes(Integer tipoDocumento, String nroOrden, 
			String fecInicio, String fecFin, Integer estado, String nroDocumento) {
		List<Orden> listaOrdenes = consultaDocRepository.getOrdenesCabecera(tipoDocumento, nroOrden, fecInicio,
				fecFin, nroDocumento);
		for (Orden orden : listaOrdenes) {
			orden.setListaDetalles(consultaDocRepository.getOrdenesDetalle(orden.getNroOrden()));
		}
		return listaOrdenes;
	}

	@Override
	public List<Facturas> consultaFacturas(String nroOrden, String fecInicio, String fecFin,
			Integer estado) {
		List<Facturas> listaFacturas = consultaDocRepository.getFacturasCabecera( nroOrden, fecInicio,
				fecFin, estado);
		for (Facturas facturas : listaFacturas) {
			facturas.setFacturasDestalle(consultaDocRepository.getFacturasDetalle(facturas.getIdFactura()));
		}
		return listaFacturas;
	}
}
