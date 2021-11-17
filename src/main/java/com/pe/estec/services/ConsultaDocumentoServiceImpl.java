package com.pe.estec.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pe.estec.config.Constantes;
import com.pe.estec.model.Archivo;
import com.pe.estec.model.Asiento;
import com.pe.estec.model.AsientoDetalle;
import com.pe.estec.model.Comprobante;
import com.pe.estec.model.ComprobanteDetalle;
import com.pe.estec.model.Contrato;
import com.pe.estec.model.DocumentoOrigen;
import com.pe.estec.model.Facturas;
import com.pe.estec.model.Orden;
import com.pe.estec.request.ServiceResult;
import com.pe.estec.util.FilesUtils;
import com.pe.estec.util.*;
import com.pe.estec.repository.ConsultaDocumentoRepository;
import com.pe.estec.repository.DocumentoOrigenRepository;

/**
 * @author user
 *
 */
@Service
public class ConsultaDocumentoServiceImpl implements ConsultaDocumentoService {
	@Autowired
	private ConsultaDocumentoRepository consultaDocRepository;

	@Autowired
	private DocumentoOrigenRepository docOriginRep;

	@Autowired
	private ArchivoService archivoRep;

	@Override
	public List<Orden> consultaOrdenes(Integer tipoDocumento, String nroOrden, String fecInicio, String fecFin,
			Integer estado, String nroDocumento) {
		List<Orden> listaOrdenes = consultaDocRepository.getOrdenesCabecera(tipoDocumento, nroOrden, fecInicio, fecFin,
				nroDocumento);
		for (Orden orden : listaOrdenes) {
			orden.setListaDetalles(consultaDocRepository.getOrdenesDetalle(orden.getNroOrden()));
		}
		return listaOrdenes;
	}

	@Override
	public List<Contrato> getContrato(Integer nroContrato) {
		List<Contrato> listaContrato = consultaDocRepository.getContrato(nroContrato);
		return listaContrato;
	}
	
	@Override
	public String obtenerTraObservacion( Integer idComprobante) {
		String encargado = consultaDocRepository.obtenerTraObservacion(idComprobante);
		return encargado;
	}
	
