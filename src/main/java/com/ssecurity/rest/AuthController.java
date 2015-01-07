package com.ssecurity.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssecurity.view.AuthRepresentation;
import com.ssecurity.view.ViewMapper;

@RestController
public class AuthController {

	@Autowired
	private ViewMapper<AuthRepresentation> userViewMapper;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public @ResponseBody
	AuthRepresentation success(HttpServletRequest request,
			HttpServletResponse response) {

		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		AuthRepresentation auth = userViewMapper.map(user,
				AuthRepresentation.class);
		auth.setStatusCode("200");
		auth.setMessage("Authentication Successful");
		return auth;

	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/failure", method = RequestMethod.GET)
	public @ResponseBody
	AuthRepresentation failure(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		response.setStatus(400);
		AuthRepresentation auth = new AuthRepresentation();
		auth.setStatusCode("400");

		AuthenticationException exception = (AuthenticationException) request
				.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

		if (exception instanceof UsernameNotFoundException) {

			auth.setMessage("Invalid User id.");
			return auth;

		}
		if (exception instanceof BadCredentialsException) {
			auth.setMessage("BadCredential");
		}

		auth.setMessage("Authentication Failed!!!!.");
		return auth;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public @ResponseBody
	AuthRepresentation accessDenied(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		response.setStatus(400);
		AuthRepresentation auth = new AuthRepresentation();
		auth.setStatusCode("400");

		AuthenticationException exception = (AuthenticationException) request
				.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

		if (exception instanceof UsernameNotFoundException) {

			auth.setMessage("Invalid User id.");
			return auth;

		}
		if (exception instanceof BadCredentialsException) {
			auth.setMessage("BadCredential");
		}

		auth.setMessage("Authentication Failed!!!!.");
		return auth;
	}
	

}
