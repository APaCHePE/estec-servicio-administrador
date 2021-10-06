package com.pe.estec.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase para utilitarios de tipo string
 * @author Lizandro Alipazaga
 *
 */
public class UtilString {
	private static final String CADENA_TILDE_N = "&Ntilde;";
	private static final String PATRON_TILDE_N = "Ñ";
	
	private static final String CADENA_TILDE_NM = "&ntilde;";
	private static final String PATRON_TILDE_NM = "ñ";
	
	/**
	 * Método que remplaza los caracteres especiales de tipo &xtilde;
	 * @param cadena Elemento al que se le reemplazará los caracteres
	 * @return Regresa el mismo parametro de entrada con los caracteres reemplazados.
	 */
	public static String reemplazaCaracteres(String cadena){
		cadena.replace(CADENA_TILDE_NM, PATRON_TILDE_NM).replace(CADENA_TILDE_N, PATRON_TILDE_N);
		
		return cadena;
	}
	public static String retornarEncryptMd5(String cadenaSinEncriptar) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.reset();
		md.update(cadenaSinEncriptar.getBytes());
		byte[] digest = md.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashText = bigInt.toString(16);
		 while (hashText.length() < 32) {
			 hashText = "0" + hashText;
		 }
		return hashText;
	}
}
