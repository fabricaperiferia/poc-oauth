/*
 * @(#) OauthApp.java
 * Copyright (c) Periferia IT Group, Todos Los Derechos Reservados
 * 
 * Este software es confidencial y propiedad de Periferia IT Group. El uso y
 * distribución está restringido a las actividades comerciales e internas de la
 * compañía.
 * 
 */
package com.aval.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal encargada de iniciar la aplicación
 * 
 * @author Andrés Motavita
 *
 */
@SpringBootApplication
public class OauthApp {
	/**
	 * Método principal para iniciar la aplicación
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(OauthApp.class, args);
	}
}
