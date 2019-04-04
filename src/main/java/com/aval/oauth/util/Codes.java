/*
 * @(#) Codes.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth.util;

/**
 * Enumeración de códigos de respuesta
 * 
 * @author Andrés Motavita
 *
 */
public enum Codes {
	SUCCESS(1000, "Success"), ERROR_TOKEN(2033, "Error Token");
	/**
	 * Código
	 */
	private final int value;
	/**
	 * Etiqueta
	 */
	private final String label;

	/**
	 * Constructor
	 * 
	 * @param value
	 * @param label
	 */
	private Codes(int value, String label) {
		this.value = value;
		this.label = label;
	}

	/**
	 * Getter value
	 * 
	 * @return value
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Getter label
	 * 
	 * @return label
	 */
	public String getLabel() {
		return this.label;
	}
}
