package com.arphoenix.microservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arphoenix.microservice.dataprovider.DataProxy;
import com.arphoenix.microservice.model.AreaDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataProviderService {

	private final DataProxy dataProxy;

	public ResponseEntity<List<AreaDTO>> listAll() {
		return dataProxy.listAll();
	}
}