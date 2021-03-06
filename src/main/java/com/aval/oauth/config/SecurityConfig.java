/*
 * @(#) SecurityConfig.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.aval.oauth.service.AuthenticationService;

/**
 * Clase de configuración de seguridad para los servicios
 * 
 * @author Andrés Motavita
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Servicio de autenticación
	 */
	@Autowired
	private AuthenticationService authenticationService;
	/**
	 * Propiedades de la aplicación
	 */
	@Autowired
	private ApplicationProperties applicationProperties;

	/**
	 * Método de administración de autenticación
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * Configuración global de resolución de peticiones
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(applicationProperties.getClientId())
				.password(applicationProperties.getClientSecret()).roles("ANONYMOUS");
	}

	/**
	 * Configuración de proveedor de autenticación
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationService);
	}

	/**
	 * Bean de almacén de tokens
	 * 
	 * @return InMemoryTokenStore
	 */
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

	/**
	 * Bean de contexto de Controlador de aprobación de usuarios
	 * 
	 * @param tokenStore           Almacén del token
	 * @param clientDetailsService servicio de detallado de los clientes
	 * @return TokenStoreUserApprovalHandler
	 */
	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore,
			ClientDetailsService clientDetailsService) {
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}

	/**
	 * Bean de contexto de almacén de aprobación
	 * 
	 * @param tokenStore Almacén de tokens
	 * @return ApprovalStore
	 */
	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore) {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}

	/**
	 * Bean para contexto de codificador de contraseñas BCrypt
	 * 
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Bean para contexto de filtro CORS
	 * 
	 * @return SimpleCorsFilter
	 */
	@Bean
	public SimpleCorsFilter corsFilter() {
		return new SimpleCorsFilter();
	}

	/**
	 * Método de configuración de seguridad para autorización de peticiones
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
	}

	/**
	 * Bean de configuración CORS
	 * 
	 * @return CorsConfigurationSource
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin",
				"Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin", "Cache-Control",
				"Content-Type", "Authorization"));
		configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
		return source;
	}
}
