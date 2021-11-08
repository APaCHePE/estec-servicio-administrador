package com.pe.estec.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.pe.estec.model.PersonaPIDE;
import com.pe.estec.model.PideCarnet;
import com.pe.estec.model.PideSunarp;
import com.pe.estec.model.PideSunat;
import com.pe.estec.model.RespuestaPIDE;

//import gob.pe.miraflores.comunes.model.bean.PersonaPIDE;
//import gob.pe.miraflores.comunes.model.bean.PideAntecedentes;
//import gob.pe.miraflores.comunes.model.bean.PideCarnet;
//import gob.pe.miraflores.comunes.model.bean.PideSunarp;
//import gob.pe.miraflores.comunes.model.bean.PideSunat;
//import gob.pe.miraflores.comunes.model.bean.PideTipoCambio;
//import gob.pe.miraflores.comunes.model.bean.RespuestaPIDE;

public class UtilMap {
 public static RespuestaPIDE MapearPersonaPide(Map<String, Object> _respuestaPIDE) {
	 RespuestaPIDE respuesta=new RespuestaPIDE();
	 if (_respuestaPIDE.get("coResultado").toString().equals("1001")) {
		 respuesta.setCoResultado(_respuestaPIDE.get("coResultado").toString());
		 respuesta.setDeResultado(_respuestaPIDE.get("deResultado").toString());
	 }else {
		 respuesta.setCoResultado(_respuestaPIDE.get("coResultado").toString());
		 respuesta.setDeResultado(_respuestaPIDE.get("deResultado").toString());
		 _respuestaPIDE=(LinkedHashMap)_respuestaPIDE.get("datosPersona");
		 PersonaPIDE persona=new PersonaPIDE();
		 persona.setApPrimer(_respuestaPIDE.get("apPrimer").toString());
		 persona.setApSegundo(_respuestaPIDE.get("apSegundo").toString());
		 persona.setDireccion(_respuestaPIDE.get("direccion").toString());
		 persona.setEstadoCivil(_respuestaPIDE.get("estadoCivil").toString());
		 persona.setFoto(_respuestaPIDE.get("foto").toString());
		 persona.setPrenombres(_respuestaPIDE.get("prenombres").toString());
		 persona.setRestriccion(_respuestaPIDE.get("restriccion").toString());
		 persona.setUbigeo(_respuestaPIDE.get("ubigeo").toString());
		 respuesta.setPersona(persona);
	 }
	 return respuesta;
 }
 
 public static RespuestaPIDE MapearSunatDatosPrincipales(Map<String, Object> _respuestaPIDE) {
	 RespuestaPIDE respuesta=new RespuestaPIDE();
	 PideSunat sunat = new PideSunat();
	 Map<String, Object> nombre = (LinkedHashMap)_respuestaPIDE.get("ddp_nombre");
	 Map<String, Object> fechaAct = (LinkedHashMap)_respuestaPIDE.get("ddp_fecact");
	 Map<String, Object> fechaAlta = (LinkedHashMap)_respuestaPIDE.get("ddp_fecalt");
	 Map<String, Object> fechaBaja = (LinkedHashMap)_respuestaPIDE.get("ddp_fecbaj");
	 Map<String, Object> tipoVia = (LinkedHashMap)_respuestaPIDE.get("desc_tipvia");
	 Map<String, Object> nombVia = (LinkedHashMap)_respuestaPIDE.get("ddp_nomvia");
	 Map<String, Object> numVia = (LinkedHashMap)_respuestaPIDE.get("ddp_numer1");
	 Map<String, Object> interior = (LinkedHashMap)_respuestaPIDE.get("ddp_inter1");
	 Map<String, Object> tipZona = (LinkedHashMap)_respuestaPIDE.get("desc_tipzon");
	 Map<String, Object> zona = (LinkedHashMap)_respuestaPIDE.get("ddp_nomzon");
	 Map<String, Object> referencia = (LinkedHashMap)_respuestaPIDE.get("ddp_refer1");
	 Map<String, Object> condDomi = (LinkedHashMap)_respuestaPIDE.get("desc_flag22");
	 Map<String, Object> codDependencia = (LinkedHashMap)_respuestaPIDE.get("desc_numreg");
	 Map<String, Object> tipoContrib = (LinkedHashMap)_respuestaPIDE.get("desc_tpoemp");
	 Map<String, Object> secuencia = (LinkedHashMap)_respuestaPIDE.get("ddp_secuen");
	 Map<String, Object> activo = (LinkedHashMap)_respuestaPIDE.get("desc_estado");
	 Map<String, Object> habido = (LinkedHashMap)_respuestaPIDE.get("desc_flag22");
	 Map<String, Object> dpto = (LinkedHashMap)_respuestaPIDE.get("desc_dep");
	 Map<String, Object> provincia = (LinkedHashMap)_respuestaPIDE.get("desc_prov");
	 Map<String, Object> distrito = (LinkedHashMap)_respuestaPIDE.get("desc_dist");
	 Map<String, Object> actEconomica = (LinkedHashMap)_respuestaPIDE.get("desc_ciiu");
	 Map<String, Object> estado = (LinkedHashMap)_respuestaPIDE.get("desc_estado");
	 Map<String, Object> tipoPersona = (LinkedHashMap)_respuestaPIDE.get("desc_identi");
	 Map<String, Object> libreta = (LinkedHashMap)_respuestaPIDE.get("ddp_lllttt");
	 Map<String, Object> ruc = (LinkedHashMap)_respuestaPIDE.get("ddp_numruc");
	 
	 if(dpto.get("$")!=null) sunat.setDepartamento(dpto.get("$").toString());
	 if(provincia.get("$")!=null) sunat.setProvincia(provincia.get("$").toString());
	 if(distrito.get("$")!=null) sunat.setDistrito(distrito.get("$").toString());
	 if(actEconomica.get("$")!=null) sunat.setActEconomica(actEconomica.get("$").toString());
	 if(estado.get("$")!=null) sunat.setEstadoContrib(estado.get("$").toString());
	 if(fechaAct.get("$")!=null) sunat.setFechaActualiza(fechaAct.get("$").toString());
	 if(fechaAlta.get("$")!=null) sunat.setFechaAlta(fechaAlta.get("$").toString());
	 if(fechaBaja.get("$")!=null) sunat.setFechaBaja(fechaBaja.get("$").toString()); 	 
	 if(tipoPersona.get("$")!=null) sunat.setTipoPersona(tipoPersona.get("$").toString());
	 if(libreta.get("$")!=null) sunat.setLibretaTributaria(libreta.get("$").toString());
	 if(nombre.get("$")!=null) sunat.setNombresContrib(nombre.get("$").toString());
	 if(tipoVia.get("$")!=null) sunat.setTipoVia(tipoVia.get("$").toString());
	 if(nombVia.get("$")!=null) sunat.setNombVia(nombVia.get("$").toString());
	 if(numVia.get("$")!=null) sunat.setNumVia(numVia.get("$").toString());
	 if(interior.get("$")!=null) sunat.setInterior(interior.get("$").toString());
//	 sunat.setTipoZona(tipZona.get("$").toString());
	 if(zona.get("$")!=null) sunat.setZona(zona.get("$").toString());
	 if(referencia.get("$")!=null) sunat.setReferencia(referencia.get("$").toString());
	 if(condDomi.get("$")!=null) sunat.setCondDomicilio(condDomi.get("$").toString());
	 if(codDependencia.get("$")!=null) sunat.setDependencia(codDependencia.get("$").toString());
	 if(ruc.get("$")!=null) sunat.setNumRuc(ruc.get("$").toString());
	 if(tipoContrib.get("$")!=null) sunat.setTipoContrib(tipoContrib.get("$").toString());
	 if(secuencia.get("$")!=null) sunat.setSecuencia(secuencia.get("$").toString());
	 if(activo.get("$")!=null) sunat.setActivo(activo.get("$").toString());
	 if(habido.get("$")!=null) sunat.setHabido(habido.get("$").toString());
	 	 
	 respuesta.setSunat(sunat);
	 
	 return respuesta;
 }
 