	@Override
	public List<Asiento> consultarAsiento(Integer idComprobante){
		List<Asiento> listaAsiento = null;
		try {
			listaAsiento = consultaDocRepository.consultarAsiento(idComprobante);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaAsiento;
	}
	
	
	@Override
	public Integer grabarAsiento(Asiento asiento) throws Exception{
		
		if(asiento.getAfectoTipoComprobante()==26){
			asiento.setSub_diario(15);
			asiento.setSub_diario_detalle("REGISTRO HONORARIOS");
		}else {
		if(asiento.getAfectoDetraccion()) {
			asiento.setSub_diario(10);
			asiento.setSub_diario_detalle("REGISTRO COMPRAS DETRA");
			
		}else {
			asiento.setSub_diario(11);
			asiento.setSub_diario_detalle("REGISTRO COMPRAS LOCAL");
		}}
		//consultar id 
		Integer idAsiento = consultaDocRepository.grabarAsiento(asiento);
		System.out.println(asiento);
		if(asiento.getAfectoTipoComprobante()==26){
			AsientoDetalle asientodetalle = new AsientoDetalle();
			asientodetalle.setId_asiento_provision(idAsiento);
			asientodetalle.setId_asiento_regla(null);
			asientodetalle.setAnexo(null);
			asientodetalle.setArea(null);
			asientodetalle.setCc(null);
			asientodetalle.setCuenta(null);
			asientodetalle.setDebe(null);
			asientodetalle.setDescripcion(null);
			asientodetalle.setTp(null);
			asientodetalle.setHaber(null);
			asientodetalle.setDocumento(null);
			asientodetalle.setFecha_asiento_detalle(null);
			asientodetalle.setVencimiento_asiento_detalle(null);
			asientodetalle.setEstado(null);
			//15	registro honorarios
			consultaDocRepository.grabarAsientoDetalle( asientodetalle,idAsiento);
		}
		if(asiento.getAfectoDetraccion()) {
			AsientoDetalle asientodetalle = new AsientoDetalle();
			asientodetalle.setId_asiento_provision(idAsiento);
			asientodetalle.setId_asiento_regla(null);
			asientodetalle.setAnexo(null);
			asientodetalle.setArea(null);
			asientodetalle.setCc(null);
			asientodetalle.setCuenta(null);
			asientodetalle.setDebe(null);
			asientodetalle.setDescripcion(null);
			asientodetalle.setTp(null);
			asientodetalle.setHaber(null);
			asientodetalle.setDocumento(null);
			asientodetalle.setFecha_asiento_detalle(null);
			asientodetalle.setVencimiento_asiento_detalle(null);
			asientodetalle.setEstado(null);
			//10	con detraccion
			consultaDocRepository.grabarAsientoDetalle( asientodetalle,idAsiento);
		}else {
			AsientoDetalle asientodetalle = new AsientoDetalle();
			asientodetalle.setId_asiento_provision(idAsiento);
			asientodetalle.setId_asiento_regla(651101);
			asientodetalle.setAnexo(null);
			asientodetalle.setArea(null);
			asientodetalle.setCc(null);
			asientodetalle.setCuenta(null);
			asientodetalle.setDebe(null);
			asientodetalle.setDescripcion(null);
			asientodetalle.setTp(null);
			asientodetalle.setHaber(null);
			asientodetalle.setDocumento(null);
			asientodetalle.setFecha_asiento_detalle(null);
			asientodetalle.setVencimiento_asiento_detalle(null);
			asientodetalle.setEstado(null);
			//11	sin detraccion 
			consultaDocRepository.grabarAsientoDetalle(asientodetalle, idAsiento);
		}
		
		if(asiento.getAfectoIgv()) {
			AsientoDetalle asientodetalle = new AsientoDetalle();
			asientodetalle.setId_asiento_provision(idAsiento);
			asientodetalle.setId_asiento_regla(451101);
			asientodetalle.setAnexo(null);
			asientodetalle.setArea(null);
			asientodetalle.setCc(null);
			asientodetalle.setCuenta(null);
			asientodetalle.setDebe(null);
			asientodetalle.setDescripcion(null);
			asientodetalle.setTp(null);
			asientodetalle.setHaber(null);
			asientodetalle.setDocumento(null);
			asientodetalle.setFecha_asiento_detalle(null);
			asientodetalle.setVencimiento_asiento_detalle(null);
			asientodetalle.setEstado(null);
			consultaDocRepository.grabarAsientoDetalle(asientodetalle, idAsiento);
		}
		
		return idAsiento;
	}

	@Override
	public List<Facturas> consultaFacturas(String nroOrden, String fecInicio, String fecFin, Integer estado,
			String nroDocumento) {
		List<Facturas> listaFacturas = consultaDocRepository.getFacturasCabecera(nroOrden, fecInicio, fecFin, estado,
				nroDocumento);
		if (listaFacturas.size() == 0)
			return listaFacturas;
		for (Facturas factura : listaFacturas) {
			if (factura.getMoneda().equals(Constantes.MONEDA_NACIONAL))
				factura.setSimboloMoneda("S/");
			else if (factura.getMoneda().equals(Constantes.MONEDA_DOLAR))
				factura.setSimboloMoneda("$");

			if (factura.getFechaDocumento() != null)
				factura.setFechaDocumento("20" + factura.getFechaDocumento());
			if (factura.getFechaVenta() != null)
				factura.setFechaVenta("20" + factura.getFechaVenta());
			if (factura.getFechaRecibido() != null)
				factura.setFechaRecibido("20" + factura.getFechaRecibido());

			if (factura.getFechaDocumento() != null && factura.getFechaDocumento().length() == 6) {
//				factura.getFechaDocumento().si
			}

		}
		return listaFacturas;
	}

	@Override
	public ServiceResult<Map<String, Object>> guardarZip(MultipartFile archivoZip, MultipartFile archivoPdf,
			MultipartFile archivoInforme, MultipartFile archivoGuia) {
		ServiceResult<Map<String, Object>> response = new ServiceResult();
		try {
			Map<String, Object> resultado = new HashMap<>();
			System.out.println(archivoZip.getOriginalFilename().toUpperCase() );
			String tmp = UUID.randomUUID().toString().replace("-", "");
			resultado.put("token", tmp);
			guardarAdjuntos(archivoZip, archivoPdf, archivoInforme, archivoGuia, tmp);
			if (archivoZip.getOriginalFilename().toUpperCase().contains(".ZIP")) {
				resultado.put("factura", FilesUtils.descromprimirZIP(archivoZip));
			} else if (archivoZip.getOriginalFilename().toUpperCase().contains(".XML")) {
				File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + archivoZip.getName());
				archivoZip.transferTo(convFile);
				resultado.put("factura", FilesUtils.convertirXmlJson(convFile));
				convFile.delete();
			}
			response.setHttpStatus(HttpStatus.OK.value());
			response.setResultado(resultado);
		} catch (Exception e) {
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
			e.printStackTrace();
		}
		return response;
	}

