/*
 * @(#) CustomOauthException.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

public class CustomOauthException extends OAuth2Exception {

	/**
	 * Serial de la clase
	 */
	private static final long serialVersionUID = 8013603780775312923L;

	/**
	 * Constructor con parámetros
	 * 
	 * @param msg
	 * @param t
	 */
	public CustomOauthException(String msg, Throwable t) {
		super(msg, t);
	}
}