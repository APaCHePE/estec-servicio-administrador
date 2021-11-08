package com.pe.estec.model;

import lombok.Data;

@Data
public class PideSunarp {
	
	//Datos Principales
		private String aPaterno;
		private String aMaterno;
		private String nombresContrib;
		private String direccion;
		private String departamento;
		private String provincia;
		private String distrito;
		private String actEconomica;
		private String estadoContrib;
		private String fechaActualiza;
		private String fechaAlta;
		private String fechaBaja;
		private String tipoPersona;
		private String libretaTributaria;
		private String nombVia;
		private String numVia;
		private String interior;
		private String nombZona;
		private String referencia;
		private String condDomicilio;
		private String dependencia;
		private String tipoVia;
		private String tipoZona;
		private String zona;
		private String tipoContrib;
		private String secuencia;
		private String activo;
		private String habido;
		private String numRuc;
		
		//Datos Secundarios
		private String calConducta;
		private String actComercioExt;
		private String fechaConstitucion;
		private String tipoContabilidad;
		private String tipoDocIdentidad;
		private String numDocIdentidad;
		private String condDomiciliado;
		private String tipoFactura;
		private String fechaNacimiento;
		private String numAsientoRRPP;
		private String tomoRRPP;
		private String numFolioRRPP;
		private String fechaIniActividades;
		private String numLicMunicipal;
		private String nacionalidad;
		private String nombComercial;
		private String nroRuc;
		private String orgEntidad;
		private String paisPasaporte;
		private String numPasaporte;
		private String carnetPatron;
		private String sexo;
		private String numTelefono;
		private String numTelefono2;
		private String numTelefono3;
		private String numFax;
		
		//T1144
		private String t1144ActEconomicaSec;
		private String t1144ActEconomicaSecu;
		private String t1144Correo1;
		private String t1144Correo2;
		private String t1144Telef1;
		private String t1144Departamento1;
		private String t1144Telef2;
		private String t1144Departamento2;
		private String t1144Telef3;
		private String t1144Departamento3;
		private String t1144Telef4;
		private String t1144Departamento4;
		private String t1144Telef5;
		private String t1144Departamento5;
		private String t1144NumFax;
		private String t1144AsientoRRPP;
		private String t1144pRegistral;
		private String t1144RefNotificacion;
		private String t1144CondLegalDomi;
		private String t1144IndCorreo;
		private String t1144FechaConfirmaCorreo;
		private String t1144IndCorreo2;
		private String t1144FechaConfirmaCorreo2;
		private String t1144TipoRepresentacion;
		private String t1144Km;
		private String t1144Mz;
		private String t1144Dpto;
		private String t1144NumLote;
		private String t1144NumRuc;
		
		//T362
		private String t362DescOfiRRPP;
		private String t362FechaActualiza;
		private String t362FechaBaja;
		private String t362NumIndice;
		private String t362NombEmpresa;
		private String t362NumRegistro;
		private String t362NumRuc;
		
		//Domicilio Legal
		private String domLegal;
		
		//Establecimientos Anexos
		private String eaDepartamento;
		private String eaProvincia;
		private String eaDistrito;
		private String eaNumRuc;
		private String eaNombVia;
		private String eaNroKmMz;
		private String eaIntDptoLt;
		private String eaNombZona;
		private String eaReferencia;
		private String eaNombEstablecimiento;
		private String eaTipoEstablecimiento;
		private String eaNumLicencia;
		private String eaTipoVia;
		private String eaTipoZona;
		private String eaFechaActualiza;
		private String eaDireccion;
		
		//Establecimientos Anexos T1150
		private String t1150NumCorrelativo;
		private String t1150Km;
		private String t1150Mz;
		private String t1150Dpto;
		private String t1150NumLt;
		
		//Representantes Legales
		private String rlCodDptoNumTelefono;
		private String rlNumOrdRepreSucesiva;
		private String rlcodCargo;
		private String rlCargo;
		private String rlFechaDesdeCargo;
		private String rlTipoDocIdent;
		private String rlNumDocIdent;
		private String rlFechaActualiza;
		private String rlFechaNacimiento;
		private String rlNombRepresentante;
		private String rlNumRuc;		
		
		private String correoUsuario;
		
}
