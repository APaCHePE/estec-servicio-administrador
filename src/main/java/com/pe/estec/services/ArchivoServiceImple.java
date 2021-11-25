package com.pe.estec.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.pe.estec.model.Archivo;
import com.pe.estec.model.Asiento;
import com.pe.estec.repository.ArchivoRepository;
import com.pe.estec.repository.FileRepository;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


@Service
public class ArchivoServiceImple implements ArchivoService{

	@Autowired
	private FileRepository fileRepo;
	@Autowired
	private ArchivoRepository archivoRepo;
	@Override
	public Archivo recuperarEntidadArchivo(int tipoEntidad,int  idEntidad,int entidadArchivo, String token) {
		Archivo archivo = fileRepo.recuperaEntidadArchivo(tipoEntidad, idEntidad, entidadArchivo, token);
		archivo.setArchivoBase64(Base64.getEncoder().encodeToString(archivo.getArchivo()));
		return archivo;
	}
	@Override
	public void guardarFile(List<Archivo> listaArchivos) throws IOException {
		listaArchivos.forEach(item ->{
			item.setIdArchivo(archivoRepo.guardarArchivo(item));
			archivoRepo.guardarArchivoRepo(item);
		});
	}
	@Override
	public void actualizarTemporal(String token, Integer idEntidad) throws Exception {
		archivoRepo.actualizarTemporal(token, idEntidad);
	}
	
	
	@Override
	public InputStreamResource obtenerEstadoCuentaRep(Integer secuencia, List<Asiento> listAsiento, Integer igv, String detraccion,String distribucion) {
		try {
		InputStream is = this.getClass().getResourceAsStream("/Reportes/Asiento_contabilidad.jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(is);
		JasperReport reporteJasper = JasperCompileManager.compileReport(jasperDesign);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		listAsiento.forEach(System.out :: println);
		
		JRBeanCollectionDataSource datosReporteJasper = new JRBeanCollectionDataSource(listAsiento.get(0).getListAsientoDetalle());
		Calendar fecha = new GregorianCalendar();                                                     
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fechaActual = dia + "/" + (mes+1) + "/" + año;
        String moneda = "MN";
		Map<String, Object> params = new HashMap<>();
		params.put("fecHora", fechaActual);
		params.put("moneda", moneda);
		params.put("comprobante", listAsiento.get(0).getId_comprobante()+"a");
		params.put("concepto", listAsiento.get(0).getConcepto());
		listAsiento.get(0).setIdTipoComprobante(24);
		if(listAsiento.get(0).getIdTipoComprobante()==26) {
			params.put("nroSubDiario", "15");
			params.put("concepSubDiario", "REGISTRO HONORARIOS" );
		}else {
			if(detraccion.equals("true")) {
				params.put("nroSubDiario", "10");
				params.put("concepSubDiario", "REGISTRO COMPRAS DETRA" );
			}else {
				params.put("nroSubDiario", "11");
				params.put("concepSubDiario", "REGISTRO COMPRAS LOCAL" );
			}
		}
		
		params.put("ruc", listAsiento.get(0).getRuc());
		
		//parametros de la tabla 
//		if(igv == 1) {
//		params.put("cuenta", "401111");
//		params.put("anexo", " ");
//		params.put("descripcion", " ");
//		params.put("cc", " ");
//		params.put("tp", " ");
//		params.put("debe", listComprobante.get(0).getImporteIgv().toString());
//		params.put("haber", " ");
//		params.put("documento", "OT 01-3030");
//		params.put("fechavenci", fechaActual);
//		params.put("totaldebe", listComprobante.get(0).getImporteTotal().toString());
//		params.put("totalhaber", listComprobante.get(0).getImporteTotal().toString());
//		params.put("sinIgv", listComprobante.get(0).getImporteSubTotal().toString());
//		params.put("cc2", "100");
//		params.put("cuenta1", distribucion);
//		}
//		else {
//		params.put("cuenta", " ");
//		params.put("anexo", " ");
//		params.put("descripcion", " ");
//		params.put("cc", " ");
//		params.put("tp", " ");
//		params.put("debe"," ");
//		params.put("haber", " ");
//		params.put("documento", " ");
//		params.put("fechavenci", " ");
//		params.put("totaldebe", " ");
//		params.put("totalhaber", " ");
//		params.put("sinIgv", "");
//		params.put("cc2", "");
//		params.put("cuenta1", "");}
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporteJasper, params, datosReporteJasper);
		byte[] eecc = JasperExportManager.exportReportToPdf(jasperPrint);
		InputStreamResource fileInputStream = new InputStreamResource(new ByteArrayInputStream(eecc));
		return fileInputStream;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}

		
	}


}
