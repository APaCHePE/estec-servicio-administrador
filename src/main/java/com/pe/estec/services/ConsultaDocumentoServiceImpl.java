package com.pe.estec.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pe.estec.config.Constantes;
import com.pe.estec.model.Comprobante;
import com.pe.estec.model.ComprobanteDetalle;
import com.pe.estec.model.Facturas;
import com.pe.estec.model.Orden;
import com.pe.estec.model.Proveedor;
import com.pe.estec.model.request.ServiceResult;
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
			Integer estado, String nroDocumento) {
		List<Facturas> listaFacturas = consultaDocRepository.getFacturasCabecera( nroOrden, fecInicio,
				fecFin, estado, nroDocumento);
		if(listaFacturas.size()==0)return listaFacturas;
		for (Facturas factura : listaFacturas) {
			if(factura.getMoneda().equals(Constantes.MONEDA_NACIONAL))
				factura.setSimboloMoneda("S/");
			else if(factura.getMoneda().equals(Constantes.MONEDA_DOLAR))
				factura.setSimboloMoneda("$");
			
			if(factura.getFechaDocumento()!= null)
				factura.setFechaDocumento("20"+factura.getFechaDocumento());
			if(factura.getFechaVenta()!= null)
				factura.setFechaVenta("20"+factura.getFechaVenta());
			if(factura.getFechaRecibido()!= null)
				factura.setFechaRecibido("20"+factura.getFechaRecibido());
			
			if(factura.getFechaDocumento()!= null && factura.getFechaDocumento().length()==6) {
//				factura.getFechaDocumento().si
			}

		}
		return listaFacturas;
	}
	
	/////////////////////////////////////////////////////////////////
	
	@Override
	public List<Comprobante> consultarComprobante(String nroOrden, String fecInicio, String fecFin,
			Integer estado, String nroDocumento) {
		List<Comprobante> listaFacturas = consultaDocRepository.consultarComprobante( nroOrden, fecInicio,
				fecFin, estado, nroDocumento);
		for (Comprobante comprobante : listaFacturas) {
			comprobante.setListaComprobanteDetalle(consultaDocRepository.consultarComprobanteDetalle(comprobante.getId_comprobante()));
		}		
		return listaFacturas;
	}
	
	@Override
	public ServiceResult<String> estadoFactura(Integer estado,String numeroFactura) {
		ServiceResult<String> response = new ServiceResult();
		try {
			consultaDocRepository.estadoFactura(estado,numeroFactura);
			response.setEsCorrecto(true);
			response.setHttpStatus(HttpStatus.OK.value());
		}catch (Exception e) {
			e.printStackTrace();
			response.setEsCorrecto(false);
			response.setMensajeError("No se pudo cambiar de estado");
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		}
		return response;
	}
	
	@Override
	public ServiceResult<String> guardarComprobante(Comprobante Comprobante){
		ServiceResult<String> response = new ServiceResult();
		try {
			consultaDocRepository.guardarComprobante(Comprobante);
			response.setEsCorrecto(true);
			response.setHttpStatus(HttpStatus.OK.value());
		}catch (Exception e) {
			e.printStackTrace();
			response.setEsCorrecto(false);
			response.setMensajeError("No se pudo guardar comprobante");
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		}
		return response;
	}
	
	@Override
	public ServiceResult<String> guardarComprobanteDetalle(ComprobanteDetalle ComprobanteDetalle){
		ServiceResult<String> response = new ServiceResult();
		try {
			consultaDocRepository.guardarComprobanteDetalle(ComprobanteDetalle);
			response.setEsCorrecto(true);
			response.setHttpStatus(HttpStatus.OK.value());
		}catch (Exception e) {
			e.printStackTrace();
			response.setEsCorrecto(false);
			response.setMensajeError("No se pudo guardar el detalle del comprobante");
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		}
		return response;
	}
	
	
	
}
