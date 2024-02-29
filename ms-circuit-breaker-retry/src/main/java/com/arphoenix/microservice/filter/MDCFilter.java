package com.arphoenix.microservice.filter;

import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * A filter that adds a key to the Mapped Diagnostic Context (MDC) to each request so you can print a unique id in the log messages of each
 * request
 **/
@EqualsAndHashCode(callSuper = false)
@Component
@Slf4j
public class MDCFilter extends OncePerRequestFilter {

	private static final String MDC_UUID_TOKEN_KEY = "uuid";

	@Value("${HOSTNAME:unknown}")
	private String podName;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) {
		try {
			StringBuilder sb = this.formatarMdcLog(request);
			MDC.put(MDC_UUID_TOKEN_KEY, sb.toString());
			chain.doFilter(request, response);
		} catch (Exception ex) {
			log.error("Exception occurred in filter while setting UUID for logs", ex);
		} finally {
			MDC.remove(MDC_UUID_TOKEN_KEY);
		}
	}

	/**
	 * @param request
	 * @return
	 */
	private StringBuilder formatarMdcLog(final HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		String referer = request.getHeader(HttpHeaders.REFERER);
		sb.append("[");
		sb.append(UUID.randomUUID().toString());
		sb.append("] [");
		sb.append(referer);
		sb.append("] [");
		sb.append(podName);
		sb.append("]");
		return sb;
	}

	@Override
	protected boolean isAsyncDispatch(final HttpServletRequest request) {
		return false;
	}

	@Override
	protected boolean shouldNotFilterErrorDispatch() {
		return false;
	}
}