 public static RespuestaPIDE MapearSunatDatosSecundarios(Map<String, Object> _respuestaPIDE) {
	 RespuestaPIDE respuesta=new RespuestaPIDE();
	 PideSunat sunat = new PideSunat();
	 Map<String, Object> califConducta = (LinkedHashMap)_respuestaPIDE.get("dds_califi");
	 Map<String, Object> actComercioEx = (LinkedHashMap)_respuestaPIDE.get("desc_comext");
	 Map<String, Object> fechaConstitucion = (LinkedHashMap)_respuestaPIDE.get("dds_consti");
	 Map<String, Object> tipoConta = (LinkedHashMap)_respuestaPIDE.get("desc_contab");
	 Map<String, Object> tipoDocIdent = (LinkedHashMap)_respuestaPIDE.get("desc_docide");
	 Map<String, Object> numDocIdent = (LinkedHashMap)_respuestaPIDE.get("dds_nrodoc");
	 Map<String, Object> condDomiciliado = (LinkedHashMap)_respuestaPIDE.get("desc_domici");
	 Map<String, Object> tipoFactura = (LinkedHashMap)_respuestaPIDE.get("desc_factur");
	 Map<String, Object> fechaNacimiento = (LinkedHashMap)_respuestaPIDE.get("dds_fecnac");
	 Map<String, Object> nroAsientoRRPP = (LinkedHashMap)_respuestaPIDE.get("dds_asient");
	 Map<String, Object> tomoRRPP = (LinkedHashMap)_respuestaPIDE.get("dds_ficha");
	 Map<String, Object> numFolioRRPP = (LinkedHashMap)_respuestaPIDE.get("dds_nfolio");
	 Map<String, Object> fechaIniAct = (LinkedHashMap)_respuestaPIDE.get("dds_inicio");
	 Map<String, Object> numLicMunicip = (LinkedHashMap)_respuestaPIDE.get("dds_licenc");
	 Map<String, Object> nacionalidad = (LinkedHashMap)_respuestaPIDE.get("dds_nacion");
	 Map<String, Object> nombComercial = (LinkedHashMap)_respuestaPIDE.get("dds_nomcom");
	 Map<String, Object> numRuc = (LinkedHashMap)_respuestaPIDE.get("dds_numruc");
	 Map<String, Object> orgEntidad = (LinkedHashMap)_respuestaPIDE.get("desc_orient");
	 Map<String, Object> paisPasaporte = (LinkedHashMap)_respuestaPIDE.get("dds_paispa");
	 Map<String, Object> numPasaporte = (LinkedHashMap)_respuestaPIDE.get("dds_pasapo");
	 Map<String, Object> cPatronal = (LinkedHashMap)_respuestaPIDE.get("dds_patron");
	 Map<String, Object> sexo = (LinkedHashMap)_respuestaPIDE.get("desc_sexo");
	 Map<String, Object> numTelefono = (LinkedHashMap)_respuestaPIDE.get("dds_telef1");
	 Map<String, Object> numTelefono2 = (LinkedHashMap)_respuestaPIDE.get("dds_telef2");
	 Map<String, Object> numTelefono3 = (LinkedHashMap)_respuestaPIDE.get("dds_telef3");
	 Map<String, Object> numFax = (LinkedHashMap)_respuestaPIDE.get("dds_numfax");
	 
	 if(califConducta.get("$")!=null) sunat.setCalConducta(califConducta.get("$").toString());
	 if(actComercioEx.get("$")!=null) sunat.setActComercioExt(actComercioEx.get("$").toString());
	 if(fechaConstitucion.get("$")!=null) sunat.setFechaConstitucion(fechaConstitucion.get("$").toString()); 
	 if(tipoConta.get("$")!=null) sunat.setTipoContabilidad(tipoConta.get("$").toString());
//	 sunat.setTipoDocIdentidad(tipoDocIdent.get("$").toString());
	 if(numDocIdent.get("$")!=null) sunat.setNumDocIdentidad(numDocIdent.get("$").toString());
//	 sunat.setCondDomiciliado(condDomiciliado.get("$").toString());
	 if(tipoFactura.get("$")!=null) sunat.setTipoFactura(tipoFactura.get("$").toString());
//	 sunat.setFechaNacimiento(fechaNacimiento.get("$").toString());
	 if(nroAsientoRRPP.get("$")!=null) sunat.setNumAsientoRRPP(nroAsientoRRPP.get("$").toString());
	 if(tomoRRPP.get("$")!=null) sunat.setTomoRRPP(tomoRRPP.get("$").toString());
	 if(numFolioRRPP.get("$")!=null) sunat.setNumFolioRRPP(numFolioRRPP.get("$").toString());
	 if(fechaIniAct.get("$")!=null) sunat.setFechaIniActividades(fechaIniAct.get("$").toString());
	 if(numLicMunicip.get("$")!=null) sunat.setNumLicMunicipal(numLicMunicip.get("$").toString());
	 if(nacionalidad.get("$")!=null) sunat.setNacionalidad(nacionalidad.get("$").toString());
	 if(nombComercial.get("$")!=null) sunat.setNombComercial(nombComercial.get("$").toString());
	 if(numRuc.get("$")!=null) sunat.setNroRuc(numRuc.get("$").toString());
	 if(orgEntidad.get("$")!=null) sunat.setOrgEntidad(orgEntidad.get("$").toString());
	 if(paisPasaporte.get("$")!=null) sunat.setPaisPasaporte(paisPasaporte.get("$").toString());
	 if(numPasaporte.get("$")!=null) sunat.setNumPasaporte(numPasaporte.get("$").toString());
	 if(cPatronal.get("$")!=null) sunat.setCarnetPatron(cPatronal.get("$").toString());
//	 sunat.setSexo(sexo.get("$").toString());
	 if(numTelefono.get("$")!=null) sunat.setNumTelefono(numTelefono.get("$").toString());
	 if(numTelefono2.get("$")!=null) sunat.setNumTelefono2(numTelefono2.get("$").toString());
	 if(numTelefono3.get("$")!=null) sunat.setNumTelefono3(numTelefono3.get("$").toString());
	 if(numFax.get("$")!=null) sunat.setNumFax(numFax.get("$").toString());
	 
	 respuesta.setSunat(sunat);
	 
	 return respuesta;
 }
 
