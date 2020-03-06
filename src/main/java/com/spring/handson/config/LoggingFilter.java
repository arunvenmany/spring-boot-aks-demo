package com.spring.handson.config;

import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class LoggingFilter extends OncePerRequestFilter {

	private String appUuidKey;

	public LoggingFilter(final String appUuidKey) {
		this.appUuidKey = appUuidKey;
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {

		try {
			final String token = StringUtils.isNotBlank(request.getHeader(appUuidKey))
					? request.getHeader(appUuidKey) :
					UUID.randomUUID().toString().toUpperCase().replace("-", "");
			MDC.put(appUuidKey, token);
			response.addHeader(appUuidKey, token);
			logger.info("request Uri: " + request.getRequestURI()
					+ (request.getQueryString() != null ? "?" + request.getQueryString() : ""));
			filterChain.doFilter(request, response);
		} finally {
			logger.info("response Status: " + response.getStatus());
			logger.info("response Timestamp: " + response.getHeader("Date"));
			logger.info("response Transaction Id: " + response.getHeader(appUuidKey));
			MDC.clear();
		}
	}
}