/*
 * @(#) ApplicationProperties.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Clase de configuración para la carga de propiedades de configuración. Toma
 * las propiedades del archivo application.properties bajo la directiva app.
 * 
 * @author Andrés Motavita
 *
 */
@Configuration
@ConfigurationProperties("app")
@Data
public class ApplicationProperties {
	/**
	 * Identificador del recurso
	 */
	private String resourceId;
	/**
	 * Identificador del cliente
	 */
	private String clientId;
	/**
	 * Clave del cliente
	 */
	private String clientSecret;
	/**
	 * Tipo de permisos que se conceden
	 */
	private String grantType;
	/**
	 * Alcance de lectura
	 */
	private String readScope;
	/**
	 * Alcance de escritura
	 */
	private String writeScope;
	/**
	 * Tiempo en segundos para validación de Token
	 */
	private Integer accessToken;
	/**
	 * Tiempo en segundos para refrescar Token
	 */
	private Integer refreshToken;
}
