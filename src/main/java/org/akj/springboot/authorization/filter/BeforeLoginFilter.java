package org.akj.springboot.authorization.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

public class BeforeLoginFilter extends GenericFilterBean {

	private Logger log = LoggerFactory.getLogger(BeforeLoginFilter.class);

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException, IOException, ServletException {
		log.info("This is a filter before UsernamePasswordAuthenticationFilter.");
		filterChain.doFilter(servletRequest, servletResponse);
	}
}