	private void guardarAdjuntos(MultipartFile archivoZip, MultipartFile archivoPdf, MultipartFile archivoInforme,
			MultipartFile archivoGuia, String tmp) throws Exception {
		List<Archivo> lista = new ArrayList();

		Archivo archivo = new Archivo();
		if (archivoPdf != null) {
			archivo = new Archivo();
			archivo.setNombreArchivo(archivoPdf.getOriginalFilename());
			archivo.setArchivo(archivoPdf.getBytes());
			archivo.setIdParametro(Constantes.ARCHIVOS_COMPROBANTE);
			archivo.setIdDocumento(0);
			archivo.setIdDocumentoArchivo(1);
			archivo.setToken(tmp);
			lista.add(archivo);
		}
		if (archivoZip != null) {
			archivo = new Archivo();
			archivo.setNombreArchivo(archivoZip.getOriginalFilename());
			archivo.setArchivo(archivoZip.getBytes());
			archivo.setIdParametro(Constantes.ARCHIVOS_COMPROBANTE);
			archivo.setIdDocumento(0);
			archivo.setIdDocumentoArchivo(2);
			archivo.setToken(tmp);
			lista.add(archivo);
		}
		if (archivoGuia != null) {
			archivo = new Archivo();
			archivo.setNombreArchivo(archivoGuia.getOriginalFilename());
			archivo.setArchivo(archivoGuia.getBytes());
			archivo.setIdParametro(Constantes.ARCHIVOS_COMPROBANTE);
			archivo.setIdDocumento(0);
			archivo.setIdDocumentoArchivo(3);
			archivo.setToken(tmp);
			lista.add(archivo);
		}
		if (archivoInforme != null) {
			archivo = new Archivo();
			archivo.setNombreArchivo(archivoInforme.getOriginalFilename());
			archivo.setArchivo(archivoInforme.getBytes());
			archivo.setIdParametro(Constantes.ARCHIVOS_COMPROBANTE);
			archivo.setIdDocumento(0);
			archivo.setIdDocumentoArchivo(4);
			archivo.setToken(tmp);
			lista.add(archivo);
		}
		archivoRep.guardarFile(lista);
	}

