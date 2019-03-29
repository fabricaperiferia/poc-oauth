/*
 * @(#) AuthUser.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth.dto;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Clase de implementación de UserDetails
 * 
 * @author Andrés Motavita
 *
 */
public class AuthUser implements UserDetails {

	/**
	 * Serial de la clase
	 */
	private static final long serialVersionUID = -624215884845261514L;
	/**
	 * Nombre de usuario
	 */
	private String username;
	/**
	 * Contraseña de usuario
	 */
	private String password;
	/**
	 * Autorizaciones
	 */
	private List<SimpleGrantedAuthority> authorities;

	/**
	 * Constructor con parámetros
	 * 
	 * @param username
	 * @param password
	 * @param authorities
	 */
	public AuthUser(String username, String password, List<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
		this.username = username;
		this.password = password;
	}

	/**
	 * Getter Autorizaciones
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * Getter password
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * Getter username
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * Método que indica si la cuenta no ha expirado
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Método que indica si la cuenta no está bloqueada
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Método que indica si las credenciales no están expiradas
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Método que indica si el usuario está habilitado
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
