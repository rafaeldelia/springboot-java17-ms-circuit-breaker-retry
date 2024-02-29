package com.arphoenix.microservice.dataprovider;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.arphoenix.microservice.exception.NotFoundException;
import com.arphoenix.microservice.model.AreaDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "ms-cache", url = "${data.url}")
public interface DataProxy {
	Logger log = LoggerFactory.getLogger(DataProxy.class);

	@GetMapping
	@Retry(name = "redisDistributedProxyRetry")
	@CircuitBreaker(name = "redisDistributedProxyCircuitBreaker", fallbackMethod = "serviceListAllFallbackMethod")
	ResponseEntity<List<AreaDTO>> listAll();

	default ResponseEntity<List<AreaDTO>> serviceListAllFallbackMethod(Throwable exception) throws NotFoundException {
		log.error("Houve um problema na comunicação do microserviço {}", exception.getMessage());
		throw new NotFoundException(exception.getMessage());
	}
}