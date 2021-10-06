package com.pe.estec.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	private final static String extPlantillaMM = ".html";
	
	
	/**
	 * Obtenemos el digito de chequeo para generar el recibo de pago
	 * 
	 * @param codPreRecibo
	 * @return Digito de chequeo
	 */
	private static String digitoChequeo(String codPreRecibo) {
		char[] multif;
		char[] resSumax;
		Integer sumaUFila = 0;
		Integer sumaUFilaxnueve = 0;
		String digitoChequeo = "";
		
		char[] numero = codPreRecibo.toCharArray();
		Integer[] factor = {1,	2,	1,	2,	1,	2,	1,	2,	1,	2,	1, 2};
		Integer[] multixfactor = new Integer[12];
		Integer[] uFila = new Integer[12];
		
		for(int i=0;i<numero.length;i++){
			multixfactor[i] = Integer.parseInt(""+numero[i])*factor[i];
		 
			if(multixfactor[i]>9){
				Integer sumamf = 0;
				multif = (""+multixfactor[i]).toCharArray();
				 
				for(int j=0;j<multif.length;j++){
					sumamf = sumamf + Integer.parseInt(""+multif[j]);
				}
				 
				uFila[i] = sumamf;
			 }else{
				 uFila[i] = multixfactor[i];
			 }
		 }
		 
		 for(int k=0;k<uFila.length;k++){			 
			 sumaUFila = sumaUFila + uFila[k];
		 }
		 
		 sumaUFilaxnueve = sumaUFila*9;
		 
		 resSumax = (""+sumaUFilaxnueve).toCharArray();
		 
		 digitoChequeo = "" + resSumax[resSumax.length-1];
		
		return digitoChequeo;
	}
	
	/**
	 * Método que permite obtener el html de una plantilla.
	 * 
	 * @param plantilla Nombre de la plantilla
	 * @return Html de la plantilla elegida
	 * @throws Exception
	 */
	public static String obtenerPlantillaCorreoMiraflores(String urlPlantillaMM,String plantilla) throws Exception {
		StringBuilder html = new StringBuilder();
		StringBuilder urlPlantilla = new StringBuilder();
		
		  try{
			  urlPlantilla.append(urlPlantillaMM);
			  urlPlantilla.append(plantilla);
			  urlPlantilla.append(extPlantillaMM);

			  URL url = new URL(urlPlantilla.toString());
			  URLConnection connection = url.openConnection();
			  connection.connect();
			  
			  BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			  String inputLine;
			  
			  while ((inputLine = in.readLine()) != null) {
				  html.append(inputLine);
			  }
			  
			  in.close();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }

		  return html.toString();
	}
	
	public static String obtenerPlantillaCorreoMiraflores(String urlServidor ,String carpeta, String plantilla) throws Exception {
		StringBuilder html = new StringBuilder();
		StringBuilder urlPlantilla = new StringBuilder();
		System.out.println("ingresando a librerias comunes");
		  urlPlantilla.append(urlServidor);
		  urlPlantilla.append(carpeta);
		  urlPlantilla.append(plantilla);
		  urlPlantilla.append(extPlantillaMM);
		  System.out.println("plantilla armada librerias comunes "+urlPlantilla);
		  URL url = new URL(urlPlantilla.toString());
		  URLConnection connection = url.openConnection();
		  connection.connect();
		  
		  BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		  String inputLine;
		  
		  while ((inputLine = in.readLine()) != null) {
			  html.append(inputLine);
		  }
		  
		  in.close();
	  
		  return html.toString();
	}
	public static String obtenerPlantillaCorreoMiraflores_TEST(String urlServidor ,String carpeta, String plantilla) throws Exception {
		StringBuilder html = new StringBuilder();
		StringBuilder urlPlantilla = new StringBuilder();
		
		  try{
			  urlPlantilla.append(urlServidor);
			  urlPlantilla.append(carpeta);
			  urlPlantilla.append(plantilla);
			  urlPlantilla.append(extPlantillaMM);

			  URL url = new URL(urlPlantilla.toString());
			  URLConnection connection = url.openConnection();
			  connection.connect();
			  
			  BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			  String inputLine;
			  
			  while ((inputLine = in.readLine()) != null) {
				  html.append(inputLine);
			  }
			  
			  in.close();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }

		  return html.toString();
	}
	
	/**
	 * Método que permite reemplazar elementos de una plantilla.
	 * 
	 * @param cadena Html de la plantilla
	 * @param seccion Cadena a reemplazar
	 * @param valorRemplazo Cadena a reemplazar
	 * @return
	 */
	public static String reemplazaSeccionPlantilla(String cadena,String seccion,String valorRemplazo) {
		if(valorRemplazo!=null) {
			String replacement = Matcher.quoteReplacement(valorRemplazo);
			String searchString = Pattern.quote(seccion);
			cadena = cadena.replaceAll(searchString, replacement);
		}else {
			String replacement = Matcher.quoteReplacement("");
			String searchString = Pattern.quote(seccion);
			cadena = cadena.replaceAll(searchString, replacement);
		}
		
		return cadena;
	}
	
	public String readStackTrace(Exception e) { 
		StringWriter sw = new StringWriter(); 
		PrintWriter pw = new PrintWriter(sw); 
		e.printStackTrace(pw); 
		return sw.toString(); 
	}
	
	public static String convertirFormatoMoneda(Double d) {
        DecimalFormat df = new DecimalFormat(d%0 == 0 ? "###,###.##" : "###,##0.00"); 
        String moneda = df.format(d)+"";
        moneda = moneda.replace(",", "*");
        moneda = moneda.replace(".", ",");
        moneda = moneda.replace("*", ".");  
        return moneda;
    }
}
