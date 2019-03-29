/*
 * @(#) AuthorizationServerConfig.java
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.aval.oauth.exception.CustomOauthException;

/**
 * Clase de configuración de servidor de autorización.
 * 
 * @author Andrés Motavita
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	/**
	 * Almacén de Tokens
	 */
	@Autowired
	private TokenStore tokenStore;
	/**
	 * Controlador de aprovación de usuarios
	 */
	@Autowired
	private UserApprovalHandler userApprovalHandler;
	/**
	 * Propiedades de aplicación
	 */
	@Autowired
	private ApplicationProperties applicationProperties;
	/**
	 * Administrador de autenticación
	 */
	@Autowired
	private AuthenticationManager authenticationManager;
	/**
	 * Codificador de contraseñas bajo BCrypt
	 */
	@Autowired
	private BCryptPasswordEncoder encoder;

	/**
	 * Método sobre cargado de configuración de servicio
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
		configurer.inMemory().withClient(applicationProperties.getClientId())
				.secret(encoder.encode(applicationProperties.getClientSecret()))
				.authorizedGrantTypes(applicationProperties.getGrantType())
				.scopes(applicationProperties.getReadScope(), applicationProperties.getWriteScope())
				.accessTokenValiditySeconds(applicationProperties.getAccessToken())
				.refreshTokenValiditySeconds(applicationProperties.getRefreshToken());
	}

	/**
	 * Bean de mejora de token para el contexto
	 * 
	 * @return CustomTokenEnhancer
	 */
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

	/**
	 * Método de configuración de mejora del token para conexiones al servicio de
	 * autorización
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer()));
		endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
				.authenticationManager(authenticationManager).exceptionTranslator(e -> {
					if (e instanceof OAuth2Exception) {
						OAuth2Exception oAuth2Exception = (OAuth2Exception) e;

						return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
								.body(new CustomOauthException(oAuth2Exception.getMessage(), oAuth2Exception));
					}
					throw e;
				}).tokenEnhancer(tokenEnhancerChain);
	}
}
