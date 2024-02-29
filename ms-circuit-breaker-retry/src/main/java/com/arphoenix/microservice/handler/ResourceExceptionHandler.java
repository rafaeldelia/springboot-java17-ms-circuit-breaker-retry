package com.arphoenix.microservice.handler;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.arphoenix.microservice.response.ResponseMessageError;

import lombok.extern.slf4j.Slf4j;

/**
 * Handle das exceptions capturadas e devolvidas para o Controller.
 *
 * @author rsdelia
 */
@Slf4j
@ControllerAdvice
public class ResourceExceptionHandler {

	/**
	 * Captura as Exceções do Exception
	 *
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessageError> handleException(Exception e, HttpServletRequest request) {
		return popularResponseMessageError(e, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request);
	}

	/**
	 * Método que popula o response para cada exceção.
	 *
	 * @param e
	 * @param status
	 * @param mensagemErro
	 * @param request
	 * @return ResponseEntity<ResponseMessageError>
	 */
	private static ResponseEntity<ResponseMessageError> popularResponseMessageError(Exception e, Integer status, String mensagemErro,
			HttpServletRequest request) {
		e.printStackTrace();
		log.error("ERROR: [" + mensagemErro + "]");
		ResponseMessageError err = new ResponseMessageError();
		err.setTimestamp(Instant.now());
		err.setStatus(status);
		err.setError(mensagemErro);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}