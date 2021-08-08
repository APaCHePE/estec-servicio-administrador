package com.pe.estec.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.pe.estec.config.Constantes;
import com.pe.estec.model.Catalogo;
import com.pe.estec.model.Facturas;
import com.pe.estec.model.Orden;
import com.pe.estec.model.request.ServiceResult;
import com.pe.estec.repository.ConsultaDocumentoRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	@Override
	public ServiceResult<Map<String, Object>> guardarZip(MultipartFile dataFile) {
		byte[] buffer = new byte[1024];
		Map<String,Object> json= new HashMap<>();
		ServiceResult<Map<String, Object>>response = new ServiceResult();
		try {
			File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+dataFile.getName());
			dataFile.transferTo(convFile);
			ZipInputStream zis = new ZipInputStream(new FileInputStream(convFile));
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
                String nombreArchivo = ze.getName();
                File archivoNuevo = new File("4L3X/" +File.separator + nombreArchivo);
                System.out.println("archivo descomprimido : " + archivoNuevo.getAbsoluteFile());
                new File(archivoNuevo.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(archivoNuevo);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                if(nombreArchivo!=null && nombreArchivo.toUpperCase().contains(".XML"))
                	response.setResultado(leerXml(archivoNuevo));
                fos.close();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            System.out.println("Listo");
		}catch (Exception e) {
            e.printStackTrace();
		}
		response.setHttpStatus(HttpStatus.OK.value());
		return response;
	}
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	private Map<String, Object> leerXml(File archivo) {
		try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document document = documentBuilder.parse(archivo);
            document.getDocumentElement().normalize();
            
            DOMSource domSource = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            JSONObject xmlJSONObj = XML.toJSONObject (writer.toString());   
            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {};
            Map<String, Object> mapping = new ObjectMapper().readValue(jsonPrettyPrintString, typeRef);
            return mapping;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
	@Override
	public ServiceResult<String> estadoFactura(String numeroFactura) {
		ServiceResult<String> response = new ServiceResult();
		try {
			consultaDocRepository.estadoFactura(numeroFactura);
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
}
