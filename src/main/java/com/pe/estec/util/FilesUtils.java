package com.pe.estec.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.springframework.web.multipart.MultipartFile;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pe.estec.request.ServiceResult;

public class FilesUtils {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	public static Map<String, Object> convertirXmlJson(File archivo) throws Exception {
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
		JSONObject xmlJSONObj = XML.toJSONObject(writer.toString());
		String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
		TypeReference<HashMap<String, Object>> typeRef = new TypeReference() {};
		Map<String, Object> mapping = new ObjectMapper().readValue(jsonPrettyPrintString, typeRef);
		return mapping;
	}

	public static Map<String, Object> descromprimirZIP(MultipartFile archivoZip) throws Exception {
		Map<String, Object> jsonRespuesta = new HashMap();
		byte[] buffer = new byte[1024];
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + archivoZip.getName());
		archivoZip.transferTo(convFile);
		ZipInputStream zis = new ZipInputStream(new FileInputStream(convFile));
		ZipEntry ze = zis.getNextEntry();
		while (ze != null) {
			String nombreArchivo = ze.getName();
			File archivoNuevo = new File(System.getProperty("java.io.tmpdir") + "/" + nombreArchivo);
			System.out.println("archivo descomprimido : " + archivoNuevo.getAbsoluteFile());
			new File(archivoNuevo.getParent()).mkdirs();
			FileOutputStream fos = new FileOutputStream(archivoNuevo);
			int len;
			while ((len = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			if (nombreArchivo != null && nombreArchivo.toUpperCase().contains(".XML")) {
				jsonRespuesta.put("Invoice",FilesUtils.convertirXmlJson(archivoNuevo).get("Invoice"));
				fos.close();
				ze = zis.getNextEntry();
				if (archivoNuevo.delete())
					System.out.println("El fichero ha sido borrado satisfactoriamente");
				else
					System.out.println("El fichero no puede ser borrado");

				zis.closeEntry();
				zis.close();
				convFile.delete();
				return jsonRespuesta;
			}
			fos.close();
			ze = zis.getNextEntry();
			if (archivoNuevo.delete())
				System.out.println("El fichero ha sido borrado satisfactoriamente");
			else
				System.out.println("El fichero no puede ser borrado");
		}
		zis.closeEntry();
		zis.close();

		convFile.delete();
		return null;
	}
}
