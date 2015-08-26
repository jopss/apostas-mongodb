package com.jopss.apostas.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.Properties;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

/**
 * Formatacao e conversao de valores diversos.
 */
public final class FormatterAndValues {
	
	public static <T> T convertJsonToObject(String json, Class clazz) {
		Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("dd/MM/yyyy HH:mm:ss").excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC).setExclusionStrategies(
				new ExcludeJSonStrategy()).create();

		return (T) gson.fromJson(json, clazz);
	}
	
	/**
	 * Transforma um objeto em sua representacao JSon.<br/><br/>
	 * Remove do JSON os campos transientes, estaticos e que contenha a anotacao @ExcludeJSon. Ainda, formata o valor das datas para padrao pt-BR (dd/MM/yyyy HH:mm:ss).
	 */
	public static String convertToJson(Object obj) {

		Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("dd/MM/yyyy HH:mm:ss")
			.excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
			.setExclusionStrategies(new ExcludeJSonStrategy()).create();

		return gson.toJson(obj);
	}
	
	/**
	 * Retorna o valor de uma chave do arquivo "messages.properties".
	 */
	public static String getMessage(String key, String... args) {

		try {
			InputStream istream = FormatterAndValues.class.getClassLoader().getResourceAsStream("messages.properties");
			if (istream != null) {
				Properties props = new Properties();
				props.load(istream);
                                
                                String value = props.getProperty(key.trim());
                                
                                if(args!=null && args.length > 0){
                                        return MessageFormat.format(value, args);
                                }
                                
				return value;
			}
			return key;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getMessage(String file, String key) {

		try {
			InputStream istream = FormatterAndValues.class.getClassLoader().getResourceAsStream(file);
			if (istream != null) {
				Properties props = new Properties();
				props.load(istream);
				return props.getProperty(key.trim());
			}
			return key;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String encryptMD5(String text) {
		return encryptMD5(text.getBytes());
	}
	
	public static String encryptMD5(byte[] text) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text);
			BigInteger hash = new BigInteger(1, md.digest());
			return org.apache.commons.lang.StringUtils.leftPad(hash.toString(16), 32, '0');
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex);
		}
	}
}
