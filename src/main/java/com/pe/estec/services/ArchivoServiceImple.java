package com.pe.estec.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.pe.estec.model.Archivo;
import com.pe.estec.repository.ArchivoRepository;
import com.pe.estec.repository.FileRepository;
//**********************************++

import net.sf.jasperreports.engine.JRException;
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
	public InputStreamResource obtenerEstadoCuentaRep(Integer secuencia) throws JRException {
		String periodo = "";
		String estados = "";

		InputStream is = this.getClass().getResourceAsStream("/Reportes/Asiento.jrxml");

		JasperDesign jasperDesign = JRXmlLoader.load(is);
		JasperReport reporteJasper = JasperCompileManager.compileReport(jasperDesign);

		JRBeanCollectionDataSource datosReporteJasper = new JRBeanCollectionDataSource(null);

		Map<String, Object> params = new HashMap<>();

		JasperPrint jasperPrint = JasperFillManager.fillReport(reporteJasper, params, datosReporteJasper);
		byte[] eecc = JasperExportManager.exportReportToPdf(jasperPrint);

		InputStreamResource fileInputStream = new InputStreamResource(new ByteArrayInputStream(eecc));

		return fileInputStream;
	}


}
