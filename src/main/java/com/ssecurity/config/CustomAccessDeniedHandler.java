package com.ssecurity.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

	@Override
	public void handle(HttpServletRequest _request,
			HttpServletResponse _response, AccessDeniedException _exception)
			throws IOException, ServletException {
		setErrorPage("/accessDenied");
		super.handle(_request, _response, _exception);
	}
}
