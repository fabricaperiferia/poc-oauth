/*
 * @(#) ResponseObject.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase POJO para respuesta del servicio REST
 * 
 * @author Andrés Motavita
 *
 */
@Getter
@Setter
public class ResponseObject {
	/**
	 * Código de respuesta
	 */
	private int code;
	/**
	 * Mensaje de respuesta
	 */
	private String message;
	/**
	 * Mensaje detallado
	 */
	private String detailedMessage;

}