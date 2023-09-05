package com.learn.question.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
		/*
		 * http.authorizeRequests().requestMatchers(null)
		 * http.csrf().disable().authorizeRequests().antMatchers("/**").authenticated().
		 * and().httpBasic().and()
		 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		 * and() .addFilterBefore(new AuthenticationFilter(),
		 * UsernamePasswordAuthenticationFilter.class);
		 * 
		 * return http.build();
		 * 
		 */
		/*
		 * httpSecurity.authorizeHttpRequests((authz) -> authz
		 * .mvcMatchers("/admin/**").hasRole("ADMIN") .anyRequest().authenticated())
		 * .sessionManagement(sess ->
		 * sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		 * .addFilterBefore(new AuthenticationFilter(),
		 * UsernamePasswordAuthenticationFilter.class) .build();
		 */
		MvcRequestMatcher mvcRequestMatcher = new MvcRequestMatcher(introspector, "/**");
		
		return httpSecurity.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
						auth -> auth.requestMatchers(mvcRequestMatcher).permitAll().anyRequest().authenticated())
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.build();
	}

}