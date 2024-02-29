package com.arphoenix.microservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arphoenix.microservice.model.AreaDTO;
import com.arphoenix.microservice.service.DataProviderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
public class DataController {

	private final DataProviderService dataProviderService;

	@GetMapping
	public List<AreaDTO> getUsers() {
		return dataProviderService.listAll().getBody();
	}
}