package com.pe.estec.model;

import lombok.Data;

//import gob.pe.miraflores.comunes.model.bean.PersonaPIDE;
//import gob.pe.miraflores.comunes.model.bean.PideAntecedentes;
//import gob.pe.miraflores.comunes.model.bean.PideCarnet;
//import gob.pe.miraflores.comunes.model.bean.PideSunarp;
//import gob.pe.miraflores.comunes.model.bean.PideSunat;
//import gob.pe.miraflores.comunes.model.bean.PideTipoCambio;

@Data
public class RespuestaPIDE {
	private String coResultado;
	private String deResultado;
	private PersonaPIDE persona;
	private PideSunat sunat;
	private PideSunarp sunarp;
	private PideCarnet carnet;
	private PideAntecedentes antecedentes;	
//	private PideTipoCambio cambio;
}
