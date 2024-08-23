/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ms.afinity.config;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseService<T> {
	public static final String MSGCORRECTO = "Datos devueltos correctamente";
	public static final String MSGERRORSOLICITUD = "Ocurri\u00f3 un error al ";
	public static final String MSGERROR = "Error al recuperar los datos";
	
	public static final String MSGCORRECTOGUARDAR = "Se registr\u00f3 correctamente ";
	public static final String MSGERRORSOLICITUDGUARDAR = "Ocurri\u00f3 un error al guardar ";
	public static final String MSGERRORGUARDAR = "Error al guardar los datos";

	public static final String MSGCORRECTOACTUALIZAR= "Se actualiz\u00f3 correctamente ";
	public static final String MSGERRORSOLICITUDACTUALIZAR = "Ocurri\u00f3 un error al actualizar ";
	public static final String MSGERRORACTUALIZAR = "Error al actualizar los datos";

	private boolean success;
	private T data;
	private String message;

	public ResponseService() {

	}

	public ResponseService(String message) {
		super();
		this.message = message;
	}

	public ResponseEntity<ResponseService<T>> getResponseError(String message) {
		this.success = false;
		this.message = message;
		return new ResponseEntity<>(this, HttpStatus.EXPECTATION_FAILED);
	}
	
	public ResponseEntity<ResponseService<T>> getResponseError(@NotNull T data, String message) {
		this.data = data;
		this.success = false;
		this.message = message;
		return new ResponseEntity<>(this, HttpStatus.EXPECTATION_FAILED);
	}

	public ResponseEntity<ResponseService<T>> getResponse(@NotNull T data, String message) {
		this.success = true;
		this.data = data;
		this.message = message;
		return new ResponseEntity<>(this, HttpStatus.OK);
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
