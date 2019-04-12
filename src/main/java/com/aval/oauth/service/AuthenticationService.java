/*
 * @(#) AuthenticationService.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;

import com.aval.oauth.dto.AuthUser;
import com.aval.oauth.model.User;
import com.aval.oauth.repository.UserRepository;

/**
 * Servicio de autenticación
 * 
 * @author Andrés Motavita
 *
 */
@Service
public class AuthenticationService implements AuthenticationProvider {
	/**
	 * Repositorio de user
	 */
	@Autowired
	private UserRepository userRepository;
	/**
	 * Codificador de contraseñas BCrypt
	 */
	@Autowired
	private BCryptPasswordEncoder encoder;

	/**
	 * Método para obtención de autorizaciones
	 * 
	 * @return Listado de autorizaciones
	 */
	private List<SimpleGrantedAuthority> getUserAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}

	/**
	 * Método de autenticación
	 */
	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent() && encoder.matches(password, user.get().getPassword())) {
		
			return new UsernamePasswordAuthenticationToken(
					new AuthUser(username, user.get().getPassword(), getUserAuthority()), password, getUserAuthority());
		} else {
			throw new OAuth2Exception("Credenciales Inválidas");
		}
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
