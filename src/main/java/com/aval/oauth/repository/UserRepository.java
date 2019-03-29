/*
 * @(#) UserRepository.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.aval.oauth.model.User;

/**
 * Interfaz para consulta del documento User
 * 
 * @author Andrés Motavita
 *
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
	/**
	 * Método de búsqueda de usuario por nombre de usuario
	 * 
	 * @param username
	 * @return Optional con objeto usuario si existe, null en caso contrario
	 */
	@Query("{ 'username' : ?0 }")
	public Optional<User> findByUsername(String username);
}