 public static RespuestaPIDE MapearSunatDatosT1144(Map<String, Object> _respuestaPIDE) {
	 RespuestaPIDE respuesta=new RespuestaPIDE();
	 PideSunat sunat = new PideSunat();
	 Map<String, Object> actEcono1 = (LinkedHashMap)_respuestaPIDE.get("des_ciiu2");
	 Map<String, Object> actEcono2 = (LinkedHashMap)_respuestaPIDE.get("des_ciiu3");
	 Map<String, Object> correo1 = (LinkedHashMap)_respuestaPIDE.get("cod_correo1");
	 Map<String, Object> correo2 = (LinkedHashMap)_respuestaPIDE.get("cod_correo2");
	 Map<String, Object> dpto1 = (LinkedHashMap)_respuestaPIDE.get("des_depar1");
	 Map<String, Object> dpto2 = (LinkedHashMap)_respuestaPIDE.get("des_depar2");
	 Map<String, Object> dpto3 = (LinkedHashMap)_respuestaPIDE.get("des_depar3");
	 Map<String, Object> dpto4 = (LinkedHashMap)_respuestaPIDE.get("des_depar4");
	 Map<String, Object> dpto5 = (LinkedHashMap)_respuestaPIDE.get("des_depar5");
	 Map<String, Object> numTelef1 = (LinkedHashMap)_respuestaPIDE.get("num_telef1");
	 Map<String, Object> numTelef2 = (LinkedHashMap)_respuestaPIDE.get("num_telef2");
	 Map<String, Object> numTelef3 = (LinkedHashMap)_respuestaPIDE.get("num_telef3");
	 Map<String, Object> numTelef4 = (LinkedHashMap)_respuestaPIDE.get("num_telef4");
	 Map<String, Object> numFax = (LinkedHashMap)_respuestaPIDE.get("num_fax");
	 Map<String, Object> desAsientoRRPP = (LinkedHashMap)_respuestaPIDE.get("des_asiento");
	 Map<String, Object> pRegistral = (LinkedHashMap)_respuestaPIDE.get("des_parreg");
	 Map<String, Object> refNotifica = (LinkedHashMap)_respuestaPIDE.get("des_refnot");
	 Map<String, Object> condLegal = (LinkedHashMap)_respuestaPIDE.get("des_conleg");
	 Map<String, Object> indCorreo = (LinkedHashMap)_respuestaPIDE.get("ind_correo1");
	 Map<String, Object> fechaConfirmaCorreo = (LinkedHashMap)_respuestaPIDE.get("fec_confir1");
	 Map<String, Object> indCorreo2 = (LinkedHashMap)_respuestaPIDE.get("ind_correo2");
	 Map<String, Object> fechaConfirmaCorreo2 = (LinkedHashMap)_respuestaPIDE.get("fec_confir2");
	 Map<String, Object> tipoPresenta = (LinkedHashMap)_respuestaPIDE.get("des_proind");
	 Map<String, Object> km = (LinkedHashMap)_respuestaPIDE.get("num_kilom");
	 Map<String, Object> mz = (LinkedHashMap)_respuestaPIDE.get("num_manza");
	 Map<String, Object> dpto = (LinkedHashMap)_respuestaPIDE.get("num_depar");
	 Map<String, Object> lote = (LinkedHashMap)_respuestaPIDE.get("num_lote");
	 Map<String, Object> ruc = (LinkedHashMap)_respuestaPIDE.get("num_ruc");
	 
//	 sunat.setT1144ActEconomicaSec(actEcono1.get("$").toString());
//	 sunat.setT1144ActEconomicaSecu(actEcono2.get("$").toString());
	 if(correo1.get("$")!=null) sunat.setT1144Correo1(correo1.get("$").toString());
	 if(correo2.get("$")!=null) sunat.setT1144Correo2(correo2.get("$").toString());
	 if(dpto1.get("$")!=null) sunat.setT1144Departamento1(dpto1.get("$").toString());
	 if(dpto2.get("$")!=null) sunat.setT1144Departamento2(dpto2.get("$").toString());
	 if(dpto3.get("$")!=null) sunat.setT1144Departamento3(dpto3.get("$").toString());
	 if(dpto4.get("$")!=null) sunat.setT1144Departamento4(dpto4.get("$").toString());
	 if(dpto5.get("$")!=null) sunat.setT1144Departamento5(dpto5.get("$").toString());
	 if(numTelef1.get("$")!=null) sunat.setT1144Telef1(numTelef1.get("$").toString());
	 if(numTelef2.get("$")!=null) sunat.setT1144Telef2(numTelef2.get("$").toString());
	 if(numTelef3.get("$")!=null) sunat.setT1144Telef3(numTelef3.get("$").toString());
	 if(numTelef4.get("$")!=null) sunat.setT1144Telef4(numTelef4.get("$").toString());
	 if(numFax.get("$")!=null) sunat.setT1144NumFax(numFax.get("$").toString());
	 if(desAsientoRRPP.get("$")!=null) sunat.setT1144AsientoRRPP(desAsientoRRPP.get("$").toString());
	 if(pRegistral.get("$")!=null) sunat.setT1144pRegistral(pRegistral.get("$").toString());
	 if(refNotifica.get("$")!=null) sunat.setT1144RefNotificacion(refNotifica.get("$").toString());
	 if(condLegal.get("$")!=null) sunat.setT1144CondLegalDomi(condLegal.get("$").toString());
	 if(indCorreo.get("$")!=null) sunat.setT1144IndCorreo(indCorreo.get("$").toString());
	 if(fechaConfirmaCorreo.get("$")!=null) sunat.setT1144FechaConfirmaCorreo(fechaConfirmaCorreo.get("$").toString());
	 if(indCorreo2.get("$")!=null) sunat.setT1144IndCorreo2(indCorreo2.get("$").toString());
	 if(fechaConfirmaCorreo2.get("$")!=null) sunat.setT1144FechaConfirmaCorreo2(fechaConfirmaCorreo2.get("$").toString());
	 if(tipoPresenta.get("$")!=null) sunat.setT1144TipoRepresentacion(tipoPresenta.get("$").toString());
	 if(km.get("$")!=null) sunat.setT1144Km(km.get("$").toString());
	 if(mz.get("$")!=null) sunat.setT1144Mz(mz.get("$").toString());
	 if(dpto.get("$")!=null) sunat.setT1144Dpto(dpto.get("$").toString());
	 if(lote.get("$")!=null) sunat.setT1144NumLote(lote.get("$").toString());
	 if(ruc.get("$")!=null) sunat.setT1144NumRuc(ruc.get("$").toString());
	 
	 respuesta.setSunat(sunat);

	 return respuesta;
 }
 
