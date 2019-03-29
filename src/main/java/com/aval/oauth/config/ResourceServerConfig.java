/*
 * @(#) ResourceServerConfig.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * Clase encargada de configurar el servidor de recursos
 * 
 * @author Andrés Motavita
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/**
	 * Punto de entrada de las peticiones
	 */
	@Autowired
	private BasicAuthenticationEntryPoint basicAuthenticationPoint;
	/**
	 * Propiedades de la aplicación
	 */
	@Autowired
	private ApplicationProperties applicationProperties;

	/**
	 * Método de configuración de seguridad del servidor de recursos
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(applicationProperties.getResourceId()).stateless(false);
	}

	/**
	 * Método de configuración de seguridad
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().anonymous().disable().authorizeRequests()
				.antMatchers("/goal/{\\d+}", "/users/password/**").access("hasRole('USER')").and().exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler()).and().httpBasic()
				.authenticationEntryPoint(basicAuthenticationPoint).and().authorizeRequests().antMatchers("/none/**")
				.authenticated();
	}
}