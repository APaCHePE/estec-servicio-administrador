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
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pe.estec.config.Constantes;
import com.pe.estec.model.Archivo;
import com.pe.estec.model.Comprobante;
import com.pe.estec.model.ComprobanteDetalle;
import com.pe.estec.model.Contrato;
import com.pe.estec.model.DocumentoOrigen;
import com.pe.estec.model.Facturas;
import com.pe.estec.model.Orden;
import com.pe.estec.model.request.ServiceResult;
import com.pe.estec.repository.ConsultaDocumentoRepository;
import com.pe.estec.repository.DocumentoOrigenRepository;
import com.pe.estec.util.FilesUtils;

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
	public List<Contrato> getContrato( Integer nroContrato){
		List<Contrato> listaContrato = consultaDocRepository.getContrato(nroContrato);
		return listaContrato;
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
	public ServiceResult<Map<String, Object>> guardarZip(MultipartFile archivoZip, MultipartFile archivoPdf) {
		byte[] buffer = new byte[1024];
		ServiceResult<Map<String, Object>> response = new ServiceResult();
		try {
			File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + archivoZip.getName());
			archivoZip.transferTo(convFile);
			ZipInputStream zis = new ZipInputStream(new FileInputStream(convFile));
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String nombreArchivo = ze.getName();
				File archivoNuevo = new File("4L3X/" + File.separator + nombreArchivo);
				System.out.println("archivo descomprimido : " + archivoNuevo.getAbsoluteFile());
				new File(archivoNuevo.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(archivoNuevo);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				if (nombreArchivo != null && nombreArchivo.toUpperCase().contains(".XML")) {
					try {
						response.setResultado(FilesUtils.convertirXmlJson(archivoNuevo));
					}catch (Exception e) {
						System.out.println("Ocurrió un error al convertir XML en JSON");
//						logger.error("Ocurrió un error al convertir XML en JSON");
					}
				}
				fos.close();
				ze = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
			guardarFile( archivoPdf);
			System.out.println("Listo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setHttpStatus(HttpStatus.OK.value());
		return response;
	}

	private void guardarFile(MultipartFile archivoRep) throws IOException {
		Archivo archivo = new Archivo();
		archivo.setNombreArchivo(archivoRep.getName());
		archivo.setArchivo(archivoRep.getBytes());
		archivo.setIdParametro(7);
		archivo.setIdDocumento(10);
		archivo.setIdArchivo(consultaDocRepository.guardarArchivo(archivo));
		consultaDocRepository.guardarArchivoRepo(archivo);
	}

	@Override
	public List<Comprobante> consultarComprobante( String usuariosresponsable, String nroOrden, String fecInicio, String fecFin, Integer estado,
			String nroDocumento, Integer idComprobante, Integer tipoComprobante) {
		System.out.println(tipoComprobante);
		List<Comprobante> listaFacturas = consultaDocRepository.consultarComprobante(usuariosresponsable, nroOrden, fecInicio, fecFin,
				estado, nroDocumento, idComprobante, tipoComprobante);
		for (Comprobante comprobante : listaFacturas) {
			comprobante.setListaComprobanteDetalle(
					consultaDocRepository.consultarComprobanteDetalle(comprobante.getIdComprobante()));
		}
		for (Comprobante comprobante : listaFacturas) {
			comprobante.setListaComprobanteTrazabilidad(
					consultaDocRepository.consultarComprobanteTrazabilidad(comprobante.getIdComprobante()));
		}
		return listaFacturas;
	}

	@Override
	public ServiceResult<String> estadoFactura(String usuarioResponsable, Integer estado, Integer idComprobante, Integer id008Trazabilidad,
			String observacion, String usuarioModificador) {
		ServiceResult<String> response = new ServiceResult();
		try {
			consultaDocRepository.estadoFactura(usuarioResponsable,estado, idComprobante);
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
			if (comprobante.getOrdenContrato() == null && comprobante.getOrdenNumero() == null) {
				response.setEsCorrecto(false);
				response.setMensajeError("Ingresee número de orden y/o contrato por favor");
				response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
				return response;
			}
			final List<Orden> listaOrdenes = (comprobante.getOrdenNumero()!=null)? consultaDocRepository.getOrdenesCabecera(null, comprobante.getOrdenNumero(),
					null, null, null): null;
			final List<DocumentoOrigen> listaContratos = (comprobante.getOrdenContrato()!=null)? 
					docOriginRep.consultaDocumento(comprobante.getOrdenContrato()): null;
			if(listaOrdenes == null && listaContratos == null) {
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
			}
			comprobante.setUsuarioResponsable(((listaOrdenes != null) && (listaOrdenes.size() > 0)) ? listaOrdenes.get(0).getSolicitante()
					: ((listaContratos != null) && (listaContratos.size() > 0)) ? listaContratos.get(0).getUsuarioResponsable() : null);
			
			comprobante.setIdComprobante(consultaDocRepository.guardarComprobante(comprobante));
			
			comprobante.getListaComprobanteDetalle().forEach((item) -> {

				try {
					consultaDocRepository.guardarComprobanteDetalle(item, comprobante.getIdComprobante());
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			});
			consultaDocRepository.estadoFacturaTrazabilidad(comprobante.getIdComprobante(), Constantes.EstadoTrazabilidad
							,"","" );
			response.setEsCorrecto(true);
			response.setHttpStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			System.out.println("catch");
			e.printStackTrace();
			response.setEsCorrecto(false);
			response.setMensajeError("No se pudo guardar comprobante");
			response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		}
		return response;
	}

	@Override
	public ServiceResult<Map<String, Object>> cargarFilesHonorarios(MultipartFile archivoZip,
			MultipartFile archivoPdf) {
		ServiceResult<Map<String, Object>> response = new ServiceResult();
		response.setHttpStatus(HttpStatus.OK.value());
		try {

			System.out.println(archivoZip.getOriginalFilename());
			if(archivoZip.getOriginalFilename().toUpperCase().contains(".XML")) {
				File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+archivoZip.getName());
				archivoZip.transferTo(convFile);
//				response.setResultado(FilesUtils.convertirXmlJson(convFile));
				response.setResultado(filtrarJsonRecibosHonorarios(
						(Map<String, Object>)FilesUtils.convertirXmlJson(convFile).get("Invoice")));
			}else if(archivoZip.getName().contains(".zip")) {
				response.setResultado(filtrarJsonRecibosHonorarios(
						(Map<String, Object>)FilesUtils.descromprimirZIP(archivoZip).get("Invoice")));
			}
		}catch (Exception e) {
			
		}
		return response;
	}
	private Map<String, Object> filtrarJsonRecibosHonorarios(Map<String, Object> xmlCompleto){
		Map<String, Object> json = new HashMap();
		try {
			//RECEPTOR DATOS
			
			ComprobanteDetalle detalle = new ComprobanteDetalle();
            detalle.setDescripcion(((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:InvoiceLine")).get("cac:Item")).get("cbc:Description").toString() );
			detalle.setCantidad(1.0);
            
			json.put("enteContratante" , ((Map<String, Object>)((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:AccountingCustomerParty")).get("cac:Party")).get("cac:PartyName")).get("cbc:Name"));
			json.put("enteTipoDocumento" , "RUC");
			json.put("enteNroDocumento" , ((Map<String, Object>) xmlCompleto.get("cac:AccountingCustomerParty")).get("cbc:CustomerAssignedAccountID"));
			json.put("enteDireccion" , ((Map<String, Object>)((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:AccountingCustomerParty")).get("cac:Party")).get("cac:PostalAddress")).get("cbc:StreetName"));
			//EMISOR DATOS
			json.put("proveedorNombre" , ((Map<String, Object>)((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:AccountingSupplierParty")).get("cac:Party")).get("cac:PartyName")).get("cbc:Name"));
			json.put("proveedorNombreComercial" , ((Map<String, Object>)((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:AccountingSupplierParty")).get("cac:Party")).get("cac:PartyName")).get("cbc:Name"));
			json.put("proveedorNumeroDocumento", ((Map<String, Object>) xmlCompleto.get("cac:AccountingSupplierParty")).get("cbc:CustomerAssignedAccountID"));
			json.put("proveedorDireccion", ((Map<String, Object>)((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:AccountingSupplierParty")).get("cac:Party")).get("cac:PostalAddress")).get("cbc:StreetName"));
			json.put("proveedorDireccionDepartamento" , "LIMA");
			json.put("proveedorDireccionProvincia" , "LIMA");
			json.put("proveedorTelefono" ,((Map<String, Object>)((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:AccountingSupplierParty")).get("cac:Party")).get("cac:Contact")).get("cbc:Telephone") );
			json.put("proveedorDireccionDistrito" , "VILLA MARIA DEL TRIUNFO");
			json.put("proveedorDireccionZona" , ((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:AccountingSupplierParty")).get("cac:Party")).get("cac:PostalAddress"));
			
			json.put("idRecibo", xmlCompleto.get("cbc:ID"));
			//MONTOS TOTALES
			json.put("concepto" , ((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:InvoiceLine")).get("cac:Item")).get("cbc:Description") );
			json.put("observacion" , "-");
			
			json.put("retencionTipo" , ((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:InvoiceLine")).get("cac:TaxTotal")).get("cac:TaxSubtotal") );
			json.put("incisoTipo" , "A");
			json.put("incisoDescripcion" , "DEL ARTÍCULO 33 DE LA LEY DEL IMPUESTO A LA RENTA");
			json.put("fechaEmisionCompleta" , "10-04-2021");
			json.put("importeSubTotal" , ((Map<String, Object>)((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:TaxTotal")).get("cac:TaxSubtotal")).get("cbc:TaxableAmount")).get("content"));
			if(!json.get("importeSubTotal").toString().contains(".")) {
				json.put("importeSubTotal" , json.get("importeSubTotal")+".00");
			}
			json.put("importeIgv" , ((Map<String, Object>)((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:TaxTotal")).get("cac:TaxSubtotal")).get("cbc:TaxAmount")).get("content"));
			if(!json.get("importeIgv").toString().contains(".")) {
				json.put("importeIgv" , json.get("importeIgv")+".00");
			}
			json.put("importeTotal" ,((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:LegalMonetaryTotal")).get("cbc:PayableAmount")).get("content"));
			if(!json.get("importeTotal").toString().contains(".")) {
				json.put("importeTotal" , json.get("importeTotal")+".00");
			}
			json.put("tipoMonedaDescripcion" , "SOLES");
			json.put("tipoMonedaISO" ,((Map<String, Object>) ((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:TaxTotal")).get("cac:TaxSubtotal")).get("cbc:TaxAmount")).get("currencyID"));
			json.put("tipoMonedaSimbolo" , "S/");
			String[] idRecibo = xmlCompleto.get("cbc:ID").toString().split("-");
			json.put("serie" , idRecibo[0]);
			json.put("numero" , idRecibo[1]);
			json.put("montoRecibidoTexto", xmlCompleto.get("cbc:Note"));

			json.put("fechaEmisionCompleto" , xmlCompleto.get("cbc:IssueDate"));
			Locale spain=new Locale("es", "ES");
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd", spain); 
			LocalDate fecha = LocalDate.parse(json.get("fechaEmisionCompleto").toString(), formato); 
			json.put("fechaEmisionDia" , fecha.getDayOfMonth());
			json.put("fechaEmisionMes" , fecha.getMonth());
			json.put("fechaEmisionAnio" , fecha.getYear());
			json.put("id007TipoComprobante" , 26);
			
			json.put("fechaEmision", ((Map<String, Object>)((Map<String, Object>) xmlCompleto.get("cac:OrderReference")).get("cac:DocumentReference")).get("cbc:IssueDate"));
			json.put("fechaVencimiento", xmlCompleto.get("cbc:ExpiryDate"));
			detalle.setValorUnitario(Double.parseDouble(json.get("importeTotal").toString()));
            List<ComprobanteDetalle> listaComprobanteDetalle = new ArrayList();
            listaComprobanteDetalle.add(detalle);
            
            json.put("listaComprobanteDetalle", listaComprobanteDetalle);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