 public static RespuestaPIDE MapearSunatDatosT362(Map<String, Object> _respuestaPIDE) {
	 RespuestaPIDE respuesta=new RespuestaPIDE();
	 PideSunat sunat = new PideSunat();
	 Map<String, Object> descRRPP = (LinkedHashMap)_respuestaPIDE.get("desc_numreg");
	 Map<String, Object> fechaAct = (LinkedHashMap)_respuestaPIDE.get("t362_fecact");
	 Map<String, Object> fechaBaja = (LinkedHashMap)_respuestaPIDE.get("t362_fecbaj");
	 Map<String, Object> numIndice = (LinkedHashMap)_respuestaPIDE.get("t362_indice");
	 Map<String, Object> nombEmresa = (LinkedHashMap)_respuestaPIDE.get("t362_nombre");
	 Map<String, Object> numRegistro = (LinkedHashMap)_respuestaPIDE.get("t362_numreg");
	 Map<String, Object> numRuc = (LinkedHashMap)_respuestaPIDE.get("t362_numruc");
	 
	 if(descRRPP.get("$")!=null) sunat.setT362DescOfiRRPP(descRRPP.get("$").toString());
//	 sunat.setT362FechaActualiza(fechaAct.get("$").toString());
	 if(fechaBaja.get("$")!=null) sunat.setT362FechaBaja(fechaBaja.get("$").toString()); 
	 if(numIndice.get("$")!=null) sunat.setT362NumIndice(numIndice.get("$").toString());
	 if(nombEmresa.get("$")!=null) sunat.setT362NombEmpresa(nombEmresa.get("$").toString());
	 if(numRegistro.get("$")!=null) sunat.setT362NumRegistro(numRegistro.get("$").toString());
	 if(numRuc.get("$")!=null) sunat.setT362NumRuc(numRuc.get("$").toString());
	 
	 respuesta.setSunat(sunat);

	 return respuesta;
 }
 
 public static RespuestaPIDE MapearSunatDatosDomLegal(Map<String, Object> _respuestaPIDE) {
	 RespuestaPIDE respuesta=new RespuestaPIDE();
	 PideSunat sunat = new PideSunat();
	 Map<String, Object> domicilio = (LinkedHashMap)_respuestaPIDE.get("getDomicilioLegalReturn");
	 
	 if(domicilio.get("$")!=null) sunat.setDomLegal(domicilio.get("$").toString());
	 
	 respuesta.setSunat(sunat); 

	 return respuesta;
 }
 
 public static RespuestaPIDE MapearSunatDatosEstablecimientoAnexo(Map<String, Object> _respuestaPIDE) {
	 RespuestaPIDE respuesta=new RespuestaPIDE();
	 PideSunat sunat = new PideSunat();
	 Map<String, Object> dpto = (LinkedHashMap)_respuestaPIDE.get("desc_dep");
	 Map<String, Object> provincia = (LinkedHashMap)_respuestaPIDE.get("desc_prov");
	 Map<String, Object> distrito = (LinkedHashMap)_respuestaPIDE.get("desc_dist");
	 Map<String, Object> numRuc = (LinkedHashMap)_respuestaPIDE.get("spr_numruc");
	 Map<String, Object> nombVia = (LinkedHashMap)_respuestaPIDE.get("spr_nomvia");
	 Map<String, Object> nroKmMz = (LinkedHashMap)_respuestaPIDE.get("spr_numer1");
	 Map<String, Object> intDptoLt = (LinkedHashMap)_respuestaPIDE.get("spr_inter1");
	 Map<String, Object> nombZona = (LinkedHashMap)_respuestaPIDE.get("spr_nomzon");
	 Map<String, Object> referencia = (LinkedHashMap)_respuestaPIDE.get("spr_refer1");
	 Map<String, Object> nombEstablecimiento = (LinkedHashMap)_respuestaPIDE.get("spr_nombre");
	 Map<String, Object> tipoEstablecimiento = (LinkedHashMap)_respuestaPIDE.get("desc_tipest");
	 Map<String, Object> numLicencia = (LinkedHashMap)_respuestaPIDE.get("spr_licenc");
	 Map<String, Object> tipoVia = (LinkedHashMap)_respuestaPIDE.get("desc_tipvia");
	 Map<String, Object> tipoZona = (LinkedHashMap)_respuestaPIDE.get("desc_tipzon");
	 Map<String, Object> fechaActualiza = (LinkedHashMap)_respuestaPIDE.get("spr_fecact");
	 Map<String, Object> direccion = (LinkedHashMap)_respuestaPIDE.get("direccion");	 
	 
	 if(dpto.get("$")!=null) sunat.setEaDepartamento(dpto.get("$").toString());
	 if(provincia.get("$")!=null) sunat.setEaProvincia(provincia.get("$").toString());
	 if(distrito.get("$")!=null) sunat.setEaDistrito(distrito.get("$").toString());
	 if(numRuc.get("$")!=null) sunat.setEaNumRuc(numRuc.get("$").toString());
	 if(nombVia.get("$")!=null) sunat.setEaNombVia(nombVia.get("$").toString());
	 if(nroKmMz.get("$")!=null) sunat.setEaNroKmMz(nroKmMz.get("$").toString());
	 if(intDptoLt.get("$")!=null) sunat.setEaIntDptoLt(intDptoLt.get("$").toString());
	 if(nombZona.get("$")!=null) sunat.setEaNombZona(nombZona.get("$").toString());
	 if(referencia.get("$")!=null) sunat.setEaReferencia(referencia.get("$").toString());
	 if(nombEstablecimiento.get("$")!=null) sunat.setEaNombEstablecimiento(nombEstablecimiento.get("$").toString());
	 if(tipoEstablecimiento.get("$")!=null) sunat.setEaTipoEstablecimiento(tipoEstablecimiento.get("$").toString());
	 if(numLicencia.get("$")!=null) sunat.setEaNumLicencia(numLicencia.get("$").toString());
//	 sunat.setEaTipoVia(tipoVia.get("$").toString());
//	 sunat.setEaTipoZona(tipoZona.get("$").toString());
	 if(fechaActualiza.get("$")!=null) sunat.setEaFechaActualiza(fechaActualiza.get("$").toString());
	 if(direccion.get("$")!=null) sunat.setEaDireccion(direccion.get("$").toString());
	 
	 respuesta.setSunat(sunat); 

	 return respuesta;
 }
 
