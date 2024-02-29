package com.arphoenix.microservice.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaDTO implements Serializable {

	private static final long serialVersionUID = 5831132908711002407L;
	private String id;
	private String codigoArea;
	private String descricao;
	private String status;
}