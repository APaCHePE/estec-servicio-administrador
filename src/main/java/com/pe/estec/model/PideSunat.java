package com.pe.estec.model;

public class PideSunat {
	
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
		
		public String getCorreoUsuario() {
			return correoUsuario;
		}
		public void setCorreoUsuario(String correoUsuario) {
			this.correoUsuario = correoUsuario;
		}
		public String getaPaterno() {
			return aPaterno;
		}
		public String getCalConducta() {
			return calConducta;
		}
		public void setCalConducta(String calConducta) {
			this.calConducta = calConducta;
		}
		public String getActComercioExt() {
			return actComercioExt;
		}
		public void setActComercioExt(String actComercioExt) {
			this.actComercioExt = actComercioExt;
		}
		public String getFechaConstitucion() {
			return fechaConstitucion;
		}
		public void setFechaConstitucion(String fechaConstitucion) {
			this.fechaConstitucion = fechaConstitucion;
		}
		public String getTipoContabilidad() {
			return tipoContabilidad;
		}
		public void setTipoContabilidad(String tipoContabilidad) {
			this.tipoContabilidad = tipoContabilidad;
		}
		public String getTipoDocIdentidad() {
			return tipoDocIdentidad;
		}
		public void setTipoDocIdentidad(String tipoDocIdentidad) {
			this.tipoDocIdentidad = tipoDocIdentidad;
		}
		public String getNumDocIdentidad() {
			return numDocIdentidad;
		}
		public void setNumDocIdentidad(String numDocIdentidad) {
			this.numDocIdentidad = numDocIdentidad;
		}		
		public String getSecuencia() {
			return secuencia;
		}
		public void setSecuencia(String secuencia) {
			this.secuencia = secuencia;
		}		
		public String getZona() {
			return zona;
		}
		public void setZona(String zona) {
			this.zona = zona;
		}
		public String getActivo() {
			return activo;
		}
		public void setActivo(String activo) {
			this.activo = activo;
		}
		public String getHabido() {
			return habido;
		}
		public void setHabido(String habido) {
			this.habido = habido;
		}
		public String getNumRuc() {
			return numRuc;
		}
		public void setNumRuc(String numRuc) {
			this.numRuc = numRuc;
		}
		public String getCondDomiciliado() {
			return condDomiciliado;
		}
		public void setCondDomiciliado(String condDomiciliado) {
			this.condDomiciliado = condDomiciliado;
		}
		public String getTipoFactura() {
			return tipoFactura;
		}
		public void setTipoFactura(String tipoFactura) {
			this.tipoFactura = tipoFactura;
		}
		public String getFechaNacimiento() {
			return fechaNacimiento;
		}
		public void setFechaNacimiento(String fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
		}
		public String getNumAsientoRRPP() {
			return numAsientoRRPP;
		}
		public void setNumAsientoRRPP(String numAsientoRRPP) {
			this.numAsientoRRPP = numAsientoRRPP;
		}
		public String getTomoRRPP() {
			return tomoRRPP;
		}
		public void setTomoRRPP(String tomoRRPP) {
			this.tomoRRPP = tomoRRPP;
		}
		public String getNumFolioRRPP() {
			return numFolioRRPP;
		}
		public void setNumFolioRRPP(String numFolioRRPP) {
			this.numFolioRRPP = numFolioRRPP;
		}
		public String getFechaIniActividades() {
			return fechaIniActividades;
		}
		public void setFechaIniActividades(String fechaIniActividades) {
			this.fechaIniActividades = fechaIniActividades;
		}
		public String getNumLicMunicipal() {
			return numLicMunicipal;
		}
		public void setNumLicMunicipal(String numLicMunicipal) {
			this.numLicMunicipal = numLicMunicipal;
		}
		public String getNacionalidad() {
			return nacionalidad;
		}
		public void setNacionalidad(String nacionalidad) {
			this.nacionalidad = nacionalidad;
		}
		public String getNombComercial() {
			return nombComercial;
		}
		public void setNombComercial(String nombComercial) {
			this.nombComercial = nombComercial;
		}
		public String getNroRuc() {
			return nroRuc;
		}
		public void setNroRuc(String nroRuc) {
			this.nroRuc = nroRuc;
		}
		public String getOrgEntidad() {
			return orgEntidad;
		}
		public void setOrgEntidad(String orgEntidad) {
			this.orgEntidad = orgEntidad;
		}
		public String getPaisPasaporte() {
			return paisPasaporte;
		}
		public void setPaisPasaporte(String paisPasaporte) {
			this.paisPasaporte = paisPasaporte;
		}
		public String getNumPasaporte() {
			return numPasaporte;
		}
		public void setNumPasaporte(String numPasaporte) {
			this.numPasaporte = numPasaporte;
		}
		public String getCarnetPatron() {
			return carnetPatron;
		}
		public void setCarnetPatron(String carnetPatron) {
			this.carnetPatron = carnetPatron;
		}
		public String getSexo() {
			return sexo;
		}
		public void setSexo(String sexo) {
			this.sexo = sexo;
		}
		public String getNumTelefono() {
			return numTelefono;
		}
		public void setNumTelefono(String numTelefono) {
			this.numTelefono = numTelefono;
		}		
		public String getNumTelefono2() {
			return numTelefono2;
		}
		public void setNumTelefono2(String numTelefono2) {
			this.numTelefono2 = numTelefono2;
		}
		public String getNumTelefono3() {
			return numTelefono3;
		}
		public void setNumTelefono3(String numTelefono3) {
			this.numTelefono3 = numTelefono3;
		}
		public String getNumFax() {
			return numFax;
		}
		public void setNumFax(String numFax) {
			this.numFax = numFax;
		}
		public String getT1144ActEconomicaSec() {
			return t1144ActEconomicaSec;
		}
		public void setT1144ActEconomicaSec(String t1144ActEconomicaSec) {
			this.t1144ActEconomicaSec = t1144ActEconomicaSec;
		}
		public String getT1144ActEconomicaSecu() {
			return t1144ActEconomicaSecu;
		}
		public void setT1144ActEconomicaSecu(String t1144ActEconomicaSecu) {
			this.t1144ActEconomicaSecu = t1144ActEconomicaSecu;
		}
		public String getT1144Correo1() {
			return t1144Correo1;
		}
		public void setT1144Correo1(String t1144Correo1) {
			this.t1144Correo1 = t1144Correo1;
		}
		public String getT1144Correo2() {
			return t1144Correo2;
		}
		public void setT1144Correo2(String t1144Correo2) {
			this.t1144Correo2 = t1144Correo2;
		}
		public String getT1144Telef1() {
			return t1144Telef1;
		}
		public void setT1144Telef1(String t1144Telef1) {
			this.t1144Telef1 = t1144Telef1;
		}
		public String getT1144Departamento1() {
			return t1144Departamento1;
		}
		public void setT1144Departamento1(String t1144Departamento1) {
			this.t1144Departamento1 = t1144Departamento1;
		}
		public String getT1144Telef2() {
			return t1144Telef2;
		}
		public void setT1144Telef2(String t1144Telef2) {
			this.t1144Telef2 = t1144Telef2;
		}
		public String getT1144Departamento2() {
			return t1144Departamento2;
		}
		public void setT1144Departamento2(String t1144Departamento2) {
			this.t1144Departamento2 = t1144Departamento2;
		}
		public String getT1144Telef3() {
			return t1144Telef3;
		}
		public void setT1144Telef3(String t1144Telef3) {
			this.t1144Telef3 = t1144Telef3;
		}
		public String getT1144Departamento3() {
			return t1144Departamento3;
		}
		public void setT1144Departamento3(String t1144Departamento3) {
			this.t1144Departamento3 = t1144Departamento3;
		}
		public String getT1144Telef4() {
			return t1144Telef4;
		}
		public void setT1144Telef4(String t1144Telef4) {
			this.t1144Telef4 = t1144Telef4;
		}
		public String getT1144Departamento4() {
			return t1144Departamento4;
		}
		public void setT1144Departamento4(String t1144Departamento4) {
			this.t1144Departamento4 = t1144Departamento4;
		}
		public String getT1144Telef5() {
			return t1144Telef5;
		}
		public void setT1144Telef5(String t1144Telef5) {
			this.t1144Telef5 = t1144Telef5;
		}
		public String getT1144Departamento5() {
			return t1144Departamento5;
		}
		public void setT1144Departamento5(String t1144Departamento5) {
			this.t1144Departamento5 = t1144Departamento5;
		}
		public String getT1144AsientoRRPP() {
			return t1144AsientoRRPP;
		}
		public void setT1144AsientoRRPP(String t1144AsientoRRPP) {
			this.t1144AsientoRRPP = t1144AsientoRRPP;
		}
		public String getT1144pRegistral() {
			return t1144pRegistral;
		}
		public void setT1144pRegistral(String t1144pRegistral) {
			this.t1144pRegistral = t1144pRegistral;
		}
		public String getT1144RefNotificacion() {
			return t1144RefNotificacion;
		}
		public void setT1144RefNotificacion(String t1144RefNotificacion) {
			this.t1144RefNotificacion = t1144RefNotificacion;
		}
		public String getT1144CondLegalDomi() {
			return t1144CondLegalDomi;
		}
		public void setT1144CondLegalDomi(String t1144CondLegalDomi) {
			this.t1144CondLegalDomi = t1144CondLegalDomi;
		}
		public String getT1144IndCorreo() {
			return t1144IndCorreo;
		}		
		public String getT1144NumFax() {
			return t1144NumFax;
		}
		public void setT1144NumFax(String t1144NumFax) {
			this.t1144NumFax = t1144NumFax;
		}
		public void setT1144IndCorreo(String t1144IndCorreo) {
			this.t1144IndCorreo = t1144IndCorreo;
		}
		public String getT1144FechaConfirmaCorreo() {
			return t1144FechaConfirmaCorreo;
		}
		public void setT1144FechaConfirmaCorreo(String t1144FechaConfirmaCorreo) {
			this.t1144FechaConfirmaCorreo = t1144FechaConfirmaCorreo;
		}		
		public String getT1144IndCorreo2() {
			return t1144IndCorreo2;
		}
		public void setT1144IndCorreo2(String t1144IndCorreo2) {
			this.t1144IndCorreo2 = t1144IndCorreo2;
		}
		public String getT1144FechaConfirmaCorreo2() {
			return t1144FechaConfirmaCorreo2;
		}
		public void setT1144FechaConfirmaCorreo2(String t1144FechaConfirmaCorreo2) {
			this.t1144FechaConfirmaCorreo2 = t1144FechaConfirmaCorreo2;
		}
		public String getT1144TipoRepresentacion() {
			return t1144TipoRepresentacion;
		}
		public void setT1144TipoRepresentacion(String t1144TipoRepresentacion) {
			this.t1144TipoRepresentacion = t1144TipoRepresentacion;
		}
		public String getT1144Km() {
			return t1144Km;
		}
		public void setT1144Km(String t1144Km) {
			this.t1144Km = t1144Km;
		}
		public String getT1144Mz() {
			return t1144Mz;
		}
		public void setT1144Mz(String t1144Mz) {
			this.t1144Mz = t1144Mz;
		}
		public String getT1144Dpto() {
			return t1144Dpto;
		}
		public void setT1144Dpto(String t1144Dpto) {
			this.t1144Dpto = t1144Dpto;
		}
		public String getT1144NumLote() {
			return t1144NumLote;
		}
		public void setT1144NumLote(String t1144NumLote) {
			this.t1144NumLote = t1144NumLote;
		}
		public String getT1144NumRuc() {
			return t1144NumRuc;
		}
		public void setT1144NumRuc(String t1144NumRuc) {
			this.t1144NumRuc = t1144NumRuc;
		}
		public String getT362DescOfiRRPP() {
			return t362DescOfiRRPP;
		}
		public void setT362DescOfiRRPP(String t362DescOfiRRPP) {
			this.t362DescOfiRRPP = t362DescOfiRRPP;
		}
		public String getT362FechaActualiza() {
			return t362FechaActualiza;
		}
		public void setT362FechaActualiza(String t362FechaActualiza) {
			this.t362FechaActualiza = t362FechaActualiza;
		}
		public String getT362FechaBaja() {
			return t362FechaBaja;
		}
		public void setT362FechaBaja(String t362FechaBaja) {
			this.t362FechaBaja = t362FechaBaja;
		}
		public String getT362NumIndice() {
			return t362NumIndice;
		}
		public void setT362NumIndice(String t362NumIndice) {
			this.t362NumIndice = t362NumIndice;
		}
		public String getT362NombEmpresa() {
			return t362NombEmpresa;
		}
		public void setT362NombEmpresa(String t362NombEmpresa) {
			this.t362NombEmpresa = t362NombEmpresa;
		}
		public String getT362NumRegistro() {
			return t362NumRegistro;
		}
		public void setT362NumRegistro(String t362NumRegistro) {
			this.t362NumRegistro = t362NumRegistro;
		}
		public String getT362NumRuc() {
			return t362NumRuc;
		}
		public void setT362NumRuc(String t362NumRuc) {
			this.t362NumRuc = t362NumRuc;
		}
		public String getDomLegal() {
			return domLegal;
		}
		public void setDomLegal(String domLegal) {
			this.domLegal = domLegal;
		}
		public void setaPaterno(String aPaterno) {
			this.aPaterno = aPaterno;
		}
		public String getaMaterno() {
			return aMaterno;
		}
		public void setaMaterno(String aMaterno) {
			this.aMaterno = aMaterno;
		}
		public String getNombresContrib() {
			return nombresContrib;
		}
		public void setNombresContrib(String nombresContrib) {
			this.nombresContrib = nombresContrib;
		}
		public String getDireccion() {
			return direccion;
		}
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
		public String getDepartamento() {
			return departamento;
		}
		public void setDepartamento(String departamento) {
			this.departamento = departamento;
		}
		public String getProvincia() {
			return provincia;
		}
		public void setProvincia(String provincia) {
			this.provincia = provincia;
		}
		public String getDistrito() {
			return distrito;
		}
		public void setDistrito(String distrito) {
			this.distrito = distrito;
		}
		public String getActEconomica() {
			return actEconomica;
		}
		public void setActEconomica(String actEconomica) {
			this.actEconomica = actEconomica;
		}
		public String getEstadoContrib() {
			return estadoContrib;
		}
		public void setEstadoContrib(String estadoContrib) {
			this.estadoContrib = estadoContrib;
		}
		public String getFechaActualiza() {
			return fechaActualiza;
		}
		public void setFechaActualiza(String fechaActualiza) {
			this.fechaActualiza = fechaActualiza;
		}
		public String getFechaAlta() {
			return fechaAlta;
		}
		public void setFechaAlta(String fechaAlta) {
			this.fechaAlta = fechaAlta;
		}
		public String getFechaBaja() {
			return fechaBaja;
		}
		public void setFechaBaja(String fechaBaja) {
			this.fechaBaja = fechaBaja;
		}
		public String getTipoPersona() {
			return tipoPersona;
		}
		public void setTipoPersona(String tipoPersona) {
			this.tipoPersona = tipoPersona;
		}
		public String getLibretaTributaria() {
			return libretaTributaria;
		}
		public void setLibretaTributaria(String libretaTributaria) {
			this.libretaTributaria = libretaTributaria;
		}
		public String getNombVia() {
			return nombVia;
		}
		public void setNombVia(String nombVia) {
			this.nombVia = nombVia;
		}
		public String getNumVia() {
			return numVia;
		}
		public void setNumVia(String numVia) {
			this.numVia = numVia;
		}
		public String getInterior() {
			return interior;
		}
		public void setInterior(String interior) {
			this.interior = interior;
		}
		public String getNombZona() {
			return nombZona;
		}
		public void setNombZona(String nombZona) {
			this.nombZona = nombZona;
		}
		public String getReferencia() {
			return referencia;
		}
		public void setReferencia(String referencia) {
			this.referencia = referencia;
		}
		public String getCondDomicilio() {
			return condDomicilio;
		}
		public void setCondDomicilio(String condDomicilio) {
			this.condDomicilio = condDomicilio;
		}
		public String getDependencia() {
			return dependencia;
		}
		public void setDependencia(String dependencia) {
			this.dependencia = dependencia;
		}
		public String getTipoVia() {
			return tipoVia;
		}
		public void setTipoVia(String tipoVia) {
			this.tipoVia = tipoVia;
		}
		public String getTipoZona() {
			return tipoZona;
		}
		public void setTipoZona(String tipoZona) {
			this.tipoZona = tipoZona;
		}
		public String getTipoContrib() {
			return tipoContrib;
		}
		public void setTipoContrib(String tipoContrib) {
			this.tipoContrib = tipoContrib;
		}
		public String getEaDepartamento() {
			return eaDepartamento;
		}
		public void setEaDepartamento(String eaDepartamento) {
			this.eaDepartamento = eaDepartamento;
		}
		public String getEaProvincia() {
			return eaProvincia;
		}
		public void setEaProvincia(String eaProvincia) {
			this.eaProvincia = eaProvincia;
		}
		public String getEaDistrito() {
			return eaDistrito;
		}
		public void setEaDistrito(String eaDistrito) {
			this.eaDistrito = eaDistrito;
		}
		public String getEaNumRuc() {
			return eaNumRuc;
		}
		public void setEaNumRuc(String eaNumRuc) {
			this.eaNumRuc = eaNumRuc;
		}
		public String getEaNombVia() {
			return eaNombVia;
		}
		public void setEaNombVia(String eaNombVia) {
			this.eaNombVia = eaNombVia;
		}
		public String getEaNroKmMz() {
			return eaNroKmMz;
		}
		public void setEaNroKmMz(String eaNroKmMz) {
			this.eaNroKmMz = eaNroKmMz;
		}
		public String getEaIntDptoLt() {
			return eaIntDptoLt;
		}
		public void setEaIntDptoLt(String eaIntDptoLt) {
			this.eaIntDptoLt = eaIntDptoLt;
		}
		public String getEaNombZona() {
			return eaNombZona;
		}
		public void setEaNombZona(String eaNombZona) {
			this.eaNombZona = eaNombZona;
		}
		public String getEaReferencia() {
			return eaReferencia;
		}
		public void setEaReferencia(String eaReferencia) {
			this.eaReferencia = eaReferencia;
		}
		public String getEaNombEstablecimiento() {
			return eaNombEstablecimiento;
		}
		public void setEaNombEstablecimiento(String eaNombEstablecimiento) {
			this.eaNombEstablecimiento = eaNombEstablecimiento;
		}
		public String getEaTipoEstablecimiento() {
			return eaTipoEstablecimiento;
		}
		public void setEaTipoEstablecimiento(String eaTipoEstablecimiento) {
			this.eaTipoEstablecimiento = eaTipoEstablecimiento;
		}
		public String getEaNumLicencia() {
			return eaNumLicencia;
		}
		public void setEaNumLicencia(String eaNumLicencia) {
			this.eaNumLicencia = eaNumLicencia;
		}
		public String getEaTipoVia() {
			return eaTipoVia;
		}
		public void setEaTipoVia(String eaTipoVia) {
			this.eaTipoVia = eaTipoVia;
		}
		public String getEaTipoZona() {
			return eaTipoZona;
		}
		public void setEaTipoZona(String eaTipoZona) {
			this.eaTipoZona = eaTipoZona;
		}
		public String getEaFechaActualiza() {
			return eaFechaActualiza;
		}
		public void setEaFechaActualiza(String eaFechaActualiza) {
			this.eaFechaActualiza = eaFechaActualiza;
		}
		public String getEaDireccion() {
			return eaDireccion;
		}
		public void setEaDireccion(String eaDireccion) {
			this.eaDireccion = eaDireccion;
		}
		public String getT1150NumCorrelativo() {
			return t1150NumCorrelativo;
		}
		public void setT1150NumCorrelativo(String t1150NumCorrelativo) {
			this.t1150NumCorrelativo = t1150NumCorrelativo;
		}
		public String getT1150Km() {
			return t1150Km;
		}
		public void setT1150Km(String t1150Km) {
			this.t1150Km = t1150Km;
		}
		public String getT1150Mz() {
			return t1150Mz;
		}
		public void setT1150Mz(String t1150Mz) {
			this.t1150Mz = t1150Mz;
		}
		public String getT1150Dpto() {
			return t1150Dpto;
		}
		public void setT1150Dpto(String t1150Dpto) {
			this.t1150Dpto = t1150Dpto;
		}
		public String getT1150NumLt() {
			return t1150NumLt;
		}
		public void setT1150NumLt(String t1150NumLt) {
			this.t1150NumLt = t1150NumLt;
		}
		public String getRlCodDptoNumTelefono() {
			return rlCodDptoNumTelefono;
		}
		public void setRlCodDptoNumTelefono(String rlCodDptoNumTelefono) {
			this.rlCodDptoNumTelefono = rlCodDptoNumTelefono;
		}
		public String getRlNumOrdRepreSucesiva() {
			return rlNumOrdRepreSucesiva;
		}
		public void setRlNumOrdRepreSucesiva(String rlNumOrdRepreSucesiva) {
			this.rlNumOrdRepreSucesiva = rlNumOrdRepreSucesiva;
		}
		public String getRlcodCargo() {
			return rlcodCargo;
		}
		public void setRlcodCargo(String rlcodCargo) {
			this.rlcodCargo = rlcodCargo;
		}
		public String getRlCargo() {
			return rlCargo;
		}
		public void setRlCargo(String rlCargo) {
			this.rlCargo = rlCargo;
		}
		public String getRlFechaDesdeCargo() {
			return rlFechaDesdeCargo;
		}
		public void setRlFechaDesdeCargo(String rlFechaDesdeCargo) {
			this.rlFechaDesdeCargo = rlFechaDesdeCargo;
		}
		public String getRlTipoDocIdent() {
			return rlTipoDocIdent;
		}
		public void setRlTipoDocIdent(String rlTipoDocIdent) {
			this.rlTipoDocIdent = rlTipoDocIdent;
		}
		public String getRlNumDocIdent() {
			return rlNumDocIdent;
		}
		public void setRlNumDocIdent(String rlNumDocIdent) {
			this.rlNumDocIdent = rlNumDocIdent;
		}
		public String getRlFechaActualiza() {
			return rlFechaActualiza;
		}
		public void setRlFechaActualiza(String rlFechaActualiza) {
			this.rlFechaActualiza = rlFechaActualiza;
		}
		public String getRlFechaNacimiento() {
			return rlFechaNacimiento;
		}
		public void setRlFechaNacimiento(String rlFechaNacimiento) {
			this.rlFechaNacimiento = rlFechaNacimiento;
		}
		public String getRlNombRepresentante() {
			return rlNombRepresentante;
		}
		public void setRlNombRepresentante(String rlNombRepresentante) {
			this.rlNombRepresentante = rlNombRepresentante;
		}
		public String getRlNumRuc() {
			return rlNumRuc;
		}
		public void setRlNumRuc(String rlNumRuc) {
			this.rlNumRuc = rlNumRuc;
		}
		

}