 public static RespuestaPIDE MapearSunatDatosEstablecimientoT1150(Map<String, Object> _respuestaPIDE) {
	 RespuestaPIDE respuesta=new RespuestaPIDE();
	 PideSunat sunat = new PideSunat();
	 Map<String, Object> correlativo = (LinkedHashMap)_respuestaPIDE.get("num_correl");
	 Map<String, Object> km = (LinkedHashMap)_respuestaPIDE.get("num_kilom");
	 Map<String, Object> mz = (LinkedHashMap)_respuestaPIDE.get("num_manza");
	 Map<String, Object> dpto = (LinkedHashMap)_respuestaPIDE.get("num_depar");
	 Map<String, Object> lt = (LinkedHashMap)_respuestaPIDE.get("num_lote");

	 if(correlativo.get("$")!=null) sunat.setT1150NumCorrelativo(correlativo.get("$").toString());
	 if(km.get("$")!=null) sunat.setT1150Km(km.get("$").toString());
	 if(mz.get("$")!=null) sunat.setT1150Mz(mz.get("$").toString());
	 if(dpto.get("$")!=null) sunat.setT1150Dpto(dpto.get("$").toString());
	 if(lt.get("$")!=null) sunat.setT1150NumLt(lt.get("$").toString());
	 
	 respuesta.setSunat(sunat); 

	 return respuesta;
 }
 
