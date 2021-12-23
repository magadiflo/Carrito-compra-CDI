package org.aguzman.apiservlet.webapp.headers.configs;

import jakarta.inject.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Este es un calificador, crear una anotación personalizada que esté
 * anotada con @Qualifier le damos un nombre, un retention y un target (dónde lo vamos a aplicar)
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE, ElementType.CONSTRUCTOR})
public @interface ProductoServicePrincipal {

}
