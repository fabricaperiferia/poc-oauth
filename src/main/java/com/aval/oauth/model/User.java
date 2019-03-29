/*
 * @(#) User.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

/**
 * Clase de documento de usuario
 * 
 * @author Andrés Motavita
 *
 */
@Data
@Document(collection = "user")
public class User {
	/**
	 * Identificador
	 */
	@Id
	private ObjectId id;
	/**
	 * Nombre de usuario
	 */
	@Field("username")
	private String username;
	/**
	 * Contraseña
	 */
	@Field("password")
	private String password;
}
