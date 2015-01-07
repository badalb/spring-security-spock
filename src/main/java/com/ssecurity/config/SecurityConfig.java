package com.ssecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
@EnableWebSecurity
@Order(Ordered.LOWEST_PRECEDENCE - 6)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomSecurityFailureHandler customSecurityFailureHandler;

	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Autowired
	private AjaxTimeoutRedirectFilter ajaxTimeoutRedirectFilter;
	

	@Autowired
	CustomSecuritySuccessHandler customSecuritySuccessHandler;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password")
                .roles("ADMIN", "USER");
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/login.html", "/app/**", "/assets/**", "/login","/failure",
						"/api/v1/*","/register").permitAll().anyRequest().authenticated();
		http.formLogin().loginPage("/login").failureUrl("/")
				.successHandler(customSecuritySuccessHandler)
				.failureHandler(customSecurityFailureHandler).permitAll().and()

				.logout().logoutSuccessUrl("/login").permitAll().and()
	            .rememberMe().and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
		http.addFilterAfter(ajaxTimeoutRedirectFilter, ExceptionTranslationFilter.class);
		
		return;
	}

}