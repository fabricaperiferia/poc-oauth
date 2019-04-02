/*
 * @(#) TokenController.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aval.oauth.dto.ResponseObject;
import com.aval.oauth.service.TokenService;
import com.aval.oauth.util.Codes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Clase de controlador Rest
 * 
 * @author Andrés Motavita
 *
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Token System")
public class TokenController {

	/**
	 * Servicio de token
	 */
	@Autowired
	private TokenService tokenService;

	/**
	 * Método Get para validación de token
	 * 
	 * @param request
	 * @return ResponseObject con cabecera http aceptada en caso que el token sea
	 *         válido
	 */
	@ApiOperation(value = "Valida un token", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Token válido"),
			@ApiResponse(code = 401, message = "Token inválido. No es autorizado") })
	@GetMapping(value = "/validateToken")
	public ResponseEntity<ResponseObject> validateToken(HttpServletRequest request) {
		ResponseObject response = tokenService.validateToken(request);
		if (response.getCode() == Codes.SUCCESS.getValue()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
}