 public static RespuestaPIDE MapearSunatDatosRepreLegal(Map<String, Object> _respuestaPIDE) {
	 RespuestaPIDE respuesta=new RespuestaPIDE();
	 PideSunat sunat = new PideSunat();
	 Map<String, Object> codDpto = (LinkedHashMap)_respuestaPIDE.get("cod_depar");
	 Map<String, Object> numOrd = (LinkedHashMap)_respuestaPIDE.get("num_ord_suce");
	 Map<String, Object> cargo = (LinkedHashMap)_respuestaPIDE.get("rso_cargoo");
	 Map<String, Object> fechaDesde = (LinkedHashMap)_respuestaPIDE.get("rso_vdesde");
	 Map<String, Object> tipoDoc = (LinkedHashMap)_respuestaPIDE.get("desc_docide");
	 Map<String, Object> numDoc = (LinkedHashMap)_respuestaPIDE.get("rso_nrodoc");
	 Map<String, Object> fechaActualiza = (LinkedHashMap)_respuestaPIDE.get("rso_fecact");
	 Map<String, Object> fechaNacimiento = (LinkedHashMap)_respuestaPIDE.get("rso_fecnac");
	 Map<String, Object> nombRepre = (LinkedHashMap)_respuestaPIDE.get("rso_nombre");
	 Map<String, Object> numRuc = (LinkedHashMap)_respuestaPIDE.get("rso_numruc");	 
	 
//	 sunat.setRlCodDptoNumTelefono(codDpto.get("$").toString());
	 if(numOrd.get("$")!=null) sunat.setRlNumOrdRepreSucesiva(numOrd.get("$").toString());
	 if(cargo.get("$")!=null) sunat.setRlCargo(cargo.get("$").toString());
	 if(fechaDesde.get("$")!=null) sunat.setRlFechaDesdeCargo(fechaDesde.get("$").toString());
	 if(tipoDoc.get("$")!=null) sunat.setRlTipoDocIdent(tipoDoc.get("$").toString());
	 if(numDoc.get("$")!=null) sunat.setRlNumDocIdent(numDoc.get("$").toString());
	 if(fechaActualiza.get("$")!=null) sunat.setRlFechaActualiza(fechaActualiza.get("$").toString());
	 if(fechaNacimiento.get("$")!=null) sunat.setRlFechaNacimiento(fechaNacimiento.get("$").toString());
	 if(nombRepre.get("$")!=null) sunat.setRlNombRepresentante(nombRepre.get("$").toString());
	 if(numRuc.get("$")!=null) sunat.setRlNumRuc(numRuc.get("$").toString());	 
	 
	 respuesta.setSunat(sunat); 

	 return respuesta;
 }
 
// public static RespuestaPIDE MapearSunarpJuridica(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideSunarp sunarp = new PideSunarp();
//	 
//	 if(_respuestaPIDE.get("zona")!=null) sunarp.setZona(_respuestaPIDE.get("zona").toString());
//	 if(_respuestaPIDE.get("oficina")!=null) sunarp.setOficina(_respuestaPIDE.get("oficina").toString());
//	 if(_respuestaPIDE.get("partida")!=null) sunarp.setPartida(_respuestaPIDE.get("partida").toString());
//	 if(_respuestaPIDE.get("tomo")!=null) sunarp.setTomo(_respuestaPIDE.get("tomo").toString());
//	 if(_respuestaPIDE.get("folio")!=null) sunarp.setFolio(_respuestaPIDE.get("folio").toString());
//	 if(_respuestaPIDE.get("ficha")!=null) sunarp.setFicha(_respuestaPIDE.get("ficha").toString());
//	 if(_respuestaPIDE.get("tipo")!=null) sunarp.setTipoPersona(_respuestaPIDE.get("tipo").toString());
//	 if(_respuestaPIDE.get("denominacion")!=null) sunarp.setRazonSocial(_respuestaPIDE.get("denominacion").toString());
//	 
//	 respuesta.setSunarp(sunarp); 
//
//	 return respuesta;
// }
// 
// public static RespuestaPIDE MapearSunarpTitular(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideSunarp sunarp = new PideSunarp();
//	 
//	 if(_respuestaPIDE.get("registro")!=null) sunarp.setRegistro(_respuestaPIDE.get("registro").toString());
//	 if(_respuestaPIDE.get("libro")!=null) sunarp.setLibro(_respuestaPIDE.get("libro").toString());
//	 if(_respuestaPIDE.get("apPaterno")!=null) sunarp.setAPaterno(_respuestaPIDE.get("apPaterno").toString());
//	 if(_respuestaPIDE.get("apMaterno")!=null) sunarp.setaMaterno(_respuestaPIDE.get("apMaterno").toString());
//	 if(_respuestaPIDE.get("nombre")!=null) sunarp.setNombres(_respuestaPIDE.get("nombre").toString());
//	 if(_respuestaPIDE.get("razonSocial")!=null) sunarp.setRazonSocial(_respuestaPIDE.get("razonSocial").toString());
//	 if(_respuestaPIDE.get("tipoDocumento")!=null) sunarp.setTipoDocumento(_respuestaPIDE.get("tipoDocumento").toString());
//	 if(_respuestaPIDE.get("numeroDocumento")!=null) sunarp.setNumDocumento(_respuestaPIDE.get("numeroDocumento").toString());
//	 if(_respuestaPIDE.get("numeroPartida")!=null) sunarp.setPartida(_respuestaPIDE.get("numeroPartida").toString());
//	 if(_respuestaPIDE.get("numeroPlaca")!=null) sunarp.setMatricula(_respuestaPIDE.get("numeroPlaca").toString());
//	 if(_respuestaPIDE.get("estado")!=null) sunarp.setEstado(_respuestaPIDE.get("estado").toString());
//	 if(_respuestaPIDE.get("zona")!=null) sunarp.setZona(_respuestaPIDE.get("zona").toString());
//	 if(_respuestaPIDE.get("oficina")!=null) sunarp.setOficina(_respuestaPIDE.get("oficina").toString());
//	 if(_respuestaPIDE.get("direccion")!=null) sunarp.setDireccion(_respuestaPIDE.get("direccion").toString());
//	 
//	 respuesta.setSunarp(sunarp); 
//
//	 return respuesta;
// }
// 
// public static RespuestaPIDE MapearSunarpNave(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideSunarp sunarp = new PideSunarp();
//	 
//	 if(_respuestaPIDE.get("matricula")!=null) sunarp.setMatricula(_respuestaPIDE.get("matricula").toString());
//	 if(_respuestaPIDE.get("oficina")!=null) sunarp.setOficina(_respuestaPIDE.get("oficina").toString());
//	 if(_respuestaPIDE.get("numeroPartida")!=null) sunarp.setPartida(_respuestaPIDE.get("numeroPartida").toString());
//	 if(_respuestaPIDE.get("registro")!=null) sunarp.setRegistro(_respuestaPIDE.get("registro").toString());
//	 if(_respuestaPIDE.get("libro")!=null) sunarp.setLibro(_respuestaPIDE.get("libro").toString());
//	 if(_respuestaPIDE.get("numeroSerie")!=null) sunarp.setSerie(_respuestaPIDE.get("numeroSerie").toString());
//	 if(_respuestaPIDE.get("modelo")!=null) sunarp.setModelo(_respuestaPIDE.get("modelo").toString());
//	 
//	 if(_respuestaPIDE.get("tomoFolio")!=null) {
//		 Map<String, Object> listTomos = (HashMap)_respuestaPIDE.get("tomoFolio");
//		 List<String> lista = (ArrayList)listTomos.get("tomoFolio");
//		 sunarp.setListaTomos(lista);
//	 }
//	 	 
//	 respuesta.setSunarp(sunarp); 
//
//	 return respuesta;
// }
// 
// public static RespuestaPIDE MapearSunarpOficinas(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideSunarp sunarp = new PideSunarp();
//
//	 if(_respuestaPIDE.get("codOficina")!=null) sunarp.setOfiCodigo(_respuestaPIDE.get("codOficina").toString());
//	 if(_respuestaPIDE.get("codZona")!=null) sunarp.setOfiZona(_respuestaPIDE.get("codZona").toString());
//	 if(_respuestaPIDE.get("descripcion")!=null) sunarp.setOfiDescripcion(_respuestaPIDE.get("descripcion").toString());
//	 
//	 respuesta.setSunarp(sunarp); 
//
//	 return respuesta;
// }
// 
// public static RespuestaPIDE MapearSunarpAsientos(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideSunarp sunarp = new PideSunarp();
//	 
//	 
//	 if(_respuestaPIDE.get("transaccion")!=null) sunarp.setTransaccion(_respuestaPIDE.get("transaccion").toString());
//	 if(_respuestaPIDE.get("nroTotalPag")!=null) sunarp.setnTotalPagina(_respuestaPIDE.get("nroTotalPag").toString());
//	 //Lista de páginas 
//	 try {
//		 List<Map<String, Object>>_respuestaPIDEList = (ArrayList)_respuestaPIDE.get("listAsientos");
//		 List<Object> list = new ArrayList<>();
//		 if(_respuestaPIDEList!=null) {
//			 for (Map<String, Object> map : _respuestaPIDEList) {		 
//				 RespuestaPIDE asientos = new RespuestaPIDE();
//				 asientos = MapearSunarpListaAsientos(map);
//				 if(asientos.getSunarp()!=null)list.add(asientos);	
//			 }
//			 sunarp.setListAsiento(list);
//		 }
//	 }catch (Exception e) {
//		 
//	 }
//	 //Lista de folios
//	 try {
//		 List<Map<String, Object>>_respuestaPIDEListF = (ArrayList)_respuestaPIDE.get("listFolios");
//		 List<Object> listF = new ArrayList<>();
//		 if(_respuestaPIDEListF!=null) {
//			 for (Map<String, Object> map : _respuestaPIDEListF) {		 
//				 RespuestaPIDE folio = new RespuestaPIDE();
//				 folio = MapearSunarpListaFolio(map);
//				 if(folio.getSunarp()!=null)listF.add(folio);	
//			 }
//			 sunarp.setListFolio(listF);
//		 }
//	 }catch (Exception e) {
//		 Map<String, Object>_respuestaPIDEListF = (LinkedHashMap)_respuestaPIDE.get("listFolios");
//		 List<Object> listF = new ArrayList<>();
//		 RespuestaPIDE folio = MapearSunarpListaFolio(_respuestaPIDEListF);
//		 if(folio.getSunarp()!=null)listF.add(folio);
//		 sunarp.setListFolio(listF);
//	 }
//	 //Lista de fichas
//	 try {
//		 List<Map<String, Object>>_respuestaPIDEListFicha = (ArrayList)_respuestaPIDE.get("listFichas");
//		 List<Object> listFicha = new ArrayList<>();
//		 if(_respuestaPIDEListFicha!=null) {
//			 for (Map<String, Object> map : _respuestaPIDEListFicha) {		 
//				 RespuestaPIDE ficha = new RespuestaPIDE();
//				 ficha = MapearSunarpListaFicha(map);
//				 if(ficha.getSunarp()!=null)listFicha.add(ficha);	
//			 }
//			 sunarp.setListFicha(listFicha);
//		 }
//	 } catch (Exception e) {
//		 Map<String, Object>_respuestaPIDEListFicha = (LinkedHashMap)_respuestaPIDE.get("listFichas");
//		 List<Object> listFicha = new ArrayList<>();
//		 RespuestaPIDE ficha = MapearSunarpListaFicha(_respuestaPIDEListFicha);
//		 if(ficha.getSunarp()!=null)listFicha.add(ficha);
//		 sunarp.setListFicha(listFicha);
//	 }
//	 
//	 respuesta.setSunarp(sunarp); 
//
//	 return respuesta;
// }
// 
// public static RespuestaPIDE MapearSunarpListaAsientos(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideSunarp sunarp = new PideSunarp();
//	 
//	 if(_respuestaPIDE.get("numPag")!=null) sunarp.setPaginaAsiento(_respuestaPIDE.get("numPag").toString());
//	 if(_respuestaPIDE.get("tipo")!=null) sunarp.setTipoAsiento(_respuestaPIDE.get("tipo").toString());
//	 if(_respuestaPIDE.get("idImgAsiento")!=null) sunarp.setIdImagen(_respuestaPIDE.get("idImgAsiento").toString());
//
//	 try {
//		 List<Map<String, Object>>_respuestaPIDEList = (ArrayList)_respuestaPIDE.get("listPag");	 
//		 
//		 List<Object> list = new ArrayList<>();
//		 if(_respuestaPIDEList!=null) {
//			 for (Map<String, Object> map : _respuestaPIDEList) {		 
//				 RespuestaPIDE pagina = new RespuestaPIDE();
//				 pagina = MapearSunarpListaPagina(map);
//				 list.add(pagina);	
//			 }
//			 sunarp.setListPagina(list);
//		 }
//		 respuesta.setSunarp(sunarp); 
//	 }catch (Exception e) {
//		 Map<String, Object>_respuestaPIDEList = (LinkedHashMap)_respuestaPIDE.get("listPag");
//		 List<Object> list = new ArrayList<>();
//		 RespuestaPIDE pagina = MapearSunarpListaPagina(_respuestaPIDEList);
//		 list.add(pagina);
//		 sunarp.setListPagina(list);
//	 }
//	 
//	 
//	 return respuesta;
// }
// 
// public static RespuestaPIDE MapearSunarpListaPagina(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideSunarp sunarp = new PideSunarp();
//	 
//	 if(_respuestaPIDE.get("pagina")!=null) sunarp.setnPagina(_respuestaPIDE.get("pagina").toString());
//	 if(_respuestaPIDE.get("nroPagRef")!=null) sunarp.setnPaginaRef(_respuestaPIDE.get("nroPagRef").toString());
//	 
//	 respuesta.setSunarp(sunarp); 
//
//	 return respuesta;
// }
// 
// public static RespuestaPIDE MapearSunarpListaFolio(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideSunarp sunarp = new PideSunarp();
//	 
//	 if(_respuestaPIDE.get("idImgFolio")!=null) sunarp.setFolio(_respuestaPIDE.get("idImgFolio").toString());
//	 if(_respuestaPIDE.get("pagina")!=null) sunarp.setnPagina(_respuestaPIDE.get("pagina").toString());
//	 if(_respuestaPIDE.get("nroPagRef")!=null) sunarp.setnPaginaRef(_respuestaPIDE.get("nroPagRef").toString());
//	 if(_respuestaPIDE.get("tipo")!=null) sunarp.setTipoAsiento(_respuestaPIDE.get("tipo").toString());
//	 
//	 
//	 respuesta.setSunarp(sunarp); 
//
//	 return respuesta;
// }
// 
// public static RespuestaPIDE MapearSunarpListaFicha(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideSunarp sunarp = new PideSunarp();
//	 
//	 if(_respuestaPIDE.get("idImgFicha")!=null) sunarp.setFicha(_respuestaPIDE.get("idImgFicha").toString());
//	 if(_respuestaPIDE.get("numPag")!=null) sunarp.setnPagina(_respuestaPIDE.get("numPag").toString());
//	 if(_respuestaPIDE.get("tipo")!=null) sunarp.setTipoAsiento(_respuestaPIDE.get("tipo").toString());
//	 
//	 
//	 respuesta.setSunarp(sunarp); 
//
//	 return respuesta;
// }
// 
// public static RespuestaPIDE MapearSunarpVerAsiento(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideSunarp sunarp = new PideSunarp();
//	 
//	 if(_respuestaPIDE.get("img")!=null) sunarp.setIdImagenAsiento(_respuestaPIDE.get("img").toString());
//	 
//	 respuesta.setSunarp(sunarp); 
//
//	 return respuesta;
// }
// 
// public static RespuestaPIDE MapearSunarpRPV(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideSunarp sunarp = new PideSunarp();
//
//	 if(_respuestaPIDE.get("placa")!=null) sunarp.setMatricula(_respuestaPIDE.get("placa").toString());
//	 if(_respuestaPIDE.get("serie")!=null) sunarp.setSerie(_respuestaPIDE.get("serie").toString());
//	 if(_respuestaPIDE.get("vin")!=null) sunarp.setVin(_respuestaPIDE.get("vin").toString());
//	 if(_respuestaPIDE.get("nro_motor")!=null) sunarp.setMotor(_respuestaPIDE.get("nro_motor").toString());
//	 if(_respuestaPIDE.get("color")!=null) sunarp.setColor(_respuestaPIDE.get("color").toString());
//	 if(_respuestaPIDE.get("marca")!=null) sunarp.setMarca(_respuestaPIDE.get("marca").toString());
//	 if(_respuestaPIDE.get("modelo")!=null) sunarp.setModelo(_respuestaPIDE.get("modelo").toString());
//	 if(_respuestaPIDE.get("estado")!=null) sunarp.setEstado(_respuestaPIDE.get("estado").toString());
//	 if(_respuestaPIDE.get("sede")!=null) sunarp.setSede(_respuestaPIDE.get("sede").toString());
//	 
//	 try {
//		 if(_respuestaPIDE.get("propietarios")!=null) {
//			 Map<String, Object> listPropietario = (HashMap)_respuestaPIDE.get("propietarios");
//			 List<String> lista = (ArrayList)listPropietario.get("nombre");
//			 sunarp.setListaPropietarios(lista);
//		 }
//	 }catch(Exception e) {
//		 
//	 }
//	 respuesta.setSunarp(sunarp); 
//
//	 return respuesta;
// }
// 
// public static RespuestaPIDE MapearCarnetExtranjeria(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideCarnet carnet = new PideCarnet();
//	 
//	 if(_respuestaPIDE.get("strNombres")!=null) carnet.setNombre(_respuestaPIDE.get("strNombres").toString());
//	 if(_respuestaPIDE.get("strPrimerApellido")!=null) carnet.setAPaterno(_respuestaPIDE.get("strPrimerApellido").toString());
//	 if(_respuestaPIDE.get("strSegundoApellido")!=null) carnet.setaMaterno(_respuestaPIDE.get("strSegundoApellido").toString());
//	 if(_respuestaPIDE.get("strCalidadMigratoria")!=null) carnet.setcMigratoria(_respuestaPIDE.get("strCalidadMigratoria").toString());
//	 if(_respuestaPIDE.get("strNumRespuesta")!=null) carnet.setnRespuesta(_respuestaPIDE.get("strNumRespuesta").toString());
//	 
//	 respuesta.setCarnet(carnet); 
//
//	 return respuesta;
// }
// 
// public static RespuestaPIDE MapearAntecedentesPoliciales(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideAntecedentes antecedentes = new PideAntecedentes();
//		 
//	 if(_respuestaPIDE.get("nombres")!=null) antecedentes.setNombres(_respuestaPIDE.get("nombres").toString());
//	 if(_respuestaPIDE.get("apellidoPaterno")!=null) antecedentes.setAPaterno(_respuestaPIDE.get("apellidoPaterno").toString());
//	 if(_respuestaPIDE.get("apellidoMaterno")!=null) antecedentes.setaMaterno(_respuestaPIDE.get("apellidoMaterno").toString());
//	 if(_respuestaPIDE.get("nombrecompleto")!=null) antecedentes.setNombreCompleto(_respuestaPIDE.get("nombrecompleto").toString());
//	 if(_respuestaPIDE.get("codigoPersona")!=null) antecedentes.setcPersona(_respuestaPIDE.get("codigoPersona").toString());
//	 if(_respuestaPIDE.get("lugarNacimiento")!=null) antecedentes.setLugarNacimiento(_respuestaPIDE.get("lugarNacimiento").toString());
//	 if(_respuestaPIDE.get("fechaNacimiento")!=null) antecedentes.setFecNacimiento(_respuestaPIDE.get("fechaNacimiento").toString());
//	 if(_respuestaPIDE.get("tipoDocumento")!=null) antecedentes.setTipoDoc(_respuestaPIDE.get("tipoDocumento").toString());
//	 if(_respuestaPIDE.get("nroDocumento")!=null) antecedentes.setNumDoc(_respuestaPIDE.get("nroDocumento").toString());
//	 if(_respuestaPIDE.get("sexo")!=null) antecedentes.setSexo(_respuestaPIDE.get("sexo").toString());
//	 if(_respuestaPIDE.get("talla")!=null) antecedentes.setTalla(_respuestaPIDE.get("talla").toString());
//	 if(_respuestaPIDE.get("contextura")!=null) antecedentes.setContextura(_respuestaPIDE.get("contextura").toString());
//	 if(_respuestaPIDE.get("nombrePadre")!=null) antecedentes.setNombPadre(_respuestaPIDE.get("nombrePadre").toString());
//	 if(_respuestaPIDE.get("nombreMadre")!=null) antecedentes.setNombMadre(_respuestaPIDE.get("nombreMadre").toString());
//	 if(_respuestaPIDE.get("descripcionMensaje")!=null) antecedentes.setRespuesta(_respuestaPIDE.get("descripcionMensaje").toString());
//	
//	 respuesta.setAntecedentes(antecedentes); 
//
//	 return respuesta;
// }
// 
// public static RespuestaPIDE MapearTipoCambio(Map<String, Object> _respuestaPIDE) {
//	 RespuestaPIDE respuesta=new RespuestaPIDE();
//	 PideTipoCambio tipoCambio = new PideTipoCambio();
//	
// 	 if(_respuestaPIDE.get("tasa")!=null) tipoCambio.setTasa(_respuestaPIDE.get("tasa").toString());
//	 if(_respuestaPIDE.get("compra")!=null) tipoCambio.setCompra(_respuestaPIDE.get("compra").toString());
//	 if(_respuestaPIDE.get("venta")!=null) tipoCambio.setVenta(_respuestaPIDE.get("venta").toString());
//	
//	 respuesta.setCambio(tipoCambio); 
//
//	 return respuesta;
// }
  
}
