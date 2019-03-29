/*
 * @(#) AuthenticationEntryPoint.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Clase componente para manejo de punto de entrada para autorización
 * 
 * @author Andrés Motavita
 *
 */
@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	/**
	 * Método para enrutamiento de la autorización
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
			throws IOException, ServletException {
		response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		writer.println("HTTP Status 401 - " + authEx.getMessage());
	}

	/**
	 * Método que establece el nombre del dominio posterior a establecer propiedades
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("pocaval");
		super.afterPropertiesSet();
	}

}
