package com.ssecurity.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("deprecation")
@RestController
public class LoginController {

	@RequestMapping("/user_login/{userId}/{password}")
	public @ResponseBody String login(@PathVariable String userId,
			@PathVariable String password) {
		
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new GrantedAuthorityImpl("ADMIN");
		auth.add(authority);

		Authentication authentication = new UsernamePasswordAuthenticationToken(
				userId, password, auth);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "success";
	}
}
