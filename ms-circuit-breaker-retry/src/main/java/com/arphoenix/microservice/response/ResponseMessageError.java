package com.arphoenix.microservice.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe default de resposta de erro da aplicação.
 *
 * @author rsdelia
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessageError {
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}