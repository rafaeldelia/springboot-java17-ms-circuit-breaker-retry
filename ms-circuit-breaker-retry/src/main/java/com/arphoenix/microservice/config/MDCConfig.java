package com.arphoenix.microservice.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arphoenix.microservice.filter.MDCFilter;

import lombok.RequiredArgsConstructor;

/**
 * A filter that adds a key to the Mapped Diagnostic Context (MDC) to each request so you can print a unique id in the log messages of
 * eachrequest
 * 
 * @author rsdelia
 *
 */
@Configuration
@RequiredArgsConstructor
public class MDCConfig {
	private final MDCFilter slf4jMDCFilter;

	@Bean
	public FilterRegistrationBean<MDCFilter> servletRegistrationBean() {
		final FilterRegistrationBean<MDCFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(slf4jMDCFilter);
		filterRegistrationBean.setOrder(2);
		return filterRegistrationBean;
	}
}