	@Override
	public List<Comprobante> consultarComprobante(String usuariosresponsable, String nroOrden, String fecInicio,
			String fecFin, Integer estado, String nroDocumento, Integer idComprobante, Integer tipoComprobante) {
		List<Comprobante> listaFacturas = consultaDocRepository.consultarComprobante(usuariosresponsable, nroOrden,
				fecInicio, fecFin, estado, nroDocumento, idComprobante, tipoComprobante);
		for (Comprobante comprobante : listaFacturas) {
			comprobante.setListaComprobanteDetalle(
					consultaDocRepository.consultarComprobanteDetalle(comprobante.getIdComprobante()));
			comprobante.setListaComprobanteTrazabilidad(
					consultaDocRepository.consultarComprobanteTrazabilidad(comprobante.getIdComprobante()));
		}
		return listaFacturas;
	}

	@Override
	public ServiceResult<String> estadoFactura(String usuarioResponsable, Integer estado, Integer idComprobante,
			Integer id008Trazabilidad, String observacion, String usuarioModificador) {
		ServiceResult<String> response = new ServiceResult();
		try {
			consultaDocRepository.estadoFactura(usuarioResponsable, estado, idComprobante);
			consultaDocRepository.estadoFacturaTrazabilidad(idComprobante, id008Trazabilidad, observacion,
					usuarioModificador);
			response.setEsCorrecto(true);
			response.setHttpStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			e.printStackTrace();
			response.setEsCorrecto(false);
			response.setMensajeError("No se pudo cambiar de estado");
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		}
		return response;
	}

