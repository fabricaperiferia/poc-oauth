/*
 * @(#) TokenService.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import com.aval.oauth.dto.ResponseObject;
import com.aval.oauth.util.Codes;

/**
 * Servicio de token
 * 
 * @author Andrés Motavita
 *
 */
@Service
public class TokenService {
	/**
	 * Almacén de tokens
	 */
	@Autowired
	private TokenStore tokenStore;

	/**
	 * Método de validación de token
	 * 
	 * @param request
	 * @return
	 */
	public ResponseObject validateToken(HttpServletRequest request) {
		ResponseObject response = new ResponseObject();
		if (request == null || request.getHeader(HttpHeaders.AUTHORIZATION) == null) {
			response.setCode(Codes.ERROR_TOKEN.getValue());
			response.setDetailedMessage(Codes.ERROR_TOKEN.getLabel());
			return response;
		}
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		token = token.substring(7);
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
		if (!accessToken.isExpired()) {
			response.setCode(Codes.SUCCESS.getValue());
		} else {
			response.setCode(Codes.ERROR_TOKEN.getValue());
			response.setDetailedMessage(Codes.ERROR_TOKEN.getLabel());
		}
		return response;
	}
}
