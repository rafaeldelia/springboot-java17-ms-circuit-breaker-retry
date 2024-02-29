package com.arphoenix.microservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/circuit")
public class CircuitBreakerController {

	private final CircuitBreakerRegistry circuitBreakerRegistry;

	/**
	 * It disables the given Circuit Breaker.
	 *
	 * @param circuitBreakerName
	 */
	@PutMapping(value = "/disable/{circuit_breaker}")
	public void disableCircuitBreaker(@PathVariable("circuit_breaker") String circuitBreakerName) {
		try {
			circuitBreakerRegistry.circuitBreaker(circuitBreakerName).transitionToDisabledState();
		} catch (Exception exception) {
			log.error("Circuit Breaker with name '{}' doesn't exist", circuitBreakerName);
			throw new RuntimeException(String.format("%s doesn't exist", circuitBreakerName));
		}
		log.info(" {} circuit breaker is now in disabled state.", circuitBreakerName);
	}

	/**
	 * It enables the given Circuit Breaker.
	 *
	 * @param circuitBreakerName
	 */
	@PutMapping(value = "/enable/{circuit_breaker}")
	public void enableCircuitBreaker(@PathVariable("circuit_breaker") String circuitBreakerName) {
		try {
			circuitBreakerRegistry.circuitBreaker(circuitBreakerName).transitionToClosedState();
		} catch (Exception exception) {
			log.error("Circuit Breaker with name '{}' doesn't exist", circuitBreakerName);
			throw new RuntimeException(String.format("%s doesn't exist", circuitBreakerName));
		}
		log.info(" {} circuit breaker is now in closed state.", circuitBreakerName);
	}
}