	@Override
	public ServiceResult<String> guardarComprobante(Comprobante comprobante) {
		ServiceResult<String> response = new ServiceResult();
		try {
			if (comprobante.getRequiereValidación() == null || comprobante.getRequiereValidación() == true) {
				if (comprobante.getOrdenContrato() == null && comprobante.getOrdenNumero() == null) {
					response.setEsCorrecto(false);
					response.setMensajeError("Ingresee número de orden y/o contrato por favor");
					response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
					return response;
				}
				final List<Orden> listaOrdenes = (comprobante.getOrdenNumero() != null)
						? consultaDocRepository.getOrdenesCabecera(null, comprobante.getOrdenNumero(), null, null, null)
						: null;
				final List<DocumentoOrigen> listaContratos = (comprobante.getOrdenContrato() != null)
						? docOriginRep.consultaDocumento(comprobante.getOrdenContrato())
						: null;
				if (listaOrdenes == null && listaContratos == null) {
					response.setEsCorrecto(false);
					response.setMensajeError("Número de orden y/o contrato ingresado es incorrecto");
					response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
					return response;
				} else if (comprobante.getOrdenContrato() != null && listaContratos.size() == 0) {
					response.setEsCorrecto(false);
					response.setMensajeError("No exite el número de contrato ingresado");
					response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
					return response;
				} else if (comprobante.getOrdenNumero() != null && listaOrdenes.size() == 0) {
					response.setEsCorrecto(false);
					response.setMensajeError("No exite el número de orden ingresado");
					response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
					return response;
//			}else if(!comprobante.getProveedorNumeroDocumento().trim().equals(listaContratos.get(listaContratos.size()-1).getNumeroDocumento())) {
//				response.setEsCorrecto(false);
//				response.setMensajeError("El número de contrato ingresado no te corresponde");
//				response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
//				return response;
//			}else if(!comprobante.getProveedorNumeroDocumento().trim().equals(listaOrdenes.get(listaOrdenes.size()-1).getDocumentoProveedor())) {
//				response.setEsCorrecto(false);
//				response.setMensajeError("El número de orden ingresado no te corresponde");
//				response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
//				return response;
				}
				comprobante.setUsuarioResponsable(
						((listaOrdenes != null) && (listaOrdenes.size() > 0)) ? listaOrdenes.get(0).getSolicitante()
								: ((listaContratos != null) && (listaContratos.size() > 0))
										? listaContratos.get(0).getUsuarioResponsable()
										: null);
			}

			comprobante.setIdComprobante(consultaDocRepository.guardarComprobante(comprobante));
			response.setResultado(comprobante.getIdComprobante() + "");
			comprobante.getListaComprobanteDetalle().forEach((item) -> {
				try {
					consultaDocRepository.guardarComprobanteDetalle(item, comprobante.getIdComprobante());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			consultaDocRepository.estadoFacturaTrazabilidad(comprobante.getIdComprobante(),
					Constantes.EstadoTrazabilidad, "", "");
			response.setEsCorrecto(true);
			response.setHttpStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			e.printStackTrace();
			response.setEsCorrecto(false);
			response.setMensajeError("No se pudo guardar comprobante");
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		}
		return response;
	}

	@Override
	public ServiceResult<Map<String, Object>> cargarFilesHonorarios(MultipartFile archivoZip, MultipartFile archivoPdf,
			MultipartFile archivoInforme, Integer idDocumento) {
		ServiceResult<Map<String, Object>> response = new ServiceResult();
		response.setHttpStatus(HttpStatus.OK.value());
		try {
			System.out.println(archivoZip.getOriginalFilename());
			if (archivoZip.getOriginalFilename().toUpperCase().contains(".XML")) {
				File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + archivoZip.getName());
				archivoZip.transferTo(convFile);
//				response.setResultado(FilesUtils.convertirXmlJson(convFile));
				response.setResultado(filtrarJsonRecibosHonorarios(
						(Map<String, Object>) FilesUtils.convertirXmlJson(convFile).get("Invoice")));
				convFile.delete();
			} else if (archivoZip.getName().contains(".zip")) {
				response.setResultado(filtrarJsonRecibosHonorarios(
						(Map<String, Object>) FilesUtils.descromprimirZIP(archivoZip).get("Invoice")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private Map<String, Object> filtrarJsonRecibosHonorarios(Map<String, Object> xmlCompleto) throws Exception {
		Map<String, Object> json = new HashMap();
		// RECEPTOR DATOS

		ComprobanteDetalle detalle = new ComprobanteDetalle();
		detalle.setDescripcion(
				((Map<String, Object>) ((Map<String, Object>) xmlCompleto.get("cac:InvoiceLine")).get("cac:Item"))
						.get("cbc:Description").toString());
		detalle.setCantidad(1.0);

		json.put("enteContratante", ((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) xmlCompleto
				.get("cac:AccountingCustomerParty")).get("cac:Party")).get("cac:PartyName")).get("cbc:Name"));
		json.put("enteTipoDocumento", "RUC");
		json.put("enteNroDocumento", ((Map<String, Object>) xmlCompleto.get("cac:AccountingCustomerParty"))
				.get("cbc:CustomerAssignedAccountID"));
		json.put("enteDireccion",
				((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) xmlCompleto
						.get("cac:AccountingCustomerParty")).get("cac:Party")).get("cac:PostalAddress"))
								.get("cbc:StreetName"));
		// EMISOR DATOS
		json.put("proveedorNombre", ((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) xmlCompleto
				.get("cac:AccountingSupplierParty")).get("cac:Party")).get("cac:PartyName")).get("cbc:Name"));
		json.put("proveedorNombreComercial",
				((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) xmlCompleto
						.get("cac:AccountingSupplierParty")).get("cac:Party")).get("cac:PartyName")).get("cbc:Name"));
		json.put("proveedorNumeroDocumento", ((Map<String, Object>) xmlCompleto.get("cac:AccountingSupplierParty"))
				.get("cbc:CustomerAssignedAccountID"));
		json.put("proveedorDireccion",
				((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) xmlCompleto
						.get("cac:AccountingSupplierParty")).get("cac:Party")).get("cac:PostalAddress"))
								.get("cbc:StreetName"));
		json.put("proveedorDireccionDepartamento", "LIMA");
		json.put("proveedorDireccionProvincia", "LIMA");
		json.put("proveedorTelefono",
				((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) xmlCompleto
						.get("cac:AccountingSupplierParty")).get("cac:Party")).get("cac:Contact"))
								.get("cbc:Telephone"));
		json.put("proveedorDireccionDistrito", "VILLA MARIA DEL TRIUNFO");
		json.put("proveedorDireccionZona",
				((Map<String, Object>) ((Map<String, Object>) xmlCompleto.get("cac:AccountingSupplierParty"))
						.get("cac:Party")).get("cac:PostalAddress"));

		json.put("idRecibo", xmlCompleto.get("cbc:ID"));
		// MONTOS TOTALES
		json.put("concepto",
				((Map<String, Object>) ((Map<String, Object>) xmlCompleto.get("cac:InvoiceLine")).get("cac:Item"))
						.get("cbc:Description"));
		json.put("observacion", "-");

		json.put("retencionTipo",
				((Map<String, Object>) ((Map<String, Object>) xmlCompleto.get("cac:InvoiceLine")).get("cac:TaxTotal"))
						.get("cac:TaxSubtotal"));
		json.put("incisoTipo", "A");
		json.put("incisoDescripcion", "DEL ARTÍCULO 33 DE LA LEY DEL IMPUESTO A LA RENTA");
		json.put("fechaEmisionCompleta", "10-04-2021");
		json.put("importeSubTotal",
				((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) xmlCompleto.get("cac:TaxTotal"))
						.get("cac:TaxSubtotal")).get("cbc:TaxableAmount")).get("content"));
		if (!json.get("importeSubTotal").toString().contains(".")) {
			json.put("importeSubTotal", json.get("importeSubTotal") + ".00");
		}
		json.put("importeIgv",
				((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) xmlCompleto.get("cac:TaxTotal"))
						.get("cac:TaxSubtotal")).get("cbc:TaxAmount")).get("content"));
		if (!json.get("importeIgv").toString().contains(".")) {
			json.put("importeIgv", json.get("importeIgv") + ".00");
		}
		json.put("importeTotal",
				((Map<String, Object>) ((Map<String, Object>) xmlCompleto.get("cac:LegalMonetaryTotal"))
						.get("cbc:PayableAmount")).get("content"));
		if (!json.get("importeTotal").toString().contains(".")) {
			json.put("importeTotal", json.get("importeTotal") + ".00");
		}
		json.put("tipoMonedaDescripcion", "SOLES");
		json.put("tipoMonedaISO",
				((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) xmlCompleto.get("cac:TaxTotal"))
						.get("cac:TaxSubtotal")).get("cbc:TaxAmount")).get("currencyID"));
		json.put("tipoMonedaSimbolo", "S/");
		String[] idRecibo = xmlCompleto.get("cbc:ID").toString().split("-");
		json.put("serie", idRecibo[0]);
		json.put("numero", idRecibo[1]);
		json.put("montoRecibidoTexto", xmlCompleto.get("cbc:Note"));

		json.put("fechaEmisionCompleto", xmlCompleto.get("cbc:IssueDate"));
		Locale spain = new Locale("es", "ES");
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd", spain);
		LocalDate fecha = LocalDate.parse(json.get("fechaEmisionCompleto").toString(), formato);
		json.put("fechaEmisionDia", fecha.getDayOfMonth());
		json.put("fechaEmisionMes", fecha.getMonth());
		json.put("fechaEmisionAnio", fecha.getYear());
		json.put("id007TipoComprobante", 26);

		json.put("fechaEmision", ((Map<String, Object>) ((Map<String, Object>) xmlCompleto.get("cac:OrderReference"))
				.get("cac:DocumentReference")).get("cbc:IssueDate"));
		json.put("fechaVencimiento", xmlCompleto.get("cbc:ExpiryDate"));
		detalle.setValorUnitario(Double.parseDouble(json.get("importeTotal").toString()));
		List<ComprobanteDetalle> listaComprobanteDetalle = new ArrayList();
		listaComprobanteDetalle.add(detalle);

		json.put("listaComprobanteDetalle", listaComprobanteDetalle);
		return json;
	}

}
